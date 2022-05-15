package JeuDeLaVie.Cellule;

import JeuDeLaVie.JeuDeLaVie;
import JeuDeLaVie.Visiteur.Visiteur;

public class Cellule {

    /**
     * Coordonnées des cellules
     */
    private int x;
    private int y;


    private CelluleEtat celluleEtat;

    /**
     * Constructeur de Cellule
     * @param x coordonnée sur l'axe x
     * @param y coordonnée sur l'axe y
     * @param etat état de la cellule
     */
    public Cellule(int x, int y, CelluleEtat etat){
        this.x = x;
        this.y = y;
        this.celluleEtat = etat;
    }

    /**
     * estVivante() retourne un booleen qui annonce l'état de la cellule.
     * @return boolean
     */

    public boolean estVivante(){
        return celluleEtat.estVivante();
    }

    /**
     * vit() affect l'état vivant à la celluleEtat.
     */
    public void vit(){
        celluleEtat = celluleEtat.vit();
    }

    /**
     * meurt affect l'état mort à la celluleEtat.
     */
    public void meurt(){
        celluleEtat = celluleEtat.meurt();
    }

    /**
     * Permet de compter le nombre de voisin vivant
     * @param jeu le jeu avec la grille.
     * @return le nombre de voisin vivant
     */
    public int nombreVoisinesVivantes(JeuDeLaVie jeu){
        int cpt = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(i != 0 || j != 0){
                    if((x + i) >= 0 && (x + i) < jeu.getxMax() && (y + j) >= 0 && (y + j) < jeu.getyMax()){
                        if(jeu.getGrilleXY(x + i, y + j).estVivante()){
                            cpt++;
                        }
                    }
                }
            }
        }
        return cpt;
    }

    public void accept(Visiteur visiteur){
        celluleEtat.accepte(visiteur, this);
    }
}
