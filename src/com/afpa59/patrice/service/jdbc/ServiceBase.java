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
	/* D�claration des variables d'instance */
	/****************************************/
	private ArrayList<Entite> tabEntite = new ArrayList<Entite>();
	
	
	
	/************************************/
	/*	D�claration des constructeurs	*/
	/************************************/
	
	/**
	 * 	1er constructeur 
	 */
	public ServiceBase() {
//		this.tabEntite = new ArrayList<Entite>();
	}	
	
	
	
	/************************************/
	/*		D�claration des GETTERS	*/
	/************************************/
	/**
	 * @return tabEntite du type ArrayList<"Entite">
	 */
	public ArrayList<Entite> getTabEntite() { return tabEntite;	}
	
	
	
	/************************************/
	/*		D�claration des SETTERS	*/
	/************************************/	
	/**
	 * @param tabEntite du type ArrayList<"Entite">
	 */
	public void setTabEntite(ArrayList<Entite> tabEntite) {
		this.tabEntite = tabEntite;
	}

	
	
	/************************************/
	/*		D�claration des m�thodes	*/
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
		@param tabArticle Un tableau d�articles
		@param out Un printWriter
	 */
	public void writeData(ArrayList<Entite> tabEntite, PrintWriter out) throws IOException{
//		// �crire le nombre d'articles
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
		Lit un tableau d'articles � partir d�un lecteur buff�ris�
		@param in Le lecteur buff�ris�
		@return Le tableau d�articles
	 */
//	public ArrayList<Entite> readData(BufferedReader in) throws IOException{
//		
//		// r�cup�rer la taille du tableau
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
