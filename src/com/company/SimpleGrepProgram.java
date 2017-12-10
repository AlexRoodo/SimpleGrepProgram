package com.company;

import java.io.*;
import java.util.Scanner;

public class SimpleGrepProgram {
    private String key;
    private Scanner scanner;
    private String SOURCE_FILE_NAME = "/home/roodo/IdeaProjects/SimpleGrepProgram/SourceFile.txt";

    private void ReadFromConsole() {
        System.out.println("Please enter keyword!");
        this.scanner = new Scanner(System.in);
        this.key = scanner.nextLine();
    }

    private void setFilePath() {
        System.out.println("Do you want set path to source file? (Y or N)");
        if(this.scanner.nextLine().toUpperCase().equals("Y")) {
            SOURCE_FILE_NAME = this.scanner.nextLine();
        }
    }

    private void grep () {
        String line;

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(SOURCE_FILE_NAME)))){

            while((line = reader.readLine()) != null) {
                if(line.contains(key)) {
                    System.out.println(line);
                }
            }

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleGrepProgram simpleGrepProgram = new SimpleGrepProgram();

        simpleGrepProgram.ReadFromConsole();
        simpleGrepProgram.setFilePath();
        simpleGrepProgram.grep();
    }
}