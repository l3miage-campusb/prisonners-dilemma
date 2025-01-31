package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.util.List;

public class StrategyDonnantPourDeuxDonnants extends StrategieAbstract{

    public boolean coupsEgaux(int joueur, Tour tour1, Tour tour2){
        if(joueur==1){
            return tour1.getChoixJoueur1()==tour2.getChoixJoueur1();
        }
        else{

            return tour1.getChoixJoueur2()==tour2.getChoixJoueur2();
        }
    }


    public Choice inverse(Choice choice){
        if(choice==Choice.COOPERER) return Choice.TRAHIR;
        return Choice.COOPERER;
    }


    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {

        if(historique.size() < 2){
            return Choice.COOPERER;
        }


        Tour avantDernierTour = historique.get(historique.size()-2);
        Tour dernierTour = historique.get(historique.size()-1);

        boolean coupsEgaux;

        if(joueurRemplace==1){
            coupsEgaux=coupsEgaux(2,avantDernierTour,dernierTour);
            return coupsEgaux ?  dernierTour.getChoixJoueur2() : inverse(dernierTour.getChoixJoueur2());     //On suppose que on doit envoyer le meme EXCLUSIVEMENT s'il y a deux coups pareils d'affilee
        }

        else{
            coupsEgaux=coupsEgaux(1,avantDernierTour,dernierTour);
            return coupsEgaux ? dernierTour.getChoixJoueur1() : inverse(dernierTour.getChoixJoueur1());
        }

    }
}
