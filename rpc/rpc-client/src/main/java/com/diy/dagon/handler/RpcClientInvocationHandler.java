package com.diy.dagon.handler;

import com.diy.dagon.request.RpcRequest;
import com.diy.dagon.transport.RpcNetTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Dagon on 2019/6/29 - 9:47
 */
public class RpcClientInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RpcClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("come in invocation handler......");

        RpcRequest request = new RpcRequest();
        //此处是方法的声明的类
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);

        return new RpcNetTransport(host, port).sendRequest(request);
    }
}
