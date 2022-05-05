import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics;

import java.awt.Image;


public class Verso 
{

    protected final String couleur;
    protected final String action;

    public Verso(String couleur, String action) 
    {
        this.couleur = couleur;
        this.action = action;
    }

    public String getCouleur() 
    {
        return couleur;
    }

    public String getAction() 
    {
        return action;
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

    public Verso[] initVersosplage()
    {
        Verso[] versos = new Verso[9];
        Verso verso ;


        verso = new Verso("vert","aigle");
        versos[0]  = verso;

        verso = new Verso("vert","requin");
        versos[1]  = verso;

        verso = new Verso("vert","bateau");
        versos[2]  = verso;

        verso = new Verso("rouge","bateau");
        versos[3]  = verso;

        verso = new Verso("rouge","dauphin");
        versos[4]  = verso;

        verso = new Verso("rouge","dragon");
        versos[5]  = verso;

        verso = new Verso("rouge","requin");
        versos[6]  = verso;

        verso = new Verso("rouge","aigle");
        versos[7]  = verso;

        verso = new Verso("rouge","requin_barre");
        versos[8]  = verso;


        return versos;
    }


    public Verso[] initVersosForet()
    {
        Verso[] versos = new Verso[10];
        Verso verso ;


        verso = new Verso("vert","aigle");
        versos[0]  = verso;

        verso = new Verso("vert","requin");
        versos[1]  = verso;

        verso = new Verso("vert","bateau");
        versos[2]  = verso;

        verso = new Verso("vert","toran");
        versos[3]  = verso;

        verso = new Verso("rouge","dauphin");
        versos[4]  = verso;

        verso = new Verso("rouge","dragon");
        versos[5]  = verso;

        verso = new Verso("rouge","requin");
        versos[6]  = verso;

        verso = new Verso("rouge","aigle");
        versos[7]  = verso;

        verso = new Verso("rouge","requin_barre");
        versos[8]  = verso;

        verso = new Verso("rouge","aigle_barre");
        versos[9]  = verso;

        return versos;
    }

    public Verso[] initVersosMontagne()
    {
        Verso[] versos = new Verso[5];
        Verso verso ;



        verso = new Verso("vert","requin");
        versos[0]  = verso;

        verso = new Verso("vert","toran");
        versos[1]  = verso;

        verso = new Verso("vert","volcan");
        versos[2]  = verso;

        verso = new Verso("rouge","requin_barre");
        versos[3]  = verso;

        verso = new Verso("rouge","aigle_barre");
        versos[4]  = verso;

        return versos;
    }

    public String determinerNomFichier()
    {
        String nom_fichier = null;

        if(this.couleur.equals("vert"))
        {
            if(this.action.equals("volcan"))
            {
                
            }
            else if(this.action.equals("requin"))
            {
                nom_fichier = "image/REV.png";
            }
        }
        nom_fichier = "image/REV.png";

        return nom_fichier;
    }

    public void afficherVerso(Graphics g2D, TuileTerrain tuile)
    {
        
        try 
        {
            String nom_fichier = this.determinerNomFichier();
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
}