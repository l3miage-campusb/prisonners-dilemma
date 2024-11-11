package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;
import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.security.SecureRandom;
import java.util.ArrayList;

public class StrategyAleatoire implements IStrategy {
    @Override
    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace) {
        SecureRandom random = new SecureRandom();
        return random.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
    }
}
