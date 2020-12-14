package com.learn.cglib;

import com.learn.porxy.IProuder;

public class Produer implements IProuder {
    public void saleProduer(float money) {
        System.out.println("销售产品，并拿到钱：" + money);
    }

    public void afterService(float money) {
        System.out.println("提供售后，并拿到钱：" + money);
    }
}
