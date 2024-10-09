package fr.uga.l3miage.pc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Partie {
    private Player player1;
    private Player player2;

    private int nbTour;
    private Player gagnant;

    private ArrayList<Tour> historique;

    public Partie(Player player1,Player player2,int nbTour){
        this.player1 = player1;
        this.player2 = player2;
        this.nbTour = nbTour;

    }

    public void jouerPartie() throws IOException {
        for(int i = 0;i<nbTour;i++){
            jouerTour();
        }
    }

    public void AfficherHistorique(){
        (historique).forEach((Tour tour) -> {
            System.out.println("Tour numero "+tour.numero+" choix du joueur 1 : "+tour.choixJoueur1+" choix du joueur 2 : "+tour.choixJoueur2);
        } );
    }

    private void jouerTour() throws IOException {
        getPlayerChoices();
        sendResult();
        historique.add(new Tour(player1.choice,player2.choice));
    }

    private void getPlayerChoices() throws IOException {
        String choice1 = player1.in.readUTF();
        HandleChoice(player1, choice1);

        String choice2 = player2.in.readUTF();
        HandleChoice(player2,choice2);

        System.out.println("choix j1 : "+choice1);
        System.out.println("choix j2 : "+choice2);
    }

    private void HandleChoice(Player player, String choix1){
        switch (choix1){
            case "cooperer" :
                player.choice = Choice.COOPERER;
                break;
            case "trahir":
                player.choice = Choice.TRAHIR;
                break;
            default:
                player.choice = Choice.COOPERER;

        }
    }

    private void sendResult() throws IOException {
        //Envoie des r?sultats
        if(player1.choice == Choice.TRAHIR && player2.choice == Choice.TRAHIR){
            player1.out.writeUTF("Vous avez tous les deux trahis l'autre");
            player2.out.writeUTF("Vous avez tous les deux trahis l'autre");
            player1.score+=1;
            player2.score+=1;
        }
        else if(player1.choice == Choice.COOPERER && player2.choice == Choice.COOPERER){
            player1.out.writeUTF("Vous avez tous les deux coop?rer");
            player2.out.writeUTF("Vous avez tous les deux coop?rer");
            player1.score+=3;
            player2.score+=3;
        }
        else if(player1.choice == Choice.TRAHIR && player2.choice == Choice.COOPERER){
            player1.out.writeUTF("Tu as r?ussi a trahir le deuxi?me joueur");
            player2.out.writeUTF("Tu as ?t? tarhis par le deuxi?me joueur");
            player1.score+=5;
        }
        else if(player1.choice == Choice.COOPERER && player2.choice == Choice.TRAHIR){
            player1.out.writeUTF("Tu as ?t? tarhis par le deuxi?me joueur");
            player2.out.writeUTF("Tu as r?ussi a trahir le deuxi?me joueur");
            player2.score+=5;
        }
    }


}
