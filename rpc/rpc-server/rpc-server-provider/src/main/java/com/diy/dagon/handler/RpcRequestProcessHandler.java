package com.diy.dagon.handler;

import com.diy.dagon.request.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * Created by Dagon on 2019/6/29 - 9:19
 */
public class RpcRequestProcessHandler implements Runnable {

    private Socket socket;

    private Map<String,Object> handlerMap;

    public RpcRequestProcessHandler(Socket socket, Map<String,Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            //1.先拿到 输入流
            ois = new ObjectInputStream(socket.getInputStream());
            //2.输入流中，应该有：请求的类名/方法名/方法参数
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();
            //3.拿到这些东西，可以反射调用方法
            Object result = methodInvoke(rpcRequest);

            //4.获取输出流
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(result);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private Object methodInvoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String serviceName = rpcRequest.getClassName();

        if (!StringUtils.isEmpty(rpcRequest.getVersion())) {
            serviceName += "-" + rpcRequest.getVersion();
        }

        Object service = handlerMap.get(serviceName);
        if (service == null) {
            throw new RuntimeException("service not found,serviceName:"+serviceName);
        }



        Class<?> clazz = Class.forName(rpcRequest.getClassName());

        //要拿到Method，不要参数集合Class<?>[]
        Object[] args = rpcRequest.getParameters();
        Class<?>[] types = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        Method method = clazz.getMethod(rpcRequest.getMethodName(), types);

        /**
         * 此处方法调用，需要传入实体类，考虑当做构造参数传进来
         *
         * 不能根据rpcRequest的className实例一个，因为可能是接口/抽象类
         */
        return method.invoke(service, args);
    }
}
