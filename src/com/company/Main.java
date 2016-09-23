package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();
        ArrayList<Country> countries = new ArrayList<>();

        //Asking user for country first letter




        //File Reader
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            String abbrev = columns[0];
            String name = columns[1];
            Country c = new Country(abbrev,name);
            countries.add(c);
        }

        //Getting countries by first letter
        for (int index = 0; index < countries.size(); index++) {

        }
    }

    //Parsing File
    public void parseFile(String fileName) {

    }
}
