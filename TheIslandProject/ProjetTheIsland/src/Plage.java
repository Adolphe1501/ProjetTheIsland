import java.awt.Graphics;



public class Plage extends TuileTerrain 
{    
    public Plage(String id_plage, Verso verso) 
    {
        super(verso, id_plage);
    }

    
  
    @Override
    public void afficherCaracteristiques() 
    {
        System.out.println("Type : Plage " + ",id : " + this.id);              
    }

    public void afficherTuileTerrain(Graphics g2D, String nom_fichier)
    {
        super.afficherTuileTerrain(g2D, "ProjetTheIsland/image/TP.png");
    }
}