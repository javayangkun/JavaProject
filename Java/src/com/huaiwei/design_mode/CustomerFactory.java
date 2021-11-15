package com.huaiwei.design_mode;

public class CustomerFactory {
    Customer getCustomer(String customerType) {
        if (customerType.equalsIgnoreCase("vip")) {
            return new VipCustomer();
        } else if (customerType.equalsIgnoreCase("general")) {
            return new GeneralCustomer();
        }
        return null;
    }
}
