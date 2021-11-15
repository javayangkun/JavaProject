package com.huaiwei.design_mode;


public class FactoryModeMain {
    public static void main(String[] args) {

        AnimalFactory factory = new AnimalFactory();
        Animal pig = factory.getAnimal("pig");
        pig.eat();

        CustomerFactory customerFactory = new CustomerFactory();
        Customer vip = customerFactory.getCustomer("vip");
        vip.login();

    }

}
