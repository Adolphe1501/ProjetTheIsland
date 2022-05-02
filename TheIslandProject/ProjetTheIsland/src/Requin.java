import java.util.ArrayList;

public class Requin extends AnimalDeMer 
{
    protected final String id_requin;

    public Requin(String id_requin) 
    {
        super();
        this.id_requin = id_requin;
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

    // Initialisation d'un tableau contenant les requins
    public ArrayList<Requin> initialiserRequin() {
		ArrayList<Requin> requin = new ArrayList<>();
		for (int i=1; i<=5; i++) {
			requin.add(new Requin(Integer.toString(i)));
		}
		return requin;
	}

}