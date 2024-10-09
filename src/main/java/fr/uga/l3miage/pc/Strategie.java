package fr.uga.l3miage.pc;

import java.util.ArrayList;

public class Strategie {

    public Strategie(){

    }

    public Choice faireUnChoix(ArrayList<Tour> historique){
        return Choice.COOPERER;
    }

}
