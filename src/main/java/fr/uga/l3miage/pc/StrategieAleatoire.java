package fr.uga.l3miage.pc;
import java.security.SecureRandom;
import java.util.Random;
import java.util.ArrayList;

public class StrategieAleatoire implements  Strategie{
    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        SecureRandom random = new SecureRandom();
        return random.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
    }
}
