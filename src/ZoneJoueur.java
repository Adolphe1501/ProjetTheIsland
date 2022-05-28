import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class ZoneJoueur extends JPanel
{
    private ZonePseudoJoueur zone_pseudo;
    private ZoneTuileEtPionJoueur zone_tuile_et_pion;
    private ZoneMenu zone_menu;
    private ZoneDe zone_de;
    private Jeu jeu;

    public ZoneJoueur(Jeu jeu)
    {
        super();
        this.jeu = jeu;

        this.setBackground(new Color(176,196,222));

        this.construireZoneJoueur();
    }
    
    // **************************************    Methodes   *********************************************** //

    private void construireZoneJoueur()
    {
        
        this.zone_menu = new ZoneMenu(this);
        this.zone_menu.setPreferredSize(new Dimension(360, 70));
        this.add(this.zone_menu);

        this.zone_pseudo = new ZonePseudoJoueur(Jeu.list_joueur);
        this.zone_pseudo.setPreferredSize(new Dimension(360, 80));
        this.add(this.zone_pseudo);

        this.zone_tuile_et_pion = new ZoneTuileEtPionJoueur();
        this.zone_tuile_et_pion.setPreferredSize(new Dimension(360, 500));
        this.add(this.zone_tuile_et_pion);

        this.zone_de = new ZoneDe(this);
        this.zone_de.setPreferredSize(new Dimension(360,150));
        this.add(this.zone_de);
    }

    public void paintComponent(Graphics g)
    {
        //this.revalidate();
        zone_de.repaint();
        zone_tuile_et_pion.repaint();
        zone_pseudo.repaint();
       
    }

    // **************************************    Getters   *********************************************** //

    public ZonePseudoJoueur getZone_pseudo() {
        return zone_pseudo;
    }

    public ZoneTuileEtPionJoueur getZone_tuile_et_pion() {
        return zone_tuile_et_pion;
    }

    public ZoneDe getZoneDe() 
    {
        return zone_de;
    }

    public Jeu getJeu() 
    {
        return jeu;
    }

}
