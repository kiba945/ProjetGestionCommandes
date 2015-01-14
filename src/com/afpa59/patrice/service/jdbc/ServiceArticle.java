package com.afpa59.patrice.service.jdbc;

import com.afpa59.patrice.donnees.Article;
import com.afpa59.patrice.service.jdbc.ServiceBase;
import com.afpa59.patrice.service.jdbc.ServiceArticle;
import com.afpa59.patrice.utils.ConnectionJDBC;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceArticle extends ServiceBase implements Serializable {

	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	private static final long serialVersionUID = 1L;
	private ArrayList<Article> tabArticle = new ArrayList<Article>();


	/************************************/
	/*		Déclaration des GETTERS	*/
	/************************************/
	/**
	 * @return tabArticle la liste des articles
	 */
	public ArrayList<Article> getTabArticle() {
		return tabArticle;
	}
	

	/************************************/
	/*		Déclaration des SETTERS	*/
	/************************************/
	/**
	 * @param tabArticle
	 */
	public void setTabArticle(ArrayList<Article> tabArticle) {
		this.tabArticle = tabArticle;
	}

	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public ServiceArticle(){	}

	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	/**
	 * Méthode creer()
	 * 
	 */
	@Override
	public void creer(){
		
	}

	
	/**
	 * Méthode creer qui a en paramétre un nom, un prix
	 * et qui crée l'article
	 * 
	 * @param nom
	 * @param prix
	 */
	public void creer(String nom, float prix){

		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		try {

			Connection connec = JDBC.Connecter();
			state = connec.createStatement();

			//L'objet ResultSet contient le résultat de la requête SQL
			String monSelect = "SELECT * FROM Article";

			result = state.executeQuery(monSelect);	

			String monInsert="INSERT INTO "
					+ "Article"
					+ " ("
					+ "designation"
					+ ","
					+ "prix"
					+ ")"
					+ " VALUES "
					+ "("
					+ "'"
					+ nom
					+ "'"
					+ ", "
					+ prix
					+ ")";			

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
	 * Méthode visualiser qui a en paramétre un code
	 * et affiche l'article correspondant
	 * 
	 * @param code
	 */
	@Override
	public void visualiser(int code){

		if(retourner(code) != null){	
			retourner(code).toString();
		}
	}


	/**
	 * Méthode modifier qui a en paramétre un code
	 * et qui modifie l'article correspondant
	 * 
	 * @param code
	 */
	@Override
	public void modifier(int code){
		// Utile pour l'écran de saisie
	}


	/**
	 * Méthode modifier qui a en paramétre un code, un nom, un prix
	 * et qui modifie l'article correspondant
	 * 
	 * @param code
	 * @param nom
	 * @param prix
	 */
	public void modifier(int code, String nom, float prix){

		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		for(int i = 0; i < tabArticle.size(); i++){	

			if(tabArticle.get(i).getCode() == code){

				try {

					Connection connec = JDBC.Connecter();
					state = connec.createStatement();

					String monUpdate="UPDATE Article"
							+ " SET"
							+ " designation="
							+ "'"
							+ nom
							+ "'"
							+ ","
							+ "prix="
							+ prix
							+ " WHERE"
							+ " code="
							+ code;

					result=state.executeQuery(monUpdate);

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
	 * Méthode supprimer qui a en paramétre un code
	 * et qui supprime l'article correspondant
	 * 
	 * @param code
	 */
	@Override
	public void supprimer(int code){
		
		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		for(int i = 0; i < tabArticle.size(); i++){	

			if(tabArticle.get(i).getCode() == code){

				try {

					Connection connec = JDBC.Connecter();
					state = connec.createStatement();

					String monDelete="DELETE FROM "
							+ "Article"
							+ " WHERE"
							+ " code="
							+ code;

					result=state.executeQuery(monDelete);

					// Suppression de l'article dans la liste
					tabArticle.remove(i);

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
	 * Méthode retourner qui a en paramétre un code 
	 * et qui retourne l'article correspondant
	 * 
	 * @param code
	 * @return Article
	 */
	@Override
	public Article retourner(int code){
		for(Article art : tabArticle){ // For ... each utile pour consultation pas pour modification
			if(art !=  null && art.getCode() == code){	
				return art;
			}
		}
		return null;
	}	
	
	/**
	 * Méthode toString() retourne une chaîne de caractère
	 * 
	 * @param code
	 * @return String
	 */
	@Override
	public String toString(){
		String st = new String();
		for(Article art : tabArticle){
			st = st + art.toString() + "\n";
		}
		return st;
	}



	/**
	 * Lit la table Article de la base de donnée et créer une liste d'articles
	 * 
	 * @return une liste d'articles
	 */
	public ArrayList<Article> readData() throws IOException{

		int code;
		String designation;
		Float prix;
		
		/***************************************************/
		tabArticle = new ArrayList<Article>();
		/***************************************************/
		
		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		try {

			Connection connec = JDBC.Connecter();

			//L'objet ResultSet contient le résultat de la requête SQL
			String monSelect = "SELECT * FROM Article";

			state = connec.createStatement();

			result = state.executeQuery(monSelect);					

			while(result.next()){

				code = result.getInt(1);
				designation = result.getString(2);
				prix = result.getFloat(3);

				Article article = new Article(code,designation,prix);

				tabArticle.add(article);

			}

			result.close();			
			connec.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return tabArticle;
	}


	/**
	 * Ecrit tous les articles dans un tableau vers un printWriter
	 * 
	 * @param tabArticle Un tableau d’articles
	 * @param out Un printWriter
	 */
	@Override
	public void writeData(){
		//
		//		for (int i = 0; i < tabArticle.size(); i++){
		//
		//
		//		}
	}


	/**
	 * Ecrit tous les articles dans un tableau vers un printWriter
	 * @param tabArticle Un tableau d’articles
	 * @param out Un printWriter
	 */
	//	public void writeData(ArrayList<Entite> tabArticle, PrintWriter out) throws IOException{}

	/**
	 * Lit un tableau d'articles à partir d’un lecteur bufférisé
	 * @param in Le lecteur bufférisé
	 * @return Le tableau d’articles
	 */
	//	public ArrayList<Entite> readData(BufferedReader in) throws IOException{ }

}
