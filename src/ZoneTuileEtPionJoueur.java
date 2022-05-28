import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ZoneTuileEtPionJoueur extends JPanel
{

    private static int index_courant;
    public ZoneTuileEtPionJoueur()
    {
        super();
        index_courant = Jeu.index_joueur;
        this.setBackground(Color.ORANGE);    
        this.setLayout(new GridBagLayout());
        
        affichePionJoueurNonPlace();
    }

    // **************************************    Methodes   *********************************************** //

    private void affichePionJoueurNonPlace()
    {
        if(Jeu.joueur!=null && Jeu.joueur.getList_pion()!=null)
        {

            this.removeAll();

            List <P_Joueur> list_pionJoueur_temp = Jeu.joueur.getList_pion();

            GridBagConstraints c = new GridBagConstraints(); 
            c.insets = new Insets(5, 1, 10, 1);

            for(int i=0; i<list_pionJoueur_temp.size(); i++)
            {
                for(int j = 0; j<2; j++)
                {
                    if(list_pionJoueur_temp.get(i).getHexagone()==null && list_pionJoueur_temp.get(i).getBateau()==null)
                    {
                        int k = 1;

                        c.gridx = j;
                        c.gridy = i;
                       // if(j==1)
                        //{
                            //JLabel j2 = new JLabel("" + list_pionJoueur_temp.get(i).getValeur());
                            //this.add(j2, c);
                        //}
                        //else
                        //{
                           list_pionJoueur_temp.get(i).afficherPionJoueur(1150, 200+(k*40), 30, 30);
                           this.add(list_pionJoueur_temp.get(i), c);
                            k++;
                        //}
                    }
                }
            }
        }
        this.revalidate();
    }

    private void afficheTuileJoueur()
    {
        this.removeAll();

        int k = 0, j=0, w=70, h=60;
        List <TuileTerrain> list_tuile_temp = Jeu.joueur.getList_Treserve();

        GridBagConstraints c = new GridBagConstraints(); 
        c.insets = new Insets(5, 3, 5, 30);
       
        if(list_tuile_temp.size()>7)
        {
            w=70;
            h=60;
        }
        for(int i=0; i<list_tuile_temp.size(); i++)
        {
            if(i==7)
            {
                j = 1;
                k = 0;
            }
            c.gridx = j;
            c.gridy = k;
            Verso verso = list_tuile_temp.get(i).getVerso().clone();
            verso.afficherVerso(w, h);
            this.add(verso, c);        
            k++;
        }

        this.validate();
    }

    // Affiche effet clique de la souris
    private void afficheEffetMouseClicked(Graphics g2D)
    {
        if(P_Joueur.mouse_cliked && P_Joueur.pionJoueur_mouse_clicked!=null && (P_Joueur.pionJoueur_mouse_clicked.getHexagone()==null || P_Joueur.pionJoueur_mouse_clicked.getBateau() ==null))
        {
            Rectangle rect =  P_Joueur.pionJoueur_mouse_clicked.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    // Affiche effet mouvevement de la souris
    private void afficheEffetMouseMoved(Graphics g2D)
    {
        if(P_Joueur.mouse_moved && P_Joueur.pionJoueur_mouse_moved!=null && (P_Joueur.pionJoueur_mouse_moved.getHexagone()==null || P_Joueur.pionJoueur_mouse_moved.getBateau()==null) ) 
        {
            Rectangle rect =  P_Joueur.pionJoueur_mouse_moved.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }

    }
    

    public void paintComponent(Graphics g)
    {
        Graphics g2D = (Graphics2D)g;

        g2D.setColor(Color.black);
        g2D.fillRect(1070, 150, 360, 500);

        afficheEffetMouseMoved(g2D);
        afficheEffetMouseClicked(g2D);

        if(!Jeu.premier_placement)
        {
            afficheTuileJoueur();
        }
        else
        {
            if(index_courant!=Jeu.index_joueur)
            {
                this.setBackground(Color.ORANGE);    
                g2D.setColor(Color.black);
                g2D.fillRect(1070, 150, 360, 500);
                index_courant = Jeu.index_joueur;
                affichePionJoueurNonPlace();
            }   
        }
    }
}