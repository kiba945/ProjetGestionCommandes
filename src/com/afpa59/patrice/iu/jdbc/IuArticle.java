package com.afpa59.patrice.iu.jdbc;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.afpa59.patrice.service.jdbc.ServiceArticle;
import com.afpa59.patrice.utils.ESpane;



public class IuArticle extends IuBase implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nomPhysiqueArticle = "TableArticles";
	ServiceArticle s1; // pour récupérer les services lors du premier appel



	/**
	 * @param s1
	 */
	public IuArticle(ServiceArticle s1){	

		this.s1= s1;

		Container contain= getContentPane();

		JPanel p1;
		JMenuBar barMenu;
		JMenu menu,menu2;
		JMenuItem menuItem;
		JLabel label= new JLabel("<html> <h3> GESTION des ARTICLES </h3> </html>");

		setTitle(" FrameMenuGestionArticles");
		contain.setLayout(new GridLayout(1,1,20,10));

		// On cree un menu bar pour y mettre des menus
		barMenu= new JMenuBar();

		// preparation de chaque menu: menu 1
		menu = new JMenu(" A R T I C L E ");
		menu.setMnemonic('A');	//Le menu dispose du mnémonique A

		// Y ajouter des sous menus
		menuItem= new JMenuItem("Création/Ajout");		
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		SetRaccourci(menuItem, 'C');

		menu2 = new JMenu("Affichage");
		menu.add(menu2);

		menuItem= new JMenuItem("Affichage Liste");
		menuItem.addActionListener(this);
		menu2.add(menuItem);

		SetRaccourci(menuItem, 'L');

		menuItem= new JMenuItem("Affichage Formulaire");
		menuItem.addActionListener(this);
		menu2.add(menuItem);

		SetRaccourci(menuItem, 'V');

		//		menuItem= new JMenuItem("Affichage Articles Promo");
		//		menuItem.addActionListener(this);
		//		menu2.add(menuItem);
		//		barMenu.add(menu);

		menuItem = new JMenuItem("Suppression");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		SetRaccourci(menuItem, 'S');

		menuItem = new JMenuItem("Modification");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		SetRaccourci(menuItem, 'M');

		menuItem = new JMenuItem("Retour");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		SetRaccourci(menuItem, 'R');

		menuItem = new JMenuItem("Sauvegarde");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		menu = new JMenu(" SUITE ");
		menu.setMnemonic('S');	//Le menu ARTICLE dispose du mnémonique A
		menuItem= new JMenuItem("FIN");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		SetRaccourci(menuItem, 'F');

		menu.addActionListener(this);
		this.setJMenuBar(barMenu);

		// ajoute le menu bar au container + le cale à gauche de la frame
		p1= new JPanel();
		p1.add(label);
		contain.add(p1);
		this.setSize(750,300); //LONGUEUR * HAUTEUR de la frame
		//pack();
		this.setLocationRelativeTo(null); // POUR CENTRER LA FRAME
		setMinimumSize(new Dimension(550, 200)); // Définir une taille de fenetre minimum
		this.setVisible(true);
		//show();

	}


	/* (non-Javadoc)
	 * @see com.afpa59.patrice.iu.jdbc.IuBase#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		String lib = e.getActionCommand();
		if (lib.equals("Affichage Liste")) {visualiserTout(s1); }

		else if(lib.equals("Affichage Formulaire")) {visualiser(s1);

		// ==> true + true cas 1 er appel pou remettre à 0 l'indice
		}
		//		else if(lib.equals("Affichage Articles Promo")) {  }

		else if(lib.equals("Création/Ajout")) { creer(s1);}

		else if(lib.equals("Suppression")) { supprimer(s1);}

		else if(lib.equals("Modification")) { modifier(s1);}

		else if(lib.equals("Retour")) { this.dispose();}

		else if(lib.equals("Sauvegarde")) { ESpane.affiche("Procédure de sauvegarde ici prochainement"); }

		else if(lib.equals("FIN")) { this.dispose(); }
	}


	/**
	 * Méthode creerArticle qui cree un article en mode saisie assitée
	 * 
	 * @param service
	 */
	public static void creer(ServiceArticle service){
		char nouveau;
		do{
			creerligneArticle(service);
			nouveau = ESpane.saisie("Voulez-vous saisir une autre article? (O/N) ").charAt(0);
		}while(nouveau == 'O' || nouveau == 'o');
	}


	/**
	 * Méthode creerligneArticle
	 * 
	 * @param service
	 */
	public static void creerligneArticle(ServiceArticle service){
		// Déclaration des variables
//		int code;
		String nom;
		float prix;
		
		
//		code = ESpane.saisie("\n********** CREATION D'UN ARTICLE **********\n\n"
//				+ service.toString()
//				+ "\nCode Article: ", 1, Integer.MAX_VALUE);
		
		nom = ESpane.saisie("\n********** CREATION D'UN ARTICLE **********\n\n"
				+ service.toString()
				+ "\nDésignation: ");	
		
//		if(service.retourner(code) == null){
//			nom = ESpane.saisie("Désignation: ");
			prix = ESpane.saisie("Prix: ", 0F, Float.MAX_VALUE);
			service.creer(nom, prix);
			ESpane.affiche("\n ...CREATION Réussie...\n");
//		}else{
//			ESpane.affiche("LE CODE ARTICLE " + code + " EXISTE DEJA!\n");
//		}
	}


	/**
	 * Méthode visualiserArticle qui visualise l'article correspondant au code saisie
	 * 
	 * @param service
	 */
	public static void visualiser(ServiceArticle service){
		// Déclaration des variables
		int code;
		code = ESpane.saisie("\n********** AFFICHAGE D'UN ARTICLE **********\n\n"
				+ "LISTE des ARTICLES EN CATALOGUE\n\n"
				+ service.toString()
				+ "\nCode Article: ", 1, Integer.MAX_VALUE);
		if(service.retourner(code) != null){
			ESpane.affiche(service.retourner(code).toString());
		}else{
			ESpane.affiche("LE CODE ARTICLE " + code + " N'EXISTE PAS!\n");
		}			
	}


	/**
	 * Méthode modifierArticle qui modifie l'article correspondant
	 * au code saisie avec une saisie assistée
	 * 
	 * @param service du type ServiceArticle
	 */
	public static void modifier(ServiceArticle service){
		// Déclaration des variables
		int code;
		String nom;
		float prix;	
		
		code = ESpane.saisie("\n********** MODIFICATION D'UN ARTICLE **********\n\n"
				+ service.toString()
				+ "\nCode Article: ", 1, Integer.MAX_VALUE);
		
		
		if(service.retourner(code) != null){
			//IPaneES.affiche("Ancien nom de l'Article ("+service.retourner(code).getDesignation()+")\n");
			nom = ESpane.saisie("Ancien nom de l'Article ("
			+service.retourner(code).getDesignation()
			+")\n Nouveau Nom: ");

			prix = ESpane.saisie("Ancien prix de l'Article ("+service.retourner(code).getPrix()
					+")\n Nouveau prix: ", 0F, Float.MAX_VALUE);
			
			service.modifier(code, nom, prix);
			ESpane.affiche("LE CODE ARTICLE " + code + " A BIEN ETE MODIFIE\n");
		}else{
			
			ESpane.affiche("LE CODE ARTICLE " + code + " N'EXISTE PAS!\n");
		
		}		
	}



	/**
	 * Méthode supprimerArticle qui supprime l'article correspondant au code saisie
	 * 
	 * @param service
	 */
	public static void supprimer(ServiceArticle service){
		int code;
		code = ESpane.saisie("\n********** SUPPRESSION D'UN ARTICLE **********\n\n"
				+ service.toString()
				+ "\nCode Article: ", 1, Integer.MAX_VALUE);
		if(service.retourner(code) != null){
			String infoArticle = service.retourner(code).toString();
			service.supprimer(code);
			ESpane.affiche("\n ...SUPPRESSION de l'article \n"+ infoArticle +"\nRéussie...\n");
		}else{
			ESpane.affiche("LE CODE ARTICLE " + code + " N'EXISTE PAS!\n");
		}		
	}


	/**
	 * Méthode visualiserListeArticle qui affiche la liste des articles
	 * 
	 * @param service
	 */
	public static void visualiserTout(ServiceArticle service){
		ESpane.affiche("\n********** LISTE DES ARTICLES **********\n\n"
				+service.toString()+"\n");
	}
}
