package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyPavlovAleatoire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrategyPavlovAleatoireTest {

    private StrategyPavlovAleatoire strategy;

    @BeforeEach
    void setUp() {
        strategy = new StrategyPavlovAleatoire();
    }

    @Test
    void testFaireUnChoixHistoriqueVide() {
        // Cas où l'historique est vide
        ArrayList<Tour> historique = new ArrayList<>();
        Choice choix = strategy.faireUnChoix(historique, 1);

        // Le choix peut être COOPERER ou TRAHIR, donc on vérifie simplement qu'il n'est pas null
        assertNotNull(choix, "Le choix ne doit pas être null lorsque l'historique est vide");
    }

    @RepeatedTest(10)
    void testFaireUnChoixAvecProbabiliteAleatoire() {
        // Historique avec un choix quelconque pour tester la probabilité aléatoire
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));

        Choice choix = strategy.faireUnChoix(historique, 1);

        // Vérifier que le choix est COOPERER ou TRAHIR, et n'est donc pas null
        assertNotNull(choix, "Le choix ne doit pas être null");
        assertTrue(choix == Choice.COOPERER || choix == Choice.TRAHIR, "Le choix doit être soit COOPERER soit TRAHIR");
    }

    @Test
    void testFaireUnChoixApresGainMaximumJoueur1() {
        // Cas où le joueur 1 a trahi et obtenu le maximum (5 points)
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        Choice choix = strategy.faireUnChoix(historique, 1);

        // Le joueur 1 doit choisir TRAHIR de nouveau selon la stratégie Pavlov
        assertEquals(Choice.TRAHIR, choix, "La stratégie doit choisir TRAHIR après un gain maximum pour le joueur");
    }

    @Test
    void testFaireUnChoixApresGainMaximumJoueur2() {
        // Cas où le joueur 2 a trahi et obtenu le maximum (5 points)
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));

        Choice choix = strategy.faireUnChoix(historique, 2);

        // Le joueur 2 doit choisir TRAHIR de nouveau selon la stratégie Pavlov
        assertEquals(Choice.TRAHIR, choix, "La stratégie doit choisir TRAHIR après un gain maximum pour le joueur");
    }

    @Test
    void testFaireUnChoixCooperationMutuelle() {
        // Cas de coopération mutuelle entre les joueurs
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));

        Choice choixJoueur1 = strategy.faireUnChoix(historique, 1);
        Choice choixJoueur2 = strategy.faireUnChoix(historique, 2);

        // Les deux joueurs doivent coopérer de nouveau
        assertEquals(Choice.COOPERER, choixJoueur1, "Le joueur 1 doit continuer à coopérer après une coopération mutuelle");
        assertEquals(Choice.COOPERER, choixJoueur2, "Le joueur 2 doit continuer à coopérer après une coopération mutuelle");
    }

    @Test
    void testFaireUnChoixApresTrahisonSansGainMaximum() {
        // Cas où le joueur n'a pas atteint le maximum mais a trahi
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));

        Choice choixJoueur2 = strategy.faireUnChoix(historique, 2);

        // Le joueur 1 doit inverser son choix et trahir, tandis que le joueur 2 inverse son choix et
        assertEquals(Choice.TRAHIR, choixJoueur2, "Le joueur 2 doit coopérer en réponse");
    }
}
