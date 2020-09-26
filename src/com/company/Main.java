package com.company;

import com.company.helpers.Parsing;
import com.company.helpers.Processing;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Instant starts = Instant.now();
        String inputFileName = "textL.txt";
        String outputFileName = "output.txt";
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));
        Processing processing = new Processing();
        Parsing parsing = new Parsing();
        try {
//            parsing.parsBufferedReader(input,output,processing);
//            for (Object i : parsing.parsBufferedReader(input)) {
//                processing.form((String) i);
//            }
            for (Map.Entry<String, Integer> i : parsing.parsBufferedReaderAndDoProcessing(input, processing).sort(false)) {
                output.write(String.format("%s : %s\n", i.getKey(), i.getValue()));
                output.flush();
            }
//            parsing.getMetaData().forEach((s, integer) -> System.out.println(s+" : "+integer));
//            for (Map.Entry<String, Integer> i : processing.sort(false)) {
//                output.write(String.format("%s : %s\n", i.getKey(), i.getValue()));
//                output.flush();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Duration.between(starts, Instant.now()));
    }
}
