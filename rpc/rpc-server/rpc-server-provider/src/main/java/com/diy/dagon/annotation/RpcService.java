package com.diy.dagon.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Dagon on 2019/6/29 - 11:25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component //容器管理
public @interface RpcService {

    Class<?> value();//用于获取服务的接口

    /**
     * 增加版本号，实现版本隔离
     */
    String version() default "";
}
