package com.linian.groupingBy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupObject {
    // Ref: https://mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/

    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        // Group by name + Count
        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting()));

        System.out.println("Group by name + Count");
        System.out.println(counting);
        // {papaya=1, banana=2, apple=3, orang=1, watermelon=1}

        // Group by name + Sum qty
        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

        System.out.println("Group by name + Sum qty");
        System.out.println(sum);
        // {papaya=20, banana=30, apple=40, orang=10, watermelon=10}

        // Group by price
        Map<BigDecimal, List<Item>> groupByPriceMap =
                items.stream().collect(Collectors.groupingBy(Item::getPrice));

        System.out.println("Group by price");
        System.out.println(groupByPriceMap);

        // Group by price, uses 'mapping' to convert List<Item> to Set<String>
        Map<BigDecimal, Set<String>> result =
                items.stream().collect(
                        Collectors.groupingBy(Item::getPrice,
                                Collectors.mapping(Item::getName, Collectors.toSet())
                        )
                );

        System.out.println("Group by price, uses 'mapping' to convert List<Item> to Set<String>");
        System.out.println(result);
    }
}

@Data
@AllArgsConstructor
class Item {
    private String name;
    private int qty;
    private BigDecimal price;
}
