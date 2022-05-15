package JeuDeLaVie.Cellule;

import JeuDeLaVie.Visiteur.Visiteur;

/**
 * CelluleEtatVivant qui implémente CelluleEtat.
 */
public class CelluleEtatVivant implements CelluleEtat {

    /**
     * variable d'instance de la classe JeuDeLaVie.Cellule.CelluleEtatVivant
     */
    private static CelluleEtatVivant celluleInstance = null;

    /**
     * Constructeur de la classe JeuDeLaVie.Cellule.CelluleEtatVivant
     */
    private CelluleEtatVivant(){
        super();
    }

    /**
     * getCelluleInstance retourne une instance de celluleEtatVivant de façon synchronisé pour eviter de créer deux
     * fois la variable d'insance JeuDeLaVie.Cellule.CelluleEtatVivant
     * @return celluleInstance
     */
    public static synchronized CelluleEtatVivant getCelluleInstance(){
        if(celluleInstance == null){
            celluleInstance = new CelluleEtatVivant();
        }
        return celluleInstance;
    }

    /**
     * vit() défini l'état de la cellule tel que vivant.
     *
     * @return celluleEtat
     */
    @Override
    public CelluleEtat vit() {
        return this;
    }

    /**
     * meurt() défini l'état de la cellue tel que mort.
     * @return celluleEtat
     */
    @Override
    public CelluleEtat meurt() {
        return CelluleEtatMort.getCelluleInstance();
    }

    /**
     * estVivante() booléen qui indique si la cellule est vivante ou non.
     *
     * @return booleen.
     */
    @Override
    public boolean estVivante() {
        return true;
    }

    /**
     * Méthode qui permet d'accepter un visiteur
     * @param visiteur
     * @param cellule
     */
    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visitCelluleVivante(cellule);
    }

}
