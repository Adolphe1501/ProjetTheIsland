import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class ZoneMenu extends JPanel
{
    private ZoneJoueur zone_joueur;
    private JButton bt_nouveau;
    private JButton bt_rejouer;
    private JButton bt_quitter;
    public ZoneMenu(ZoneJoueur zoneJoueur)
    {
        super();

        this.zone_joueur = zoneJoueur;

        constructionZoneMenu();

        setBackground(new Color(100,149,237));
    } 

    private void constructionZoneMenu()
    {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //c.insets = new Insets(1, 1, 1, 1);
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        this.bt_nouveau = new JButton();
        this.bt_nouveau.setFont(new Font("Serif", Font.BOLD,20));
        this.bt_nouveau.setText("Nouveau");
        this.add(this.bt_nouveau, c);

        c.gridx = 1;
        c.weightx = 0.5;
        this.bt_rejouer = new JButton();
        this.bt_rejouer.setFont(new Font("Serif", Font.BOLD,20));
        this.bt_rejouer.setText("Rejouer");
        this.bt_rejouer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int option =JOptionPane.showConfirmDialog(null, "Voulez-vous rejouer ?", "Rejouer", JOptionPane.YES_NO_OPTION);
                if(option==JOptionPane.OK_OPTION)
                {
                    zone_joueur.getJeu().constructionJeu();
                }
            }
        });
        this.add(this.bt_rejouer, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        this.bt_quitter = new JButton();
        this.bt_quitter.setFont(new Font("Serif", Font.BOLD,20));
        this.bt_quitter.setText("Quitter");
        this.bt_quitter.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int option =JOptionPane.showConfirmDialog(null, "Voulez-vous quitter The Island ?", "Quitter", JOptionPane.YES_NO_OPTION);
                if(option==JOptionPane.OK_OPTION)
                {
                    zone_joueur.getJeu().getApp().dispose();
                    System.exit(0);
                }
                
            }
            
        });
        this.add(this.bt_quitter, c);

        
    }
}
