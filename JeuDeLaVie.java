package JeuDeLaVie;

import JeuDeLaVie.Cellule.Cellule;
import JeuDeLaVie.Cellule.CelluleEtatMort;
import JeuDeLaVie.Cellule.CelluleEtatVivant;
import JeuDeLaVie.Commande.Commande;
import JeuDeLaVie.Observateur.Observable;
import JeuDeLaVie.Observateur.Observateur;
import JeuDeLaVie.Visiteur.Visiteur;

import java.beans.Visibility;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class JeuDeLaVie implements Observable {

    /**
     * Coordonnée maximale en X.
     */
    private int xMax;
    /**
     * Coordonnée maximale en Y.
     */
    private int yMax;

    /**
     * Tableau de tableau de cellules.
     */
    private Cellule[][] grille;


    /**
     * Liste d'observateur
     */
    private List<Observateur> observateurs;

    private List<Commande> commandes;


    private Visiteur visiteur;

    /**
     * Constructeur de la classe JeuDeLaVie
     */
    public JeuDeLaVie(int xMax, int yMax){
        observateurs = new ArrayList<Observateur>();
        commandes = new ArrayList<Commande>();
        this.xMax = xMax;
        this.yMax = yMax;
        grille = new Cellule[xMax][yMax];
    }

    /**
     * InitialiseGrille() permet d'initialiser la grille de cellules.
     */
    public void initialiseGrille(){
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                if(Math.random() > 0.5){
                    grille[i][j] = new Cellule(i,j, CelluleEtatVivant.getCelluleInstance());
                }else{
                    grille[i][j] = new Cellule(i,j, CelluleEtatMort.getCelluleInstance());
                }
            }
        }
    }

    /**
     * getGrilleXY permet de retourner la cellule à la position XY de la grille
     * @param x Coordonnée de la cellule
     * @param y Coordonnée de la cellule
     * @return La cellule de la grille à la position XY
     */
    public Cellule getGrilleXY(int x, int y){
        return grille[x][y];
    }


    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }

    /**
     * Permet d'attahcer un observateur
     * @param o l'observateur
     */
    @Override
    public void attacheObservateur(Observateur o) {
        observateurs.add(o);
    }

    /**
     * Permet de dettacher un observateur
     * @param o l'observateur
     */
    @Override
    public void detacheObservateur(Observateur o) {
        observateurs.remove(o);
    }

    /**
     * permet de nottifier tous les observateurs
     */
    @Override
    public void notifieObservateurs() {
        for (Observateur observateur: observateurs) {
            observateur.actualise();
        }
    }

    @Override
    public String toString() {
        return "JeuDeLaVie{" +
                "xMax=" + xMax +
                ", yMax=" + yMax +
                ", grille=" + Arrays.toString(grille) +
                ", observateurs=" + observateurs +
                '}';
    }

    public void ajouteCommande(Commande c){
        commandes.add(c);
    }

    public void executeCommandes(){
        for (Commande commande: commandes) {
            commande.executer();
        }
        commandes.clear();
    }

    public void distribueVisiteur(){
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax ; j++) {
                grille[i][j].accept(visiteur);
            }
        }
    }

    public void calculerGenerationSuivante(){
        distribueVisiteur();
        executeCommandes();
        notifieObservateurs();
    }

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    public Visiteur getVisiteur(){
        return this.visiteur;
    }


    public void viderGrille(){
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                grille[i][j] = new Cellule(i,j, CelluleEtatMort.getCelluleInstance());
            }
        }
    }
}
