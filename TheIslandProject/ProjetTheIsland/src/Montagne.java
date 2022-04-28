
public class Montagne extends TuileTerrain
{
    protected final String id_montagne;

    public Montagne(String id_montagne, Verso verso) 
    {
        super(verso);

        this.id_montagne = id_montagne;
    }

    public String getId_montagne() 
    {
        return id_montagne;
    }

}