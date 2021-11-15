package com.huaiwei.practise.pass;

public class PassValue {
    public static void main(String[] args) {
        int number = 10;
        String str = "Hello";
        User user = new User();
        setValue(number, str, user);
        System.out.println(number);
        System.out.println(str);
        System.out.println(user);
    }

    public static void setValue(int number, String str, User user) {
        number = number + 1;
        str = str + "world";
        System.out.println(number);
        System.out.println(str);
        user.setId("123");
    }
}
