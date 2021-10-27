package com.huaiwei.reflect.domain;

public abstract class Human {
    private Integer age;
    private String name;
    private Boolean gender;

    public abstract void eatFood(String foodName);

    public abstract void sleep(Integer hours);

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

}
