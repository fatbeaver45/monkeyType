package com.example;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.SwingUtilities;



public class SocketClientExample {

    /*
     * Modify this example so that it opens a dialogue window using java swing,
     * takes in a user message and sends it
     * to the server. The server should output the message back to all connected
     * clients
     * (you should see your own message pop up in your client as well when you send
     * it!).
     * We will build on this project in the future to make a full fledged server
     * based game,
     * so make sure you can read your code later! Use good programming practices.
     * ****HINT**** you may wish to have a thread be in charge of sending
     * information
     * and another thread in charge of receiving information.
     */


    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private GUI gui;
    
    public SocketClientExample()
            throws Exception {
        // get the localhost IP address, if server is running on some other IP, you need
        // to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostName(), 9876);
        // write to socket using ObjectOutputStream
         oos = new ObjectOutputStream(socket.getOutputStream());
         oos.flush();
         ois = new ObjectInputStream(socket.getInputStream());
 
         gui = new GUI(this);


         startListening();
            }

            public void send(String m) {
                try {
                    oos.writeObject(m);
                oos.flush();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            private void startListening() {
                new Thread(() -> {
                    try {
                        while (true) {
                            String msg = (String) ois.readObject();

                            SwingUtilities.invokeLater(() -> {
                                handleMessage(msg);
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("disconnected");
                    }
                }).start();
            }


        
    /*    Thread t = new Thread(() -> {
        while (true) {
            if (!gui.old.equals(myOld))
                myOld = gui.old;
                input = myOld;
                try {
                    oos.writeObject(input);
                    oos.flush();
                } catch (IOException etwo) {
            } */
            
            // public static void main(String[] args)
            //         throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {//yea idk why its done like this eier
            //     new SocketClientExample();
            

    // Thread t1 = new Thread(() -> {
    //     String message = "";
    //     try {
    //         message = (String)ois.readObject();
    //     } catch (ClassNotFoundException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    //     gui.highlightTheirWords(message);
    // });
    // t.start();
    // t1.start();


private void handleMessage(String m) {
    if (m.startsWith("START|")){
        gui.setWords(m.substring(6));
    }
    else if (m.equals("WIN")){
        gui.winScreen();
    }
    else if (m.equals("LOSE")){
        gui.loseScreen();
    }
    else if (m.startsWith("PROGRESS|")){
        gui.highlightTheirWords(m.substring(9));
    }
}

        // display via swing
        // JFrame frame = new JFrame("Client Chat");
        // JTextArea serverText = new JTextArea();
        // JTextField clientText = new JTextField();

        // serverText.setEditable(false);
        // clientText.setEditable(true);

        // frame.setLayout(new BorderLayout());
        // frame.setSize(400,400);

        // frame.add(serverText, BorderLayout.CENTER);
        // frame.add(clientText, BorderLayout.SOUTH);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setVisible(true);

        // Thread t = new Thread(() -> {
        // try {
        // while (true) {
        // String message = (String)ois.readObject();
        // serverText.append(message + "\n");

        // }

        // }
        // catch (Exception e){
        // serverText.append("disconnected");
        // }
        // });
        // t.start();

        // clientText.addActionListener(e -> {
        // String input = clientText.getText();
        // clientText.setText("");

        public static void main(String[] args) throws Exception {
            new SocketClientExample();
        }

    }
