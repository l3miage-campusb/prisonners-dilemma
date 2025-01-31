package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.security.SecureRandom;
import java.util.List;

public class StrategyAleatoire extends StrategieAbstract {
    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {
        SecureRandom random = new SecureRandom();
        random.setSeed(this.getSeed());
        return random.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
    }
}
