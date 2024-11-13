package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategyPavlovAleatoire implements IStrategy{

    private final SecureRandom secureRandom = new SecureRandom();
    private final double probabiliteAleatoire = 0.2; //

    public Choice inverse(Choice choice){
        if(choice==Choice.COOPERER) return Choice.TRAHIR;
        return Choice.COOPERER;
    }


    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {

        if(historique.isEmpty()){
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        if (secureRandom.nextDouble() < probabiliteAleatoire) {   //si prob in 20% aleatoire
            return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
        }

        Tour dernierTour = historique.get(historique.size()-1);

        if(joueurRemplace==1){
            if(dernierTour.choixJoueur1==Choice.TRAHIR && dernierTour.choixJoueur2==Choice.COOPERER){   //J1 obtient 5 points
                return Choice.TRAHIR;
            }
            if(dernierTour.choixJoueur1==Choice.COOPERER && dernierTour.choixJoueur2==Choice.COOPERER){
                return Choice.COOPERER;
            }else{
                return inverse(dernierTour.choixJoueur1);
            }
        }

        if(joueurRemplace==2){
            if(dernierTour.choixJoueur2==Choice.TRAHIR && dernierTour.choixJoueur1==Choice.COOPERER){
                return Choice.TRAHIR;
            }
            if(dernierTour.choixJoueur1==Choice.COOPERER && dernierTour.choixJoueur2==Choice.COOPERER){
                return Choice.COOPERER;
            }else{
                return inverse(dernierTour.choixJoueur2);
            }
        }

        return null;
    }


}
