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
        this.nombre_deplacement = 10;
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