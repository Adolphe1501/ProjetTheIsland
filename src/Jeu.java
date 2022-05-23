import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



public class Jeu extends JPanel
{
    public static int compteur = 0; 
    public static boolean compteur_en_cours = false;

    public static Timer timer = new Timer(1000,  new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Jeu.compteur++;
            System.out.println(compteur);
            Jeu.compteur_en_cours = true;

        }
    });

    public static Joueur joueur;
    public static List<Joueur> list_joueur = new ArrayList<Joueur>();
    public static int index_joueur = 0;
    public static int nombre_joueur;
    public static int action;
    public static List<Bateau> list_Bateau;
    public static List<Requin> list_requin;
    public static List<Baleine> list_baleine;


    public static TuileTerrain[] tuile_a_placer;
    public static int compteur_tuile_terrain;

    // Variable premier placement
    public static boolean premier_placement = false;
    public static boolean fin_placement_joueur = false;
    public static boolean fin_placement_bateau = false;
    public static int nombre_placement_joueur = 0;
    public static int nombre_placement_bateau = 0;

    public static Boolean de_lance = false;

    // Decoupage de l'ecran
    public Plateau plateau;
    private ZoneJoueur zone_joueur;
    private ZonePion zone_pion;
    private Thread chronoEcran;

    private App app;


    // **************************************    Constructeur   *********************************************** //

    public Jeu(App app) 
    {
        super();
        this.app = app;
        constructionJeu();
    }

    public void constructionJeu()
    {
        this.removeAll();
        this.setLayout(null);

        initialiserPartie();


        this.plateau = new Plateau(this);
        this.plateau.setBounds(0, 0, 1070, 700);
        this.add(this.plateau);

        this.zone_joueur = new ZoneJoueur(this);
        this.zone_joueur.setBounds(1070, 0, 360, 820);
        this.add(this.zone_joueur);

        this.zone_pion = new ZonePion();
        this.zone_pion.setBounds(0, 700, 1050, 120);
        this.add(this.zone_pion);

        this.chronoEcran = new Thread(new Chrono(this));
        chronoEcran.start();

        this.revalidate();
        this.actualiser();
    }
    // **************************************    Methodes   *********************************************** //

    private void initialiserPartie()
    {
        Jeu.list_joueur = new ArrayList<Joueur>();
        Jeu.list_joueur.add(new Joueur("Adolphe", null));
        Jeu.list_joueur.add(new Joueur("Emile", null));
        //Jeu.list_joueur.add(new Joueur("Rochdi" , null));
        //Jeu.list_joueur.add(new Joueur("Waffa", null));
        Jeu.nombre_joueur = list_joueur.size();

        Joueur joueur1 = Jeu.list_joueur.get(0);
        Joueur joueur2 = Jeu.list_joueur.get(1);

        List<P_Joueur> temp1 = P_Joueur.initPJoueur("vert", joueur1);
        List<P_Joueur> temp2 = P_Joueur.initPJoueur("rouge", joueur2);

        joueur1.setList_pion(temp1);
        joueur2.setList_pion(temp2);

        TuileTerrain[] tuilesTerrain = TuileTerrain.initTuileTerrains();
        for(int i=0; i<7; i++)
        {
            joueur1.getList_Treserve().add(tuilesTerrain[i]);
        }
       
        Jeu.joueur =Jeu.list_joueur.get(0);

        Jeu.index_joueur = 0;
        
        Jeu.action = 3;
        Jeu.list_Bateau = Bateau.initBateau();
        Jeu.list_baleine = Baleine.initBaleine();
        Jeu.list_requin = Requin.initRequin();
    
        Jeu.tuile_a_placer = TuileTerrain.melangeTabTuileTerrains();
        Jeu.compteur_tuile_terrain = 0;
    
        // Variable premier placement
        Jeu.premier_placement=false;
        Jeu.fin_placement_joueur = false;
        Jeu.fin_placement_bateau = false;
        Jeu.nombre_placement_joueur = 0;
        Jeu.nombre_placement_bateau = 0;
    
        Jeu.de_lance = false;
    
    }
    

    public void jouer(Position pos)
    {
        if(Jeu.premier_placement)
    
        {
            premier_placement(Jeu.joueur, pos);
        }
        else 
        {
            if(Jeu.action==1)
            {

            }
            if(Jeu.action==3)
            {
                actionPJoueurOuBateau(Jeu.joueur, pos);
            }
            if(Jeu.action==3 )
            {
                System.out.println("Detruire tuile");   
                actionDetruireTuile(Jeu.joueur, pos);
            }
            else
            {

            }
        }
    }
    public void premier_placement(Joueur Joueur, Position pos)
    {
        // Si le clique est un pion Joueur
        if(P_Joueur.pionJoueur_mouse_clicked !=null && P_Joueur.mouse_cliked)
        {
            if(P_Joueur.pionJoueur_mouse_clicked.placer_pion(Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()]))
            {
                Jeu.nextJoueur();
                Jeu.nombre_placement_joueur += 1; 
                System.out.println("nombre de placement " + Jeu.nombre_placement_joueur);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Placer le pion sur l'ile", "Erreur placement", JOptionPane.ERROR_MESSAGE);
            }
        }
        // si le clique est sur un bateau
        else if(Bateau.bateau_mouse_clicked!=null && Bateau.mouse_clicked_origin) //&& Jeu.fin_placement_joueur)
        {
            if(Bateau.bateau_mouse_clicked.placer_bateau(Jeu.list_Bateau, Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()]))
            {   
                Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].ajouterBateau(Bateau.bateau_mouse_clicked);
                System.out.println("bateau deposer");
                Jeu.nombre_placement_bateau += 1;
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Placer le bateau à coté de l'ile", "Erreur placement", JOptionPane.ERROR_MESSAGE);
            }
        }
        Bateau.mouse_clicked_origin = false;
        P_Joueur.mouse_cliked = false;
        Bateau.bateau_mouse_clicked = null;
        P_Joueur.pionJoueur_mouse_clicked = null;

        this.finPremierPlacement();
        this.actualiser();
    }

    public void actionPJoueurOuBateau(Joueur Joueur, Position pos) 
    {
        // Si le clique est un pion Joueur
        if(P_Joueur.pionJoueur_mouse_clicked !=null && P_Joueur.mouse_cliked)
        {  
            System.out.println(" Deplacement restant " + Jeu.joueur.getNombre_deplacement());

            // Si le pion joueur se trouve sur un bateau et se deplace vers un hexagone 
            if(P_Joueur.descendre_bateau)
            {
                System.out.println("je descends d'un bateau");
                if( P_Joueur.pionJoueur_mouse_clicked.getBateau()!=null)
                {
                    Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].ajoutePionJoueur(P_Joueur.pionJoueur_mouse_clicked);
                    P_Joueur.pionJoueur_mouse_clicked.getBateau().supprimerPionjoueur(P_Joueur.pionJoueur_mouse_clicked);
                }
            }
            // Si le pion joueur se deplace d'un hexagone vers un hexagone 
            else
            {
                System.out.println("Clique sur hexagone pour joueur de hexagone a hexagone");
                if(P_Joueur.pionJoueur_mouse_clicked.getHexagone()!=null && P_Joueur.pionJoueur_mouse_clicked.deplacerPionJoueur(P_Joueur.pionJoueur_mouse_clicked.getHexagone(), Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()]))
                {
                    System.out.println(" Deplacement restant " + Jeu.joueur.getNombre_deplacement());
                    P_Joueur.pionJoueur_mouse_clicked.getHexagone().supprimePionjoueur(P_Joueur.pionJoueur_mouse_clicked);
                    Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].ajoutePionJoueur(P_Joueur.pionJoueur_mouse_clicked);
                }
            }
        }
         // si le clique est sur un bateau
        else if(Bateau.bateau_mouse_clicked!=null && Bateau.mouse_clicked_origin)
        {
            System.out.println("clique sur  hexagone pour bateau de hexagone a hexagone");
            
            // Le bateau se deplace d'un hexagone vers un hexagone
            if(Bateau.bateau_mouse_clicked.getHexagone()!=null)
            {
                Bateau.bateau_mouse_clicked.getHexagone().suprimerBateau();
                Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].ajouterBateau(Bateau.bateau_mouse_clicked);
            }
        }

        P_Joueur.mouse_cliked = false;
        P_Joueur.pionJoueur_mouse_clicked = null;
        Bateau.bateau_mouse_clicked = null;
        Bateau.mouse_clicked_origin = false;
        this.actualiser();
    }

    public void actionDetruireTuile(Joueur Joueur, Position pos)
    {
        if(!Jeu.compteur_en_cours)
        {
            Plateau.hexagone_dectruction_tuile = Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()];
            if(Plateau.hexagone_dectruction_tuile!=null)
            {
                Plateau.hexagone_dectruction_tuile.setDetruire_tuile(true);
               
                if(Plateau.hexagone_dectruction_tuile.getTuile()!=null)
                {
                    if(Plateau.hexagone_dectruction_tuile.getTuile().getVerso().getCouleur().equals("rouge"))
                    {
                        Joueur.getList_Treserve().add(Plateau.hexagone_dectruction_tuile.getTuile());
                    }
                }
                Jeu.timer.start();
            }
        }
        this.actualiser();
    }

    public static void nextJoueur()
    {
        Jeu.index_joueur ++;
        if(Jeu.index_joueur==Jeu.nombre_joueur)
        {
            Jeu.index_joueur = 0;
        }
        Jeu.joueur = Jeu.list_joueur.get(Jeu.index_joueur);
    }

    public void finPremierPlacement()
    {
        finPlacementBateau();
        finPlacementJoueur();

        if(Jeu.fin_placement_bateau && Jeu.fin_placement_joueur)
        {
            Jeu.premier_placement = false;
            System.out.println("fin premier placement");
        }
    }
    private void finPlacementJoueur()
    {
        if(Jeu.nombre_placement_joueur==Jeu.nombre_joueur*10)
        {
            Jeu.fin_placement_joueur = true;
        }
    }
      
    private void finPlacementBateau()
    {
        if(Jeu.nombre_placement_bateau==Jeu.nombre_joueur*2)
        {
            Jeu.fin_placement_bateau = true;
        }
    }

    public void finAction()
    {
        finActionJoueurOuBateau();
        finActionDetruireTuile();
    }

    private void finActionJoueurOuBateau()
    {
        if(Jeu.joueur.getNombre_deplacement()<0)
        {
            Jeu.action = 3;
        }
    }

    private void finActionDetruireTuile()
    {
        if(Jeu.compteur==3)
        {
            System.out.println("jai detrui");
            Jeu.timer.stop();
            Jeu.compteur = 0;
            Plateau.hexagone_dectruction_tuile.setTuile(null);
            Plateau.hexagone_dectruction_tuile = null;
            Jeu.compteur_en_cours = false;
            Jeu.action = 3;
        }
    }


    public void actualiser()
    {
        this.zone_joueur.repaint();
        this.zone_pion.repaint();
        this.plateau.repaint();
    }
    // **************************************    Setters   *********************************************** //


    // **************************************    Getters   *********************************************** //

    public ZoneJoueur getZone_joueur() {
        return zone_joueur;
    }

    public ZonePion getZone_pion() {
        return zone_pion;
    }

    public int getNombre_joueur() {
        return nombre_joueur;
    }

    public App getApp() {
        return app;
    }
}



/*
    private void provisoireInitPartieJoueur()
    {
        Jeu.tuile_a_placer = TuileTerrain.melangeTabTuileTerrains();
        Jeu.compteur_tuile_terrain = 0;
        Jeu.premier_placement=false;
        Jeu.action = 3;
        Jeu.list_joueur = new ArrayList<Joueur>();

        TuileTerrain[] tuilesTerrain = TuileTerrain.initTuileTerrains();

        Jeu.list_Bateau = Bateau.initBateau();


        Jeu.nombre_joueur = list_joueur.size();
        Joueur joueur1 = Jeu.list_joueur.get(0);
        Joueur joueur2 = Jeu.list_joueur.get(1);

        List<P_Joueur> temp1 = P_Joueur.initPJoueur("vert", joueur1);
        List<P_Joueur> temp2 = P_Joueur.initPJoueur("rouge", joueur2);

        joueur1.setList_pion(temp1);
        joueur2.setList_pion(temp2);

        for(int i=0; i<7; i++)
        {
            joueur1.getList_Treserve().add(tuilesTerrain[i]);
        }
        
        Jeu.joueur =Jeu.list_joueur.get(0);

       

    }
    */