package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import contract.CommonStrategy;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.StrategyToujoursCooperer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

class StrategyToujoursCoopererTest {

    @Test
    void testFaireUnChoixToujoursCoopererConHistorialVacio() {
        // Arrange
        StrategyToujoursCooperer strat = new StrategyToujoursCooperer();
        ArrayList<Tour> historique = new ArrayList<>(); // historial vacío
        int joueurRemplace = 1; // índice del jugador, no afecta el resultado

        // Act
        Choice choix = strat.faireUnChoix(historique, joueurRemplace);

        // Assert
        assertEquals(Choice.COOPERER, choix, "La estrategia debe devolver siempre COOPERER cuando el historial está vacío");
        assertNotNull(choix, "El resultado no debe ser null");
        assertFalse(historique.contains(choix), "El historial no debería afectar el resultado de la elección");
        assertTrue(strat instanceof CommonStrategy, "La estrategia debería ser una instancia de IStrategy");
    }

    @Test
    void testFaireUnChoixToujoursCoopererConHistorialLleno() {
        // Arrange
        StrategyToujoursCooperer strat = new StrategyToujoursCooperer();
        ArrayList<Tour> historique = new ArrayList<>();

        // Agregar algunos datos al historial
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        int joueurRemplace = 1;

        // Act
        Choice choix = strat.faireUnChoix(historique, joueurRemplace);

        // Assert
        assertEquals(Choice.COOPERER, choix, "La estrategia debe devolver siempre COOPERER incluso cuando el historial tiene jugadas");
        assertNotNull(choix, "El resultado no debe ser null");
        assertFalse(historique.contains(choix), "El historial no debería afectar el resultado de la elección");
    }
}
