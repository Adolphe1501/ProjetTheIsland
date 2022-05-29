import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Baleine extends AnimalDeMer 
{
    public Baleine(String id_baleine) 
    {
        super(id_baleine);
    }

    public void afficherAnimalDeMer(Plateau plateau, String nom_fichier, int x, int y, int w, int h)
    {
        super.afficherAnimalDeMer(plateau, "image/BAL.png", x, y,  w, h);
    }

    public static List <Baleine> initBaleine()
    {
        List <Baleine> list = new ArrayList<Baleine>();

        for(int i=0; i<5; i++)
        {
            String id = "" + (i+1);
            list.add(new Baleine(id));
        }

        return list;
    }

    @Override
    public void attaquer() 
    {
        if (this.hexagone!=null && this.hexagone.getTuile() == null) 
        {
            if ((this.hexagone.getBateau() != null) && !this.hexagone.getBateau().getListe_pionJoueur().isEmpty())/* action sur les bateaux */
            {
                List<P_Joueur> list = this.hexagone.getBateau().getListe_pionJoueur();

                for (int i = 0; i < list.size(); i++) 
                {
                    list.get(i).setEst_nageur(true);

                    this.hexagone.ajoutePionJoueur(list.get(i));
                }

                while(!list.isEmpty())
                {
                    list.get(0).setEst_nageur(true); 
                    this.hexagone.ajoutePionJoueur(list.get(0));
                }

                this.hexagone.supprimerBateauDuPlateau();
                this.hexagone.suprimerBateau();
            }
        }
    }
    
    public boolean attaquerEtJouerTuile() 
    {
        if (this.hexagone!=null && this.hexagone.getTuile() == null) 
        {
            if ((this.hexagone.getBateau() != null) && !this.hexagone.getBateau().getListe_pionJoueur().isEmpty())/* action sur les bateaux */
            {
                if(Jeu.action==4)
                {
                    for(int i=0;i<Jeu.list_joueur.size();i++)
                    {
                        if(!Jeu.list_joueur.get(i).list_Treserve.isEmpty())
                        {
                            for(int j=0; j<Jeu.list_joueur.get(i).list_Treserve.size(); j++)
                            {
                                if(Jeu.list_joueur.get(i).list_Treserve.get(j).verso.action.equals("baleine_barre"))
                                {
                                    if(Jeu.list_joueur.get(i).estMajoritaireSurBateau(this.hexagone.getBateau()))
                                    {
                                        int option =JOptionPane.showConfirmDialog(this.hexagone.getPlateau().getJeu(),Jeu.list_joueur.get(i).getPseudo()  + " Voulez-vous uiliser une tuile pour sauver le bateau?", "Jouer tuile", JOptionPane.YES_NO_OPTION);
                                        if(option==JOptionPane.OK_OPTION)
                                        {
                                            this.suprimerDuPlateau(this.hexagone.getPlateau());
                                            this.hexagone.supprimerAnimalDeMer(this);
                                            Jeu.list_joueur.get(i).list_Treserve.remove(j);
                                            return true;
                                        }
                                        else
                                        {
                                            List<P_Joueur> list = this.hexagone.getBateau().getListe_pionJoueur();

                                            for (int k = 0; k < list.size(); k++) 
                                            {
                                                this.hexagone.ajoutePionJoueur(list.get(k));
                                                list.get(k).setEst_nageur(true);
                                            }
                                            this.hexagone.supprimerBateauDuPlateau();
                                            this.hexagone.suprimerBateau();
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    List<P_Joueur> list = this.hexagone.getBateau().getListe_pionJoueur();

                    for (int i = 0; i < list.size(); i++) 
                    {
                        this.hexagone.ajoutePionJoueur(list.get(i));
                        list.get(i).setEst_nageur(true);
                    }

                    this.hexagone.supprimerBateauDuPlateau();
                    this.hexagone.suprimerBateau();
                }
            }
        }

        return false;
    }

    @Override
    public boolean deplacer(Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
        boolean effectuer = false;
        
        if(hexagoneArrivee.getTuile() == null)
        {
            Position posD = hexagoneDepart.getPosition();
            Position posA = hexagoneArrivee.getPosition(); 
            int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();
            
            if ((((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y ==3 || y == -3)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 3 || x == -3)) || ((y < 3 && y >-3) && (x == 3 || x == -3)) || ( (posD.getNumero_ligne()%2==0 && x<3 && x>-3 && x!=0 && y < 3 && y >-3 && y !=0 ) || (posD.getNumero_ligne()%2!=0 && x<4 && x>-4 && x!=0 && y < 4 && y >-4 && y !=0 )))  || (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))  ||  (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 2 || y == -2)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 2 || x == -2)) || ((y < 2 && y > -2) && (x == 2 || x == -2)) ||  (posD.getNumero_ligne()%2==0 && (y == -1 ||  y == 2) && (x== 1 || x == -1)   ) ||   (posD.getNumero_ligne()%2!=0 && (y == -2 ||  y == 1) && (x==1 || x == -1))   )  ) 
            {
                effectuer = true;
            }
            else
            {
                JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), "Baleine : Deplacement 1 a 3 case de mer", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
            }    
        }
        else
        {
            JOptionPane.showMessageDialog(this.hexagone.getPlateau().getJeu(), " Deplacement sur l'ile impossible", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
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