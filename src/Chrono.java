

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

            jeu.actualiser();
          
           if(Jeu.compteur==3)
            {
                System.out.println("jai fini");
                Jeu.timer.stop();
                Jeu.compteur = 0;
                if(Plateau.hexagone_dectruction_tuile.getTuile()!=null)
                {
                    Plateau.hexagone_dectruction_tuile.getTuile().effetImmediatVerso();

                }
                Plateau.hexagone_dectruction_tuile.setTuile(null);
                Plateau.hexagone_dectruction_tuile = null;
                Jeu.compteur_en_cours = false;
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
