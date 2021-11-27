package com.linian.enumtest;

public enum LittleDog {
    QIUQIU("I'm 3 years old, I love eating beef."),
    GUOGUO("I'm 2.5 years old, QIUQIU is my sister."),
    XIAOPIQIU("Hi, I'm a boy, I live in ZhiChun Roud."),
    XIAOHUA("I'm a white little dog, I'm cute.");

    private String description;

    private LittleDog(String description) {
        this.description = description;
    }

    public String getDescription() {return description;}

    public static void main(String[] args) {
        for(LittleDog ldog: LittleDog.values()) {
            System.out.println(ldog + " " + ldog.getDescription());
        }
    }
}
