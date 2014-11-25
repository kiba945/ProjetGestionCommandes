package com.afpa59.patrice.donnees;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Article extends Entite{
	
	/****************************************/
	/* D�claration des variables d'instance */
	/****************************************/
	private static final long serialVersionUID = 1L;
	private String designation;
	private float prix;
	
	
	/************************************/
	/*	D�claration des constructeurs	*/
	/************************************/
	/**
	 *	1er constructeur d'Article
	 */
	public Article(){}
	

	/**
	 * 2�me constructeur d'Article avec des param�tres
	 * 
	 * @param code du type Integer
	 * @param designation du type String
	 * @param prix du type Float
	 */
	public Article(int code, String designation, float prix){
		//super(code, designation);
		super(code);
		this.designation=designation;
		this.prix=prix;
	}
	
	
	
	/************************************/
	/*		D�claration des GETTERS	*/
	/************************************/
	/**
	 * M�thode getDesignation
	 * 
	 * @return designation du type String
	 */
	public String getDesignation(){return designation;}
	
	/**
	 * M�thode getPrix
	 * 
	 * @return prix du type Float
	 */
	public float getPrix(){return prix;}
	
	
	/************************************/
	/*		D�claration des SETTERS	*/
	/************************************/
	/**
	 * M�thode setDesignation
	 * 
	 * @param designation du type String
	 */
	public void setDesignation(String designation){this.designation=designation;}	
	
	/**
	 * M�thode setPrix
	 * 
	 * @param prix du type Float
	 */
	public void setPrix(float prix){this.prix=prix;}	
	
	
	
	/************************************/
	/*		D�claration des m�thodes	*/
	/************************************/
	/** 
	* M�thode toString() retourne une cha�ne de caract�re
	* 
	*  @return String une cha�ne de caract�re 
	*  avec le code, la d�signation et le prix de l'Article
	*/
	public String toString(){return (super.toString()
			+" Designation: "+designation+
			" Prix: "+prix);
	}
	
	
	/**
	 * M�thode prixFacture
	 * 
	 * @param quantite
	 * @return Float le prix factur�
	 */
	public float prixFacture(int quantite){
		return (quantite*prix);
	}
	
	
	
	/**
	* M�thode qui �crit une ligne d'article dans un PrintWriter
	* 
	* @param out du type PrintWriter
	*/
	public void writeData(PrintWriter out) throws IOException{
		super.writeData(out);
		out.println(designation + "|" + prix);		
	}


	
	/**
	* M�thode qui lit une ligne du fichier qui correspond � une ligne Article
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
	
}
