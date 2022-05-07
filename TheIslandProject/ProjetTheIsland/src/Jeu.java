import javax.swing.JPanel;
import java.awt.*;

public class Jeu extends JPanel
{

    private int nombre_tour;
    private int nombre_joueur;
    private Plateau plateau;
    private ZoneJoueur zone_joueur;
    private ZonePion zone_pion;

    public Jeu() 
    {
        super();
        this.setLayout(new BorderLayout());

        this.plateau = new Plateau();
        this.plateau.setPreferredSize(new Dimension(1050, 0));

        this.add(plateau, BorderLayout.WEST);

        this.zone_joueur = new ZoneJoueur();
        this.zone_joueur.setPreferredSize(new Dimension(400, 0));
        this.add(zone_joueur, BorderLayout.EAST);

        this.zone_pion = new ZonePion();
        this.zone_pion.setPreferredSize(new Dimension(1500, 100));
        this.add(zone_pion, BorderLayout.SOUTH);

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