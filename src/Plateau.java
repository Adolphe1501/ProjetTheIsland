import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Point;


import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class Plateau extends JPanel implements MouseListener
{
    private static final int nombre_ligne = 13;
    private static final int nombre_colonne = 12;


    public static Hexagone map[][] = new Hexagone[nombre_ligne][nombre_colonne]; 
    public Jeu partie;

    public Plateau() 
    {
        super();
        
        setLayout(null);
        this.addMouseListener(this);
        construirePlateau();     
    }

    public void paintComponent(Graphics g)
    {
        Graphics g2D = (Graphics2D) g;
        afficherBackground(g2D);
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
                    map[i][j] = null;
                }
                else
                {
                    Position  position = new Position(i, j);  
                    map[i][j] = new Hexagone(xPoints, yPoints, position, this);
                }
            }
            if(i%2==0)
            {
                k++;
            }
        }
        //map[6][5].setBateau(new Bateau("id_bateau"));
        map[6][6].getListe_joueur().add(new P_Joueur("id_P_joueur", "couleur", 1, null));
        map[6][6].getListe_joueur().add(new P_Joueur("id_P_joueur", "couleur", 1, null));
        //map[6][6].getListe_joueur().add(new P_Joueur("id_P_joueur", "couleur", 1, null));
        //map[6][6].getListe_joueur().add(new P_Joueur("id_P_joueur", "couleur", 1, null));
        //map[6][6].getListe_joueur().add(new P_Joueur("id_P_joueur", "couleur", 1, null));
        //map[6][6].getListe_joueur().add(new P_Joueur("id_P_joueur", "couleur", 1, null));
    }
    
    public void afficherPlateau(Graphics g2D)
    {
        //P_Joueur joueur = new P_Joueur("id_P_joueur", "couleur", 1,null);
        //joueur.afficherPionJoueur(this);

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

    private Hexagone determineHexagoneCliquer(Point p)
    {
        for(int i=0; i<nombre_ligne; i++)
        {
            for(int j=0; j<nombre_colonne; j++)
            {
                if(map[i][j]!=null && map[i][j].contains(p))
                {
                    return map[i][j];
                }
            }
        }
        return null;
    }


    private void afficherBackground(Graphics g2d)
    {
        File file = new File("image/plateau.jpg");

        try
        {
            Image image = ImageIO.read(file);
            g2d.drawImage(image, 0, 0, 1050, 700, null);

        }
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("Erreur Fichier non trouve");
        }
        
    }

    public static int getNombreLigne() {
        return nombre_ligne;
    }

    public static int getNombreColonne() {
        return nombre_colonne;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Hexagone hexagone = determineHexagoneCliquer(e.getPoint());

        System.out.println("clique");
        if(hexagone !=null)
        {
            //Rectangle rec = hexagone.getBounds();

            System.out.println(hexagone.xpoints[0] + " et " + hexagone.ypoints[1]);

            hexagone.detruireTuileTerrain();
            this.repaint();
            //System.out.println("numero ligne : " + hexagone.getPosition().getNumero_ligne() + " numero col : " + hexagone.getPosition().getNumero_colone());
        }
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


