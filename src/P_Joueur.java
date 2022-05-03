
public class P_Joueur 
{

    protected final String id_P_joueur;
    public final String couleur;
    private final int valeur;
    protected Boolean est_nageur;
    public final Joueur joueur;

    public P_Joueur(String id_P_joueur, String couleur, int valeur, Joueur joueur) 
    {
        this.id_P_joueur = id_P_joueur;
        this.couleur = couleur;
        this.valeur = valeur;
        this.joueur = joueur;
        this.est_nageur = false;
    }

   
    public void defendre() 
    {
        // TODO implement here
    }

    /**
     * 
     */
    public void attaquer() 
    {
        // TODO implement here
    }

    public String getId_P_joueur() 
    {
        return id_P_joueur;
    }

    public String getCouleur() 
    {
        return couleur;
    }

    public int getValeur() 
    {
        return valeur;
    }

    public Boolean getEst_nageur() 
    {
        return est_nageur;
    }

    public void setEst_nageur(Boolean est_nageur) 
    {
        this.est_nageur = est_nageur;
    }

    public Joueur getJoueur() 
    {
        return joueur;
    }

}