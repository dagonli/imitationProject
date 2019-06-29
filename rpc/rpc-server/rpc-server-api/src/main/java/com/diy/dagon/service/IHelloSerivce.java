package com.diy.dagon.service;

import com.diy.dagon.pojo.User;

/**
 * Created by Dagon on 2019/6/29 - 8:58
 */
public interface IHelloSerivce {

    String sayHello(String msg);

    String saveUser(User user);

}
