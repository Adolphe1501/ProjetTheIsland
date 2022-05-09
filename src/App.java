import javax.swing.JFrame;


public class App extends JFrame
{

    private Jeu jeu;

    public App()
    {
        super("The Island");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1430, 820);
        this.setLocationRelativeTo(null);  
        this.setResizable(false);
        this.jeu = new Jeu();
        this.setContentPane(jeu);
    }
    public static void main(String[] args) throws Exception 
    {

       App myApp = new App();

       myApp.setVisible(true);
     
    }
}
