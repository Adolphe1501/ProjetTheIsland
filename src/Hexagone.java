
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Hexagone extends Polygon
{
    private static TuileTerrain[] tuile_a_placer = TuileTerrain.melangeTabTuileTerrains();
    private static int compteur_tuile_terrain = 0;

    private Plateau plateau;
    private Bateau bateau;
    private List<P_Joueur> liste_joueur;
    private List<AnimalDeMer> liste_animaux;
    private Boolean zone_ile; 
    private Position position;
    private TuileTerrain tuile;
    private boolean centrePlateau;
    private Boolean detruire_tuile;


    private static final int nPoints = 6;

    
    public Hexagone(int[] xPoints, int[] yPoints, Position position, Plateau plateau) 
    {
        super(xPoints, yPoints, nPoints);
        this.position = position;
        this.plateau = plateau;

        this.liste_animaux = new ArrayList<AnimalDeMer>();
        this.liste_joueur = new ArrayList<P_Joueur>();
        this.setBateau(null);
        this.zone_ile = false;
        this.tuile = null;
        this.centrePlateau = false;
        this.detruire_tuile = false;

        this.contientZoneIle();
        this.determineHexagoneCentrePlateau();
        this.placerTuileTerrain(tuile_a_placer[compteur_tuile_terrain]);
    }

   

   // **************************************    Methodes   *********************************************** //

    public void afficherHexagone(Graphics g2D)
    {
        if(this.zone_ile && !this.centrePlateau)
        {
            g2D.setColor(Color.red);
            if(this.tuile !=null)
            {
                if(!this.detruire_tuile)
                {
                    this.tuile.afficherTuileTerrain(g2D, null);
                }
                else
                {   
                    this.tuile.getVerso().afficherVerso(g2D, this.tuile);
                }
            }
        }
        else
        {
            g2D.setColor(Color.black);

        }
        g2D.drawPolygon(this);
        this.AfficherPion();
    }


    //determine si l'hexagone fait partie de l'ile
    public void contientZoneIle()
    {
        Boolean res = contientZoneIleAux(this.position.getNumero_ligne(), this.position.getNumero_colone());
        if(res)
        {
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
    private void determineHexagoneCentrePlateau()
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
            this.setTuile(tuile);
            tuile.setHexagone(this);
            compteur_tuile_terrain++;
        }
    }

    public void detruireTuileTerrain()
    {
        int i = 0;
        if(this.tuile!= null)
        {
            this.detruire_tuile = true;

            
                this.plateau.repaint();

            /*
                Plateau.time.start();
                System.out.println(Plateau.compteur);
            while(Plateau.time.isRunning())
            {
                System.out.println(" je compte " + Plateau.compteur);

                if(Plateau.compteur==100)
                {
                    Plateau.time.stop();
                    this.tuile = null;
                }
            }
            */

            //try 
            //{
                //Thread.sleep(2000);
                //this.plateau.repaint();
                //Thread.sleep(2000);
               // this.plateau.repaint();

            //} catch (InterruptedException e) {
                // TODO Auto-generated catch block
             //   e.printStackTrace();
            //}

            //this.tuile = null;
            
        }
    }

    public void AfficherPion()
    {
        List<P_Joueur> list_j = this.getListe_joueur();
        int nombrePion = this.liste_joueur.size() +  this.liste_animaux.size();
        int k = 0;

        Rectangle rect =  this.getBounds();
        double w = rect.getWidth() ;
        double h = rect.getHeight();
        double x = rect.getX();
        double y = rect.getY();

        if(this.bateau==null && !list_j.isEmpty() && list_j.size()<=6 && liste_animaux.isEmpty())
        {
            for(int i=0; i<nombrePion; i++)
            {
                if(nombrePion==1)
                {
                    x += 20;
                    y += 12;
                    w = 40;  
                    h = 40;
                }
                else if(nombrePion==2)
                {
                    x = rect.getX() +  5 + i*40;
                    y = rect.getY() + 15;
                    w = 30;
                    h = 30;
                }
                else if(nombrePion==3)
                {
                    x = rect.getX() + 5 + i*25;
                    y = rect.getY() +  15;
                    w = 20;
                    h = 30;
                }
                else if(nombrePion>=3 && nombrePion<=6)
                {
                    w = 20;
                    h = 20;
                    if(i<3)
                    {
                        x = rect.getX() + 5 + i*25;
                        y = rect.getY() +  15;
                    }
                    else
                    {
                        x = rect.getX() + 5 + k*25;
                        y = rect.getY() + 35;
                        k++;
                    }
                }
                list_j.get(i).afficherPionJoueur(this.plateau, (int)x, (int)y, (int)w, (int)h);
            }
        }
        else if(this.bateau != null && list_j.isEmpty() && liste_animaux.isEmpty())
        {
            x = rect.getX() + 4;
            y = rect.getY() + 17;
            w = 75;
            h = 30;
            this.bateau.afficherBateau(this.plateau, (int)x, (int)y, (int)w, (int)h);
        }
        else if(this.bateau != null && !list_j.isEmpty() && list_j.size()<=3 && liste_animaux.isEmpty())
        {
            x = rect.getX() + 3;
            y = rect.getY() + 12;
            w = 75;
            h = 25;
            this.bateau.afficherBateau(this.plateau, (int)x, (int)y, (int)w, (int)h);

            for(int i=0; i<list_j.size(); i++)
            {
                w = 20;
                h = 17;
                x = rect.getX() + 3 + 26;
                y = rect.getY() + 12 + 32;
                if(i==1)
                {
                    x = rect.getX() + 7;
                    y = rect.getY() + 12 + 25;
                }
                else if(i==2)
                {
                    x = rect.getX() + 3 + 48;
                    y = rect.getY() + 12 + 25;
                }
                list_j.get(i).afficherPionJoueur(this.plateau, (int)x, (int)y, (int)w, (int)h);
            }
        }
    }
    public void detruire_bateau() 
    {
        // TODO implement here
    }
  
    public void sortir_joueur() 
    {
    }

    public void ajoutePionJoueur(P_Joueur pionJoueur)
    {
        pionJoueur.setHexagone(this);
        this.liste_joueur.add(pionJoueur);
    }
  
    public void supprimePionjoueur(P_Joueur pionJoueur)
    {
        for(int i=0; i<this.liste_joueur.size(); i++)
        {
            if(this.liste_joueur.get(i).getId_P_joueur().equals(pionJoueur.getId_P_joueur()))
            {
                this.liste_joueur.get(i).setHexagone(null);
                this.liste_joueur.remove(i);
            }
        }
    }

    public void ajouterBateau(Bateau bateau)
    {
        bateau.setHexagone(this);
        this.setBateau(bateau);
    }

    public void suprimerBateau()
    {
        this.getBateau().setHexagone(null);
        this.setBateau(null);
    }

    public void afficherlistePionJoueur()
    {
        if(!this.liste_joueur.isEmpty())
        {
            for(int i=0; i<this.liste_joueur.size(); i++)
            {
                this.liste_joueur.get(i).afficherCaracteristiques();
            }
        }
        else
        {
            System.out.println("Liste des joueurs vide");
        }
    }
    
    // **************************************    Setters   *********************************************** //

    public void setListe_joueur(List<P_Joueur> liste_joueur) 
    {
        this.liste_joueur = liste_joueur;
    }

    public void setListe_animaux(List<AnimalDeMer> liste_animaux) 
    {
        this.liste_animaux = liste_animaux;
    }

    public void setZone_ile(Boolean zone_ile) {
        this.zone_ile = zone_ile;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public void setTuile(TuileTerrain tuile) {
        this.tuile = tuile;
    }

    public void setCentrePlateau(boolean centrePlateau) {
        this.centrePlateau = centrePlateau;
    }

    public void setBateau(Bateau bateau) 
    {
        this.bateau = bateau;
    }
   
    // **************************************    Getters   *********************************************** //
   
    public Bateau getBateau() 
    {
        return bateau;
    }

    public boolean getCentrePlateau() 
    {
        return centrePlateau;
    }

    public TuileTerrain getTuile() 
    {
        return tuile;
    }
    public static int getNpoints() 
    {
        return nPoints;
    }

    public Position getPosition() 
    {
        return position;
    }
   
    public List<AnimalDeMer> getListe_animaux() 
    {
        return liste_animaux;
    }
    public Boolean getZone_ile() 
    {
        return zone_ile;
    }
    public List<P_Joueur> getListe_joueur() 
    {
        return liste_joueur;
    }

}