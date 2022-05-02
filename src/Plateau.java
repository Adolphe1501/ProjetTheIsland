import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.JPanel;


public class Plateau extends JPanel
{
    private static final int nombre_ligne = 13;
    private static final int nombre_colonne = 12;

    public static Hexagone map[][];
    public Jeu partie;

    public Plateau() 
    {
        //super();
        map = new Hexagone[nombre_ligne][nombre_colonne];        
    }

    public void paintComponent(Graphics g)
    {
        Graphics g2D = (Graphics2D) g;

        construirePlateau(g2D);
    }


    private void construirePlateau(Graphics g2D)
    {
        int decalageX = 40, decalageY = 0, k=0;
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        int[] debutX = {40, 80, 120, 120, 80, 40};
        int[] debutY = {20, 5, 20, 55, 70, 55};

        try {

            /*
            for(int point=0; point<Hexagone.getNpoints(); point++)
            {
    
                xPoints[point] = decalageX + debutX[point];
                yPoints[point] = decalageY + debutY[point];
                        
                    
            }

            Image image = ImageIO.read(new File("image/TP001.png"));
        
            g2D.drawImage(image, 80, 5, 80, 65, null);

            Hexagone hexagone = new Hexagone(xPoints, yPoints);

            g2D.setColor(Color.BLUE);

            g2D.drawPolygon(hexagone);
            */

            Image image = ImageIO.read(new File("image/TM001.png"));

            char[][] mapChargee = new char[nombre_ligne+1][nombre_colonne+1];
            mapChargee = chargerMap();



            for(int i = 0; i<nombre_ligne; i++)
            {
                for(int j = 0; j<nombre_colonne; j++)
                {
                    for(int point=0; point<Hexagone.getNpoints(); point++)
                    {
                        if(i%2==0)
                        {
                            xPoints[point] = decalageX + debutX[point] + j*80;
                            yPoints[point] = decalageY + debutY[point] + k*100;
                        }
                        else
                        {
                            xPoints[point] = debutX[point] + j*80;
                            yPoints[point] = debutY[point] + i*50;
                        }
                    }
                    if(mapChargee[i][j] == '0')
                    {
                        map[i][j] = null;
                    }
                    else
                    {
                        Hexagone hexagone = new Hexagone(xPoints, yPoints);
                        if(contientZoneIle(i, j))
                        {
                            hexagone.setZone_ile(true);
                            g2D.setColor(Color.red);

                            if(i%2==0)
                            {
                                int x =decalageX + debutX[0] + j*80;
                                int y = decalageY + debutY[1] + k*100;
                                g2D.drawImage(image, x, y, 80, 65, null);
                            }
                            else
                            {
                                int x = debutX[0] + j*80;
                                int y = debutY[1] + i*50;
                                g2D.drawImage(image, x, y, 80, 65, null);
                            }
                            
                        }
                        else
                        {
                            g2D.setColor(Color.black);
                        }
                        map[i][j] = hexagone;
                        g2D.drawPolygon(hexagone);
                    }
                }

                if(i%2==0)
                {
                    k++;
                }
            }
        } 
        catch (IOException e) 
        {
        // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Erreur Fichier non trouve");
        }

    }
    
    
    private boolean contientZoneIle_aux(int i, int j)
    {
        int Ymin = 3, Ymax = 9;
        int Xmin = 4, Xmax = 8;
        boolean ile = false;

        if(i==Ymin || i==Ymax)
        {
            if(j>=Xmin && j<=Xmax-1)
            {
                ile = true;
            }
        }
        else if(i==Ymin+1 || i==Ymax-1)
        {
            if(j>=Xmin-1 && j<=Xmax-1)
            {
                ile = true;
            }
        }
        else if(i==Ymin+2 || i==Ymax-2)
        {
            if(j>=Xmin-2 && j<=Xmax+1)
            {
                ile = true;
            }
        }
        else if(i==6)
        {
            if(j>=Xmin-2 && j<=Xmax)
            {
                ile = true;
            }
        }           
        return ile;
    }
    
    private char[][] chargerMap()
    {
         // une colonne suplémntaire pour le caractere 'Retour a la ligne'
         // une ligne suplementtaire pour le caractere '-1' de fin de ligne
        char[][] map = new char[nombre_ligne+1][nombre_colonne+1];

        try 
        {
            FileReader fichier = new FileReader("map.txt");

            BufferedReader reader = new BufferedReader(fichier);

            int count, i=0;
            do
            {
                count =reader.read(map[i]);
                i++;

            }while(count!=-1);

            reader.close();
        } 
        catch (FileNotFoundException e1) 
        {
            e1.printStackTrace();
            System.out.println("Erreur Ouverture");
        }
        catch(IOException e2)
        {
            System.out.println("Erreur Lecture");

        }
        
        return map;
    }
}


