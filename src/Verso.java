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

    // **************************************    Getters   *********************************************** //

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
                nom_fichier = "image/VOV.png";
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