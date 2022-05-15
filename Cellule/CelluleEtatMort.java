package JeuDeLaVie.Cellule;

import JeuDeLaVie.Visiteur.Visiteur;

/**
 * Classe CelluleEtatMort qui implémente CelluleEtat.
 */
public class CelluleEtatMort implements CelluleEtat{


    /**
     * variable d'instance de la classe JeuDeLaVie.Cellule.CelluleEtatMort
     */
    private static CelluleEtatMort celluleInstance = null;

    /**
     * Constructeur de la classe JeuDeLaVie.Cellule.CelluleEtatMort
     */
    private CelluleEtatMort(){
        super();
    }

    /**
     * getCelluleInstance retourne une instance de celluleEtatMort de façon synchronisé pour eviter de créer deux
     * fois la variable d'insance JeuDeLaVie.Cellule.CelluleEtatMort
     * @return celluleInstance
     */
    public static synchronized CelluleEtatMort getCelluleInstance(){
        if(celluleInstance == null){
            celluleInstance = new CelluleEtatMort();
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
        return CelluleEtatVivant.getCelluleInstance();
    }

    /**
     * meurt() défini l'état de la cellue tel que mort.
     *
     * @return celluleEtat
     */
    @Override
    public CelluleEtat meurt() {
        return this;
    }

    /**
     * estVivante() booléen qui indique si la cellule est vivante ou non.
     *
     * @return booleen.
     */
    @Override
    public boolean estVivante() {
        return false;
    }

    /**
     * Méthode qui permet d'accepter un visiteur
     *
     * @param visiteur
     * @param cellule
     */
    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visitCelluleMorte(cellule);
    }
}
