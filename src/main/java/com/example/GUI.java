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
import javax.swing.border.Border;

import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class GUI {

    public String old = "";
    JTextArea wordsToType;
    JFrame everything;
    JTextArea myTyped;
    JTextArea theirTyped;
    // JTable scores;
    // DefaultTableModel model;

    public GUI(SocketClientExample client) {

        everything = new JFrame("The blue box is where you type!");

        everything.setSize(900, 700);

        everything.setLayout(new BorderLayout());
        everything.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // try {
        // System.out.println(getClass().toString());
        // BufferedImage blackImg = ImageIO.read(getResource("background1.png"));
        // everything.setContentPane(new ImagePanel(blackImg));

        // } catch (IOException e) {
        // e.printStackTrace();

        // }

        wordsToType = new JTextArea();
        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
        Border blueLine = BorderFactory.createLineBorder(Color.BLUE);
        Border redLine = BorderFactory.createLineBorder(Color.RED);
        wordsToType.setBorder(blackLine);
        wordsToType.setEditable(false);
        wordsToType.setText("Waiting...");
        wordsToType.setLineWrap(true);
        wordsToType.setWrapStyleWord(true);
        wordsToType.setFont(new Font("Sans Serif", Font.PLAIN, 25));

        theirTyped = new JTextArea();
        theirTyped.setBorder(redLine);
        theirTyped.setLineWrap(true);
        theirTyped.setEditable(false);
        theirTyped.setWrapStyleWord(true);

        // String[][] data = {{"Enemy:", Integer.toString(0)}, {"You ",
        // Integer.toString(0)}};
        // String[] column = {"Player:", "Letters Typed"};
        // scores = new JTable(data, column);
        // model = (DefaultTableModel) scores.getModel();

        myTyped = new JTextArea();
        boolean hasStartedTyping = false;
        myTyped.setBorder(blueLine);
        myTyped.setLineWrap(true);
        myTyped.setWrapStyleWord(true);
        myTyped.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        JScrollPane myTypedScroll = new JScrollPane(myTyped,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        myTypedScroll.setPreferredSize(new Dimension(0, 120));

        everything.add(wordsToType, BorderLayout.NORTH);
        everything.add(myTypedScroll, BorderLayout.CENTER);
        everything.add(theirTyped, BorderLayout.SOUTH);

        everything.setVisible(true);

        myTyped.getDocument().addDocumentListener(new DocumentListener() {
            
           
            private void update() {
                client.send(myTyped.getText());
                boolean found = false;
                String myWords = myTyped.getText();
                String theWords = wordsToType.getText();
                for(int i = 0; i < myTyped.getText().length(); i++){
                    if(i > theWords.length()){
                        break;
                    }
                    if(!myWords.substring(i,i+1).equals(theWords.substring(i,i+1))){
                        myTyped.setBackground(new Color(255,0,0, 50));
                        found = true;
                        break;
                    }
                }
                if(!found){
                    myTyped.setBackground(Color.WHITE);
                }
                
                // model.setValueAt(Integer.toString(myTyped.getText().length()), 1, 1);
            }

            public void insertUpdate(DocumentEvent e) {
                update();
            }

            public void removeUpdate(DocumentEvent e) {
                update();
            }

            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });

        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
        // public void run() {
        // everything.createAndShowGUI();
        // }
        // });

    }

    // precondition: the typed variable contains how much the opponent has typed
    // CORRECTLY
    public void highlightTheirWords(String typed) {
        Runnable a = new Runnable() {
            public void run() {
                
                StringBuilder after = new StringBuilder("");
                for(int i = 0; i < typed.length(); i++){
                    if(typed.substring(i,i+1).equals(wordsToType.getText().substring(i,i+1))){
                        after.append(typed.substring(i,i+1));
                    }
                    else{
                        after.append(" ");
                    }
                    theirTyped.setText(after.toString());
                }
            }
        };
        SwingUtilities.invokeLater(() ->
        a.run()
        // everything.setTitle("Enemy: " +
        // wordsToType.getText().substring(0,Integer.parseInt(typed)))
        // theirTyped.setText("Enemy's text: " + wordsToType.getText().substring(0, Integer.parseInt(typed)))
        
        // model.setValueAt(typed, 0,1)
        );

    }

    public void setWords(String target) {
        SwingUtilities.invokeLater(() -> wordsToType.setText(target));
    }

    /*
     * public void run(){
     * 
     * while(true){
     * // break out
     * if (wordsToType.getText().equals(myTyped.getText())){
     * 
     * break;
     * }
     * if(!myTyped.getText().equals(old)){
     * 
     * if(!wordsToType.getText().startsWith((myTyped.getText())))
     * myTyped.setText(old);
     * else{
     * old = myTyped.getText();
     * }
     * 
     * }
     * }
     * }
     */

    public void winScreen() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(everything, "YOU WIN!");
        });
        // ImageIcon image = new ImageIcon("winns.png");
        // JLabel label = new JLabel(image);
        // everything.add(label);
    }

    public void loseScreen() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(everything, "YOU LOSE!");
        });
        // ImageIcon image2 = new ImageIcon("losees.png");
        // JLabel label2 = new JLabel(image2);
        // everything.add(label2);
    }
}
