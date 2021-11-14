package com.linian.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;
import static java.lang.System.out;

public class DishStream {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

//        menu.stream().forEach(System.out::println);

        System.out.println(menu.stream().count());  // 9
        // Same as above line
        menu.stream().collect(Collectors.counting());
        /**
         * public static <T> Collector<T, ?, Long>
         *     counting() {
         *         return reducing(0L, e -> 1L, Long::sum);
         *     }
         */

        List<String> thresHighCaloricDishName =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .map(Dish::getName) //.map(d -> d.getName())
                        .limit(3)
                        .collect(toList());
        System.out.println(thresHighCaloricDishName); // [pork, beef, chicken]

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories); // 4200

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics); // IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}

        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        out.println(shortMenu); // pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon
    }
}
