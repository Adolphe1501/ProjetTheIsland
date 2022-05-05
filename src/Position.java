public class Position 
{
    private int numero_ligne;
    private int numero_colone;

    public Position(int numero_ligne, int numero_colone)
    {
        this.numero_ligne = numero_ligne;
        this.numero_colone = numero_colone;
    }

    public int getNumero_ligne() {
        return numero_ligne;
    }

    public void setNumero_ligne(int numero_ligne) {
        this.numero_ligne = numero_ligne;
    }

    public int getNumero_colone() {
        return numero_colone;
    }

    public void setNumero_colone(int numero_colone) {
        this.numero_colone = numero_colone;
    }
    
}
