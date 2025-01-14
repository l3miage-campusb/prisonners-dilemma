package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategyPacificateurNaif extends StrategieAbstract{

    private static final double PROBABILITEALEATOIRE = 0.05; // 20% de probabilidad de jugar al azar

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(this.getSeed());

        if (historique.isEmpty()) {
            // En el primer turno, decide aleatoriamente entre TRAHIR y COOPERER
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        Tour dernierTour = historique.get(historique.size()-1);
        Choice dernierChoix1 = dernierTour.getChoixJoueur1();
        Choice dernierChoix2 = dernierTour.getChoixJoueur2();

        // Verifica si se juega aleatoriamente según la probabilidad
        if (secureRandom.nextDouble() < PROBABILITEALEATOIRE && (dernierChoix1==Choice.TRAHIR || dernierChoix2==Choice.TRAHIR)) {
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
