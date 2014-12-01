package com.afpa59.patrice.service.fichier;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import com.afpa59.patrice.donnees.Article;
import com.afpa59.patrice.donnees.Entite;

public class ServiceArticle extends ServiceBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	private ArrayList<Article> tabArticle = new ArrayList<Article>();


	/************************************/
	/*		Déclaration des GETTERS	*/
	/************************************/
	/**
	 * @return tabArticle la liste d'article
	 */
	public ArrayList<Article> getTabArticle() {return tabArticle;}


	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public ServiceArticle(){
		// Création d'un tableau d'article de base
//		Article a1 = new Article(1,"Disque dur 1To",99);
//		Article a2 = new Article(2,"Clé USB 8Go",25);
//		Article a3 = new Article(3,"Carte graphique",600);
//
//		tabArticle.add(a1);
//		tabArticle.add(a2);
//		tabArticle.add(a3);	
	}

	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	/*** Méthode creer() crée 3 articles  ***/
	public void creer(){
		// Création d'un tableau d'article de base
		//		tabArticle.add(new Article(1,"Disque dur 1To",99));
		//		tabArticle.add(new Article(2,"Clé USG 8Go",25));
		//		tabArticle.add(new Article(3,"Carte graphique",600));
		//		ESconsole.affiche("\n********** CREATION CATALOGUE DE BASE ARTICLE **********\n\n"
		//				+ "Réussie...\n");
	}

	/*** Méthode creer qui a en paramétre un code, un nom, un prix
	 *  et qui crée l'article ***/	
	public void creer(int code, String nom, float prix){
		// Création d'un article
		tabArticle.add(new Article(code,nom,prix));
	}

	/*** Méthode visualiser qui a en paramétre un code
	 *  et affiche l'article correspondant ***/	
	public void visualiser(int code){
		if(retourner(code) != null){  
			retourner(code).toString();
		}
	}

	/*** Méthode modifier qui a en paramétre un code
	 *  et qui modifie l'article correspondant ***/	
	public void modifier(int code){
		// Utile pour l'écran de saisie
	}

	/*** Méthode modifier qui a en paramétre un code, un nom, un prix
	 *  et qui modifie l'article correspondant ***/	
	public void modifier(int code, String nom, float prix){
		for(int i = 0; i < tabArticle.size(); i++){	
			if(tabArticle.get(i).getCode() == code){
				// Modfification de l'article
				tabArticle.set(i, new Article(code,nom,prix)); break;
			}
		}		
	}

	/*** Méthode supprimer qui a en paramétre un code
	 * et qui supprime l'article correspondant ***/
	public void supprimer(int code){
		for(int i = 0; i < tabArticle.size(); i++){	
			if(tabArticle.get(i).getCode() == code){
				// Modfification de l'article
				tabArticle.remove(i);
				break;
			}
		}
	}

	/*** Méthode retourner qui a en paramétre un code 
	 * et qui retourne l'article correspondant ***/
	public Article retourner(int code){
		for(Article art : tabArticle){ // For ... each utile pour consultation pas pour modification
			if(art !=  null && art.getCode() == code){	
				return art;
			}
		}
		return null;
	}	

	/*** Méthode toString() retourne une chaîne de caractère  ***/
	public String toString(){
		String st = new String();
		for(Article art : tabArticle){
			st =st + art.toString() + "\n";
		}
		return st;
	}
	
	
	
	/**
	* Lit un tableau d'articles à partir d’un lecteur bufférisé
	* 
	* @param in Le lecteur bufférisé
	* @return Le tableau d’articles
	*/
	public ArrayList<Article> readData(BufferedReader in) throws IOException{
		
		String s;
		while((s=in.readLine())!=null){
			Article article = new Article();
			article.readData(s);
			tabArticle.add(article);
		}
		
		return tabArticle;
	}

	
	/**
	* Ecrit tous les articles dans un tableau vers un printWriter
	* @param tabArticle Un tableau d’articles
	* @param out Un printWriter
	 */
	public void writeData(PrintWriter out) throws IOException {
		// TODO Auto-generated method stub
		
		// écrire le nombre d'articles
		//out.println(tabArticle.size());

		for (int i = 0; i < tabArticle.size(); i++){
			tabArticle.get(i).writeData(out);
		}
	}
	
	
	
	/**
	* Ecrit tous les articles dans un tableau vers un printWriter
	* @param tabArticle Un tableau d’articles
	* @param out Un printWriter
	 */
	public void writeData(ArrayList<Entite> tabArticle, PrintWriter out) throws IOException{}
	
	/**
	* Lit un tableau d'articles à partir d’un lecteur bufférisé
	* @param in Le lecteur bufférisé
	* @return Le tableau d’articles
	 */
//	public ArrayList<Entite> readData(BufferedReader in) throws IOException{ }
	
}
