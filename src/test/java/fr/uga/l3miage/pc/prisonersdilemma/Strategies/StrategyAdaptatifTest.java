package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.StrategyAdaptatif;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyAdaptatifTest {

    private StrategyAdaptatif strategy;

    @BeforeEach
    void setUp() {
        strategy = new StrategyAdaptatif();
    }

    @Test
    void testFaireUnChoixPendantSequenceInitiale() {
        // Tester chaque coup dans la séquence initiale de longueur 11
        ArrayList<Tour> historique = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Choice expectedChoice = (i < 6) ? Choice.COOPERER : Choice.TRAHIR;
            Choice actualChoice = strategy.faireUnChoix(historique, 1);
            assertEquals(expectedChoice, actualChoice, "Le choix pendant la séquence initiale doit correspondre à la séquence pré-définie.");
            historique.add(new Tour(expectedChoice, Choice.COOPERER)); // Ajouter le tour au fur et à mesure
        }
    }

    @Test
    void testFaireUnChoixApresSequenceInitiale() {
        // Simuler un historique complet de la séquence initiale
        ArrayList<Tour> historique = new ArrayList<>();
        for (Choice choice : new Choice[]{Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER,
                Choice.COOPERER, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR}) {
            historique.add(new Tour(choice, Choice.COOPERER)); // Ajouter des tours où l'adversaire coopère
        }

        // Ajouter des tours pour influencer les scores et tester la logique adaptative
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR)); // Joue COOPERER, l'adversaire trahit
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER)); // Joue TRAHIR, l'adversaire coopère

        // Calculer le prochain choix après la séquence initiale, basé sur les scores
        Choice choix = strategy.faireUnChoix(historique, 1);
        assertEquals(Choice.TRAHIR, choix, "La stratégie doit trahir si le score moyen pour trahison est supérieur.");
    }

    @Test
    void testScoreMoyenFavoriseCooperation() {
        // Historique favorisant la coopération
        ArrayList<Tour> historique = new ArrayList<>();
        for (Choice choice : new Choice[]{Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER,
                Choice.COOPERER, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR}) {
            if(choice==Choice.TRAHIR){
                historique.add(new Tour(choice, Choice.TRAHIR));
            }else{
            historique.add(new Tour(choice, Choice.COOPERER));
            }
        }

        // Ajouter des tours de coopération gagnants
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER)); // Joue COOPERER, l'adversaire coopère (score favorable à COOPERER)
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER)); // Joue COOPERER, l'adversaire coopère



        // Le choix suivant doit favoriser la coopération
        Choice choix = strategy.faireUnChoix(historique, 1);
        assertEquals(Choice.COOPERER, choix, "La stratégie doit coopérer si le score moyen pour coopération est supérieur.");
    }

    @Test
    void testScoreMoyenFavoriseTrahison() {
        // Historique favorisant la trahison
        ArrayList<Tour> historique = new ArrayList<>();
        for (Choice choice : new Choice[]{Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER, Choice.COOPERER,
                Choice.COOPERER, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR, Choice.TRAHIR}) {
            historique.add(new Tour(choice, Choice.TRAHIR)); // Adversaire trahit plus souvent
        }

        // Ajouter des tours de trahison gagnants
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER)); // Joue TRAHIR, l'adversaire coopère (score favorable à TRAHIR)
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER)); // Joue TRAHIR, l'adversaire coopère

        // Le choix suivant doit favoriser la trahison
        Choice choix = strategy.faireUnChoix(historique, 1);
        assertEquals(Choice.TRAHIR, choix, "La stratégie doit trahir si le score moyen pour trahison est supérieur.");
    }
}
