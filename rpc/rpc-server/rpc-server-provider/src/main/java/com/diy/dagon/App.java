package com.diy.dagon;

import com.diy.dagon.config.SpringConfig;
import com.diy.dagon.remote.RpcServerProxy;
import com.diy.dagon.service.IHelloSerivce;
import com.diy.dagon.service.impl.HelloServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        IHelloSerivce helloSerivce = new HelloServiceImpl();
//        RpcServerProxy serverProxy = new RpcServerProxy();
//        serverProxy.publisher(7777, helloSerivce);


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        applicationContext.start();
    }
}
