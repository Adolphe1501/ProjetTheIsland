import java.util.ArrayList;
import java.util.List;

public class Baleine extends AnimalDeMer 
{

    protected final int id_baleine;

    public Baleine(int id_baleine) 
    {
        super();
        this.id_baleine = id_baleine;
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
    public void balein_vert(List<Baleine> list , Position position)
    {
        Baleine baleine=list.get(0);
        list.remove(0);
       if (Plateau.map[position.getNumero_ligne()][position.getNumero_colone()].getzone_ile()==false)
       {
        Plateau.map[position.getNumero_ligne()][position.getNumero_colone()].addPionMer(baleine);
       }
    }

}