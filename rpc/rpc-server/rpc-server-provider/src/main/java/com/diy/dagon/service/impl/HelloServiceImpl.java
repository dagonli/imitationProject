package com.diy.dagon.service.impl;

import com.diy.dagon.pojo.User;
import com.diy.dagon.service.IHelloSerivce;

/**
 * Created by Dagon on 2019/6/29 - 9:05
 */
public class HelloServiceImpl implements IHelloSerivce {
    @Override
    public String sayHello(String msg) {
        System.err.println("say hello:"+msg);
        return "say hello:"+msg;
    }

    @Override
    public String saveUser(User user) {
        System.err.println("save user:" + user);
        return "SUCCESS";
    }
}
