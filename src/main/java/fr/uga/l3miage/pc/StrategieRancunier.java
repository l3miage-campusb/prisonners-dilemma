package fr.uga.l3miage.pc;

import java.util.ArrayList;

public class StrategieRancunier implements Strategie{

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        Tour dernierTour = historique.get(historique.size()-1);

        if(joueurRemplace == 1 && dernierTour.choixJoueur2 == Choice.TRAHIR) {
            return Choice.TRAHIR;
        }

        if(joueurRemplace == 2 && dernierTour.choixJoueur1 == Choice.TRAHIR){
            return Choice.TRAHIR;
        }

        return Choice.COOPERER;

    }


//    @Override
//    public Choice faireUnChoix(ArrayList<Tour> historique, int numeroJoueur) {
//        Tour dernierTour = historique.get(historique.size()-1);
//
//        if(numeroJoueur == 1){  //Le Joueur numero 1 part
//            if(dernierTour.choixJoueur2 == Choice.TRAHIR){
//                return Choice.TRAHIR;
//            }else{
//                return Choice.COOPERER;
//            }
//        }else{  //Le Joueur numero 2 part
//            if(dernierTour.choixJoueur1 == Choice.TRAHIR){
//                return Choice.TRAHIR;
//            }else{
//                return Choice.COOPERER;
//            }
//        }
//
//    }
}
