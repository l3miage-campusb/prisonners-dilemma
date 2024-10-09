package fr.uga.l3miage.pc;
import java.util.Random;
import java.util.ArrayList;

public class StrategieAleatoire implements  Strategie{
    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        Random random = new Random();
        return random.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
    }
}
