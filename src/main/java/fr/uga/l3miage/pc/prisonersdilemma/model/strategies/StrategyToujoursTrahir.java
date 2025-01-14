package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;

import java.util.List;

public class StrategyToujoursTrahir extends StrategieAbstract{

    @Override
    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace) {
        return Choice.TRAHIR;
    }

}
