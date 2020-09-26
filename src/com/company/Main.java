package com.company;

import com.company.helpers.Parsing;
import com.company.helpers.Processing;

import java.io.*;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));
        Processing processing = new Processing();
        Parsing parsing = new Parsing();
        try {
            for (Object i : parsing.parsBufferedReader(input)) {
                processing.form((String) i);
            }
//            Parsing.getMetaData().forEach((s, integer) -> System.out.println(s+" : "+integer));
            for (Map.Entry<String, Integer> i : processing.sort(false)) {
                output.write(String.format("%s : %s\n", i.getKey(), i.getValue()));
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
