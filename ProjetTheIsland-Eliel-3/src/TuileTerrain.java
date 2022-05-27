import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

        // verification retirer une tuile
        public static boolean verifierRetirerTuileTerrain( Hexagone Hex )
        {
            boolean retirer =false;
            int nombreP =0 , nombreF = 0;
    
            
            for(int i =0; i<13; i++ )
            {
                for(int j =0 ; j<12 ; j++)
                {
                    if( Plateau.map[i][j].getTuile() instanceof Plage)
                    {
                        if( Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()+1].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() -1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() +1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()-1].getTuile() == null)
                            nombreP += 1 ;
                    }
                    else
                    {
                        if (Plateau.map[i][j].getTuile() instanceof Foret)
                          {
                             if( Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()+1].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() -1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() +1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()-1].getTuile() == null)
                                nombreF += 1 ;
                          }  
                    }
                }
            }
    
            if ( Hex.getTuile() instanceof Plage)
            {
                if( Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()+1].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() -1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() +1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()-1].getTuile() == null)
                    retirer = true;    
            }else{

                if ( Hex.getTuile() instanceof Foret && nombreP == 0)
                {
                    if( Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()+1].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() -1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() +1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()-1].getTuile() == null)
                        retirer = true;
    
                }else{
                    
                    if(Hex.getTuile() instanceof Montagne && nombreP == 0 && nombreF == 0)
                    {
                        if( Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()-1][Hex.getPosition().getNumero_colone()+1].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() -1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()][Hex.getPosition().getNumero_colone() +1 ].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()].getTuile() == null || Plateau.map[Hex.getPosition().getNumero_ligne()+1][Hex.getPosition().getNumero_colone()-1].getTuile() == null)
                            retirer = true;
    
                    }
                }
    
            }
    
    
        
    
            return retirer;
        }
    
        public static boolean retirerTuileTerrain ( Joueur joueur ,Hexagone Hex)
        {
            boolean retirer = false;
    
            if (Hex.getTuile() != null)
            {
                if ( verifierRetirerTuileTerrain(Hex) == true)
                {
                   if ( Hex.getListe_joueur() != null)
                   {
                        for( P_Joueur Pj : Hex.getListe_joueur())
                            Pj.est_nageur = true;
                   }
      
                   joueur.list_Treserve.add(Hex.getTuile());
                   Hex.setTuile(null); 
                   retirer = true;
                }
            }
           
    
            return retirer;
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
