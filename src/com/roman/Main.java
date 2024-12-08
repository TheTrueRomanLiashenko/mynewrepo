package com.roman;

import com.roman.strategy.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) throws IOException, ClassNotFoundException {
        Set<Long> set = new HashSet<>();
        for (String string : strings)
            set.add(shortener.getId(string));

        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) throws IOException, ClassNotFoundException {
        Set<String> set = new HashSet<>();
        for (long l : keys)
            set.add(shortener.getString(l));

        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) throws IOException, ClassNotFoundException {
        System.out.println("Strategy name: " + strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date start = new Date();
        Set<Long> ids = getIds(shortener, strings);
        Date finish = new Date();
        System.out.println("Spent time on getIds(): " + (finish.getTime() - start.getTime()) + " ms.");

        start = new Date();
        Set<String> rStrings = getStrings(shortener, ids);
        finish = new Date();
        System.out.println("Spent time on getStrings(): " + (finish.getTime() - start.getTime()) + " ms.");

        boolean areSame = true;
        ArrayList<String> generatedStrings = new ArrayList<>(strings);
        ArrayList<String> returnedString = new ArrayList<>(rStrings);
        try {
            for (int i = 0; i < generatedStrings.size(); i++) {
                if (generatedStrings.get(i) != returnedString.get(i))
                    areSame = false;
            }
        } catch (Exception e) {
            System.out.println("Тест не пройден.");
        }

        if (areSame)
            System.out.println("Тест пройден.");
        else
            System.out.println("Тест не пройден.");

    }
}
