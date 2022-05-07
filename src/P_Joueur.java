import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class P_Joueur extends JLabel implements MouseListener {

    protected final String id_P_joueur;
    public final String couleur;
    private final int valeur;
    protected Boolean est_nageur;
    public final Joueur joueur;
    private Hexagone hexagone;

    public P_Joueur(String id_P_joueur, String couleur, int valeur, Joueur joueur) {
        super();
        this.id_P_joueur = id_P_joueur;
        this.couleur = couleur;
        this.valeur = valeur;
        this.joueur = joueur;
        this.est_nageur = false;
        this.hexagone = null;
        this.addMouseListener(this);
    }

    public void afficherPionJoueur(Plateau plateau, int x, int y, int w, int h) {
        ImageIcon image_temp = new ImageIcon("image/PJ.png");
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
        this.setIcon(image);
        this.setBounds(x, y, w, h);
        plateau.add(this);
    }

    public void defendre() {
        // TODO implement here
    }

    /**
     * 
     */
    public void attaquer() {
        // TODO implement here
    }

    public String getId_P_joueur() {
        return id_P_joueur;
    }

    public String getCouleur() {
        return couleur;
    }

    public int getValeur() {
        return valeur;
    }

    public Boolean getEst_nageur() {
        return est_nageur;
    }

    public void setEst_nageur(Boolean est_nageur) {
        this.est_nageur = est_nageur;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("jai clique sur le pion");

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public Hexagone getHexagone() {
        return hexagone;
    }

    public void setHexagone(Hexagone hexagone) {
        this.hexagone = hexagone;
    }

    public boolean placer_pion(Hexagone hexagone) {
        if ((this.id_P_joueur.toCharArray()[2] == this.joueur.couleur.toCharArray()[0])
                && (hexagone.getListe_joueur() == null) && (hexagone.getzone_ile() == true)) {
            hexagone.setlistJoueur(this);
            this.setHexagone(hexagone);
            return true;
        }

        else
            return false;
    }

    /* on vas pas tester l adjacence des hexagone */
    public boolean deplacer_pion(Joueur j, Hexagone hexagone) {
        if (this.joueur == j) {
            if (hexagone.getTuile() == null) {
                if (this.est_nageur) {
                    if (hexagone == this.hexagone) {
                        if (!(hexagone.getBateau().est_complet())) {
                            hexagone.getBateau().add_pion(this);
                            return true;
                        } else
                            return false;

                    } else {
                        hexagone.add_pion(this);
                        return true;
                    }

                } else {
                    this.est_nageur = true;
                    hexagone.add_pion(this);
                    return true;

                }
            }

            else {
                if (this.est_nageur == true) {
                    return false;
                } else {
                    hexagone.add_pion(this);
                    return true;
                }
            }
        } else
            return false;

    }

}