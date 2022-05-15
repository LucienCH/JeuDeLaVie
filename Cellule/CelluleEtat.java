package JeuDeLaVie.Cellule;

import JeuDeLaVie.Visiteur.Visiteur;

public interface CelluleEtat {

    /**
     * vit() défini l'état de la cellule tel que vivant.
     * @return celluleEtat
     */

    public CelluleEtat vit();

    /**
     * meurt() défini l'état de la cellue tel que mort.
     * @return celluleEtat
     */

    public CelluleEtat meurt();

    /**
     * estVivante() booléen qui indique si la cellule est vivante ou non.
     * @return booleen.
     */

    public boolean estVivante();

    /**
     * Méthode qui permet d'accepter un visiteur
     * @param visiteur
     * @param cellule
     */
    public void accepte(Visiteur visiteur, Cellule cellule);
}
