package com.afpa59.patrice.iu.console;

import com.afpa59.patrice.service.fichier.ServiceArticle;
import com.afpa59.patrice.service.fichier.ServiceClient;
import com.afpa59.patrice.service.fichier.ServiceCommande;

import com.afpa59.patrice.utils.ConnectionFichiersArticles;
import com.afpa59.patrice.utils.ConnectionFichiersClients;
import com.afpa59.patrice.utils.ConnectionFichiersCommandes;

import com.afpa59.patrice.utils.ESconsole;


public class IuGeneral extends IuBase {
	
	static ServiceArticle s1;
	static ServiceClient s2;
	static ServiceCommande s3;
	
	static ConnectionFichiersArticles fichArt;
	static ConnectionFichiersClients fichClt;
	static ConnectionFichiersCommandes fichCde;


	static String nomPhysiqueArticle = "TableArticles";
	static String nomPhysiqueClient = "TableClients";
	static String nomPhysiqueCommande = "TableCommandes";

	public static void menuGeneral(){
		
		fichArt = new ConnectionFichiersArticles(nomPhysiqueArticle);
		fichClt = new ConnectionFichiersClients(nomPhysiqueClient);
		fichCde = new ConnectionFichiersCommandes(nomPhysiqueCommande);

		s1 = fichArt.getTab();
		s2 = fichClt.getTab();
		s3 = fichCde.getTab();

		String mes;

		mes = "*** CHARGEMENT du FICHIER des ARTICLES ***\n";
		if(s1 == null){

			s1 = new ServiceArticle(); 
			mes = mes + "*** TABLE ARTICLES VIDE !! ==>" +
					"CREATION par DEFAUT de la TABLE des ARTICLES ***\n";
		}


		mes = mes + "\n*** CHARGEMENT du FICHIER des CLIENTS ***\n";
		if(s2 == null){
			s2 = new ServiceClient();
			mes = mes + "*** TABLE des CLIENTS VIDE ***" +
					"CREATION par DEFAUT de la TABLE des CLIENTS ***\n";
		}


		mes = mes + "\n*** CHARGEMENT du FICHIER des COMMANDES ***\n";
		if(s3 == null){
			s3 = new ServiceCommande();
			mes = mes + "*** TABLE des COMMANDES VIDE ***";
		}		
		ESconsole.affiche(mes);			
		/**
		 * 
		 */
		int rep;
//		ServiceArticle s1 = new ServiceArticle();
//		ServiceClient s2 = new ServiceClient();
//		ServiceCommande s3 = new ServiceCommande();
//		s1.creer(); 
//		s2.creer();
//		s3.creer();

		do{
			rep = choix();
			switch(rep){
			case 1: IuArticle.menuGeneral(s1); break;
			case 2: IuClient.menuGeneral(s2); break;
			case 3: IuCommande.menuGeneral(s1,s2,s3); break;
			case 0:  break;
			}
		}while(rep != 0);
		sauvegardeFichiers(nomPhysiqueArticle, nomPhysiqueClient, nomPhysiqueCommande);
		ESconsole.affiche("\n\t AU REVOIR ... A BIENTOT!!");
	}

	/*** Méthode menuGeneral qui affiche un menu avec 2 choix
	 * et retourne un entier qui correspondant au choix ***/
	static int choix(){
		String s = "\n\t\t\t   BIENVENUE\n\n"+
				"\t\t GESTION DES ARTICLES .............. 1\n"+
				"\t\t GESTION DES CLIENTS ............... 2\n"+
				"\t\t GESTION DES COMMANDES ............. 3\n\n"+
				"\t\t FIN ............................. 0\n\n"+
				"\t\t             Choix : ............ ";
		return ESconsole.saisie(s, 0, 3);
	}
	
	public static void sauvegardeFichiers(String nomPhysiqueArticle, String nomPhysiqueClient, String nomPhysiqueCommande){
		
		ESconsole.affiche(" Le fichier ARTICLES sera sauvegardé sous le nom suivant: "
				+ nomPhysiqueArticle+"\n");
		fichArt.ecrire(nomPhysiqueArticle);


		ESconsole.affiche(" Le fichier CLIENTS sera sauvegardé sous le  nom suivant: "
				+ nomPhysiqueClient+"\n");
		fichClt.ecrire(nomPhysiqueClient);


		ESconsole.affiche(" Le fichier COMMANDES sera sauvegardé sous le nom suivant: "
				+ nomPhysiqueCommande+"\n\n");
		fichCde.ecrire(nomPhysiqueCommande);
	}
}
