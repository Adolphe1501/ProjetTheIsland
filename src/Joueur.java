import java.util.ArrayList;
import java.util.List;

public class Joueur {

    public String pseudo;
    protected List<P_Joueur> list_pion;
    protected List<Bateau> list_bateau;
    protected List<TuileTerrain> list_Treserve;

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
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

    public void setList_Bateau(List<Bateau> list_bateau) {
        this.list_bateau = list_bateau;
    }

    public List<P_Joueur> initPJoueur(String couleur) {
        List<P_Joueur> list = new ArrayList<>();
        int i;
        for (i = 0; i < 3; i++) {
            list.add(new P_Joueur("PJ" + couleur.toCharArray()[0] + "01", couleur, 1, this));
        }
        list.add(new P_Joueur("PJ" + couleur.toCharArray()[0] + "02", couleur, 2, this));
        list.add(new P_Joueur("PJ" + couleur.toCharArray()[0] + "02", couleur, 2, this));
        list.add(new P_Joueur("PJ" + couleur.toCharArray()[0] + "03", couleur, 3, this));
        list.add(new P_Joueur("PJ" + couleur.toCharArray()[0] + "03", couleur, 3, this));
        list.add(new P_Joueur("PJ" + couleur.toCharArray()[0] + "04", couleur, 4, this));
        list.add(new P_Joueur("PJ" + couleur.toCharArray()[0] + "05", couleur, 5, this));
        list.add(new P_Joueur("PJ" + couleur.toCharArray()[0] + "06", couleur, 6, this));
        return list;
    }

    public void initJoeur(String couleur) {
        this.setList_pion(initPJoueur(couleur));

    }
  /*  public boolean verifPion(P_Joueur pion)
    {   int i=1;
        P_Joueur p=this.list_pion.get(0);
        while ((i<this.list_pion.size())&&(p!=pion))
        {   p=this.list_pion.get(i);
            i++;
        }
        if(pion==p)
        return true ;
        else return false;

    }
*/
    /*
      public static void main(String[] args) {
      Joueur joueur = new Joueur("wafa");
      Joueur joueur2 = new Joueur("wafa");

      joueur.initPJoueur("Rouge");
      joueur.initJoeur("Rouge");
      System.out.println("bjrrrrrr");
      P_Joueur pion1=new P_Joueur("PJR03", "vert", 03, joueur);
      P_Joueur pion2=new P_Joueur("jjjj", "Rouge", 10, joueur);
      P_Joueur pion3=new P_Joueur("jjjj", "Rouge", 10, joueur2);
      System.out.println(joueur.verifPion(pion1));


    /* String j = joueur.list_pion.get(0).id_P_joueur;
     * System.out.println(j.toCharArray()[2]);
      
      }*/
     

}
