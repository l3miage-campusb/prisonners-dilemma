package fr.uga.l3miage.pc.prisonersdilemma.Strategies;

import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StrategyFactoryTest {

    @Test
    void testCreateStrategyAdaptatif() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.ADAPTATIF);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyAdaptatif);
    }

    @Test
    void testCreateStrategyDonnantDonnant() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.DONNANT_DONNANT);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyDonnantDonnant);
    }

    @Test
    void testCreateStrategyDonnantDonnantAleatoire() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.DONNANT_DONNANT_ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyDonnantDonnantAleatoire);
    }

    @Test
    void testCreateStrategyDonnantPourDeuxDonnants() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.DONNANT_POUR_DEUX_DONNANTS);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyDonnantPourDeuxDonnants);
    }

    @Test
    void testCreateStrategyDonnantPourDeuxDonnantsAleatoire() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.DONNANT_POUR_DEUX_DONNANTS_ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyDonnantPourDeuxDonnantsAleatoire);
    }

    @Test
    void testCreateStrategySondeurNaif() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.SONDEUR_NAIF);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategySondeurNaif);
    }

    @Test
    void testCreateStrategyPacificateurNaif() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.PACIFICATEUR_NAIF);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyPacificateurNaif);
    }

    @Test
    void testCreateStrategyVraiPacificateur() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.VRAI_PACIFICATEUR);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyVraiPacificateur);
    }

    @Test
    void testCreateStrategyAleatoire() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyAleatoire);
    }

    @Test
    void testCreateStrategyToujoursTrahir() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.TOUJOURS_TRAHIR);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyToujoursTrahir);
    }

    @Test
    void testCreateStrategyToujoursCooperer() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.TOUJOURS_COOPERER);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyToujoursCooperer);
    }

    @Test
    void testCreateStrategyRancunier() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.RANCUNIER);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyRancunier);
    }

    @Test
    void testCreateStrategyPavlov() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.PAVLOV);
        assertNotNull(strategy);
        assertTrue(strategy instanceof StrategyPavlov);
    }

    @Test
    void testCreateStrategyPavlovAleatoire() {
        IStrategy strategy = StrategyFactory.createStrategy(Strategy.PAVLOV_ALEATOIRE);
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
