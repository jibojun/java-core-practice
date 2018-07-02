package io.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/5/2_11:32 PM
 */
public class NioReactorServer implements Runnable {
    private Selector selector;
    private ByteBuffer byteBuffer;
    private static final int BUFFER_SIZE=2048;
    private static final String DATA_PREFIX="data";
    private static int count=1;

    @Override
    public void run() {
        try {
            init();
            listen();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

        }
    }

    private void init() throws IOException {
        byteBuffer=ByteBuffer.allocate(BUFFER_SIZE);
        //TCP channel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(),8888));
        //non-blocking, different with BIO
        serverSocketChannel.configureBlocking(false);
        selector= Selector.open();
        //register channel with selector, register accept when init
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
    }

    private void listen() throws IOException {
        while(true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //remove, otherwise, will be repeat entering
                iterator.remove();
                handle(key);
            }
        }
    }

    private void handle(SelectionKey key) throws IOException {
        //accept request
        if(key.isAcceptable()){
            System.out.println("Accept connection request:"+key.attachment());
            //get channel and accept
            ServerSocketChannel channel= (ServerSocketChannel) key.channel();
            //register read or write
            channel.accept().configureBlocking(false).register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE );
            System.out.println("Connection finished:"+key.attachment());
        }
        //read data
        else if(key.isReadable()){
            System.out.println("Read request:"+key.attachment());
            SocketChannel channel= (SocketChannel) key.channel();
            byteBuffer.flip();
            //buffer->channel
            int count=channel.read(byteBuffer);
            System.out.println("Read finished:"+key.attachment()+",data read:"+count+",content is:"+NioUtil.castByteToString(byteBuffer));
        }
        //write data
        else if(key.isWritable()){
            System.out.println("Write request:"+key.attachment());
            SocketChannel channel= (SocketChannel) key.channel();
            //clean buffer
            byteBuffer.clear();
            //prefix
            byteBuffer.put((DATA_PREFIX+(count++)).getBytes());
            byteBuffer.flip();
            //channel->buffer
            while(byteBuffer.hasRemaining()) {
                channel.write(byteBuffer);
            }
            System.out.println("Write finished:"+key.attachment());
        }
    }


}
