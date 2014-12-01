package com.afpa59.patrice.utils;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ESconsole {
	
	public static Scanner sc = new Scanner(System.in);
	
	/*** Méthode qui controle la saisie d'un entier entre un intervalle donnée (inf et sup) 
	 * avec un message d'accueil mis paramétre puis retourne un entier ***/
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
	
	/*** Méthode qui controle la saisie d'un nombre réel entre un intervalle donnée (inf et sup) 
	 * avec un message d'accueil mis paramétre puis retourne un nombre réel ***/
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
	
	/*** Méthode qui affiche un message mis en paramétre et demande une saisie clavier ***/
	public static String saisie(String mes){
		affiche(mes); return sc.next();
	}

	/*** Méthode qui affiche un message mis en paramétre ***/
	public static void affiche(String mes){
		System.out.print(mes);
	}
}
