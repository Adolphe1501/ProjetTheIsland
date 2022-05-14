
public class Verso 
{

    protected final String couleur;
    protected final String action;

    public Verso(String couleur, String action) 
    {
        this.couleur = couleur;
        this.action = action;
    }

    public String getCouleur() 
    {
        return couleur;
    }

    public String getAction() 
    {
        return action;
    }

    public boolean equals(Verso v)
    {
        if((this.couleur == v.couleur) && (this.action == v.action))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Verso[] initVersosplage()
    {
        Verso[] versos = new Verso[9];
        Verso verso ;


        verso = new Verso("vert","baleine");
        versos[0]  = verso;

        verso = new Verso("vert","requin");
        versos[1]  = verso;

        verso = new Verso("vert","bateau");
        versos[2]  = verso;

        verso = new Verso("rouge","bateau");
        versos[3]  = verso;

        verso = new Verso("rouge","dauphin");
        versos[4]  = verso;

        verso = new Verso("rouge","dragon");
        versos[5]  = verso;

        verso = new Verso("rouge","requin");
        versos[6]  = verso;

        verso = new Verso("rouge","baleine");
        versos[7]  = verso;

        verso = new Verso("rouge","requin barre");
        versos[8]  = verso;


        return versos;
    }


    public Verso[] initVersosForet()
    {
        Verso[] versos = new Verso[10];
        Verso verso ;


        verso = new Verso("vert","baleine");
        versos[0]  = verso;

        verso = new Verso("vert","requin");
        versos[1]  = verso;

        verso = new Verso("vert","bateau");
        versos[2]  = verso;

        verso = new Verso("vert","toran");
        versos[3]  = verso;

        verso = new Verso("rouge","dauphin");
        versos[4]  = verso;

        verso = new Verso("rouge","dragon");
        versos[5]  = verso;

        verso = new Verso("rouge","requin");
        versos[6]  = verso;

        verso = new Verso("rouge","aigle");
        versos[7]  = verso;

        verso = new Verso("rouge","requin barre");
        versos[8]  = verso;

        verso = new Verso("rouge","baleine barre");
        versos[9]  = verso;

        return versos;
    }

    public Verso[] initVersosMontagne()
    {
        Verso[] versos = new Verso[5];
        Verso verso ;



        verso = new Verso("vert","requin");
        versos[0]  = verso;

        verso = new Verso("vert","toran");
        versos[1]  = verso;

        verso = new Verso("vert","volcan");
        versos[2]  = verso;

        verso = new Verso("rouge","requin barre");
        versos[3]  = verso;

        verso = new Verso("rouge","baleine barre");
        versos[4]  = verso;

        return versos;
    }
}