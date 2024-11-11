package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategyPacificateurNaif implements  IStrategy{

    private final SecureRandom secureRandom = new SecureRandom();
    private final double probabiliteAleatoire = 0.2; // 20% de probabilidad de jugar al azar

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        if (historique.isEmpty()) {
            // En el primer turno, decide aleatoriamente entre TRAHIR y COOPERER
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        Tour dernierTour = historique.get(historique.size()-1);
        Choice dernierChoix1 = dernierTour.choixJoueur1;
        Choice dernierChoix2 = dernierTour.choixJoueur2;

        // Verifica si se juega aleatoriamente según la probabilidad
        if (secureRandom.nextDouble() < probabiliteAleatoire && (dernierChoix1==Choice.TRAHIR || dernierChoix2==Choice.TRAHIR)) {
            return Choice.COOPERER;
        }

        if(joueurRemplace==1){
            return dernierChoix2;
        }

        if(joueurRemplace==2){
            return dernierChoix1;
        }


        return null;
    }
}
