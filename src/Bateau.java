import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bateau extends JLabel {
    protected final String id_bateau;
    protected List<P_Joueur> liste_pionJoueur;

    public Bateau(String id_bateau) {
        this.id_bateau = id_bateau;
        this.liste_pionJoueur = new ArrayList<P_Joueur>();
    }

    public void afficherBateau(Plateau plateau, int x, int y, int w, int h) {
        ImageIcon image_temp = new ImageIcon("image/BA.png");
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
        this.setIcon(image);
        this.setBounds(x, y, w, h);
        plateau.add(this);
    }

    public String getId_bateau() {
        return id_bateau;
    }

    public List<P_Joueur> getListe_pionJoueur() {
        return liste_pionJoueur;
    }

    public void setListe_pionJoueur(List<P_Joueur> liste_pionJoueur) {
        this.liste_pionJoueur = liste_pionJoueur;
    }
    public void add_pion(P_Joueur pion)
    {
        this.liste_pionJoueur.add(pion);
    }
    public boolean placer_bateau(List<Bateau> bateaux, Hexagone hexagone, Plateau plateau) {
        if ((hexagone.getListe_joueur() == null) && (hexagone.getzone_ile() == false)
                && (hexagone.getBateau() == null)) {
            if ((plateau.map[hexagone.getPosition().getNumero_ligne()][hexagone.getPosition().getNumero_colone() + 1]
                    .getTuile() != null)
                    || (plateau.map[hexagone.getPosition().getNumero_ligne()][hexagone.getPosition().getNumero_colone()
                            - 1].getTuile() != null)) {
                this.RemoveBateau(bateaux);
                return true;
            } else
                return false;
        } else
            return false;
    }

    public void RemoveBateau(List<Bateau> bateaux) {
        Iterator success = bateaux.iterator();
        while (success.hasNext()) {
            Bateau elem = (Bateau) success.next();
            if (elem.id_bateau == this.id_bateau) {
                success.remove();
            }
        }
    }
    public boolean est_complet()
    {
        if (this.liste_pionJoueur.size()==3)
            return true;
        else
             return false;
    }

    public boolean deplacer(Joueur j, Hexagone hexagone) {
        if (hexagone.getTuile() == null) 
        {
            if ((this.liste_pionJoueur.size() == 1) && (this.liste_pionJoueur.get(0).couleur == j.couleur))
            {   
                hexagone.setBateau(this);
                return true;
            } 
            else if ((this.liste_pionJoueur.size() == 2) && ((this.liste_pionJoueur.get(0).couleur == j.couleur)
                    || (this.liste_pionJoueur.get(1).couleur == j.couleur)))
            {
                hexagone.setBateau(this);
                return true;
            }

            else if (est_dominant(j, this)) 
            {
                hexagone.setBateau(this);
                return true;
            } 
            else 
            {
                return false;
            }
        }
        else    
        {
            return false;
        }
    }

    public boolean est_dominant(Joueur j, Bateau bateau) {
        int nb = 0;
        for (int i = 0; i < 3; i++) {
            if (bateau.liste_pionJoueur.get(i).couleur == j.couleur) {
                nb++;
            }
        }
        if (nb > 1)
        {                
            return true;
        } else if ((nb == 1) && (bateau.liste_pionJoueur.get(0).couleur != bateau.liste_pionJoueur.get(1).couleur)
                && (bateau.liste_pionJoueur.get(0).couleur != bateau.liste_pionJoueur.get(2).couleur)
                && (bateau.liste_pionJoueur.get(1).couleur != bateau.liste_pionJoueur.get(2).couleur))
            return true;
        else
            return false;

    }
    /*
    public static void main(String[] args) 
    {
        Joueur joueur1 = new Joueur("wafa", "Rouge");
        Joueur joueur2= new Joueur("wafa", "Vert");
        P_Joueur pion = new P_Joueur("rrr", "Rouge", 6, joueur1);
        P_Joueur pion2 = new P_Joueur("rrr", "Vert", 6, joueur1);
        P_Joueur pion3 = new P_Joueur("rrr", "Bleu", 6, joueur1);



        Hexagone hexagone=new Hexagone();
       Bateau b =new Bateau("iff");
        b.add_pion(pion);
        b.add_pion(pion2);
        b.add_pion(pion3);

        System.out.println(b.deplacer(joueur1, hexagone));

    }*/
}