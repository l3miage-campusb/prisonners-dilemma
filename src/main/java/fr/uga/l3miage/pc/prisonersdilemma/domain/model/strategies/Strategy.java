package fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies;

public enum Strategy {

    DONNANT_DONNANT,                //fait,test
    DONNANT_DONNANT_ALEATOIRE,      //fait, teste
    DONNANT_POUR_DEUX_DONNANTS_ALEATOIRE,               //fait,teste
    DONNANT_POUR_DEUX_DONNANTS,         //fait,test
    SONDEUR_NAIF,               //fait,teste
    SONDEUR_REPENTANT,          //fait,teste
    PACIFICATEUR_NAIF,              //fait,test
    VRAI_PACIFICATEUR,              //fait,teste
    ALEATOIRE,                  //fait,teste
    TOUJOURS_TRAHIR,                //fait,teste
    TOUJOURS_COOPERER,          //fait,teste
    RANCUNIER,                  //fait et teste
    PAVLOV,                     //fait et teste
    PAVLOV_ALEATOIRE,           //fait,teste
    ADAPTATIF,                  //fait,teste
    GRADUEL,                    //fait
    DONNANT_DONNANT_SUPCONNEUX,         //fait,teste
    RANCUNIER_DOUX,                      //fait,teste1/2
    ADAPTIVE,
    ALWAYSBETRAY ,
    ALWAYSCOOPERATE,
    GRADUALSTRATEGY ,
    NAIVEPEACEMAKER ,
    PAVLOV2 ,
    PAVLORANDOM ,
    PEACEMAKER ,
    POLLSTERRANDOMBETRAY,
    RANDOMSTRATEGY ,
    REPENTANTPOLLSTER ,
    RESENTFULSTRATEGY ,
    SOFTRESENTFUL,
    TITFORTAT ,
    TITFORTATRANDOM,
    TITFORTATSUSPICIOUS ,
    TITFORTWOTATS ,
    TITFORTWOTATSRANDOM ,
}
