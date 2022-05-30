import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel
{
    private Image background;
 
    public BackgroundPanel(Image background)
    {
        this.background = background;
        setLayout(null);
    }
 
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
 
        g.drawImage(background, 0, 0, null);
    }
 
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }
}