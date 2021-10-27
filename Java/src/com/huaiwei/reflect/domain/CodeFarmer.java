package com.huaiwei.reflect.domain;

public class CodeFarmer extends Human {


    private String occupation;

    @Override
    public void eatFood(String foodName) {
        System.out.println("吃了" + foodName);
    }

    @Override
    public void sleep(Integer hours) {
        System.out.println("睡了" + hours + "小时");
    }

    public void sleep(Integer hours, String dream) {
        System.out.println("睡了" + hours + "小时" + "并做了" + dream);
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public CodeFarmer() {
    }

    public CodeFarmer(String occupation) {
        this.occupation = occupation;
    }

    private void test1() {
    }
}
