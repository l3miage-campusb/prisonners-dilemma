package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.security.SecureRandom;
import java.util.List;

public class StrategySondeurRepentant extends StrategieAbstract {

    private static final double PROBABILITETRAHISON = 0.05; // Probabilité de trahir même après la coopération de l'adversaire

    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(this.getSeed());

        if(historique.isEmpty()){
            return Choice.COOPERER; // Coopérer au premier tour
        }

        // Dernier tour de l'historique
        Tour dernierTour = historique.get(historique.size() - 1);

        // Si l'adversaire a trahi dans le dernier tour, on choisit de coopérer (en se montrant repentant)
        if((joueurRemplace == 1 && dernierTour.getChoixJoueur2() == Choice.TRAHIR) ||
                (joueurRemplace == 2 && dernierTour.getChoixJoueur1() == Choice.TRAHIR)) {
            return Choice.COOPERER;
        }

        // Ajouter un élément aléatoire
        if(secureRandom.nextDouble() < PROBABILITETRAHISON) {
            return Choice.TRAHIR; // Chance de trahir même si l'adversaire a coopéré
        }

        // Sinon, jouer comme le dernier coup de l'adversaire
        if(joueurRemplace == 1) {
            return dernierTour.getChoixJoueur2(); // Imiter le dernier coup de l'adversaire (joueur 2)
        } else {
            return dernierTour.getChoixJoueur1(); // Imiter le dernier coup de l'adversaire (joueur 1)
        }
    }
}
