package com.linian;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HashMapTest {
    private final Map<String, String> favoriteFood = new HashMap<>();

    @Before
    public void setup() {
        favoriteFood.put("qiuqiu", "apple");
        favoriteFood.put("guoguo", "orange");
    }

    @Test
    public void simpleTest() {
        assertEquals(favoriteFood.size(), 2);

        assertEquals(favoriteFood.get("qiuqiu"), "apple");

        assertTrue(Objects.isNull(favoriteFood.get("piuqiu")));

        assertEquals(favoriteFood.getOrDefault("xiaohua", "banana"),"banana");

//        favoriteFood.forEach((k, v) ->
//                System.out.println(String.format("key=%s, value=%s", k, v)));

        Map<String, String> food = new HashMap<>();
        food.put("xiaohua", "cake");
        food.put("piqiu", "water");
        favoriteFood.putAll(food);

        favoriteFood.forEach((k, v) ->
                System.out.println(String.format("key=%s, value=%s", k, v)));

        assertTrue(favoriteFood.containsKey("qiuqiu"));
        assertTrue(favoriteFood.containsKey("xiaohua"));
        assertFalse(favoriteFood.containsKey("trouble"));
        assertTrue(favoriteFood.containsValue("apple"));
    }

    @Test
    public void viewTest() {
        System.out.println("keySet: " + favoriteFood.keySet());
        System.out.println("valueSet: " + favoriteFood.values());
        System.out.println("entrySet: " + favoriteFood.entrySet());

        System.out.println("\nFor each Entry: ");
        for (Map.Entry<String, String> entry: favoriteFood.entrySet()) {
            if (entry.getValue().equals("orange")) {
                entry.setValue("beef");
            }
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("\nforEach:");
        favoriteFood.forEach((k, v) ->
                System.out.println(k + ":" + v));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void UnmodifiableMapViewTest() {
        Map<String, String> readOnlyFavoriteFood = Collections.unmodifiableMap(favoriteFood);

        assertEquals(readOnlyFavoriteFood.size(), 2);
        assertEquals(readOnlyFavoriteFood.get("qiuqiu"), "apple");

//        will throw UnsupportedOperationException
        readOnlyFavoriteFood.put("trouble", "cake");
    }

    @Test
    public void UnmodifiableMapViewTest2() {
        Map<String, String> readOnlyFavoriteFood = Collections.unmodifiableMap(favoriteFood);

        assertEquals(readOnlyFavoriteFood.size(), 2);

        favoriteFood.put("xiaohua", "cake");

//        Even the original map is changed, unmodifiableMap still is a view to that updated map
        assertEquals(readOnlyFavoriteFood.size(), 3);
        assertEquals(readOnlyFavoriteFood.get("xiaohua"), "cake");
        System.out.println(readOnlyFavoriteFood);
    }

}
