package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;

import java.util.List;

public class StrategyDonnantDonnantSupconneux extends StrategieAbstract{

    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {
        //Si on a pas d'historique de tou,r on revoit le choix de cooperer par defaut
        if(historique.isEmpty()){
            return Choice.TRAHIR;
        }

        Tour dernierTour = historique.get(historique.size()-1);
        if(joueurRemplace==1){
            return dernierTour.getChoixJoueur2();
        }

        else{
            return dernierTour.getChoixJoueur1();
        }
    }
}
