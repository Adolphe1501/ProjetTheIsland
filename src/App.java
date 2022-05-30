import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App
{
    /*public App()
    {
        super("The Island");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1430, 820);
        this.setLocationRelativeTo(null);  
        this.setResizable(false);
        this.jeu = new Jeu(this);
        this.setContentPane(jeu);
    }*/
    JPanel container = new JPanel();
    Menu panelMenu = null;
    PanelJoueurs panelNombreJoueurs = null;
    public JPanel createContentPane(){
    	try {
			panelMenu = new Menu(ImageIO.read(new File("image/menu.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		container.add(panelMenu);
		container.setOpaque(false);
        return container;  
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("The Island");

        //Create and set up the content pane.
        App demo = new App();
        frame.setContentPane(demo.createContentPane());

        // The other bits and pieces that make our program a bit more stable.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(740, 580);
        frame.setLocationRelativeTo(null);  
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

