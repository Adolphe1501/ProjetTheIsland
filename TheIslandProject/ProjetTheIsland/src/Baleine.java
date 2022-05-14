import java.util.ArrayList;
import java.util.List;

public class Baleine extends AnimalDeMer 
{

    protected final int id_baleine;
    protected Joueur dernierJoueurAdeplacer;

    public Baleine(int i) 
    {
        super();
        this.id_baleine = i;
        this.dernierJoueurAdeplacer = null;
    }

    @Override
    public void attaquer() 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deplacer() 
    {
        // TODO Auto-generated method stub
        
    }

    public int getId_baleine()
    {
        return this.id_baleine;
    }

    public List<Baleine> initListBalent()
    {   int i;
        List<Baleine> list= new ArrayList<>();
        for(i=0;i<5;i++)
        {
            list.add(new Baleine(i));
        }
        return list ;
    }


    public boolean jouerBaleineRouge(Baleine baleine,  Hexagone hexagoneDepart, Hexagone hexagoneArriver)
    {
        boolean jouer = false;

        if(hexagoneArriver.getZone_ile() == false && hexagoneArriver.liste_joueur == null && hexagoneArriver.getListe_animaux() == null && hexagoneArriver.getListe_bateau() == null)
        {
            List <AnimalDeMer> list = hexagoneDepart.getListe_animaux();
            list.remove(baleine);
            hexagoneDepart.setListe_animaux(list);
            list = hexagoneArriver.getListe_animaux();
            list.add(baleine);
            hexagoneArriver.setListe_animaux(list);
            jouer =true;
        }

        return jouer ;
    }
  
    public boolean jouerBaleineRougeBarrer(Baleine baleine,  Hexagone hexagone, Joueur joueur )
    {
        boolean jouer = false;

        if(baleine.dernierJoueurAdeplacer != joueur)
        {
            if(hexagone.getListe_bateau() != null)
            {
                for( Bateau bateau: hexagone.getListe_bateau())
                {
                    if(joueur.estMajoritaireSurBateau(bateau))
                    {
                        retirerBaleinedeJeu(hexagone, baleine);
                        jouer = true;
                    }
                }
            }
        }

        return jouer;
    }

    public void retirerBaleinedeJeu( Hexagone hexagone, Baleine baleine)
    {
        List <AnimalDeMer> list = hexagone.getListe_animaux();
        list.remove(baleine);
        hexagone.setListe_animaux(list);
    }



}