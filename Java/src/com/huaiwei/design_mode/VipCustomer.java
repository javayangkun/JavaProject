package com.huaiwei.design_mode;

public class VipCustomer implements Customer{
    @Override
    public void login() {
        System.out.println("vip login ...");
    }
}
