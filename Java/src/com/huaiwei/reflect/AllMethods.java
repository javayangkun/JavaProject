package com.huaiwei.reflect;

import com.huaiwei.reflect.domain.CodeFarmer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AllMethods {
    public static void main(String[] args) throws Exception {
        CodeFarmer code = new CodeFarmer();
        //1.反射对象 getClass()
        Class<? extends CodeFarmer> aClass = code.getClass();
        //getMethods();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            System.out.println("method:" + name);
        }
        //getFields();
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            String name = field.getName();
            System.out.println("field:" + name);
        }

        //declaredMethod(自己所有的方法，不包含父类) //包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            String name = method.getName();
            System.out.println("declaredMethod:" + name);
        }
        CodeFarmer codeFarmer = aClass.newInstance();
        System.out.println(aClass);
        System.out.println("codeFarmer" + codeFarmer);
        //2.反射对象 CodeFarmer.class
        Class<CodeFarmer> codeFarmerClass = CodeFarmer.class;
        CodeFarmer codeFarmer1 = codeFarmerClass.newInstance();
        System.out.println(codeFarmerClass);
        System.out.println("codeFarmer1:" + codeFarmer1);
        //3.类路径反射对象
        Class<?> aClass1 = Class.forName("com.huaiwei.reflect.domain.CodeFarmer");
        System.out.println(aClass1);
        Object o = aClass1.newInstance();
        System.out.println(o);
    }
}
