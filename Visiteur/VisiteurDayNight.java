package JeuDeLaVie.Visiteur;

import JeuDeLaVie.Cellule.Cellule;
import JeuDeLaVie.Commande.CommandeMeurt;
import JeuDeLaVie.Commande.CommandeVit;
import JeuDeLaVie.JeuDeLaVie;

public class VisiteurDayNight extends Visiteur{
    /**
     * Constructeur de la classe Visiteur
     *
     * @param jeu
     */
    public VisiteurDayNight(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Méthode définissant les règles pour qu'une cellule vivante meurt
     *
     * @param cellule
     */
    @Override
    public void visitCelluleVivante(Cellule cellule) {
        if(!(cellule.nombreVoisinesVivantes(jeu) == 3 || cellule.nombreVoisinesVivantes(jeu) == 4 || cellule.nombreVoisinesVivantes(jeu) == 6 || cellule.nombreVoisinesVivantes(jeu) == 7 || cellule.nombreVoisinesVivantes(jeu) == 8)){
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    /**
     * Méthode définissant les règle pour qu'une cellule puisse naitre.
     *
     * @param cellule
     */
    @Override
    public void visitCelluleMorte(Cellule cellule) {
        if(cellule.nombreVoisinesVivantes(jeu) == 3 || cellule.nombreVoisinesVivantes(jeu) == 6 || cellule.nombreVoisinesVivantes(jeu) == 7 || cellule.nombreVoisinesVivantes(jeu) == 8){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }
}
