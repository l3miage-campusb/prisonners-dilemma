package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategyDonnantDonnantAleatoire implements IStrategy{
    private final SecureRandom secureRandom = new SecureRandom();
    private final double probabiliteAleatoire = 0.2; // 20% de probabilité de jouer aléatoirement

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        if (historique.isEmpty()) {
            // Au premier tour, décide aléatoirement entre TRAHIR et COOPERER
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        // Vérifie si le choix est aléatoire selon la probabilité
        if (secureRandom.nextDouble() < probabiliteAleatoire) {
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        // Si ce n'est pas aléatoire, suit le choix du dernier tour de l'adversaire
        Tour dernierTour = historique.get(historique.size() - 1);
        if(joueurRemplace == 1){
            return dernierTour.choixJoueur2;
        }

        else {
            return dernierTour.choixJoueur1;
        }

    }
}
