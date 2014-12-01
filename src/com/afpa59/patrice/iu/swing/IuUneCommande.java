package com.afpa59.patrice.iu.swing;

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

import com.afpa59.patrice.donnees.Article;
import com.afpa59.patrice.donnees.Commande;
import com.afpa59.patrice.donnees.LigneDeCommande;

import com.afpa59.patrice.service.fichier.ServiceArticle;
import com.afpa59.patrice.service.fichier.ServiceClient;
import com.afpa59.patrice.service.fichier.ServiceCommande;

import com.afpa59.patrice.utils.ESpane;



public class IuUneCommande extends IuBase implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ServiceArticle s1; // pour récupérer les services lors du premier appel
	ServiceClient s2; // pour récupérer les services lors du premier appel
	ServiceCommande s3; // pour récupérer les services lors du premier appel
	static Commande Cde;

	public IuUneCommande(ServiceArticle s1, ServiceClient s2, ServiceCommande s3){		
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;

		Container contain= getContentPane();

		JPanel p1;
		JMenuBar barMenu;
		JMenu menu;
		JMenuItem menuItem;

		JLabel label= new JLabel("<html> <h3> GESTION D'UNE COMMANDE </h3> </html>");
		setTitle(" FrameMenuGestionUneCommande");

		contain.setLayout(new GridLayout(1,1,20,10));

		// On cree un menu bar pour y mettre des menus

		barMenu= new JMenuBar();
		// preparation de chaque menu: menu 1

		menu = new JMenu(" L I G N E  D E  C O M M A N D E ");
		menu.setMnemonic('L');	//Le menu dispose d'un mnémonique

		// Y ajouter des sous menus
		menuItem= new JMenuItem("Création/Ajout d'une ligne de commande");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		SetRaccourci(menuItem, 'C');

		menuItem = new JMenuItem("Suppression d'une ligne de commande");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		SetRaccourci(menuItem, 'S');

		menuItem = new JMenuItem("Modification d'une ligne de commande");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		SetRaccourci(menuItem, 'M');

		menuItem= new JMenuItem("Affichage de la commande (en cours)");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);	

		SetRaccourci(menuItem, 'V');

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
		menu.setMnemonic('S');	//Le menu dispose d'un mnémonique

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

		setSize(750,300); //LONGUEUR * HAUTEUR de la frame
		//pack();
		this.setLocationRelativeTo(null); // POUR CENTRER LA FRAME
		setMinimumSize(new Dimension(550, 200)); // Définir une taille de fenetre minimum
		this.setVisible(true);
		//this.show();

	}
	

	public void actionPerformed(ActionEvent e) {

		String lib = e.getActionCommand();
		String numeroCdeEncours = "";

		if(lib.equals("Création/Ajout d'une ligne de commande")) { creer(s1,s2,s3);}


		if(lib.equals("Suppression d'une ligne de commande")) { supprimer(s3);}


		if(lib.equals("Modification d'une ligne de commande")) { modifier(s3);}


		if (lib.equals("Affichage de la commande (en cours)")) {visualiserTout(s3); }		


		if(lib.equals("Retour")) { if(s3.retourner(IuCommande.numCde) != null){
			
			/*******************DEBUGING*******************************/
			
						
			numeroCdeEncours = IuCommande.numCde;
			
			/*******************DEBUGING*******************************/
			IuCommande.numOrd++;
			IuCommande.numCde = ""+Cde.getDateCde().getAnnee()
					+Cde.getDateCde().getMois()+Cde.getDateCde().getJour()
					+IuCommande.numOrd;
			
		}
		
		ESpane.affiche("Fin de Traitement de la Commande\n"
		+ numeroCdeEncours);
		this.dispose();}
		//		

		//		if(lib.equals("Sauvegarde")) { }


		if(lib.equals("FIN")) { if(s3.retourner(IuCommande.numCde) != null){
			
			/*******************DEBUGING*******************************/
			
			numeroCdeEncours = IuCommande.numCde;
			
			/*******************DEBUGING*******************************/			
			
			IuCommande.numOrd++;
			IuCommande.numCde = ""+Cde.getDateCde().getAnnee()
					+ Cde.getDateCde().getMois()
					+ Cde.getDateCde().getJour()
					+ IuCommande.numOrd;
		}
		ESpane.affiche("Fin de Traitement de la Commande\n"
		+ numeroCdeEncours);
		this.dispose();}
	}


	/**
	 * Méthode creer qui cree une commande en mode saisie assitée
	 * 
	 * @param s1 du type ServiceArticle
	 * @param s2 du type ServiceClient
	 * @param s3 du type ServiceCommande
	 */
	public static void creer(ServiceArticle s1, ServiceClient s2, ServiceCommande tabCdes){
		// Déclaration des variables
		char nouveau;

		if(tabCdes.retourner(IuCommande.numCde) == null){
			Cde = new Commande();
		}

		do{
			LigneDeCommande ldc = creerLdc(s1);

			if(ldc != null){
				Cde.setCodeCde(IuCommande.numCde);
				Cde.ajouter(ldc);
				tabCdes.creer(Cde);
			}

			nouveau = ESpane.saisie("Voulez-vous saisir une autre ligne de commande? (O/N) ").charAt(0);
		}while(nouveau == 'O' || nouveau == 'o');
	}


	/**
	 * Méthode creerLdc qui retourne une ligne de commande
	 * 
	 * @param service du type ServiceArticle
	 * @return LigneDeCommande
	 */
	public static LigneDeCommande creerLdc(ServiceArticle s1){
		// Déclaration des variables
		LigneDeCommande ldc = new LigneDeCommande();

		int code = ESpane.saisie("LISTE des ARTICLES EN CATALOGUE\n\n"+s1.toString()+
				"\nSaisir code Produit: ", 1, Integer.MAX_VALUE);

		Article a = s1.retourner(code);

		if(a!=null){
			int qte = ESpane.saisie("Quelle quantite: ", 1, Integer.MAX_VALUE);
			ldc = new LigneDeCommande(code,qte);
			return ldc;

		}else{
			ESpane.affiche("LE CODE ARTICLE COMMANDE N'EXISTE PAS ....\n");

			return ldc=null;

		}
	}


	/**
	 * Méthode supprimer qui supprime la ligne de commande
	 * 
	 * @param service du type ServiceCommande
	 */
	public void supprimer(ServiceCommande tabCdes){

		String num = IuCommande.numCde;

		if(tabCdes.retourner(num) != null){	
			effacerLigne(tabCdes);

		}else{
			ESpane.affiche("COMMANDE VIDE!");
		}
	}


	/**
	 * Méthode effacerLigne qui supprime une ligne correspondante au code saisie
	 * 
	 * @param service du type ServiceCommande
	 */
	public void effacerLigne(ServiceCommande tabCdes){

		int lg;
		String num = IuCommande.numCde;

		lg = ESpane.saisie("******** SUPPRESSION d'une ligne *********\n\n" +
				"LISTE DES ARTICLES \n"+tabCdes.retourner(num).toString()+
				"\n Quel ligne supprimer ? ", 1, Integer.MAX_VALUE);

		if(Cde.taille() >= lg && lg != 0){

			ESpane.affiche("La ligne n° "+lg+" a été supprimée! \n");
			Cde.supprimer(Cde.retourner(lg-1));	

			if(Cde.retourner(0) == null){
				tabCdes.supprimer(tabCdes.retourner(num));
			}

		}else{

			ESpane.affiche("La ligne n° "+lg+" N'EXISTE PAS!\n");

		}

	}


	/**
	 * Méthode visualiser qui visualise une ligne de commande
	 * 	 * 
	 * @param service du type ServiceCommande
	 */
	public static void visualiser(ServiceCommande service){

		// Déclaration des variables
		String num;
		num=IuCommande.numCde;

		if(service.retourner(num) != null){

			visualiserLigne(service);

		}else{

			ESpane.affiche("COMMANDE VIDE!");		

		}

	}

	/**
	 * Méthode visualiser qui visualise une ligne de commande
	 * correspondante au numéro de ligne saisie
	 * 
	 * @param service du type ServiceCommande
	 */
	public static void visualiserLigne(ServiceCommande service){

		// Déclaration des variables
		String num;
		num=IuCommande.numCde;
		int lg;

		lg = ESpane.saisie("******** VISUALISATION d'une ligne *********\n\n" +
				"LISTE DES LIGNES DES ARTICLES COMMANDEES \n"+service.retourner(num).toString()+
				"\nQuelle ligne voulez-vous visualiser ? ", 1, Integer.MAX_VALUE);

		if(Cde.taille() >= lg && lg != 0){

			ESpane.affiche("\nLa ligne n° "+lg+" est :\n"
					+Cde.retourner(lg-1).toString());	

		}else{

			ESpane.affiche("La ligne n° "+lg+" N'EXISTE PAS!\n");

		}		
	}

	/***  ***/
	/**
	 * Méthode modifier qui modifie la commande correspondante
	 * au code saisie avec une saisie assistée
	 * 
	 * @param service du type ServiceCommande
	 */
	public static void modifier(ServiceCommande service){
		// Déclaration des variables


		ESpane.affiche("******** MODIFICATION d'une ligne COMMANDE*********\n"	
				+"           ******** BIENTOT *********\n");	

	}


	/**
	 * Méthode visualiserTout qui affiche la liste des articles commandees
	 * 
	 * @param service du type ServiceCommande
	 */
	public static void visualiserTout(ServiceCommande service){
		// Déclaration des variables		
		String num;
		num=IuCommande.numCde;

		if(service.retourner(num) != null){
			ESpane.affiche("\n==> LISTE DES ARTICLES de la Commande \n\nCommande numero: "+num+
					" Date Cde: "+service.retourner(num).getDateCde()+service.retourner(num).toString());

		}else{
			ESpane.affiche("COMMANDE VIDE!");		
		}	

	}

}
