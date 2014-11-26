package org.afpaformation.g59.mesessais.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.afpaformation.g59.mesessais.utils.ES;


public class MonJDBC01{


	public void test(){
		
		String st;
		
		try {
			
			Class.forName("org.hsqldb.jdbcDriver");
			st = "Driver O.K. \n";

			String url = "jdbc:hsqldb:hsql://localhost/xdb";
			String user = "SA";
			String passwd = "";

			Connection conn = DriverManager.getConnection(url, user, passwd);
			st= st+"Connexion effective !";   

//			ES.affiche(st);

			//Création d'un objet Statement
			Statement state = conn.createStatement();
			
			
			ResultSet result;
						
			
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
			state.close();
			conn.close();


		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}      
	}


}

