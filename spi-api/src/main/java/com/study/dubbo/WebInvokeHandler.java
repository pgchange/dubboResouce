package com.study.dubbo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>
 * Created by zhangchangji on 2020/6/28.
 */
public class WebInvokeHandler<T> implements InvocationHandler {

    private T target;

    public WebInvokeHandler(T t) {
        this.target = t;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
