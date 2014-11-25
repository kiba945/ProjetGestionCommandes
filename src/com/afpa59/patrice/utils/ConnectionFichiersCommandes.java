package com.afpa59.patrice.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.afpa59.patrice.service.fichier.ServiceCommande;


public class ConnectionFichiersCommandes{

	private ServiceCommande ServiceCde;

	/**
	 * 	Méthode pour lire un Fichier
	 * 
	 * @param nomFichier de type String
	 */
	public ConnectionFichiersCommandes(String nomFichier){

		try
		{
			// récupérer tous les enregistrements dans un nouveau tableau
			BufferedReader in = new BufferedReader(new FileReader(nomFichier));

			ServiceCde= new ServiceCommande();			

			
			ServiceCde.readData(in);
			
			
			/* ************** DEBUGGING ************************** */
			System.out.println("Je suis dans de la classse ConnectionFichiersCommandes"
					+ " dans le constructeur ConnectionFichiersCommandes");	
			System.out.println(ServiceCde.getTabCdes().toString());
			/* *************************************************** */				

			in.close();

		} catch (FileNotFoundException e) {

			/* ************** DEBUGGING ************************** */
			System.out.println("Je suis dans le catch de l'exception FileNotFoundException"
					+ " de la classse ConnectionFichiersCommandes");
			
			/* *************************************************** */			
			
//			e.printStackTrace();	//Affiche le message de l'exception attrapé
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}	



	/**
	 * 	Méthode pour écrire dans un Fichier
	 * 
	 * @param nomFichier de type String
	 * @param tab
	 */
	public void ecrire(String nomFichier){

		try
		{
			// enregistrer tout le tableau dans le
			// fichier
			PrintWriter out = new PrintWriter(new FileWriter(nomFichier));

			ServiceCde.writeData(out);

			//			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			
			/* ************** DEBUGGING ************************** */
			System.out.println("Je suis dans le catch de l'exception FileNotFoundException"
					+ " de la classse ConnectionFichiersCommandes"
					+ " dans la méthode ecrire()");
			
			/* *************************************************** */	
			
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}

	/**
	 * @return
	 */
	public ServiceCommande getTab(){return ServiceCde;}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){return "tab"+(ServiceCde.toString());}

}
