import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Plateau extends JPanel
{
    public static Hexagone map[][];
    public Jeu partie;

    public Plateau() 
    {
        super();

        this.setBackground(Color.ORANGE);
    }

}