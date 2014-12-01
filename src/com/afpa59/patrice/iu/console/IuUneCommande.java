package com.afpa59.patrice.iu.console;

import com.afpa59.patrice.donnees.Article;
import com.afpa59.patrice.donnees.Commande;
import com.afpa59.patrice.donnees.LigneDeCommande;
import com.afpa59.patrice.service.fichier.ServiceArticle;
import com.afpa59.patrice.service.fichier.ServiceClient;
import com.afpa59.patrice.service.fichier.ServiceCommande;
import com.afpa59.patrice.utils.ESconsole;



public class IuUneCommande extends IuBase {

	static Commande Cde;


	/**
	 * Méthode menuGeneral qui renvoie un choix de menu
	 * 
	 * @param s1 du type ServiceArticle
	 * @param s2 du type ServiceClient
	 * @param s3 du type ServiceCommande
	 */
	public static void menuGeneral(ServiceArticle s1, ServiceClient s2, ServiceCommande s3){
		int rep;
		do{
			rep = choix();
			switch(rep){
			case 1: creer(s1,s2,s3); break;
			case 2: visualiser(s3); break;
			case 3: modifier(s3); break;
			case 4: supprimer(s3); break;
			case 5: visualiserTout(s3); break;
			case 0: if(s3.retourner(IuCommande.numCde) != null){
				
				ESconsole.affiche("Fin de Traitement de la Commande\n"
						+IuCommande.numCde);

				IuCommande.numOrd++;

				IuCommande.numCde = ""
						+Cde.getDateCde().getAnnee()
						+Cde.getDateCde().getMois()
						+Cde.getDateCde().getJour()
						+IuCommande.numOrd;

				break;


			}
			}
		}while(rep != 0);
	}

	
	/**
	 * Méthode choix qui affiche un menu avec X choix
	 * et retourne un entier qui correspondant au choix
	 * 
	 * @return int du type Integer
	 */
	static int choix(){
		String s ="\n\t\t\t GESTION LIGNE DE COMMANDE\n\n"+
				"\t\t CREATION D'UNE LIGNE DE COMMANDE .............................. 1\n"+	
				"\t\t VISUALISATION D'UNE LIGNE DE COMMANDE ......................... 2\n"+
				"\t\t MODIFICATION D'UNE LIGNE DE COMMANDE .......................... 3\n"+
				"\t\t SUPPRESSION D'UNE LIGNE DE COMMANDE ........................... 4\n"+
				"\t\t VISUALISATION DE LA COMMANDE .................................. 5\n\n"+
				"\t\t FIN ...................................................... 0\n\n"+
				"\t\t              Choix : ............ ";
		return ESconsole.saisie(s, 0, 5);
	}
	

	/**
	 * Méthode creer qui cree une commande en mode saisie assitée
	 * 
	 * @param s1 du type ServiceArticle
	 * @param s2 du type ServiceClient
	 * @param s3 du type ServiceCommande
	 */
	public static void creer(ServiceArticle s1, ServiceClient s2, ServiceCommande s3){
		// Déclaration des variables
		char nouveau;

		if(s3.retourner(IuCommande.numCde) == null){
			Cde = new Commande();
		}

		do{
			LigneDeCommande ldc = creerLdc(s1);

			if(ldc != null){
				Cde.setCodeCde(IuCommande.numCde);
				Cde.ajouter(ldc);
				s3.creer(Cde);
			}

			nouveau = ESconsole.saisie("Voulez-vous saisir une autre ligne de commande? (O/N) ").charAt(0);
		}while(nouveau == 'O' || nouveau == 'o');
	}


	/**
	 * Méthode creerLdc qui retourne une ligne de commande
	 * 
	 * @param service du type ServiceArticle
	 * @return LigneDeCommande
	 */
	public static LigneDeCommande creerLdc(ServiceArticle service){
		// Déclaration des variables
		LigneDeCommande ldc = new LigneDeCommande();

		int code = ESconsole.saisie("LISTE des ARTICLES EN CATALOGUE\n\n"+service.toString()+
				"\nSaisir code Produit: ", 1, Integer.MAX_VALUE);

		Article a = service.retourner(code);

		if(a!=null){
			int qte = ESconsole.saisie("Quelle quantite: ", 1, Integer.MAX_VALUE);
			ldc = new LigneDeCommande(code,qte);
			return ldc;

		}else{
			ESconsole.affiche("LE CODE ARTICLE COMMANDE N'EXISTE PAS ....\n");

			return ldc=null;

		}
	}



	/**
	 * Méthode supprimer qui supprime la ligne de commande
	 * 
	 * @param service du type ServiceCommande
	 */
	public static void supprimer(ServiceCommande service){

		String num = IuCommande.numCde;

		if(service.retourner(num) != null){	
			
			effacerLigne(service);

		}else{
			
			ESconsole.affiche("COMMANDE VIDE!");
			
		}
	}


	/**
	 * Méthode effacerLigne qui supprime une ligne correspondante au code saisie
	 * 
	 * @param service du type ServiceCommande
	 */
	public static void effacerLigne(ServiceCommande service){

		int lg;
		String num = IuCommande.numCde;

		lg = ESconsole.saisie("******** SUPPRESSION d'une ligne *********\n\n" +
				"LISTE DES ARTICLES \n"+service.retourner(num).toString()+
				"Quelle ligne supprimer ? ", 1, Integer.MAX_VALUE);

		if(Cde.taille() >= lg && lg != 0){

			
			Cde.supprimer(Cde.retourner(lg-1));	
			ESconsole.affiche("La ligne n° "+lg+" a été supprimée! \n");

			if(Cde.retourner(0) == null){
				service.supprimer(service.retourner(num));
			}

		}else{

			ESconsole.affiche("La ligne n° "+lg+" N'EXISTE PAS!\n");

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
		
			ESconsole.affiche("COMMANDE VIDE!");		
			
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
		
		lg = ESconsole.saisie("******** VISUALISATION d'une ligne *********\n\n" +
				"LISTE DES LIGNES DES ARTICLES COMMANDEES \n"+service.retourner(num).toString()+
				"\nQuelle ligne voulez-vous visualiser ? ", 1, Integer.MAX_VALUE);
		
		if(Cde.taille() >= lg && lg != 0){

			ESconsole.affiche("\nLa ligne n° "+lg+" est :\n"
					+Cde.retourner(lg-1).toString());	

		}else{

			ESconsole.affiche("La ligne n° "+lg+" N'EXISTE PAS!\n");

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

		
		ESconsole.affiche("******** MODIFICATION d'une ligne COMMANDE*********\n");	
		ESconsole.affiche("           ******** BIENTOT *********\n");	
		
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
			ESconsole.affiche("\n==> LISTE DES ARTICLES de la Commande \n\nCommande numero: "+num+
					" Date Cde: "+service.retourner(num).getDateCde()+service.retourner(num).toString());
		
		}else{
			ESconsole.affiche("COMMANDE VIDE!");		}	
	
	}
}
