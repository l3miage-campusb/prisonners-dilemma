package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.StrategyDonnantDonnant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

class StrategyDonnantDonnantTest {

    @Test
    void testFaireUnChoixDonnantDonnantAvecHistoriqueVide() {
        // Préparer
        StrategyDonnantDonnant strategie = new StrategyDonnantDonnant();
        ArrayList<Tour> historique = new ArrayList<>(); // historique vide
        int joueurRemplace = 1; // indice du joueur, n'affecte pas le résultat

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        assertEquals(Choice.COOPERER, choix, "La stratégie doit toujours choisir COOPERER lorsque l'historique est vide");
        assertNotNull(choix, "Le résultat ne doit pas être null");
        assertFalse(historique.contains(choix), "L'historique ne devrait pas influencer le choix du joueur");
    }

    @Test
    void testFaireUnChoixDonnantDonnantAvecHistoriqueAvecMouvements() {
        // Préparer
        StrategyDonnantDonnant strategie = new StrategyDonnantDonnant();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter quelques éléments à l'historique, le joueur 1 a coopéré et le joueur 2 a trahi
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        int joueurRemplace = 1;

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        // La stratégie doit jouer comme le dernier mouvement de l'adversaire
        assertEquals(Choice.COOPERER, choix, "La stratégie doit choisir COOPERER car le dernier mouvement de l'adversaire était COOPERER");
        assertNotNull(choix, "Le résultat ne doit pas être null");
    }

    @Test
    void testFaireUnChoixDonnantDonnantAvecHistoriqueAvecMouvementsOpposes() {
        // Préparer
        StrategyDonnantDonnant strategie = new StrategyDonnantDonnant();
        ArrayList<Tour> historique = new ArrayList<>();

        // Ajouter quelques éléments à l'historique, le joueur 1 a coopéré et le joueur 2 a trahi
        historique.add(new Tour(Choice.COOPERER, Choice.TRAHIR));
        historique.add(new Tour(Choice.TRAHIR, Choice.COOPERER));
        int joueurRemplace = 2; // Indice du deuxième joueur

        // Agir
        Choice choix = strategie.faireUnChoix(historique, joueurRemplace);

        // Vérifier
        // La stratégie doit jouer comme le dernier mouvement de l'adversaire
        assertEquals(Choice.TRAHIR, choix, "La stratégie doit choisir COOPERER car le dernier mouvement de l'adversaire était COOPERER");
        assertNotNull(choix, "Le résultat ne doit pas être null");
    }
}
