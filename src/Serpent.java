import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Serpent extends AnimalDeMer 
{

    protected final String id_serpent;

    public Serpent(String id_serpent) 
    {
        super(id_serpent);
        this.id_serpent = id_serpent;
    }

    public static List<Serpent> initSerpent()
    {
        List<Serpent> list = new ArrayList<>();
       
        for(int i=0; i<5; i++)
        {
            String id = "" +(i+1) ;
            list.add(new Serpent(id));
        }

        return list;
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
    public void mousePressed(MouseEvent e) 
    {
        AnimalDeMer.animal_mouse_clicked = this;
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