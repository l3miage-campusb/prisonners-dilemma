package fr.uga.l3miage.pc.prisonersdilemma.Strategies;



import ch.qos.logback.core.net.server.Client;
import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyToujoursCooperer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StrategyToujoursCoopererTest {


    @Test
    void testtt(){

        StrategyToujoursCooperer strat = new StrategyToujoursCooperer();
        ArrayList<Tour> historique = new ArrayList<>(); // historial vacío
        int joueurRemplace = 1; // índice del jugador, no afecta el resultado

        // Act
        Choice choix = strat.faireUnChoix(historique, joueurRemplace);

        // Assert
        assertEquals(Choice.COOPERER, choix, "La estrategia debe devolver siempre COOPERER cuando el historial está vacío");
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
    }

}
