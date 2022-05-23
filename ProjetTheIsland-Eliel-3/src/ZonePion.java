import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ZonePion extends JPanel
{
    private List<Bateau> liste_bateaux;
    private JButton bt_bateau;
    private JLabel lb_bateau;
    private int nombre_bateau;
    public ZonePion()
    {
        super();
        //this.setBackground(Color.blue);

        this.liste_bateaux = Jeu.list_Bateau;
        this.nombre_bateau = this.liste_bateaux.size();

        init();
    }

    public void init()
    {
        this.liste_bateaux = Bateau.initBateau();

        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints(); 
        c.insets = new Insets(1, 1, 15, 1);
        c.fill = GridBagConstraints.BOTH;

        ImageIcon image_temp = new ImageIcon("image/BA.png");
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
       
        this.bt_bateau = new JButton(image);
        this.bt_bateau.setFocusable(false);
        this.bt_bateau.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) 
            {
                if(nombre_bateau>0)
                {
                    Plateau.bateau_mouse_clicked = liste_bateaux.get(nombre_bateau-1);
                    Bateau.mouse_clicked_origin = true;
                    nombre_bateau -= 1;
                    System.out.println("bateau pri");
                }
               
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
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });

       
       
        c.gridy = 0;
        c.gridx = 0;
        this.add(this.bt_bateau, c);
        

        c.gridy = 0;
        c.gridx = 1;
        
        this.lb_bateau = new JLabel("" + liste_bateaux.size());
        this.add(this.lb_bateau, c);
    }

    public void paintComponent(Graphics g)
    {
        if(this.nombre_bateau!=Jeu.list_Bateau.size())
        {
            this.nombre_bateau = Jeu.list_Bateau.size();
            this.lb_bateau.setText(""+this.nombre_bateau);
        }
    }
    
}
