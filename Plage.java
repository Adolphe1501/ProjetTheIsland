

public class Plage extends TuileTerrain 
{
    protected final String id_plage;
    
    public Plage(String id_plage, Verso verso) 
    {
        super(verso);
        this.id_plage = id_plage;
    }

    public String getId_plage() 
    {
        return id_plage;
    }

}