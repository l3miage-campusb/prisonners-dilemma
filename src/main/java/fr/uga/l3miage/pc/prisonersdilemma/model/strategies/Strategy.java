package fr.uga.l3miage.pc.prisonersdilemma.model.strategies;

public enum Strategy {

    DONNANT_DONNANT,                //fait
    DONNANT_DONNANT_ALEATOIRE,      //fait
    DONNANT_POUR_DEUX_DONNANTS_ALEATOIRE,               //fait
    DONNANT_POUR_DEUX_DONNANTS,         //fait
    SONDEUR_NAIF,               //fait,teste
    SONDEUR_REPENTANT,
    PACIFICATEUR_NAIF,              //fait
    VRAI_PACIFICATEUR,              //fait,teste
    ALEATOIRE,                  //fait,teste
    TOUJOURS_TRAHIR,                //fait,teste
    TOUJOURS_COOPERER,          //fait,teste
    RANCUNIER,                  //fait et teste
    PAVLOV,                     //fait et teste
    PAVLOV_ALEATOIRE,           //fait
    ADAPTATIF,
    GRADUEL,
    DONNANT_DONNANT_SUPCONNEUX,
    RANCUNIER_DOUX
}
