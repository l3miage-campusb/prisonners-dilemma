package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import contract.CommonStrategy;
import contract.Game;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.util.ArrayList;

import static fr.uga.l3miage.pc.prisonersdilemma.domain.service.Util.convertChoiceContract;
import static fr.uga.l3miage.pc.prisonersdilemma.domain.service.Util.convertTurnsToTours;

public abstract class StrategieAbstract implements CommonStrategy {
    private long seed;


    @Override
    public contract.Choice makeChoice(Game game, int playerReplaced) {
        return convertChoiceContract(faireUnChoix(convertTurnsToTours(game.getTurns()),playerReplaced));
    }

    public abstract Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace);

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return seed;
    }
}
