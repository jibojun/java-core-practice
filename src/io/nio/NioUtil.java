package io.nio;

import java.nio.ByteBuffer;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/5/3_1:18 AM
 */
public class NioUtil {

    public static String castByteToString(ByteBuffer byteBuffer){
        StringBuilder sb=new StringBuilder();
        try
        {
            for(int i = 0; i<byteBuffer.position();i++){
                sb.append((char)byteBuffer.get(i));
            }
            return sb.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "";
        }
    }
}
