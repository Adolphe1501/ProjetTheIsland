import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelJoueurs extends BackgroundPanel implements ActionListener{
	CardLayout card = new CardLayout();
	JPanel panelDeuxPseudos;
	JPanel panelTroisPseudos;
	JPanel panelQuatrePseudos;
	
	PanelPseudos deuxJoueurs;
	PanelPseudos troisJoueurs;
	PanelPseudos quatreJoueurs;
	
	//Préparation des boutons du menu
	JButton bouttonDeuxJoueurs = new JButton("2 Joueurs");
	JButton bouttonTroisJoueurs = new JButton("3 Joueurs");
	JButton bouttonquatreJoueurs = new JButton("4 Joueurs");
	JButton retourDeuxJoueurs = new JButton("< Précédent");
	JButton retourTroisJoueurs = new JButton("< Précédent");
	JButton retourQuatreJoueurs = new JButton("< Précédent");
	
	public PanelJoueurs(Image background) {
		super(background);

		try {
			deuxJoueurs = new PanelPseudos(ImageIO.read(new File("image/menu.jpg")), 2);
			troisJoueurs = new PanelPseudos(ImageIO.read(new File("image/menu.jpg")), 3);
			quatreJoueurs = new PanelPseudos(ImageIO.read(new File("image/menu.jpg")), 4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(card);
		
		//Configuration largeur, hauteur, position au sein de la fenêtre
		bouttonDeuxJoueurs.setBounds(270, 200, 200, 60);
		bouttonTroisJoueurs.setBounds(270, 270, 200, 60);
		bouttonquatreJoueurs.setBounds(270, 340, 200, 60);
		retourDeuxJoueurs.setBounds(600, 250, 120, 30);
		retourTroisJoueurs.setBounds(600, 250, 120, 30);
		retourQuatreJoueurs.setBounds(600, 250, 120, 30);
		
		bouttonDeuxJoueurs.addActionListener(this);
		bouttonTroisJoueurs.addActionListener(this);
		bouttonquatreJoueurs.addActionListener(this);
		retourDeuxJoueurs.addActionListener(this);
		retourTroisJoueurs.addActionListener(this);
		retourQuatreJoueurs.addActionListener(this);
		
		panelDeuxPseudos = new JPanel();
		panelDeuxPseudos.setLayout(null);
		deuxJoueurs.setSize(600, 400);
		panelDeuxPseudos.add(deuxJoueurs);
		panelDeuxPseudos.add(retourDeuxJoueurs);
		
		panelTroisPseudos = new JPanel();
		panelTroisPseudos.setLayout(null);
		troisJoueurs.setSize(600, 400);
		panelTroisPseudos.add(troisJoueurs);
		panelTroisPseudos.add(retourTroisJoueurs);
		
		panelQuatrePseudos = new JPanel();
		panelQuatrePseudos.setLayout(null);
		quatreJoueurs.setSize(780, 700);
		panelQuatrePseudos.add(quatreJoueurs);
		panelQuatrePseudos.add(retourQuatreJoueurs);
		
		panelDeuxPseudos.setOpaque(false);
		panelTroisPseudos.setOpaque(false);
		panelQuatrePseudos.setOpaque(false);
		
		JPanel menu = new JPanel();
		menu.setLayout(null);
		
		//Ajout des boutons sur la fenêtre
		menu.add(bouttonDeuxJoueurs);
		menu.add(bouttonTroisJoueurs);
		menu.add(bouttonquatreJoueurs);
		menu.setOpaque(false);
		
		add("menu", menu);
		add("deuxJoueurs", panelDeuxPseudos);
		add("troisJoueurs", panelTroisPseudos);
		add("quatreJoueurs", panelQuatrePseudos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == bouttonDeuxJoueurs) {
			card.show(this, "deuxJoueurs");
		}
		if(obj == bouttonTroisJoueurs) {
			card.show(this, "troisJoueurs");
		}
		if(obj == bouttonquatreJoueurs) {
			card.show(this, "quatreJoueurs");
		}
		if(obj == retourDeuxJoueurs || obj == retourTroisJoueurs || obj == retourQuatreJoueurs){
			card.first(this);
		}
	}

}
