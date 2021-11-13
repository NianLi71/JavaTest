package com.linian;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionTest {
    @Test
    public void simpleCollectionTest() {
        List<String> dogs = new ArrayList<>();
        dogs.add("qiuqiu");
        dogs.add("guoguo");

        Collection<String> c = dogs;
        Iterator<String> iter = c.iterator();
        while (iter.hasNext()) {
            String dog = iter.next();
            System.out.println(dog);
        }
    }
}
