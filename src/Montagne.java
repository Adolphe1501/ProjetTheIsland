import java.awt.Graphics;


public class Montagne extends TuileTerrain
{

    public Montagne(String id_montagne, Verso verso) 
    {
        super(verso, id_montagne);
    }


    @Override
    public void afficherCaracteristiques() 
    {
        System.out.println("Type : Montagne " + ",id : " + this.id);                      
    }

    public void afficherTuileTerrain(Graphics g2D, String nom_fichier)
    {
        super.afficherTuileTerrain(g2D, "image/TM.png");
    }

}