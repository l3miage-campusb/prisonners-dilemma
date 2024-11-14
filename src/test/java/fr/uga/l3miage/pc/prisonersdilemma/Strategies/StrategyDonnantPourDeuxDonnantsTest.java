package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyDonnantPourDeuxDonnants;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StrategyDonnantPourDeuxDonnantsTest {

    @Test
    void testFaireUnChoixAvecHistoriqueVide() {
        // Préparer
        StrategyDonnantPourDeuxDonnants strategie = new StrategyDonnantPourDeuxDonnants();
        ArrayList<Tour> historique = new ArrayList<>(); // historique vide
        int joueurRemplace = 1; // peu importe le joueur

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "La stratégie doit choisir COOPERER lorsque l'historique est vide");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupEgaux() {
        // Préparer
        StrategyDonnantPourDeuxDonnants strategie = new StrategyDonnantPourDeuxDonnants();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter quelques éléments à l'historique, les deux joueurs ont fait les mêmes choix
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));

        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Le joueur 1 doit coopérer, car le dernier coup de l'adversaire était COOPERER");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupOpposes() {
        // Préparer
        StrategyDonnantPourDeuxDonnants strategie = new StrategyDonnantPourDeuxDonnants();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter quelques éléments à l'historique, les deux joueurs ont fait des choix opposés
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.TRAHIR, choix, "Le joueur 1 doit trahir car le dernier coup de l'adversaire était TRAHIR");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupInverse() {
        // Préparer
        StrategyDonnantPourDeuxDonnants strategie = new StrategyDonnantPourDeuxDonnants();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter quelques éléments à l'historique, les deux joueurs ont fait des choix opposés
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        int joueurRemplace = 2; // Le joueur 2 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Le joueur 2 doit coopérer car le dernier coup de l'adversaire était COOPERER");
    }

    @Test
    void testCoupsEgauxAvecCoupIdentiques() {
        // Préparer
        StrategyDonnantPourDeuxDonnants strategie = new StrategyDonnantPourDeuxDonnants();
        Tour tour1 = new Tour(Choice.COOPERER, Choice.TRAHIR);
        Tour tour2 = new Tour(Choice.COOPERER, Choice.TRAHIR);

        // Agir
        boolean resultJoueur1 = strategie.coupsEgaux(1, tour1, tour2);
        boolean resultJoueur2 = strategie.coupsEgaux(2, tour1, tour2);

        // Vérifier
        assertTrue(resultJoueur1, "Les coups des joueurs 1 doivent être égaux");
        assertTrue(resultJoueur2, "Les coups des joueurs 2 doivent être égaux");
    }

    @Test
    void testCoupsEgauxAvecCoupDifferents() {
        // Préparer
        StrategyDonnantPourDeuxDonnants strategie = new StrategyDonnantPourDeuxDonnants();
        Tour tour1 = new Tour(Choice.COOPERER, Choice.TRAHIR);
        Tour tour2 = new Tour(Choice.TRAHIR, Choice.COOPERER);

        // Agir
        boolean resultJoueur1 = strategie.coupsEgaux(1, tour1, tour2);
        boolean resultJoueur2 = strategie.coupsEgaux(2, tour1, tour2);

        // Vérifier
        assertFalse(resultJoueur1, "Les coups des joueurs 1 ne doivent pas être égaux");
        assertFalse(resultJoueur2, "Les coups des joueurs 2 ne doivent pas être égaux");
    }
}
