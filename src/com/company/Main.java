package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Country country = new Country();
        country.saveTxtCountry();
        country.addCountry();
        country.chooseCountry();
        country.saveFile(country,country.letter + "_country.json");
    }
}
