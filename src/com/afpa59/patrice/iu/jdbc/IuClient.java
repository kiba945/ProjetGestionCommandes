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

import com.afpa59.patrice.service.jdbc.ServiceClient;
import com.afpa59.patrice.utils.ESpane;



public class IuClient extends IuBase implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ServiceClient s2; // pour récupérer les services lors du premier appel
	
	/**
	 * @param s2
	 */
	public IuClient(ServiceClient s2){		
		this.s2=s2;
		
		Container contain= getContentPane();
		JPanel p1;
		JMenuBar barMenu;
		JMenu menu,menu2;
		JMenuItem menuItem;
		JLabel label= new JLabel("<html> <h3> GESTION des CLIENTS </h3> </html>");
		setTitle(" FrameMenuGestionClients");
		contain.setLayout(new GridLayout(1,1,20,10));
		// On cree un menu bar pour y mettre des menus
		barMenu= new JMenuBar();
		// preparation de chaque menu: menu 1
		menu = new JMenu(" C L I E N T ");
		menu.setMnemonic('C');	//Le menu CLIENT dispose du mnémonique C
		
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
		
//		menuItem = new JMenuItem("Sauvegarde");
//		menuItem.addActionListener(this);
//		menu.add(menuItem);
//		barMenu.add(menu);
		
		menu = new JMenu(" SUITE ");
		menu.setMnemonic('S');	//Le menu CLIENT dispose du mnémonique S
		
		menuItem= new JMenuItem("FIN");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);
		menu.addActionListener(this);
		SetRaccourci(menuItem, 'F');
		
		
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
		//this.show();

	}
	
	/* (non-Javadoc)
	 * @see com.afpa59.patrice.iu.jdbc.IuBase#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
		String lib = e.getActionCommand();
		
		
		if (lib.equals("Affichage Liste")) {visualiserTout(s2); }
		
		else if(lib.equals("Affichage Formulaire")) {visualiser(s2);
		
			// ==> true + true cas 1 er appel pou remettre à 0 l'indice
		}
		
//		else if(lib.equals("Affichage Articles Promo")) {  }
		
		else if(lib.equals("Création/Ajout")) { creer(s2);}
		
		else if(lib.equals("Suppression")) { supprimer(s2);}
		
		else if(lib.equals("Modification")) { modifier(s2);}
		
		else if(lib.equals("Retour")) { this.dispose();}
		
//		else if(lib.equals("Sauvegarde")) { }
		
		else if(lib.equals("FIN")) { this.dispose(); }
		
	}

	/**
	 * Méthode creer qui cree un client en mode saisie assitée
	 * 
	 * @param service
	 */
	public static void creer(ServiceClient service){
		char nouveau;
		do{
			creerligneClient(service);
			nouveau = ESpane.saisie("Voulez-vous saisir un autre client? (O/N) ").charAt(0);
		}while(nouveau == 'O' || nouveau == 'o');
	}

	/**
	 * Méthode creerligneClient
	 * 
	 * @param service
	 */
	public static void creerligneClient(ServiceClient service){
		// Déclaration des variables
		String nom;
		String prenom;
		String adresse;
		
		nom = ESpane.saisie("\n********** CREATION d'un nouveau CLIENT ****\n\n"
				+ "LISTE des CLIENTS\n\n"
				+ service.toString()+"\n"
				+ "Saisir Nom du client: ");
//		if(service.retourner(code) == null){
			prenom = ESpane.saisie("Saisir Prenom du client: ");
			adresse = ESpane.saisie("Saisir Adresse du client: ");
			service.creer(nom, prenom, adresse);
			ESpane.affiche("LE CLIENT "
			+ nom
			+ " "
			+ prenom
			+ " A ETE CREE!\n");
//		}else{
//			ESpane.affiche("LE CODE CLIENT " + code + " EXISTE DEJA!");
//		}
	}
	
	
	/**
	 * Méthode visualiser qui affiche le client correspondant au code saisie
	 * 
	 * @param service
	 */
	public static void visualiser(ServiceClient service){
		int code = ESpane.saisie("\n********** AFFICHAGE D'UN CLIENT ****\n\n"
				+ service.toString()+"\n"
				+"Saisir code client: ", 1, Integer.MAX_VALUE);
		if(service.retourner(code) != null){
			ESpane.affiche(service.retourner(code).toString());
		}else{
			ESpane.affiche("LE CODE CLIENT " + code + " N'EXISTE PAS!");
		}			
	}


	/**
	 * Méthode modifier qui modifie le client correspondant 
	 * au code saisie avec une saisie assistée
	 * 
	 * @param service
	 */
	public static void modifier(ServiceClient service){
		int code = ESpane.saisie("\n********** MODIFICATION d'un CLIENT ****\n\n"
				+ service.toString()+"\n"
				+"Saisir code client: ", 1, Integer.MAX_VALUE);
		if(service.retourner(code) != null){
			String nom = ESpane.saisie("Ancien nom du client ("+service.retourner(code).getNom()+")"
					+"Saisir nouveau Nom: ");
			String prenom = ESpane.saisie("Ancien prenom du client ("+service.retourner(code).getPrenom()+")"
					+"Saisir nouveau Prenom: ");
			String adresse = ESpane.saisie("Ancienne adresse du client ("+service.retourner(code).getAdresse()+")"
					+"Saisir nouvelle Adresse: ");
			service.modifier(code, nom, prenom, adresse);
		}else{
			ESpane.affiche("LE CODE CLIENT " + code + " N'EXISTE PAS!");
		}		
	}


	/**
	 * Méthode supprimer qui supprime le client correspondant au code saisie
	 * 
	 * @param service
	 */
	public static void supprimer(ServiceClient service){
		int code = ESpane.saisie("\n********** SUPPRESSION D'UN CLIENT ****\n\n"
				+ service.toString()+"\n"
				+ "Saisir code client: ", 1, Integer.MAX_VALUE);
		if(service.retourner(code) != null){
			String infoClt = service.retourner(code).toString();
			service.supprimer(code);
			ESpane.affiche("\n ...SUPPRESSION du Client "+ infoClt +" Réussie...\n");
		}else{
			ESpane.affiche("LE CODE CLIENT " + code + " N'EXISTE PAS!");
		}		
	}


	/**
	 * Méthode visualiserListeclient qui affiche la liste des clients
	 * 
	 * @param s1
	 */
	public static void visualiserTout(ServiceClient s1){
		ESpane.affiche("\n********** LISTE DES CLIENTS **********\n\n"
				+s1.toString() +"\n");
	}

}
