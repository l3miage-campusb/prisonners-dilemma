package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;

import java.util.ArrayList;

public class StrategyRancunierDoux implements IStrategy {

    private int phaseCastigo = 0; // Contador de la fase de castigo
    private int ultimaTraicionAdversario = -1; // Índice de la última traición válida del adversario fuera del castigo

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        int indexAdversaire = (joueurRemplace == 1) ? 2 : 1;

        // Solo buscar una nueva traición si no estamos en un ciclo de castigo
        if (phaseCastigo == 0) {
            for (int i = ultimaTraicionAdversario + 1; i < historique.size(); i++) {
                Choice choixAdversaire = (indexAdversaire == 1) ? historique.get(i).getChoixJoueur1() : historique.get(i).getChoixJoueur2();
                if (choixAdversaire == Choice.TRAHIR) {
                    ultimaTraicionAdversario = i;
                    phaseCastigo = 1; // Iniciar el ciclo de castigo tras detectar una traición
                    break;
                }
            }
        }

        // Aplicar el ciclo de castigo: cuatro traiciones seguidas de dos cooperaciones
        if (phaseCastigo > 0 && phaseCastigo <= 4) {
            phaseCastigo++;
            return Choice.TRAHIR;
        } else if (phaseCastigo > 4 && phaseCastigo <= 6) {
            phaseCastigo++;
            return Choice.COOPERER;
        } else if (phaseCastigo > 6) {
            // Reiniciar después del ciclo de castigo
            phaseCastigo = 0;
        }

        // Cooperar si no hay un ciclo de castigo activo
        return Choice.COOPERER;
    }
}
