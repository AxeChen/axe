package com.mg.axe.androiddevelop.develop.rxjavacat.bean;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Cat implements Comparable<Cat> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Cat another) {
        return 0;
    }
}
