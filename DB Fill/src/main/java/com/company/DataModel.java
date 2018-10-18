package com.company;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.chart.PieChart;
import org.json.JSONArray;
import org.json.JSONObject;

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
        try {
            getDataFromAPI();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        generateAddress();
        generateBirthDate();
        generateINN();

    }

    public void getDataFromAPI() throws UnirestException {
        Consts value = new Consts();
        HttpResponse<JsonNode> jsonResponse =
                Unirest.get(value.API).asJson();
        JSONObject body = jsonResponse.getBody().getObject();
        String date = body.getString("date");
        this.name = body.getString("fname");
        this.surname = body.getString("lname");
        this.secondName = body.getString("patronymic");
        this.city = body.getString("city");
        this.postIndex = body.getString("postcode");
        this.street = body.getString("street");
        this.houseNum = body.getInt("house");
        this.flatNum = body.getInt("apartment");

        String gender = body.getString("gender");
        if (gender.equals("m"))
            this.sex = true;
        else
            this.sex = false;
    }

    public void generateINN() {
        Random random = new Random();

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

    public void generateBirthDate() {
        Random random = new Random();
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

    }


    private void generateAddress() throws IOException {
        Random random = new Random();
        String path = new File("").getAbsolutePath() + "/" + "src/main/resources/";

        List<String> regions = readAllLines(Paths.get(path+"region.txt"), StandardCharsets.UTF_8);
        this.region = regions.get(random.nextInt(regions.size()) % regions.size());
        List<String> countries = readAllLines(Paths.get(path+"country.txt"), StandardCharsets.UTF_8);
        this.country = countries.get(random.nextInt(countries.size()) % countries.size());
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
