package com.company;

import javafx.scene.chart.PieChart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static java.nio.file.Files.readAllLines;

public class DataModel {
    String name;
    String surname;
    String secondName;
    int age;
    boolean sex; // 1 - male, 0 - female;
    String dateOfBirth; // ДД-ММ-ГГГГ
    String INN;
    String postIndex;
    String country;
    String region;
    String city;
    String street;
    int houseNum;
    int flatNum;

    public DataModel () throws IOException {
        // generation

        Random random = new Random();
        this.sex = random.nextBoolean();
        String path = new File("").getAbsolutePath() + "/" + "src/main/resources/";

        if (this.sex) {
            try {
                List<String> names = readAllLines(Paths.get(path+"MaleNames.txt"), StandardCharsets.UTF_8);
                List<String> surnames = readAllLines(Paths.get(path+"surnames.txt"), StandardCharsets.UTF_8);
                List<String> secondNames = readAllLines(Paths.get(path+"secondNameMale.txt"), StandardCharsets.UTF_8);
                this.name = names.get(random.nextInt(names.size()) % names.size());
                this.surname = surnames.get(random.nextInt(surnames.size()) % surnames.size());
                this.secondName = secondNames.get(random.nextInt(secondNames.size()) % secondNames.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                List<String> names = readAllLines(Paths.get(path+"femaleNames.txt"), StandardCharsets.UTF_8);
                List<String> surnames = readAllLines(Paths.get(path+"surnames.txt"), StandardCharsets.UTF_8);
                List<String> secondNames = readAllLines(Paths.get(path+"secondNameFemale.txt"), StandardCharsets.UTF_8);
                this.name = names.get(random.nextInt(names.size()) % names.size());
                this.surname = surnames.get(random.nextInt(surnames.size()) % surnames.size()) + "a";
                this.secondName = secondNames.get(random.nextInt(secondNames.size()) % secondNames.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.age = 10 + random.nextInt(50);
        this.houseNum = 10 + random.nextInt(100);
        this.flatNum = 1 + random.nextInt(100);
        List<String> cities = readAllLines(Paths.get(path+"city.txt"), StandardCharsets.UTF_8);
        this.city = cities.get(random.nextInt(cities.size()) % cities.size());
        List<String> streets = readAllLines(Paths.get(path+"street.txt"), StandardCharsets.UTF_8);
        this.street = streets.get(random.nextInt(streets.size()) % streets.size());
        List<String> regions = readAllLines(Paths.get(path+"region.txt"), StandardCharsets.UTF_8);
        this.region = regions.get(random.nextInt(regions.size()) % regions.size());
        List<String> countries = readAllLines(Paths.get(path+"country.txt"), StandardCharsets.UTF_8);
        this.country = countries.get(random.nextInt(countries.size()) % countries.size());

        this.postIndex = String.valueOf(random.nextInt(9));
        for (int i = 0; i < 5; i++) {
            this.postIndex += String.valueOf(random.nextInt(9));
        }

        int day = random.nextInt(28);
        int month = random.nextInt(12);
        int _year = 1900 + random.nextInt(99);
        String year = String.valueOf(_year);
        if (day < 10) {
            this.dateOfBirth = "0" + String.valueOf(day) + "-";
        } else {
            this.dateOfBirth = String.valueOf(day) + "-";
        }
        if (month < 10) {
            this.dateOfBirth += "0" + String.valueOf(month) + "-";
        } else {
            this.dateOfBirth += String.valueOf(month) + "-";
        }

        this.dateOfBirth += year;
        if (month < 10) {
            this.age = 2018 - _year;
        } else {
            this.age = 2018 - _year - 1;
        }

        this.INN = String.valueOf(77);
        int n1[] = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int n2[] = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int resultForN1 = 7*3 + 7*7;
        int resultForN2 = 7*7 + 7*2;
        for (int i = 0; i < 8; i++) {
            int value = random.nextInt(9);
            this.INN += String.valueOf(value);
            resultForN1 += value*n1[i+2];
            resultForN2 += value*n2[i+2];
        }

        if (resultForN2 % 11 == 10) {
            resultForN2 = 0;
        } else {
            resultForN2 = resultForN2 % 11;
        }

        this.INN += String.valueOf(resultForN2);

        resultForN1 += resultForN2 * n1[10];

        if (resultForN1 % 11 == 10) {
            resultForN1 = 0;
        } else {
            resultForN1 = resultForN1 % 11;
        }

        this.INN += String.valueOf(resultForN1);

    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public boolean isSex() {
        return sex;
    }

    public int getFlatNum() {
        return flatNum;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public String getCountry() {
        return country;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getINN() {
        return INN;
    }

    public String getName() {
        return name;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public String getRegion() {
        return region;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getStreet() {
        return street;
    }

    public String getSurname() {
        return surname;
    }
}
