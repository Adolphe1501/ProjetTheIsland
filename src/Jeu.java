import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



public class Jeu extends JPanel
{
    public static int compteur = 0; 
    public static boolean compteur_en_cours = false;
    public static Timer timer = new Timer(1000,  new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Jeu.compteur++;
            Jeu.compteur_en_cours = true;
        }
    });

    public static List<Joueur> list_joueur = new ArrayList<Joueur>();
    public static Joueur joueur;
    private int nombre_tour;
    private int nombre_joueur;
    public Plateau plateau;
    private ZoneJoueur zone_joueur;
    private ZonePion zone_pion;

    // **************************************    Constructeur   *********************************************** //

    public Jeu() 
    {
        super();
        this.setLayout(null);

        Thread chronoEcran = new Thread(new Chrono(this));
        chronoEcran.start();

        this.plateau = new Plateau(this);
        this.plateau.setBounds(0, 0, 1050, 700);
        this.add(this.plateau);
        

        this.zone_joueur = new ZoneJoueur();
        this.zone_joueur.setBounds(1050, 0, 380, 700);
        this.add(this.zone_joueur);

        this.zone_pion = new ZonePion();
        this.zone_pion.setBounds(0, 700, 1430, 120);
        this.add(this.zone_pion);
    }

    // **************************************    Methodes   *********************************************** //

    public void jouer(Joueur Joueur) 
    {
        // TODO implement here
    }

    
    public void initialiser_partie() 
    {
        // TODO implement here
    }

    public void determiner_nombre_joeur() 
    {
        // TODO implement here
    }
      
    // **************************************    Setters   *********************************************** //


    public void setNombre_tour(int nombre_tour) {
        this.nombre_tour = nombre_tour;
    }

    public void setNombre_joueur(int nombre_joueur) {
        this.nombre_joueur = nombre_joueur;
    }

    // **************************************    Getters   *********************************************** //

    public ZoneJoueur getZone_joueur() {
        return zone_joueur;
    }

    public ZonePion getZone_pion() {
        return zone_pion;
    }

    public int getNombre_tour() {
        return nombre_tour;
    }

    public int getNombre_joueur() {
        return nombre_joueur;
    }

}