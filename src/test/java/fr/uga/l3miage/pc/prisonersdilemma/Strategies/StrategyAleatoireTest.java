package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.StrategyAleatoire;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class StrategyAleatoireTest {

    @Test
    void testFaireUnChoixAvecHistoriqueVide() {
        // Test avec historique vide
        StrategyAleatoire strategie = new StrategyAleatoire();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();
        Choice choix = strategie.faireUnChoix(historique, 1);

        // Vérification : Le résultat doit être COOPERER ou TRAHIR
        assertTrue(choix == Choice.COOPERER || choix == Choice.TRAHIR,
                "Le résultat doitt être COOPERER ou TRAHIR");
    }

    @Test
    void testFaireUnChoixAleatoireDistribution() {
        // Test pour vérifier que tant COOPERER que TRAHIR sont possibles
        StrategyAleatoire strategie = new StrategyAleatoire();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();
        Set<Choice> resultats = new HashSet<>();

        // Exécution de la méthode plusieurs fois pour observer différents résultats
        for (int i = 0; i < 100; i++) {
            resultats.add(strategie.faireUnChoix(historique, 1));
            if (resultats.contains(Choice.COOPERER) && resultats.contains(Choice.TRAHIR)) {
                break;
            }
        }

        // Vérification : Les deux résultats doivent apparaître dans plusieurs exécutions
        assertTrue(resultats.contains(Choice.COOPERER), "La stratégie devrait parfois coopérer");
        assertTrue(resultats.contains(Choice.TRAHIR), "La stratégie devrait parfois trahir");
    }
}
