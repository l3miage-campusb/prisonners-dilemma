package fr.uga.l3miage.pc;

import java.util.ArrayList;

public interface  Strategie {

    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace);

}
