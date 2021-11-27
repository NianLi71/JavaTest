package com.linian.serialization;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersonSerializationTest {
    @Test
    public void serializeAndDeserializeTest() throws IOException, ClassNotFoundException {
        final String outFilePath = "data/serialization/personSerialization.txt";

        Person person = new Person();
        person.setAge(20);
        person.setName("Joe");
        person.setHeight(1.78);
        person.setCountry(Address.builder().country("ITALY").build());

        FileOutputStream fileOutputStream = new FileOutputStream(outFilePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(outFilePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Person p2 = (Person) objectInputStream.readObject();
        objectInputStream.close();

        Assert.assertEquals(p2.getAge(), person.getAge());
        Assert.assertEquals(p2.getName(), person.getName());

        System.out.println(p2);
//        Assert.assertEquals(p2.getHeight(), null);
    }
}
