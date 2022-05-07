import javax.swing.JPanel;
import java.awt.*;

public class Jeu extends JPanel
{

    private int nombre_tour;
    private int nombre_joueur;
    public Plateau plateau;
    private ZoneJoueur zone_joueur;
    private ZonePion zone_pion;

    public Jeu() 
    {
        super();
        this.setLayout(null);

        this.plateau = new Plateau();
        this.plateau.setBounds(0, 0, 1050, 700);
        this.add(this.plateau);
        

        this.zone_joueur = new ZoneJoueur();
        this.zone_joueur.setBounds(1050, 0, 380, 700);
        this.add(this.zone_joueur);

        this.zone_pion = new ZonePion();
        this.zone_pion.setBounds(0, 700, 1430, 120);
        this.add(this.zone_pion);
    }

    public void jouer(Joueur Joueur) 
    {
        // TODO implement here
    }

    
    public void initialiser_partie() 
    {
        // TODO implement here
    }

    /**
     * 
     */
    public void determiner_nombre_joeur() 
    {
        // TODO implement here
    }

    public int getNombre_tour() {
        return nombre_tour;
    }

    public void setNombre_tour(int nombre_tour) {
        this.nombre_tour = nombre_tour;
    }

    public int getNombre_joueur() {
        return nombre_joueur;
    }

    public void setNombre_joueur(int nombre_joueur) {
        this.nombre_joueur = nombre_joueur;
    }

}