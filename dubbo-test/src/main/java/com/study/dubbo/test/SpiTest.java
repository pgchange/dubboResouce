package com.study.dubbo.test;

import com.study.dubbo.PrizeService;
import com.study.dubbo.PrizeServiceImpl;

import java.util.ServiceLoader;

/**
 * <p>
 * Created by zhangchangji on 2020/3/23.
 */
public class SpiTest {


    public static void main(String[] args) {
        ServiceLoader<PrizeService> services = ServiceLoader.load(PrizeService.class);

        for (PrizeService prizeService : services) {
            System.out.println(prizeService.getPrize());
            prizeService.sendPrize();
        }


        PrizeService prizeService = new PrizeServiceImpl();
        prizeService.sendPrize();
    }
}
