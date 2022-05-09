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
    public ZoneTuileEtPionJoueur()
    {
        super();


        this.setBackground(Color.ORANGE);    
        affichePionJoueurNonPlace();
        
    }

    // **************************************    Methodes   *********************************************** //

    public void affichePionJoueurNonPlace()
    {
        if(Jeu.joueur!=null)
        {
            this.removeAll();

            List <P_Joueur> list_pionJoueur_temp = Jeu.joueur.getList_pion();

    
            this.setLayout(new GridBagLayout());

            GridBagConstraints c = new GridBagConstraints(); 

            c.insets = new Insets(5, 10, 10, 10);
            System.out.println("ici ****");

            for(int i=0; i<list_pionJoueur_temp.size(); i++)
            {
                for(int j = 0; j<2; j++)
                {
                    if(list_pionJoueur_temp.get(i).getHexagone()==null && list_pionJoueur_temp.get(i).getBateau()==null)
                    {
                        System.out.println("ici **** " + i);

                        int k = 1;
                        c.ipadx = 5;
                        c.ipady = 5;
                        c.gridx = j;
                        c.gridy = i;
                        if(j==1)
                        {
                            JLabel j2 = new JLabel("" + list_pionJoueur_temp.get(i).getValeur());
                            this.add(j2, c);
                        }
                        else
                        {
                            list_pionJoueur_temp.get(i).afficherPionJoueur(1150, 200+(k*40), 30, 30);
                            //list_pionJoueur_temp.get(i).setBackground(Color.black);
                            //list_pionJoueur_temp.get(i).setOpaque(true);
                            this.add(list_pionJoueur_temp.get(i), c);
                            k++;
                        }
                    }
                }
            }
        }
        this.revalidate();
    }

    // Affiche effet clique de la souris
    private void afficheEffetMouseClicked(Graphics g2D)
    {
        if(P_Joueur.mouse_cliked && Plateau.pionJoueur_mouse_clicked!=null && (Plateau.pionJoueur_mouse_clicked.getHexagone()==null || Plateau.pionJoueur_mouse_clicked.getBateau() ==null))
        {
            Rectangle rect =  Plateau.pionJoueur_mouse_clicked.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    // Affiche effet mouvevement de la souris
    private void afficheEffetMouseMoved(Graphics g2D)
    {
        if(P_Joueur.mouse_moved && Plateau.pionJoueur_mouse_moved!=null && (Plateau.pionJoueur_mouse_moved.getHexagone()==null || Plateau.pionJoueur_mouse_moved.getBateau()==null) ) 
        {
            Rectangle rect =  Plateau.pionJoueur_mouse_moved.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }

    }
    
    public void paintComponent(Graphics g)
    {
        Graphics g2D = (Graphics2D)g;

        afficheEffetMouseMoved(g2D);
        afficheEffetMouseClicked(g2D);
    }
}