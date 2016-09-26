package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Troy on 9/23/16.
 */
public class Country {
    public String name;
    public String abbrev;
    public String letter;
    public String firstLetter;
    public HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();
    public ArrayList<Country> countries = new ArrayList<>();

    public Country () {
    }

    public Country(String name, String abbrev) {
        this.name = name;
        this.abbrev = abbrev;
    }

    public String getName() {
        return name;
    }

    public String getAbbrev() {
        return abbrev;
    }


    public void addCountry() {
        for (Country country : countries) {
            firstLetter = String.valueOf(country.name.charAt(0));
            if (!countryMap.containsKey(firstLetter)) {
                countryMap.put(firstLetter, new ArrayList<>());
            }
            countryMap.get(firstLetter).add(country);
        }
    }

    public void chooseCountry() throws Exception {
        System.out.println("Enter a letter");
        Scanner scanner = new Scanner(System.in);
        letter = scanner.nextLine();

        if (letter.equals("")){
            throw new Exception("Invalid letter");
        }

        if (letter.length() > 1) {
            throw new Exception("Invalid letter");
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

        saveFile(letter,countryMap.get(letter.toUpperCase()));
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

    public void saveFile(String firstLetter, ArrayList<Country> countries1) {
        JsonSerializer serializer = new JsonSerializer();
        CountryWrapper cw = new CountryWrapper();
        cw.countries = countries1;
        String json = serializer.deep(true).serialize(cw);
        File f = new File(firstLetter + "_countries.json");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(json);
            fw.close();
        } catch (Exception e) {
            System.out.println("Couldn't save to file");
        }
    }


    public void saveTxtCountry() throws FileNotFoundException {
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            String abbrev = columns[0];
            String name = columns[1];
            Country c = new Country(abbrev, name);
            countries.add(c);
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", abbrev='" + abbrev + '\'' +
                '}';
    }
}

