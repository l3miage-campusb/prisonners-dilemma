package fr.uga.l3miage.pc.prisonersdilemma;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.controller.GameController;
import fr.uga.l3miage.pc.prisonersdilemma.model.ResultMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.IStrategy;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyAleatoire;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyToujoursCooperer;
import fr.uga.l3miage.pc.prisonersdilemma.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GameServiceTest {

    private void resetGameService(GameService gameservice){
        gameService.setChoixJ1(null);
        gameService.setChoixJ2(null);
        gameService.setScorej1(0);
        gameservice.setScoreJ2(0);

    }

    @BeforeEach
    void setUp() {
        this.resetGameService(gameService);
    }

    GameService gameService = new GameService();
    @Test
    void testProcessChoiceTrahirTrahir() {
        // Arrange
        Choice choix1 = Choice.TRAHIR;
        Choice choix2 = Choice.TRAHIR;
        ResultMessage resultexpected = new ResultMessage("Vous avez tous les deux trahis l'adversaire","Vous avez tous les deux trahis l'adversaire",1,1);
        //Act

        ResultMessage result = gameService.getResults(choix1,choix2);

        // Assert
        assertEquals(result.getReponseJ1(),resultexpected.getReponseJ1());
        assertEquals(result.getReponseJ2(),resultexpected.getReponseJ2());
        assertEquals(result.getScoreJ1(),resultexpected.getScoreJ1());
        assertEquals(result.getScoreJ2(),resultexpected.getScoreJ2());


    }
    @Test
    void testProcessChoiceTrahirCooperer() {
        // Arrange
        Choice choix1 = Choice.TRAHIR;
        Choice choix2 = Choice.COOPERER;
        ResultMessage resultexpected = new ResultMessage("Vous avez réussi à trahir l'adversaire","Vous avez été trahis par l'adversaire",5,0);
        //Act

        ResultMessage result = gameService.getResults(choix1,choix2);

        // Assert
        assertEquals(result.getReponseJ1(),resultexpected.getReponseJ1());
        assertEquals(result.getReponseJ2(),resultexpected.getReponseJ2());
        assertEquals(result.getScoreJ1(),resultexpected.getScoreJ1());
        assertEquals(result.getScoreJ2(),resultexpected.getScoreJ2());


    }
    @Test
    void testProcessChoiceCoopererTrahir() {
        // Arrange
        Choice choix1 = Choice.COOPERER;
        Choice choix2 = Choice.TRAHIR;
        ResultMessage resultexpected = new ResultMessage("Vous avez été trahis par l'adversaire","Vous avez réussi à trahir l'adversaire",0,5);
        //Act

        ResultMessage result = gameService.getResults(choix1,choix2);

        // Assert
        assertEquals(result.getReponseJ1(),resultexpected.getReponseJ1());
        assertEquals(result.getReponseJ2(),resultexpected.getReponseJ2());
        assertEquals(result.getScoreJ1(),resultexpected.getScoreJ1());
        assertEquals(result.getScoreJ2(),resultexpected.getScoreJ2());


    }
    @Test
    void testProcessChoiceCoopererCooperer() {
        // Arrange
        Choice choix1 = Choice.COOPERER;
        Choice choix2 = Choice.COOPERER;
        ResultMessage resultexpected = new ResultMessage("Vous avez tous les deux coopérer","Vous avez tous les deux coopérer",3,3);
        //Act

        ResultMessage result = gameService.getResults(choix1,choix2);

        // Assert
        assertEquals(result.getReponseJ1(),resultexpected.getReponseJ1());
        assertEquals(result.getReponseJ2(),resultexpected.getReponseJ2());
        assertEquals(result.getScoreJ1(),resultexpected.getScoreJ1());
        assertEquals(result.getScoreJ2(),resultexpected.getScoreJ2());


    }

    @Test
    void testGetResult() {
        // Arrange
        Choice choix1 = Choice.COOPERER;
        Choice choix2 = Choice.COOPERER;
        ResultMessage resultexpected = new ResultMessage("Vous avez tous les deux coopérer","Vous avez tous les deux coopérer",3,3);
        //Act

        ResultMessage result = gameService.getResults(choix1,choix2);

        // Assert
        assertEquals(result.getReponseJ1(),resultexpected.getReponseJ1());
        assertEquals(result.getReponseJ2(),resultexpected.getReponseJ2());
        assertEquals(result.getScoreJ1(),resultexpected.getScoreJ1());
        assertEquals(result.getScoreJ2(),resultexpected.getScoreJ2());


    }




}
