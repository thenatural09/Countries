package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();
    public static ArrayList<Country> countries = new ArrayList<>();


    public static void main(String[] args) throws Exception {

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

        Country country = new Country();
        country.chooseCountry();
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
