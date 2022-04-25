
import java.util.*;

public class Hexagone {

    public List<Bateau> liste_bateau;
    public List<P_Joueur> liste_joueur;
    public List<AnimalDeMer> liste_animaux;
    
    public Hexagone() 
    {
        this.liste_animaux = new ArrayList<AnimalDeMer>();
        this.liste_joueur = new ArrayList<P_Joueur>();
        this.liste_bateau = new ArrayList<Bateau>();
    }

    
    public void detruire_bateau() 
    {
        // TODO implement here
    }
    public void sortir_joueur() 
    {
        // TODO implement here
    }


    public List<Bateau> getListe_bateau() 
    {
        return liste_bateau;
    }


    public void setListe_bateau(List<Bateau> liste_bateau) 
    {
        this.liste_bateau = liste_bateau;
    }


    public List<P_Joueur> getListe_joueur() 
    {
        return liste_joueur;
    }


    public void setListe_joueur(List<P_Joueur> liste_joueur) 
    {
        this.liste_joueur = liste_joueur;
    }


    public List<AnimalDeMer> getListe_animaux() 
    {
        return liste_animaux;
    }


    public void setListe_animaux(List<AnimalDeMer> liste_animaux) 
    {
        this.liste_animaux = liste_animaux;
    }

}