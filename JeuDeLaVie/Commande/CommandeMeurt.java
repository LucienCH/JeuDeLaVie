package JeuDeLaVie.Commande;

import JeuDeLaVie.Cellule.Cellule;

public class CommandeMeurt extends Commande{
    /**
     * Constructeur de la classe Commande
     *
     * @param cellule une cellule.
     */
    public CommandeMeurt(Cellule cellule) {
        super(cellule);
    }

    /**
     * Permet d'executer la commande.
     */
    @Override
    public void executer() {
        cellule.meurt();
    }
}
