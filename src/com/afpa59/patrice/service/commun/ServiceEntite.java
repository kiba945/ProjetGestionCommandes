package com.afpa59.patrice.service.commun;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.afpa59.patrice.donnees.Entite;

public interface ServiceEntite {
	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	// signature des méthodes seulement
	
	/**
	 * 	Méthode creer
	 */
	public void creer();
	
	
	/**
	 * 	Méthode Visualiser
	 * @param code du type Integer
	 */
	public void visualiser(int code);
	
	
	/**
	 * 	Méthode modifier
	 * @param code du type integer
	 */
	public void modifier(int code);
	
	
	/**
	 * @param code
	 */
	public void supprimer(int code);
	
	
	/**
	 * @param code
	 * @return
	 */
	public Entite retourner(int code);
	
	
	/**
	 * @return
	 */
	public String toString(); 
	
	
	/**
	 * @param tabEntite
	 * @param out
	 * @throws IOException
	 */
	public void writeData(ArrayList<Entite> tabEntite, PrintWriter out) 
			throws IOException;

	/**
	 * @param in
	 * @return
	 * @throws IOException
	 */
//	public ArrayList<Entite> readData(BufferedReader in) 
//			throws IOException;

}
