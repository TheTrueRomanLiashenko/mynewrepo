package com.roman.tests;


import com.roman.Helper;
import com.roman.Shortener;
import com.roman.strategy.HashBiMapStorageStrategy;
import com.roman.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) throws IOException, ClassNotFoundException {
        Date start = new Date();
        for (String s : strings)
            ids.add(shortener.getId(s));
        Date finish = new Date();

        return finish.getTime() - start.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) throws IOException, ClassNotFoundException {
        Date start = new Date();
        for (Long id : ids)
            strings.add(shortener.getString(id));
        Date finish = new Date();

        return finish.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() throws IOException, ClassNotFoundException {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> idsHashMap = new HashSet<>();
        Set<Long> idsHashBiMap = new HashSet<>();
        long timeIdsHashMap = getTimeToGetIds(shortener1, origStrings, idsHashMap);
        long timeIdsHashBiMap = getTimeToGetIds(shortener2, origStrings, idsHashBiMap);
        Assert.assertTrue(timeIdsHashMap > timeIdsHashBiMap);

        long timeStringsHashMap = getTimeToGetStrings(shortener1, idsHashMap, origStrings);
        long timeStringsHashBiMap = getTimeToGetStrings(shortener2, idsHashBiMap, origStrings);
        Assert.assertEquals(timeStringsHashMap, timeStringsHashBiMap, 30);



    }
}
