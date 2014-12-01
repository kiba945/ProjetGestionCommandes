package com.afpa59.patrice.iu.console;

import com.afpa59.patrice.service.fichier.ServiceArticle;
import com.afpa59.patrice.utils.ESconsole;


public class IuArticle extends IuBase{
		
		/*** M�thode menuGeneral qui renvoie le choix du menu  ***/
		public static void menuGeneral(ServiceArticle s1){
			int rep;
			do{
				rep = choix();
				switch(rep){
					case 1: creer(s1); break;
					case 2: visualiser(s1); break;
					case 3: modifier(s1); break;
					case 4: supprimer(s1); break;
					case 5: visualiserTout(s1); break;
					case 0: break;
				}
			}while(rep != 0);
		}

		/*** M�thode menuGestionArticle qui affiche un menu avec 6 choix
		 * et retourne un entier qui correspondant au choix ***/
		static int choix(){
			String s ="\n\t\t\t GESTION DES ARTICLES\n\n"+
			"\t\t CREATION D'UN ARTICLE .............................. 1\n"+	
			"\t\t VISUALISATION D'UN ARTICLE ......................... 2\n"+
			"\t\t MODIFICATION D'UN ARTICLE .......................... 3\n"+
			"\t\t SUPPRESSION D'UN ARTICLE ........................... 4\n"+
			"\t\t VISUALISATION DE LA LISTE DES ARTICLES ............. 5\n\n"+
			"\t\t FIN ............................................... 0\n\n"+
			"\t\t              Choix : ............ ";
			return ESconsole.saisie(s, 0, 5);
		}

		/*** M�thode creerArticle qui cree un article en mode saisie assit�e  ***/
		public static void creer(ServiceArticle service){
			// D�claration des variables
			int code;
			String nom;
			float prix;
			code = ESconsole.saisie("\n********** CREATION D'UN ARTICLE **********\n\n"
					+ service.toString()
					+ "\nCode Article: ", 1, Integer.MAX_VALUE);
			if(service.retourner(code) == null){
				nom = ESconsole.saisie("D�signation: ");
				prix = ESconsole.saisie("Prix: ", 0F, Float.MAX_VALUE);
				service.creer(code, nom, prix);
				ESconsole.affiche("\n ...CREATION R�ussie...\n");
			}else{
				ESconsole.affiche("LE CODE ARTICLE " + code + " EXISTE DEJA!\n");
			}
		}

		/*** M�thode visualiserArticle qui visualise l'article correspondant au code saisie  ***/
		public static void visualiser(ServiceArticle service){
			// D�claration des variables
			int code;
			code = ESconsole.saisie("\n********** AFFICHAGE D'UN ARTICLE **********\n\n"
					+ service.toString()
					+"\nCode Article: ", 1, Integer.MAX_VALUE);
			if(service.retourner(code) != null){
				ESconsole.affiche(service.retourner(code).toString() + "\n");
			}else{
				ESconsole.affiche("LE CODE ARTICLE " + code + " N'EXISTE PAS!\n");
			}			
		}

		/*** M�thode modifierArticle qui modifie l'article correspondant au code saisie avec une saisie assist�e ***/
		public static void modifier(ServiceArticle service){
			// D�claration des variables
			int code;
			String nom;
			float prix;			
			code = ESconsole.saisie("\n********** MODIFICATION D'UN ARTICLE **********\n\n"
					+ service.toString()
					+ "\nCode Article: ", 1, Integer.MAX_VALUE);
			if(service.retourner(code) != null){
				nom = ESconsole.saisie("Nouveau Nom: ");
				prix = ESconsole.saisie("Nouveau prix: ", 0F, Float.MAX_VALUE);
				service.modifier(code, nom, prix);
			}else{
				ESconsole.affiche("LE CODE ARTICLE " + code + " N'EXISTE PAS!\n");
			}		
		}

		/*** M�thode supprimerArticle qui supprime l'article correspondant au code saisie ***/
		public static void supprimer(ServiceArticle service){
			int code;
			code = ESconsole.saisie("\n********** SUPPRESSION D'UN ARTICLE **********\n\n"
					+ service.toString()
					+ "\nCode Article: ", 1, Integer.MAX_VALUE);
			if(service.retourner(code) != null){
				String infoArticle = service.retourner(code).toString();
				service.supprimer(code);
				ESconsole.affiche("\n ...SUPPRESSION de l'article "+ infoArticle +" R�ussie...\n");
			}else{
				ESconsole.affiche("LE CODE ARTICLE " + code + " N'EXISTE PAS!\n");
			}		
		}
		
		/*** M�thode visualiserListeArticle qui affiche la liste des articles ***/	
		public static void visualiserTout(ServiceArticle service){
			ESconsole.affiche("\n********** LISTE DES ARTICLES **********\n\n"
					+service.toString()+"\n");
		}
	}
