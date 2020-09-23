package com.company.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для выполнения обработки потока и сохранения результата
 */
public class Processing {
    private HashMap<String,Integer> words = new HashMap<>();

    public List sort() {
        return new ArrayList(words.keySet());
    }

    public void form(String key) {
        try {
            if (!words.containsKey(key))
                words.put(key, 1);
            else {
                words.put(key, words.get(key) + 1);
            }
        } catch (NullPointerException e) {
            words.put(key,1);
        }
    }
}
