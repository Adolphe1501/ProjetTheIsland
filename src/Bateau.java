import java.util.ArrayList;
import java.util.List;

public class Bateau 
{
    protected final String id_bateau;
    protected List<P_Joueur> liste_pionJoueur;

    public Bateau(String id_bateau) 
    {
        this.id_bateau = id_bateau;
        this.liste_pionJoueur = new ArrayList<P_Joueur>();
    }

   
    public void deplacer() 
    {
        // TODO implement here
    }


    public String getId_bateau() 
    {
        return id_bateau;
    }


    public List<P_Joueur> getListe_pionJoueur() 
    {
        return liste_pionJoueur;
    }


    public void setListe_pionJoueur(List<P_Joueur> liste_pionJoueur) 
    {
        this.liste_pionJoueur = liste_pionJoueur;
    }

}