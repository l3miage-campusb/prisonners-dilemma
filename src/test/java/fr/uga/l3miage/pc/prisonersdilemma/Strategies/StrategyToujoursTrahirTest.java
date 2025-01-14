package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.StrategyToujoursTrahir;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
class StrategyToujoursTrahirTest {


    @Test
    void testFaireUnChoixConHistorialVacio() {
        // Prueba con historial vacío
        StrategyToujoursTrahir strat = new StrategyToujoursTrahir();
        ArrayList<Tour> historique = new ArrayList<>();
        Choice choix = strat.faireUnChoix(historique, 1);

        // Validaciones
        assertNotNull(choix, "El resultado no debe ser null");
        assertEquals(Choice.TRAHIR, choix, "La estrategia debe devolver siempre TRAHIR");
    }

    @Test
    void testFaireUnChoixConHistorialConValores() {
        // Prueba con historial lleno
        StrategyToujoursTrahir strat = new StrategyToujoursTrahir();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        Choice choix = strat.faireUnChoix(historique, 1);

        // Validaciones
        assertNotNull(choix, "El resultado no debe ser null");
        assertEquals(Choice.TRAHIR, choix, "La estrategia debe devolver siempre TRAHIR");
    }
}
