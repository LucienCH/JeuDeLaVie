package JeuDeLaVie.Observateur;

import JeuDeLaVie.JeuDeLaVie;

import javax.swing.*;
import java.awt.*;

public class JeuDeLaVieUI extends JPanel implements Observateur {

    private final JeuDeLaVie jeu;


    public JeuDeLaVieUI(JeuDeLaVie jeu){
        this.jeu = jeu;
    }


    @Override
    public void actualise() {
        repaint();
    }

    public void paint(Graphics g){
        super.paint(g);
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if(jeu.getGrilleXY(x, y).estVivante()){
                    g.fillRect(((this.getWidth() * x )/ jeu.getxMax()), ((this.getHeight() * y )/ jeu.getyMax()), (this.getWidth() / jeu.getxMax()), (this.getHeight() / jeu.getyMax()));
                }
            }
        }
    }
}
