package com.afpa59.patrice.utils;

import java.io.*;

import com.afpa59.patrice.service.fichier.ServiceArticle;
import com.afpa59.patrice.service.fichier.ServiceBase;

public class ConnectionFichiersBase implements ConnectionFichiers{

	private ServiceBase tabServiceBase;

	/**
	 * Constructeur ConnectionFichiersArticles
	 * pour lire un fichier
	 * 
	 * @param nomFichier de type String
	 */
	public void ConnectionFichierBase(String nomFichier){

		try
		{
			// récupérer tous les enregistrements dans un nouveau tableau
			BufferedReader in = new BufferedReader(new FileReader(nomFichier));
			
			tabServiceBase= new ServiceArticle();			
			
//			tabServiceBase.readData(in);
			
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
			// enregistrer tout le tableau dans le fichier
			PrintWriter out = new PrintWriter(new FileWriter(nomFichier));
						
//			tabServiceBase.writeData(out);
			
			out.flush();
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
	public ServiceBase getTab(){return tabServiceBase;}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){return "tab"+(tabServiceBase.toString());}



	public void lire(String nomFichier) {
		// TODO Auto-generated method stub
		
	}

}
