import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class ZonePseudoJoueur extends JPanel
{
    private List <JButton> pseudoJoueur_label = new ArrayList<JButton>(); 
    private List <Joueur> liste_joueur = new ArrayList<Joueur>();

    public ZonePseudoJoueur(List <Joueur> liste_joueur)
    {
        super();
        this.liste_joueur = liste_joueur;
        this.setBackground(Color.blue);
        this.miseEnPlaceLayout();
        this.construireZonePseudoJoueur();
    }

    // **************************************    Methodes   *********************************************** //
    
    private void construireZonePseudoJoueur()
    {
        for(int i=0; i<this.liste_joueur.size(); i++)
        {
            JButton label = new JButton(this.liste_joueur.get(i).getPseudo());
            label.setFont(new Font("Serif", Font.BOLD,25));
            label.setFocusable(false);
            label.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    
                    
                }

            });
            this.pseudoJoueur_label.add(label);
            this.add(label);
        }
    }

    private void miseEnPlaceLayout()
    {
        if(this.liste_joueur.size()<=2)
        {
            this.setLayout(new GridLayout(1, 2, 10, 5));
        }
        else
        {
            this.setLayout(new GridLayout(2, 2, 10, 5));
        }
    }

    public void paintComponent(Graphics g)
    {
        if(Jeu.joueur!=null)
        {
            for(int i = 0; i<this.liste_joueur.size(); i++)
            {
                if(this.liste_joueur.get(i).getPseudo().equals(Jeu.joueur.getPseudo()))
                {
                    pseudoJoueur_label.get(i).setBackground(new Color(30,144,255));
                    pseudoJoueur_label.get(i).setOpaque(true);
                }
                else
                {
                    pseudoJoueur_label.get(i).setOpaque(false);
                }   
            }
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
