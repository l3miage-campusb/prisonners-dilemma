package fr.uga.l3miage.pc.prisonersdilemma;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.ChoiceMessage;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.LeaveMessage;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.ResultMessage;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.Strategy;
import fr.uga.l3miage.pc.prisonersdilemma.domain.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    void testRedemarrerService() {
        // Ejecutar el método redemarrerService()
        gameService.redemarrerService();

        // Verificar que todas las variables se han reiniciado correctamente
        assertNull(gameService.getChoixJ1(), "choixJ1 no se reinició correctamente");
        assertNull(gameService.getChoixJ2(), "choixJ2 no se reinició correctamente");
        assertNull(gameService.getStrategy(), "La estrategia no se reinició correctamente");
        assertEquals(-1, gameService.getLeftPlayerId(), "leftPlayerId no se reinició correctamente");
        assertEquals(0, gameService.getNbJoueurController(), "nbJoueurController no se reinició correctamente");
        assertEquals(0, gameService.getScorej1(), "scorej1 no se reinició correctamente");
        assertEquals(0, gameService.getScorej2(), "scoreJ2 no se reinició correctamente");
        assertEquals(new ArrayList<>(), gameService.getHistorique(), "El historial no se reinició correctamente");
    }

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

    @Test
    void testProcessChoice_OneChoiceOnly() {
        // Arrange
        ChoiceMessage choiceMessage = new ChoiceMessage("1", Choice.COOPERER);
        gameService.setChoixJ2(null); // Aucun choix pour joueur 2

        // Act
        ResultMessage result = gameService.processChoice(choiceMessage);

        // Assert
        assertNull(result, "Le résultat devrait être nul tant que les deux choix ne sont pas reçus");
    }

    @Test
    void testProcessChoice_BothChoicesReceived() {
        // Arrange
        ChoiceMessage choiceMessageJ1 = new ChoiceMessage("1", Choice.COOPERER);
        ChoiceMessage choiceMessageJ2 = new ChoiceMessage("2", Choice.TRAHIR);
        gameService.processChoice(choiceMessageJ1);

        // Act
        ResultMessage result = gameService.processChoice(choiceMessageJ2);

        // Assert
        assertNotNull(result, "Le résultat ne devrait pas être nul une fois les deux choix reçus");
        assertEquals(0, result.getScoreJ1(), "Score du joueur 1 devrait être 0");
        assertEquals(5, result.getScoreJ2(), "Score du joueur 2 devrait être 5");
    }

    @Test
    void testHandleLeave_Player1Leaves_Player2HasMadeChoice() {
        // Arrange
        gameService.setChoixJ2(Choice.TRAHIR);  // Joueur 2 a choisi de trahir
        LeaveMessage leaveMessage = new LeaveMessage(Strategy.TOUJOURS_COOPERER, 1);  // Joueur 1 quitte avec une stratégie

        // Act
        ResultMessage result = gameService.handleLeave(leaveMessage);

        // Assert
        assertNotNull(result, "Un résultat devrait être généré après le départ du joueur 1");
        assertEquals("Vous avez été trahis par l'adversaire", result.getReponseJ1());
        assertEquals("Vous avez réussi à trahir l'adversaire", result.getReponseJ2());
        assertEquals(0, result.getScoreJ1());
        assertEquals(5, result.getScoreJ2());
    }

    @Test
    void testHandleLeave_Player2Leaves_Player1HasMadeChoice() {
        // Arrange
        gameService.setChoixJ1(Choice.COOPERER);  // Joueur 1 a choisi de coopérer
        LeaveMessage leaveMessage = new LeaveMessage(Strategy.TOUJOURS_TRAHIR, 2);  // Joueur 2 quitte avec une stratégie

        // Act
        ResultMessage result = gameService.handleLeave(leaveMessage);

        // Assert
        assertNotNull(result, "Un résultat devrait être généré après le départ du joueur 2");
        assertEquals("Vous avez été trahis par l'adversaire", result.getReponseJ1());
        assertEquals("Vous avez réussi à trahir l'adversaire", result.getReponseJ2());
        assertEquals(0, result.getScoreJ1());
        assertEquals(5, result.getScoreJ2());
    }

    @Test
    void testHandleLeave_Player1Leaves_NoChoiceFromPlayer2() {
        // Arrange
        gameService.setChoixJ2(null);  // Joueur 2 n'a pas fait de choix
        LeaveMessage leaveMessage = new LeaveMessage(Strategy.TOUJOURS_COOPERER, 1);  // Joueur 1 quitte

        // Act
        ResultMessage result = gameService.handleLeave(leaveMessage);

        // Assert
        assertNull(result, "Le résultat devrait être nul si le joueur restant n'a pas fait de choix.");
    }

    @Test
    void testHandleLeave_Player2Leaves_NoChoiceFromPlayer1() {
        // Arrange
        gameService.setChoixJ1(null);  // Joueur 1 n'a pas fait de choix
        LeaveMessage leaveMessage = new LeaveMessage(Strategy.TOUJOURS_TRAHIR, 2);  // Joueur 2 quitte

        // Act
        ResultMessage result = gameService.handleLeave(leaveMessage);

        // Assert
        assertNull(result, "Le résultat devrait être nul si le joueur restant n'a pas fait de choix.");
    }




}
