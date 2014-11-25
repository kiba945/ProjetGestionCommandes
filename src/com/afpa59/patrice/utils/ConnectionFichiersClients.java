package com.afpa59.patrice.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.afpa59.patrice.service.fichier.ServiceClient;


public class ConnectionFichiersClients{

	private ServiceClient serviceC;

	/**
	 * 	Méthode pour lire un Fichier
	 * 
	 * @param nomFichier de type String
	 */
	public ConnectionFichiersClients(String nomFichier){

		try
		{
			// récupérer tous les enregistrements dans un nouveau tableau
			BufferedReader in = new BufferedReader(new FileReader(nomFichier));
			
			serviceC= new ServiceClient();			
			
			serviceC.readData(in);
					
			in.close();

		}

		catch(IOException exception)

		{
			exception.printStackTrace();
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
						
			serviceC.writeData(out);
			
//			out.flush();
			out.close();
		}

		catch(IOException exception)

		{
			exception.printStackTrace();
		}	
	}

	/**
	 * @return
	 */
	public ServiceClient getTab(){return serviceC;}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){return "tab"+(serviceC.toString());}

}
