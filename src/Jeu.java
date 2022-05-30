import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//import javax.swing.Timer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Jeu extends JPanel implements Runnable
{
    public static int compteur = 0; 
    public static boolean compteur_en_cours = false;

    public  Timer timer;

    /*
    public static Timer timer = new Timer(1000,  new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Jeu.compteur=0;
            Jeu.compteur++;
            System.out.println(compteur);
            Jeu.compteur_en_cours = true;

            if(Jeu.compteur==3)
            {
                if(Plateau.hexagone_dectruction_tuile!=null && Plateau.hexagone_dectruction_tuile.getTuile()!=null)
                {
                    Plateau.hexagone_dectruction_tuile.getTuile().effetImmediatVerso();
                    Plateau.hexagone_dectruction_tuile.setTuile(null);
                }
                System.out.println("Jai fini");

                Plateau.hexagone_dectruction_tuile = null;
            }
        }
    });
*/
    public static Joueur joueur;
    public static List<Joueur> list_joueur = new ArrayList<Joueur>();
    public static List<P_Joueur> list_pionJoueur_sortie;
    public static int index_joueur = 0;
    public static int nombre_joueur;
    public static int action;
    public static List<Bateau> list_Bateau;
    public static List<Requin> list_requin;
    public static List<Baleine> list_baleine;
    public static List<Serpent> list_serpent;


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

    private JFrame app;
    private ZonePseudoJoueur zone_pseudo;
    private ZoneTuileEtPionJoueur zone_tuile_et_pion;
    private ZoneMenu zone_menu;
    private ZoneDe zone_de;

    private boolean rejouer;


    // **************************************    Constructeur   *********************************************** //

    public Jeu(JFrame app, List<Joueur> list_joueur) 
    {
        super();
        this.app = app;
        constructionJeu();
        Jeu.list_joueur = list_joueur;
        this.rejouer = false;
    }

    public void constructionJeu()
    {
        this.removeAll();
        this.setLayout(null);

        initialiserPartie();


        this.plateau = new Plateau(this);
        this.plateau.setBounds(0, 0, 1070, 700);
        this.add(this.plateau);

        this.zone_menu = new ZoneMenu(this);
        this.zone_menu.setBounds(1070, 0,360, 70);
        this.add(this.zone_menu);

        this.zone_pseudo = new ZonePseudoJoueur(Jeu.list_joueur);
        this.zone_pseudo.setBounds(1070, 70,360, 80);
        this.add(this.zone_pseudo);

        this.zone_joueur = new ZoneJoueur(this);
        this.zone_joueur.setBounds(1070, 150, 360, 50);
        this.add(this.zone_joueur);

        this.zone_tuile_et_pion = new ZoneTuileEtPionJoueur();
        this.zone_tuile_et_pion.setBounds(1070, 200,360, 450);
        this.add(this.zone_tuile_et_pion);

        this.zone_de = new ZoneDe(this);
        this.zone_de.setBounds(1070, 650,360, 150);
        this.add(this.zone_de);

        this.zone_pion = new ZonePion();
        this.zone_pion.setBounds(0, 700, 1050, 120);
        this.add(this.zone_pion);

        this.chronoEcran = new Thread(this);
        chronoEcran.start();

        timer = new Timer();

        this.revalidate();
        this.actualiser();
    }
    // **************************************    Methodes   *********************************************** //

    private void initialiserPartie()
    {

        // initialisation des variabbles statics
        Bateau.bateau_mouse_clicked = null;
        Bateau.mouse_moved = false;
        Bateau.mouse_clicked_origin = false;
        Bateau.mouse_clicked_destination = false;
        Bateau.bateau_mouse_moved = null;

        Jeu.premier_placement = false;
        Jeu.fin_placement_joueur = false;
        Jeu.fin_placement_bateau = false;
        Jeu.de_lance = false;
        Jeu.compteur_en_cours = false;
        Jeu.compteur = 0; 
        Jeu.nombre_placement_joueur = 0;
        Jeu.nombre_placement_bateau = 0;
        Jeu.compteur_tuile_terrain = 0;
        Jeu.action = 1;
        Jeu.index_joueur = 0;
        Jeu.list_Bateau = Bateau.initBateau();
        Jeu.list_baleine = Baleine.initBaleine();
        Jeu.list_requin = Requin.initRequin();
        Jeu.list_serpent = Serpent.initSerpent();
        Jeu.tuile_a_placer = TuileTerrain.melangeTabTuileTerrains();

        P_Joueur.pionJoueur_mouse_clicked = null;
        P_Joueur.pionJoueur_mouse_moved = null;
        P_Joueur.mouse_moved = false;
        P_Joueur.mouse_cliked = false;
        P_Joueur.descendre_bateau = false;

        AnimalDeMer.animal_mouse_clicked = null;
        AnimalDeMer.animal_mouse_moved = null;

        Plateau.mouse_moved = false;
        Plateau.hexagone_dectruction_tuile = null;
        Plateau.hexagone_mouse_moved = null;

        Verso.verso_jouer = null;


        // Affectation joueur 
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
       
        Jeu.joueur = Jeu.list_joueur.get(0);
        Jeu.list_pionJoueur_sortie = new ArrayList<>();
    }
    
    

    public void jouer(Position pos)
    {
        if(Jeu.premier_placement)
        {
            //placementAutomatiquePionJoueur();
            premier_placement(Jeu.joueur, pos);
            actualiser();
        }
        else 
        {
            if(Jeu.action==1)
            {
                if(Jeu.joueur!=null && !Jeu.joueur.list_Treserve.isEmpty())
                {
                    actionJouerTuile(Jeu.joueur, pos);
                }
                else
                {
                    Jeu.action = 2;
                }
            }
            if(Jeu.action==2)
            {
                actionPJoueurOuBateau(Jeu.joueur, pos);
            }
            if(Jeu.action==3)
            {
                actionDetruireTuile(Jeu.joueur, pos);
            }
            if(Jeu.action==4)
            {
                actionJouerDe(Jeu.joueur, pos);
            }
        }

        Bateau.mouse_clicked_origin = false;
        Bateau.mouse_clicked_destination = false;
        P_Joueur.mouse_cliked = false;
        P_Joueur.descendre_bateau = false;
        Bateau.bateau_mouse_clicked = null;
        P_Joueur.pionJoueur_mouse_clicked = null;
        AnimalDeMer.animal_mouse_clicked = null;
        Verso.verso_jouer = null;
        this.actualiser();
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
                Jeu.nombre_placement_bateau += 1;
                Jeu.nextJoueur();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Placer le bateau à coté de l'ile", "Erreur placement", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.finPremierPlacement(pos);
    }


    public void actionJouerTuile(Joueur Joueur, Position pos)
    {
        if(Verso.verso_jouer!=null)
        {
            if(Verso.verso_jouer.action.equals("serpent"))
            {
                if((AnimalDeMer.animal_mouse_clicked!=null) && (AnimalDeMer.animal_mouse_clicked instanceof Serpent))
                {
                    if(Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getTuile()==null && Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getListe_animaux().isEmpty() && Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getBateau()==null)
                    {
                        joueur.supprimerTuileReserve(Verso.verso_jouer);
                        Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].ajouterAnimalDeMer(AnimalDeMer.animal_mouse_clicked);
                        Jeu.action = 2;
                    }
                     else
                    {
                        JOptionPane.showMessageDialog(this, "Deplacement impossible", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "L'animal doit être un serpent de mer", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
            else if(Verso.verso_jouer.action.equals("baleine"))
            {
                if((AnimalDeMer.animal_mouse_clicked!=null) && (AnimalDeMer.animal_mouse_clicked instanceof Baleine))
                {
                    if(Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getTuile()==null && Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getListe_animaux().isEmpty() && Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getBateau()==null)
                    {
                        joueur.supprimerTuileReserve(Verso.verso_jouer);
                        Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].ajouterAnimalDeMer(AnimalDeMer.animal_mouse_clicked);
                        Jeu.action = 2;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Deplacement impossible", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "L'animal doit être une baleine", "Erreur", JOptionPane.ERROR_MESSAGE);

                }
            }
            else if(Verso.verso_jouer.action.equals("requin"))
            {
                if((AnimalDeMer.animal_mouse_clicked!=null) && (AnimalDeMer.animal_mouse_clicked instanceof Requin))
                {
                    if(Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getTuile()==null && Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getListe_animaux().isEmpty() && Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getBateau()==null)
                    {
                        joueur.supprimerTuileReserve(Verso.verso_jouer);
                        Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].ajouterAnimalDeMer(AnimalDeMer.animal_mouse_clicked);
                        Jeu.action = 2;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Deplacement impossible", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "L'animal doit être un requin", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(Verso.verso_jouer.action.equals("bateau"))
            {
                if(Bateau.bateau_mouse_clicked!=null)
                {
                    if(Bateau.bateau_mouse_clicked.deplacerPionBateau(Jeu.joueur, Bateau.bateau_mouse_clicked.getHexagone(), Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()]))
                    {
                        joueur.supprimerTuileReserve(Verso.verso_jouer);
                        Jeu.joueur.setNombre_deplacement(3);
                        Jeu.action = 2;
                    } 
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Vous devez choisir un bateau", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(Verso.verso_jouer.action.equals("dauphin"))
            {
                
               if(P_Joueur.pionJoueur_mouse_clicked!=null && P_Joueur.pionJoueur_mouse_clicked.getEst_nageur())
                {
                    if(Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].getTuile()!=null)
                    {
                        JOptionPane.showMessageDialog(this, "Mouvement vers l'ile impossible", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        P_Joueur.pionJoueur_mouse_clicked.setEst_nageur(false);
                        if(P_Joueur.pionJoueur_mouse_clicked.deplacerPionJoueur(P_Joueur.pionJoueur_mouse_clicked.getHexagone(), Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()]))
                        {
                            joueur.supprimerTuileReserve(Verso.verso_jouer);
                            Jeu.joueur.setNombre_deplacement(3);
                            Jeu.action = 2;
                        }
                        P_Joueur.pionJoueur_mouse_clicked.setEst_nageur(true);
                    }   
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Vous devez choisir un de vos pions qui soit nageur", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void actionPJoueurOuBateau(Joueur Joueur, Position pos) 
    {
        // Si le clique est un pion Joueur
        if(P_Joueur.pionJoueur_mouse_clicked !=null && P_Joueur.mouse_cliked && (Jeu.joueur!=null && P_Joueur.pionJoueur_mouse_clicked.getJoueur().getPseudo().equals(Jeu.joueur.getPseudo())))
        {  
            // Si le pion joueur se trouve sur un bateau et se deplace vers un hexagone 
            if(P_Joueur.descendre_bateau)
            {
                System.out.println("je descends d'un bateau");
                if( P_Joueur.pionJoueur_mouse_clicked.getBateau()!=null)
                {
                    Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].ajoutePionJoueur(P_Joueur.pionJoueur_mouse_clicked);
                }
            }

            // Si le pion joueur se deplace d'un hexagone vers un hexagone 
            else
            {                
                if(P_Joueur.pionJoueur_mouse_clicked.getHexagone()!=null)
                {
                    System.out.println("Clique sur hexagone pour joueur de hexagone a hexagone");

                    if(Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].verifieHexagonePlein())
                    {
                        JOptionPane.showMessageDialog(this, "Hexagone Plein", "Erreur Deplacement", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        P_Joueur.pionJoueur_mouse_clicked.deplacerPionJoueur(P_Joueur.pionJoueur_mouse_clicked.getHexagone(), Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()]);

                    }
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
                System.out.println("Avant deplacement : " + Jeu.joueur.getNombre_deplacement());
                Bateau.bateau_mouse_clicked.deplacerPionBateau(Jeu.joueur, Bateau.bateau_mouse_clicked.getHexagone(), Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()]);
                System.out.println("apres deplacement : " + Jeu.joueur.getNombre_deplacement());

            }
        }
        if(Jeu.joueur!=null && Jeu.joueur.getNombre_deplacement()==0)
        {
            this.timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    Jeu.action=3;
                    System.out.println("Fin action pionJoeur ou bateau");
                }
                
            }, 1000);
        }
    }

    public void actionDetruireTuile(Joueur Joueur, Position pos)
    {
        if(!Jeu.compteur_en_cours)
        {
            Plateau.hexagone_dectruction_tuile = Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()];

            if(Plateau.hexagone_dectruction_tuile!=null && Plateau.hexagone_dectruction_tuile.getTuile()!=null) //&& TuileTerrain.verifierRetirerTuileTerrain(Plateau.hexagone_dectruction_tuile))
            {
                Plateau.hexagone_dectruction_tuile.setDetruire_tuile(true);
               
                if(Plateau.hexagone_dectruction_tuile.getTuile()!=null)
                {
                    if(Plateau.hexagone_dectruction_tuile.getTuile().getVerso().getCouleur().equals("rouge"))
                    {
                        Joueur.getList_Treserve().add(Plateau.hexagone_dectruction_tuile.getTuile());
                    }
                }
                
                Jeu.compteur_en_cours = true;
                
                for(int i=0; i<Plateau.hexagone_dectruction_tuile.getListe_joueur().size();i++)
                {
                    Plateau.hexagone_dectruction_tuile.getListe_joueur().get(i).setEst_nageur(true);
                }
                //Jeu.timer.start();
                this.timer.schedule(new TimerTask() {

                    @Override
                    public void run() {

                        Plateau.hexagone_dectruction_tuile.getTuile().effetImmediatVerso();
                        Plateau.hexagone_dectruction_tuile.setTuile(null);
                        System.out.println("fin action jouer tuile ");
                        Plateau.hexagone_dectruction_tuile = null;
                        Jeu.compteur_en_cours = false;
                        Jeu.action=4;
                    }
                    
                }, 3000);
                
            }
        }
    }

    public void actionJouerDe(Joueur Joueur, Position pos)
    {
        if(AnimalDeMer.animal_mouse_clicked!=null && AnimalDeMer.animal_mouse_clicked.deplacer(AnimalDeMer.animal_mouse_clicked.getHexagone(), Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()]))
        {
            System.out.println("jouer de ");
            Plateau.map[pos.getNumero_ligne()][pos.getNumero_colone()].ajouterAnimalDeMer(AnimalDeMer.animal_mouse_clicked);
            AnimalDeMer.animal_mouse_clicked.attaquerEtJouerTuile();

            try 
            {
                Thread.sleep(1000);
            } catch (InterruptedException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Jeu.nextJoueur();
        }
    }
    public static void nextJoueur()
    {
        if(Jeu.joueur != null)
        {
            Jeu.joueur.list_pion_jouer.clear();
            Jeu.joueur.setNombre_deplacement(3);
        }
        Jeu.index_joueur = (Jeu.index_joueur+1)%Jeu.nombre_joueur;
        Jeu.joueur = Jeu.list_joueur.get(Jeu.index_joueur);
        if(!Jeu.joueur.getList_Treserve().isEmpty())
        {
            int option =JOptionPane.showConfirmDialog(null, "Voulez-vous uiliser vos tuiles ?", "Rejouer", JOptionPane.YES_NO_OPTION);
            if(option!=JOptionPane.OK_OPTION)
            {
                Jeu.action = 2;
            }
            else
            {
                Jeu.action = 1;
            }
        }
        else
        {
            Jeu.action = 2;
        }
    }

    public void finPremierPlacement(Position pos)
    {
        finPlacementBateau();
        finPlacementJoueur();

        if(Jeu.fin_placement_bateau && Jeu.fin_placement_joueur)
        {
            Jeu.premier_placement = false;
            this.jouer(pos);
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
            //Jeu.timer.stop();
            Jeu.compteur = 0;
            Plateau.hexagone_dectruction_tuile.setTuile(null);
            Plateau.hexagone_dectruction_tuile = null;
            Jeu.compteur_en_cours = false;
            Jeu.action = 3;
        }
    }


    public void actualiser()
    {
        this.revalidate();
        this.zone_joueur.repaint();
    
        zone_de.repaint();
        zone_tuile_et_pion.repaint();
        zone_pseudo.repaint();

        this.zone_pion.repaint();
        this.plateau.repaint();
    }

    public void placementAutomatiquePionJoueur()
    {
        List<P_Joueur> list_temp = new ArrayList<>();
        Random rand = new Random();


        if(!Jeu.list_joueur.isEmpty())
        {
            for(int i=0; i<Jeu.list_joueur.size(); i++)
            {
                for(int j=0; j<Jeu.list_joueur.get(i).list_pion.size(); j++)
                {
                    list_temp.add(Jeu.list_joueur.get(i).list_pion.get(j));
                }
                while(!list_temp.isEmpty())
                {
                    boolean placer = false;

                    while(placer == false)
                    {
                        int nombre_aleatoire_ligne = 3 + rand.nextInt(7);
                        int nombre_aleatoire_colonne = 2 + rand.nextInt(8);

                        if(Plateau.map[nombre_aleatoire_ligne][nombre_aleatoire_colonne]!=null && Plateau.map[nombre_aleatoire_ligne][nombre_aleatoire_colonne].getTuile()!=null && Plateau.map[nombre_aleatoire_ligne][nombre_aleatoire_colonne].getListe_joueur().isEmpty())
                        {
                            Plateau.map[nombre_aleatoire_ligne][nombre_aleatoire_colonne].ajoutePionJoueur(list_temp.get(0));
                            list_temp.remove(0);
                            placer = true;
                        }
                    }
                }
            }
        }

        if(!Jeu.list_Bateau.isEmpty())
        {
            for(int i=0; i<Jeu.nombre_joueur*2; i++)
            {
                boolean placer = false;
    
                while(!placer)
                {
                    int nombre_aleatoire_ligne = rand.nextInt(Plateau.nombre_ligne);
                    int nombre_aleatoire_colonne = rand.nextInt(Plateau.nombre_colonne);

                    Bateau bat = Jeu.list_Bateau.get(i);
    
                    if(Plateau.map[nombre_aleatoire_ligne][nombre_aleatoire_colonne]!=null && Jeu.list_Bateau.get(i).placer_bateau(Jeu.list_Bateau, Plateau.map[nombre_aleatoire_ligne][nombre_aleatoire_colonne]))
                    {
                        Plateau.map[nombre_aleatoire_ligne][nombre_aleatoire_colonne].ajouterBateau(bat);
                        placer = true;
                    }
                }
            }
        }
    }
    // **************************************    Setters   *********************************************** //


    public void setRejouer(boolean rejouer)
    {
        this.rejouer = rejouer;
    }

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


    public Thread getChronoEcran() {
        return chronoEcran;
    }

    public boolean getRejouer()
    {
        return rejouer;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        double drawInterval = 1000000000/60;
        double next_draw_time = System.nanoTime() + drawInterval;

        while(!this.getRejouer())
        {
            actualiser();
          
            try 
            {
                double remaining_time = next_draw_time - System.nanoTime();
                remaining_time = remaining_time/1000000;

                if(remaining_time<0)
                {
                    remaining_time = 0;
                }

                Thread.sleep((long)remaining_time);

                next_draw_time +=drawInterval;
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
                System.out.println("Erreur Chrono");
            }
        }
        
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