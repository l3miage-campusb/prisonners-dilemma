package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;


import contract.CommonStrategy;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.*;

import java.security.SecureRandom;
import java.util.Random;

public class StrategyFactory {
    private static final SecureRandom secureRandom = new SecureRandom();

    private StrategyFactory(){

    }

    public static CommonStrategy createStrategy(Strategy strategy) {
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
            case GRADUEL:
                return new StrategyGraduel();
            case DONNANT_DONNANT_SUPCONNEUX:
                return new StrategyDonnantDonnantSupconneux();
            case RANCUNIER_DOUX:
                return new StrategyRancunierDoux();
            case ADAPTIVE:
                return new Adaptive();
            case ALWAYSBETRAY:
                return new AlwaysBetray();
            case ALWAYSCOOPERATE:
                return new AlwaysCooperate();
            case GRADUALSTRATEGY:
                return new GradualStrategy();
            case NAIVEPEACEMAKER:
                return new NaivePeacemaker(secureRandom);
            case PAVLOV2:
                return new Pavlov();
            case PAVLORANDOM:
                return new PavlovRandom(secureRandom);
            case PEACEMAKER:
                return new Peacemaker(secureRandom);
            case POLLSTERRANDOMBETRAY:
                return new PollsterRandomBetray(secureRandom);
            case RANDOMSTRATEGY:
                return new RandomStrategy(secureRandom);
            case REPENTANTPOLLSTER:
                return new RepentantPollster(secureRandom);
            case RESENTFULSTRATEGY:
                return new ResentfulStrategy();
            case SOFTRESENTFUL:
                return new SoftResentful();
            case TITFORTAT:
                return new TitforTat();
            case TITFORTATRANDOM:
                return new TitforTatRandom(secureRandom);
            case TITFORTATSUSPICIOUS:
                return new TitForTatSuspicious();
            case TITFORTWOTATS:
                return new TitforTwoTats();
            case TITFORTWOTATSRANDOM:
                return new TitforTatRandom(secureRandom);

            default:
                throw new IllegalArgumentException("Stratégie inconnue : " + strategy);
        }
    }
}
