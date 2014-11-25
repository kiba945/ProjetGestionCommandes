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
	/* Déclaration des variables d'instance */
	/****************************************/
	private ArrayList<Client> tabClient = new ArrayList<Client>();
	
	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public ServiceClient(){

	}
	
	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	/*** Méthode creer() qui crée 3 Clients  ***/
	public void creer(){
		// Création d'une liste de client de base
//		tabClient.add(new Client(1,"SIMPSON","Homer","SPRINGFIELD"));
//		tabClient.add(new Client(2,"SIMPSON","Marge","SPRINGFIELD"));
//		tabClient.add(new Client(3,"SIMPSON","Bart","SPRINGFIELD"));
//		ES.affiche("\n********** CREATION DE LA BASE DE DONNEE CLIENT D'ORIGINE **********\n\n"
//				 + "Réussie...\n");
	}

	/*** Méthode creer qui a en paramétre un code, un nom, un prenom, une adresse
	 *  et qui crée le client ***/	
	public void creer(int code, String nom, String prenom, String adresse){
		// Création d'un client
		tabClient.add(new Client(code, nom, prenom, adresse));
	}

	/*** Méthode visualiser qui a en paramétre un code 
	 * et affiche le client correspondant ***/	
	public void visualiser(int code){
		if(retourner(code) != null){  
			System.out.println(retourner(code).toString());
		}		
	}
	
	/*** Méthode modifier qui a en paramétre un code
	 *  et qui modifie le client correspondant ***/		
	public void modifier(int code){
		
	}
	
	/*** Méthode modifier qui a en paramétre un code, un nom, un prenom, une adresse
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

	/*** Méthode supprimer qui a en paramétre un code
	 * et qui supprime le client correspondant ***/
	public void supprimer(int code){	
		for(int i = 0; i < tabClient.size(); i++){	
			if( tabClient.get(i).getCode() == code){
				// Suppression du client
				String st = tabClient.get(i).getNom();
				st = st +" "+ tabClient.get(i).getPrenom();
				tabClient.remove(i);
				ES.affiche("\n ...SUPPRESSION du client "+ st +" Réussie...\n");
				break;
			 }
		}
	}

	/*** Méthode retourner qui a en paramétre un code 
	 * et qui retourne l'article correspondant ***/
	public Client retourner(int code){
		for(Client clt : tabClient){ // For ... each utile pour consultation pas pour modification
			if(clt !=  null && clt.getCode() == code){	
				return clt;
			}
		}
		return null;
	}	

	/*** Méthode toString() retourne une chaîne de caractère  ***/	
	public String toString(){
		String st = new String();
		for(Client clt : tabClient){
				st =st + clt.toString() + "\n";
		}
		return st;
	}

	/**
	* Lit un tableau de clients à partir d’un lecteur bufférisé
	* 
	* @param in Le lecteur bufférisé
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
