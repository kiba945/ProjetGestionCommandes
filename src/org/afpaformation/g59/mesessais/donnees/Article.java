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
	/* D�claration des variables d'instance */
	/****************************************/
	@Column( name = "designation")
	private String designation;
	
	@Column( name = "prix")
	private float prix;

	/************************************/
	/*	D�claration des constructeurs	*/
	/************************************/
	/** 1er constructeur
	 * 
	 */
	public Article(){}
	
	/** 2�me constructeur avec des param�tres
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
	/*		D�claration des GETTEURS	*/
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
	/*		D�claration des SETTEURS	*/
	/************************************/
	/**
	 * @param designation
	 */
	public void setDesignation(String designation){this.designation=designation;}	
	public void setPrix(float prix){this.prix=prix;}	

	/************************************/
	/*		D�claration des m�thodes	*/
	/************************************/
	/*** M�thode toString() retourne une cha�ne de caract�re  ***/
	/* (non-Javadoc)
	 * @see org.afpaformation.g59.mesessais.donnees.Entite#toString()
	 */
	public String toString(){return (super.toString()+" Designation: "+designation+
			" Prix: "+prix);}

	/**
	Ecrit les donn�es dans un PrintWriter
	@param out Le PrintWriter
	 */
	public void writeData(PrintWriter out) throws IOException{
		out.println(super.getCode() + "|"
				+ designation + "|"
				+ prix);
	}


	/**
	Lit les donn�es depuis un lecteur buff�ris�
	@param in Le lecteur buff�ris�
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
