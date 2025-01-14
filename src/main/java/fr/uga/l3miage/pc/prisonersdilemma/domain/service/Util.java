package fr.uga.l3miage.pc.prisonersdilemma.domain.service;

import contract.Turn;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Util {
    private Util() {}

    public static List<Tour> convertTurnsToTours(Turn[] turns) {
        ArrayList<Tour> tours = new ArrayList<>();
        for (Turn turn : turns) {
            Choice choixJoueur1 = convertChoice(turn.getPlayerOneChoice());
            Choice choixJoueur2 = convertChoice(turn.getPlayerTwoChoice());
            Tour tour = new Tour(choixJoueur1, choixJoueur2);
            tours.add(tour);
        }
        return tours;
    }

    public static Turn[] convertToursToTurns(List<Tour> tours) {
        Turn[] turns = new Turn[tours.size()];
        for (int i = 0; i < tours.size(); i++) {
            Tour tour = tours.get(i);
            contract.Choice choicePlayer1 = convertChoiceContract(tour.getChoixJoueur1());
            contract.Choice choicePlayer2 = convertChoiceContract(tour.getChoixJoueur2());
            turns[i] = new Turn(choicePlayer1, choicePlayer2);
        }
        return turns;
    }

    public static Choice convertChoice(contract.Choice contractChoice) {
        // Assurez-vous que vos enums "Choice" dans `contract` et `fr.uga.l3miage.pc.prisonersdilemma.model`
        // ont les mêmes valeurs ou faites une conversion explicite si elles diffèrent.
        if(contractChoice == contract.Choice.COOPERATE){
            return Choice.COOPERER;
        }
        else return Choice.TRAHIR;
    }

    public static contract.Choice convertChoiceContract(Choice choice) {
        // Assurez-vous que vos enums "Choice" dans `contract` et `fr.uga.l3miage.pc.prisonersdilemma.model`
        // ont les mêmes valeurs ou faites une conversion explicite si elles diffèrent.
        if(choice == Choice.COOPERER){
            return contract.Choice.COOPERATE;
        }
        else return contract.Choice.BETRAY;
    }
}
