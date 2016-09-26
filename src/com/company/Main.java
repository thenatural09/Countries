package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        Country country = new Country();
        country.saveTxtCountry();
        country.addCountry();
        country.chooseCountry();
        country.saveFile(country.letter + "_countries.json");
        country.loadFile(country.letter + "_countries.json");
    }
}
