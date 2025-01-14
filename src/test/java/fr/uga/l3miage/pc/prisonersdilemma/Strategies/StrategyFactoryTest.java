package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import contract.CommonStrategy;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StrategyFactoryTest {

    @Test
    void testCreateStrategyAdaptatif() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.ADAPTATIF);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyAdaptatif);
    }

    @Test
    void testCreateStrategyDonnantDonnant() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.DONNANT_DONNANT);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyDonnantDonnant);
    }

    @Test
    void testCreateStrategyDonnantDonnantAleatoire() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.DONNANT_DONNANT_ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyDonnantDonnantAleatoire);
    }

    @Test
    void testCreateStrategyDonnantPourDeuxDonnants() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.DONNANT_POUR_DEUX_DONNANTS);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyDonnantPourDeuxDonnants);
    }

    @Test
    void testCreateStrategyDonnantPourDeuxDonnantsAleatoire() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.DONNANT_POUR_DEUX_DONNANTS_ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyDonnantPourDeuxDonnantsAleatoire);
    }

    @Test
    void testCreateStrategySondeurNaif() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.SONDEUR_NAIF);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategySondeurNaif);
    }

    @Test
    void testCreateStrategyPacificateurNaif() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.PACIFICATEUR_NAIF);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyPacificateurNaif);
    }

    @Test
    void testCreateStrategyVraiPacificateur() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.VRAI_PACIFICATEUR);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyVraiPacificateur);
    }

    @Test
    void testCreateStrategyAleatoire() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyAleatoire);
    }

    @Test
    void testCreateStrategyToujoursTrahir() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.TOUJOURS_TRAHIR);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyToujoursTrahir);
    }

    @Test
    void testCreateStrategyToujoursCooperer() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.TOUJOURS_COOPERER);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyToujoursCooperer);
    }

    @Test
    void testCreateStrategyRancunier() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.RANCUNIER);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyRancunier);
    }

    @Test
    void testCreateStrategyPavlov() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.PAVLOV);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyPavlov);
    }

    @Test
    void testCreateStrategyPavlovAleatoire() {
        CommonStrategy strategy = StrategyFactory.createStrategy(Strategy.PAVLOV_ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyPavlovAleatoire);
    }

//    @Test
//    void testCreateUnknownStrategyThrowsException() {
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            StrategyFactory.createStrategy(null); // Passer une stratégie inconnue
//        });
//        assertEquals("Stratégie inconnue : null", exception.getMessage());
//    }
}
