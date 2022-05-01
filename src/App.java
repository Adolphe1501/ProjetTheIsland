import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;

public class App extends JFrame
{

    private Jeu jeu;

    public App()
    {
        super("The Island");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 1000);
        this.setLocationRelativeTo(null);  
        
        this.jeu = new Jeu();
        this.setContentPane(jeu);

    }
    public static void main(String[] args) throws Exception 
    {

       //App myApp = new App();

       //myApp.setVisible(true);

       int map[] = new int[12];

        try 
        {
            FileReader fichier = new FileReader("map.txt");

            BufferedReader reader = new BufferedReader(fichier);

            for(int i = 0; i<12; i++)
            {
                map[i] = 0;
                System.out.println(reader.readLine());
            }

            reader.close();
        } 
        catch (FileNotFoundException e1) 
        {
            e1.printStackTrace();
            System.out.println("Erreur");
        }

        /*
        for(int i = 0; i<12; i++)
        {
            System.out.println(map[i]);
        }
        */
    }
}
