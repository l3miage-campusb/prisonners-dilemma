package fr.uga.l3miage.pc.prisonersdilemma.domain.service;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.*;

public interface IGameService {


    public void redemarrerService();
    public ResultMessage processChoice(ChoiceMessage message) ;
    public ResultMessage getResults(Choice choix1, Choice choix2) ;

    public ResultMessage handleLeave(LeaveMessage message);

    public void setChoixJ1(Choice choixJ1) ;

    public void setChoixJ2(Choice choixJ2);

    public void setScorej1(int scorej1);

    public void setScoreJ2(int scoreJ2) ;

    public int getNbJoueurController() ;

    public void setNbJoueurController(int nbJoueurController);


}