package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;

import java.util.ArrayList;

public interface IStrategy {

    public Choice faireUnChoix(ArrayList<Tour> historique, int joueurRemplace);
}
