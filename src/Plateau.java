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
    private static final int nombre_ligne = 13;
    private static final int nombre_colonne = 12;
    public static Hexagone map[][] = new Hexagone[nombre_ligne][nombre_colonne]; 

    public static boolean premier_placement = false;
    public static boolean destructionTuile = true;
    private static boolean mouse_moved = false;
    public static Hexagone hexagone_dectruction_tuile = null;
    private static Hexagone hexagone_mouse_moved = null;
    public static P_Joueur pionJoueur_mouse_clicked = null;
    public static P_Joueur pionJoueur_mouse_moved = null;
    public static Bateau bateau_mouse_clicked = null;
    public static Bateau bateau_mouse_moved = null;

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
                }
            }
            if(i%2==0)
            {
                k++;
            }
        }

        //map[0][3].ajoutePionJoueur(new P_Joueur("id1", "rouge", 1, null));
        //map[6][6].ajoutePionJoueur(new P_Joueur("id2", "vert", 1, null));
        map[10][7].ajouterBateau(new Bateau("id1"));
        map[10][8].ajouterBateau(new Bateau("id2"));
        //map[10][7].getBateau().ajoutePionJoueur(new P_Joueur("id3", "vert", 1, null));
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
        if(P_Joueur.mouse_cliked && Plateau.pionJoueur_mouse_clicked!=null && Plateau.pionJoueur_mouse_clicked.getHexagone()!=null)
        {
            Rectangle rect =  Plateau.pionJoueur_mouse_clicked.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
        if(Bateau.mouse_clicked_origin && Plateau.bateau_mouse_clicked!=null)
        {
            Rectangle rect =  Plateau.bateau_mouse_clicked.getBounds();
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
        else if(P_Joueur.mouse_moved && Plateau.pionJoueur_mouse_moved!=null &&  (Plateau.pionJoueur_mouse_moved.getHexagone()!=null || Plateau.pionJoueur_mouse_moved.getBateau()!=null)) 
        {
            Rectangle rect =  Plateau.pionJoueur_mouse_moved.getBounds();
            g2D.setColor(Color.blue);
            g2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
        else if(Bateau.mouse_moved && Plateau.bateau_mouse_moved!=null)
        {
            Rectangle rect =  Plateau.bateau_mouse_moved.getBounds();
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
            g2d.drawImage(image, 0, 0, 1050, 700, null);

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
        Position pos = determineHexagoneCliquer(e.getPoint());

        if(pos!=null)
        {
            System.out.println("Clique sur hexagone");

            // Si le clique est un pion Joueur
            if(Plateau.pionJoueur_mouse_clicked !=null && P_Joueur.mouse_cliked)
            {
                // Si le pion joueur se trouve sur un bateau et se deplace vers un hexagone
                if(P_Joueur.descendre_bateau)
                {
                    System.out.println("je descends d'un bateau");
                    if( Plateau.pionJoueur_mouse_clicked.getBateau()!=null)
                    {
                        map[pos.getNumero_ligne()][pos.getNumero_colone()].ajoutePionJoueur(Plateau.pionJoueur_mouse_clicked);
                        Plateau.pionJoueur_mouse_clicked.getBateau().supprimerPionjoueur(Plateau.pionJoueur_mouse_clicked);
                    }
                    
                    Plateau.bateau_mouse_clicked = null;
                    P_Joueur.descendre_bateau = false;
                }
                // Si le pion joueur ne se trouve pas encore sur le plateau et se deplace vers le plateau
                else if(Plateau.premier_placement)
                {
                    map[pos.getNumero_ligne()][pos.getNumero_colone()].ajoutePionJoueur(Plateau.pionJoueur_mouse_clicked);
                    Plateau.premier_placement = false;
                    if(this.jeu.getZone_joueur()!=null && this.jeu.getZone_joueur().getZone_tuile_et_pion()!=null)
                    {
                        this.jeu.getZone_joueur().getZone_tuile_et_pion().affichePionJoueurNonPlace();
                    }
                }
                // Si le pion joueur se deplace d'un hexagone vers un hexagone 
                else
                {
                    System.out.println("Clique sur hexagone pour joueur de hexagone a hexagone");
                    if(pionJoueur_mouse_clicked.getHexagone()!=null)
                    {
                        pionJoueur_mouse_clicked.getHexagone().supprimePionjoueur(pionJoueur_mouse_clicked);
                        map[pos.getNumero_ligne()][pos.getNumero_colone()].ajoutePionJoueur(pionJoueur_mouse_clicked);
                    }
                    
                }
                P_Joueur.mouse_cliked = false;
                Plateau.pionJoueur_mouse_clicked = null;
            }
            // si le clique est sur un bateau
            else if(Plateau.bateau_mouse_clicked!=null && Bateau.mouse_clicked_origin)
            {
                System.out.println("clique sur  hexagone pour bateau de hexagone a hexagone");
                // Le bateau se deplace d'un hexagone vers un hexagone
                if(Plateau.bateau_mouse_clicked.getHexagone()!=null)
                {
                    Plateau.bateau_mouse_clicked.getHexagone().suprimerBateau();
                    map[pos.getNumero_ligne()][pos.getNumero_colone()].ajouterBateau(Plateau.bateau_mouse_clicked);
                }
                Plateau.bateau_mouse_clicked = null;
                Bateau.mouse_clicked_origin = false;
            }
            // Clique pour detruire une tuile terrain
            else if(Plateau.destructionTuile && !Jeu.compteur_en_cours)
            {
                Plateau.hexagone_dectruction_tuile = map[pos.getNumero_ligne()][pos.getNumero_colone()];
                if(Plateau.hexagone_dectruction_tuile!=null)
                {
                    Plateau.hexagone_dectruction_tuile.setDetruire_tuile(true);
                    Jeu.timer.start();
                }
            }
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


