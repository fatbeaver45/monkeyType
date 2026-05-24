package com.example;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.table.DefaultTableModel;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void singlePlayerShouldReceiveStartMessage() throws Exception {
        Game game = new Game();
        try (ServerSocket server = new ServerSocket(0)) {
            server.setSoTimeout(1000);

            Thread acceptThread = new Thread(() -> {
                try {
                    Socket accepted = server.accept();
                    ConnectionHandler handler = new ConnectionHandler(accepted, game);
                    handler.start();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            acceptThread.start();

            Socket client = new Socket("localhost", server.getLocalPort());
            client.setSoTimeout(1000);
            ObjectOutputStream clientOut = new ObjectOutputStream(client.getOutputStream());
            clientOut.flush();
            ObjectInputStream clientIn = new ObjectInputStream(client.getInputStream());

            Object message = clientIn.readObject();

            assertNotNull(message);
            assertTrue(message instanceof String);
            assertTrue(((String) message).startsWith("START|"));

            client.close();
            acceptThread.join(1000);
        }
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
        assertFalse( false );
    }
}
