package JeuDeLaVie.Commande;

import JeuDeLaVie.Cellule.Cellule;

/**
 * Classe abstraite qui permet de creer des commandes à effectuer.
 */

public abstract class Commande {

    /**
     * Variable de la classe Cellule.
     */
    protected Cellule cellule;

    /**
     * Permet d'executer la commande.
     */
    public abstract void executer();

    /**
     * Constructeur de la classe Commande
     * @param cellule une cellule.
     */
    public Commande(Cellule cellule){
        this.cellule = cellule;
    }
}
