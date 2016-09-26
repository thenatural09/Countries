package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter a letter");
        Scanner scanner = new Scanner(System.in);
        String firstLetter = String.valueOf(scanner.nextLine());
        HashMap<String, ArrayList<Country>> countryMap = addCountries(firstLetter);
        saveToTxt(countryMap.get(firstLetter),firstLetter + "_countries.txt");
        saveToJson(countryMap.get(firstLetter), firstLetter + "_countries.json");
    }

    public static HashMap<String, ArrayList<Country>> addCountries(String firstLetter) throws Exception {
        ArrayList<Country> countries = new ArrayList<>();
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            String abbrev = columns[0];
            String name = columns[1];
            Country c = new Country(name, abbrev);
            countries.add(c);
        }

        HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();
        for (Country country : countries) {
            firstLetter = String.valueOf(country.name.charAt(0));
            if (!countryMap.containsKey(firstLetter)) {
                countryMap.put(firstLetter, new ArrayList<>());
            } else {
                countryMap.get(firstLetter).add(country);
            }
        }
        if (firstLetter.equals("")){
            throw new Exception("Invalid letter");
        }

        if (firstLetter.length() > 1) {
            throw new Exception("Invalid letter");
        }
        return countryMap;
    }

    public static void saveToTxt (ArrayList<Country> countries, String fileName) {
        File f1 = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f1);
            fw.write(countries.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToJson (ArrayList<Country> countries,String fileName) {
        File f2 = new File(fileName);
        try {
            JsonSerializer serializer = new JsonSerializer();
            CountryWrapper cw = new CountryWrapper(countries);
            cw.countries = countries;
            String json = serializer.deep(true).serialize(cw);
            FileWriter fw = new FileWriter(f2);
            fw.write(json);
            fw.close();
        } catch (Exception e) {
            System.out.println("Couldn't save to file");
        }
    }


    public void loadFile(String fileName) {
        File f = new File(fileName);
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char[fileSize];
            fr.read(contents, 0, fileSize);
            JsonParser parser = new JsonParser();
            CountryWrapper cw = parser.parse(contents,CountryWrapper.class);
            System.out.println(cw);
        } catch (Exception e) {
            System.out.println("Couldn't load file");
        }
    }
}
