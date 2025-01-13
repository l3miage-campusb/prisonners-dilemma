package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyDonnantDonnantAleatoire;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class StrategyDonnantDonnantAleatoireTest {

    @Test
    void testFaireUnChoixAvecHistoriqueVide() {
        // Test avec historique vide
        StrategyDonnantDonnantAleatoire strategie = new StrategyDonnantDonnantAleatoire();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Le choix doit être soit COOPERER, soit TRAHIR
        Choice choix = strategie.faireUnChoix(historique, 1);

        // Vérification : Le choix doit être aléatoire entre COOPERER ou TRAHIR
        assertTrue(choix == Choice.COOPERER || choix == Choice.TRAHIR,
                "Le résultat doit être COOPERER ou TRAHIR");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecMouvements() {
        // Test avec historique contenant des choix précédents
        StrategyDonnantDonnantAleatoire strategie = new StrategyDonnantDonnantAleatoire();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajout de mouvements à l'historique
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        // Exécution du test pour le joueur 1
        Choice choixJoueur1 = strategie.faireUnChoix(historique, 1);
        // Vérification : Le choix du joueur 1 doit être celui du dernier mouvement de l'adversaire
        assertEquals(Choice.COOPERER, choixJoueur1, "Le joueur 1 doit suivre le choix du joueur 2");

        // Exécution du test pour le joueur 2
        Choice choixJoueur2 = strategie.faireUnChoix(historique, 2);
        // Vérification : Le choix du joueur 2 doit être celui du dernier mouvement de l'adversaire
        assertEquals(Choice.TRAHIR, choixJoueur2, "Le joueur 2 doit suivre le choix du joueur 1");
    }

    @Test
    void testFaireUnChoixAleatoire() {
        // Test avec historique contenant des choix précédents
        StrategyDonnantDonnantAleatoire strategie = new StrategyDonnantDonnantAleatoire();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajout de mouvements à l'historique
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        // Exécution du test pour le joueur 1
        Choice choixJoueur1 = strategie.faireUnChoix(historique, 1);

        // Exécution du test pour le joueur 2
        Choice choixJoueur2 = strategie.faireUnChoix(historique, 2);



        boolean cooperer = false;
        boolean trahir = false;
        for (int i = 0; i < 1000; i++) {
            // Exécution du test pour le joueur 1
             choixJoueur1 = strategie.faireUnChoix(historique, 1);

            // Exécution du test pour le joueur 2
            choixJoueur2 = strategie.faireUnChoix(historique, 2);
            if (choixJoueur1 == Choice.TRAHIR) {
                trahir = true;
            } else if (choixJoueur2 == Choice.COOPERER) {
                cooperer = true;
            }
            if (cooperer && trahir) {
                break; // Les deux choix ont été rencontrés, on peut arrêter le test
            }
        }

        // Vérification : Les joueurs font parfois le choix qu ils ne devraient pas faire
        assertTrue(cooperer, "La stratégie doit parfois choisir COOPERER");
        assertTrue(trahir, "La stratégie doit parfois choisir TRAHIR");
    }
}
