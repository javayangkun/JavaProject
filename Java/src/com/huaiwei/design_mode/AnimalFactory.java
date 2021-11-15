package com.huaiwei.design_mode;

public class AnimalFactory {
    public Animal getAnimal(String name) {
        if (name.equalsIgnoreCase("pig")) {
            return new Pig();
        } else if (name.equalsIgnoreCase("dog")) {
            return new Dog();
        }
        return null;
    }
}
