package fr.uga.l3miage.pc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Player {
    Socket socket;
    Choice choice;
    int score = 0;
    DataInputStream in;
    DataOutputStream out;
    boolean isPlaying = true;


    public Player(Socket socket) throws IOException {
        this.socket = socket;
        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream()) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
