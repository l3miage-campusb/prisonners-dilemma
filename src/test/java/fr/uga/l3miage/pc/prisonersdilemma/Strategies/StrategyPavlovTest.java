package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyPavlov;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyPavlovTest {

    @Test
    void testFaireUnChoixAvecHistoriqueVide() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();
        ArrayList<Tour> historique = new ArrayList<>(); // historique vide
        int joueurRemplace = 1; // peu importe le joueur

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertNotNull(choix, "Le choix ne doit pas être null");
        assertTrue(choix == Choice.COOPERER || choix == Choice.TRAHIR, "Le choix doit être soit COOPERER soit TRAHIR");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupJ1CoopererJ2Cooperer() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter quelques éléments à l'historique, les deux joueurs ont coopéré
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));
        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Le joueur 1 doit coopérer car les deux ont coopéré dans le dernier tour");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupJ1CoopererJ2CoopererOppose() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter quelques éléments à l'historique, les deux joueurs ont coopéré
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));
        int joueurRemplace = 2; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Le joueur 1 doit coopérer car les deux ont coopéré dans le dernier tour");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupJ1CoopererJ2Trahir() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a trahi et le joueur 2 a coopéré
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.TRAHIR, choix, "Le joueur 1 doit trahir car le dernier choix n a pas ete profitable");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupJ1CoopererJ2TrahirOppose() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a trahi et le joueur 2 a coopéré
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        int joueurRemplace = 2; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.TRAHIR, choix, "Le joueur 2 doit trahir car le derneir choix a ete profitable");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupJ1TrahirJ2Cooperer() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a trahi et le joueur 2 a coopéré
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.TRAHIR, choix, "Le joueur 1 doit trahir car le derneir choix a ete profitable");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupJ1TrahirJ2CoopererOppose() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a trahi et le joueur 2 a coopéré
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        int joueurRemplace = 2; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.TRAHIR, choix, "Le joueur 2 doit trahir car le derneir choix a ete defavorable");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupJ1TrahirJ2Trahir() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a trahi et le joueur 2 a coopéré
        historique.add(new Tour(Choice.TRAHIR, Choice.TRAHIR));
        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Le joueur 1 doit cooperer car le dernier tour n'a pas était profitable");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupJ1TrahirJ2TrahirOppose() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a trahi et le joueur 2 a coopéré
        historique.add(new Tour(Choice.TRAHIR, Choice.TRAHIR));
        int joueurRemplace = 2; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Le joueur 2 doit cooperer car le dernier tour n'a pas était profitable");
    }

    @Test
    void testInverseAvecChoiceCooperer() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();

        // Agir
        Choice choixInverse = strategie.inverse(Choice.COOPERER);

        // Vérifier
        assertEquals(Choice.TRAHIR, choixInverse, "Si le choix est COOPERER, l'inverse doit être TRAHIR");
    }

    @Test
    void testInverseAvecChoiceTrahir() {
        // Préparer
        StrategyPavlov strategie = new StrategyPavlov();

        // Agir
        Choice choixInverse = strategie.inverse(Choice.TRAHIR);

        // Vérifier
        assertEquals(Choice.COOPERER, choixInverse, "Si le choix est TRAHIR, l'inverse doit être COOPERER");
    }
}
