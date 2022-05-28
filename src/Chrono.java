

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
        while(!this.jeu.getRejouer())
        {

            jeu.actualiser();
          
           if(Jeu.compteur==4)
            {
                System.out.println("j'arrete le chrono");
                Jeu.timer.stop();
                Jeu.compteur = 0;

                Jeu.compteur_en_cours = false;
                Plateau.hexagone_dectruction_tuile = null;
                jeu.actualiser();
            }


            if(Jeu.index_joueur<Jeu.list_joueur.size())
            {
                Jeu.joueur = Jeu.list_joueur.get(Jeu.index_joueur);
            }
            else if(Jeu.index_joueur==Jeu.list_joueur.size())
            {
                Jeu.index_joueur=0;
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
