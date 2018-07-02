package io.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/5/2_11:57 PM
 */
public class NioClient {
    private static Executor threadPool=Executors.newCachedThreadPool();
    private static final int BUFFER_SIZE=2048;
    private static Random random=new Random();

    public static void main(String[] args) {
        //send message to server
        threadPool.execute(new Thread(() -> {
            SocketChannel channel = null;
            Selector selector = null;
            try {
                channel = SocketChannel.open();
                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
                selector = Selector.open();
                channel.register(selector, SelectionKey.OP_CONNECT);
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isConnectable()) {
                        if (channel.isConnectionPending()) {
                            //start to register read or write when connection is completed
                            if (channel.isConnected()) {
                                key.interestOps(SelectionKey.OP_WRITE);
                            } else {
                                key.cancel();
                            }
                        } else {
                            SocketChannel writeChannel = null;
                            while (true) {
                                try {
                                    TimeUnit.SECONDS.sleep(random.nextInt(5));
                                    writeChannel = SocketChannel.open();
                                    ByteBuffer byteBuffer = ByteBuffer.wrap((String.valueOf(Thread.currentThread().getId()) + "test data").getBytes());
                                    while (byteBuffer.hasRemaining()) {
                                        writeChannel.write(byteBuffer);
                                    }
                                    System.out.println(Thread.currentThread().getName() + ":write data:" + NioUtil.castByteToString(byteBuffer));
                                } catch (Exception e) {
                                    break;
                                } finally {
                                    try {
                                        if (writeChannel != null && writeChannel.isOpen()) {
                                            writeChannel.close();
                                        }
                                    } catch (Exception e) {

                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
        }));


        for (int i=0;i<5;i++) {
            threadPool.execute(new Thread(() -> {
                SocketChannel channel = null;
                Selector selector = null;
                try {
                    channel = SocketChannel.open();
                    channel.configureBlocking(false);
                    channel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
                    selector = Selector.open();
                    channel.register(selector, SelectionKey.OP_CONNECT);
                    boolean isFinished = false;

                    while (!isFinished) {
                        selector.select();
                        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            iterator.remove();
                            if (key.isConnectable()) {
                                if (channel.isConnectionPending()) {
                                    //start to register read or write when connection is completed
                                    if (channel.isConnected()) {
                                        key.interestOps(SelectionKey.OP_READ);
                                    } else {
                                        key.cancel();
                                    }
                                    //read message
                                } else if (key.isReadable()) {
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
                                    SocketChannel readChannel = (SocketChannel) key.channel();
                                    readChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(Thread.currentThread().getName() + ":read data:" + NioUtil.castByteToString(byteBuffer));
                                    byteBuffer.clear();
                                    isFinished = true;
                                    TimeUnit.SECONDS.sleep(random.nextInt(5));
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    try {
                        if (channel != null && channel.isOpen()) {
                            channel.close();
                        }
                        if (selector != null && selector.isOpen()) {
                            selector.close();
                        }
                    } catch (Exception e) {

                    }
                }
            }));
        }
    }
}
