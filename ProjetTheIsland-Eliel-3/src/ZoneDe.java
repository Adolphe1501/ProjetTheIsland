import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class  ZoneDe extends JPanel
{
    private De de;
  
    public ZoneDe()
    {
        this.setBackground(new Color(176,196,222));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.de = new De();
        this.de.afficherDe(110, 100);

        this.add(this.de);
    }

    public void paintComponent(Graphics g)
    {

    }
}
