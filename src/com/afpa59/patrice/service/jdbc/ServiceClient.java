package com.afpa59.patrice.service.jdbc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.afpa59.patrice.donnees.Client;
import com.afpa59.patrice.utils.ConnectionJDBC;

public class ServiceClient extends ServiceBase implements Serializable{

	/****************************************/
	/* D�claration des variables d'instance */
	/****************************************/
	private static final long serialVersionUID = 1L;
	private ArrayList<Client> tabClient = new ArrayList<Client>();


	/************************************/
	/*		D�claration des GETTERS	*/
	/************************************/
	/**
	 * @return tabClient la liste des clients
	 */
	public ArrayList<Client> getTabClient() {
		return tabClient;
	}

	/************************************/
	/*		D�claration des SETTERS	*/
	/************************************/
	/**
	 * @param tabClient
	 */
	public void setTabClient(ArrayList<Client> tabClient) {
		this.tabClient = tabClient;
	}

	/************************************/
	/*	D�claration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public ServiceClient(){

	}

	/************************************/
	/*		D�claration des m�thodes	*/
	/************************************/
	/**
	 * M�thode creer()
	 * 
	 */
	@Override
	public void creer(){

	}


	/**
	 * M�thode creer qui a en param�tre un code, un nom, un prenom, une adresse
	 * et qui cr�e le client
	 * 
	 * @param code
	 * @param nom
	 * @param prenom
	 * @param adresse
	 */
	public void creer(String nom, String prenom, String adresse){

		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		try {

			Connection connec = JDBC.Connecter();
			state = connec.createStatement();

			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			String monSelect = "SELECT * FROM Client";

			result = state.executeQuery(monSelect);	

			String monInsert="INSERT INTO "
					+ "Client"
					+ " ("
					+ "nom"
					+ ","
					+ "prenom"
					+ ","
					+ "adresse"
					+ ")"
					+ " VALUES "
					+ "("
					+ "'"
					+ nom
					+ "'"
					+ ", "
					+ "'"
					+ prenom
					+ "'"
					+ ", "
					+ "'"
					+ adresse
					+ "'"
					+ ")";			

			System.out.println(monInsert);

			result = state.executeQuery(monInsert);

			readData();

			result.close();			
			connec.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	/**
	 * M�thode visualiser qui a en param�tre un code 
	 * et affiche le client correspondant
	 * 
	 * @param code
	 */
	@Override
	public void visualiser(int code){
		if(retourner(code) != null){  
			System.out.println(retourner(code).toString());
		}		
	}

	/** 
	 * M�thode modifier qui a en param�tre un code
	 *  et qui modifie le client correspondant		
	 * 
	 * @param code
	 */
	@Override
	public void modifier(int code){
		// Utile pour l'�cran de saisie
	}


	/**
	 * M�thode modifier qui a en param�tre un code, un nom, un prenom, une adresse
	 * et qui modifie le client correspondant
	 * 
	 * @param code
	 * @param nom
	 * @param prenom
	 * @param adresse
	 */
	public void modifier(int code, String nom, String prenom, String adresse){

		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		for(int i = 0; i < tabClient.size(); i++){	

			if(tabClient.get(i).getCode() == code){

				try {

					Connection connec = JDBC.Connecter();
					state = connec.createStatement();

					String monUpdate="UPDATE Client"
							+ " SET"
							+ " nom="
							+ "'"
							+ nom
							+ "'"
							+ ","
							+ " prenom="
							+ "'"
							+ prenom
							+ "'"
							+ ","
							+ " adresse="
							+ "'"
							+ adresse
							+ "'"
							+ " WHERE"
							+ " code="
							+ code;

					result=state.executeQuery(monUpdate);

					System.out.println(monUpdate);

					// Modification d'un article
					readData();

					result.close();			
					connec.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				

			}
		}		
	}

	/**
	 * M�thode supprimer qui a en param�tre un code
	 * et qui supprime le client correspondant
	 * 
	 * @param code
	 */
	@Override
	public void supprimer(int code){	

		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		for(int i = 0; i < tabClient.size(); i++){	

			if(tabClient.get(i).getCode() == code){

				try {

					Connection connec = JDBC.Connecter();
					state = connec.createStatement();

					String monDelete="DELETE"
							+ " FROM "
							+ "Client"
							+ " WHERE"
							+ " code="
							+ code;

					result=state.executeQuery(monDelete);

					// Suppression de l'article dans la liste
					tabClient.remove(i);

					result.close();			
					connec.close();
					break;

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}

		}		

	}

	/**
	 * M�thode retourner qui a en param�tre un code 
	 * et qui retourne le client correspondant
	 * 
	 * @param code
	 * @return Client
	 */
	@Override
	public Client retourner(int code){
		for(Client clt : tabClient){ // For ... each utile pour consultation pas pour modification
			if(clt !=  null && clt.getCode() == code){	
				return clt;
			}
		}
		return null;
	}	

	/**
	 * M�thode toString() retourne une cha�ne de caract�re
	 * 
	 * @param code
	 * @return String
	 */
	@Override
	public String toString(){
		String st = new String();
		for(Client clt : tabClient){
			st =st + clt.toString() + "\n";
		}
		return st;
	}

	/**
	 * Lit la table Client de la base de donn�e et cr�er la liste des clients
	 * 
	 * @return la liste des clients
	 */
	public ArrayList<Client> readData() throws IOException{

		int code;
		String nom;
		String prenom;
		String adresse;

		/***************************************************/
		tabClient = new ArrayList<Client>();
		/***************************************************/

		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		try {

			Connection connec = JDBC.Connecter();

			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			String monSelect = "SELECT"
					+ " * "
					+ "FROM"
					+ " Client";

			state = connec.createStatement();

			result = state.executeQuery(monSelect);					

			while(result.next()){


				/****Attention Ordre des Champs de la table dans la BD***/
				code = result.getInt(1);
				adresse = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				/*******************************************************/

				Client clt = new Client(code,nom,prenom,adresse);

				tabClient.add(clt);

			}

			result.close();			
			connec.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return tabClient;
	}


	/**
	 * Ecrit tous les clients dans un tableau vers un printWriter
	 * 
	 * @param tabArticle Un tableau de clients
	 * @param out Un printWriter
	 */
	//	public void writeData(PrintWriter out) throws IOException {
	//
	//		for (int i = 0; i < tabClient.size(); i++){
	//			tabClient.get(i).writeData(out);
	//		}
	//	}

}
