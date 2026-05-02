package com.example;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler extends Thread {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Game game;

    public ConnectionHandler(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
        } catch (IOException e) {
            game.removePlayer(this);
        }
    }

    @Override
    public void run() {
        game.addPlayer(this);

        try {
            while (true) {
                String msg = (String) ois.readObject();
                game.handleInput(this, msg);
            }

        } catch (Exception e) {
            System.out.println("Client disconnected");

        } finally {
            game.removePlayer(this);
            try { socket.close(); } catch (IOException ignored) {}
        }
    }
}