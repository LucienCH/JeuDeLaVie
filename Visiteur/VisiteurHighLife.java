package JeuDeLaVie.Visiteur;

import JeuDeLaVie.Cellule.Cellule;
import JeuDeLaVie.Commande.CommandeMeurt;
import JeuDeLaVie.Commande.CommandeVit;
import JeuDeLaVie.JeuDeLaVie;

public class VisiteurHighLife extends Visiteur{

    public VisiteurHighLife(JeuDeLaVie jeu){
        super(jeu);
    }

    /**
     * Méthode définissant les règles pour qu'une cellule vivante meurt
     * @param cellule
     */
    @Override
    public void visitCelluleVivante(Cellule cellule) {
        if(cellule.nombreVoisinesVivantes(jeu) > 3 || cellule.nombreVoisinesVivantes(jeu) < 2){
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    /**
     * Méthode définissant les règle pour qu'une cellule puisse naitre.
     * @param cellule
     */
    @Override
    public void visitCelluleMorte(Cellule cellule) {
        if(cellule.nombreVoisinesVivantes(jeu) == 3 || cellule.nombreVoisinesVivantes(jeu) == 6){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }
}
