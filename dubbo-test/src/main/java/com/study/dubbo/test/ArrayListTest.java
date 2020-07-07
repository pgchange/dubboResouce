package com.study.dubbo.test;

import java.util.ArrayList;

/**
 * <p>
 * Created by zhangchangji on 2020/3/27.
 */
public class ArrayListTest {


    public static void main(String[] args) {


        ArrayList<String> strList = new ArrayList<>();

        strList.add("a");

        ArrayList<String> newList = new ArrayList<>(strList);

        System.out.println(newList);

    }
}
