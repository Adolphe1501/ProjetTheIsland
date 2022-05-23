import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Baleine extends AnimalDeMer 
{

    public Baleine(String id_baleine) 
    {
        super(id_baleine);
    }

    public void afficherAnimalDeMer(Plateau plateau, String nom_fichier, int x, int y, int w, int h)
    {
        super.afficherAnimalDeMer(plateau, "image/BAL.png", x, y,  w, h);
    }

    public static List <Baleine> initBaleine()
    {
        List <Baleine> list = new ArrayList<Baleine>();

        for(int i=0; i<5; i++)
        {
            String id = "" + (i+1);
            list.add(new Baleine(id));
        }

        return list;
    }
    
    @Override
    public void attaquer() 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deplacer() 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        AnimalDeMer.animal_mouse_moved = this;
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        AnimalDeMer.animal_mouse_moved = null;
        
    }

}