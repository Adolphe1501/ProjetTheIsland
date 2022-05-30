import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PanelPseudos extends BackgroundPanel implements ActionListener{
	
	private Jeu jeu;
	private Joueur joueur;
	JPanel pseudosPanel;
	JLabel[] pseudosLabels;
	JTextField[] pseudos;
	JLabel titre = new JLabel("Entrez les pseudos des joueurs :");
	JButton commencer = new JButton("Commencer !");
	int gap;
	int champs;
	public PanelPseudos(Image background, int champs) {
		super(background);
		this.champs = champs;
		setLayout(null);
		gap = 0;
		pseudosPanel = new JPanel();
		pseudosPanel.setLayout(null);
		
		titre.setBounds(220, 150, 200, 30);
		titre.setForeground(Color.WHITE);
		//pseudosPanel.setOpaque(false);
			
		pseudosLabels = new JLabel[champs];
		pseudos = new JTextField[champs];
		
		for (int i = 0; i < champs; i++) {
			pseudosLabels[i] = new JLabel("Joueur "+(i+1)+" :");
			pseudos[i] = new JTextField();
			
			pseudosLabels[i].setBounds(250, 200+gap, 60, 30);
			pseudosLabels[i].setForeground(Color.WHITE);
			pseudos[i].setBounds(310, 200+gap, 170, 30);
			
			add(pseudosLabels[i]);
			add(pseudos[i]);
			gap = gap + 40;
		}
		commencer.setBounds(310, 200+gap, 170, 30);
		commencer.addActionListener(this);
		add(commencer);
		add(titre);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		List<Joueur> list_joueur = new ArrayList<Joueur>();
		for (int i = 0; i < champs; i++) {
			this.joueur = new Joueur(pseudos[i].getText(), null);
			list_joueur.add(this.joueur);
		}
		
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("The Island");
        this.jeu = new Jeu(frame, list_joueur);

        frame.setContentPane(this.jeu);

        // The other bits and pieces that make our program a bit more stable.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1430, 820);
        frame.setLocationRelativeTo(null);  
        frame.setResizable(false);
        frame.setVisible(true);
        topFrame.dispose();
	}

}
