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
    void testFaireUnChoixHistoriqueVide() {
        // Test pour le cas où l'historique est vide
        StrategySondeurNaif strategie = new StrategySondeurNaif();
        ArrayList<Tour> historique = new ArrayList<>();

        Set<Choice> resultatsPossibles = EnumSet.of(Choice.COOPERER, Choice.TRAHIR);

        // Exécution de la méthode plusieurs fois pour capturer l'aspect aléatoire
        for (int i = 0; i < 100; i++) {
            Choice choix = strategie.faireUnChoix(historique, 1);
            assertTrue(resultatsPossibles.contains(choix), "Le choix doit être COOPERER ou TRAHIR au premier tour");
        }
    }

    @Test
    void testFaireUnChoixProbabiliteAleatoire() {
        // Test pour le cas de probabilité aléatoire lorsque le dernier tour était COOPERER
        StrategySondeurNaif strategie = new StrategySondeurNaif();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));  // Dernier tour

        // Exécution de la méthode plusieurs fois pour capturer l'effet de la probabilité
        int trahisons = 0;

        for (int i = 0; i < 100; i++) {
            Choice choix = strategie.faireUnChoix(historique, 1);
            if (choix == Choice.TRAHIR) trahisons++;
        }

        // Vérification qu'il y a eu des trahisons lors de l'exécution (probabilité de 20%)
        assertTrue(trahisons > 0, "Il devrait y avoir quelques TRAHISONS en raison de la probabilité aléatoire");
    }

    @Test
    void testFaireUnChoixImiteDernierMouvementAdversairePourJoueur1() {
        // Test pour vérifier que le joueur 1 imite le dernier mouvement du joueur 2
        StrategySondeurNaif strategie = new StrategySondeurNaif();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));  // Dernier tour

        Choice choix = strategie.faireUnChoix(historique, 1);
        assertEquals(Choice.TRAHIR, choix, "Le joueur 1 doit imiter le dernier mouvement du joueur 2");
    }

    @Test
    void testFaireUnChoixImiteDernierMouvementAdversairePourJoueur2() {
        // Test pour vérifier que le joueur 2 imite le dernier mouvement du joueur 1
        StrategySondeurNaif strategie = new StrategySondeurNaif();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));  // Dernier tour

        Choice choix = strategie.faireUnChoix(historique, 2);
        assertEquals(Choice.TRAHIR, choix, "Le joueur 2 doit imiter le dernier mouvement du joueur 1");
    }

    @Test
    void testFaireUnChoixSansProbabiliteAleatoire() {
        // Test pour vérifier le comportement sans probabilité aléatoire
        StrategySondeurNaif strategie = new StrategySondeurNaif();
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        int imitations = 0;
        for (int i = 0; i < 1000; i++) {
            Choice choix = strategie.faireUnChoix(historique, 1);
            if (choix == Choice.COOPERER) {
                imitations++;
            }
        }

        // Vérification que le joueur 1 doit imiter le joueur 2 dans la majorité des cas lorsque la probabilité aléatoire est faible
        assertTrue(imitations >= 80, "Le joueur 1 doit imiter le joueur 2 dans la plupart des cas lorsque la probabilité aléatoire est faible");
    }
}
