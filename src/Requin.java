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

    public void requin_vert(List<Requin> list, Position position) {
        Requin requin = list.get(0);
        list.remove(0);
        if (Plateau.map[position.getNumero_ligne()][position.getNumero_colone()].getTuile() == null) {
            Hexagone hexagone = Plateau.map[position.getNumero_ligne()][position.getNumero_colone()];
            if (Plateau.map[position.getNumero_ligne()][position.getNumero_colone()].getListe_joueur() != null) {
                for (int i = 0; i < hexagone.getListe_joueur().size(); i++) {
                    hexagone.getListe_joueur().get(i).supprimerPionDeJeu();
                }
            }
            hexagone.addPionMer(requin);
        }
    }
}