package org.afpaformation.g59.mesessais.donnees;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entite {
	
	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
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
}
