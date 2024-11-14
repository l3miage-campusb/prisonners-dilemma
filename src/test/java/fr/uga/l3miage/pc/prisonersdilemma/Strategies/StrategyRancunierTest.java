package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyRancunier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StrategyRancunierTest {

    @Test
    void testFaireUnChoixAvecHistoriqueVide() {
        // Préparer
        StrategyRancunier strategie = new StrategyRancunier();
        ArrayList<Tour> historique = new ArrayList<>(); // historique vide
        int joueurRemplace = 1; // peu importe le joueur

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Si l'historique est vide, la stratégie doit coopérer.");
    }

    @Test
    void testFaireUnChoixJoueur1AvecTrahisonJoueur2() {
        // Préparer
        StrategyRancunier strategie = new StrategyRancunier();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a coopéré et le joueur 2 a trahi
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.TRAHIR, choix, "Le joueur 1 doit trahir s'il a vu que le joueur 2 a trahi.");
    }

    @Test
    void testFaireUnChoixJoueur1AvecCooperationJoueur2() {
        // Préparer
        StrategyRancunier strategie = new StrategyRancunier();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a coopéré et le joueur 2 a coopéré
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));
        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Le joueur 1 doit coopérer si le joueur 2 a coopéré.");
    }

    @Test
    void testFaireUnChoixJoueur2AvecTrahisonJoueur1() {
        // Préparer
        StrategyRancunier strategie = new StrategyRancunier();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a trahi et le joueur 2 a coopéré
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        int joueurRemplace = 2; // Le joueur 2 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.TRAHIR, choix, "Le joueur 2 doit trahir s'il a vu que le joueur 1 a trahi.");
    }

    @Test
    void testFaireUnChoixJoueur2AvecCooperationJoueur1() {
        // Préparer
        StrategyRancunier strategie = new StrategyRancunier();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où le joueur 1 a coopéré et le joueur 2 a coopéré
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));
        int joueurRemplace = 2; // Le joueur 2 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Le joueur 2 doit coopérer si le joueur 1 a coopéré.");
    }
}
