import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bateau extends JLabel
{
    protected final String id_bateau;
    protected List<P_Joueur> liste_pionJoueur;

    public Bateau(String id_bateau) 
    {
        this.id_bateau = id_bateau;
        this.liste_pionJoueur = new ArrayList<P_Joueur>();
    }

   
    public void afficherBateau(Plateau plateau, int x, int y, int w, int h)
    {
        ImageIcon image_temp = new ImageIcon("image/BA.png");
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
        this.setIcon(image);
        this.setBounds(x, y, w, h);
        
        if(!this.liste_pionJoueur.isEmpty())
        {
            liste_pionJoueur.get(0).afficherPionJoueur(plateau, x+8, y-6, 18, 18);
            liste_pionJoueur.get(1).afficherPionJoueur(plateau, x+26, y-6, 18, 18);
            liste_pionJoueur.get(2).afficherPionJoueur(plateau, x+44, y-6, 18, 18);

        }
        plateau.add(this);

    }

    public void deplacer() 
    {
        // TODO implement here
    }


    public String getId_bateau() 
    {
        return id_bateau;
    }


    public List<P_Joueur> getListe_pionJoueur() 
    {
        return liste_pionJoueur;
    }


    public void setListe_pionJoueur(List<P_Joueur> liste_pionJoueur) 
    {
        this.liste_pionJoueur = liste_pionJoueur;
    }

}