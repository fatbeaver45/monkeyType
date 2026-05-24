package com.example;
import java.io.FileNotFoundException;
import java.util.*;

public class Game {

    // GUI gui;
    WordGettter wordGettter;
    private ArrayList<ConnectionHandler> players = new ArrayList<>();
    boolean gameActive = false;
    ArrayList<String> textOneRos = new ArrayList<>();
    String target = "";

    public synchronized void addPlayer(ConnectionHandler p){
        players.add(p);

        if (gameActive == false && players.size() ==2 ) {
            startGame();
        } else if (!target.isEmpty()) {
            p.send("START|" + target);
        }
    }

    public synchronized void removePlayer(ConnectionHandler p){
        players.remove(p);
        gameActive = false;
        if (players.isEmpty()) {
            target = "";
        }
    }

    private void startGame(){
                wordGettter = new WordGettter();
            
            
            textOneRos = wordGettter.get50Words();

            target = String.join(" ", textOneRos);
gameActive = true;

for (ConnectionHandler p : players){
    p.send("START|" + target);
}
    }

    public synchronized void handleInput(ConnectionHandler player, String m){
        if (gameActive == false){
            return;
        }

            for (ConnectionHandler p : players){
                if (p != player){
                    p.send("PROGRESS|" + m);
                }
            }

        if (m.equals(target)){
            gameActive = false;
        for (ConnectionHandler p : players){
            if (p == player){
                p.send("WIN");
            }
            else{
                p.send("LOSE");
            }
            }
        resetGame();
        
        
        }
       

            
        

    }

    private void resetGame() {
        gameActive = false;
        target = "";
    }

    // transient -> i don't want to send this information
    // related to serialized things



/*    public Game(){
        
             
            try {
                wordGettter = new WordGettter();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
*/

}