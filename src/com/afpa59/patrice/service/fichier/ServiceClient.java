package com.afpa59.patrice.service.fichier;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import com.afpa59.patrice.donnees.Client;
import com.afpa59.patrice.utils.ES;

public class ServiceClient extends ServiceBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************************/
	/* D�claration des variables d'instance */
	/****************************************/
	private ArrayList<Client> tabClient = new ArrayList<Client>();
	
	/************************************/
	/*	D�claration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public ServiceClient(){

	}
	
	/************************************/
	/*		D�claration des m�thodes	*/
	/************************************/
	/*** M�thode creer() qui cr�e 3 Clients  ***/
	public void creer(){
		// Cr�ation d'une liste de client de base
//		tabClient.add(new Client(1,"SIMPSON","Homer","SPRINGFIELD"));
//		tabClient.add(new Client(2,"SIMPSON","Marge","SPRINGFIELD"));
//		tabClient.add(new Client(3,"SIMPSON","Bart","SPRINGFIELD"));
//		ES.affiche("\n********** CREATION DE LA BASE DE DONNEE CLIENT D'ORIGINE **********\n\n"
//				 + "R�ussie...\n");
	}

	/*** M�thode creer qui a en param�tre un code, un nom, un prenom, une adresse
	 *  et qui cr�e le client ***/	
	public void creer(int code, String nom, String prenom, String adresse){
		// Cr�ation d'un client
		tabClient.add(new Client(code, nom, prenom, adresse));
	}

	/*** M�thode visualiser qui a en param�tre un code 
	 * et affiche le client correspondant ***/	
	public void visualiser(int code){
		if(retourner(code) != null){  
			System.out.println(retourner(code).toString());
		}		
	}
	
	/*** M�thode modifier qui a en param�tre un code
	 *  et qui modifie le client correspondant ***/		
	public void modifier(int code){
		
	}
	
	/*** M�thode modifier qui a en param�tre un code, un nom, un prenom, une adresse
	 *  et qui modifie le client correspondant ***/		
	public void modifier(int code, String nom, String prenom, String adresse){
		for(int i = 0; i < tabClient.size(); i++){	
			  if(tabClient.get(i).getCode() == code){
				  // Modfification du client
				  tabClient.set(i, new Client(code,nom,prenom,adresse));
				  break;
			  }	
		}
	}

	/*** M�thode supprimer qui a en param�tre un code
	 * et qui supprime le client correspondant ***/
	public void supprimer(int code){	
		for(int i = 0; i < tabClient.size(); i++){	
			if( tabClient.get(i).getCode() == code){
				// Suppression du client
				String st = tabClient.get(i).getNom();
				st = st +" "+ tabClient.get(i).getPrenom();
				tabClient.remove(i);
				ES.affiche("\n ...SUPPRESSION du client "+ st +" R�ussie...\n");
				break;
			 }
		}
	}

	/*** M�thode retourner qui a en param�tre un code 
	 * et qui retourne l'article correspondant ***/
	public Client retourner(int code){
		for(Client clt : tabClient){ // For ... each utile pour consultation pas pour modification
			if(clt !=  null && clt.getCode() == code){	
				return clt;
			}
		}
		return null;
	}	

	/*** M�thode toString() retourne une cha�ne de caract�re  ***/	
	public String toString(){
		String st = new String();
		for(Client clt : tabClient){
				st =st + clt.toString() + "\n";
		}
		return st;
	}

	/**
	* Lit un tableau de clients � partir d�un lecteur buff�ris�
	* 
	* @param in Le lecteur buff�ris�
	* @return un tableau de clients
	*/
	public ArrayList<Client> readData(BufferedReader in) throws IOException{
		
		String s;
		while((s=in.readLine())!=null){
			Client clt = new Client();
			clt.readData(s);
			tabClient.add(clt);
		}
		
		return tabClient;
	}

	
	/**
	* Ecrit tous les clients dans un tableau vers un printWriter
	* @param tabArticle Un tableau de clients
	* @param out Un printWriter
	 */
	public void writeData(PrintWriter out) throws IOException {

		for (int i = 0; i < tabClient.size(); i++){
			tabClient.get(i).writeData(out);
		}
	}
	
}
