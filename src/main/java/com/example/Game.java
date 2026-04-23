package com.example;
import java.util.ArrayList;

import javax.swing.*;

public class Game {

    // GUI gui;
    WordGettter wordGettter;
    ArrayList<String> textOneRos = new ArrayList<>();
    String target = "";
    ArrayList<GUI> guis = new ArrayList<GUI>(); 

    // transient -> i don't want to send this information
    // related to serialized things

    public Game(){
        
             
            wordGettter = new WordGettter();
            textOneRos = wordGettter.get50Words();
            for(int i = 0; i < textOneRos.size(); i++){
                target+=(textOneRos.get(i)+" ");
            }
    }
            public void addGUI(GUI g){
                guis.add(g);
                g.setWords(target);
           
           }

           public void run(){
            while(true){
                for(int i = 0; i<guis.size()-1; i++){
                    if (guis.get(i).getWin()){
                        guis.get(i).winScreen();
                                        for(int j = 0; j<i-1; j++){
                                            guis.get(j).loseScreen();
                                        }
                                            for(int k = i+1; k<guis.size()-1; k++){
                                                      guis.get(k).loseScreen();
                                            }

break;

                    }
                }
            }
           
}

}