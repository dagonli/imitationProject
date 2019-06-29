package com.diy.dagon.service.impl;

import com.diy.dagon.annotation.RpcService;
import com.diy.dagon.pojo.User;
import com.diy.dagon.service.IHelloSerivce;

/**
 * Created by Dagon on 2019/6/29 - 9:05
 */
@RpcService(value = IHelloSerivce.class, version = "v2.0")
public class HelloServiceImpl2 implements IHelloSerivce {
    @Override
    public String sayHello(String msg) {
        System.err.println("v2.0_say hello:"+msg);
        return "say hello:"+msg;
    }

    @Override
    public String saveUser(User user) {
        System.err.println("v2.0_save user:" + user);
        return "SUCCESS";
    }
}
