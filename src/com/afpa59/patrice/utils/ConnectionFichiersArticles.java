package com.afpa59.patrice.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.afpa59.patrice.service.fichier.ServiceArticle;


public class ConnectionFichiersArticles{

	private ServiceArticle serviceA;

	/**
	 * 	Méthode pour lire un Fichier
	 * 
	 * @param nomFichier de type String
	 */
	public ConnectionFichiersArticles(String nomFichier){

		try
		{
			// récupérer tous les enregistrements dans un nouveau tableau
			BufferedReader in = new BufferedReader(new FileReader(nomFichier));
			
			serviceA= new ServiceArticle();			
			
			serviceA.readData(in);
			
//			ArrayList<Article> newTabArticle = serviceA.readData(in);

//			// afficher les enregistrements Articles nouvellement lus
//			for (int i = 0; i < newTabArticle.size(); i++){
//				
//				//System.out.println(newTabArticle.get(i));
//									
//			}
			
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
						
			serviceA.writeData(out);
			
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
	public ServiceArticle getTab(){return serviceA;}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){return "tab"+(serviceA.toString());}

}
