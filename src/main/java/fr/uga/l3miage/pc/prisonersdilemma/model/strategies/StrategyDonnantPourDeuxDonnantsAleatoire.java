package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategyDonnantPourDeuxDonnantsAleatoire implements IStrategy{

    private final SecureRandom secureRandom = new SecureRandom();
    private final double probabiliteAleatoire = 0.2; //


    public boolean coupsEgaux(int joueur, Tour tour1, Tour tour2){
        if(joueur==1){
            return tour1.choixJoueur1==tour2.choixJoueur1;
        }
        else{
            return tour1.choixJoueur2==tour2.choixJoueur2;
        }

    }


    public Choice inverse(Choice choice){
        if(choice==Choice.COOPERER) return Choice.TRAHIR;
        return Choice.COOPERER;
    }


    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        if(historique.size() < 2){
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        if (secureRandom.nextDouble() < probabiliteAleatoire) {   //si prob in 20% aleatoire
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        Tour avantDernierTour = historique.get(historique.size()-2);
        Tour dernierTour = historique.get(historique.size()-1);

        boolean coupsEgaux;

        if(joueurRemplace==1){
            coupsEgaux=coupsEgaux(2,avantDernierTour,dernierTour);
            return coupsEgaux ?  dernierTour.choixJoueur2 : inverse(dernierTour.choixJoueur2);     //On suppose que on doit envoyer le meme EXCLUSIVEMENT s'il y a deux coups pareils d'affilee
        }

        else{
            coupsEgaux=coupsEgaux(1,avantDernierTour,dernierTour);
            return coupsEgaux ? dernierTour.choixJoueur1 : inverse(dernierTour.choixJoueur1);
        }

    }
}
