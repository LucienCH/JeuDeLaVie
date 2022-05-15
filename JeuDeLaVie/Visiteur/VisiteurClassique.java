package JeuDeLaVie.Visiteur;


import JeuDeLaVie.Cellule.Cellule;
import JeuDeLaVie.Commande.CommandeMeurt;
import JeuDeLaVie.Commande.CommandeVit;
import JeuDeLaVie.JeuDeLaVie;

public class VisiteurClassique extends Visiteur{


    /**
     * Constructeur de la classe Visiteur
     * @param jeu
     */
    public VisiteurClassique(JeuDeLaVie jeu) {
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
        if(cellule.nombreVoisinesVivantes(jeu) == 3){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }
}
