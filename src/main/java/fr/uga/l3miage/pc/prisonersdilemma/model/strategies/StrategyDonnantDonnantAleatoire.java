package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.ArrayList;

public class StrategyDonnantDonnantAleatoire implements IStrategy{
    private final SecureRandom secureRandom = new SecureRandom();
    private final double probabiliteAleatoire = 0.2; // 20% de probabilidad de jugar al azar

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        if (historique.isEmpty()) {
            // En el primer turno, decide aleatoriamente entre TRAHIR y COOPERER
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        // Verifica si se juega aleatoriamente según la probabilidad
        if (secureRandom.nextDouble() < probabiliteAleatoire) {
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        // Si no es aleatorio, sigue la elección del último turno del adversario
        Tour dernierTour = historique.get(historique.size() - 1);
        if(joueurRemplace==1){
            return dernierTour.choixJoueur2;
        }

        if(joueurRemplace==2){
            return dernierTour.choixJoueur1;
        }

        return null;
    }
}
