package com.afpa59.patrice.service.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.afpa59.patrice.donnees.Article;
import com.afpa59.patrice.donnees.Client;
import com.afpa59.patrice.donnees.Entite;
import com.afpa59.patrice.service.commun.ServiceEntite;

public abstract class ServiceBase implements ServiceEntite{
	
	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	private ArrayList<Entite> tabEntite = new ArrayList<Entite>();
	
	
	
	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	
	/**
	 * 	1er constructeur 
	 */
	public ServiceBase() {
//		this.tabEntite = new ArrayList<Entite>();
	}	
	
	
	
	/************************************/
	/*		Déclaration des GETTERS	*/
	/************************************/
	/**
	 * @return tabEntite du type ArrayList<"Entite">
	 */
	public ArrayList<Entite> getTabEntite() { return tabEntite;	}
	
	
	
	/************************************/
	/*		Déclaration des SETTERS	*/
	/************************************/	
	/**
	 * @param tabEntite du type ArrayList<"Entite">
	 */
	public void setTabEntite(ArrayList<Entite> tabEntite) {
		this.tabEntite = tabEntite;
	}

	
	
	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	public void creer(){
		
	}
	
	public void visualiser(int code){
		
	}
	
	public void modifier(int code){
		
	}
	
	public void supprimer(int code) {
		// TODO Auto-generated method stub
		
	}
	
	public void supprimer(Entite entite){
		
	}
	

	public Entite retourner(int code){
		Entite ent = new Entite();
		return ent;
	}
	
	public String toString(){
		String st="";
		return st;
	}
	

	/**
		Ecrit tous les articles dans un tableau vers un printWriter
		@param tabArticle Un tableau d’articles
		@param out Un printWriter
	 */
	public void writeData(ArrayList<Entite> tabEntite, PrintWriter out) throws IOException{
//		// écrire le nombre d'articles
//		out.println(tabEntite.size());
//		
//		for (int i = 0; i < tabEntite.size(); i++){
//			tabEntite.get(i).writeData(out);
//		}
	}



//	public ArrayList<Entite> readData() throws IOException {
//		// TODO Auto-generated method stub
//		return null;
//	}



	public void writeData() {
		// TODO Auto-generated method stub
		
	}


	/**
		Lit un tableau d'articles à partir d’un lecteur bufférisé
		@param in Le lecteur bufférisé
		@return Le tableau d’articles
	 */
//	public ArrayList<Entite> readData(BufferedReader in) throws IOException{
//		
//		// récupérer la taille du tableau
//		//int n = Integer.parseInt(in.readLine());
//		ArrayList<Entite> tabEntite = new ArrayList<Entite>();
//		
//		
//		for (int i = 0; i < tabEntite.size(); i++){
//			tabEntite.get(i).readData(in);
//		}
//		
//		return tabEntite;
//	}

}
