package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.StrategyDonnantDonnantSupconneux;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyDonnantDonnantSupconneuxTest {

    private StrategyDonnantDonnantSupconneux strategy;

    @BeforeEach
    void setUp() {
        strategy = new StrategyDonnantDonnantSupconneux();
    }

    @Test
    void testPremierTourTrahison() {
        // Au premier tour, sans historique, la stratégie devrait trahir.
        ArrayList<Tour> historique = new ArrayList<>();
        Choice choix = strategy.faireUnChoix(historique, 1);
        assertEquals(Choice.TRAHIR, choix, "La stratégie devrait trahir au premier tour.");
    }

    @Test
    void testReproduitChoixAdversaireApresPremierTour() {
        // Simuler un historique avec un seul tour où l'adversaire a coopéré.
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        // La stratégie devrait maintenant coopérer pour imiter l'adversaire
        Choice choix = strategy.faireUnChoix(historique, 1);
        assertEquals(Choice.COOPERER, choix, "La stratégie devrait reproduire le choix de l'adversaire (COOPERER).");

        // Ajouter un tour où l'adversaire trahit cette fois
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));

        // La stratégie devrait maintenant trahir pour imiter l'adversaire
        choix = strategy.faireUnChoix(historique, 1);
        assertEquals(Choice.TRAHIR, choix, "La stratégie devrait reproduire le choix de l'adversaire (TRAHIR).");
    }

    @Test
    void testChoixJoueurRemplace2() {
        // Vérifie le comportement lorsque le joueur remplacé est le joueur 2
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));

        // La stratégie pour joueurRemplace = 2 devrait trahir pour imiter le joueur 1
        Choice choix = strategy.faireUnChoix(historique, 2);
        assertEquals(Choice.COOPERER, choix, "La stratégie pour joueur 2 devrait reproduire le choix du joueur 1 (COOPERER).");

        // Ajouter un tour où l'adversaire coopère
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        // La stratégie pour joueurRemplace = 2 devrait coopérer pour imiter le joueur 1
        choix = strategy.faireUnChoix(historique, 2);
        assertEquals(Choice.TRAHIR, choix, "La stratégie pour joueur 2 devrait reproduire le choix du joueur 1 (TRAHIR).");
    }
}
