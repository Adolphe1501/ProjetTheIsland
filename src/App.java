import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.Principal;

import javax.swing.JFrame;

public class App extends JFrame
{

    private Jeu jeu;

    public App()
    {
        super("The Island");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 1300);
        this.setLocationRelativeTo(null);  
        
        this.jeu = new Jeu();
        this.setContentPane(jeu);

    }
    public static void main(String[] args) throws Exception 
    {

       App myApp = new App();

       myApp.setVisible(true);

     
    }
}
