package com.afpa59.patrice.iu.console;

import com.afpa59.patrice.service.fichier.ServiceClient;
import com.afpa59.patrice.utils.ESconsole;


public class IuClient extends IuBase{

	/*** Méthode menuGeneral qui renvoie le choix du menu  ***/
	public static void menuGeneral(ServiceClient s1){
		int rep;
		do{
			rep = choix();
			switch(rep){
			case 1: creer(s1);break;
			case 2: visualiser(s1);break;
			case 3: modifier(s1); break;
			case 4: supprimer(s1); break;
			case 5: visualiserTout(s1); break;
			case 0: break;
			default: ESconsole.affiche("\n CHOIX INEXISTANT!!! \n");
			}
		}while(rep != 0);
	}

	/*** Méthode menuGestionClient qui affiche un menu avec 6 choix
	 * et retourne un entier qui correspondant au choix ***/		
	static int choix(){
		String s = "\n\t\t\t GESTION des CLIENTS\n\n"
				+"\t\t CREATION D'UN NOUVEAU CLIENT ...................... 1\n"	
				+"\t\t VISUALISATION D'UN CLIENT ......................... 2\n"
				+"\t\t MODIFICATION D'UN CLIENT .......................... 3\n"
				+"\t\t SUPPRESSION D'UN CLIENT ........................... 4\n"
				+"\t\t VISUALISATION DE LA LISTE CLIENT .................. 5\n\n"
				+"\t\t FIN ............................................... 0\n"
				+"\t\t              Choix : ............ ";
		return ESconsole.saisie(s, 0, 5);
	}

	/*** Méthode creerClient qui cree un client en mode saisie assitée  ***/
	public static void creer(ServiceClient service){
		int code = ESconsole.saisie("\n********** CREATION d'un nouveau CLIENT **********\n\n"
				+ service.toString()+"\n"
				+"Saisir code client: ", 1, Integer.MAX_VALUE);
		if(service.retourner(code) == null){
			String nom = ESconsole.saisie("Saisir Nom du client: ");
			String prenom = ESconsole.saisie("Saisir Prenom du client: ");
			String adresse = ESconsole.saisie("Saisir Adresse du client: ");
			service.creer(code, nom, prenom, adresse);
			ESconsole.affiche("\n ...CREATION Réussie...\n");
		}else{
			ESconsole.affiche("LE CODE CLIENT " + code + " EXISTE DEJA!\n");
		}
	}

	/*** Méthode visualiserClient qui visualise le client correspondant au code saisie  ***/
	public static void visualiser(ServiceClient service){
		int code = ESconsole.saisie("\n********** AFFICHAGE D'UN CLIENT **********\n\n"
				+ service.toString()+"\n"
				+"Saisir code client: ", 1, Integer.MAX_VALUE);
		if(service.retourner(code) != null){
			ESconsole.affiche(service.retourner(code).toString() + "\n");
		}else{
			ESconsole.affiche("LE CODE CLIENT " + code + " N'EXISTE PAS!\n");
		}			
	}

	/*** Méthode modifierClient qui modifie le client correspondant au code saisie avec une saisie assistée***/
	public static void modifier(ServiceClient service){
		int code = ESconsole.saisie("\n********** MODIFICATION d'un CLIENT **********\n\n"
				+ service.toString()+"\n"
				+"Saisir code client: ", 1, Integer.MAX_VALUE);
		if(service.retourner(code) != null){
			String nom = ESconsole.saisie("Ancien nom du client ("+service.retourner(code).getNom()+")"
					+"\nSaisir nouveau Nom: ");
			String prenom = ESconsole.saisie("Ancien prenom du client ("+service.retourner(code).getPrenom()+")"
					+"\nSaisir nouveau Prenom: ");
			String adresse = ESconsole.saisie("Ancienne adresse du client ("+service.retourner(code).getAdresse()+")"
					+"\nSaisir nouvelle Adresse: ");
			service.modifier(code, nom, prenom, adresse);
		}else{
			ESconsole.affiche("LE CODE CLIENT " + code + " N'EXISTE PAS!\n");
		}		
	}

	/*** Méthode supprimerClient qui supprime le client correspondant au code saisie ***/	
	public static void supprimer(ServiceClient service){
		int code = ESconsole.saisie("\n********** SUPPRESSION D'UN CLIENT **********\n\n"
				+ service.toString()+"\n"
				+ "Saisir code client: ", 1, Integer.MAX_VALUE);
		if(service.retourner(code) != null){
			String infoClt = service.retourner(code).toString();
			service.supprimer(code);
			ESconsole.affiche("\n ...SUPPRESSION du client "+ infoClt +" Réussie...\n");
		}else{
			ESconsole.affiche("LE CODE CLIENT " + code + " N'EXISTE PAS!\n");
		}		
	}

	/*** Méthode visualiserListeclient qui affiche la liste des clients ***/	
	public static void visualiserTout(ServiceClient s1){
		ESconsole.affiche("\n********** LISTE DES CLIENTS **********\n\n"
				+s1.toString());
	}
}
