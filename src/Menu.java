import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Menu extends BackgroundPanel implements ActionListener{
	PanelJoueurs panelNombreJoueurs = null;
	JPanel panelJoueurs;
	CardLayout card = new CardLayout();
	
	//Préparation des boutons du menu
	JButton nouvellePartie = new JButton("Nouvelle Partie");
	JButton consulterAide = new JButton("Consulter l'aide");
	JButton quitter = new JButton("Quitter");
	JButton retour = new JButton("< Retour au menu");
	public Menu(Image background) {
		super(background);
		try {
			panelNombreJoueurs = new PanelJoueurs(ImageIO.read(new File("image/menu.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		setLayout(card);

		//Configuration largeur, hauteur, position au sein de la fenêtre
		nouvellePartie.setBounds(270, 200, 200, 60);
		consulterAide.setBounds(270, 270, 200, 60);
		quitter.setBounds(270, 340, 200, 60);
		retour.setBounds(570, 200, 150, 30);
		
		nouvellePartie.addActionListener(this);
		consulterAide.addActionListener(this);
		quitter.addActionListener(this);
		retour.addActionListener(this);
		
		panelJoueurs = new JPanel();
		panelJoueurs.setLayout(null);
		
		panelNombreJoueurs.setSize(780, 400);
		panelJoueurs.add(panelNombreJoueurs);
		panelJoueurs.add(retour);
		panelJoueurs.setOpaque(false);
		
		JPanel menu = new JPanel();
		menu.setLayout(null);

		//Ajout des boutons sur la fenêtre
		menu.add(nouvellePartie);
		menu.add(consulterAide);
		menu.add(quitter);
		menu.setOpaque(false);
		
		add("menu", menu);
		add("nombreJoueurs", panelJoueurs);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		//System.out.print(obj);
		//card.next(this);
		if(obj == nouvellePartie) {
			card.show(this, "nombreJoueurs");
		}
		if(obj == quitter) {
			topFrame.dispatchEvent(new WindowEvent(topFrame, WindowEvent.WINDOW_CLOSING));
		}
		if(obj == retour) {
			card.show(this, "menu");
		}
	}
	//nouvellePartie.setBounds(100, 220, 170, 30);
}
