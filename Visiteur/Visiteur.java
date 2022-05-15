package JeuDeLaVie.Visiteur;

import JeuDeLaVie.Cellule.Cellule;
import JeuDeLaVie.JeuDeLaVie;

/**
 * Classe qui définit les règles de naissance / mort des cellules.
 */
public abstract class Visiteur {

    /**
     * Instance de JeuDeLaVie.
     */
    protected JeuDeLaVie jeu;

    /**
     * Constructeur de la classe Visiteur
     * @param jeu
     */
    public Visiteur(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    /**
     * Méthode définissant les règles pour qu'une cellule vivante meurt
     * @param cellule
     */
    public abstract void visitCelluleVivante(Cellule cellule);

    /**
     * Méthode définissant les règle pour qu'une cellule puisse naitre.
     * @param cellule
     */
    public abstract void visitCelluleMorte(Cellule cellule);

}
