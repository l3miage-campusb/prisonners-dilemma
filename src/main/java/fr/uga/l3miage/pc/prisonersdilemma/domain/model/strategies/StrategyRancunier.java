package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.util.List;

public class StrategyRancunier extends StrategieAbstract{

    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {
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
