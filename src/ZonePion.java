import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private List<Requin> liste_requin;
    private JButton bt_requin;
    private JLabel lb_requin;
    private int nombre_requin;
    private List<Baleine> liste_baleine;
    private JButton bt_baleine;
    private JLabel lb_baleine;
    private int nombre_baleine;


    public ZonePion()
    {
        super();
        //this.setBackground(Color.blue);

        this.liste_bateaux = Jeu.list_Bateau;
        this.liste_requin = Jeu.list_requin;
        this.liste_baleine = Jeu.list_baleine;
        this.nombre_bateau = this.liste_bateaux.size();
        this.nombre_requin = this.liste_requin.size();
        this.nombre_baleine = this.liste_baleine.size();

        init();
    }

    public void init()
    {
        //this.liste_bateaux = Bateau.initBateau();

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
        this.bt_bateau.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nombre_bateau>0)
                {
                    Bateau.bateau_mouse_clicked = liste_bateaux.get(nombre_bateau-1);
                    Bateau.mouse_clicked_origin = true;
                    nombre_bateau -= 1;
                    System.out.println("bateau pri");
                }
            }

        });
        c.gridy = 0;
        c.gridx = 0;
        this.add(this.bt_bateau, c);

        c.gridy = 0;
        c.gridx = 1;
        this.lb_bateau = new JLabel("" + liste_bateaux.size());
        this.add(this.lb_bateau, c);

        image_temp = new ImageIcon("image/RE.png");
        imgScale = image_temp.getImage();
        icon = imgScale.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        image = new ImageIcon(icon);

        c.insets.set(1, 60, 15, 1);
        this.bt_requin= new JButton(image);
        this.bt_requin.setFocusable(false);
        this.bt_requin.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nombre_requin>0)
                {
                    nombre_requin -= 1;
                    System.out.println("requin pri");
                }
            }

        });
        c.gridy = 0;
        c.gridx = 2;
        this.add(this.bt_requin, c);

        c.insets.set(1, 1, 15, 1);
        c.gridy = 0;
        c.gridx = 3;
        this.lb_requin = new JLabel("" + liste_requin.size());
        this.add(this.lb_requin, c);

        image_temp = new ImageIcon("image/BAL.png");
        imgScale = image_temp.getImage();
        icon = imgScale.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        image = new ImageIcon(icon);

        c.insets.set(1, 60, 15, 1);
        this.bt_baleine = new JButton(image);
        this.bt_baleine.setFocusable(false);
        this.bt_baleine.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nombre_baleine>0)
                {
                    nombre_baleine -= 1;
                    System.out.println("requin pri");
                }
            }

        });
        c.gridy = 0;
        c.gridx = 4;
        this.add(this.bt_baleine, c);

        c.insets.set(1, 1, 15, 1);
        c.gridy = 0;
        c.gridx = 5;
        this.lb_baleine = new JLabel("" + liste_baleine.size());
        this.add(this.lb_baleine, c);



    }

    public void paintComponent(Graphics g)
    {
        if(this.nombre_bateau!=Jeu.list_Bateau.size())
        {
            this.nombre_bateau = Jeu.list_Bateau.size();
            this.lb_bateau.setText(""+this.nombre_bateau);
        }
        if( this.nombre_baleine!=Jeu.list_baleine.size())
        {
            this.nombre_baleine = Jeu.list_baleine.size();
            this.lb_baleine.setText(""+this.nombre_baleine);
        }
        if(this.nombre_requin != Jeu.list_requin.size())
        {
            this.nombre_requin = Jeu.list_requin.size();
            this.lb_requin.setText(""+this.nombre_requin);
        }
    }

    
}
