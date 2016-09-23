package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
        for(Country country : countries) {
            ArrayList<Country> countries1 = countryMap.get(country.name.substring(0,1));
            if (countries1 == null) {
                countries1 = new ArrayList<>();
                countryMap.put(country.name.substring(0,1),countries1);
            }
            countries1.add(country);
        }
    }

    //Loading File
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

    //Saving File
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
