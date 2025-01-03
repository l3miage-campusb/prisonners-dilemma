package fr.uga.l3miage.pc.prisonersdilemma.service;

import contract.Turn;
import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class Util {

    public static ArrayList<Tour> convertTurnsToTours(Turn[] turns) {
        ArrayList<Tour> tours = new ArrayList<>();
        for (Turn turn : turns) {
            Choice choixJoueur1 = convertChoice(turn.getPlayerOneChoice());
            Choice choixJoueur2 = convertChoice(turn.getPlayerTwoChoice());
            Tour tour = new Tour(choixJoueur1, choixJoueur2);
            tours.add(tour);
        }
        return tours;
    }

    public static Turn[] convertToursToTurns(ArrayList<Tour> tours) {
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
