package com.linian.enumtest;


enum Dog { QIUQIU, GUOGUO, XIAOPIQIU, XIAOHUA}

public class EnumClass {
    public static void main(String[] args) {
        /**
         * enum Dog length: 4
         * QIUQIU 0
         * class com.linian.enumtest.Dog
         * QIUQIU
         * -------------
         * GUOGUO 1
         * class com.linian.enumtest.Dog
         * GUOGUO
         * -------------
         * XIAOPIQIU 2
         * class com.linian.enumtest.Dog
         * XIAOPIQIU
         * -------------
         * XIAOHUA 3
         * class com.linian.enumtest.Dog
         * XIAOHUA
         * -------------
         * QIUQIU
         * GUOGUO
         * XIAOPIQIU
         * XIAOHUA
         * Exception in thread "main" java.lang.IllegalArgumentException: No enum constant com.linian.enumtest.Dog.DAWANG
         * 	at java.lang.Enum.valueOf(Enum.java:238)
         * 	at com.linian.enumtest.EnumClass.main(EnumClass.java:19)
         *
         */

        System.out.println("enum Dog length: " + Dog.values().length);

        for(Dog dog : Dog.values()) {
            System.out.println(dog + " " + dog.ordinal());
            System.out.println(dog.getDeclaringClass());
            System.out.println(dog.name());
            System.out.println("-------------");
        }

        for(String s : "QIUQIU GUOGUO XIAOPIQIU XIAOHUA DAWANG".split(" ")) {
            Dog dog = Enum.valueOf(Dog.class, s);
            System.out.println(dog);
        }
    }
}
