
public class Requin extends AnimalDeMer 
{
    protected final String id_requin;
    private boolean arreter ;

    public Requin(String id_requin, Hexagone hexagone) 
    {
        super(hexagone);
        this.id_requin = id_requin;
        this.arreter = false;

    }

    public String getId_requin() 
    {
        return this.id_requin;
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

    public boolean resultatDeRequin(Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
        boolean effectuer = false;

        if (this.arreter == false)
        {
            if( hexagoneArrivee.getTuile() == null)
            {
                Position posD = hexagoneDepart.getPosition();
                Position posA = hexagoneArrivee.getPosition(); 
                int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();
        
                if ((((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) ))  ||  (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 2 || y == -2)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 2 || x == -2)) || ((y < 2 && y > -2) && (x == 2 || x == -2)) ||  (posD.getNumero_ligne()%2==0 && (y == -1 ||  y == 2) && (x== 1 || x == -1)   ) ||   (posD.getNumero_ligne()%2!=0 && (y == -2 ||  y == 1) && (x==1 || x == -1))) )            
                {
                    this.hexagone = hexagoneArrivee;
                    if(hexagoneArrivee.getListe_joueur() != null)
                    {
                        this.arreter = true;
                        hexagoneArrivee.setListe_joueur(null);
                    }

                    effectuer =true;
        
                }else
                    System.out.println("Deplacement trop grand");
                
            }else
                System.out.println(" desoler Deplacement uniquement en mer");
                
    
        }else
            System.out.println( " Requin en arret ");


        return effectuer;

    }
    

}