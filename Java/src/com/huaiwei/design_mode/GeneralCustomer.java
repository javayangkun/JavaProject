package com.huaiwei.design_mode;

public class GeneralCustomer implements Customer{
    @Override
    public void login() {
        System.out.println("general customer login ...");
    }
}
