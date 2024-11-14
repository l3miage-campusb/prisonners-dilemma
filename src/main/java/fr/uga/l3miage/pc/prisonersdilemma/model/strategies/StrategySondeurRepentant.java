package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategySondeurRepentant implements IStrategy {

    private final SecureRandom secureRandom = new SecureRandom();
    private final double probabiliteTrahison = 0.2; // Probabilité de trahir même après la coopération de l'adversaire

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        if(historique.size() < 1){
            return Choice.COOPERER; // Coopérer au premier tour
        }

        // Dernier tour de l'historique
        Tour dernierTour = historique.get(historique.size() - 1);

        // Si l'adversaire a trahi dans le dernier tour, on choisit de coopérer (en se montrant repentant)
        if((joueurRemplace == 1 && dernierTour.choixJoueur2 == Choice.TRAHIR) ||
                (joueurRemplace == 2 && dernierTour.choixJoueur1 == Choice.TRAHIR)) {
            return Choice.COOPERER;
        }

        // Ajouter un élément aléatoire
        if(secureRandom.nextDouble() < probabiliteTrahison) {
            return Choice.TRAHIR; // Chance de trahir même si l'adversaire a coopéré
        }

        // Sinon, jouer comme le dernier coup de l'adversaire
        if(joueurRemplace == 1) {
            return dernierTour.choixJoueur2; // Imiter le dernier coup de l'adversaire (joueur 2)
        } else {
            return dernierTour.choixJoueur1; // Imiter le dernier coup de l'adversaire (joueur 1)
        }
    }
}
