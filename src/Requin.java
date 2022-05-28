import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Requin extends AnimalDeMer
{


    public Requin(String id_requin) 
    {
        super(id_requin);
    }

    public void afficherAnimalDeMer(Plateau plateau, String nom_fichier, int x, int y, int w, int h)
    {
        super.afficherAnimalDeMer(plateau, "image/RE.png", x, y,  w, h);
    }

    public static List <Requin> initRequin()
    {
        List <Requin> list = new ArrayList<Requin>();

        for(int i=0; i<6; i++)
        {
            String id = "" + (i+1);
            list.add(new Requin(id));
        }

        return list;
    }
    @Override
    public void attaquer() 
    {
        if (this.hexagone!= null) 
        {
            if (this.hexagone.getListe_joueur() != null) 
            {
                this.hexagone.supprimerListePJoueurDuPlateau();
                this.hexagone.getListe_joueur().clear();
            }
        }        
    }
    @Override
    public boolean deplacer(Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
        boolean effectuer = false;

        if( hexagoneArrivee.getTuile() == null)
        {
            Position posD = hexagoneDepart.getPosition();
            Position posA = hexagoneArrivee.getPosition(); 
            int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();
    
            if ((((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))  ||  (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 2 || y == -2)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 2 || x == -2)) || ((y < 2 && y > -2) && (x == 2 || x == -2)) ||  (posD.getNumero_ligne()%2==0 && (y == -1 ||  y == 2) && (x== 1 || x == -1)   ) ||   (posD.getNumero_ligne()%2!=0 && (y == -2 ||  y == 1) && (x==1 || x == -1))) )            
            {
                /*
                if(!hexagoneArrivee.getListe_joueur().isEmpty())
                {
                    JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Requin : Fin deplacement, Pion(s) joueur(s) rencontré", "Erreur Deplacement", JOptionPane.INFORMATION_MESSAGE);
                }
                */
                effectuer =true;
            }
            else
            {
                JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Requin : Deplacement 1 a 2 case de mer", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Desoler Deplacement uniquement en mer", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
        }
        return effectuer;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
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
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        AnimalDeMer.animal_mouse_moved = this;
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        AnimalDeMer.animal_mouse_moved = null;
        
    }
}