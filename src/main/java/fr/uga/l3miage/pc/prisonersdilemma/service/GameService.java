package fr.uga.l3miage.pc.prisonersdilemma.service;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.ResultMessage;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    Choice choixJ1 = null;
    Choice choixJ2 = null;

    int scorej1 = 0;
    int scoreJ2 = 0;


    public ResultMessage processChoice(ChoiceMessage message) {
        // Logique pour traiter les choix des joueurs
        // Initialiser gameState selon les choix

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
        return results;

    }

}