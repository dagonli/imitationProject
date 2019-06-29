package com.diy.dagon.transport;

import com.diy.dagon.request.RpcRequest;
import com.diy.dagon.util.StreamCloserUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Dagon on 2019/6/29 - 10:02
 */
public class RpcNetTransport {

    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object sendRequest(RpcRequest request) {
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Object result = null;
        try {
            socket = new Socket(host, port);
            System.out.println("连接服务端成功");

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            oos.flush();

            ois = new ObjectInputStream(socket.getInputStream());
            result = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            StreamCloserUtil.close(oos);
            StreamCloserUtil.close(ois);
            StreamCloserUtil.close(socket);
        }
        return result;

    }
}
