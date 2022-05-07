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

        if(this.getHexagone()!=null && this.getHexagone().getListe_joueur().isEmpty() && this.getHexagone().getListe_animaux().isEmpty())
        {
            h=20;
        }

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
            
            //liste_pionJoueur.get(0).afficherPionJoueur(plateau, x+8, y-6, 18, 18);
            //liste_pionJoueur.get(1).afficherPionJoueur(plateau, x+26, y-6, 18, 18);
            //liste_pionJoueur.get(2).afficherPionJoueur(plateau, x+44, y-6, 18, 18);

        }
        plateau.add(this);
    }

    public void ajoutePionJoueur(P_Joueur pionJoueur)
    {
        this.liste_pionJoueur.add(pionJoueur);
        pionJoueur.setBateau(this);

    }

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

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if(!Bateau.mouse_clicked_destination && !Bateau.mouse_clicked_origin)
        {
            Plateau.bateau_mouse_clicked = this;
            Bateau.mouse_clicked_origin = true;
            System.out.println("clique sur bateau");
        }
        else if(Bateau.mouse_clicked_destination && P_Joueur.mouse_cliked)
        {
            this.ajoutePionJoueur(Plateau.pionJoueur_mouse_clicked);
            Plateau.pionJoueur_mouse_clicked.getHexagone().supprimePionjoueur(Plateau.pionJoueur_mouse_clicked);
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
    public void mouseEntered(MouseEvent e) 
    {
        Bateau.mouse_moved = true;
        Plateau.bateau_mouse_moved = this;
        System.out.println("Entrée souris sur Bateau");
   
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        Bateau.mouse_moved = false;
        Plateau.bateau_mouse_moved = null; 
        System.out.println("Sortie souris sur Bateau");
  
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