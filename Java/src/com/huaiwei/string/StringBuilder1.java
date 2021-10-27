package com.huaiwei.string;

public class StringBuilder1 {
    private final static StringBuilder sb = new StringBuilder();
    private static final String str = "";

    public static void main(String[] args) {
        sb.append(1);
        sb.append("312312321");
        System.out.println(sb.toString());
        // str += "hello"; 不能改变。因为String 底层是final 修饰的char数组
        // 重新赋值操作不会改变原有对象，只是新创建一个字符串然后将该变量的引用改变。
        //StringBuilder 使用final修饰只要不改变该对象的引用，无论怎样操作都不变。
    }
}
