package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategyPavlov extends StrategieAbstract{

    private final SecureRandom secureRandom = new SecureRandom();

    public Choice inverse(Choice choice){
        if(choice==Choice.COOPERER) return Choice.TRAHIR;
        return Choice.COOPERER;
    }


    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        if(historique.isEmpty()){
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        Tour dernierTour = historique.get(historique.size()-1);

        if(joueurRemplace==1){
            if(dernierTour.getChoixJoueur1()==Choice.TRAHIR && dernierTour.getChoixJoueur2()==Choice.COOPERER){   //J1 obtient 5 points
                return Choice.TRAHIR;
            }
            if(dernierTour.getChoixJoueur1()==Choice.COOPERER && dernierTour.getChoixJoueur2()==Choice.COOPERER){
                return Choice.COOPERER;
            }else{
                return inverse(dernierTour.getChoixJoueur1());
            }
        }

        else{
            if(dernierTour.getChoixJoueur2()==Choice.TRAHIR && dernierTour.getChoixJoueur1()==Choice.COOPERER){
                return Choice.TRAHIR;
            }
            if(dernierTour.getChoixJoueur1()==Choice.COOPERER && dernierTour.getChoixJoueur2()==Choice.COOPERER){
                return Choice.COOPERER;
            }else{
                return inverse(dernierTour.getChoixJoueur2());
            }
        }
    }


}
