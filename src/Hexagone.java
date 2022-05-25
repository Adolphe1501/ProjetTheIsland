
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Hexagone extends Polygon {
    private static TuileTerrain[] tuile_a_placer = TuileTerrain.melangeTabTuileTerrains();
    private static int compteur_tuile_terrain = 0;

    private Plateau plateau;
    private Bateau bateau;
    private List<P_Joueur> liste_joueur;
    private List<AnimalDeMer> liste_animaux;
    private Boolean zone_ile;
    private Position position;
    private TuileTerrain tuile;
    private boolean centrePlateau;
    private Boolean detruire_tuile;

    private static final int nPoints = 6;
    public Hexagone(){}

    public Hexagone(int[] xPoints, int[] yPoints, Position position, Plateau plateau) {
        super(xPoints, yPoints, nPoints);
        this.position = position;
        this.plateau = plateau;

        this.liste_animaux = new ArrayList<AnimalDeMer>();
        this.liste_joueur = new ArrayList<P_Joueur>();
        this.setBateau(null);
        this.zone_ile = false;
        this.tuile = null;
        this.centrePlateau = false;
        this.detruire_tuile = false;

        this.contientZoneIle();
        this.determinerCentrePlateau();
        this.placerTuileTerrain(tuile_a_placer[compteur_tuile_terrain]);
    }

    public Bateau getBateau() {
        return bateau;
    }

    public void setBateau(Bateau bateau) {
        this.bateau = bateau;
    }
    public void supprimerBateau() {
        this.bateau = null;
    }

    public void setlistJoueur(P_Joueur pion)
    {
        this.liste_joueur.add(pion);
    }
  
    public void add_pion(P_Joueur pion)
    {
        this.liste_joueur.add(pion);
    }
    public void addPionMer(AnimalDeMer a)
    {
        this.liste_animaux.add(a);
    }
    public boolean getzone_ile()
    {
        return this.zone_ile;
    }

    public void afficherHexagone(Graphics g2D) {
        if (this.zone_ile && !this.centrePlateau) {
            g2D.setColor(Color.red);
            if (this.tuile != null) {
                if (!this.detruire_tuile) {
                    this.tuile.afficherTuileTerrain(g2D, null);
                } else {
                    // System.out.println("*********************");

                    this.tuile.getVerso().afficherVerso(g2D, this.tuile);
                }

            }
        } else {
            g2D.setColor(Color.black);

        }
        g2D.drawPolygon(this);
        this.AfficherPion();
    }

    // determine si l'hexagone fait partie de l'ile
    public void contientZoneIle() {
        Boolean res = contientZoneIleAux(this.position.getNumero_ligne(), this.position.getNumero_colone());
        if (res) {
            // placerTuileTerrain(null);
            this.setZone_ile(true);
        }
    }

    private boolean contientZoneIleAux(int i, int j) {
        int Ymin = 3, Ymax = 9;
        int Xmin = 4, Xmax = 8;
        boolean ile = false;

        if (i == Ymin || i == Ymax) {
            if (j >= Xmin && j <= Xmax - 1) {
                ile = true;
            }
        } else if (i == Ymin + 1 || i == Ymax - 1) {
            if (j >= Xmin - 1 && j <= Xmax - 1) {
                ile = true;
            }
        } else if (i == Ymin + 2 || i == Ymax - 2) {
            if (j >= Xmin - 2 && j <= Xmax + 1) {
                ile = true;
            }
        } else if (i == 6) {
            if (j >= Xmin - 2 && j <= Xmax) {
                ile = true;
            }
        }
        return ile;
    }

    // Determine l'hexagone qui represente le centre du plateau
    private void determinerCentrePlateau() {
        int i = this.position.getNumero_ligne();
        int j = this.position.getNumero_colone();

        if (i == 6 && j == 5) {
            this.setCentrePlateau(true);
        }
    }

    // Place une tuile dans un hexagone
    public void placerTuileTerrain(TuileTerrain tuile) {
        if (this.zone_ile && !this.centrePlateau) {
            // System.out.println("c :" + compteur_tuile_terrain + " zone ile " +
            // this.zone_ile + " centre plateau " + this.centrePlateau);
            this.setTuile(tuile);
            tuile.setHexagone(this);
            compteur_tuile_terrain++;
        }
    }

    public void detruireTuileTerrain() {
        if (this.tuile != null) {
            this.detruire_tuile = true;

            this.plateau.repaint();

            // try
            // {
            // Thread.sleep(2000);
            // this.plateau.repaint();
            // Thread.sleep(2000);
            // this.plateau.repaint();

            // } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            // }

            // this.tuile = null;

        }

    }

    public void AfficherPion() {
        List<P_Joueur> list_j = this.getListe_joueur();
        int nombrePion = this.liste_joueur.size() + this.liste_animaux.size();
        int k = 0;

        Rectangle rect = this.getBounds();
        double w = rect.getWidth();
        double h = rect.getHeight();
        double x = rect.getX();
        double y = rect.getY();

        if (this.bateau == null && !list_j.isEmpty()) {
            for (int i = 0; i < nombrePion; i++) {
                if (nombrePion == 1) {
                    x += 20;
                    y += 12;
                    w = 40;
                    h = 40;
                } else if (nombrePion == 2) {
                    x = rect.getX() + 5 + i * 40;
                    y = rect.getY() + 15;
                    w = 30;
                    h = 30;
                } else if (nombrePion == 3) {
                    x = rect.getX() + 5 + i * 25;
                    y = rect.getY() + 15;
                    w = 20;
                    h = 30;
                } else if (nombrePion >= 3 && nombrePion <= 6) {
                    w = 20;
                    h = 20;
                    if (i < 3) {
                        x = rect.getX() + 5 + i * 25;
                        y = rect.getY() + 15;
                    } else {
                        x = rect.getX() + 5 + k * 25;
                        y = rect.getY() + 35;
                        k++;
                    }
                }
                list_j.get(i).afficherPionJoueur(this.plateau, (int) x, (int) y, (int) w, (int) h);
            }
        } else if (this.bateau != null) {
            x = rect.getX() - 5;
            y = rect.getY() - 15;
            w = 80;
            h = 50;
            this.bateau.afficherBateau(this.plateau, (int) x, (int) y, (int) w, (int) h);
        }
    }

    public void detruire_bateau() {
        // TODO implement here
    }

    public void sortir_joueur() {
        // TODO implement here
    }

    public List<P_Joueur> getListe_joueur() {
        return liste_joueur;
    }

    public List<AnimalDeMer> getListe_animaux() {
        return liste_animaux;
    }

    public void setListe_animaux(List<AnimalDeMer> liste_animaux) {
        this.liste_animaux = liste_animaux;
    }

    public Boolean getZone_ile() {
        return zone_ile;
    }

    public void setZone_ile(Boolean zone_ile) {
        this.zone_ile = zone_ile;
    }

    public static int getNpoints() {
        return nPoints;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TuileTerrain getTuile() {
        return tuile;
    }

    public void setTuile(TuileTerrain tuile) {
        this.tuile = tuile;
    }

    public boolean getCentrePlateau() {
        return centrePlateau;
    }

    public void setCentrePlateau(boolean centrePlateau) {
        this.centrePlateau = centrePlateau;
    }

}