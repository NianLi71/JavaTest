package com.linian.stream;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type {MEAT, FISH, OTHER};
}