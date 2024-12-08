package com.roman.tests;


import com.roman.Shortener;
import com.roman.strategy.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class FunctionalTest {

    public void testStorage(Shortener shortener) throws IOException, ClassNotFoundException {
        String s1 = "SomeTests";
        String s2 = "SomeTests2";
        String s3 = "SomeTests";
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(shortener.getId(s1));
        ids.add(shortener.getId(s2));
        ids.add(shortener.getId(s3));

        Assert.assertNotEquals(ids.get(1), ids.get(0));
        Assert.assertNotEquals(ids.get(1), ids.get(2));
        Assert.assertEquals(ids.get(0), ids.get(2));

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            strings.add(shortener.getString(ids.get(i)));
        }
        Assert.assertEquals(strings.get(0), s1);
        Assert.assertEquals(strings.get(1), s2);
        Assert.assertEquals(strings.get(2), s3);
    }

    @Test
    public void testHashMapStorageStrategy() throws IOException, ClassNotFoundException {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() throws IOException, ClassNotFoundException {
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() throws IOException, ClassNotFoundException {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() throws IOException, ClassNotFoundException {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() throws IOException, ClassNotFoundException {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() throws IOException, ClassNotFoundException {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }

}
