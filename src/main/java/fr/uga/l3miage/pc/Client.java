package fr.uga.l3miage.pc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Client {

    private static void afficherStrategieDisponible(){
        System.out.println("Strategie disponible : Aleatoire, DonnantDonnant , Rancunier , ToujoursCooperer , ToujoursTrahir");
    }

    private static int asknbTour(Scanner scanner){
        System.out.println("Combien de tours voulez vous jouer ?");
        //PAs encore de verification sur le nombre de tour donné
        int nbTour = scanner.nextInt();
        
        return nbTour;
    }

    private static String askToContinue(Scanner scanner){
        String reponse = "";
        System.out.println(" - Voulez-vous continuer � jouer ? (Y/N)");
        reponse = scanner.nextLine();
        while(!reponse.equals("Y") && !reponse.equals("N")) {
            System.out.println(" - Mauvaise réponse, voulez-vous continuer � jouer ? (Y/N)");
            reponse = scanner.nextLine();
        }
        return reponse;
    }

    private static String askChoice(Scanner scanner){
        System.out.println(" - Faites votre choix : 'cooperer' ou 'trahir' ?");
        String choice = scanner.nextLine();
        while(!choice.equals("cooperer") && !choice.equals("trahir")){
            System.out.println(" -Mauvaise reponse, faites votre choix : 'cooperer' ou 'trahir' ?");
            choice = scanner.nextLine();
        }
        return choice;
    }

    private static String askStrategie(Scanner scanner){
        System.out.println(" - Choisissez une strategie parmis : ");
        afficherStrategieDisponible();
        String strategie = scanner.nextLine();
        List<String> strategiesDisponible = Arrays.asList("Aleatoire", "DonnantDonnant", "Rancunier","ToujoursCooperer","ToujoursTrahir");
        while(!strategiesDisponible.contains(strategie)){
            System.out.println(" -Mauvaise reponse, choisissez une strategie parmis : ");
            afficherStrategieDisponible();
            strategie = scanner.nextLine();
        }
        return strategie;
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

                nbTour = asknbTour(scanner);
                out.writeInt(nbTour);
                System.out.println("nb tour envoyer au serveru : "+nbTour);
            }
            else if(numeroConnexion == 2){
                nbTour = in.readInt();
            }

            boolean continuer = true;
            int i =0;
            while (i<nbTour && continuer) {
                continuer = askToContinue(scanner).equals("Y");
                if (continuer){
                    String choice = askChoice(scanner);
                    out.writeUTF(choice);
                    String resultat = in.readUTF();
                    System.out.println(resultat);
                }
                else{
                    String strategie = askStrategie(scanner);
                    out.writeUTF(strategie);
                }
                i++;

            }


        } catch (IOException e) {
            System.out.println("Erreur lors de la création de la serveur socket");
        }
    }
}
