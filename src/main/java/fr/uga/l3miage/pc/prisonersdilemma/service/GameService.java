package fr.uga.l3miage.pc.prisonersdilemma.service;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.LeaveMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.ResultMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService {

    private static GameService instance;

    Choice choixJ1 = null;
    Choice choixJ2 = null;

    IStrategy strategie ;
    int leftPlayerId = -1;

    ArrayList<Tour> historique = new ArrayList<>();

    int scorej1 = 0;
    int scoreJ2 = 0;


    public static GameService getInstance() {
        if(instance == null) {
            instance = new GameService();
        }
        return instance;
    }

    public ResultMessage processChoice(ChoiceMessage message) {

        // Logique pour traiter les choix des joueurs


        if(this.leftPlayerId != -1){
            return getResults(message.getChoice(), strategie.faireUnChoix(historique,leftPlayerId));
        }

        if (message.getPlayerId().equals("1")){
            choixJ1 = message.getChoice();
        }
        else if (message.getPlayerId().equals("2")){
            choixJ2 = message.getChoice();
        }

        if(choixJ1 != null && choixJ2 != null){
            Choice choixJ1tmp = choixJ1;
            Choice choixJ2tmp = choixJ2;

            //On reset les choix en prévision  du prochains round
            choixJ1 = null;
            choixJ2 = null;
            return getResults(choixJ1tmp,choixJ2tmp);
        }
        return null;
    }

    public ResultMessage getResults(Choice choix1, Choice choix2){
        ResultMessage results = new ResultMessage();

        if(choix1 == Choice.TRAHIR && choix2 == Choice.TRAHIR){
            scorej1+= 1;
            scoreJ2 += 1;
            results.setReponseJ1("Vous avez tous les deux trahis l'adversaire");
            results.setReponseJ2("Vous avez tous les deux trahis l'adversaire");
        }
        else if (choix1 == Choice.TRAHIR && choix2 == Choice.COOPERER) {
            scorej1 += 5;
            results.setReponseJ1("Vous avez réussi à trahir l'adversaire");
            results.setReponseJ2("Vous avez été trahis par l'adversaire");
        }
        else if (choix1 == Choice.COOPERER && choix2 == Choice.TRAHIR) {
            scoreJ2+=5;
            results.setReponseJ1("Vous avez été trahis par l'adversaire");
            results.setReponseJ2("Vous avez réussi à trahir l'adversaire");
        }
        else if (choix1 == Choice.COOPERER && choix2 == Choice.COOPERER) {
            scorej1+=3;
            scoreJ2+=3;
            results.setReponseJ1("Vous avez tous les deux coopérer");
            results.setReponseJ2("Vous avez tous les deux coopérer");
        }
        results.setScoreJ1(this.scorej1);
        results.setScoreJ2(this.scoreJ2);

        historique.add(new Tour(choix1,choix2));

        return results;

    }

    public ResultMessage handleLeave(LeaveMessage message){

        this.strategie = StrategyFactory.createStrategy(message.getStrategy());

        this.leftPlayerId = message.getPlayerId();

        if(this.leftPlayerId == 1 && this.choixJ2 != null){
            return getResults(choixJ2, strategie.faireUnChoix(historique,leftPlayerId));
        }
        else if(this.leftPlayerId == 2 && this.choixJ1 != null){
            return getResults(choixJ1, strategie.faireUnChoix(historique,leftPlayerId));
        }
        return null;

    }


    public void setChoixJ1(Choice choixJ1) {
        this.choixJ1 = choixJ1;
    }

    public void setChoixJ2(Choice choixJ2) {
        this.choixJ2 = choixJ2;
    }

    public void setScorej1(int scorej1) {
        this.scorej1 = scorej1;
    }

    public void setScoreJ2(int scoreJ2) {
        this.scoreJ2 = scoreJ2;
    }

}