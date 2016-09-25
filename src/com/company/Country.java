package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Troy on 9/23/16.
 */
public class Country {
    public String name;
    public String abbrev;
    public String letter;

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

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", abbrev='" + abbrev + '\'' +
                '}';
    }

    public void chooseCountry() throws Exception {
        System.out.println("Enter a letter");
        Scanner scanner = new Scanner(System.in);
        letter = scanner.nextLine();

        for (Country country : Main.countries) {
            String firstLetter = String.valueOf(country.name.charAt(0));
            if (!Main.countryMap.containsKey(firstLetter)) {
                Main.countryMap.put(firstLetter, new ArrayList<>());
            }
            Main.countryMap.get(firstLetter).add(country);
        }

        if (letter.equals("")){
            throw new Exception("Invalid letter");
        }

        //saving txt file
        File countryFile = new File(letter + "_countries.txt");
        try {
            FileWriter fw = new FileWriter(countryFile);
            ArrayList countries1 = Main.countryMap.get(letter.toUpperCase());
            fw.write(countries1.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

