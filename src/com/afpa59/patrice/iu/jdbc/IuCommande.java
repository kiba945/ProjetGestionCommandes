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

import com.afpa59.patrice.donnees.Commande;
import com.afpa59.patrice.service.jdbc.ServiceArticle;
import com.afpa59.patrice.service.jdbc.ServiceClient;
import com.afpa59.patrice.service.jdbc.ServiceCommande;
import com.afpa59.patrice.utils.DateUser;
import com.afpa59.patrice.utils.ESpane;



public class IuCommande extends IuBase implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static DateUser dateJ = new DateUser();
	static int numOrd;
	static String numCde;

	ServiceArticle s1; // pour récupérer les services lors du premier appel
	ServiceClient s2; // pour récupérer les services lors du premier appel
	ServiceCommande s3; // pour récupérer les services lors du premier appel

	public IuCommande(ServiceArticle s1, ServiceClient s2, ServiceCommande s3){		
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;

		Container contain= getContentPane();
		JPanel p1;
		JMenuBar barMenu;
		JMenu menu,menu2;
		JMenuItem menuItem;
		JLabel label= new JLabel("<html> <h3> GESTION des COMMANDES </h3> </html>");

		setTitle(" FrameMenuGestionCommandes");
		contain.setLayout(new GridLayout(1,1,20,10));

		// On cree un menu bar pour y mettre des menus
		barMenu= new JMenuBar();

		// preparation de chaque menu: menu 1
		menu = new JMenu(" C O M M A N D E ");
		menu.setMnemonic('C');	//Le menu dispose d'un mnémonique

		// Y ajouter des sous menus
		menuItem= new JMenuItem("Création/Ajout d'une commande");
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

		menuItem = new JMenuItem("Suppression d'une commande");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		barMenu.add(menu);

		SetRaccourci(menuItem, 'S');

		menuItem = new JMenuItem("Modification d'une commande");
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
		menu.setMnemonic('S');	//Le menu dispose d'un mnémonique

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

	public void actionPerformed(ActionEvent e) {

		String lib = e.getActionCommand();

		if (lib.equals("Affichage Liste")) {visualiserTout(s3); }

		else if(lib.equals("Affichage Formulaire")) {visualiser(s3);
		// ==> true + true cas 1 er appel pou remettre à 0 l'indice
		}

		//		else if(lib.equals("Affichage Articles Promo")) {  }

		else if(lib.equals("Création/Ajout d'une commande")) { 

			//			ESconsole.affiche("NumOrd: "+s3.getTabCdes().size());

			numOrd= s3.getTabCdes().size()+1;
			numCde = ""+dateJ.getAnnee()+dateJ.getMois()+dateJ.getJour()+numOrd;
			
			/*******************DEBUGING*******************************/
			ESpane.affiche("Création Cde num: " + numCde);
			/*******************DEBUGING*******************************/
			
			new IuUneCommande(s1,s2, s3);
		}

		else if(lib.equals("Suppression d'une commande")) { supprimer(s3);}

		else if(lib.equals("Modification d'une commande")) { modifier(s3);}

		else if(lib.equals("Retour")) { this.dispose();}

		//		else if(lib.equals("Sauvegarde")) { }

		else if(lib.equals("FIN")) { this.dispose(); }
	}

	public String cle(ServiceCommande tabCdes){
		return "LISTE DES CODES DES COMMANDES \n" + tabCdes.cle();
	}

	/*** Méthode supprimer qui supprime la commande correspondante au code saisie ***/
	public void supprimer(ServiceCommande tabCdes){
		if(tabCdes.taille()>0){
			effacerCommande(tabCdes);
		}else{
			ESpane.affiche("AUCUNE COMMANDE CREE!!");
		}
	}

	public void effacerCommande(ServiceCommande tabCdes){
		String num;
		num = ESpane.saisie("******** SUPPRESSION d'une COMMANDE ********\n\n" +cle(tabCdes)+
				"\n\n Vous voulez supprimez quelle commande (numero) : \n");
		Commande Cde = tabCdes.retourner(num);
		if(Cde != null){
			//			if(Cde.getEtatFacture()){
			String rep = ESpane.saisie("Etes- vous certain de vouloir supprimer? (action irréversible) O/N:\n");
			if(rep.equals("O") || rep.equals("o")){
				tabCdes.supprimer(Cde);
				ESpane.affiche("LA COMMANDE numero "+num+" EST SUPPRIME!\n");
			}
			//			}else{
			//				ESconsole.affiche("Cette COMMANDE n'est pas encore facturee... Suppression IMPOSSIBLE...");
			//			}
		}else{
			ESpane.affiche("LE COMMANDE numero "+num+" N'EXISTE PAS!");
		}
	}	

	/*** Méthode visualiser qui visualise la commande correspondante au code saisie  ***/
	public void visualiser(ServiceCommande tabCdes){
		if(tabCdes.taille()>0){
			apercuCommande(tabCdes);
		}else{
			ESpane.affiche("AUCUNE COMMANDE CREE!!");
		}
	}

	public void apercuCommande(ServiceCommande tabCdes){
		String num;
		num = ESpane.saisie("******** VISUALISATION DE LA COMMANDE ********\n\n"
				+ cle(tabCdes)
				+ "\n\n Vous voulez editez quelle CDE? (numero) : ");

		if(tabCdes.retourner(num) != null){

			ESpane.affiche("==> LISTE DES ARTICLES de la Commande \n\nCommande numero: "
					+ num
					+" Date Cde: "
					+tabCdes.retourner(num).getDateCde()
					+ tabCdes.retourner(num).toString());

		}else{

			ESpane.affiche("LE COMMANDE numero "+num+" N'EXISTE PAS!");

		}
	}

	public void facturerCommande(ServiceCommande tabCdes, ServiceArticle tabArt){		
		if(tabCdes.taille() == 0){
			ESpane.affiche("AUCUNE FACTURE A EDITER");
		}else{
			editerCommande(tabCdes, tabArt);
		}
	}

	public void editerCommande(ServiceCommande tabCdes, ServiceArticle tabArt){
		String num;
		num = ESpane.saisie(cle(tabCdes)+"\n\n Vous voulez éditer la FACTURE de quelle commande ?" +
				" (numero) : ");
		if(tabCdes.retourner(num) != null){
//			ESpane.affiche("\n"+tabCdes.retourner(num).facturer(tabArt));
		}else{
			ESpane.affiche("LE COMMANDE numero "+num+" N'EXISTE PAS!");
		}
	}

	/*** Méthode visualiserTout qui affiche la liste des commandes ***/	
	public void visualiserTout(ServiceCommande tabCdes){
		if(tabCdes.taille()>0){
		
			ESpane.affiche("******** LISTE DES COMMANDES ********\n"
			+tabCdes.toString()+"\n");
		
		}else{
			
			ESpane.affiche("AUCUNE COMMANDE CREE!!");
		
		}
	}	

	/*** Méthode modifier qui modifie la commande correspondante au code saisie avec une saisie assistée ***/
	public static void modifier(ServiceCommande service){
		// Déclaration des variables

		ESpane.affiche("******** MODIFICATION d'une COMMANDE*********\n"
				+"           ******** BIENTOT *********\n");	
	}

}
