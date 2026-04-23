package com.example;
// Milan Chandra, Ulises Cantor, Quinn
// Menchukov
// Period 5
// 3/27/2026
// Server game project
// Mouse maze 1 vs 1 game where each player has to compete to 
// complete the maze first, utilizing onMouseDrag() 
// and nodes on screen that lead players to their final destination
import java.io.IOException;
import java.util.regex.Matcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class GUI {
    public String old = "";
    JTextArea wordsToType;
    JFrame everything;
    JTextArea myTyped;
    public GUI(){
        
         everything = new JFrame("Player GUIs");
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
boolean win = false;
        everything.setTitle("Multitype");
        everything.setSize(900, 700);
        everything.setVisible(true);
        everything.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //  try {
        //     System.out.println(getClass().toString());
        //     BufferedImage blackImg = ImageIO.read(getResource("background1.png"));
        //     everything.setContentPane(new ImagePanel(blackImg));
            

        // } catch (IOException e) {
        //     e.printStackTrace();
            
        // }
        
        wordsToType = new JTextArea(10, 30);
       
        wordsToType.setEditable(false);

        everything.add(wordsToType);
         myTyped = new JTextArea(10,30);
        myTyped.setEditable(true);
        everything.add(myTyped);


          javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                everything.createAndShowGUI();
            }
        });
        
    }
    //precondition: the typed variable contains how much the opponent has typed CORRECTLY
    public void highlightTheirWords(String typed){
        String a = wordsToType.getText().substring(0,typed.length()-1);
        String b = wordsToType.getText().substring(typed.length()-1);
        everything.wordsToType.setText("<html><font color=red>"+a+"/font"+b+"/html");
    }
    public void setWords(String target){
        everything.wordsToType.setText(target);
    }
    public void run(){
        
        while(true){
            // break out 
            if (wordsToType.getText().equals(myTyped.getText())){
                win = true;
                break;
            }
            if(!myTyped.getText().equals(old)){
                
                if(!wordsToType.getText().startsWith((myTyped.getText())))
                    myTyped.setText(old);
                else{
                    old = myTyped.getText();
                }

            }
        }
    }

    public boolean getWin(){
        return win;
    }
    public void winScreen(){
ImageIcon image = new ImageIcon("winns.png");
JLabel label = new JLabel(image);
everything.add(label);
    }
    public void loseScreen(){
ImageIcon image2 = new ImageIcon("losees.png");
JLabel label2 = new JLabel(image2);
everything.add(label2);
    }
}
 