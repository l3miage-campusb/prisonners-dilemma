package fr.uga.l3miage.pc;

import java.util.ArrayList;

public class StrategieRancunier implements Strategie{

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        //Si on a pas d'historique de tou,r on revoit le choix de cooperer par defaut
        if(historique.size() == 0){
            return Choice.COOPERER;
        }

        Tour dernierTour = historique.get(historique.size()-1);

        if(joueurRemplace == 1 && dernierTour.choixJoueur2 == Choice.TRAHIR) {
            return Choice.TRAHIR;
        }

        if(joueurRemplace == 2 && dernierTour.choixJoueur1 == Choice.TRAHIR){
            return Choice.TRAHIR;
        }

        return Choice.COOPERER;

    }



}
