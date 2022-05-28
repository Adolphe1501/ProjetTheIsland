import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bateau extends JLabel implements MouseListener
{
    public static boolean mouse_moved = false;
    public static boolean mouse_clicked_origin = false;
    public static boolean mouse_clicked_destination = false;

    private final String id_bateau;
    private List<P_Joueur> liste_pionJoueur;
    private Hexagone hexagone;

    public Bateau(String id_bateau) 
    {
        this.id_bateau = id_bateau;
        this.liste_pionJoueur = new ArrayList<P_Joueur>();
        this.hexagone = null;
        this.addMouseListener(this);
    }

    // **************************************    Methodes   *********************************************** //

    // Initialisation des bateau
    public static List<Bateau> initBateau(){
        List<Bateau> bateaux=new ArrayList<Bateau>();
        for (int i = 0; i < 12; i++) {
            String id = "B" + i;
            Bateau bateau= new Bateau(id);
            bateaux.add(bateau);
        }
        return bateaux;
    }

    // deplacer bateau

    public boolean deplacerPb(Joueur joueur, Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
        boolean deplacer = false;

        Position posD = hexagoneDepart.getPosition();
        Position posA = hexagoneArrivee.getPosition(); 
        int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();
       
        
        if (joueur.getNombre_deplacement() >0)
        {
            if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))                 
            {
                hexagoneDepart.suprimerBateau();
                hexagoneArrivee.ajouterBateau(this);
                deplacer =  true;
                joueur.setNombre_deplacement(joueur.getNombre_deplacement() - 1);
    

            }else{

                if (joueur.getNombre_deplacement() >1)
                {
                    if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 2 || y == -2)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 2 || x == -2)) || ((y < 2 && y > -2) && (x == 2 || x == -2)) ||  (posD.getNumero_ligne()%2==0 && (y == -1 ||  y == 2) && (x== 1 || x == -1)   ) ||   (posD.getNumero_ligne()%2!=0 && (y == -2 ||  y == 1) && (x==1 || x == -1))   )            
                    {
                        hexagoneDepart.suprimerBateau();
                        hexagoneArrivee.ajouterBateau(this);
                        deplacer =  true;
                        joueur.setNombre_deplacement(joueur.getNombre_deplacement() - 2);
            
                    }else{

                        if (joueur.getNombre_deplacement() >2)
                        {
                            if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y ==3 || y == -3)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 3 || x == -3)) || (((posD.getNumero_ligne()%2 == 0 && y <4  && y >-4) || (posD.getNumero_ligne()%2 != 0 && y <2  && y >-3)) && (x == 3 || x == -3))|| (((posD.getNumero_ligne()%2 == 0 && y <3  && y >-3) || (posD.getNumero_ligne()%2 != 0 && y <3  && y >-3)) && (x == 2 || x == -2)) || (((posD.getNumero_ligne()%2 == 0 && y <4  && y >-3) || (posD.getNumero_ligne()%2 != 0 && y <3  && y >-4)) && (x == 1 || x == -1)))                 
                            {
                                hexagoneDepart.suprimerBateau();
                                hexagoneArrivee.ajouterBateau(this);
                                deplacer =  true;
                                joueur.setNombre_deplacement(joueur.getNombre_deplacement() - 3);
                    

                            }else
                                System.out.println("deplacement trop grand");
                        }else
                            System.out.println( " nombre de deplacement insuffisant !!");
                    }

                }else
                    System.out.println( " nombre de deplacement insuffisant !!");
            }
    


            
        }
          

        return  deplacer;
    }
    public boolean deplacerPionBateau(Joueur joueur ,Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
        boolean deplacer = false;

        if (joueur.getNombre_deplacement() > 0)
        {
            if (hexagoneArrivee.getTuile() == null)
            {
                if (this.estVideBateau())
                {
                    deplacer = deplacerPb(joueur, hexagoneDepart, hexagoneArrivee);
                }else{
                    if (joueur.estMajoritaireSurBateau(this) == true)
                    {
                        deplacer = deplacerPb(joueur, hexagoneDepart, hexagoneArrivee);
                    }else
                        System.out.println(" desole vous n'etes pas majoritaire sur le bateau");
                }
    
            }else
                System.out.println ( "  les bateaux ne se deplacent que sur mer :) ");
    
    
        }else
            System.out.println(" Desole vous n'avez plus de deplacement ");

        return deplacer ;
       
    }


    // on test si le bateau possede une liste de pions
    public boolean estVideBateau()
    {
        if (this.liste_pionJoueur == null)
            return true;
        else
            return false;
    }
    //Affiche le bateau sur le plateau
    public void afficherBateau(Plateau plateau, int x, int y, int w, int h)
    {
        ImageIcon image_temp = new ImageIcon("image/BA.png");
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
        this.setIcon(image);
        this.setBounds(x, y, w, h);

        w = 18;
        h = 18;

        // Le bateau est seul dans l'hexagone
        if(this.getHexagone()!=null && this.getHexagone().getListe_joueur().isEmpty() && this.getHexagone().getListe_animaux().isEmpty())
        {
            h=20;
        }

        // Le bateau a des Pions joueur a son bord
        if(!this.liste_pionJoueur.isEmpty())
        {
            for(int i=0; i<this.liste_pionJoueur.size(); i++)
            {
                if(i==0)
                {
                    liste_pionJoueur.get(0).afficherPionJoueur(plateau, x+8, y-6, w, h);
                }
                else if(i==1)
                {
                    liste_pionJoueur.get(1).afficherPionJoueur(plateau, x+26, y-6, w, h);
                }
                else if(i==2)
                {
                    liste_pionJoueur.get(2).afficherPionJoueur(plateau, x+44, y-6, w, h);
                }
            }
        }
        plateau.add(this);
    }

    // Ajoute un pion joueur sur le bateau
    public void ajoutePionJoueur(P_Joueur pionJoueur)
    {
        this.liste_pionJoueur.add(pionJoueur);
        pionJoueur.setBateau(this);

    }

    // Supprime un pion joueur du bateau
    public void supprimerPionjoueur(P_Joueur pionJoueur)
    {
        for(int i=0; i<this.liste_pionJoueur.size(); i++)
        {
            if(this.liste_pionJoueur.get(i).getId_P_joueur().equals(pionJoueur.getId_P_joueur()))
            {
                this.liste_pionJoueur.remove(i);
                pionJoueur.setBateau(null);
                break;
            }
        }
    }
    public void deplacer() 
    {
        // TODO implement here
    }

    public boolean placer_bateau(List<Bateau> bateaux, Hexagone hexagone) 
    {
        int i = hexagone.getPosition().getNumero_ligne();
        int j = hexagone.getPosition().getNumero_colone();

        Boolean possible = false;

        if ((hexagone.getListe_joueur().isEmpty()) && (hexagone.getZone_ile() == false)  && (hexagone.getBateau() == null)) 
        {
            if((j>0) && (j<Plateau.nombre_colonne-1) && ((Plateau.map[i][j+1]!=null) && ((Plateau.map[i][j+1].getTuile()!=null)) || ((Plateau.map[i][j-1]!=null) &&(Plateau.map[i][j-1].getTuile()!=null))))
            {
                this.RemoveBateau(bateaux);
                possible = true;     
                           
            }
            else if(i>0 && (i<Plateau.nombre_ligne-1) && ((Plateau.map[i+1][j]!=null) && ((Plateau.map[i+1][j].getTuile()!=null)) || ((Plateau.map[i-1][j]!=null) &&(Plateau.map[i-1][j].getTuile()!=null))))
            {
                this.RemoveBateau(bateaux);
                possible = true;
            }
            else if((j>0) && (j<Plateau.nombre_colonne-1) && (i>0) && (i<Plateau.nombre_ligne-1))
            {
                if(i%2==0 && ((Plateau.map[i+1][j+1]!=null) &&(Plateau.map[i+1][j+1].getTuile()!=null) || ((Plateau.map[i-1][j+1]!=null) &&Plateau.map[i-1][j+1].getTuile()!=null)))
                {
                    this.RemoveBateau(bateaux);
                    possible = true;
                }
                else if(i%2!=0 && ((Plateau.map[i+1][j-1]!=null) && (Plateau.map[i+1][j-1].getTuile()!=null)) || ((Plateau.map[i-1][j-1]!=null) && Plateau.map[i-1][j-1].getTuile()!=null))
                {
                    this.RemoveBateau(bateaux);
                    possible = true;
                }
            }
        }
        return possible;
    }

    
    public void RemoveBateau(List<Bateau> bateaux) 
    {
        for(int i=0; i<bateaux.size(); i++)
        {
            if(bateaux.get(i).id_bateau.equals(this.id_bateau))
            {
                bateaux.remove(i);
            }
        }    
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        // Determine si un clique a ete effectué sur le bateau
        System.out.println(Bateau.mouse_clicked_origin);
        System.out.println(Bateau.mouse_clicked_destination);

        if(!Bateau.mouse_clicked_origin)
        {
            Plateau.bateau_mouse_clicked = this;
            Bateau.mouse_clicked_origin = true;
            System.out.println("clique sur bateau");
        }
        // Determine si un pion joueur veux monter sur le bateau
        else if(Bateau.mouse_clicked_destination && P_Joueur.mouse_cliked)
        {     
            if(Plateau.pionJoueur_mouse_clicked.getHexagone()!=null)
            {
                this.ajoutePionJoueur(Plateau.pionJoueur_mouse_clicked);
                Plateau.pionJoueur_mouse_clicked.getHexagone().supprimePionjoueur(Plateau.pionJoueur_mouse_clicked);
            }
            P_Joueur.mouse_cliked = false;
            Bateau.mouse_clicked_destination = false;
            System.out.println("Je monte");
        }        
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    // le  curseur de la souris est sur le bateau
    public void mouseEntered(MouseEvent e) 
    {
        Bateau.mouse_moved = true;
        Plateau.bateau_mouse_moved = this;
   
    }

    // Le curseur de la souris quitte le bateau
    @Override
    public void mouseExited(MouseEvent e) 
    {
        Bateau.mouse_moved = false;
        Plateau.bateau_mouse_moved = null;   
    }
    // **************************************    Setters   *********************************************** //

    public void setListe_pionJoueur(List<P_Joueur> liste_pionJoueur) 
    {
        this.liste_pionJoueur = liste_pionJoueur;
    }

    public void setHexagone(Hexagone hexagone) {
        this.hexagone = hexagone;
    }
  
    // **************************************    Getters   *********************************************** //

    public String getId_bateau() 
    {
        return id_bateau;
    }

    public List<P_Joueur> getListe_pionJoueur() 
    {
        return liste_pionJoueur;
    }

    public Hexagone getHexagone() {
        return hexagone;
    }

}