package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyPacificateurNaif;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StrategyPacificateurNaifTest {

    private StrategyPacificateurNaif strategy;

    @BeforeEach
    void setUp() {
        strategy = new StrategyPacificateurNaif(); strategy.setSeed(42);
    }

    @Test
    void testFaireUnChoixHistoriqueVide() {
        // Cas où l'historique est vide
        ArrayList<Tour> historique = new ArrayList<>();
        Choice choix = strategy.faireUnChoix(historique, 1);

        // Le choix peut être COOPERER ou TRAHIR, donc nous vérifions simplement qu'il n'est pas null
        assertNotNull(choix, "Le choix ne doit pas être null quand l'historique est vide");
    }

    @RepeatedTest(10)
    void testFaireUnChoixAvecProbabiliteAleatoire() {
        // Historique où l'adversaire a trahi, ce qui peut déclencher la probabilité de coopérer
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.TRAHIR, Choice.TRAHIR));

        Choice choix = strategy.faireUnChoix(historique, 1);

        // Vérifier que le choix est bien COOPERER ou TRAHIR en fonction de la probabilité
        assertNotNull(choix, "Le choix ne doit pas être null");
        // Comme il y a une probabilité de 5% d'obtenir COOPERER, le résultat est variable mais toujours valide.
    }

    @Test
    void testFaireUnChoixImitationAdversaireJoueurRemplace1() {
        // Historique où l'adversaire a choisi COOPERER et joueurRemplace est 1
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        Choice choix = strategy.faireUnChoix(historique, 1);

        // Le choix doit imiter le choix de l'adversaire, qui est COOPERER
        assertEquals(Choice.COOPERER, choix, "Le joueur doit imiter le choix de l'adversaire (COOPERER)");
    }

    @Test
    void testFaireUnChoixImitationAdversaireJoueurRemplace2() {
        // Historique où l'adversaire a choisi TRAHIR et joueurRemplace est 2
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        Choice choix = strategy.faireUnChoix(historique, 2);

        // Le choix doit imiter le choix de l'adversaire, qui est TRAHIR
        assertEquals(Choice.TRAHIR, choix, "Le joueur doit imiter le choix de l'adversaire (TRAHIR)");
    }
}
