package com.huaiwei.generics;

public class PersonTest {
    public static void main(String[] args) {
        Person<String, Integer, Double, Long> p = new Person<>("你好", 123, 23.00D, 48L);
        System.out.println(p);
    }
    Hello<Long,String> h = new Hello<>();

}
