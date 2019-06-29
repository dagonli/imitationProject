package com.diy.dagon.remote;

import com.diy.dagon.handler.RpcRequestProcessHandler;
import com.diy.dagon.util.StreamCloserUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Dagon on 2019/6/29 - 9:06
 *
 * 服务端远程代理
 */
public class RpcServerProxy {

//    ExecutorService executorService = Executors.newCachedThreadPool();
//
//    /**
//     * 发布一个服务
//     */
//    public void publisher(int port, Object service) {
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(port);//启动服务
//            System.out.println("服务启动成功，端口：" + port);
//            while (true) {
//                Socket socket = serverSocket.accept();//典型的BIO模型
//
//                //优化：每接收到一个新请求，交给线程池异步处理，不阻塞！
//                executorService.execute(new RpcRequestProcessHandler(socket, service));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            StreamCloserUtil.close(serverSocket);
//        }
//    }
}
