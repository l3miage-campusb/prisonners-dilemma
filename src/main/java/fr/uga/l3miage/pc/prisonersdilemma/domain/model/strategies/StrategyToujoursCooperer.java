package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.util.List;

public class StrategyToujoursCooperer  extends StrategieAbstract{

    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {
        return Choice.COOPERER;
    }

}
