package com.afpa59.patrice.donnees;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Article extends Entite{

	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	private static final long serialVersionUID = 1L;
	private String designation;
	private float prix;


	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	/**
	 *	1er constructeur d'Article
	 */
	public Article(){}


	/**
	 * 2ème constructeur d'Article avec des paramètres
	 * 
	 * @param code du type Integer
	 * @param designation du type String
	 * @param prix du type Float
	 */
	public Article(int code, String designation, float prix){
		super(code);
		this.designation=designation;
		this.prix=prix;
	}


	/**
	 * 3ème constructeur d'Article avec des paramètres
	 * 
	 * @param designation du type String
	 * @param prix du type Float
	 */
	public Article(String designation, float prix) {
		// TODO Auto-generated constructor stub
		this.designation=designation;
		this.prix=prix;
	}


	/************************************/
	/*		Déclaration des GETTERS	*/
	/************************************/
	/**
	 * Méthode getDesignation
	 * 
	 * @return designation du type String
	 */
	public String getDesignation(){return designation;}

	/**
	 * Méthode getPrix
	 * 
	 * @return prix du type Float
	 */
	public float getPrix(){return prix;}


	/************************************/
	/*		Déclaration des SETTERS	*/
	/************************************/
	/**
	 * Méthode setDesignation
	 * 
	 * @param designation du type String
	 */
	public void setDesignation(String designation){this.designation=designation;}	

	/**
	 * Méthode setPrix
	 * 
	 * @param prix du type Float
	 */
	public void setPrix(float prix){this.prix=prix;}	



	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	/** 
	 * Méthode toString() retourne une chaîne de caractère
	 * 
	 *  @return String une chaîne de caractère 
	 *  avec le code, la désignation et le prix de l'Article
	 */
	public String toString(){return (super.toString()
			+ " Designation: "+ designation +
			" Prix: "+prix);
	}


	/**
	 * Méthode prixFacture
	 * 
	 * @param quantite
	 * @return Float le prix facturé
	 */
	public float prixFacture(int quantite){
		return (quantite*prix);
	}



	/**
	 * Méthode qui écrit une ligne d'article dans un PrintWriter
	 * 
	 * @param out du type PrintWriter
	 */
	public void writeData(PrintWriter out) throws IOException{
		super.writeData(out);
		out.println(designation + "|" + prix);		
	}



	/**
	 * Méthode qui lit une ligne du fichier qui correspond à une ligne Article
	 * 
	 * @param ligArticle du type String
	 */
	public void readData(String ligArticle) throws IOException{

		StringTokenizer t = new StringTokenizer(ligArticle, "|");
		int code = Integer.parseInt(t.nextToken());

		super.setCode(code);
		designation = t.nextToken();
		prix = Float.parseFloat(t.nextToken());	
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((designation == null) ? 0 : designation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		return true;
	}

}
