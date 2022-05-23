import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public abstract class AnimalDeMer extends JLabel implements MouseListener 
{

    public static AnimalDeMer animal_mouse_clicked = null;
    public static AnimalDeMer animal_mouse_moved = null;


    protected final int vitesse;
    protected final String id;
    protected Hexagone hexagone;

    public AnimalDeMer(String id) 
    {
        super();
        this.id = id;
        this.vitesse = 1;
        this.hexagone = null;

        this.addMouseListener(this);
    }

    public static void splitListAnimalDeMer(List<AnimalDeMer> list_animaux, List<Baleine> list_baleine, List<Serpent> list_serpent, List<Requin> list_requin)
    {
        for(int i=0; i<list_animaux.size(); i++)
        {
            if(list_animaux.get(i) instanceof Baleine)
            {
                list_baleine.add((Baleine) list_animaux.get(i));
            }
            else if(list_animaux.get(i) instanceof Requin)
            {
                list_requin.add((Requin) list_animaux.get(i));
            }
            else if(list_animaux.get(i) instanceof Serpent)
            {
                list_serpent.add((Serpent) list_animaux.get(i));
            }
        }
    }
    public void afficherAnimalDeMer(Plateau plateau, String nom_fichier, int x, int y, int w, int h)
    {
        ImageIcon image_temp = new ImageIcon(nom_fichier);
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
        this.setIcon(image);   
        this.setBounds(x, y, w, h);
        plateau.add(this);
    }

    public void suprimerDeLaMap(Plateau plateau)
    {
        plateau.remove(this);
    }
   
    public abstract void attaquer();


    public abstract void deplacer();

    public String getId() {
        return id;
    }

    public Hexagone getHexagone() {
        return hexagone;
    }

    public void setHexagone(Hexagone hexagone) {
        this.hexagone = hexagone;
    } 
}