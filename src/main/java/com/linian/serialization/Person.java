package com.linian.serialization;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    /**
     * Reference:
     *      https://www.baeldung.com/java-serialization
     *      https://www.baeldung.com/java-transient-keyword
     * Please see PersonSerializationTest
     */

    // Note that static fields belong to a class (as opposed to an object) and are not serialized
    //
    private static final long serialVersionUID = 1L;

//    static String country = "ITALY";
    private Address country;

    private int age;

    private String name;

    // we can use the keyword transient to ignore class fields during serialization:
    transient double height;
}

@Builder
@Data
class Address implements Serializable {
    /**
     * Address must be Serializable, or will throw java.io.NotSerializableException: com.linian.serialization.Address
     */
    private String country;
}