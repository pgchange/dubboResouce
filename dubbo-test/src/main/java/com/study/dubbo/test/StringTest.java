package com.study.dubbo.test;

/**
 * <p>
 * Created by zhangchangji on 2020/3/23.
 */
public class StringTest {

    public static void main(String[] args) {
        String a = "abcdefg";
        // 这里是从0开始，截取 1-0 = 1长度的字符串，那么结果就是a
        System.out.println(a.substring(0,1));
        // 这里从1开始，截取 3-1 = 2长度的字符串，结果就是bc
        System.out.println(a.substring(1,3));
        // 这里从4开始截取字符串，取的是 a的长度-4 长度的字符串，就是efg
        System.out.println(a.substring(4));

        for (;;) {
            System.out.println("循环");
        }
    }
}
