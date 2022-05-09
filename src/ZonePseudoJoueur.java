import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ZonePseudoJoueur extends JPanel
{
    private List <JButton> pseudoJoueur_label = new ArrayList<JButton>(); 
    private List <Joueur> liste_joueur = new ArrayList<Joueur>();

    public ZonePseudoJoueur(List <Joueur> liste_joueur)
    {
        super();
        this.liste_joueur = liste_joueur;
        
        this.miseEnPlaceLayout();
        this.construireZonePseudoJoueur();
    }

    // **************************************    Methodes   *********************************************** //
    
    private void construireZonePseudoJoueur()
    {
        for(int i=0; i<this.liste_joueur.size(); i++)
        {
            JButton label = new JButton(this.liste_joueur.get(i).getPseudo());
            label.setFont(new Font("sherif", Font.BOLD,25));
            label.setFocusable(false);
            this.pseudoJoueur_label.add(label);
            this.add(label);
        }
    }

    private void miseEnPlaceLayout()
    {
        if(this.liste_joueur.size()<=2)
        {
            this.setLayout(new GridLayout(1, 2, 25, 5));
        }
        else
        {
            this.setLayout(new GridLayout(2, 2, 25, 5));
        }
    }

    // **************************************    Getters   *********************************************** //

    public List<JButton> getPseudoJoueur_label() {
        return pseudoJoueur_label;
    }

    public List<Joueur> getListe_joueur() {
        return liste_joueur;
    }

    // **************************************    Setters   *********************************************** //

    public void setListe_joueur(List<Joueur> liste_joueur) {
        this.liste_joueur = liste_joueur;
    }

    public void setPseudoJoueur_label(List<JButton> pseudoJoueur_label) {
        this.pseudoJoueur_label = pseudoJoueur_label;
    }
}
