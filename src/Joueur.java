import java.util.ArrayList;
import java.util.List;

public class Joueur 
{

    private String pseudo;
    protected List<P_Joueur> list_pion;
    protected List<TuileTerrain> list_Treserve;
    protected List<P_Joueur> list_pion_jouer;
    private int nombre_deplacement;


    public Joueur(String pseudo, ArrayList<P_Joueur> list_pion) 
    {
        this.pseudo = pseudo;
        this.list_pion = list_pion;
        this.list_Treserve = new ArrayList<TuileTerrain>();
        this.list_pion_jouer = new ArrayList<P_Joueur>();
        this.nombre_deplacement = 3;
    }

    public  boolean estMajoritaireSurBateau(Bateau bateau)
    {
        int nombre = 0, different = 0;
        String pseud = null;
        for(int i=0; i<bateau.getListe_pionJoueur().size(); i++)
        {
            if(bateau.getListe_pionJoueur().get(i).joueur.pseudo.equals(this.pseudo))
            {
                nombre++;
            }
            else
            {
                pseud = bateau.getListe_pionJoueur().get(i).joueur.pseudo;
                different++;
            }
        }

        if(different==2)
        {
            for(int i=0; i<bateau.getListe_pionJoueur().size(); i++)
            {
                if(pseud!=null && pseud.equals(bateau.getListe_pionJoueur().get(i).joueur.pseudo) && !bateau.getListe_pionJoueur().get(i).joueur.pseudo.equals(this.pseudo))
                {
                    different++;
                }
            }
        }
        
        if(nombre >= 2)
        {
            System.out.println("Majoritaire 1");
            return true;
        }
        else
        {
            if(bateau.getListe_pionJoueur().size()<=2 && nombre==1)
            {
                System.out.println("Majoritaire 2");
                return true;
            }
            else
            {
                System.out.println("NON Majoritaire");
                return false;
            }
        }
    }

    public void supprimerTuileReserve(Verso verso)
    {
        for(int i=0; i<this.list_Treserve.size(); i++)
        {
            if(this.list_Treserve.get(i).getVerso().getAction().equals(verso.action) && this.list_Treserve.get(i).getVerso().getCouleur().equals(verso.couleur))
            {
                this.list_Treserve.get(i).getVerso().supprimerDuPlateau(this.list_Treserve.get(i).getHexagone().getPlateau());
                this.list_Treserve.remove(i);
            }
        }
    }

    // **************************************    Getters   *********************************************** //

    public String getPseudo() 
    {
        return pseudo;
    }

    public List<P_Joueur> getList_pion() 
    {
        return list_pion;
    }

    public List<TuileTerrain> getList_Treserve() {
        return list_Treserve;
    }

    public int getNombre_deplacement() {
        return nombre_deplacement;
    }

    // **************************************    Setters   *********************************************** //

    public void setList_pion(List<P_Joueur> list_pion) 
    {
        this.list_pion = list_pion;
    }

    public void setPseudo(String pseudo) 
    {
        this.pseudo = pseudo;
    }

    public void setList_Treserve(List<TuileTerrain> list_Treserve) {
        this.list_Treserve = list_Treserve;
    }

    public void setNombre_deplacement(int nombre_deplacement) {
        this.nombre_deplacement = nombre_deplacement;
    }

}