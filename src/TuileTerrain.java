import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;



public abstract class TuileTerrain extends JLabel
{

    protected final Verso verso;
    protected final String id;
    public Hexagone hexagone;

    public TuileTerrain(Verso verso, String id) 
    {
        this.verso = verso;
        this.hexagone = null;
        this.id = id;
    }


    // **************************************    Methodes   *********************************************** //

    // Initialisation Tuiles
    public static TuileTerrain[] initTuileTerrains()
    {
        TuileTerrain[] tuilesTerrain = new TuileTerrain[41];

        int i = 0, j=0;
        String id_plage,id_foret,id_montagne;
        Verso verso = new Verso(null, null) ;
        Verso [] versosPlage = verso.initVersosplage();
        Verso [] versosForet = verso.initVersosForet();
        Verso [] versosMontagne = verso.initVersosMontagne();

       // on enregistre les tuiles plages
        for(i=0; i<16; i++){

            j = i+1;
            id_plage =("P"+j);

            if(i<3)
                verso = versosPlage[0] ; 
            if(i<6 && i>2)
                verso = versosPlage[1] ;                           
            if(i==6)
                verso = versosPlage[2] ; 
                      
            if(i<9 && i>6)
                verso = versosPlage[3] ; 
                          
            if(i<12 && i>8)
                verso = versosPlage[4] ; 

            if(i==12)
                verso = versosPlage[5] ; 

            if(i== 13)
                verso = versosPlage[6] ; 
            
                          
            if(i== 14)
                verso = versosPlage[7] ; 
                          
            if(i== 15)
                verso = versosPlage[8] ; 
            tuilesTerrain[i] = new Plage(id_plage, verso);
        }

        // ensuite on enregistre les tuiles foret
        for(i=0; i<16; i++)
        {

            j = i+1;
            id_foret =("F"+j);

            if(i<2)
                verso = versosForet[0] ; 
            if(i<4 && i>1)
                verso = versosForet[1] ;                           
            if(i>3 && i<7)
                verso = versosForet[2] ; 
                      
            if(i<9 && i>6)
                verso = versosForet[3] ; 
                          
            if(i==9)
                verso = versosForet[4] ; 

            if(i==10)
                verso = versosForet[5] ; 

            if(i== 11)
                verso = versosForet[6] ; 
            
                          
            if(i== 12)
                verso = versosForet[7] ; 
                          
            if(i== 13)
                verso = versosForet[8] ; 
            
            if(i>13 && i<16)
                verso = versosForet[9] ;
                
            // On fait + 16 pour faire suite aux tuiles de plages
            tuilesTerrain[i+16] = new Foret(id_foret, verso);
        }

        // On enregistre enfin les tuiles montagnes
        for(i=0; i<8; i++)
        {

            j = i+1;
          
            id_montagne =("M"+j);

            if(i==0)
                verso = versosMontagne[0] ; 
            if(i<5 && i>0)
                verso = versosMontagne[1] ;                           
            if(i==5)
                verso = versosMontagne[2] ; 
                      
            if(i==6)
                verso = versosMontagne[3] ; 
                          
            if(i==7)
                verso = versosMontagne[4] ;

            tuilesTerrain[i+32] = new Montagne(id_montagne, verso);
        }

        return tuilesTerrain;
    }

    // Affiche les tuiles terrain sur le plateau
    public void afficherTuileTerrain(Graphics g2D, String nom_fichier)
    {
        try 
        {
            int xPoints[] , yPoints[];
            Image image = ImageIO.read(new File(nom_fichier));

            xPoints = this.hexagone.xpoints;
            yPoints = this.hexagone.ypoints;
            
            g2D.drawImage(image, xPoints[0], yPoints[1], 80, 65, null);
                    
        }
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("Erreur Fichier Tuile Terrain non trouve");
        }
    }

    // Melange le tableau de tuile terrain
    public static TuileTerrain[] melangeTabTuileTerrains()
    {
        TuileTerrain[] tuilesTerrain = initTuileTerrains();
        
        Random random = new Random();
    
        for(int i = 39; i>=0; i--)
        {
            int j = random.nextInt(i+1);
    
            TuileTerrain TT = tuilesTerrain[i];
            tuilesTerrain[i] = tuilesTerrain[j];
            tuilesTerrain[j] = TT;
        }
        return tuilesTerrain;
    }

    public void effetImmediatVerso()
    {
        int i, j;
        if(this.getVerso().couleur.equals("vert"))
        {
            if(this.getVerso().action.equals("tourbillon"))
            {
                System.out.println("je rentre");
                i = this.hexagone.getPosition().getNumero_ligne();
                j = this.hexagone.getPosition().getNumero_colone();
                if(Plateau.map[i][j+1]!=null && Plateau.map[i][j+1].getTuile()==null)
                {
                    System.out.println("1");

                    Plateau.map[i][j+1].supprimerListePJoueurDuPlateau();
                    Plateau.map[i][j+1].supprimerListeAnimalDeMerDuPlateau();
                    Plateau.map[i][j+1].supprimerBateauDuPlateau();
                    Plateau.map[i][j+1].supprimerListeAnimalDeMer();
                    Plateau.map[i][j+1].supprimerListePJoueur();
                    Plateau.map[i][j+1].suprimerBateau();
                }
                if(Plateau.map[i][j-1]!=null && Plateau.map[i][j-1].getTuile()==null)
                {
                    System.out.println("2");
                    Plateau.map[i][j-1].supprimerListePJoueurDuPlateau();
                    Plateau.map[i][j-1].supprimerListeAnimalDeMerDuPlateau();
                    Plateau.map[i][j-1].supprimerBateauDuPlateau();
                    Plateau.map[i][j-1].supprimerListeAnimalDeMer();
                    Plateau.map[i][j-1].supprimerListePJoueur();
                    Plateau.map[i][j-1].suprimerBateau();
                }
                if(Plateau.map[i+1][j]!=null && Plateau.map[i+1][j].getTuile()==null)
                {
                    System.out.println("3*");

                    Plateau.map[i+1][j].supprimerListePJoueurDuPlateau();
                    Plateau.map[i+1][j].supprimerListeAnimalDeMerDuPlateau();
                    Plateau.map[i+1][j].supprimerBateauDuPlateau();
                    Plateau.map[i+1][j].supprimerListeAnimalDeMer();
                    Plateau.map[i+1][j].supprimerListePJoueur();
                    Plateau.map[i+1][j].suprimerBateau();

                }
                if(Plateau.map[i-1][j]!=null && Plateau.map[i-1][j].getTuile()==null)
                {
                    System.out.println("4");
                    Plateau.map[i-1][j].supprimerListePJoueurDuPlateau();
                    Plateau.map[i-1][j].supprimerListeAnimalDeMerDuPlateau();
                    Plateau.map[i-1][j].supprimerBateauDuPlateau();
                    Plateau.map[i-1][j].supprimerListeAnimalDeMer();
                    Plateau.map[i-1][j].supprimerListePJoueur();
                    Plateau.map[i-1][j].suprimerBateau();
                }
                if(i%2==0 && Plateau.map[i-1][j+1]!=null && Plateau.map[i-1][j+1].getTuile()==null)
                {
                    System.out.println("5");

                    Plateau.map[i-1][j+1].supprimerListePJoueurDuPlateau();
                    Plateau.map[i-1][j+1].supprimerListeAnimalDeMerDuPlateau();
                    Plateau.map[i-1][j+1].supprimerBateauDuPlateau();;
                    Plateau.map[i-1][j+1].supprimerListeAnimalDeMer();
                    Plateau.map[i-1][j+1].supprimerListePJoueur();
                    Plateau.map[i-1][j+1].suprimerBateau();
                }
                if(i%2==0 && Plateau.map[i+1][j+1]!=null && Plateau.map[i+1][j+1].getTuile()==null)
                {
                    System.out.println("6");

                    Plateau.map[i+1][j+1].supprimerListePJoueurDuPlateau();
                    Plateau.map[i+1][j+1].supprimerListeAnimalDeMerDuPlateau();
                    Plateau.map[i+1][j+1].supprimerBateauDuPlateau();
                    Plateau.map[i+1][j+1].supprimerListeAnimalDeMer();
                    Plateau.map[i+1][j+1].supprimerListePJoueur();
                    Plateau.map[i+1][j+1].suprimerBateau();
                }
                if(i%2!=0 && Plateau.map[i-1][j-1]!=null && Plateau.map[i-1][j-1].getTuile()==null)
                {
                    System.out.println("7");

                    Plateau.map[i-1][j-1].supprimerListePJoueurDuPlateau();
                    Plateau.map[i-1][j-1].supprimerListeAnimalDeMerDuPlateau();
                    Plateau.map[i-1][j-1].supprimerBateauDuPlateau();
                    Plateau.map[i-1][j-1].supprimerListeAnimalDeMer();
                    Plateau.map[i-1][j-1].supprimerListePJoueur();
                    Plateau.map[i-1][j-1].suprimerBateau();
                }
                if(i%2!=0 && Plateau.map[i+1][j-1]!=null && Plateau.map[i+1][j-1].getTuile()==null)
                {
                    System.out.println("8");
                    Plateau.map[i+1][j-1].supprimerListePJoueurDuPlateau();
                    Plateau.map[i+1][j-1].supprimerListeAnimalDeMerDuPlateau();
                    Plateau.map[i+1][j-1].supprimerBateauDuPlateau();
                    Plateau.map[i+1][j-1].supprimerListeAnimalDeMer();
                    Plateau.map[i+1][j-1].supprimerListePJoueur();
                    Plateau.map[i+1][j-1].suprimerBateau();

                }
            }
            else if(this.getVerso().action.equals("bateau"))
            {
                if(!Jeu.list_Bateau.isEmpty())
                {
                    System.out.println("Action bateau");
                    this.getHexagone().ajouterBateau(Jeu.list_Bateau.get(0));
                    
                    if(!this.getHexagone().getListe_joueur().isEmpty())
                    {  
                        for(int l=0; l<3; l++)
                        {
                            if(l<this.getHexagone().getListe_joueur().size())
                            {
                                Jeu.list_Bateau.get(0).ajoutePionJoueur(this.getHexagone().getListe_joueur().get(l));
                                this.getHexagone().supprimePionjoueur(this.getHexagone().getListe_joueur().get(l));
                            }
                        }
                    }   
                    Jeu.list_Bateau.remove(0);
               }
            }
            else if(this.getVerso().action.equals("requin") && !Jeu.list_requin.isEmpty())
            {
                this.getHexagone().ajouterAnimalDeMer(Jeu.list_requin.get(0));
                Jeu.list_requin.remove(0);
            }
            else if(this.getVerso().action.equals("baleine") && !Jeu.list_baleine.isEmpty())
            {
                this.getHexagone().ajouterAnimalDeMer(Jeu.list_baleine.get(0));
                Jeu.list_baleine.remove(0);
            }
        }
    }
  



    public abstract void afficherCaracteristiques();


  
    // **************************************    Getters   *********************************************** //

    public String getId() 
    {
        return id;
    }

    public Verso getVerso() 
    {
        return verso;
    }

    public Hexagone getHexagone() {
        return hexagone;
    }


    // **************************************    Setters   *********************************************** //

    public void setHexagone(Hexagone hexagone) {
        this.hexagone = hexagone;
    }
}
