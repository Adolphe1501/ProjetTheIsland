

public class Chrono implements Runnable
{
    private final int pause = 3;
    private Plateau p;

    public Chrono(Plateau p)
    {
        this.p = p;
    }
    @Override
    public void run() 
    {
        while(true)
        {
            p.repaint();

            //System.out.println(MouseInfo.getPointerInfo().getLocation().x + " et " + MouseInfo.getPointerInfo().getLocation().y);


            //if(Jeu.worldX!=MouseInfo.getPointerInfo().getLocation().x && Jeu.worldY!=MouseInfo.getPointerInfo().getLocation().x)
            //{
                //System.out.println(MouseInfo.getPointerInfo().getLocation().x + " et " + MouseInfo.getPointerInfo().getLocation().y);
            //}
            try 
            {
                Thread.sleep(pause);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
}
