package com.study.dubbo;

/**
 * <p>
 * Created by zhangchangji on 2020/3/23.
 */
public class PrizeServiceImpl implements PrizeService {


    private static final String aa = "";


    private boolean flag = false;
    @Override
    public String getPrize() {
        boolean aa = false;
        return "默认抽奖模式";
    }

    @Override
    public void sendPrize() {
        System.out.println("默认发奖模式");
    }
}
