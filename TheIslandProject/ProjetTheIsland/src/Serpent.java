import java.util.ArrayList;

public class Serpent extends AnimalDeMer 
{

    protected final String id_serpent;

    public Serpent(String id_serpent) 
    {
        super();
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
    
    public ArrayList<Serpent> initialiserSerpent() {
    	ArrayList<Serpent> serpent = new ArrayList<>();
    	for (int i=1; i<=6; i++) {
    		serpent.add(new Serpent(Integer.toString(i)));
    	}
    	return serpent;
    }
}