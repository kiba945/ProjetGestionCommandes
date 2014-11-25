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
	/* Déclaration des variables d'instance */
	/****************************************/
	private int code;
	//private String nom;
	
	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public Entite(){}
	/*** 2ème constructeur avec des paramètres ***/
	//public Entite(int code, String nom){
	public Entite(int code){
		this.code=code;
		//this.nom=nom;
	}
	
	/************************************/
	/*		Déclaration des GETTEURS	*/
	/************************************/
	public int getCode(){return code;}
	//public String getNom(){return nom;}
	
	/************************************/
	/*		Déclaration des SETTEURS	*/
	/************************************/
	public void setCode(int code){this.code=code;}
	//public void setNom(String nom){this.nom=nom;}	
	
	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	/*** Méthode toString() retourne une chaîne de caractère  ***/
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
	* Lit une ligne du fichier qui correspond à une ligne Entite
	* @param s du type String
	*/
	public void readData(String s) throws IOException{
		
		StringTokenizer t = new StringTokenizer(s, "|");
		setCode(Integer.parseInt(t.nextToken()));
	}
	
}
