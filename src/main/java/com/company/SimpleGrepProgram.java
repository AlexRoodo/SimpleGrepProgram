package com.company;

import java.io.*;
import java.util.regex.*;

public class SimpleGrepProgram {
    private String key;
    private String fileName;
    private boolean hasCaseIgnoringParameter = false;
    private static String programUsageMessage = "Correct usage: [-i] pattern file";


    public static void argsCheck(String args[]) {
        try {
            isArgsAmountCorrect(args);
        } catch(IllegalArgumentException e) {
            hasException();
        }
    }


    SimpleGrepProgram(String[] args) {
        this.key = args[args.length - 2];
        this.fileName = args[args.length-1];
        try {
            caseIgnore(args[0], args.length);
        } catch (IllegalArgumentException e) {
            hasException();
        }
    }

    private static void isArgsAmountCorrect(String[] args) {
        if(args.length < 1 || args.length > 3) {
            throw new IllegalArgumentException(programUsageMessage);
        }
    }

    private static void hasException() {
        System.out.println(programUsageMessage);
        System.exit(1);
    }

    private  void caseIgnore(String arg, int argsLength) {
        if(arg.equalsIgnoreCase("-i")) {
            this.key = this.key.toLowerCase();
            hasCaseIgnoringParameter = true;
        } else if (argsLength == 3) {
            throw new IllegalArgumentException(programUsageMessage);
        }
    }


    public void grep() {
        String line;
        String comparedLine;
        Pattern pattern = Pattern.compile(key);

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName)))){

            while((line = reader.readLine()) != null) {
                comparedLine = fileLinePreparedForCompare(line);
                if(comparedLineHasMatch(comparedLine, pattern)) {
                    System.out.println(line);
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private String fileLinePreparedForCompare(String line) {
        if (hasCaseIgnoringParameter) {
            return line.toLowerCase();
        } else {
            return line;
        }
    }

    private boolean comparedLineHasMatch(String comparedLine, Pattern pattern) {
        String[] words = comparedLine.split(" ");
        return lookForMatches(words, pattern);
    }

    private boolean lookForMatches(String[] words, Pattern pattern) {
        Matcher matcher;
        boolean matchFound = false;
        for(String w: words) {
            matcher = pattern.matcher(w);
            if(matcher.matches()) {
                matchFound = true;
                break;
            }
        }
        return matchFound;
    }
}