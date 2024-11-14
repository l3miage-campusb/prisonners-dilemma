package fr.uga.l3miage.pc.prisonersdilemma.model;

public class Tour {
    static int compteur = 0;
    int numero;
    private Choice choixJoueur1;
    private Choice choixJoueur2;

    public Tour(Choice choix1,Choice choix2){
        this.choixJoueur1 = choix1;
        this.choixJoueur2 = choix2;
        compteur++;
        this.numero = compteur;
    }

    public Choice getChoixJoueur1() {
        return choixJoueur1;
    }

    public Choice getChoixJoueur2() {
        return choixJoueur2;
    }
}
