package com.navi.stockexchangeapp.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Simple File Parser Class
 *
 * This class contains a method "parse(File)"
 *
 * It takes file as an input and reads the input
 * line by line and sends a response as linked
 * list of Strings
 */
public class FileParser {
    public List<String> parse(File file) throws FileNotFoundException {
        List<String> input = new LinkedList<>();

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        sc.close();

        return input;
    }
}
