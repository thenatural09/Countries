package com.company;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Troy on 9/26/16.
 */
public class MainTest {
    @Test
    public void testAddCountry () throws Exception {
        File f = new File("countries.txt");
        String firstLetter = "a";
        HashMap<String, ArrayList<Country>> countryMap = Main.addCountries(firstLetter);
        assertTrue(!countryMap.isEmpty());
    }

    @Test
    public void testSaveToJson () throws Exception {
        String firstLetter = "a";
        HashMap<String, ArrayList<Country>> countryMap = Main.addCountries(firstLetter);
        Main.saveToJson(countryMap.get(firstLetter),firstLetter + "_testcountries.json");
        assertTrue(!countryMap.get(firstLetter).isEmpty());
    }

}