package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;



import java.util.ArrayList;

public class StrategyAdaptatif implements IStrategy {

    private static final int SEQUENCEINITIALELENGTH = 11;
    private final Choice[] sequenceInitiale = {
            Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER,
            Choice.COOPERER, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR
    };

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        if(historique.size() < SEQUENCEINITIALELENGTH) {
            return sequenceInitiale[historique.size()];
        }

        double scoreTotalCooperation = 0;
        double scoreTotalTrahison = 0;
        int countCooperation = 0;
        int countTrahison = 0;

        for (Tour tour : historique) {
            Choice choixAdversaire = (joueurRemplace == 1) ? tour.getChoixJoueur2() : tour.getChoixJoueur1();
            Choice choixEffectue = (joueurRemplace == 1) ? tour.getChoixJoueur1() : tour.getChoixJoueur2();

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

        System.out.println("scoreMoyenCooperation : " + scoreMoyenCooperation);
        System.out.println("scoreMoyenTrahison : " + scoreMoyenTrahison);
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
