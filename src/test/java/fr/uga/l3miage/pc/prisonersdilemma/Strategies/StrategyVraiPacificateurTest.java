package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyVraiPacificateur;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

class StrategyVraiPacificateurTest {

    @Test
    void testFaireUnChoixConHistorialMenorDeDos() {
        // Prueba cuando el historial tiene menos de dos jugadas
        StrategyVraiPacificateur strat = new StrategyVraiPacificateur();
        ArrayList<Tour> historique = new ArrayList<>();
        Choice choix = strat.faireUnChoix(historique, 1);

        // Validación: La estrategia debe cooperar
        assertEquals(Choice.COOPERER, choix, "Con historial vacío, la estrategia debe cooperar");

        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        choix = strat.faireUnChoix(historique, 1);

        // Validación: La estrategia debe cooperar
        assertEquals(Choice.COOPERER, choix, "Conn solo un turno en el historial, la estrategia debe cooperar");
    }

    @Test
    void testFaireUnChoixConTraicionesConsecutivasParaJugador1() {
        // Prueba cuando el jugador 2 traicionó en los dos últimos turnos consecutivos y el jugador reemplazado es el 1
        StrategyVraiPacificateur strat = new StrategyVraiPacificateur();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));

        // Ejecutar el método varias veces para capturar la aleatoriedad
        int cooperations = 0;
        int traiciones = 0;

        for (int i = 0; i < 100; i++) {
            Choice choix = strat.faireUnChoix(historique, 1);
            if (choix == Choice.COOPERER) cooperations++;
            else traiciones++;
        }

        // Validación: El resultado debe incluir tanto cooperaciones como traiciones
        assertTrue(cooperations > 0, "Debe haber algunas cooperaciones debido a la probabilidad aleatoria");
        assertTrue(traiciones > 0, "Debe haber algunas traiciones debido a la probabilidad aleatoria");
    }

    @Test
    void testFaireUnChoixSinTraicionesConsecutivasParaJugador1() {
        // Prueba cuando el jugador 2 no traicionó en ambos turnos anteriores
        StrategyVraiPacificateur strat = new StrategyVraiPacificateur();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));

        Choice choix = strat.faireUnChoix(historique, 1);

        // Validación: La estrategia debe cooperar
        assertEquals(Choice.COOPERER, choix, "Cuando no hay traiciones consecutivas, la estrategia debe cooperar");
    }

    @Test
    void testFaireUnChoixConTraicionesConsecutivasParaJugador2() {
        // Prueba cuando el jugador 1 traicionó en los dos últimos turnos consecutivos y el jugador reemplazado es el 2
        StrategyVraiPacificateur strat = new StrategyVraiPacificateur();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        // Ejecutar el método varias veces para capturar la aleatoriedad
        int cooperations = 0;
        int traiciones = 0;

        for (int i = 0; i < 100; i++) {
            Choice choix = strat.faireUnChoix(historique, 2);
            if (choix == Choice.COOPERER) cooperations++;
            else traiciones++;
        }

        // Validación: El resultado debe incluir tanto cooperaciones como traiciones
        assertTrue(cooperations > 0, "Debe haber algunas cooperaciones debido a la probabilidad aleatoria");
        assertTrue(traiciones > 0, "Debe haber algunas traiciones debido a la probabilidad aleatoria");
    }
}
