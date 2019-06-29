package com.diy.dagon;

import com.diy.dagon.pojo.User;
import com.diy.dagon.remote.RpcClientProxy;
import com.diy.dagon.service.IHelloSerivce;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //1.先获取一个代理类
        IHelloSerivce helloSerivce = RpcClientProxy.proxy(IHelloSerivce.class, "localhost", 7777);
        String msg = helloSerivce.sayHello("dagonli");
        System.out.println(msg);
        String result = helloSerivce.saveUser(new User());
        System.out.println(result);
    }
}
