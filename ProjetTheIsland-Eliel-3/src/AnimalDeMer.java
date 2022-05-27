

public abstract class AnimalDeMer {

    protected final int vitesse;
    protected Hexagone hexagone;


    public AnimalDeMer(Hexagone hexagone) 
    {
        this.vitesse = 1;
        this.hexagone = hexagone;
    }

   
    public abstract void attaquer();


    public abstract void deplacer(); 
}