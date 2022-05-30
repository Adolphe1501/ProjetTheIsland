import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class P_Joueur extends JLabel implements MouseListener
{

    public static P_Joueur pionJoueur_mouse_clicked = null;
    public static P_Joueur pionJoueur_mouse_moved = null;
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
        if(this.hexagone==null && this.bateau==null)
        {   
            this.setText(""+this.valeur);
        }
       
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
            //System.out.println("ca marche !!!");
            hexagone.ajoutePionJoueur(this);
            return true;
        }
        else
        { 
            /*
            System.out.println("ca marche pas !!!");
            System.out.println("Pseudo : " + this.joueur.getPseudo() + " = " + Jeu.joueur.getPseudo());
            System.out.println("Taille : " + hexagone.getListe_joueur().size());
            System.out.println("Zone ile : " + hexagone.getZone_ile());
            */
            return false;
        }
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

    public void suprimerDuPlateau(Plateau plateau)
    {
        plateau.remove(this);
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
                            hexagoneArrivee.ajoutePionJoueur(this);
                            this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement() - 1);
                            deplacement = true;
                            this.joueur.list_pion_jouer.add(this);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Nageur : Deplacement maximum 1 par tour", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Nageur : Impossible de retourner sur l'île", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                    }

                }
                else
                    JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Nageur : Deplacement maximum 1 par tour", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);   
            }
            else
            {
                if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))                 
                {
                    hexagoneArrivee.ajoutePionJoueur(this);
                    this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement() - 1);
                    deplacement= true;
                    if ( hexagoneArrivee.getTuile() == null)
                        this.est_nageur = true;
                }
                else
                {
                    if(this.joueur.getNombre_deplacement()== 1)
                       JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Deplacement maximum 1", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);

                    if(this.joueur.getNombre_deplacement()>1)
                    {
                        if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 2 || y == -2)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 2 || x == -2)) || ((y < 2 && y > -2) && (x == 2 || x == -2)) ||  (posD.getNumero_ligne()%2==0 && (y == -1 ||  y == 2) && (x== 1 || x == -1)   ) ||   (posD.getNumero_ligne()%2!=0 && (y == -2 ||  y == 1) && (x==1 || x == -1))   )            
                        {
                            if(hexagoneArrivee.getTuile() != null)
                            {
                                hexagoneArrivee.ajoutePionJoueur(this);
                                this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement() - 2);
                                deplacement= true;
                                if ( hexagoneArrivee.getTuile() == null)
                                    this.est_nageur = true;
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Explorateur : Deplacement de 2 cases impossible vers une case de mer, Allez d'abord dans l'eau", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                        {
                            if(this.joueur.getNombre_deplacement()>2)
                            {
                                if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y ==3 || y == -3)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 3 || x == -3)) || ((y < 3 && y >-3) && (x == 3 || x == -3)) || ( (posD.getNumero_ligne()%2==0 && x<3 && x>-3 && x!=0 && y < 3 && y >-3 && y !=0 ) || (posD.getNumero_ligne()%2!=0 && x<4 && x>-4 && x!=0 && y < 4 && y >-4 && y !=0 )))                 
                                {
                                    if(hexagoneArrivee.getTuile() != null)
                                    {
                                        hexagoneArrivee.ajoutePionJoueur(this);
                                        this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement() - 3);
                                        deplacement= true;
                                        if ( hexagoneArrivee.getTuile() == null)
                                            this.est_nageur = true;
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Explorateur : Deplacement de 3 cases impossible vers une case de mer, Allez d'abord dans l'eau", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Deplacement Impossible", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Point de deplacement insuffisant", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Point de deplacement insuffisant", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Point de deplacement insuffisant", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
        }

        return deplacement;
    }    

    public boolean deplacerPionJoueurVersBateau( Bateau bateau2, Bateau bateau)
    {
        boolean deplacement = false;

        Position posD = bateau2.getHexagone().getPosition();
        Position posA = bateau.getHexagone().getPosition(); 
        int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();

        int i =0;
        for(P_Joueur Pj : bateau.getListe_pionJoueur())
        {
            if( Pj != null)
             i +=   1 ;
        }
         
        if(bateau.getListe_pionJoueur().size()<3)
        { 
            if ((bateau2 != null) && (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) )))
            {
                bateau.ajoutePionJoueur(this);
            }  
            else if(this.hexagone.getPosition().getNumero_ligne() == bateau.getHexagone().getPosition().getNumero_ligne())
            {
                bateau.ajoutePionJoueur(this);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Bateau Plein", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
        }

        return deplacement ;
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
                    bateau.ajoutePionJoueur(this);
                    this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement()-1);
                    deplacement = true;    
                }
                else
                {
                    JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Deplacement impossible", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                }  
            }
            else
            {
                if ((this.hexagone.getPosition().getNumero_ligne() == bateau.getHexagone().getPosition().getNumero_ligne()) && (this.hexagone.getPosition().getNumero_colone() == bateau.getHexagone().getPosition().getNumero_colone()))
                {
                    bateau.ajoutePionJoueur(this);
                    this.joueur.setNombre_deplacement(this.joueur.getNombre_deplacement()-1);
                    deplacement = true;   
                }
                else
                {
                    JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Deplacement impossible", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                }  
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Bateau Plein", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
        }
        return deplacement ;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {  
        if(Jeu.action==1 || Jeu.action==2 || Jeu.premier_placement)
        {
            P_Joueur.pionJoueur_mouse_clicked = this;
            P_Joueur.mouse_cliked = true;
            

            // Si le joueur n'est pas encore sur le plateau
            if(this.hexagone==null && this.bateau==null)
            {
                Jeu.premier_placement = true;
            }
            // Si le pion joeur se trouve sur le plateau et sur un bateeau
            else if(this.bateau!=null)
            {
                System.out.println("je demande a descendre");
                P_Joueur.descendre_bateau = true;

                Bateau.mouse_clicked_origin = false;
                Bateau.bateau_mouse_clicked = null;
            }
            // Si le pion joueur veux monter sur un bateau
            else if(Jeu.action==2)
            {
                System.out.println("je veux monter sur un bateau ou me deplacer");
                Bateau.mouse_clicked_destination = true;

                Bateau.mouse_clicked_origin = false;
                Bateau.bateau_mouse_clicked = null;
            }
        }
    }

    /*
    public void paintComponent(Graphics g)
    {
        /*
        Graphics g2D = (Graphics2D)g;

        if(this.hexagone==null && this.bateau==null)
        {
            g2D.drawString(""+this.valeur, this.getX(), this.getX());
        }
        
    }
    */

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
        P_Joueur.pionJoueur_mouse_moved = this;
        mouse_moved = true;
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        P_Joueur.pionJoueur_mouse_moved = null;
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
}