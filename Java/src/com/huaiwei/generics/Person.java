package com.huaiwei.generics;

public class Person<TFirst, TSecond, TTRee, TFBoy> {
    private TFirst first;
    private TSecond second;
    private TTRee ttRee;
    private TFBoy boy;


    public Person(TFirst first, TSecond second, TTRee ttRee, TFBoy boy) {
        this.first = first;
        this.second = second;
        this.ttRee = ttRee;
        this.boy = boy;
    }


    //C#的写法，可读太低
    // public Person(TFirst first, TSecond second) => (first,second)=(first,second);


    public TFirst getFirst() {
        return first;
    }

    public void setFirst(TFirst first) {
        this.first = first;
    }

    public TSecond getSecond() {
        return second;
    }

    public void setSecond(TSecond second) {
        this.second = second;
    }

    public TTRee getTtRee() {
        return ttRee;
    }

    public void setTtRee(TTRee ttRee) {
        this.ttRee = ttRee;
    }

    public TFBoy getBoy() {
        return boy;
    }

    public void setBoy(TFBoy boy) {
        this.boy = boy;
    }

    @Override
    public String toString() {
        return "Person{" +
                "first=" + first +
                ", second=" + second +
                ", ttRee=" + ttRee +
                ", boy=" + boy +
                '}';
    }
}
