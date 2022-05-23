import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ZoneDe extends JPanel
{
    private De de;
    private ZoneJoueur zoneJoueur;
    public ZoneDe(ZoneJoueur zoneJoueur)
    {
        super();
        this.zoneJoueur = zoneJoueur;
        //this.setBackground(new Color(70,130,180));
        this.setLayout(new FlowLayout());
        this.de = new De();
        this.de.afficherDe(110, 100);

        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setVisible(true);
        this.add(this.de);
    }

    

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Bonjour", 1070, 600);
        g.fillRect(1050, 550, 380, 150);
    }


    public ZoneJoueur getZoneJoueur() {
        return zoneJoueur;
    }
}
