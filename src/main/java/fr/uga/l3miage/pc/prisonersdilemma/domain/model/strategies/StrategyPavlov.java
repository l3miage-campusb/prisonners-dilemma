package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.util.List;

public class StrategyPavlov extends AbstractStrategyPavlov {

    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {
        return super.faireUnChoix(historique, joueurRemplace, DEFAULT_PROBABILITEALEATOIRE);
    }
}