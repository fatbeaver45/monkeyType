package com.example;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements java Socket server
 * @author pankaj
 * This is an example socket server that you can use for reference. You should be able to run
 * it with SocketClientExample to see results. Note that since this doesn't use threads it can only
 * handle one connection at a time.
 */
public class SocketServerExample {
    
    //static ServerSocket variable
    private static ServerSocket server1;
    //socket server port on which it will listen
    private static int port1 = 9876;
    private static ServerSocket server2;
    //socket server port on which it will listen
    private static int port2 = 9877;
    public boolean gameWon = false;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server1 = new ServerSocket(port1);
        server2 = ne ServerSocket(port2);

Game g = new Game();
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){


            //creating socket and waiting for client connection
            Socket socket1 = server1.accept();
            Socket socket2 = server1.accept();
            //read from socket to ObjectInputStream object
            // MUST BE OPPOSITE ORDER FOR INPUT/OUTPUT STREAM BETWEEN SERVER AND CLIENT
            //run server first
            ObjectInputStream ois1 = new ObjectInputStream(socket1.getInputStream());
            ObjectInputStream ois2 = new ObjectInputStream(socket2.getInputStream());

            // MAKE THE INPUT STREAMS IN SEPARATE THREADS   

            

            //convert ObjectInputStream object to String
            String message = (String) ois1.readObject();
            String message2 = (String) ois2.readObject();
            //create ObjectOutputStream object
            ObjectOutputStream oos1 = new ObjectOutputStream(socket1.getOutputStream());
            //write object to 
            ObjectOutputStream oos2 = new ObjectOutputStream(socket2.getOutputStream());







            /*
                        if (curr>textOneRos.length()-1){

if (!gameWon){
gameWon = true;
               ImageIcon icon = new ImageIcon("winns.png");
JLabel label = new JLabel(icon);
myJPanel.add(label);
}
else{
               ImageIcon icon = new ImageIcon("losees.png");
JLabel label = new JLabel(icon);
myJPanel.add(label);
}
            }
            else if (message.equals(textOneRos.get(curr))){
                curr++;
            }
*/

            oos1.writeObject(message2);
            oos2.writeObject(message1);
            //close resources
            ois1.close();
            oos1.close();
            ois2.close();
            oos2.close();
            socket1.close();
            socket2.close();

            //terminate the server if client sends exit request
            if(message1.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }
    
}
