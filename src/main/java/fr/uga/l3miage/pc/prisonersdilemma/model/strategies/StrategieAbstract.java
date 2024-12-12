package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import contract.CommonStrategy;
import contract.Game;
import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;

import java.util.ArrayList;

import static fr.uga.l3miage.pc.prisonersdilemma.service.Util.convertChoiceContract;
import static fr.uga.l3miage.pc.prisonersdilemma.service.Util.convertTurnsToTours;

public abstract class StrategieAbstract implements CommonStrategy {

    @Override
    public contract.Choice makeChoice(Game game, int playerReplaced) {
        return convertChoiceContract(faireUnChoix(convertTurnsToTours(game.getTurns()),playerReplaced));
    }

    public abstract Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace);
}
