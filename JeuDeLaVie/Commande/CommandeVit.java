package JeuDeLaVie.Commande;


import JeuDeLaVie.Cellule.Cellule;

public class CommandeVit extends Commande {

    /**
     * Constructeur de la classe Commande
     *
     * @param cellule une cellule.
     */
    public CommandeVit(Cellule cellule) {
        super(cellule);
    }

    /**
     * Permet d'executer la commande.
     */
    @Override
    public void executer() {
        cellule.vit();
    }
}
