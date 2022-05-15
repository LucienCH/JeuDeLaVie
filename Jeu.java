package JeuDeLaVie;

import JeuDeLaVie.Observateur.JeuDeLaVieConsole;
import JeuDeLaVie.Observateur.JeuDeLaVieUI;
import JeuDeLaVie.Visiteur.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import static java.lang.Thread.sleep;

/**
 * Classe de base de la création de la fenetre et du jeu de la vie.
 */
public class Jeu extends JFrame implements Runnable{

    /**
     * Instance de la classe JeuDeLaVie.
     */
    private JeuDeLaVie jeu = new JeuDeLaVie(200, 200);

    /**
     * Etat du thread du jeu.
     */
    private boolean estEnPause = true;

    /**
     * Nombre de miliseconde pour actualiser le jeu.
     */
    private int stepper = 200;

    /**
     * Dimension de l'écran de l'utilisateur.
     */
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Liste des visiteurs du jeu de la vie.
     */
    private ArrayList<Visiteur> visiteurs = new ArrayList<>();

    /**
     * Méthode permettant de créer la fenêtre graphique.
     */
    public void demarrer(){

        JeuDeLaVieUI jeuUi = new JeuDeLaVieUI(jeu);
        JeuDeLaVieConsole jeuConsole = new JeuDeLaVieConsole(jeu);
        JPanel jpJeu = new JPanel(new BorderLayout());
        JPanel jpControle = new JPanel(new BorderLayout());
        JPanel jpControleCenter = new JPanel(new BorderLayout());

        JButton stopBtn = new JButton("Pause");
        JButton lancerBtn = new JButton("Lancer");

        JSlider vitSlid = new JSlider(JSlider.HORIZONTAL, 0, 1000,200);

        JButton settButton = new JButton("Paramètres");

        JFrame settFrame = new JFrame("Paramètres");
        JPanel settPan = new JPanel(new BorderLayout());

        JButton settFrameBtn = new JButton("Valider");
        JButton genSuivantBtn = new JButton("Suivant");


        Object[] elements = {"Classique", "High Life", "Day & Night"};

        visiteurs.add(new VisiteurClassique(jeu));
        visiteurs.add(new VisiteurHighLife(jeu));
        visiteurs.add(new VisiteurDayNight(jeu));


        JComboBox visitBox = new JComboBox(elements);

        settPan.add(visitBox, BorderLayout.CENTER);
        settPan.add(settFrameBtn, BorderLayout.SOUTH);
        settFrame.add(settPan);


        visitBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Test = " + visitBox.getSelectedIndex());
                jeu.setVisiteur(visiteurs.get(visitBox.getSelectedIndex()));

            }
        });


        settButton.setVisible(true);
        lancerBtn.setVisible(true);
        lancerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reprendre();
                jpControleCenter.remove(lancerBtn);
                jpControleCenter.add(stopBtn, BorderLayout.CENTER);
                jpControleCenter.revalidate();
                jpControleCenter.repaint();
            }
        });

        stopBtn.setVisible(true);
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause();
                jpControleCenter.remove(stopBtn);
                jpControleCenter.add(lancerBtn, BorderLayout.CENTER);
                jpControleCenter.add(genSuivantBtn, BorderLayout.EAST);
                jpControleCenter.revalidate();
                jpControleCenter.repaint();
            }
        });
        genSuivantBtn.setVisible(true);
        genSuivantBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.calculerGenerationSuivante();
            }
        });



        Hashtable<Integer, JLabel> labelTable = new Hashtable();
        labelTable.put(0, new JLabel("Rapide"));
        labelTable.put(1000, new JLabel("Lent"));


        vitSlid.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                stepper =  (((JSlider)e.getSource()).getValue());
            }
        });

        vitSlid.setLabelTable(labelTable);
        vitSlid.setPaintLabels(true);
        vitSlid.setVisible(true);


        jeu.attacheObservateur(jeuUi);
        jeu.attacheObservateur(jeuConsole);
        jeu.initialiseGrille();



        jpJeu.add(jeuUi, BorderLayout.CENTER);
        jpControle.add(vitSlid, BorderLayout.EAST);
        jpControle.add(settButton,BorderLayout.WEST);
        jpControle.add(jpControleCenter,BorderLayout.CENTER);
        jpControleCenter.add(lancerBtn, BorderLayout.CENTER);
        jpControleCenter.add(genSuivantBtn, BorderLayout.EAST);

        this.setSize(650,650);
        this.add(jpJeu);
        this.add(jpControle, BorderLayout.SOUTH);
        this.setVisible(true);
        int xPos = ((int)dim.getWidth() - this.getWidth()) / 2;
        int yPos = ((int)dim.getHeight() - this.getHeight()) / 2;
        System.out.println("xpos = " + xPos);
        System.out.println("ypos = " + yPos);

        this.setLocation(xPos , yPos);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //System.out.println(jeu.toString());
        jeuUi.repaint();
        jeu.setVisiteur(new VisiteurClassique(jeu));



        settButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause();
                jpControle.remove(stopBtn);
                jpControle.add(lancerBtn, BorderLayout.CENTER);
                settFrame.setSize(300,250);
                settFrame.setVisible(true);
                settFrame.setLocation(((int)dim.getWidth() - settFrame.getWidth()) / 2, ((int)dim.getHeight() - settFrame.getHeight()) /2);
            }
        });

        settFrameBtn.setVisible(true);
        settFrameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settFrame.setVisible(false);
                jeu.initialiseGrille();
                jeuConsole.raz();
                jeuUi.repaint();
                jpControleCenter.remove(stopBtn);
                jpControleCenter.add(lancerBtn, BorderLayout.CENTER);
                jpControleCenter.add(genSuivantBtn, BorderLayout.EAST);
                jpControleCenter.revalidate();
                jpControleCenter.repaint();
            }
        });

    }

    /**
     * Méthode principale
     * @param args
     */
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.demarrer();
        jeu.run();
    }

    /**
     * Méthode permettant de mettre en pause le thread du jeu.
     */
    private void pause(){
        estEnPause = true;
    }

    /**
     * Méthode permettant de reprendre le thread du jeux précédement suspendu.
     */
    private void reprendre(){
        estEnPause = false;
    }

    /**
     * Méthode de la classe Runnable qui lance le thread pour le jeux de la vie.
     */
    @Override
    public void run() {

        while(true){
            try {
                sleep(stepper);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!estEnPause){
                jeu.calculerGenerationSuivante();
            }
        }
    }


}