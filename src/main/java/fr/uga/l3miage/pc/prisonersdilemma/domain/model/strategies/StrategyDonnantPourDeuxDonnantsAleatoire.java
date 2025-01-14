package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategyDonnantPourDeuxDonnantsAleatoire extends StrategyDonnantPourDeuxDonnants{

    private static final double PROBABILITEALEATOIRE = 0.05; //

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(this.getSeed());

        if(historique.size() < 2){
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        if (secureRandom.nextDouble() < PROBABILITEALEATOIRE) {   //si prob in 20% aleatoiree
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        Tour avantDernierTour = historique.get(historique.size()-2);
        Tour dernierTour = historique.get(historique.size()-1);

        boolean coupsEgaux;

        if(joueurRemplace==1){
            coupsEgaux=coupsEgaux(2,avantDernierTour,dernierTour);
            return coupsEgaux ?  dernierTour.getChoixJoueur2() : inverse(dernierTour.getChoixJoueur2());     //On suppose que on doit envoyer le meme EXCLUSIVEMENT s'il y a deux coups pareils d'affilee
        }

        else{
            coupsEgaux=coupsEgaux(1,avantDernierTour,dernierTour);
            return coupsEgaux ? dernierTour.getChoixJoueur1() : inverse(dernierTour.getChoixJoueur1());
        }

    }
}
