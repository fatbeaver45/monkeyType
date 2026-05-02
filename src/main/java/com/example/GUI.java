package com.example;
// Milan Chandra, Ulises Cantor, Quinn
// Menchukov
// Period 5
// 3/27/2026
// Server game project
// Mouse maze 1 vs 1 game where each player has to compete to 
// complete the maze first, utilizing onMouseDrag() 
// and nodes on screen that lead players to their final destination

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class GUI {

    public String old = "";
    JTextArea wordsToType;
    JFrame everything;
    JTextArea myTyped;
    public GUI(SocketClientExample client){
        
         everything = new JFrame("Racer GUIs");

        everything.setSize(900, 700);
   
        everything.setLayout(new BorderLayout());
        everything.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //  try {
        //     System.out.println(getClass().toString());
        //     BufferedImage blackImg = ImageIO.read(getResource("background1.png"));
        //     everything.setContentPane(new ImagePanel(blackImg));
            

        // } catch (IOException e) {
        //     e.printStackTrace();
            
        // }
        
        wordsToType = new JTextArea();
       
        wordsToType.setEditable(false);
        wordsToType.setText("Waiting...");
         myTyped = new JTextArea();

        everything.add(wordsToType, BorderLayout.CENTER);
        everything.add(myTyped, BorderLayout.SOUTH);

             everything.setVisible(true);

        myTyped.getDocument().addDocumentListener(new DocumentListener() {

            private void update() {
                client.send(myTyped.getText());
            }

            public void insertUpdate(DocumentEvent e) { update(); }
            public void removeUpdate(DocumentEvent e) { update(); }
            public void changedUpdate(DocumentEvent e) { update(); }
        });

   
        //   javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //     public void run() {
        //         everything.createAndShowGUI();
        //     }
        // });
        
    }
    //precondition: the typed variable contains how much the opponent has typed CORRECTLY
    public void highlightTheirWords(String typed){
        SwingUtilities.invokeLater(() -> 
        everything.setTitle("Enemy: " + typed)
    );
       
    }


    public void setWords(String target){
        SwingUtilities.invokeLater(() -> 
        wordsToType.setText(target)
    );
    }


  /*  public void run(){
        
        while(true){
            // break out 
            if (wordsToType.getText().equals(myTyped.getText())){
         
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
    } */

    public void winScreen(){
                SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(everything, "YOU WIN!");
        });
// ImageIcon image = new ImageIcon("winns.png");
// JLabel label = new JLabel(image);
// everything.add(label);
    }


    public void loseScreen(){
                SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(everything, "YOU LOSE!");
        });
// ImageIcon image2 = new ImageIcon("losees.png");
// JLabel label2 = new JLabel(image2);
// everything.add(label2);
    }
}
 