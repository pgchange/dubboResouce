package com.study.dubbo.test;

import com.study.dubbo.PrizeService;
import com.study.dubbo.PrizeServiceImpl;
import com.study.dubbo.WebInvokeHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * <p>
 * Created by zhangchangji on 2020/6/28.
 */
public class InvokeTest {

    public static void main(String[] args) {
        PrizeService prizeService = new PrizeServiceImpl();
        InvocationHandler invocationHandler = new WebInvokeHandler<PrizeService>(prizeService);
        PrizeService prozeProxy = (PrizeService)Proxy.newProxyInstance(PrizeService.class.getClassLoader(),
                new Class[]{PrizeService.class}, invocationHandler);
        System.out.println(prozeProxy.getPrize());
        prozeProxy.sendPrize();
    }
}
