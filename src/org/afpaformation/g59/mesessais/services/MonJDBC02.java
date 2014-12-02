package org.afpaformation.g59.mesessais.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.afpaformation.g59.mesessais.utils.ES;


public class MonJDBC02{

	private Connection conn;


	public void test(){
		// Déclaration des variables
		int code;
		String designation;
		float prix;
		String st;

		try {

			Statement state = SuperMethode();

			ResultSet result;

			code = ES.saisie("\n********** CREATION D'UN ARTICLE **********\n\n"
					+ "\nCode Article: ", 1, Integer.MAX_VALUE);
			designation = ES.saisie("Désignation: ");
			prix = ES.saisie("Prix: ", 0F, Float.MAX_VALUE);


			String monInsert="INSERT INTO Article (code,designation,prix) VALUES ("
					+code+","
					+ "'"
					+ designation
					+ "'"
					+ ","
					+ prix
					+ ")";


			creerArticleJDBC(state, monInsert);
			
			state.close();
			conn.close();


		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}      
	}


	/**
	 * @param state
	 * @param monInsert
	 * @throws SQLException
	 */
	private void creerArticleJDBC(Statement state, String monInsert)
			throws SQLException {
		String st;
		ResultSet result;
		result=state.executeQuery(monInsert);
		ES.affiche("\n ...CREATION Réussie...\n");

		//L'objet ResultSet contient le résultat de la requête SQL
		String monSelect = "SELECT * FROM Article";
		result = state.executeQuery(monSelect);					

		//On récupère les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();


		st="\n**********************************\n";
		//On affiche le nom des colonnes
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			st = st+"\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *";

		st= st + "\n**********************************\n";

		while(result.next()){         
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				st=st+"\t" + result.getObject(i).toString() + "\t |";

			st= st + "\n---------------------------------\n";

		}

		ES.affiche(st);

		result.close();
	}


	private Statement SuperMethode() throws ClassNotFoundException,
			SQLException {
		String st;
		Class.forName("org.hsqldb.jdbcDriver");
		st = "Driver O.K. \n";

		String url = "jdbc:hsqldb:hsql://localhost/xdb";
		String user = "SA";
		String passwd = "";

		conn = DriverManager.getConnection(url, user, passwd);
		st= st+"Connexion effective !";   

		//			ES.affiche(st);


		//Création d'un objet Statement
		Statement state = conn.createStatement();
		return state;
	}


}

