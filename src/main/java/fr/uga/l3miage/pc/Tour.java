package fr.uga.l3miage.pc;

public class Tour {
    static int compteur = 0;
    int numero;
    Choice choixJoueur1;
    Choice choixJoueur2;

    public Tour(Choice choix1,Choice choix2){
        this.choixJoueur1 = choix1;
        this.choixJoueur2 = choix2;
        compteur++;
        this.numero = compteur;
    }
}
