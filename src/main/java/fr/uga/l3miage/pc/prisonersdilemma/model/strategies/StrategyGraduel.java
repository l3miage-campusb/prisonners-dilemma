package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.util.ArrayList;

public class StrategyGraduel implements IStrategy {

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        int trahisonsAdversaire = 0;
        int trahisonsJoueur = 0;

        for (Tour tour : historique) {
            Choice choixAdversaire = (joueurRemplace == 1) ? tour.choixJoueur2 : tour.choixJoueur1;
            if (choixAdversaire == Choice.TRAHIR) {
                trahisonsAdversaire++;
            }
            if ((joueurRemplace == 1 && tour.choixJoueur1 == Choice.TRAHIR) || (joueurRemplace == 2 && tour.choixJoueur2 == Choice.TRAHIR)) {
                trahisonsJoueur++;
            }
        }

        if (trahisonsAdversaire > 0) {
            if (trahisonsJoueur < trahisonsAdversaire) {
                return Choice.TRAHIR; // Trahir autant de fois que l'adversaire
            } else {
                return (trahisonsJoueur % 2 == 0) ? Choice.COOPERER : Choice.COOPERER; // Après avoir trahi, on revient à deux coopérations
            }
        }

        return Choice.COOPERER; // Coopérer jusqu'à ce que l'adversaire trahisse
    }
}
