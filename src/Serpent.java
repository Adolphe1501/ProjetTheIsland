import java.awt.event.MouseEvent;

public class Serpent extends AnimalDeMer 
{

    protected final String id_serpent;

    public Serpent(String id_serpent) 
    {
        super(id_serpent);
        this.id_serpent = id_serpent;
    }

    public void afficherAnimalDeMer(Plateau plateau, String nom_fichier, int x, int y, int w, int h)
    {
        super.afficherAnimalDeMer(plateau, "image/SE.png", x, y,  w, h);
    }
    @Override
    public void attaquer() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deplacer() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        AnimalDeMer.animal_mouse_moved = this;
        
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        AnimalDeMer.animal_mouse_moved = null;
    }
}