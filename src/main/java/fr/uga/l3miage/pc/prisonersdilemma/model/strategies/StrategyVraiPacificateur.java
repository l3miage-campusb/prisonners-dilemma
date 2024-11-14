package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategyVraiPacificateur implements IStrategy{

    private final SecureRandom secureRandom = new SecureRandom();
    private final static double probabiliteAleatoire = 0.2; //

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        if(historique.size() < 2){
            return Choice.COOPERER;
        }

        Tour avantDernierTour = historique.get(historique.size()-2);
        Tour dernierTour = historique.get(historique.size()-1);


        if(joueurRemplace==1 && dernierTour.getChoixJoueur2()==Choice.TRAHIR && avantDernierTour.getChoixJoueur2()==Choice.TRAHIR){
            return secureRandom.nextDouble() < probabiliteAleatoire ? Choice.COOPERER : Choice.TRAHIR;
        }

        if(joueurRemplace==2 && dernierTour.getChoixJoueur1()==Choice.TRAHIR && avantDernierTour.getChoixJoueur1()==Choice.TRAHIR){
            return secureRandom.nextDouble() < probabiliteAleatoire ? Choice.COOPERER : Choice.TRAHIR;
        }

        return Choice.COOPERER;
    }
}
