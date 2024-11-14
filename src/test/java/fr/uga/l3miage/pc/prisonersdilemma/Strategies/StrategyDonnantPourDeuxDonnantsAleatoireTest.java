package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyAleatoire;
import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.StrategyDonnantPourDeuxDonnantsAleatoire;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StrategyDonnantPourDeuxDonnantsAleatoireTest {

    @Test
    void testFaireUnChoixAvecHistoriqueVide() {
        // Test avec historique vide
        StrategyDonnantPourDeuxDonnantsAleatoire strategie = new StrategyDonnantPourDeuxDonnantsAleatoire();
        ArrayList<Tour> historique = new ArrayList<>();
        Choice choix = strategie.faireUnChoix(historique, 1);

        // Vérification : Le résultat doit être COOPERER ou TRAHIR
        assertTrue(choix == Choice.COOPERER || choix == Choice.TRAHIR,
                "Le résultat doitt être COOPERER ou TRAHIR");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupEgaux() {
        // Préparer
        StrategyDonnantPourDeuxDonnantsAleatoire strategie = new StrategyDonnantPourDeuxDonnantsAleatoire();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter quelques éléments à l'historique, les deux joueurs ont fait les mêmes choix
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));

        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "Le joueur 1 doitt coopérer, car le dernier coup de l'adversaire était COOPERER");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueNonRepete() {
        // Préparer
        StrategyDonnantPourDeuxDonnantsAleatoire strategie = new StrategyDonnantPourDeuxDonnantsAleatoire();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter quelques éléments à l'historique, les deux joueurs ont fait des choix opposés
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));

        int joueurRemplace = 1; // Le joueur 1 est remplacé

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.TRAHIR, choix, "Le joueur 1 doit cooperer car le joeuur n a pas fait le meme choix deux fois de suite");
    }

    @Test
    void testFaireUnChoixAvecHistoriqueAvecCoupInverse() {
        // Préparer
        StrategyDonnantPourDeuxDonnantsAleatoire strategie = new StrategyDonnantPourDeuxDonnantsAleatoire();
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

//    @Test
//    void testCoupsEgauxAvecCoupIdentiques() {
//        // Préparer
//        StrategyDonnantPourDeuxDonnantsAleatoire strategie = new StrategyDonnantPourDeuxDonnantsAleatoire();
//        Tour tour1 = new Tour(Choice.COOPERER, Choice.TRAHIR);
//        Tour tour2 = new Tour(Choice.COOPERER, Choice.TRAHIR);
//
//        // Agir
//        boolean resultJoueur1 = strategie.coupsEgaux(1, tour1, tour2);
//        boolean resultJoueur2 = strategie.coupsEgaux(2, tour1, tour2);
//
//        // Vérifier
//        assertTrue(resultJoueur1, "Les coups des joueurs 1 doivent être égaux");
//        assertTrue(resultJoueur2, "Les coups des joueurs 2 doivent être égaux");
//    }
//
//    @Test
//    void testCoupsEgauxAvecCoupDifferents() {
//        // Préparer
//        StrategyDonnantPourDeuxDonnantsAleatoire strategie = new StrategyDonnantPourDeuxDonnantsAleatoire();
//        Tour tour1 = new Tour(Choice.COOPERER, Choice.TRAHIR);
//        Tour tour2 = new Tour(Choice.TRAHIR, Choice.COOPERER);
//
//        // Agir
//        boolean resultJoueur1 = strategie.coupsEgaux(1, tour1, tour2);
//        boolean resultJoueur2 = strategie.coupsEgaux(2, tour1, tour2);
//
//        // Vérifier
//        assertFalse(resultJoueur1, "Les coups des joueurs 1 ne doivent pas être égaux");
//        assertFalse(resultJoueur2, "Les coups des joueurs 2 ne doivent pas être égaux");
//    }
}
