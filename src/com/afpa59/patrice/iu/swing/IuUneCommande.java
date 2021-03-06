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
import com.afpa59.patrice.utils.ES;



public class IuUneCommande extends IuBase implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ServiceArticle s1; // pour r�cup�rer les services lors du premier appel
	ServiceClient s2; // pour r�cup�rer les services lors du premier appel
	ServiceCommande s3; // pour r�cup�rer les services lors du premier appel
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
		menu.setMnemonic('L');	//Le menu dispose d'un mn�monique

		// Y ajouter des sous menus
		menuItem= new JMenuItem("Cr�ation/Ajout d'une ligne de commande");
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
		menu.setMnemonic('S');	//Le menu dispose d'un mn�monique
		
		menuItem= new JMenuItem("FIN");
		menuItem.addActionListener(this);

		menu.add(menuItem);
		barMenu.add(menu);
		
		SetRaccourci(menuItem, 'F');
		
		menu.addActionListener(this);

		this.setJMenuBar(barMenu);

		// ajoute le menu bar au container + le cale � gauche de la frame

		p1= new JPanel();
		p1.add(label);
		contain.add(p1);

		setSize(750,300); //LONGUEUR * HAUTEUR de la frame
		//pack();
		this.setLocationRelativeTo(null); // POUR CENTRER LA FRAME
		setMinimumSize(new Dimension(550, 200)); // D�finir une taille de fenetre minimum
		this.setVisible(true);
		//this.show();

	}
	public void actionPerformed(ActionEvent e) {

		String lib = e.getActionCommand();


		if(lib.equals("Cr�ation/Ajout d'une ligne de commande")) { creer(s1,s2,s3);}


		if(lib.equals("Suppression d'une ligne de commande")) { supprimer(s3);}


		if(lib.equals("Modification d'une ligne de commande")) { modifier(s3);}


		if (lib.equals("Affichage de la commande (en cours)")) {visualiser(s3); }		


		if(lib.equals("Retour")) { if(s3.retourner(IuCommande.numCde) != null){
			IuCommande.numOrd++;
			IuCommande.numCde = ""+Cde.getDateCde().getAnnee()+
					Cde.getDateCde().getMois()+Cde.getDateCde().getJour()+
					IuCommande.numOrd;
		}
		ES.affiche("Fin de Traitement de la Commande"); this.dispose();}
		//		

//		if(lib.equals("Sauvegarde")) { }


		if(lib.equals("FIN")) { if(s3.retourner(IuCommande.numCde) != null){
			IuCommande.numOrd++;
			IuCommande.numCde = ""+Cde.getDateCde().getAnnee()+
					Cde.getDateCde().getMois()+Cde.getDateCde().getJour()+
					IuCommande.numOrd;
		}
		ES.affiche("Fin de Traitement de la Commande"); this.dispose();}
	}


	/*** M�thode creer qui cree une commande en mode saisie assit�e  ***/

	public static void creer(ServiceArticle s1, ServiceClient s2, ServiceCommande tabCdes){
		// D�claration des variables
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

			nouveau = ES.saisie("Voulez-vous saisir une autre ligne de commande? (O/N) ").charAt(0);
		}while(nouveau == 'O' || nouveau == 'o');
	}


	public static LigneDeCommande creerLdc(ServiceArticle s1){
		// D�claration des variables
		LigneDeCommande ldc = new LigneDeCommande();

		int code = ES.saisie("LISTE des ARTICLES EN CATALOGUE\n\n"+s1.toString()+
				"\nSaisir code Produit: ", 1, Integer.MAX_VALUE);

		Article a = s1.retourner(code);

		if(a!=null){
			int qte = ES.saisie("Quelle quantite: ", 1, Integer.MAX_VALUE);
			ldc = new LigneDeCommande(code,qte);
			return ldc;

		}else{
			ES.affiche("LE CODE ARTICLE COMMANDE N'EXISTE PAS ....\n");

			return ldc=null;

		}
	}

	/*** M�thode supprimer qui supprime la commande correspondante au code saisie ***/
	public void supprimer(ServiceCommande tabCdes){

		String num = IuCommande.numCde;

		if(tabCdes.retourner(num) != null){	
			effacerLigne(tabCdes);

		}else{
			ES.affiche("COMMANDE VIDE!");
		}
	}


	public void effacerLigne(ServiceCommande tabCdes){

		int lg;
		String num = IuCommande.numCde;

		lg = ES.saisie("******** SUPPRESSION d'une ligne *********\n\n" +
				"LISTE DES ARTICLES \n"+tabCdes.retourner(num).toString()+
				"\n Quel ligne supprimer ? ", 1, Integer.MAX_VALUE);

		if(Cde.taille() >= lg && lg != 0){

			ES.affiche("La ligne n� "+lg+" a �t� supprim�e! \n");
			Cde.supprimer(Cde.retourner(lg-1));	

			if(Cde.retourner(0) == null){
				tabCdes.supprimer(tabCdes.retourner(num));
			}
			
		}else{
			
			ES.affiche("La ligne n� "+lg+" N'EXISTE PAS!\n");
			
		}

	}


	/*** M�thode visualiser qui visualise la commande correspondante au code saisie  ***/
	public static void visualiser(ServiceCommande tabCdes){
		// D�claration des variables
		String num;
		num=IuCommande.numCde;
		if(tabCdes.retourner(num) != null){
			ES.affiche("==> LISTE DES ARTICLES de la Commande \n\nCommande numero: "+num+
					" Date Cde: "+tabCdes.retourner(num).getDateCde()+tabCdes.retourner(num).toString());
		}else{
			ES.affiche("COMMANDE VIDE!");		}					
	}

	/*** M�thode modifier qui modifie la commande correspondante au code saisie avec une saisie assist�e ***/
	public static void modifier(ServiceCommande service){
		// D�claration des variables

	}


	/*** M�thode visualiserTout qui affiche la liste des commandes ***/	
	public static void visualiserTout(ServiceCommande service){

	}
}
