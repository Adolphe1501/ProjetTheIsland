
import java.util.ArrayList;
import java.util.List;
import java.awt.*;



public class Hexagone extends Polygon
{

    private List<Bateau> liste_bateau;
    private List<P_Joueur> liste_joueur;
    private List<AnimalDeMer> liste_animaux;
    private Boolean zone_ile; 

    private int[] xPoints;
    private int[] yPoints;
    private static final int nPoints = 6;

    
    public Hexagone(int[] xPoints, int[] yPoints) 
    {
        super(xPoints, yPoints, nPoints);

        this.liste_animaux = new ArrayList<AnimalDeMer>();
        this.liste_joueur = new ArrayList<P_Joueur>();
        this.liste_bateau = new ArrayList<Bateau>();        
        this.zone_ile = false;
    }

    
    public void detruire_bateau() 
    {
        // TODO implement here
    }
    public void sortir_joueur() 
    {
        // TODO implement here
    }


    public List<Bateau> getListe_bateau() 
    {
        return liste_bateau;
    }


    public void setListe_bateau(List<Bateau> liste_bateau) 
    {
        this.liste_bateau = liste_bateau;
    }


    public List<P_Joueur> getListe_joueur() 
    {
        return liste_joueur;
    }


    public void setListe_joueur(List<P_Joueur> liste_joueur) 
    {
        this.liste_joueur = liste_joueur;
    }


    public List<AnimalDeMer> getListe_animaux() 
    {
        return liste_animaux;
    }


    public void setListe_animaux(List<AnimalDeMer> liste_animaux) 
    {
        this.liste_animaux = liste_animaux;
    }


    public Boolean getZone_ile() {
        return zone_ile;
    }


    public void setZone_ile(Boolean zone_ile) {
        this.zone_ile = zone_ile;
    }


    public int[] getxPoints() {
        return xPoints;
    }


    public void setxPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }


    public int[] getyPoints() {
        return yPoints;
    }


    public void setyPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }


    public static int getNpoints() {
        return nPoints;
    }

}