package com.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class WordGettter{
      ArrayList<String> words;

 public WordGettter() throws FileNotFoundException {
    
      //this reads all words from the words doc
      //taken from https://research-says.com/5000-word-list/
      //I repeated the first list to make common words more common
      //the bottom also has some extra words :)
      // -milan

    words = new ArrayList<String>();


      
        // pass the path to the file as a parameter
        File file = new File("/workspaces/game1/src/main/java/com/example/Words.txt");
      
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
            words.add(sc.nextLine());

        // while(true)
        //   System.out.println(words.get((int)(Math.random()*words.size())));

        sc.close();
        
    }
    public ArrayList<String> get30Words(){
        ArrayList<String> a = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            a.add(words.get((int)(Math.random()*words.size())));
        }
        return a;
    }

    public ArrayList<String> get50Words(){
        ArrayList<String> a = new ArrayList<>();
        System.out.println(words.size());
        for(int i = 0; i < 50; i++){
            a.add(words.get( (int) (Math.random()* (words.size()) ) )); 
        }
        return a;
    }

    
}