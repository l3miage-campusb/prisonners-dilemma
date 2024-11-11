package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.util.ArrayList;

public class StrategyAdaptatif implements IStrategy{

    int[] premiersCoups = {2,2,2,2,2,2,1,1,1,1,1};
    ArrayList<Double> moyennes = new ArrayList<>();
    int maxMoyenne=0;
    int positionBestMoyenne;

    public Choice traduire(int i){
        if(i==1) return Choice.TRAHIR;
        return Choice.COOPERER;
    }

    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {


        System.out.println("taille ARRay : " + historique.size());


        if(historique.isEmpty()){
            return traduire(premiersCoups[0]);
        }
        return null;
    }



    public static void main(String[] args){
        StrategyAdaptatif s = new StrategyAdaptatif();
        Tour tour=new Tour(Choice.TRAHIR,Choice.COOPERER);
        ArrayList<Tour> at = new ArrayList<>();
        at.add(tour);
        s.faireUnChoix(at,1);
        System.out.println();
    }

}



