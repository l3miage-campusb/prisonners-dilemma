package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.util.ArrayList;

public class StrategyDonnantDonnantSupconneux extends StrategieAbstract{

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
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
