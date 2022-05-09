import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class ZoneJoueur extends JPanel
{
    private ZonePseudoJoueur zone_pseudo;
    private ZoneTuileEtPionJoueur zone_tuile_et_pion;

    public ZoneJoueur()
    {
        super();

        this.setBackground(new Color(176,196,222));

        this.construireZoneJoueur();
    }
    
    // **************************************    Methodes   *********************************************** //

    private void construireZoneJoueur()
    {
        Jeu.list_joueur.add(new Joueur("Adolphe", null));
        Jeu.list_joueur.add(new Joueur("Emile", null));
        Jeu.list_joueur.add(new Joueur("Rochdi" , null));
        Jeu.list_joueur.add(new Joueur("Waffa", null));

        Jeu.joueur = Jeu.list_joueur.get(0);
        List<P_Joueur> temp = new ArrayList<P_Joueur>();

        for(int i=0; i<10; i++)
        {
            String id = "id" +i;
            temp.add(new P_Joueur(id, "vert", i, Jeu.joueur));
        }

        Jeu.joueur.setList_pion(temp);

        this.zone_pseudo = new ZonePseudoJoueur(Jeu.list_joueur);
        this.zone_pseudo.setPreferredSize(new Dimension(380, 100));

        this.add(this.zone_pseudo);

        this.zone_tuile_et_pion = new ZoneTuileEtPionJoueur();
        this.zone_tuile_et_pion.setPreferredSize(new Dimension(380, 600));
        this.add(this.zone_tuile_et_pion);
    }

    public void paintComponent(Graphics g)
    {
        zone_tuile_et_pion.repaint();
    }

    // **************************************    Getters   *********************************************** //

    public ZonePseudoJoueur getZone_pseudo() {
        return zone_pseudo;
    }

    public ZoneTuileEtPionJoueur getZone_tuile_et_pion() {
        return zone_tuile_et_pion;
    }
}
