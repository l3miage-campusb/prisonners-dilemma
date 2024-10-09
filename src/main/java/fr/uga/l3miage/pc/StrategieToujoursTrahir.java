package fr.uga.l3miage.pc;
import java.util.ArrayList;

public class StrategieToujoursTrahir implements Strategie{
    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        return Choice.TRAHIR;
    }


}
