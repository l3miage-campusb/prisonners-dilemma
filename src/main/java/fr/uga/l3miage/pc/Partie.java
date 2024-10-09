package fr.uga.l3miage.pc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Partie {
    private Player player1;
    private Player player2;

    private int nbTour;
    private Player gagnant;
    private Strategie strategie;

    private ArrayList<Tour> historique = new ArrayList<Tour>();

    public Partie(Player player1,Player player2,int nbTour){
        this.player1 = player1;
        this.player2 = player2;
        this.nbTour = nbTour;

    }

    public void jouerPartie() throws IOException {
        int i =0;
        while(i<nbTour && player1.isPlaying && player2.isPlaying){
            jouerTour();
            i++;
        }

        if (!player1.isPlaying && !player2.isPlaying) {
            System.out.println("Les deux joeuurs se sont decconectÈs en meme temps");
        }

        else if(!player1.isPlaying ){
            finishGameAlone(player2,nbTour-i);
        }
        else if (!player2.isPlaying) {
            finishGameAlone(player1,nbTour-i);
        }

    }

    public void AfficherHistorique(){
        (historique).forEach((Tour tour) -> {
            System.out.println("Tour num√©ro "+tour.numero+" choix du joueur 1 : "+tour.choixJoueur1+" choix du joueur 2 : "+tour.choixJoueur2);
        } );
    }

    private void jouerTour() throws IOException {
        getPlayerChoices();
        sendResult();
        historique.add(new Tour(player1.choice,player2.choice));
    }

    private void finishGameAlone(Player player, int nbTour) throws IOException {
        int i =0;
        while(i<nbTour && player.isPlaying){
            jouerTourAlone(player);
            i++;
        }
    }

    private void jouerTourAlone(Player player) throws IOException {
        //On rÈcupËre le choix du joueur encore en train de jouer
        getPlayerChoice(player);
        //On instancie automatiquement le choix du joueur qui a quittÈ ‡ l'aide de la stratÈgie qu'il a donner avant de partir;
        InstanciateDisconnectedChoice();
        sendResult();
    }

    private void InstanciateDisconnectedChoice(){
        if(!player1.isPlaying){
            player1.choice = strategie.faireUnChoix(historique);
        }
        else if(!player2.isPlaying){
            player2.choice = strategie.faireUnChoix(historique);
        }
    }


    private void getPlayerChoices() throws IOException {
        String response1 = player1.in.readUTF();
        HandleResponse(player1, response1);

        String response2 = player2.in.readUTF();
        HandleResponse(player2,response2);

        System.out.println("choix j1 : "+response1);
        System.out.println("choix j2 : "+response2);
    }

    private void getPlayerChoice(Player player) throws IOException {
        String response = player.in.readUTF();
        HandleResponse(player,response);
        System.out.println("choix : "+response);

    }

    private void HandleResponse(Player player, String response){
        switch (response){
            case "cooperer" :
                player.choice = Choice.COOPERER;
                break;
            case "trahir":
                player.choice = Choice.TRAHIR;
                break;
            case "1":
                HandleDisconnection(player, new Strategie());
            case "2":
                HandleDisconnection(player, new Strategie());
                break;

            default:                                 //Si one ne comprend pas la rÈponse du joeuur, on se dit qu'il a voulu coopÈrer
                player.choice = Choice.COOPERER;

        }
    }

    private void HandleDisconnection(Player player, Strategie strategie){
        player.isPlaying = false;
        this.strategie = strategie;
        player.choice = strategie.faireUnChoix(historique);
    }

    private void sendResult() throws IOException {
        //Envoie des r?sultats
        if(player1.choice == Choice.TRAHIR && player2.choice == Choice.TRAHIR){
            if(player1.isPlaying){
                player1.out.writeUTF("Vous avez tous les deux trahis l'autre");
            }
            if(player2.isPlaying){
                player2.out.writeUTF("Vous avez tous les deux trahis l'autre");
            }

            player1.score+=1;
            player2.score+=1;
        }
        else if(player1.choice == Choice.COOPERER && player2.choice == Choice.COOPERER){
            if(player1.isPlaying) {
                player1.out.writeUTF("Vous avez tous les deux coop?rer");
            }
            if(player2.isPlaying) {
                player2.out.writeUTF("Vous avez tous les deux coop?rer");
            }
            player1.score+=3;
            player2.score+=3;
        }
        else if(player1.choice == Choice.TRAHIR && player2.choice == Choice.COOPERER){
            if(player1.isPlaying) {
                player1.out.writeUTF("Tu as r?ussi a trahir le deuxi?me joueur");
            }
            if(player2.isPlaying) {
                player2.out.writeUTF("Tu as ?t? tarhis par le deuxi?me joueur");
            }
            player1.score+=5;
        }
        else if(player1.choice == Choice.COOPERER && player2.choice == Choice.TRAHIR){
            if(player1.isPlaying) {
                player1.out.writeUTF("Tu as ?t? tarhis par le deuxi?me joueur");
            }
            if(player2.isPlaying) {
                player2.out.writeUTF("Tu as r?ussi a trahir le deuxi?me joueur");
            }
            player2.score+=5;
        }
    }



}
