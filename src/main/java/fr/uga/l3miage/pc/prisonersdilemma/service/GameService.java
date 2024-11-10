package fr.uga.l3miage.pc.prisonersdilemma.service;

import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.ResultMessage;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    String choixJ1 = "";
    String choixJ2 = "";

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

        if(!choixJ1.isEmpty() && !choixJ2.isEmpty()){
            return getResults(choixJ1,choixJ2);
        }
        return null;
    }

    public ResultMessage getResults(String choix1, String choix2){
        ResultMessage results = new ResultMessage();

        if(choix1.equals("trahir") && choix2.equals("trahir")){
            scorej1+= 1;
            scoreJ2 += 1;
            results.setReponseJ1("Vous avez tous les deux trahis l'adversaire");
            results.setReponseJ2("Vous avez tous les deux trahis l'adversaire");
        }
        else if (choix1.equals("trahir") && choix2.equals("cooperer")) {
            scorej1 += 5;
            results.setReponseJ1("Vous avez réussi à trahir l'adversaire");
            results.setReponseJ2("Vous avez été trahis par l'adversaire");
        }
        else if (choix1.equals("cooperer") && choix2.equals("trahir")) {
            scoreJ2+=5;
            results.setReponseJ1("Vous avez été trahis par l'adversaire");
            results.setReponseJ2("Vous avez réussi à trahir l'adversaire");
        }
        else if (choix1.equals("cooperer") && choix2.equals("cooperer")) {
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