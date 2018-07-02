package io.nio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/5/2_11:58 PM
 */
public class NioServerStartUp {
    private static Executor threadPool=Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //start server
        threadPool.execute(new NioReactorServer());
    }
}
