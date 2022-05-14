import java.util.ArrayList;
import java.util.List;

public class Joueur {

    public String pseudo;
    protected List<P_Joueur> list_pion;
    public int nombre_deplacement;
    protected List<TuileTerrain> list_Treserve;

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.nombre_deplacement = 3;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public List<P_Joueur> getList_pion() {
        return list_pion;
    }

    public void setList_pion(List<P_Joueur> list_pion) {
        this.list_pion = list_pion;
    }



    public List<P_Joueur> initListPJoueur(String couleur) {
        List<P_Joueur> list = new ArrayList<>();
        int i;
        for (i = 0; i < 3; i++) {
            list.add(new P_Joueur("Pj" + couleur.toCharArray()[0] + "01", couleur, 1, this));
        }
        list.add(new P_Joueur("Pj" + couleur.toCharArray()[0] + "02", couleur, 2, this));
        list.add(new P_Joueur("Pj" + couleur.toCharArray()[0] + "02", couleur, 2, this));
        list.add(new P_Joueur("Pj" + couleur.toCharArray()[0] + "03", couleur, 3, this));
        list.add(new P_Joueur("Pj" + couleur.toCharArray()[0] + "03", couleur, 3, this));
        list.add(new P_Joueur("Pj" + couleur.toCharArray()[0] + "04", couleur, 4, this));
        list.add(new P_Joueur("Pj" + couleur.toCharArray()[0] + "05", couleur, 5, this));
        list.add(new P_Joueur("Pj" + couleur.toCharArray()[0] + "06", couleur, 6, this));
        for (i = 0; i < 10; i++) {
            System.out.println(list.get(i).getValeur() + "+++++++" + list.get(i).getId_P_joueur());
        }
        return list;
    }


    public  boolean estMajoritaireSurBateau(Bateau bateau)
    {
       
        int i = 0,j = 0;

        for(P_Joueur pionJ : bateau.liste_pionJoueur)
        {
            if(pionJ != null)
              i++;
        }
        for(P_Joueur pionJ : this.list_pion)
        {
            for(P_Joueur pionJB : bateau.liste_pionJoueur)
            {
                if(pionJ == pionJB)
                    j++;
            }
        }

        if(j>=i/2)
            return true;
        else
            return false;
    }

   /* public static void main(String[] args) {
        Joueur joueur = new Joueur("wafa");
        joueur.initPJoueur("rouge");
    }*/
}