package com.company.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для парсинга текста
 */
public class Parsing {

    private Map<String, Integer> metaData = new HashMap<>();

    public List<String> parsList(List<String> list) {
        List<String> parsedList = new ArrayList<>();
        for (String line : list) {
            parsedList.addAll(parsLine(line));
        }
        parsedList.removeAll(Collections.singleton(""));
        return parsedList;
    }

    /**
     * Парсит на слова BufferedReader
     *
     * @param input BufferedReader
     * @return список слов BufferedReader'а
     */
//    public List<String> parsBufferedReader(BufferedReader input) {
//        List<String> lineList = input.lines().collect(Collectors.toList());
//        List<String> parsedBufferedReader = new ArrayList<>();
//        for (String line : lineList) {
//            parsedBufferedReader.addAll(parsLine(line));
//        }
//        parsedBufferedReader.removeAll(Collections.singleton(""));
//        return parsedBufferedReader;
//    }
    public List<String> parsBufferedReader(BufferedReader input) throws IOException {
        List<String> parsedBufferedReader = new ArrayList<>();
        while (input.ready()) {
            parsedBufferedReader.addAll(parsLine(input.readLine()));
        }
        parsedBufferedReader.removeAll(Collections.singleton(""));
        return parsedBufferedReader;
    }

//    public void parsBufferedReader(BufferedReader input, BufferedWriter output, Processing processing) throws IOException {
//        List<String> lineList = new ArrayList<>();
//        List<File> files = new ArrayList<>();
//        while (input.ready()) {
//            Instant starts = Instant.now();
//            for (int i = 0; i < 100000; i++) {
//                if (input.ready())
//                    lineList.add(input.readLine());
//                else break;
//            }
//            System.out.println("0 - "+Duration.between(starts, Instant.now()));
//            files.add(new File(String.format("data%s.txt", files.size())));
//            threads.add(new WordsThread());
//            threads.get(threads.size()-1).setLineList(lineList,files.get(files.size()-1),this);
//            threads.get(threads.size()-1).start();
//            System.out.println("1 - "+Duration.between(starts, Instant.now()));
//            lineList.clear();
//        }
//
//        for(WordsThread thread:threads) {
//            while (thread.isAlive()) {
//            }
//        }
//
//        for(File file:files) {
//            BufferedReader inp = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//            while (inp.ready()) {
//                processing.form(inp.readLine());
//            }
//        }
//
//        for (Map.Entry<String, Integer> i : processing.sort(false)) {
//            output.write(String.format("%s : %s\n", i.getKey(), i.getValue()));
//            output.flush();
//        }
//    }

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
//            Matcher matcher = pattern.matcher(line);
//            count(matcher);
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

    public Processing parsBufferedReaderAndDoProcessing(BufferedReader input, Processing processing) throws IOException {
        while (input.ready()) {
            for (String word : parsLine(input.readLine())) {
                if (!word.equals("")) {
                    processing.form(word);
                }
            }
        }
        return processing;
    }
}
