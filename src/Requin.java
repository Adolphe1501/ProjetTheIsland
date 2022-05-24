import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Requin extends AnimalDeMer implements MouseListener
{

    public Requin(String id_requin) 
    {
        super(id_requin);
    }

    public void afficherAnimalDeMer(Plateau plateau, String nom_fichier, int x, int y, int w, int h)
    {
        super.afficherAnimalDeMer(plateau, "image/RE.png", x, y,  w, h);
    }

    public static List <Requin> initRequin()
    {
        List <Requin> list = new ArrayList<Requin>();

        for(int i=0; i<6; i++)
        {
            String id = "" + (i+1);
            list.add(new Requin(id));
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
    public void mousePressed(MouseEvent e) 
    {
        AnimalDeMer.animal_mouse_clicked = this;
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