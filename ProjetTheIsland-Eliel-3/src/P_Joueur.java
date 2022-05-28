import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class P_Joueur extends JLabel implements MouseListener
{

    public static boolean mouse_moved = false;
    public static boolean mouse_cliked = false;
    public static boolean descendre_bateau = false;

    protected final String id_P_joueur;
    public final String couleur;
    private final int valeur;
    protected Boolean est_nageur;
    public final Joueur joueur;
    private Hexagone hexagone;
    private Bateau bateau;

    // **************************************    Constructeur   *********************************************** //

    public P_Joueur(String id_P_joueur, String couleur, int valeur, Joueur joueur) 
    {
        super();
        this.id_P_joueur = id_P_joueur;
        this.couleur = couleur;
        this.valeur = valeur;
        this.joueur = joueur;
        this.est_nageur = false;
        this.hexagone = null;
        this.addMouseListener(this);
    }

    // **************************************    Methodes   *********************************************** //

    // Affiche le pion joueur
    public void afficherPionJoueur(int x, int y, int w, int h)
    {
        afficherPionJoueur_aux(x, y, w, h);
    }

    // Affiche le pion joueur sur le bateau
    public void afficherPionJoueur(Plateau plateau, int x, int y, int w, int h)
    {
        afficherPionJoueur_aux(x, y, w, h);
        this.setBounds(x, y, w, h);
        plateau.add(this);
    }

    private void afficherPionJoueur_aux(int x, int y, int w, int h)
    {
        String nom_fichier = determineNomFichier();
        ImageIcon image_temp = new ImageIcon(nom_fichier);
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
        this.setIcon(image);   
    }

    private String determineNomFichier()
    {
        String nom_fichier;

        if(this.couleur.equals("vert"))
        {
            nom_fichier = "image/PJV.png";
        }
        else if(this.couleur.equals("rouge"))
        {
            nom_fichier = "image/PJR.png";
        }
        else if(this.couleur.equals("bleu"))
        {
            nom_fichier = "image/PJB.png";
        }
        else
        {
            nom_fichier = "image/PJJ.png";
        }

        return nom_fichier;
    }
    public void afficherCaracteristiques()
    {
        System.out.println("Couleur : " + this.couleur + " ,id : " + this.id_P_joueur);
    }
   
    public boolean placer_pion(Hexagone hexagone) 
    {
        if ((Jeu.joueur!= null && (this.joueur.getPseudo()==Jeu.joueur.getPseudo())  && (hexagone.getListe_joueur().size()==0) && (hexagone.getZone_ile() == true) && !hexagone.getCentrePlateau())) 
        {
            System.out.println("ca marche !!!");
            hexagone.ajoutePionJoueur(this);
            return true;
        }
        else
        { 
            System.out.println("ca marche pas !!!");
            System.out.println("Pseudo : " + this.joueur.getPseudo() + " = " + Jeu.joueur.getPseudo());
            System.out.println("Taille : " + hexagone.getListe_joueur().size());
            System.out.println("Zone ile : " + hexagone.getZone_ile());
            return false;
        }
    }

    public boolean rechercherDansPionJouer()
    {
        boolean trouver = false ;

        if (this.joueur.list_pion_jouer != null)
        {
            for(P_Joueur PionJ : this.joueur.list_pion_jouer)
            {
                if ( PionJ == this)
                    trouver = true ;
            }
        }

        return trouver;
    }




    public boolean deplacerPionJoueur(Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {

        boolean deplacement = false;
        
        if(this.joueur.getNombre_deplacement()>0)
        {
            Position posD = hexagoneDepart.getPosition();
            Position posA = hexagoneArrivee.getPosition(); 
            int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();
            if(this.est_nageur)
            {
                if( this.rechercherDansPionJouer() == false)
                {
                    if ( hexagoneArrivee.getTuile() == null)
                    {
                        if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))                 
                        {
                            this.deplacerPj(hexagoneDepart, hexagoneArrivee);
                            this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement() - 1);
                            deplacement = true;
                            this.joueur.list_pion_jouer.add(this);

                        }else{
                            System.out.println("le pion est nageur le deplacement maximum est de 1");
                        }
                    }else{
                        System.out.println("le pion est nageur le deplacement vers l'ile est impossible");
                    }

                }else
                    System.out.println(" Deplacement impossible le pion est nageur le deplacement a deja ete effectue durant ce tour");
                    
            }else{
                if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))                 
                {
                    this.deplacerPj(hexagoneDepart, hexagoneArrivee);
                    this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement() - 1);
                    deplacement= true;
                    if ( hexagoneArrivee.getTuile() == null)
                        this.est_nageur = true;
                    
                }else{

                    if(this.joueur.getNombre_deplacement()== 1)
                        System.out.println(" le nombre de depalcement maximum est de 1");
                    if(this.joueur.getNombre_deplacement()>1)
                    {
                        if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 2 || y == -2)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 2 || x == -2)) || ((y < 2 && y > -2) && (x == 2 || x == -2)) ||  (posD.getNumero_ligne()%2==0 && (y == -1 ||  y == 2) && (x== 1 || x == -1)   ) ||   (posD.getNumero_ligne()%2!=0 && (y == -2 ||  y == 1) && (x==1 || x == -1))   )            
                        {
                            if(hexagoneArrivee.getTuile() != null)
                            {
                                this.deplacerPj(hexagoneDepart, hexagoneArrivee);
                                this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement() - 2);
                                deplacement= true;
                                if ( hexagoneArrivee.getTuile() == null)
                                    this.est_nageur = true;
                            
                            }else
                                System.out.println ( " deplacement de 2 impossible vers une case de mer ");
    
                        }else{
                            if(this.joueur.getNombre_deplacement()>2)
                            {
                            if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y ==3 || y == -3)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 3 || x == -3)) || (((posD.getNumero_ligne()%2 == 0 && y <4  && y >-4) || (posD.getNumero_ligne()%2 != 0 && y <2  && y >-3)) && (x == 3 || x == -3))|| (((posD.getNumero_ligne()%2 == 0 && y <3  && y >-3) || (posD.getNumero_ligne()%2 != 0 && y <3  && y >-3)) && (x == 2 || x == -2)) || (((posD.getNumero_ligne()%2 == 0 && y <4  && y >-3) || (posD.getNumero_ligne()%2 != 0 && y <3  && y >-4)) && (x == 1 || x == -1)))                 
                            {
                                    if(hexagoneArrivee.getTuile() != null)
                                    {
                                        this.deplacerPj(hexagoneDepart, hexagoneArrivee);
                                        this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement() - 3);
                                        deplacement= true;
                                        if ( hexagoneArrivee.getTuile() == null)
                                            this.est_nageur = true;
            
                                    }else
                                        System.out.println ( " deplacement de 3 impossible vers une case de mer ");
                          
                                }else{
                                    System.out.println("Deplacement impossible !!!!");
                                }
                            }else{
                                System.out.println("le nombre de deplacement minimum pour effectuer le deplacement est de 3");
                            }
                        }
                    }else{
                        System.out.println("le nombre de deplacement minimum pour effectuer le deplacement est de 2");
                    }
                }
            }
        }else{
            System.out.println("Nombre de deplacement insuffisant");
        }

        return deplacement;
    }    
   
    
   
    public boolean deplacerPionJoueurVersBateau(Bateau bateau)
    {
        boolean deplacement = false;

        int i =0;
        if (bateau.getListe_pionJoueur() != null)
        {
            for(P_Joueur Pj : bateau.getListe_pionJoueur())
            {
                if( Pj != null)
                 i +=   1 ;
            }
             
        }

        if(i <3)
        { 
            if(this.bateau != null)
            {
                Position posD = this.bateau.getHexagone().getPosition();
                Position posA = bateau.getHexagone().getPosition(); 
                int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();
        
                if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))
                {
                    this.bateau.supprimerPionjoueur(this);
                    deplacement = deplacerPjsurBat(bateau);          
                }  
            }else
            {
                if (this.hexagone == bateau.getHexagone())
                    deplacement = deplacerPjsurBat(bateau);   
            }

        }

        return deplacement ;
    }
    public static List<P_Joueur> initPJoueur(String couleur, Joueur joueur) 
    {
        int valeur;
        List<P_Joueur> list = new ArrayList<>();
        int i;
        for (i = 0; i < 10; i++) 
        {
            if(i<=2)
            {
                valeur = 1;
            }
            else if(i==3 || i==4)
            {
                valeur=2;
            }
            else if(i==5 || i==6)
            {
                valeur =3;
            }
            else
            {
                valeur = 6;
            }

            String id = "PJ" + (i+1);
            list.add(new P_Joueur(id, couleur, valeur, joueur));
        }
        return list;
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {  
        Plateau.pionJoueur_mouse_clicked = this;
        P_Joueur.mouse_cliked = true;
        

        // Si le joueur n'est pas encore sur le plateau
        if(this.hexagone==null && this.bateau==null)
        {
            Jeu.premier_placement = true;
        }
        // Si le pion joeur se trouve sur le plateau
        else if(this.bateau!=null)
        {
            System.out.println("je demande a descendre");
            P_Joueur.descendre_bateau = true;
        }
        // Si le pion joueur veux monter sur un bateau
        else
        {
            System.out.println("je veux monter sur un bateau ou me deplacer");
            Bateau.mouse_clicked_destination = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {   
        Plateau.pionJoueur_mouse_moved = this;
        mouse_moved = true;
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        Plateau.pionJoueur_mouse_moved = null;
        mouse_moved = false;
    }

    // **************************************    Getteurs   *********************************************** //

    public String getId_P_joueur() 
    {
        return id_P_joueur;
    }

    public String getCouleur() 
    {
        return couleur;
    }

    public int getValeur() 
    {
        return valeur;
    }

    public Boolean getEst_nageur() 
    {
        return est_nageur;
    }

    public Joueur getJoueur() 
    {
        return joueur;
    }

    public Hexagone getHexagone() {
        return hexagone;
    }

    public Bateau getBateau() {
        return bateau;
    }
    // **************************************    Setteurs   *********************************************** //

    public void setEst_nageur(Boolean est_nageur) 
    {
        this.est_nageur = est_nageur;
    }

    public void setHexagone(Hexagone hexagone) {
        this.hexagone = hexagone;
    }

    public void setBateau(Bateau bateau) {
        this.bateau = bateau;
    }

    public boolean deplacerPj(Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
             
        ArrayList<P_Joueur> list =  (ArrayList<P_Joueur>) hexagoneDepart.getListe_joueur();
        list.remove(this);
        hexagoneDepart.setListe_joueur(list);

        list = (ArrayList<P_Joueur>) hexagoneArrivee.getListe_joueur();
        list.add(this);
        hexagoneArrivee.setListe_joueur(list);

        this.hexagone = hexagoneArrivee;
        return true;
    }

    public boolean deplacerPjsurBat(Bateau bat)
    {
                     
        ArrayList<P_Joueur> list =  (ArrayList<P_Joueur>) this.hexagone.getListe_joueur();
        list.remove(this);
        this.hexagone.setListe_joueur(list);

        bat.ajoutePionJoueur(this);

        return true ;

    }
}