import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class P_Joueur extends JLabel implements MouseListener
{

    protected final String id_P_joueur;
    public final String couleur;
    private final int valeur;
    protected Boolean est_nageur;
    public final Joueur joueur;
    private Hexagone hexagone;

    public P_Joueur(String id_P_joueur, String couleur, int valeur, Joueur joueur) 
    {
        super();
        this.id_P_joueur = id_P_joueur;
        this.couleur = couleur;
        this.valeur = valeur;
        this.joueur = joueur;
        this.est_nageur = false;
        this.hexagone = null;
    }

    


    public boolean deplacerPjoueurEtOuBateau(Joueur joueur, Bateau bateau, Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
       
       if(joueur.nombre_deplacement>0)
       {
            if(!bateau.estVideBateau())
            {
                if (joueur.estMajoritaireSurBateau(bateau))
                {
                    bateau.deplacerPionBateau(joueur, hexagoneDepart, hexagoneArrivee);
                    for(P_Joueur pJ : bateau.getListe_pionJoueur())
                        pJ.deplacerPj(hexagoneDepart, hexagoneArrivee);
                    return true;
                }else
                    System.out.println(" Joueur non Majoritaire vous ne pouvez pas deplacer le bateau  ");
                    return false;
            }else
                bateau.deplacerPionBateau(joueur, hexagoneDepart, hexagoneArrivee);
                return true;
       }else
            System.out.println(" Nombre de deplacements Insuffisants ");
            return  false;
     
    }

    public boolean deplacerPjoueurHorsduBateau(P_Joueur pJ , Bateau bateau, Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
        bateau.liste_pionJoueur.remove(pJ);
        pJ.deplacerPionJoueur(hexagoneDepart, hexagoneArrivee);
        return true;
    }


    
    public boolean deplacerPionJoueur(Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {

        boolean deplacement = false;
        
        if(this.joueur.nombre_deplacement>0)
        {
            Position posD = hexagoneDepart.getPosition();
            Position posA = hexagoneArrivee.getPosition(); 
            int x = posD.getNumero_ligne() - posA.getNumero_ligne(), y = posA.getNumero_colone() - posA.getNumero_colone();
            if(this.estNageur())
            {
                    
                if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1)))                 
                {
                    this.deplacerPj(hexagoneDepart, hexagoneArrivee);
                    this.joueur.nombre_deplacement-=1;
                    deplacement = true;
                }else{
                    deplacement = false ;
                }
                    
            }else{
            
                if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1)))                 
                {
                    this.deplacerPj(hexagoneDepart, hexagoneArrivee);
                    this.joueur.nombre_deplacement-=1;
                    deplacement= true;

                }
                if(this.joueur.nombre_deplacement>1)
                {
                    if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y < 3 || y > -3)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x < 3 || x > -3)) || ((y < 3 || y > -3) && (x < 3 || x > -3)))                 
                    {
                        this.deplacerPj(hexagoneDepart, hexagoneArrivee);
                        this.joueur.nombre_deplacement-=2;
                        deplacement= true;

                    }else{
                        System.out.println("Nombre de deplacement insuffisant");
                        deplacement= false;

                    }
                }

                if(this.joueur.nombre_deplacement>2)
                {
                    if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y < 4 || y > -4)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x < 4 || x > -4)) || ((y < 4 || y > -4) && (x < 4 || x > -4)))                 
                    {
                        this.deplacerPj(hexagoneDepart, hexagoneArrivee);
                        this.joueur.nombre_deplacement-=3;
                        deplacement= true;


                    }else{
                        System.out.println("le nombre de deplacement maximum est de 3");
                        deplacement= false;

                    }
                }
            }
        }else{
            System.out.println("Nombre de deplacement insuffisant");
            deplacement = false;
        }

        return false;
    }  
    

    public boolean deplacerPj(Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
             
        ArrayList<P_Joueur> list =  (ArrayList<P_Joueur>) hexagoneDepart.getListe_joueur();
        list.remove(this);
        hexagoneDepart.setListe_joueur(list);

        list = (ArrayList<P_Joueur>) hexagoneArrivee.getListe_joueur();
        list.add(this);
        hexagoneArrivee.setListe_joueur(list);

        this.hexagone = hexagoneArrivee;
        return true;
    }
    public void defendre() 
    {
        // TODO implement here
    }

    /**
     * 
     */
    public void attaquer() 
    {
        // TODO implement here
    }

    public String getId_P_joueur() 
    {
        return id_P_joueur;
    }

    public String getCouleur() 
    {
        return couleur;
    }

    public int getValeur() 
    {
        return valeur;
    }

    public Boolean getEst_nageur() 
    {
        return est_nageur;
    }

    public void setEst_nageur(Boolean est_nageur) 
    {
        this.est_nageur = est_nageur;
    }

    public boolean estNageur()
    {
        if(this.est_nageur == true)
            return true;
        else
            return false;
    }

    public boolean estExplorateur()
    {
        if(this.est_nageur == false)
            return true;
        else
            return false;
    }

    public Joueur getJoueur() 
    {
        return joueur;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
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

}