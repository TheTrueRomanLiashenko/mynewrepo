package com.roman.strategy;

import java.io.IOException;
import java.util.HashMap;

public class OurHashBiMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> k2v = new HashMap<>();
    private HashMap<String, Long> v2k = new HashMap<>();


    @Override
    public boolean containsKey(Long key) throws IOException, ClassNotFoundException {
        return k2v.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) throws IOException, ClassNotFoundException {
        return v2k.containsKey(value);
    }

    @Override
    public void put(Long key, String value) throws IOException, ClassNotFoundException {
        k2v.put(key, value);
        v2k.put(value, key);
    }

    @Override
    public Long getKey(String value) throws IOException, ClassNotFoundException {
        return v2k.get(value);
    }

    @Override
    public String getValue(Long key) throws IOException, ClassNotFoundException {
        return k2v.get(key);
    }
}
