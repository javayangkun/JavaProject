package com.huaiwei.jdbctempalte.config;

/**
 * jdk 1.8方法引入虚拟扩展方法，可以有方法体，子类可以不用实现
 */
public interface TestInterface {
    default Object sayHello() {
        return true;
    }
}
