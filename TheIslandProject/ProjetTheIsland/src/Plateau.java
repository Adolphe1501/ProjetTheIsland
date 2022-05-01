import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JPanel;


public class Plateau extends JPanel
{
    private static final int nombre_ligne = 13;
    private static final int nombre_colonne = 12;

    public static Hexagone map[][];
    public Jeu partie;

    public Plateau() 
    {
        super();
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
        String mapCharge[] = chargerMap();

        for(int i =0; i<nombre_ligne; i++)
        {            
            System.out.print(mapCharge[i]);
    
        }
        

        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        int[] debutX = {40, 80, 120, 120, 80, 40};
        int[] debutY = {15, 5, 15, 55, 65, 55};


        for(int i =0; i<nombre_ligne; i++)
        {            
            for(int j=0; j<nombre_colonne; j++)
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

                Hexagone hexagone = new Hexagone(xPoints, yPoints);
                map[i][j] = hexagone;

                g2D.setColor(Color.blue);
                g2D.drawPolygon(hexagone);
            }

            if(i%2==0)
            {
                k++;
            }
        }
    }

    private boolean contientZoneIle(int i, int j)
    {
        int Ymin = 3, Ymax = 9;
        int Xmin = 4, Xmax = 9;
        boolean ile = false;

        if(i>=Ymin && i<=Ymax) 
        {
            if(i==Ymin || i==Ymax)
            {
                if(j>Xmin && j<Xmax)
                {
                    ile = true;
                }
            }
            else if(i==Ymin+1 || i==Ymax-1)
            {
                if(j>Xmin && j<Xmax+1)
                {
                    ile = true;
                }
            }
            else if(i==Ymin+2 || i==Ymax-2)
            {
                if(j>Xmin-2 && j<Xmax+2)
                {
                    ile = true;
                }
            }
            else if(i==6)
            {
                if(j>3 && j<=10)
                {
                    ile = true;
                }
            }           
        }
        return ile;
    }
    
    private String[] chargerMap()
    {
        String map[] = new String[nombre_ligne];

        try 
        {
            FileReader fichier = new FileReader("map.txt");

            System.out.println("Erreur ****");
            BufferedReader reader = new BufferedReader(fichier);

            System.out.println("Erreur******************");
            for(int i = 0; i<nombre_ligne; i++)
            {
                map[i] = reader.readLine();
            }

            reader.close();
        } 
        catch (Exception e1) 
        {
            e1.printStackTrace();
            System.out.println("Erreur");
        }
        
        return map;
    }
}