package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;

import java.util.ArrayList;

public class StrategyRancunier implements  IStrategy{

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        //Si on a pas d'historique de tou,r on revoit le choix de cooperer par defaut
        if(historique.isEmpty()){
            return Choice.COOPERER;
        }

        Tour dernierTour = historique.get(historique.size()-1);

        if(joueurRemplace == 1 && dernierTour.getChoixJoueur2() == Choice.TRAHIR) {
            return Choice.TRAHIR;
        }

        if(joueurRemplace == 2 && dernierTour.getChoixJoueur1() == Choice.TRAHIR){
            return Choice.TRAHIR;
        }

        return Choice.COOPERER;

    }
}
