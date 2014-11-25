package com.afpa59.patrice.donnees;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.StringTokenizer;

public class Entite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************************/
	/* D�claration des variables d'instance */
	/****************************************/
	private int code;
	//private String nom;
	
	/************************************/
	/*	D�claration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public Entite(){}
	/*** 2�me constructeur avec des param�tres ***/
	//public Entite(int code, String nom){
	public Entite(int code){
		this.code=code;
		//this.nom=nom;
	}
	
	/************************************/
	/*		D�claration des GETTEURS	*/
	/************************************/
	public int getCode(){return code;}
	//public String getNom(){return nom;}
	
	/************************************/
	/*		D�claration des SETTEURS	*/
	/************************************/
	public void setCode(int code){this.code=code;}
	//public void setNom(String nom){this.nom=nom;}	
	
	/************************************/
	/*		D�claration des m�thodes	*/
	/************************************/
	/*** M�thode toString() retourne une cha�ne de caract�re  ***/
	//public String toString(){return ("Code: "+code+" Nom: "+nom);}	
	public String toString(){return ("Code: "+code);}	
	
	
	/**
	Ecrit le code dans un PrintWriter
	@param out Le PrintWriter
	 */
	public void writeData(PrintWriter out) throws IOException{
		out.print(code + "|");
	}


	/**
	* Lit une ligne du fichier qui correspond � une ligne Entite
	* @param s du type String
	*/
	public void readData(String s) throws IOException{
		
		StringTokenizer t = new StringTokenizer(s, "|");
		setCode(Integer.parseInt(t.nextToken()));
	}
	
}
