import java.util.ArrayList;
import java.util.IllegalFormatFlagsException;
import java.util.List;

public class Requin extends AnimalDeMer {
    protected final int id_requin;

    public Requin(int id_requin) {
        super();
        this.id_requin = id_requin;
    }

    public int getId_requin() {
        return this.id_requin;
    }

    @Override
    public void attaquer() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deplacer() {
        // TODO Auto-generated method stub

    }

    public List<Requin> initListRequin() {
        int i;
        List<Requin> list = new ArrayList<>();
        for (i = 0; i < 5; i++) {
            list.add(new Requin(id_requin));
        }
        return list;
    }
    public void supprimerRequin(List<Requin> list)
    {    
        list.remove(0);
    }

 
}