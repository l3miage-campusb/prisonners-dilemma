package fr.uga.l3miage.pc;

import java.util.ArrayList;

public class StrategieDonnantDonnant implements Strategie{
    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        Tour dernierTour = historique.get(historique.size()-1);

        if(joueurRemplace==1){
            return dernierTour.choixJoueur2;
        }

        if(joueurRemplace==2){
            return dernierTour.choixJoueur1;
        }

        return null;  //garanti qu'on passe pas par la car c'est le serveur qui envoit soit
        //1 soit 2
    }
}
