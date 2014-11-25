package com.afpa59.patrice.donnees;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Client extends Entite{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	private String nom;
	private String prenom;
	private String adresse;
	
	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public Client(){}
	/*** 2ème constructeur avec des paramètres ***/
	public Client(int code, String nomClient, String prenomClient, String adresseClient){
		super(code);
		this.nom=nomClient;
		this.prenom=prenomClient;
		this.adresse=adresseClient;
	}
	
	/************************************/
	/*		Déclaration des GETTEURS	*/
	/************************************/
	public String getNom(){return nom;}
	public String getPrenom(){return prenom;}
	public String getAdresse(){return adresse;}
	
	/************************************/
	/*		Déclaration des SETTEURS	*/
	/************************************/
	public void setNom(String nomClient){this.nom=nomClient;}
	public void setPrenom(String prenomClient){this.prenom=prenomClient;}
	public void setAdresse(String adresseClient){this.adresse=adresseClient;}
	
	/************************************/
	/*		Décalaration des méthodes	*/
	/************************************/
	/*** Méthode toString() retourne une chaîne de caractère  ***/
	public String toString(){return (super.toString()+" Nom: "+nom+
			" Prénom: "+prenom+" Adresse: "+adresse);}	
	
	
	
	/**
	* Méthode qui écrit une ligne client dans un PrintWriter
	* 
	* @param out du type PrintWriter
	*/
	public void writeData(PrintWriter out) throws IOException{
		super.writeData(out);
		out.println(nom + "|" + prenom + "|" + adresse);		
	}


	
	/**
	* Méthode qui lit une ligne du fichier qui correspond à une ligne Client
	* 
	* @param ligClient du type String
	*/
	public void readData(String ligClient) throws IOException{
		
		StringTokenizer t = new StringTokenizer(ligClient, "|");
		int code = Integer.parseInt(t.nextToken());
		
		super.setCode(code);
		nom = t.nextToken();
		prenom = t.nextToken();
		adresse = t.nextToken();
	}	
}
