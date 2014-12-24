package com.afpa59.patrice.utils;

import javax.swing.JOptionPane;

public class ES {
	
	public static int saisie(String mes, int inf, int sup){
		int ent=0;
		do{
			String s = JOptionPane.showInputDialog(null,mes);
			try{
				ent = Integer.parseInt(s);
				if(ent >= inf && ent <= sup) break;
					affiche("HORS INTERVALLE " + inf + " et " + sup + "\n");
			}catch (NumberFormatException e){
				affiche("SAISIE NON NUMERIQUE \n RESSAISSEZ SVP");
			}
		}while(true); 
		return ent;
	}
	
	public static float saisie(String mes, float inf, float sup){
		float reel;
		do{
			String s = JOptionPane.showInputDialog(null,mes);
			try{
				reel = Float.parseFloat(s);
				if(reel >= inf && reel <= sup) break;
				affiche("HORS INTERVALLE " + inf + " et " + sup + "\n");
			}catch (NumberFormatException e){
				affiche("SAISIE NON NUMERIQUE \n RESSAISSEZ SVP \n");
			}
		}while(true);
		return reel;
	}
	
	public static String saisie(String mes){
		return JOptionPane.showInputDialog(null,mes);
	}
	
	public static void affiche(String mes){
		JOptionPane.showMessageDialog(null,mes);
	}
}

