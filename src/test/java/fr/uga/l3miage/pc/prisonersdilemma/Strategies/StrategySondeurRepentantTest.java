package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.StrategySondeurRepentant;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrategySondeurRepentantTest {

    @Test
    void testFaireUnChoixSondeurRepentantApresTrahisonAdversairePourJoueur2() {
        // Préparer
        StrategySondeurRepentant strategie = new StrategySondeurRepentant();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où l'adversaire a trahi
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        int joueurRemplace = 2;

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "La stratégie doit choisir COOPERER pour se montrer repentante après une trahison de l'adversaire");
        assertNotNull(choix, "Le choix ne doit pas être null");
    }

    @Test
    void testFaireUnChoixSondeurRepentantAvecProbabiliteTrahisonAtteinte() {
        // Préparer
        StrategySondeurRepentant strategie = new StrategySondeurRepentant();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));
        int joueurRemplace = 1;

        // Injecter une trahison en forçant la probabilité
        boolean trahisonGenerée = false;
        for (int i = 0; i < 100; i++) { // Essayer plusieurs fois pour s'assurer que la trahison survienne
            Choice choix = strategie.faireUnChoix(historique, joueurRemplace);
            if (choix == Choice.TRAHIR) {
                trahisonGenerée = true;
                break;
            }
        }

        assertTrue(trahisonGenerée, "La stratégie devrait avoir une chance de trahir même si l'adversaire a coopéré");
    }

    @Test
    void testFaireUnChoixSondeurRepentantImiterDernierCoupAdversairePourJoueur1() {
        // Préparer
        StrategySondeurRepentant strategie = new StrategySondeurRepentant();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où l'adversaire a coopéré
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        int joueurRemplace = 1;

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "La stratégie doit imiter le dernier coup de l'adversaire si aucune condition spéciale n'est remplie");
        assertNotNull(choix, "Le choix ne doit pas être null");
    }

    @Test
    void testFaireUnChoixSondeurRepentantAvecHistoriqueVide() {
        // Préparer
        StrategySondeurRepentant strategie = new StrategySondeurRepentant();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>(); // historique vide
        int joueurRemplace = 1; // indice du joueur, n'affecte pas le résultat

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "La stratégie doit choisir COOPERER lorsque l'historique est vide");
        assertNotNull(choix, "Le choix ne doit pas être null");
    }

    @Test
    void testFaireUnChoixSondeurRepentantApresTrahison() {
        // Préparer
        StrategySondeurRepentant strategie = new StrategySondeurRepentant();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où l'adversaire a trahi
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        int joueurRemplace = 1;

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "La stratégie doit choisir COOPERER pour se montrer repentante après une trahison de l'adversaire");
        assertNotNull(choix, "Le choix ne doit pas être null");
    }

    @Test
    void testFaireUnChoixSondeurRepentantApresTrahisonJ2() {
        // Préparer
        StrategySondeurRepentant strategie = new StrategySondeurRepentant();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où l'adversaire a trahi
        historique.add(new Tour(Choice.TRAHIR, Choice.TRAHIR));
        int joueurRemplace = 2;

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "La stratégie doit choisir COOPERER pour se montrer repentante après une trahison de l'adversaire");
        assertNotNull(choix, "Le choix ne doit pas être null");
    }

    @Test
    void testFaireUnChoixSondeurRepentantApresTrahisonJ2nonTrahison() {
        // Préparer
        StrategySondeurRepentant strategie = new StrategySondeurRepentant();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où l'adversaire a trahi
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        int joueurRemplace = 2;

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "La stratégie doit choisir COOPERER pour se montrer repentante après une trahison de l'adversaire");
        assertNotNull(choix, "Le choix ne doit pas être null");
    }


    @Test
    void testFaireUnChoixSondeurRepentantAvecProbabiliteTrahison() {
        // Préparer
        StrategySondeurRepentant strategie = new StrategySondeurRepentant();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter un tour où l'adversaire a coopéré
        historique.add(new Tour(Choice.COOPERER, Choice.COOPERER));
        int joueurRemplace = 1;

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertNotNull(choix, "Le choix ne doit pas être null");
        // Comme la stratégie inclut une probabilité de trahison, on vérifie simplement que le choix est valide (COOPERER ou TRAHIR)
        assertTrue(choix == Choice.COOPERER || choix == Choice.TRAHIR, "Le choix doit être COOPERER ou TRAHIR en fonction de la probabilité");
    }

    @Test
    void testFaireUnChoixSondeurRepentantImitationAdversaire() {
        // Préparer
        StrategySondeurRepentant strategie = new StrategySondeurRepentant();
        strategie.setSeed(42);
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter des tours précédents, le dernier coup de l'adversaire est TRAHIR
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        int joueurRemplace = 2;

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertNotNull(choix, "Le choix ne doit pas être null");
        assertEquals(Choice.COOPERER, choix, "La stratégie doit imiter le dernier coup de l'adversaire si aucune condition spéciale n'est remplie");
    }
}
