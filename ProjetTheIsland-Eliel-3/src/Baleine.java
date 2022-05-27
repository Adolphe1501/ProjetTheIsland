

public class Baleine extends AnimalDeMer 
{

    protected final String id_baleine;
    boolean arret ;

    public Baleine(String id_baleine, Hexagone hexagone) 
    {
        super(hexagone);
        this.id_baleine = id_baleine;
        this.arret =false;
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

    public String getId_baleine()
    {
        return this.id_baleine;
    }


    public boolean resultatDeBaleine( Hexagone hexagoneDepart, Hexagone hexagoneArrivee)
    {
        boolean effectuer = false;
        
        if (this.arret == false)
        {
            if(hexagoneArrivee.getTuile() == null)
            {
                Position posD = hexagoneDepart.getPosition();
                Position posA = hexagoneArrivee.getPosition(); 
                int x = posA.getNumero_ligne() - posD.getNumero_ligne(), y = posA.getNumero_colone() - posD.getNumero_colone();
              
                if ((((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 1 || y == -1)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 1 || x == -1)) || ((y == 1 || y == -1) && (x == 1 || x == -1) && !((posD.getNumero_ligne()%2==0 && y == -1 && (x== 1 || x == -1)) || (posD.getNumero_ligne()%2!=0 && y == 1 && (x== 1 || x == -1))) )) || (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y == 2 || y == -2)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 2 || x == -2)) || ((y < 2 && y > -2) && (x == 2 || x == -2)) ||  (posD.getNumero_ligne()%2==0 && (y == -1 ||  y == 2) && (x== 1 || x == -1)   ) ||   (posD.getNumero_ligne()%2!=0 && (y == -2 ||  y == 1) && (x==1 || x == -1))) || (((posD.getNumero_ligne() == posA.getNumero_ligne()) && (y ==3 || y == -3)) || ((posD.getNumero_colone() == posA.getNumero_colone()) && (x == 3 || x == -3)) || ((y < 3 && y >-3) && (x == 3 || x == -3)) || ( (posD.getNumero_ligne()%2==0 && x<3 && x>-3 && x!=0 && y < 4 && y >-3 && y !=0 ) || (posD.getNumero_ligne()%2!=0 && x<4 && x>-4 && x!=0 && y < 4 && y >-4 && y !=0 ))))                 
                {
                    this.hexagone = hexagoneArrivee;      
                    if(hexagoneArrivee.getBateau() != null)
                    {
                        if(hexagoneArrivee.getBateau().getListe_pionJoueur() != null)
                        {
                            this.arret = true;
                            for( P_Joueur Pj : hexagoneArrivee.getBateau().getListe_pionJoueur())
                            {
                                Pj.setHexagone(hexagoneArrivee);
                                Pj.est_nageur = true;
                            }
                            hexagoneArrivee.getBateau().setListe_pionJoueur(null);
                            hexagoneArrivee.setBateau(null);

                            for ( AnimalDeMer aMer : hexagoneArrivee.getListe_animaux())
                            {
                                if ( aMer instanceof Requin)
                                {
                                    hexagoneArrivee.setListe_joueur(null);
                                }
                            }
                        }
                    }

                    effectuer = true;
    
    
                }else
                    System.out.println("Deplacement Trop grand");
    
    
            }else
                System.out.println(" deplacement impossible hors de mer ");
    
     
        }else
            System.out.println(" desoler pion en arret  ");


        return effectuer;
    }


}
