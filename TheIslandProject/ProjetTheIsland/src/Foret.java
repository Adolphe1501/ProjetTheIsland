import java.awt.Graphics;



public class Foret extends TuileTerrain 
{
    public Foret(String id_foret, Verso verso) 
    {
        super(verso, id_foret);
    }

    @Override
    public void afficherCaracteristiques() 
    {
        System.out.println("Type : Foret " + ",id : " + this.id);        
    }

    public void afficherTuileTerrain(Graphics g2D, String nom_fichier)
    {
        super.afficherTuileTerrain(g2D, "ProjetTheIsland/image/TF.png");
    }
}