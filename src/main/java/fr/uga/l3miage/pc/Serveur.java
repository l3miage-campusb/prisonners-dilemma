package fr.uga.l3miage.pc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Serveur en attente des joueurs...");


            // Connexion du premier joueur
            Player player1 = new Player(serverSocket.accept());
            System.out.println("Joueur 1 connecte�.");


            //On indique au premier joueur qu'il est le premier et on lui demande le nombre de tours
            player1.out.writeInt(1);
            int nbTour = player1.in.readInt();
            System.out.println("nb tours  : "+nbTour);


            //Connexion du deuxième joueur
            Player player2 = new Player(serverSocket.accept());
            System.out.println("Joueur 2 connectd�.");

            //On indique au deuxième joueur qu'il est le deuxième et on lui envoie le nombre de tours
            player2.out.writeInt(2);
            player2.out.writeInt(nbTour);


            Partie partie = new Partie(player1,player2,nbTour);

            partie.jouerPartie();

            // Fermer les connexions
            player1.socket.close();
            player2.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
