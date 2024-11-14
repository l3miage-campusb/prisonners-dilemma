package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.*;

public class StrategyFactory {

    public static IStrategy createStrategy(Strategy strategy) {
        switch (strategy) {
            case ADAPTATIF:
                return new StrategyAdaptatif(); // Assurez-vous que la classe existe et implémente IStrategy.
            case DONNANT_DONNANT:
                return new StrategyDonnantDonnant();
            case DONNANT_DONNANT_ALEATOIRE:
                return new StrategyDonnantDonnantAleatoire();
            case DONNANT_POUR_DEUX_DONNANTS:
                return new StrategyDonnantPourDeuxDonnants();
            case DONNANT_POUR_DEUX_DONNANTS_ALEATOIRE:
                return new StrategyDonnantPourDeuxDonnantsAleatoire();
            case SONDEUR_NAIF:
                return new StrategySondeurNaif();
            //case SONDEUR_REPENTANT:
            //    return new StrategySondeurRepentant(); // Assurez-vous que la classe existe et implémente IStrategy.
            case PACIFICATEUR_NAIF:
                return new StrategyPacificateurNaif();
            case VRAI_PACIFICATEUR:
                return new StrategyVraiPacificateur();
            case ALEATOIRE:
                return new StrategyAleatoire();
            case TOUJOURS_TRAHIR:
                return new StrategyToujoursTrahir();
            case TOUJOURS_COOPERER:
                return new StrategyToujoursCooperer();
            case RANCUNIER:
                return new StrategyRancunier(); // Assurez-vous que la classe existe et implémente IStrategy.
            case PAVLOV:
                return new StrategyPavlov();
            case PAVLOV_ALEATOIRE:
                return new StrategyPavlovAleatoire();
//            case GRADUEL:
//                return new StrategyGraduel(); // Assurez-vous que la classe existe et implémente IStrategy.
//            case DONNANT_DONNANT_SUPCONNEUX:
//                return new StrategyDonnantDonnantSuspicieux(); // Assurez-vous que la classe existe et implémente IStrategy.
//            case RANCUNIER_DOUX:
//                return new StrategyRancunierDoux(); // Assurez-vous que la classe existe et implémente IStrategy.
            default:
                throw new IllegalArgumentException("Stratégie inconnue : " + strategy);
        }
    }
}
