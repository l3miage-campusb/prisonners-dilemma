package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategySondeurNaif;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrategySondeurNaifTest {

    @Test
    void testFaireUnChoixHistorialVacio() {
        // Prueba para el caso en que el historial está vacío
        StrategySondeurNaif strat = new StrategySondeurNaif();
        ArrayList<Tour> historique = new ArrayList<>();

        Set<Choice> resultadosPosibles = EnumSet.of(Choice.COOPERER, Choice.TRAHIR);

        // Ejecutamos el método varias veces para capturar la aleatoriedad
        for (int i = 0; i < 100; i++) {
            Choice choix = strat.faireUnChoix(historique, 1);
            assertTrue(resultadosPosibles.contains(choix), "Debe ser COOPERER o TRAHIR en el primer turno");
        }
    }

//    @Test
//    void testFaireUnChoixProbabilidadAleatoria() {
//        // Prueba para el caso de probabilidad aleatoria cuando el último turno fue COOPERER
//        StrategySondeurNaif strat = new StrategySondeurNaif();
//        ArrayList<Tour> historique = new ArrayList<>();
//        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));  // Último turno
//
//        // Ejecutamos el método varias veces para capturar el efecto de la probabilidad
//        int traiciones = 0;
//
//        for (int i = 0; i < 100; i++) {
//            Choice choix = strat.faireUnChoix(historique, 1);
//            if (choix == Choice.TRAHIR) traiciones++;
//        }
//
//        // Verificamos que se haya elegido TRAHIR en algunas de las ejecuciones (probabilidad del 20%)
//        assertTrue(traiciones > 0, "Debería haber algunas TRAICIONES debido a la probabilidad aleatoria");
//    }

    @Test
    void testFaireUnChoixImitaUltimoMovimientoDelOponenteParaJugador1() {
        // Prueba para verificar que el jugador 1 imita el último movimiento del jugador 2
        StrategySondeurNaif strat = new StrategySondeurNaif();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));  // Último turno

        Choice choix = strat.faireUnChoix(historique, 1);
        assertEquals(Choice.TRAHIR, choix, "El jugador 1 debe imitar el último movimiento del jugador 2");
    }

    @Test
    void testFaireUnChoixImitaUltimoMovimientoDelOponenteParaJugador2() {
        // Prueba para verificar que el jugador 2 imita el último movimiento del jugador 1
        StrategySondeurNaif strat = new StrategySondeurNaif();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));  // Último turno

        Choice choix = strat.faireUnChoix(historique, 2);
        assertEquals(Choice.TRAHIR, choix, "El jugador 2 debe imitar el último movimiento del jugador 1");
    }

//    @Test
//    void testFaireUnChoixSinProbabilidadAleatoria() {
//        // Prueba cuando la probabilidad aleatoria no se cumple y se espera una imitación del último movimiento
//        StrategySondeurNaif strat = new StrategySondeurNaif();
//        ArrayList<Tour> historique = new ArrayList<>();
//        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
//
//        // Verificamos que el jugador 1 imita a jugador 2 (sin traicionar aleatoriamente)
//        for (int i = 0; i < 100; i++) {
//            Choice choix = strat.faireUnChoix(historique, 1);
//            assertEquals(Choice.COOPERER, choix, "El jugador 1 debe imitar a jugador 2 cuando no se cumple la probabilidad aleatoria");
//        }
//    }
}
