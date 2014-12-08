package com.afpa59.patrice.service.jdbc;

import com.afpa59.patrice.donnees.Article;
import com.afpa59.patrice.service.jdbc.ServiceBase;
import com.afpa59.patrice.service.jdbc.ServiceArticle;
import com.afpa59.patrice.utils.ConnectionJDBC;
import com.afpa59.patrice.utils.ES;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceArticle extends ServiceBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************************/
	/* D�claration des variables d'instance */
	/****************************************/
	private ArrayList<Article> tabArticle = new ArrayList<Article>();


	/************************************/
	/*		D�claration des SETTERS	*/
	/************************************/
	/**
	 * @param tabArticle
	 */
	public void setTabArticle(ArrayList<Article> tabArticle) {
		this.tabArticle = tabArticle;
	}


	/************************************/
	/*		D�claration des GETTERS	*/
	/************************************/
	/**
	 * @return tabArticle la liste d'article
	 */
	public ArrayList<Article> getTabArticle() {return tabArticle;}


	/************************************/
	/*	D�claration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public ServiceArticle(){
		// Cr�ation d'un tableau d'article de base
		//		Article a1 = new Article(1,"Disque dur 1To",99);
		//		Article a2 = new Article(2,"Cl� USB 8Go",25);
		//		Article a3 = new Article(3,"Carte graphique",600);
		//
		//		tabArticle.add(a1);
		//		tabArticle.add(a2);
		//		tabArticle.add(a3);	
	}

	/************************************/
	/*		D�claration des m�thodes	*/
	/************************************/
	/*** M�thode creer() cr�e 3 articles  ***/
	public void creer(){
		// Cr�ation d'un tableau d'article de base
		//		tabArticle.add(new Article(1,"Disque dur 1To",99));
		//		tabArticle.add(new Article(2,"Cl� USG 8Go",25));
		//		tabArticle.add(new Article(3,"Carte graphique",600));
		//		ES.affiche("\n********** CREATION CATALOGUE DE BASE ARTICLE **********\n\n"
		//				+ "R�ussie...\n");
	}

	/*** M�thode creer qui a en param�tre un code, un nom, un prix
	 *  et qui cr�e l'article ***/	
	/**
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

			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			String monSelect = "SELECT * FROM Article";

			result = state.executeQuery(monSelect);	

			String monInsert="INSERT INTO Article (designation,prix) VALUES ( "
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


	/*** M�thode visualiser qui a en param�tre un code
	 *  et affiche l'article correspondant ***/	
	public void visualiser(int code){

		if(retourner(code) != null){	
			retourner(code).toString();
		}
	}

	/*** M�thode modifier qui a en param�tre un code
	 *  et qui modifie l'article correspondant ***/	
	public void modifier(int code){
		// Utile pour l'�cran de saisie
	}

	/*** M�thode modifier qui a en param�tre un code, un nom, un prix
	 *  et qui modifie l'article correspondant ***/	
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
							+ ", prix="
							+ prix
							+ " WHERE"
							+ " code="
							+ code;

					System.out.println(monUpdate);

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

	/*** M�thode supprimer qui a en param�tre un code
	 * et qui supprime l'article correspondant ***/
	public void supprimer(int code){
		for(int i = 0; i < tabArticle.size(); i++){	
			if(tabArticle.get(i).getCode() == code){
				// Modfification de l'article
				String st = tabArticle.get(i).getDesignation();
				tabArticle.remove(i);
				ES.affiche("\n ...SUPPRESSION de l'article "+ st +" R�ussie...\n");
				break;
			}
		}
	}

	/*** M�thode retourner qui a en param�tre un code 
	 * et qui retourne l'article correspondant ***/
	public Article retourner(int code){
		for(Article art : tabArticle){ // For ... each utile pour consultation pas pour modification
			if(art !=  null && art.getCode() == code){	
				return art;
			}
		}
		return null;
	}	

	/*** M�thode toString() retourne une cha�ne de caract�re  ***/
	public String toString(){
		String st = new String();
		for(Article art : tabArticle){
			st = st + art.toString() + "\n";
		}
		return st;
	}



	/**
	 * Lit un tableau d'articles � partir d�un lecteur buff�ris�
	 * 
	 * @param in Le lecteur buff�ris�
	 * @return Le tableau d�articles
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

			//L'objet ResultSet contient le r�sultat de la requ�te SQL
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

		/*  **************************************************************  */	

		return tabArticle;
	}


	/**
	 * Ecrit tous les articles dans un tableau vers un printWriter
	 * @param tabArticle Un tableau d�articles
	 * @param out Un printWriter
	 */
	public void writeData(){
		//
		//		for (int i = 0; i < tabArticle.size(); i++){
		//
		//
		//		}
	}



	/**
	 * Ecrit tous les articles dans un tableau vers un printWriter
	 * @param tabArticle Un tableau d�articles
	 * @param out Un printWriter
	 */
	//	public void writeData(ArrayList<Entite> tabArticle, PrintWriter out) throws IOException{}

	/**
	 * Lit un tableau d'articles � partir d�un lecteur buff�ris�
	 * @param in Le lecteur buff�ris�
	 * @return Le tableau d�articles
	 */
	//	public ArrayList<Entite> readData(BufferedReader in) throws IOException{ }

}
