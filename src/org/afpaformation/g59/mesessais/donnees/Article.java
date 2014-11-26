package org.afpaformation.g59.mesessais.donnees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Article extends Entite{

	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	@Column( name = "designation")
	private String designation;
	
	@Column( name = "prix")
	private float prix;

	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	/** 1er constructeur
	 * 
	 */
	public Article(){}
	
	/** 2ème constructeur avec des paramètres
	 * 
	 * @param Integer code
	 * @param String designation
	 * @param Float prix
	 */
	public Article(int code, String designation, float prix){
		//super(code, designation);
		super(code);
		this.designation=designation;
		this.prix=prix;
	}

	/************************************/
	/*		Déclaration des GETTEURS	*/
	/************************************/
	/**
	 * @return String designation
	 */
	public String getDesignation(){return designation;}
	/**
	 * @return Float prix
	 */
	public float getPrix(){return prix;}

	/************************************/
	/*		Déclaration des SETTEURS	*/
	/************************************/
	/**
	 * @param designation
	 */
	public void setDesignation(String designation){this.designation=designation;}	
	public void setPrix(float prix){this.prix=prix;}	

	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	/*** Méthode toString() retourne une chaîne de caractère  ***/
	/* (non-Javadoc)
	 * @see org.afpaformation.g59.mesessais.donnees.Entite#toString()
	 */
	public String toString(){return (super.toString()+" Designation: "+designation+
			" Prix: "+prix);}

	/**
	Ecrit les données dans un PrintWriter
	@param out Le PrintWriter
	 */
	public void writeData(PrintWriter out) throws IOException{
		out.println(super.getCode() + "|"
				+ designation + "|"
				+ prix);
	}


	/**
	Lit les données depuis un lecteur bufférisé
	@param in Le lecteur bufférisé
	 */
	public void readData(BufferedReader in) throws IOException{
		String s = in.readLine();
		StringTokenizer t = new StringTokenizer(s, "|");
		setCode(Integer.parseInt(t.nextToken()));
		designation = t.nextToken();
		prix = Float.parseFloat(t.nextToken());
	}	

	//	@Override
	//	public int hashCode(){
	//		return getCode();
	//	}
	//
	//	@Override
	//	public boolean equals(Object o){
	//		if(getCode() == ((Article)o).getCode()){
	//			return true;
	//		}
	//		return false;
	//	}

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
