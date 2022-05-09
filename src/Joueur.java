import java.util.ArrayList;
import java.util.List;

public class Joueur 
{

    private String pseudo;
    protected List<P_Joueur> list_pion;

    public Joueur(String pseudo, ArrayList<P_Joueur> list_pion) 
    {
        this.pseudo = pseudo;
        this.list_pion = list_pion;
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

    // **************************************    Setters   *********************************************** //

    public void setList_pion(List<P_Joueur> list_pion) 
    {
        this.list_pion = list_pion;
    }

    public void setPseudo(String pseudo) 
    {
        this.pseudo = pseudo;
    }

}