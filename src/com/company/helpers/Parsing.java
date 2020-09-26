package com.company.helpers;

import java.io.BufferedReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Класс для парсинга текста
 */
public class Parsing {

    private Map<String, Integer> metaData = new HashMap<>();

    /**
     * Парсит на слова BufferedReader
     *
     * @param input BufferedReader
     * @return список слов BufferedReader'а
     */
    public List<String> parsBufferedReader(BufferedReader input) {
        List<String> lineList = input.lines().collect(Collectors.toList());
        List<String> parsedBufferedReader = new ArrayList<>();

        for (String line : lineList) {
            parsedBufferedReader.addAll(parsLine(line));
        }
        parsedBufferedReader.removeAll(Collections.singleton(""));
        return parsedBufferedReader;
    }

    /**
     * Парсит линию по regexList
     *
     * @param line строка
     * @return список слов строки
     */
    private List<String> parsLine(String line) {
        List<String> regexList = Arrays.asList("\\(", "\\)", "\\.", "!", "\\?", ",", ":", " ", ";", "'", "-", "`", "\\\"");
        String delimiter = "&nwrd";
        for (String regex : regexList) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);
            count(matcher);
            line = String.join(delimiter, pattern.split(line));
        }
        return Arrays.asList(Pattern.compile(delimiter).split(line));
    }

    /**
     * Считает разделители и их количество
     *
     * @param matcher matcher
     */
    private void count(Matcher matcher) {
        String regex = String.join("", Pattern.compile("\\\\").split(matcher.pattern().pattern()));
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        try {
            if (!metaData.containsKey(regex)) {
                metaData.put(regex, count);
                System.out.println(String.format("Добавлен разделитель: %s", regex));
            } else
                metaData.put(regex, metaData.get(regex) + count);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    /**
     * Получение информации по разделителям
     *
     * @return Map<String, Integer> , где String - разделитель, а Integer - количество
     */
    public Map<String, Integer> getMetaData() {
        return metaData;
    }
}
