import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class ZoneJoueur extends JPanel
{
    private ZonePseudoJoueur zone_pseudo;
    private ZoneTuileEtPionJoueur zone_tuile_et_pion;
    private ZoneDe zone_de;

    public ZoneJoueur()
    {
        super();

        this.setBackground(new Color(176,196,222));

        this.construireZoneJoueur();
    }
    
    // **************************************    Methodes   *********************************************** //

    private void construireZoneJoueur()
    {
        this.zone_pseudo = new ZonePseudoJoueur(Jeu.list_joueur);
        this.zone_pseudo.setPreferredSize(new Dimension(380, 100));

        this.add(this.zone_pseudo);

        this.zone_tuile_et_pion = new ZoneTuileEtPionJoueur();
        this.zone_tuile_et_pion.setPreferredSize(new Dimension(380, 550));
        this.add(this.zone_tuile_et_pion);

        this.zone_de = new ZoneDe();
        this.zone_de.setPreferredSize(new Dimension(380,150));
        this.add(this.zone_de);
    }

    public void paintComponent(Graphics g)
    {
        zone_tuile_et_pion.repaint();
        zone_pseudo.repaint();
        zone_de.repaint();
    }

    // **************************************    Getters   *********************************************** //

    public ZonePseudoJoueur getZone_pseudo() {
        return zone_pseudo;
    }

    public ZoneTuileEtPionJoueur getZone_tuile_et_pion() {
        return zone_tuile_et_pion;
    }

    public ZoneDe getZoneDe() {
        return zone_de;
    }
}
