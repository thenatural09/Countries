package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();
        ArrayList<Country> countries = new ArrayList<>();

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

        //Adding array lists to hashmap and making new txt files for each user
        System.out.println("Enter a letter");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine();

        for(Country country : countries) {
            String firstLetter = String.valueOf(country.name.charAt(0));
            if (!countryMap.containsKey(firstLetter)) {
                countryMap.put(firstLetter, new ArrayList<>());
            }
            countryMap.get(firstLetter).add(country);
        }

        //saving txt file
        File countryFile = new File(letter + "_countries.txt");
        try {
        FileWriter fw = new FileWriter(countryFile);
        ArrayList countries1 = countryMap.get(letter.toUpperCase());
        fw.write(countries1.toString());
        fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Loading  json File
    public void loadFile(String fileName) {
        File f = new File(fileName);
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char[fileSize];
            fr.read(contents, 0, fileSize);
            JsonParser parser = new JsonParser();
        } catch (Exception e) {
            System.out.println("Couldn't load file");
        }
    }

    //Saving json File
    public void saveFile(Country country,String fileName) {
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.deep(true).serialize(country);
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(json);
            fw.close();
        } catch (Exception e) {
            System.out.println("Couldn't save to file");
        }
    }
}
