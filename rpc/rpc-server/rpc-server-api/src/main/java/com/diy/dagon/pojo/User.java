package com.diy.dagon.pojo;

import java.io.Serializable;

/**
 * Created by Dagon on 2019/6/29 - 8:57
 *
 * 需要进行网络传输，序列化
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1850200829856684892L;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
