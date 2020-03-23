package com.study.dubbo.test;

/**
 * <p>
 * Created by zhangchangji on 2020/3/23.
 */
public class StringTest {

    public static void main(String[] args) {

        String a = "123";
        String b = new String("123");
        System.out.println(a == b);
        String c = b.intern();
        System.out.println(a == c);

    }
}
