

public class Serpent extends AnimalDeMer 
{

    protected final String id_serpent;

    public Serpent(String id_serpent, Hexagone hexagone) 
    {
        super(hexagone);
        this.id_serpent = id_serpent;
    }

    @Override
    public void attaquer() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deplacer() {
        // TODO Auto-generated method stub
        
    }

    public String getId_serpent()
    {
        return this.id_serpent;
    }

    public boolean resultatDeSerpentDeMer( Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
        boolean effectuer = false ;


        Position posD = hexagoneDepart.getPosition();
        Position posA = hexagoneArrivee.getPosition(); 
        int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();

        if(hexagoneArrivee.getTuile() == null)
        {
            if (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))                 
            {
                this.hexagone = hexagoneArrivee;
                if(hexagoneArrivee.getBateau() != null)
                {
                    if(hexagoneArrivee.getBateau().getListe_pionJoueur() != null)
                    {
                        hexagoneArrivee.getBateau().setListe_pionJoueur(null);
                        hexagoneArrivee.setBateau(null);
                    }
    
                    if(hexagoneArrivee.getListe_joueur() != null)
                    {
                        hexagoneArrivee.setListe_joueur(null);
                    }
    
                }

                effectuer =true ;
    
            }else
                System.out.println("Deplacement Trop grand");
        }else
            System.out.println(" deplacement impossible hors de mer ");


        return effectuer;
    }
}