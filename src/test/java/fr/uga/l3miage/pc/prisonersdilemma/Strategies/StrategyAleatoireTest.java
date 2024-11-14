package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyAleatoire;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StrategyAleatoireTest {

    @Test
    void testFaireUnChoixConHistorialVacio() {
        // Prueba con historial vacío
        StrategyAleatoire strat = new StrategyAleatoire();
        ArrayList<Tour> historique = new ArrayList<>();
        Choice choix = strat.faireUnChoix(historique, 1);

        // Validación: El resultado debe ser COOPERER o TRAHIR
        assertTrue(choix == Choice.COOPERER || choix == Choice.TRAHIR,
                "El resultado debe ser COOPERER o TRAHIR");
    }

    @Test
    void testFaireUnChoixAleatorioDistribucion() {
        // Prueba para verificar que tanto COOPERER como TRAHIR son posibles
        StrategyAleatoire strat = new StrategyAleatoire();
        ArrayList<Tour> historique = new ArrayList<>();
        Set<Choice> resultados = new HashSet<>();

        // Ejecutar el método varias veces para observar diferentes resultados
        for (int i = 0; i < 100; i++) {
            resultados.add(strat.faireUnChoix(historique, 1));
            if (resultados.contains(Choice.COOPERER) && resultados.contains(Choice.TRAHIR)) {
                break;
            }
        }

        // Validación: Ambos resultados deben aparecer en múltiples ejecuciones
        assertTrue(resultados.contains(Choice.COOPERER), "La estrategia debería a veces cooperar");
        assertTrue(resultados.contains(Choice.TRAHIR), "La estrategia debería a veces traicionar");
    }
}
