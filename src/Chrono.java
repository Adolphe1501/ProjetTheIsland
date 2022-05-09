

public class Chrono implements Runnable
{
    private final int pause = 3;
    private Jeu jeu;

    public Chrono(Jeu jeu)
    {
        this.jeu = jeu;
    }
    @Override
    public void run() 
    {
        while(true)
        {

            // Actualise le plateau a chaque tour de boucle
           if(jeu.plateau != null)
           {
               jeu.plateau.repaint();
           }

           // Actualise la zone des noms de joueur a chaque tour de boucle
           if(jeu.getZone_joueur()!=null)
           {
                jeu.getZone_joueur().repaint();
           }

           // Regle l'affichage des versos apres la destruction d'une tuile
           if(Jeu.compteur==3)
            {
                Jeu.timer.stop();
                Jeu.compteur = 0;
                Plateau.hexagone_dectruction_tuile.setTuile(null);
                Plateau.hexagone_dectruction_tuile = null;
                Jeu.compteur_en_cours = false;
                //Plateau.destructionTuile=false;
            }
            try 
            {
                Thread.sleep(pause);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
                System.out.println("Erreur Chrono");
            }
        }
    }
    
}
