import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        ImageIcon image_temp = new ImageIcon("image/PJ.png");
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
        this.setIcon(image);   
    }

    public void afficherCaracteristiques()
    {
        System.out.println("Couleur : " + this.couleur + " ,id : " + this.id_P_joueur);
    }
   
    @Override
    public void mouseClicked(MouseEvent e)
    {  
        Plateau.pionJoueur_mouse_clicked = this;
        P_Joueur.mouse_cliked = true;

        // Si le joueur n'est pas encore sur le plateau
        if(this.hexagone==null && this.bateau==null)
        {
            Plateau.premier_placement = true;
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
}