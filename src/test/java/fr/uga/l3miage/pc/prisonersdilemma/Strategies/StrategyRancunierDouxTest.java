package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.StrategyRancunierDoux;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyRancunierDouxTest {

    private StrategyRancunierDoux strategie;
    private ArrayList<Tour> historique;

    @BeforeEach
    void setUp() {
        strategie = new StrategyRancunierDoux();
        historique = new ArrayList<>();
    }

    @Test
    void testPremierTourCooperation() {
        // Vérifier que la stratégie coopère au premier tour
        Choice choix = strategie.faireUnChoix(historique, 1);
        assertEquals(Choice.COOPERER, choix, "Au premier tour, la stratégie doit coopérer.");
    }

    @Test
    void testTrahisonDetectee() {
        // Ajouter une trahison de l'adversaire
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        Choice choix = strategie.faireUnChoix(historique, 1);
        assertEquals(Choice.TRAHIR, choix, "La stratégie doit trahir en réponse à une trahison de l'adversaire.");


    }

    @Test
    void testPunitionSuivieDeCooperation() {
        // Ajouter une trahison suivie d'une punition
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));

        // Phase de punition : trahir 4 fois
        for (int i = 0; i < 4; i++) {
            strategie.faireUnChoix(historique, 1);
        }

        // Phase de punition : coopérer 2 fois
        for (int i = 0; i < 2; i++) {
            Choice choix = strategie.faireUnChoix(historique, 1);
            assertEquals(Choice.COOPERER, choix, "La stratégie doit coopérer pendant les deux derniers tours du cycle de punition.");
        }
    }


}
