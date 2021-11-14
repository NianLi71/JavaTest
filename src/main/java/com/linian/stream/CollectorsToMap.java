package com.linian.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsToMap {
    /**
     * Ref:
     * 1. https://www.baeldung.com/java-collectors-tomap
     * 2. https://www.logicbig.com/how-to/code-snippets/jcode-java-8-streams-collectors-tomap.html
     * 3. https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toMap-java.util.function.Function-java.util.function.Function-java.util.function.BinaryOperator-java.util.function.Supplier-
     */

    public static void main(String[] args) {
        toMapExample1();

        toMapExample2();
    }

    static void toMapExample1() {
        System.out.println("toMap example 1");

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));

        System.out.println(listToMap(bookList));

        try {
            listToMapWithDupKeyError(bookList);
        } catch (final IllegalStateException e) {
            System.out.println(e);
        }

        System.out.println(listToMapWithDupKey(bookList));
    }

    static void toMapExample2() {
        System.out.println("toMap example 2");

        example2_1();

        example2_2();

        example2_3();
    }

    static Map<String, String> listToMap(List<Book> books) {
        /**
         * Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
         *                                  Function<? super T, ? extends U> valueMapper)
         *   https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toMap-java.util.function.Function-java.util.function.Function-java.util.function.BinaryOperator-
         */
        return books.stream().collect(Collectors.toMap(Book::getIsbn, Book::getName));
    }

    static Map<Integer, Book> listToMapWithDupKeyError(List<Book> books) {
        return books.stream().collect(
                Collectors.toMap(Book::getReleaseYear, Function.identity()));
    }

    static Map<Integer, Book> listToMapWithDupKey(List<Book> books) {
        /**
         * Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper,
         *                          Function<? super T, ? extends U> valueMapper,
         *                          BinaryOperator<U> mergeFunction)
         */
        return books.stream().collect(
                Collectors.toMap(Book::getReleaseYear, Function.identity(), (existing, replacement) -> existing)
        );
    }

    static void example2_1() {
        Stream<String> s = Stream.of("apple", "banana", "orange");

        Map<Character, String> m = s.collect(Collectors.toMap(s1 -> s1.charAt(0), s1 -> s1));
        // same as above
        // s.collect(Collectors.toMap(s1 -> s1.charAt(0), Function.identity()));

        System.out.println(m); // {a=apple, b=banana, o=orange}
    }

    static void example2_2() {
        /**
         * <T,K,U> Collector<T,?,Map<K,U>> toMap(
         *                                   Function<? super T,? extends K> keyMapper,
         *                                   Function<? super T,? extends U> valueMapper,
         *                                   BinaryOperator<U> mergeFunction)
         */
        Stream<String> s = Stream.of("apple", "banana", "apricot", "orange", "apple");
        Map<Character, String> m = s.collect(Collectors.toMap(str -> str.charAt(0),
                Function.identity(),
                (existing, replacement) -> existing + "|" + replacement));

        System.out.println(m + " " + m.getClass());
    }

    static void example2_3() {
        /**
         * <T,K,U,M extends Map<K,U>> Collector<T,?,M> toMap(
         *                                   Function<? super T,? extends K> keyMapper,
         *                                   Function<? super T,? extends U> valueMapper,
         *                                   BinaryOperator<U> mergeFunction,
         *                                   Supplier<M> mapSupplier)
         */

        Stream<String> s = Stream.of("apple", "banana", "apricot", "orange",
                "apple");

        LinkedHashMap<Character, String> m = s.collect(
                Collectors.toMap(s1 -> s1.charAt(0),
                        s1 -> s1,
                        (s1, s2) -> s1 + "|" + s2,
                        LinkedHashMap::new));

        System.out.println(m + " " + m.getClass());
    }
}

@Data
@AllArgsConstructor
class Book {
    private String name;
    private int releaseYear;
    private String isbn;
}