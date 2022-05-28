import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Serpent extends AnimalDeMer 
{

    protected final String id_serpent;

    public Serpent(String id_serpent) 
    {
        super(id_serpent);
        this.id_serpent = id_serpent;
    }

    public static List<Serpent> initSerpent()
    {
        List<Serpent> list = new ArrayList<>();
       
        for(int i=0; i<5; i++)
        {
            String id = "" +(i+1) ;
            list.add(new Serpent(id));
        }

        return list;
    }

    public void afficherAnimalDeMer(Plateau plateau, String nom_fichier, int x, int y, int w, int h)
    {
        super.afficherAnimalDeMer(plateau, "image/SE.png", x, y,  w, h);
    }

    @Override
    public void attaquer() 
    {
        if (this.hexagone!=null && this.hexagone.getTuile() == null)/* une tuile mer */
        {
            if (this.hexagone.getBateau() != null && !this.hexagone.getBateau().getListe_pionJoueur().isEmpty())/* y un bateau et non vide */
            {
                this.hexagone.getBateau().supprimerListePJoueurDuPlateau();
                this.hexagone.getBateau().getListe_pionJoueur().clear();
                this.hexagone.supprimerBateauDuPlateau();
                this.hexagone.suprimerBateau();
            }
            if (hexagone.getListe_joueur() != null)/* si existe des nageurs */
            {
                this.hexagone.supprimerListePJoueurDuPlateau();
                this.hexagone.getListe_joueur().clear();
            }
        } 
    }

    @Override
    public boolean deplacer(Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
        boolean effectuer = false ;


        Position posD = hexagoneDepart.getPosition();
        Position posA = hexagoneArrivee.getPosition(); 
        int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();

        if(hexagoneArrivee.getTuile() == null)
        {
            if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))                 
            {
                /*
                if((!hexagoneArrivee.getListe_joueur().isEmpty()) || (hexagoneArrivee.getBateau()!=null && !hexagoneArrivee.getBateau().getListe_pionJoueur().isEmpty()))
                {
                    JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Serpent : Fin deplacement, Pion(s) joueur(s) et|ou bateau rencontré(s)", "Fin Deplacement", JOptionPane.INFORMATION_MESSAGE);
                }
                */
                effectuer =true ;
            }
            else
            {
                JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Serpent : Deplacement 1 case de mer", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Deplacement impossible hors de mer", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
        }
        return effectuer;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        if(Jeu.action==1 || Jeu.action==4)
        {
            AnimalDeMer.animal_mouse_clicked = this;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        AnimalDeMer.animal_mouse_moved = this;
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        AnimalDeMer.animal_mouse_moved = null;
    }
}