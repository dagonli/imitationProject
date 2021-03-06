package com.diy.dagon.remote;

import com.diy.dagon.handler.RpcClientInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * Created by Dagon on 2019/6/29 - 9:45
 *
 * 采用jdk的动态代理
 */
public class RpcClientProxy {

    public static <T> T proxy(final Class<?> interfaceCls, final String host, final int port) {

        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{interfaceCls},
                new RpcClientInvocationHandler(host, port));
    }

}
