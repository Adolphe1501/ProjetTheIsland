import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;


public class Plateau extends JPanel
{
    private static final int nombre_ligne = 13;
    private static final int nombre_colonne = 12;

    public static Hexagone map[][] = new Hexagone[nombre_ligne][nombre_colonne]; 
    public Jeu partie;

    public Plateau() 
    {
        super();
        construirePlateau();     
    }

    public void paintComponent(Graphics g)
    {
        Graphics g2D = (Graphics2D) g;

        afficherPlateau(g2D);
    }

    private void construirePlateau()
    {
        int decalageX = 40, decalageY = 0, k=0;
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        int[] debutX = {40, 80, 120, 120, 80, 40};
        int[] debutY = {20, 5, 20, 55, 70, 55};

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
                    this.map[i][j] = null;
                }
                else
                {
                    Position  position = new Position(i, j);
                    Hexagone hexagone = new Hexagone(xPoints, yPoints, position);
                    
                    map[i][j] = hexagone;
                }
                
            }
            if(i%2==0)
            {
                k++;
            }
        }

    }
    
   
    public void afficherPlateau(Graphics g2D)
    {
        for(int i = 0; i<nombre_ligne; i++)
        {
            for(int j = 0; j<nombre_colonne; j++)
            {
                if(map[i][j] != null)
                {
                    map[i][j].afficherHexagone(g2D);
                }
            }
        }
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

    public static int getNombreLigne() {
        return nombre_ligne;
    }

    public static int getNombreColonne() {
        return nombre_colonne;
    }
}


