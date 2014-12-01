package com.afpa59.patrice.utils;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ESconsole {
	
	public static Scanner sc = new Scanner(System.in);
	
	/*** M�thode qui controle la saisie d'un entier entre un intervalle donn�e (inf et sup) 
	 * avec un message d'accueil mis param�tre puis retourne un entier ***/
	public static int saisie(String lib, int inf, int sup){
		int ent;
		affiche(lib);
		do{
			try{
				ent = sc.nextInt();
				if(ent >= inf && ent <= sup) break;
				affiche("HORS INTERVALLE " + inf + " et " + sup + "\n");
			}catch (InputMismatchException e){
				affiche("SAISIE NON NUMERIQUE \n RESSAISSEZ SVP \n");
				sc.nextLine();
			}
		}while(true);
		return ent;
	}
	
	/*** M�thode qui controle la saisie d'un nombre r�el entre un intervalle donn�e (inf et sup) 
	 * avec un message d'accueil mis param�tre puis retourne un nombre r�el ***/
	public static float saisie(String lib, float inf, float sup){
		float reel;
		affiche(lib);
		do{
			try{
				reel = sc.nextFloat();
				if(reel >= inf && reel <= sup) break;
				affiche("HORS INTERVALLE " + inf + " et " + sup + "\n");
			}catch (InputMismatchException e){
				affiche("SAISIE NON NUMERIQUE \n RESSAISSEZ SVP \n");
				sc.nextLine();
			}
		}while(true);
		return reel;
	}
	
	/*** M�thode qui affiche un message mis en param�tre et demande une saisie clavier ***/
	public static String saisie(String mes){
		affiche(mes); return sc.next();
	}

	/*** M�thode qui affiche un message mis en param�tre ***/
	public static void affiche(String mes){
		System.out.print(mes);
	}
}
