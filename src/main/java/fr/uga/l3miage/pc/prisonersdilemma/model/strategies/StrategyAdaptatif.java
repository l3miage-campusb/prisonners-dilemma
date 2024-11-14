package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.util.ArrayList;

public class StrategyAdaptatif implements IStrategy {

    private final int SEQUENCE_INITIALE_LENGTH = 11;
    private final Choice[] SEQUENCE_INITIALE = {
            Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER,
            Choice.COOPERER, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR
    };

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        if(historique.size() < SEQUENCE_INITIALE_LENGTH) {
            return SEQUENCE_INITIALE[historique.size()];
        }

        double scoreTotalCooperation = 0;
        double scoreTotalTrahison = 0;
        int countCooperation = 0;
        int countTrahison = 0;

        for (Tour tour : historique) {
            Choice choixAdversaire = (joueurRemplace == 1) ? tour.choixJoueur2 : tour.choixJoueur1;
            Choice choixEffectue = (joueurRemplace == 1) ? tour.choixJoueur1 : tour.choixJoueur2;

            if (choixEffectue == Choice.COOPERER) {
                scoreTotalCooperation += getScore(Choice.COOPERER, choixAdversaire);
                countCooperation++;
            } else {
                scoreTotalTrahison += getScore(Choice.TRAHIR, choixAdversaire);
                countTrahison++;
            }
        }

        double scoreMoyenCooperation = (countCooperation > 0) ? scoreTotalCooperation / countCooperation : 0;
        double scoreMoyenTrahison = (countTrahison > 0) ? scoreTotalTrahison / countTrahison : 0;

        return (scoreMoyenCooperation >= scoreMoyenTrahison) ? Choice.COOPERER : Choice.TRAHIR;
    }

    private int getScore(Choice choixJoueur, Choice choixAdversaire) {
        if (choixJoueur == Choice.COOPERER && choixAdversaire == Choice.COOPERER) {
            return 3;
        } else if (choixJoueur == Choice.TRAHIR && choixAdversaire == Choice.COOPERER) {
            return 5;
        } else if (choixJoueur == Choice.COOPERER && choixAdversaire == Choice.TRAHIR) {
            return 0;
        } else if (choixJoueur == Choice.TRAHIR && choixAdversaire == Choice.TRAHIR) {
            return 1;
        }
        return 0;
    }
}
