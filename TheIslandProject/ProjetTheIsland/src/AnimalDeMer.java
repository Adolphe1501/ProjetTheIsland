import java.util.ArrayList;

public abstract class AnimalDeMer {

    protected final int vitesse;

    public AnimalDeMer() 
    {
        this.vitesse = 1;
    }

    public abstract void attaquer();

    public abstract void deplacer(); 
    
}