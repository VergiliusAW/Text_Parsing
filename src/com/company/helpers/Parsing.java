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
    public List pars(BufferedReader input) {
        List<String> list = input.lines().collect(Collectors.toList());
        List<String> parsedList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\s*(\\s|,|!|`|:|;|-|\"|'|\\r?\\n|\\(|\\)|\\?|\\.)\\s*");
        for (int i=0;i<list.size();i++) {
            parsedList.addAll(Arrays.asList(pattern.split(list.get(i))));
        }
        parsedList.removeAll(Collections.singleton(""));
        return parsedList;
    }
}
