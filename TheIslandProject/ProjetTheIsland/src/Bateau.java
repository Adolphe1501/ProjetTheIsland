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

   
 

    // on test si le bateau possede une liste de pions
    public boolean estVideBateau()
    {
        if (this.liste_pionJoueur == null)
            return true;
        else
            return false;
    }

    public List<Bateau> initBateau(){
        List<Bateau> bateaux=new ArrayList<Bateau>();
        for (int i = 0; i < 12; i++) {
            String id = "B" + i;
            Bateau bateau= new Bateau(id);
            bateaux.add(bateau);
        }
        return bateaux;
    }


    public String getId_bateau() 
    {
        return id_bateau;
    }


    public List<P_Joueur> getListe_pionJoueur() 
    {
        return liste_pionJoueur;
    }

    public void retirerPjoueurDeBateau(P_Joueur pion)
    {
        this.liste_pionJoueur.remove(pion);
    }

    


    public void setListe_pionJoueur(List<P_Joueur> liste_pionJoueur) 
    {
        this.liste_pionJoueur = liste_pionJoueur;
    }

    public boolean deplacerPionBateau(Joueur joueur ,Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
       
        if(joueur.nombre_deplacement>0)
        {
            Position posD = hexagoneDepart.getPosition();
            Position posA = hexagoneArrivee.getPosition(); 
            int x = posD.getNumero_ligne() - posA.getNumero_ligne(), y = posA.getNumero_colone() - posA.getNumero_colone();

            if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y < 4 || y > -4)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x < 4 || x > -4)) || ((y < 4 || y > -4) && (x < 4 || x > -4)))                 
            {
                ArrayList<Bateau> list =  (ArrayList<Bateau>) hexagoneDepart.getListe_bateau();
                list.remove(this);
                hexagoneDepart.setListe_bateau(list);
        
                list = (ArrayList<Bateau>) hexagoneArrivee.getListe_bateau();
                list.add(this);
                hexagoneArrivee.setListe_bateau(list);

                joueur.nombre_deplacement -=1;
                return true;
            }else{
                System.out.println("deplacement trop grand");
                return false;
            }

        }else{
            System.out.println("Nombre de deplacement Insuffisant");
            return false;

        }
       
    }

}