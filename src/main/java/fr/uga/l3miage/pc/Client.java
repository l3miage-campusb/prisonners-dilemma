package fr.uga.l3miage.pc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Client {

    private static void afficherStrategieDisponible(){
        System.out.println("Strategie disponible : 1, 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9");
    }



    public static void main(String[] args) {
        try (Socket serverSocket = new Socket("localhost", 12345)) {
            DataOutputStream out = new DataOutputStream(serverSocket.getOutputStream());
            DataInputStream in = new DataInputStream(serverSocket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            int nbTour = 0;
            int numeroConnexion = in.readInt();
            System.out.println("numero de connexion : "+numeroConnexion);
            if(numeroConnexion == 1){
                System.out.println("Combien de tours voulez vous jouer ?");
                nbTour = parseInt(scanner.nextLine());
                out.writeInt(nbTour);
                System.out.println("nb tour envoyer au serveru : "+nbTour);
            }
            else if(numeroConnexion == 2){
                nbTour = in.readInt();
            }

            boolean continuer = true;
            int i =0;
            while (i<nbTour && continuer) {
                System.out.println(" - Voulez-vous continuer à jouer ? (Y/N)");
                continuer = scanner.nextLine().equals("Y");
                if (continuer){
                    System.out.println(" - Faites votre choix : 'cooperer' ou 'trahir' ?");
                    String choice = scanner.nextLine();
                    out.writeUTF(choice);

                    String resultat = in.readUTF();
                    System.out.println(resultat);
                }
                else{
                    System.out.println(" - Choisissez une strategie parmis : ");
                    Client.afficherStrategieDisponible();
                    String strategie = scanner.nextLine();
                    out.writeUTF(strategie);
                }
                i++;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
