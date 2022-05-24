import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

// **************************************    Constructeur   *********************************************** //

public class Plateau extends JPanel implements MouseListener, MouseMotionListener
{
    public static final int nombre_ligne = 13;
    public static final int nombre_colonne = 12;
    public static Hexagone map[][] = new Hexagone[nombre_ligne][nombre_colonne]; 

    private static boolean mouse_moved = false;
    public static Hexagone hexagone_dectruction_tuile = null;
    private static Hexagone hexagone_mouse_moved = null;

    private Jeu jeu;

    
    public Plateau(Jeu jeu) 
    {
        super();
        this.jeu = jeu;
        setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.construirePlateau();
    }

    // **************************************    Methodes   *********************************************** //

    public void paintComponent(Graphics g)
    {
        Graphics g2D = (Graphics2D) g;

        afficherBackground(g2D);
        afficherPlateau(g2D);
        afficheEffetMouseMoved(g2D);
        afficheEffetMouseClicked(g2D);
    }

    // Construis les hexagone du plateau
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
                    if(!Jeu.list_serpent.isEmpty())
                    {
                        map[i][j].placerSerpentDeMer(i, j, Jeu.list_serpent.get(0));
                    }
                }
            }
            if(i%2==0)
            {
                k++;
            }
        }

        map[9][6].ajoutePionJoueur(Jeu.list_joueur.get(0).getList_pion().get(2));
        map[9][7].ajoutePionJoueur(Jeu.list_joueur.get(0).getList_pion().get(3));
        /*
        //map[0][3].ajoutePionJoueur(new P_Joueur("id1", "rouge", 1, null));
        //map[6][6].ajoutePionJoueur(new P_Joueur("id2", "vert", 1, null));
        map[10][7].ajouterBateau(new Bateau("id1"));
        map[10][8].ajouterBateau(new Bateau("id2"));
        //map[10][7].getBateau().ajoutePionJoueur(new P_Joueur("id3", "vert", 1, null));
        Jeu.list_joueur.get(0).getList_pion().get(2).setEst_nageur(true);
        map[10][6].ajoutePionJoueur(Jeu.list_joueur.get(0).getList_pion().get(2));
        map[6][6].ajoutePionJoueur(Jeu.list_joueur.get(0).getList_pion().get(3));
        TuileTerrain tuile = new Montagne("id25", new Verso("vert", "tourbillon"));
        tuile.setHexagone(map[9][7]);
        map[9][7].setTuile(tuile);
        map[9][8].ajouterAnimalDeMer(new Serpent("id3"));
        map[9][8].ajouterAnimalDeMer(new Baleine("id5"));
        map[9][8].ajouterAnimalDeMer(new Requin("id6"));

        map[8][9].ajouterAnimalDeMer(new Serpent("id33"));
        map[8][9].ajouterAnimalDeMer(new Baleine("id54"));
        //map[8][9].ajouterAnimalDeMer(new Requin("id62"));
        //map[8][9].ajoutePionJoueur(Jeu.list_joueur.get(0).getList_pion().get(4));
        map[8][9].ajoutePionJoueur(Jeu.list_joueur.get(0).getList_pion().get(5));
        map[8][9].ajoutePionJoueur(Jeu.list_joueur.get(0).getList_pion().get(6));


      
        map[9][10].ajouterAnimalDeMer(new Baleine("id34"));
        map[9][10].ajouterAnimalDeMer(new Requin("id34"));
        map[9][10].ajouterBateau(new Bateau("id7"));
        map[9][10].ajouterAnimalDeMer(new Serpent("id45"));

        


        map[9][9].ajouterAnimalDeMer(new Serpent("id5"));
        */

    }

    // Affiche le plateau avec les hexagones
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
    
    // Charge la map dans un tabeau de caractères
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

    // Determine la position de la souris sur plateau
    private Position determineHexagoneCliquer(Point p)
    {
        Position pos = null;
        for(int i=0; i<nombre_ligne; i++)
        {
            for(int j=0; j<nombre_colonne; j++)
            {
                if(map[i][j]!=null && map[i][j].contains(p))
                {
                    pos = new Position(i, j);
                    return pos;
                }
            }
        }
        return pos;
    }

    // Affiche les effets d' un clique sur un pion joueur ou bateau
    private void afficheEffetMouseClicked(Graphics g2D)
    {
        if(P_Joueur.mouse_cliked && P_Joueur.pionJoueur_mouse_clicked!=null && (P_Joueur.pionJoueur_mouse_clicked.getHexagone()!=null || P_Joueur.pionJoueur_mouse_clicked.getBateau()!=null))
        {
            Rectangle rect =  P_Joueur.pionJoueur_mouse_clicked.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
        if(Bateau.mouse_clicked_origin && Bateau.bateau_mouse_clicked!=null)
        {
            Rectangle rect =  Bateau.bateau_mouse_clicked.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    // Affiche les effets du mouvement de la souris sur un hexagone, un pion joueur ou bateau
    private void afficheEffetMouseMoved(Graphics g2D)
    {
        int xPoints[] = new int[6];
        int[] yPoints = new int[6];
        
        if(Plateau.hexagone_mouse_moved != null && Plateau.mouse_moved && !P_Joueur.mouse_moved && !Bateau.mouse_moved)
        {
            for(int i=0; i<5; i++)
            {
                for(int j=0; j<6; j++)
                {
                    xPoints[j] = Plateau.hexagone_mouse_moved.xpoints[j] + i;
                    yPoints[j] = Plateau.hexagone_mouse_moved.ypoints[j] + i;
                }
                g2D.setColor(Color.red);
                g2D.drawPolygon(xPoints, yPoints, 6);
            }
        }
        else if(P_Joueur.mouse_moved && P_Joueur.pionJoueur_mouse_moved!=null &&  (P_Joueur.pionJoueur_mouse_moved.getHexagone()!=null || P_Joueur.pionJoueur_mouse_moved.getBateau()!=null)) 
        {
            Rectangle rect =  P_Joueur.pionJoueur_mouse_moved.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
        else if(Bateau.mouse_moved && Bateau.bateau_mouse_moved!=null)
        {
            Rectangle rect =  Bateau.bateau_mouse_moved.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
        else if(AnimalDeMer.animal_mouse_moved!=null)
        {
            Rectangle rect = AnimalDeMer.animal_mouse_moved.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
   
    // Affiche le background du plateau
    private void afficherBackground(Graphics g2d)
    {
        File file = new File("image/plateau.jpg");

        try
        {
            Image image = ImageIO.read(file);
            g2d.drawImage(image, 0, 0, 1070, 700, null);

        }
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("Erreur Fichier non trouve");
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        /*
        Position pos = determineHexagoneCliquer(e.getPoint());

        if(pos!=null)
        {
            System.out.println("Clique sur hexagone");
           
            jeu.jouer(pos);
        }
        */
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        Position pos = determineHexagoneCliquer(e.getPoint());

        if(pos!=null)
        {
            System.out.println("Clique sur hexagone");
           
            jeu.jouer(pos);
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {   
        mouse_moved = false;
        hexagone_mouse_moved = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        Position pos = determineHexagoneCliquer(e.getPoint());

        if(pos != null)
        {
            int i = pos.getNumero_ligne();
            int j = pos.getNumero_colone();
            hexagone_mouse_moved = map[i][j];
            mouse_moved = true;
        }
        else
        {
            mouse_moved = false;
            hexagone_mouse_moved = null;
        }
        this.repaint();
    }
    // **************************************    Getters   *********************************************** //

    public static int getNombreLigne() {
        return nombre_ligne;
    }

    public static int getNombreColonne() {
        return nombre_colonne;
    }

    public Jeu getJeu() {
        return jeu;
    }

}


