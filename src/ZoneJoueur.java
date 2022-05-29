import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ZoneJoueur extends JPanel
{
    private Jeu jeu;

    private JLabel numero_action;
    private JLabel nombre_deplacement;
    private JLabel label; 
    private int indicateur_action;
    private int indicateur_deplacemen;

    public ZoneJoueur(Jeu jeu)
    {
        super();
        this.jeu = jeu;

        //this.setLayout(new GridLayout(2,2));
        //setBackground(Color.BLACK);
        this.setBackground(new Color(176,196,222));

        afficherInfo();

    }
    
    // **************************************    Methodes   *********************************************** //

    private void construireZoneJoueur()
    {
        if(Jeu.premier_placement)
        {
            removeAll();
            this.setLayout(new BorderLayout());
            this.label = new JLabel(" Placer vos pions sur l'île");
            this.label.setFont(new Font("Serif", Font.BOLD, 20));
            this.add(label, BorderLayout.CENTER);
        }
    }

    public void afficherInfo()
    {
        removeAll();
        this.setLayout(new GridLayout(2,2));

        JLabel deplacement_restant = new JLabel(" Deplacement restant : ");
        JLabel action = new JLabel(" Action : ");

        deplacement_restant.setFont(new Font("Serif", Font.BOLD, 14));
        action.setFont(new Font("Serif", Font.BOLD, 14));

        this.nombre_deplacement = new JLabel(""+Jeu.joueur.getNombre_deplacement());
        this.nombre_deplacement.setFont(new Font("Serif", Font.BOLD, 14));

        this.numero_action = new JLabel(""+Jeu.action);
        this.numero_action.setFont(new Font("Serif", Font.BOLD, 14));

        this.add(deplacement_restant);
        this.add(nombre_deplacement);
        this.add(action);
        this.add(numero_action);
        revalidate();
    }

    public void paintComponent(Graphics g)
    {

        this.nombre_deplacement.setText(""+Jeu.joueur.getNombre_deplacement());
        if(Jeu.premier_placement)
        {
            if(!Jeu.fin_placement_joueur)
            {
                this.numero_action.setFont(new Font("Serif", Font.BOLD, 14));
                this.numero_action.setText("Placez les pions sur l'île");
            }
            else
            {
                this.numero_action.setFont(new Font("Serif", Font.BOLD, 12));
                this.numero_action.setText("Placez les bateaux sur l'île");

            }
        }
        else
        {
            this.numero_action.setText(""+Jeu.action);
        }
        
    }

    // **************************************    Getters   *********************************************** //

    public Jeu getJeu() 
    {
        return jeu;
    }

}
