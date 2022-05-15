package JeuDeLaVie.Observateur;

import JeuDeLaVie.JeuDeLaVie;


/**
 *
 */
public class JeuDeLaVieConsole implements Observateur{

    private JeuDeLaVie jeu;

    private int nbGeneration = 0;

    public JeuDeLaVieConsole(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    @Override
    public void actualise() {
        nbGeneration++;
        int nbEnVie = 0;
        int nbMorte = 0;
        System.out.println("Nombre de génération : " + nbGeneration + " ");
        for (int i = 0; i < jeu.getxMax(); i++) {
            for (int j = 0; j < jeu.getyMax(); j++) {
                if(jeu.getGrilleXY(i,j).estVivante()){
                    nbEnVie++;
                }else{
                    nbMorte++;
                }
            }
        }
        System.out.println("Nombre de cellules en vie : " + nbEnVie + " ");
        System.out.println("Nombre de cellules mortes : " + nbMorte + " ");
        System.out.println("Le visiteur est : " + jeu.getVisiteur().toString());

        System.out.println("\n");

    }

    public void raz(){
        nbGeneration = 0;
    }
}
