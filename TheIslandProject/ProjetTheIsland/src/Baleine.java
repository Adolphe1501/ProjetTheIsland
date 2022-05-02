import java.util.ArrayList;

public class Baleine extends AnimalDeMer
{

    protected String id_baleine;
    
    public Baleine() {}
    
    public Baleine(String id_baleine) 
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

    public String getId_baleine()
    {
        return this.id_baleine;
    }

	public ArrayList<Baleine> initialiserBaleine() {
		ArrayList<Baleine> baleine = new ArrayList<>();
		for (int i=1; i<=5; i++) {
			baleine.add(new Baleine(Integer.toString(i)));
		}
		return baleine;
	}

}