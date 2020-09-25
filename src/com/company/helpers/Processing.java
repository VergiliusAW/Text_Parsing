package com.company.helpers;

import java.util.*;

/**
 * Класс для выполнения обработки потока и сохранения результата
 */
public class Processing {
    private HashMap<String, Integer> words = new HashMap<>();

    /**
     * Сортирует HashMap
     *
     * @param order true - ASC, false - DESC
     * @return HashMap
     */
    public List<Map.Entry<String, Integer>> sort(boolean order) {
        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                Integer v1 = e1.getValue();
                Integer v2 = e2.getValue();
                return v1.compareTo(v2);
            }
        };
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(words.entrySet());
        if (order)
            Collections.sort(list, valueComparator);
        else
            Collections.sort(list, valueComparator.reversed());
        return list;
    }

    /**
     * Добавляет слово, если оно ещё не встречалось.
     * Инкрементирует значение Integer, если слово уже есть.
     *
     * @param key слово
     */
    public void form(String key) {
        try {
            if (!words.containsKey(key))
                words.put(key, 1);
            else {
                words.put(key, words.get(key) + 1);
            }
        } catch (NullPointerException e) {
            words.put(key, 1);
        }
    }
}
