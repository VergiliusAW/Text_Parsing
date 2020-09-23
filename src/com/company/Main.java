package com.company;

import com.company.helpers.Parsing;
import com.company.helpers.Processing;

import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));
        Processing processing = new Processing();
        Parsing parsing = new Parsing();
        try {
            for (Object i:parsing.pars(input)) {
                processing.form((String) i);
            }
            for (Object i:processing.sort()) {
                output.write(i + "\n");
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
