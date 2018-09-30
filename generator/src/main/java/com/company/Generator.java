package com.company;


import java.io.*;
import java.util.Random;

public class Generator {
    public void generate() {
        String[] str = generateRandomWords(2);
        String folderName = new File(".").getAbsolutePath() + "/" + str[0];
        File folder = new File(folderName);
        boolean created = folder.mkdir();
        if(created)
            System.out.println("Folder has been created");

        File txt = new File(folderName+ "/" + str[1] + ".txt");
        try {
            created = txt.createNewFile();
            if(created)
                System.out.println("txt file has been created");
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    public static String[] generateRandomWords(int numberOfWords)
    {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3];
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }
}

