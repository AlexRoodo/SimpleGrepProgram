package com.company;

import java.io.*;
import java.util.regex.Pattern;

public class SimpleGrepProgram {
    private String key;
    private String fileName;
    private boolean fileLinesToLowerCase = false;

    private SimpleGrepProgram(String[] args) {
        isArgsAmountCorrect(args);
        this.key = args[args.length - 2];
        this.fileName = args[args.length-1];
        caseIgnore(args[0], args.length);
    }

    private void isArgsAmountCorrect(String[] args) {
        if(args.length < 1 || args.length > 3) {
            throw new IllegalArgumentException();
        }
    }

    private  void caseIgnore(String arg, int argsLength) {
        if((arg.equalsIgnoreCase("-i")) && (argsLength == 3)) {
            this.key = this.key.toLowerCase();
            fileLinesToLowerCase = true;
        }
    }

    private void grep () {
        String line;

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName)))){

            while((line = reader.readLine()) != null) {
                if (fileLinesToLowerCase) {
                    line = line.toLowerCase();
                }
                if(line.contains(key)) {
                    System.out.println(line);
                }
            }

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleGrepProgram simpleGrepProgram = new SimpleGrepProgram(args);

        simpleGrepProgram.grep();
    }
}