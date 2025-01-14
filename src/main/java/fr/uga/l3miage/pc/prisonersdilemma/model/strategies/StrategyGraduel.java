package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;

import java.util.List;

public class StrategyGraduel extends StrategieAbstract {

    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {

        int trahisonsAdversaire = 0;
        int trahisonsJoueur = 0;

        for (Tour tour : historique) {
            Choice choixAdversaire = (joueurRemplace == 1) ? tour.getChoixJoueur2() : tour.getChoixJoueur1();
            if (choixAdversaire == Choice.TRAHIR) {
                trahisonsAdversaire++;
            }
            if ((joueurRemplace == 1 && tour.getChoixJoueur1() == Choice.TRAHIR) || (joueurRemplace == 2 && tour.getChoixJoueur2() == Choice.TRAHIR)) {
                trahisonsJoueur++;
            }
        }

        if (trahisonsAdversaire > 0) {
            if (trahisonsJoueur < trahisonsAdversaire) {
                return Choice.TRAHIR; // Trahir autant de fois que l'adversaire
            } else {
                return Choice.COOPERER; // Après avoir trahi, on revient à deux coopérations
            }
        }

        return Choice.COOPERER; // Coopérer jusqu'à ce que l'adversaire trahisse
    }
}
