package com.diy.dagon.remote;

import com.diy.dagon.annotation.RpcService;
import com.diy.dagon.handler.RpcRequestProcessHandler;
import com.diy.dagon.util.StreamCloserUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Dagon on 2019/6/29 - 11:29
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {

    ExecutorService executorService = Executors.newCachedThreadPool();

    private Map<String, Object> handlerMap = new HashMap<>();

    private int port;

    public RpcServer(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);//启动服务
            System.out.println("服务启动成功，端口：" + port);
            while (true) {
                Socket socket = serverSocket.accept();//典型的BIO模型

                //优化：每接收到一个新请求，交给线程池异步处理，不阻塞！
                executorService.execute(new RpcRequestProcessHandler(socket, handlerMap));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            StreamCloserUtil.close(serverSocket);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);

        if (!serviceBeanMap.isEmpty()) {
            for (Object serviceBean : serviceBeanMap.values()) {
                //拿到注解里的值
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                String serviceName = rpcService.value().getName();//拿到接口类
                String version = rpcService.version();
                if (!StringUtils.isEmpty(version)) {
                    serviceName += "-" + version;
                }
                handlerMap.put(serviceName, serviceBean);
            }
        }
    }
}
