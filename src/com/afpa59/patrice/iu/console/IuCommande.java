package com.afpa59.patrice.iu.console;

import com.afpa59.patrice.donnees.Commande;

import com.afpa59.patrice.service.fichier.ServiceArticle;
import com.afpa59.patrice.service.fichier.ServiceClient;
import com.afpa59.patrice.service.fichier.ServiceCommande;

import com.afpa59.patrice.utils.DateUser;
import com.afpa59.patrice.utils.ESconsole;


public class IuCommande extends IuBase{
	
	static DateUser dateJ = new DateUser();
	static int numOrd;
	static String numCde;	
		
		/*** Méthode menuGeneral qui renvoie le choix du menu  ***/
		public static void menuGeneral(ServiceArticle s1, ServiceClient s2, ServiceCommande s3){
			int rep;
			do{
				rep = choix();
				switch(rep){
					case 1: 
												
						numOrd = s3.getTabCdes().size()+1;
						numCde = ""+dateJ.getAnnee()+dateJ.getMois()+dateJ.getJour()+numOrd;
						
						ESconsole.affiche("NumCde:" + numCde);
						
						IuUneCommande.menuGeneral(s1,s2, s3);
						
						break;
						
					case 2: visualiser(s3); break;
					case 3: modifier(s3); break;
					case 4: supprimer(s3); break;
					case 5: visualiserTout(s3); break;
					case 0: break;
				}
			}while(rep != 0);
		}

		/*** Méthode choix qui affiche un menu avec 6 choix
		 * et retourne un entier qui correspondant au choix ***/
		static int choix(){
			String s ="\n\t\t\t GESTION DES COMMANDES\n\n"+
			"\t\t CREATION D'UNE COMMANDE .............................. 1\n"+	
			"\t\t VISUALISATION D'UNE COMMANDE ......................... 2\n"+
			"\t\t MODIFICATION D'UNE COMMANDE .......................... 3\n"+
			"\t\t SUPPRESSION D'UNE COMMANDE ........................... 4\n"+
			"\t\t VISUALISATION DE LA LISTE DES COMMANDES .............. 5\n\n"+
			"\t\t FIN ............................................... 0\n\n"+
			"\t\t              Choix : ............ ";
			return ESconsole.saisie(s, 0, 5);
		}
		
		public static String cle(ServiceCommande tabCdes){
			return "\nLISTE DES CODES DES COMMANDES \n" + tabCdes.cle();
		}		

		/*** Méthode supprimer qui supprime la commande correspondante au code saisie ***/
		public static void supprimer(ServiceCommande tabCdes){
			if(tabCdes.taille()>0){
				effacerCommande(tabCdes);
			}else{
				ESconsole.affiche("AUCUNE COMMANDE CREE!!");
			}
		}

		public static void effacerCommande(ServiceCommande tabCdes){
			String num;
			num = ESconsole.saisie("\n******** SUPPRESSION d'une COMMANDE ********\n"
					+ cle(tabCdes)
					+ "\n\nVous voulez supprimez quelle commande (numero) :");
			Commande Cde = tabCdes.retourner(num);
			if(Cde != null){
				//			if(Cde.getEtatFacture()){
				String rep = ESconsole.saisie("Etes- vous certain de vouloir supprimer? (action irréversible) O/N:\n");
				if(rep.equals("O") || rep.equals("o")){
					tabCdes.supprimer(Cde);
					ESconsole.affiche("LA COMMANDE numero "+num+" EST SUPPRIME!\n\n");
				}
				//			}else{
				//				ESconsole.affiche("Cette COMMANDE n'est pas encore facturee... Suppression IMPOSSIBLE...");
				//			}
			}else{
				ESconsole.affiche("LE COMMANDE numero "+num+" N'EXISTE PAS! \n");
			}
		}	

		/*** Méthode visualiser qui visualise la commande correspondante au code saisie  ***/
		public static void visualiser(ServiceCommande tabCdes){
			if(tabCdes.taille()>0){
				apercuCommande(tabCdes);
			}else{
				ESconsole.affiche("AUCUNE COMMANDE CREE!!");
			}
		}

		public static void apercuCommande(ServiceCommande tabCdes){
			String num;
			num = ESconsole.saisie(cle(tabCdes)+"\n\nVous voulez editez quelle CDE? (numero) : ");
			if(tabCdes.retourner(num) != null){
				ESconsole.affiche("==> LISTE DES ARTICLES de la Commande \n\nCommande numero: "+num+
						" Date Cde: "+tabCdes.retourner(num).getDateCde()+ tabCdes.retourner(num).toString());
			}else{
				ESconsole.affiche("LE COMMANDE numero "+num+" N'EXISTE PAS! \n");
			}
		}

		public void facturerCommande(ServiceCommande tabCdes, ServiceArticle tabArt){		
			if(tabCdes.taille() == 0){
				ESconsole.affiche("AUCUNE FACTURE A EDITER");
			}else{
				editerCommande(tabCdes, tabArt);
			}
		}

		public void editerCommande(ServiceCommande tabCdes, ServiceArticle tabArt){
			String num;
			num = ESconsole.saisie(cle(tabCdes)+"\n\n Vous voulez éditer la FACTURE de quelle commande ?" +
					" (numero) : ");
			if(tabCdes.retourner(num) != null){
				ESconsole.affiche("\n"+tabCdes.retourner(num).facturer(tabArt));
			}else{
				ESconsole.affiche("LE COMMANDE numero "+num+" N'EXISTE PAS!");
			}
		}

		/*** Méthode visualiserTout qui affiche la liste des commandes ***/	
		public static void visualiserTout(ServiceCommande tabCdes){
			if(tabCdes.taille()>0){
				ESconsole.affiche("\n******** LISTES DES COMMANDES ********");
				ESconsole.affiche("\n"+tabCdes.toString()+"\n");
			}else{
				ESconsole.affiche("AUCUNE COMMANDE CREE!!");
			}
		}	

		/*** Méthode modifier qui modifie la commande correspondante au code saisie avec une saisie assistée ***/
		public static void modifier(ServiceCommande service){
			// Déclaration des variables
			
			ESconsole.affiche("******** MODIFICATION d'une COMMANDE*********\n");	
			ESconsole.affiche("           ******** BIENTOT *********\n");	

		}
	}
