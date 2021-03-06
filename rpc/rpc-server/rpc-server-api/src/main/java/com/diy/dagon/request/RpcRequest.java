package com.diy.dagon.request;

import java.io.Serializable;

/**
 * Created by Dagon on 2019/6/29 - 8:59
 *
 * 需要进行网络传输，序列化
 */
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -7699955108107323179L;
    private String className;
    private String methodName;
    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
