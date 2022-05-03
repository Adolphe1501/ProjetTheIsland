
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;



public class Hexagone extends Polygon
{
    private static TuileTerrain[] tuile_a_placer = TuileTerrain.melangeTabTuileTerrains();
    private static int compteur_tuile_terrain = 0;

    private List<Bateau> liste_bateau;
    private List<P_Joueur> liste_joueur;
    private List<AnimalDeMer> liste_animaux;
    private Boolean zone_ile; 
    private Position position;
    private TuileTerrain tuile;
    private boolean centrePlateau;

    private int[] xPoints;
    private int[] yPoints;
    private static final int nPoints = 6;

    
    public Hexagone(int[] xPoints, int[] yPoints, Position position) 
    {
        super(xPoints, yPoints, nPoints);

        this.liste_animaux = new ArrayList<AnimalDeMer>();
        this.liste_joueur = new ArrayList<P_Joueur>();
        this.liste_bateau = new ArrayList<Bateau>();        
        this.zone_ile = false;
        this.position = position;
        this.tuile = null;
        this.centrePlateau = false;

        this.contientZoneIle();
        this.determinerCentrePlateau();
        this.placerTuileTerrain(tuile_a_placer[compteur_tuile_terrain]);
    }

    public void afficherHexagone(Graphics g2D)
    {
        if(this.zone_ile && !this.centrePlateau)
        {
            g2D.setColor(Color.red);
            if(tuile !=null)
            {
                this.tuile.afficherTuileTerrain(g2D, null);
            }
        }
        else
        {
            g2D.setColor(Color.black);

        }
        g2D.drawPolygon(this);
    }


    //determine si l'hexagone fait partie de l'ile
    public void contientZoneIle()
    {
        Boolean res = contientZoneIleAux(this.position.getNumero_ligne(), this.position.getNumero_colone());
        if(res)
        {
            //placerTuileTerrain(null);
            this.setZone_ile(true);
        }
    }
    
    private boolean contientZoneIleAux(int i, int j)
    {
        int Ymin = 3, Ymax = 9;
        int Xmin = 4, Xmax = 8;
        boolean ile = false;

        if(i==Ymin || i==Ymax)
        {
            if(j>=Xmin && j<=Xmax-1)
            {
                ile = true;
            }
        }
        else if(i==Ymin+1 || i==Ymax-1)
        {
            if(j>=Xmin-1 && j<=Xmax-1)
            {
                ile = true;
            }
        }
        else if(i==Ymin+2 || i==Ymax-2)
        {
            if(j>=Xmin-2 && j<=Xmax+1)
            {
                ile = true;
            }
        }
        else if(i==6)
        {
            if(j>=Xmin-2 && j<=Xmax)
            {
                ile = true;
            }
        }     
        return ile;
    }


    //Determine l'hexagone qui represente le centre du plateau
    private void determinerCentrePlateau()
    {
        int i = this.position.getNumero_ligne();
        int j = this.position.getNumero_colone();

        if(i==6 && j==5)
        {
            this.setCentrePlateau(true);
        }      
    }

    //Place une tuile dans un hexagone
    public void placerTuileTerrain(TuileTerrain tuile)
    {
        if(this.zone_ile && !this.centrePlateau)
        {
            System.out.println("c :" + compteur_tuile_terrain + " zone ile " + this.zone_ile + " centre plateau " + this.centrePlateau);
            this.setTuile(tuile);
            tuile.setHexagone(this);
            compteur_tuile_terrain++;
        }
    }

    /*
    public void AfficherPion()
    {
        List<P_Joueur> list_j = this.getListe_joueur();
        List<AnimalDeMer> list_A = this.getListe_animaux();
        List<Bateau> list_B = this.liste_bateau;
        int nombrePion = this.liste_joueur.size() +  this.liste_animaux.;
        if()
    }
    */
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TuileTerrain getTuile() {
        return tuile;
    }

    public void setTuile(TuileTerrain tuile) {
        this.tuile = tuile;
    }

    public boolean isCentrePlateau() {
        return centrePlateau;
    }

    public void setCentrePlateau(boolean centrePlateau) {
        this.centrePlateau = centrePlateau;
    }

}