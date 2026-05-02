package com.example;
import java.net.*;


/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program should allow the client to send it messages. The messages should then
 * become visible to all other clients.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example).
 *
 * This version of the program creates a new thread for
 * every connection request.
 */
public class ChatServerWithThreads {

    public static final int LISTENING_PORT = 9876;

    public static void main(String[] args) {

        Game game = new Game();
try (ServerSocket server = new ServerSocket(LISTENING_PORT)) {
            System.out.println("Server running on port " + LISTENING_PORT);

            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connected: " + socket.getInetAddress());

                ConnectionHandler handler = new ConnectionHandler(socket, game);
                handler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        /* //ServerSocket listener;  // Listens for incoming connections.
        //Socket connection;      // For communication with the connecting program.

        // Accept and process connections forever, or until some error occurs. 

        try {
            listener = new ServerSocket(LISTENING_PORT);
            System.out.println("Listening on port " + LISTENING_PORT);
            while (true) {
                connection = listener.accept();
                // Accept next connection request and handle it.
                ConnectionHandler h = new ConnectionHandler(connection, game);
                h.start();
                 
            }
        }
        catch (Exception e) {
            System.out.println("Sorry, the server has shut down.");
            System.out.println("Error:  " + e);
            return;
        }*/

    }  // end main()


    /**
     *  Defines a thread that handles the connection with one
     *  client.
     */
  /*  private static class ConnectionHandler extends Thread {
        private static int clientIdCount = 0;
        private int clientId;
        private static volatile ArrayList<ConnectionHandler> handlers = new ArrayList<ConnectionHandler>(); //This is shared among all the connection handlers
        Socket client;
        ObjectOutputStream oos; //you'll need to define this one for when you're ready to talk back to the client!
        ObjectInputStream ois;

        ConnectionHandler(Socket socket) {
            client = socket;
            try{
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());

            synchronized (handlers) {
            clientId = clientIdCount++;
            handlers.add(this);
            }}
            catch(Exception e){}
           
        }
        public void run() {
            String clientAddress = client.getInetAddress().toString();
            while(true) {
                try {
                    String message = (String)ois.readObject();
                    if(!message.equals("disconnect")){
                        String finalMessage = "User " + clientId + ": " + message;
                        
                        synchronized (handlers) {
                            for (ConnectionHandler h : handlers){
                                try{
                                    h.oos.writeObject(finalMessage);
                                    h.oos.flush();
                                }
                                catch (IOException e){
                                }
                            }
                        }




                    }
                    else{
                        System.out.println("closing connection");
                        break;
                       
                    }
                }
                catch(EOFException e){
                    System.out.println("the client disconnected, bye!!!");
                    synchronized (handlers) {
                    handlers.remove(this);
                    }
                    break;
                }
                catch (Exception e){
                    System.out.println("Error on connection with: "
                            + clientAddress + ": " + e);
                }
            }
        }
    }*/


}