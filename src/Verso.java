import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Verso extends JLabel implements Cloneable, MouseListener
{

    protected final String couleur;
    protected final String action;

    public Verso(String couleur, String action) 
    {
        super();
        this.couleur = couleur;
        this.action = action;
        this.addMouseListener(this);
    }

    // **************************************    Getters   *********************************************** //

    public void afficherCaracteristiques()
    {
        System.out.println("Couleur : " + this.couleur + " Action : " + this.action);
    }

    public Verso[] initVersosplage()
    {
        Verso[] versos = new Verso[9];
        Verso verso ;

        verso = new Verso("vert","baleine");
        versos[0]  = verso;

        verso = new Verso("vert","requin");
        versos[1]  = verso;

        verso = new Verso("vert","bateau");
        versos[2]  = verso;

        verso = new Verso("rouge","bateau");
        versos[3]  = verso;

        verso = new Verso("rouge","dauphin");
        versos[4]  = verso;

        verso = new Verso("rouge","serpent");
        versos[5]  = verso;

        verso = new Verso("rouge","requin");
        versos[6]  = verso;

        verso = new Verso("rouge","baleine");
        versos[7]  = verso;

        verso = new Verso("rouge","requin_barre");
        versos[8]  = verso;

        return versos;
    }


    public Verso[] initVersosForet()
    {
        Verso[] versos = new Verso[10];
        Verso verso ;

        verso = new Verso("vert","baleine");
        versos[0]  = verso;

        verso = new Verso("vert","requin");
        versos[1]  = verso;

        verso = new Verso("vert","bateau");
        versos[2]  = verso;

        verso = new Verso("vert","tourbillon");
        versos[3]  = verso;

        verso = new Verso("rouge","dauphin");
        versos[4]  = verso;

        verso = new Verso("rouge","serpent");
        versos[5]  = verso;

        verso = new Verso("rouge","requin");
        versos[6]  = verso;

        verso = new Verso("rouge","baleine");
        versos[7]  = verso;

        verso = new Verso("rouge","requin_barre");
        versos[8]  = verso;

        verso = new Verso("rouge","baleine_barre");
        versos[9]  = verso;

        return versos;
    }

    public Verso[] initVersosMontagne()
    {
        Verso[] versos = new Verso[5];
        Verso verso ;

        verso = new Verso("vert","requin");
        versos[0]  = verso;

        verso = new Verso("vert","tourbillon");
        versos[1]  = verso;

        verso = new Verso("vert","volcan");
        versos[2]  = verso;

        verso = new Verso("rouge","requin_barre");
        versos[3]  = verso;

        verso = new Verso("rouge","baleine_barre");
        versos[4]  = verso;

        return versos;
    }

    private String determinerNomFichier()
    {
        String nom_fichier = null;

        if(this.couleur.equals("vert"))
        {
            if(this.action.equals("volcan"))
            {
                nom_fichier = "image/VO.png";
            }
            else if(this.action.equals("requin"))
            {
                nom_fichier = "image/REV.png";
            }
            else if(this.action.equals("bateau"))
            {
                nom_fichier = "image/BTV.png";
            }
            else if(this.action.equals("tourbillon"))
            {
                nom_fichier = "image/TBV.png";
            }
            else if(this.action.equals("baleine"))
            {
                nom_fichier = "image/BALV.png";
            }
        }
        else if(this.couleur.equals("rouge"))
        {
            if(this.action.equals("dauphin"))
            {
                nom_fichier = "image/DAR.png";
            }
            else if(this.action.equals("bateau"))
            {
                nom_fichier = "image/BTR.png";
            }
            else if(this.action.equals("serpent"))
            {
                nom_fichier = "image/SERPR.png";
            }
            else if(this.action.equals("requin"))
            {
                nom_fichier = "image/RER.png";
            }
            else if(this.action.equals("baleine"))
            {
                nom_fichier = "image/BALR.png";
            }
            else if(this.action.equals("requin_barre"))
            {
                nom_fichier = "image/RERB.png";
            }
            else if(this.action.equals("baleine_barre"))
            {
                nom_fichier = "image/BALRB.png";
            }
        }
        return nom_fichier;
    }

    // Affiche verso sur le plateau
    public void afficherVerso(Graphics g2D, TuileTerrain tuile)
    {
        try 
        {
            String nom_fichier = determinerNomFichier();
            int xPoint[] , yPoint[];
            Image image = ImageIO.read(new File(nom_fichier));

            xPoint = tuile.getHexagone().xpoints;
            yPoint = tuile.getHexagone().ypoints;
            
            g2D.drawImage(image, xPoint[0], yPoint[1], 80, 65, null);                    
        }
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("Erreur Fichier verso non trouve");
        }
    }

    public void afficherVerso(int w, int h)
    {
        String nom_fichier = determinerNomFichier();
        ImageIcon image_temp = new ImageIcon(nom_fichier);
        Image imgScale = image_temp.getImage();
        Image icon = imgScale.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(icon);
        this.setIcon(image);      
    }

    public boolean equals(Verso v)
    {
        if((this.couleur == v.couleur) && (this.action == v.action))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Verso clone()
    {
        Verso clone = new Verso(this.couleur, this.action);

        return clone;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        this.afficherCaracteristiques();

        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        this.afficherCaracteristiques();

        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    // **************************************    Getters   *********************************************** //

    public String getCouleur() 
    {
        return couleur;
    }

    public String getAction() 
    {
        return action;
    }

    
}