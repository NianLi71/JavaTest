package com.linian.enumtest;


public class Burrito {
    Spiciness degree;
    public Burrito(Spiciness degree) {this.degree = degree;}
    public String toString() {return "Burrito is " + degree;}

    public static void main(String[] argv) {
        System.out.println(new Burrito(Spiciness.HOT));
    }
}


enum Spiciness {
    NOT, MILD, MEDIUM, HOT, FLAMING
}

