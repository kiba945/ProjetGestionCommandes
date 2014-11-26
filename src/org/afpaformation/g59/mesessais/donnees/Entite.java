package org.afpaformation.g59.mesessais.donnees;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entite {
	
	/****************************************/
	/* D�claration des variables d'instance */
	/****************************************/
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
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
}
