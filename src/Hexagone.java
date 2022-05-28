
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Hexagone extends Polygon
{
    private Plateau plateau;
    private Bateau bateau;
    private List<P_Joueur> liste_joueur;
    private List<AnimalDeMer> liste_animaux;
    private List<AnimalDeMer> liste_animaux_reserve;
    private List<Baleine> liste_baleine;
    private List<Requin> liste_requin;
    private List<Serpent> liste_serpent;
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
        this.liste_animaux_reserve = new ArrayList<AnimalDeMer>();
        this.liste_baleine = new ArrayList<Baleine>();
        this.liste_requin = new ArrayList<Requin>();
        this.liste_serpent = new ArrayList<Serpent>();
        this.liste_joueur = new ArrayList<P_Joueur>();
        this.setBateau(null);
        this.zone_ile = false;
        this.tuile = null;
        this.centrePlateau = false;
        this.detruire_tuile = false;

        this.contientZoneIle();
        this.determineHexagoneCentrePlateau();
        this.placerTuileTerrain(Jeu.tuile_a_placer[Jeu.compteur_tuile_terrain]);
    }

   

   // **************************************    Methodes   *********************************************** //

   // Affiche l'hexagone sur le plateau
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
        if(this.liste_animaux.isEmpty())
        {
            this.afficherPion();
        }
        else
        {
            this.afficherListeAnimalDeMer();
        }
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
            Jeu.compteur_tuile_terrain++;
        }
    }

    public void placerSerpentDeMer(int i, int j, Serpent serpent)
    {
        if((i==6 && j==5) || (i==1 && j==1) || (i==2 && j==10) || (i==10 && j==0) || (i==11 && j==10))
        {
            ajouterAnimalDeMer(serpent);
            Jeu.list_serpent.remove(0);
        }
    }

    // Detruit une tuile de terrain present sur un hexagone
    public void detruireTuileTerrain()
    {
        if(this.tuile!= null)
        {
            this.detruire_tuile = true;
            this.plateau.repaint();
            
        }
    }

    //Prends en compte l'affichage quand il ya des annimaux de mer
    public void afficherListeAnimalDeMer()
    {
        AnimalDeMer.splitListAnimalDeMer(this.liste_animaux, this.liste_baleine, this.liste_serpent, this.liste_requin);

        Rectangle rect =  this.getBounds();
        double w = rect.getWidth() ;
        double h = rect.getHeight();
        double x = rect.getX();
        double y = rect.getY();
        int k = 0;

        // Prends en compte l'affichage quand l'hegone contient un bateau et des animaux de mer
        if(this.bateau!=null)
        {
            w = 50;
            h = 20;
            x = rect.getX() + 15;
            y = rect.getY() + 8;

            this.bateau.afficherBateau(this.plateau, (int)x, (int)y, (int)w, (int)h);   

            y = rect.getY() + 30;
            for(int i =0; i<this.liste_animaux.size(); i++)
            {
                if(i<this.liste_animaux.size())
                {
                    if(liste_animaux.size()==1)
                    {
                        x = rect.getX() + 10 + (i*25);
                        w = 50;
                        h = 25;
                    }
                    else if(liste_animaux.size()==2)
                    {
                        x = rect.getX() + 5 + (i*35);
                        w = 30;
                        h = 25;
                    }
                    else if(liste_animaux.size()==3)
                    {
                        x = rect.getX() + 5 + (i*25);
                        w = 25;
                        h = 25;
                    }

                    this.liste_animaux.get(i).afficherAnimalDeMer(this.plateau, null, (int)x, (int)y, (int)w, (int)h);
                    this.liste_animaux.get(i).attaquer();

                }
            }

        }
        // Prends en compte l'affichage quand l'hexagone contient des pionJoueur et animaux de mer
        else if(!this.liste_joueur.isEmpty() && this.bateau==null)
        {
            int nombre = this.liste_animaux.size() + liste_joueur.size();
            int l = 0, m = 0;
            for(int i=0; i<nombre; i++)
            {
                w = 25;
                h = 25;

                if(m<this.liste_joueur.size() && m<3)
                {
                    y = rect.getY() + 8;
                    x = rect.getX() + 5 + (m*25);

                    if(this.liste_joueur.size()==1)
                    {
                        w = 35;
                        h = 35;
                        y = rect.getY() + 13;
                        x = rect.getX() + 5;
                    }
                    else if(this.liste_joueur.size()==2)
                    {
                        w = 35;
                        x = rect.getX() + 5 + (m*35);
                    }
                    this.liste_joueur.get(m).afficherPionJoueur(this.plateau, (int)x, (int)y, (int)w, (int)h);
                    m++;
                }
                else if(l<this.liste_animaux.size() && l<3)
                {
                    y = rect.getY() + 31;
                    x = rect.getX() + 5 + (l*25);

                    if(this.liste_animaux.size()==1)
                    {
                        w = 35;
                        h=35;
                        y = rect.getY() + 13;
                        x = rect.getX() + 45;
                    }
                    else if(this.liste_animaux.size()==2)
                    {
                        w = 35;
                        x = rect.getX() + 5 + (l*35);
                    }
                    this.liste_animaux.get(l).afficherAnimalDeMer(this.plateau, null, (int)x, (int)y, (int)w, (int)h);
                    this.liste_animaux.get(l).attaquer();

                    l++;
                }
            }
        }
        // Prends en compte l'affichage l'affichage quand l'hexagone ne contient que des animaux de mer
        else
        {
            for(int i=0; i<this.liste_animaux.size(); i++)
            {
                if(liste_animaux.size()==1)
                {
                    w = 50;
                    h = 50;
                    x = rect.getX() + 15;
                    y = rect.getY() + 8;
                }
                else if(liste_animaux.size()==2)
                {
                    x = rect.getX() +  5 + i*40;
                    y = rect.getY() + 15;
                    w = 30;
                    h = 30;
                }
                else if(liste_animaux.size()==3)
                {
                    x = rect.getX() + 5 + i*25;
                    y = rect.getY() +  15;
                    w = 25;
                    h = 35;
                }
                else if(liste_animaux.size()>3 && liste_animaux.size()<=6)
                {
                    w = 25;
                    h = 25;
                    if(i<3)
                    {
                        x = rect.getX() + 3 + i*25;
                        y = rect.getY() +  10;
                    }
                    else
                    {
                        x = rect.getX() + 2 + k*25;
                        y = rect.getY() + 30;
                        k++;
                    }
                }

                this.liste_animaux.get(i).afficherAnimalDeMer(this.plateau, null, (int)x, (int)y, (int)w, (int)h);
                this.liste_animaux.get(i).attaquer();
            }
        }
    }

    // Affiche les pions bateaux et joueurs present dans l'hexagone
    private void afficherPion()
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



    // Ajoute un pion joueur dans l'hexagone
    public void ajoutePionJoueur(P_Joueur pionJoueur)
    {
        if( pionJoueur.getHexagone()!=null)
        {
            pionJoueur.getHexagone().supprimePionjoueur(pionJoueur);
        }
        pionJoueur.setHexagone(this);
        this.liste_joueur.add(pionJoueur);
    }
  
    // Supprime un pion joueur de l'hexagone
    public void supprimePionjoueur(P_Joueur pionJoueur)
    {
        for(int i=0; i<this.liste_joueur.size(); i++)
        {
            if(this.liste_joueur.get(i).getId_P_joueur().equals(pionJoueur.getId_P_joueur()))
            {
                this.liste_joueur.get(i).setHexagone(null);
                this.liste_joueur.remove(i);
                ajouterAnimalReserve();
            }
        }
    }

    // Ajoute un bateau dans l'hexagone
    public void ajouterBateau(Bateau bateau)
    {
        if( bateau.getHexagone()!=null)
        {
            bateau.getHexagone().suprimerBateau();
        }
        bateau.setHexagone(this);
        this.setBateau(bateau);
    }

    // Supprime un bateau de l'hexagone
    public void suprimerBateau()
    {
        if(this.getBateau()!=null)
        {
            this.getBateau().setHexagone(null);
        }
        System.out.println("bateau retirer");
        this.setBateau(null);
    }

    public void ajouterAnimalDeMer(AnimalDeMer animal)
    {
        if( animal.getHexagone()!=null)
        {
            animal.getHexagone().supprimerAnimalDeMer(animal);
        }
        this.liste_animaux.add(animal);
        animal.setHexagone(this);
    }

    public void supprimerAnimalDeMer(AnimalDeMer animal)
    {
        this.liste_animaux.remove(animal);
        animal.setHexagone(null);
    }
  
    public void sortir_joueur() 
    {
        // TODO implement here
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

    public void supprimerListePJoueur()
    {
        this.liste_joueur.clear();
    }
    public void supprimerListePJoueurDuPlateau()
    {
        for(int i=0; i<this.liste_joueur.size(); i++)
        {
            this.liste_joueur.get(i).suprimerDuPlateau(this.plateau);
        }
    }

    public void supprimerListeAnimalDeMer()
    {
        this.liste_animaux.clear();
    }

    public void supprimerListeAnimalDeMerDuPlateau()
    {
        for(int i=0; i<this.liste_animaux.size(); i++)
        {
            this.liste_animaux.get(i).suprimerDuPlateau(this.plateau);
        }
    }

    public void supprimerBateauDuPlateau()
    {
        if(this.bateau!=null)
        {
            this.bateau.suprimerDuPlateau(this.plateau);
        }
    }

    public boolean verifieHexagonePlein()
    {
        int nombre = this.liste_animaux.size() + this.liste_joueur.size();
        System.out.println("nombre ***" + nombre);
        if(this.bateau==null)
        {
            System.out.println("nombre " + nombre);
            if(nombre==6)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(nombre == 3)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public void ajouterAnimalReserve()
    {
        if(!this.liste_animaux_reserve.isEmpty())
        {
           this.ajouterAnimalDeMer(this.liste_animaux_reserve.get(0));

           this.liste_animaux_reserve.remove(0);
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

    public void setDetruire_tuile(boolean detruire_tuile)
    {
        this.detruire_tuile = detruire_tuile;
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

    public Plateau getPlateau() {
        return plateau;
    }

    public List<AnimalDeMer> getListe_animaux_reserve()
    {
        return liste_animaux_reserve;
    }


    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

}