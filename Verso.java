
public class Verso 
{

    protected final String couleur;
    protected final String action;

    public Verso(String couleur, String action) 
    {
        this.couleur = couleur;
        this.action = action;
    }

    public String getCouleur() 
    {
        return couleur;
    }

    public String getAction() 
    {
        return action;
    }
}