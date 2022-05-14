import java.awt.Dimension;
import java.awt.Image;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class De extends JButton implements MouseListener
{

    private String face[];
    private String face_principale;
    

    public De() 
    {
        this.face = new String[3];
        this.face[0]="serpent";
        this.face[1] = "requin";
        this.face[2] = "baleine";

        this.face_principale = this.face[0];
        this.addMouseListener(this);
    }
    
    public void lancerDe() 
    {
        Random rand = new Random();

        int indice = rand.nextInt(3);

        ImageIcon image_temp = new ImageIcon("image/de.gif");
        this.setIcon(image_temp);      

        Jeu.de_lance = true;

        this.face_principale =  this.face[indice];
        this.repaint();

        Jeu.timer.start();

    }

    public void arreterDe()
    {
        this.afficherDe(110, 100);
    }

    public void afficherDe(int w, int h)
    {
        String nom_fichier = determinerNomFichier();
        ImageIcon image_temp = new ImageIcon(nom_fichier);
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
        this.setIcon(image);      
        this.setPreferredSize(new Dimension(w,h));
    }

    private String determinerNomFichier()
    {
        String nom_fichier = "image/serpent.jpeg";
        
        if(this.face_principale == "serpent")
        {
            nom_fichier = "image/serpent.jpeg";
            //nom_fichier = "image/de.gif";
        }
        else if(this.face_principale == "requin")
        {
            nom_fichier  = "image/requin.jpeg";
        }
        else if(this.face_principale == "baleine")
        {
            nom_fichier  = "image/baleine.jpeg";
        }

        return nom_fichier;
    }

    public String getFace_principale() {
        return face_principale;
    }

    public void setFace_principale(String face_principale) {
        this.face_principale = face_principale;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        ImageIcon image_temp = new ImageIcon("image/de.gif");
        //Image imgScale = image_temp.getImage();
        //Image icon = imgScale.getScaledInstance(110, 100, Image.SCALE_SMOOTH);
        //ImageIcon image = new ImageIcon(icon);
        this.setIcon(image_temp);    
        System.err.println("clic");
        this.lancerDe();
        
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


}