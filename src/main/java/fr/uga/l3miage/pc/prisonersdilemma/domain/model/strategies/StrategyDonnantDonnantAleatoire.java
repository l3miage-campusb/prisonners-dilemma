package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.security.SecureRandom;
import java.util.List;

public class StrategyDonnantDonnantAleatoire extends StrategieAbstract{

    private static final double PROBABILITEALEATOIRE = 0.05; // 20% de probabilité de jouer aléatoirement

    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(this.getSeed());

        if (historique.isEmpty()) {
            // Au premier tour, décide aléatoirement entre TRAHIR et COOPERER
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        // Vérifie si le choix est aléatoire selon la probabilité
        if (secureRandom.nextDouble() < PROBABILITEALEATOIRE) {
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        // Si ce n'est pas aléatoire, suit le choix du dernier tour de l'adversaire
        Tour dernierTour = historique.get(historique.size() - 1);
        if(joueurRemplace == 1){
            return dernierTour.getChoixJoueur2();
        }

        else {
            return dernierTour.getChoixJoueur1();
        }

    }
}
