

public class Chrono implements Runnable
{
    private final int pause = 0;
    private Jeu jeu;

    public Chrono(Jeu jeu)
    {
        this.jeu = jeu;
    }
    @Override
    public void run() 
    {

        double drawInterval = 1000000000/60;
        double next_draw_time = System.nanoTime() + drawInterval;

        while(!this.jeu.getRejouer())
        {
            //jeu.actualiser();
          
            try 
            {
                System.out.println("*****");
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
