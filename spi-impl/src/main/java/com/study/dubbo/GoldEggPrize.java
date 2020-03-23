package com.study.dubbo;

/**
 * <p>
 * Created by zhangchangji on 2020/3/23.
 */
public class GoldEggPrize implements PrizeService {
    @Override
    public String getPrize() {
        return "砸金蛋抽奖";
    }

    @Override
    public void sendPrize() {

        System.out.println("砸金蛋发奖");
    }
}
