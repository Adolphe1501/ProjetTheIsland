import java.util.List;

public abstract class AnimalDeMer {

    protected final int vitesse;

    public AnimalDeMer() {
        this.vitesse = 1;
    }

    public abstract void attaquer();

    public abstract void deplacer();

    public void action_requin(Position position, AnimalDeMer requin) {
        if (Plateau.map[position.getNumero_ligne()][position.getNumero_colone()].getTuile() == null) {
            Hexagone hexagone = Plateau.map[position.getNumero_ligne()][position.getNumero_colone()];
            if (hexagone.getListe_joueur() != null) {
                for (int i = 0; i < hexagone.getListe_joueur().size(); i++) {
                    hexagone.getListe_joueur().get(i).supprimerPionDeJeu();
                }
            }
            hexagone.addPionMer(requin);
        }
    }

    public void action_baleine(Position position, AnimalDeMer animal) {
        Hexagone hexagone = Plateau.map[position.getNumero_ligne()][position.getNumero_colone()];
        List<P_Joueur> list = hexagone.getBateau().getListe_pionJoueur();

        if (hexagone.getTuile() == null) {
            hexagone.addPionMer(animal);
            if ((hexagone.getBateau() != null) && (list.isEmpty() == false))/* action sur les bateaux */
            {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setHexagone(hexagone);
                    list.get(i).setEst_nageur(true);
                }
                hexagone.supprimerBateau();

            }
            if (hexagone.getListe_animaux() != null)/* si hexagone contient un requin */
            {
                for (int i = 0; i < hexagone.getListe_animaux().size(); i++) {
                    if ((hexagone.getListe_animaux().get(i) instanceof Requin) && (list.isEmpty() == false)) {
                        hexagone.getListe_animaux().get(i).action_requin(hexagone.getPosition(),
                                hexagone.getListe_animaux().get(i));
                    }
                }

            }

        }
    }

    public void action_serpant(Position position, AnimalDeMer animal) {
        Hexagone hexagone = Plateau.map[position.getNumero_ligne()][position.getNumero_colone()];
        if (hexagone.getTuile() == null)/* une tuile mer */
        {
            hexagone.addPionMer(animal);
            if (hexagone.getBateau() != null)/* y un bateau et non vide */
            {
                hexagone.setBateau(null);

            }
            if (hexagone.getListe_joueur() != null)/* si existe des nageurs */
            {
                for (int i = 0; i < hexagone.getListe_joueur().size(); i++) {
                    hexagone.getListe_joueur().get(i).supprimerPionDeJeu();
                }
            }
        }
    }
}