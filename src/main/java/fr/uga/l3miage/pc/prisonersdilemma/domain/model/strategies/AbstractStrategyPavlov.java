package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.Tour;

import java.security.SecureRandom;
import java.util.List;


public abstract class AbstractStrategyPavlov extends StrategieAbstract {

    protected static final double DEFAULT_PROBABILITEALEATOIRE = 0.0;

    public Choice faireUnChoix(List<Tour> historique, int joueurRemplace, double probabiliteAleatoire) {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(this.getSeed());

        if (historique.isEmpty() || secureRandom.nextDouble() < probabiliteAleatoire) {
            return choixAleatoire(secureRandom);
        }

        Tour dernierTour = historique.get(historique.size() - 1);

        return joueurRemplace == 1
                ? choixPourJoueur(dernierTour.getChoixJoueur1(), dernierTour.getChoixJoueur2())
                : choixPourJoueur(dernierTour.getChoixJoueur2(), dernierTour.getChoixJoueur1());
    }



    private Choice choixAleatoire(SecureRandom secureRandom) {
        return secureRandom.nextBoolean() ? Choice.COOPERER : Choice.TRAHIR;
    }

    private Choice choixPourJoueur(Choice choixPrincipal, Choice choixAdverse) {
        if (choixPrincipal == Choice.TRAHIR && choixAdverse == Choice.COOPERER) {
            return Choice.TRAHIR;
        }
        if (choixPrincipal == Choice.COOPERER && choixAdverse == Choice.COOPERER) {
            return Choice.COOPERER;
        }
        return inverse(choixPrincipal);
    }

    public Choice inverse(Choice choice) {
        return choice == Choice.COOPERER ? Choice.TRAHIR : Choice.COOPERER;
    }
}