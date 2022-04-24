import java.util.ArrayList;
import java.util.List;

public class Joueur 
{

    public String pseudo;
    protected List<P_Joueur> list_pion;

    public Joueur(String pseudo, ArrayList<P_Joueur> list_pion) 
    {
        this.pseudo = pseudo;
        this.list_pion = list_pion;
    }

    public String getPseudo() 
    {
        return pseudo;
    }

    public void setPseudo(String pseudo) 
    {
        this.pseudo = pseudo;
    }

    public List<P_Joueur> getList_pion() 
    {
        return list_pion;
    }

    public void setList_pion(List<P_Joueur> list_pion) 
    {
        this.list_pion = list_pion;
    }


}