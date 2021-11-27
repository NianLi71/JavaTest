package com.linian.inheritance;


public class Phone {
    private String name;

    public Phone(String name) {
        this.name = name;
    }

    public void brand() {
        System.out.println("No brand");
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] argv) {
        Phone ip = new IPhone("iphone");
        Phone hw = new HuawiPhone("hw");

        ip.brand();
        hw.brand();

        System.out.println(ip.getClass().getSimpleName() + " " + hw.getClass().getSimpleName());
    }
}

class IPhone extends Phone {
    IPhone(String name) {
        super(name);
    }

    @Override
    public void brand() {
        System.out.println("IPhone");
    }
}

class HuawiPhone extends Phone {
    HuawiPhone(String name) {
        super(name);
    }

    @Override
    public void brand() {
        System.out.println("Huawei");
        super.brand();
    }
}