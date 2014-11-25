package com.afpa59.patrice.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionJDBC {

	private Connection conn;

	/**
	 * M�thode Connecter qui �tablit 
	 * 
	 * @return Statement
	 */
	public Statement Connecter() {

		String st;

		// TODO Auto-generated constructor stub
		try {

			Class.forName("org.hsqldb.jdbcDriver");
			st = "Driver O.K. \n";

			String url = "jdbc:hsqldb:hsql://localhost/xdb";
			String user = "SA";
			String passwd = "";

			conn = DriverManager.getConnection(url, user, passwd);
			st= st+"Connexion effective !";  
			
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			
			return state;

		} catch (ClassNotFoundException e) {
			ES.affiche("Exception lev�ee dans Connecter...\n");
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}

	
//	public Statement SuperMethode() throws ClassNotFoundException,SQLException {
//		String st;
//		
//		Class.forName("org.hsqldb.jdbcDriver");
//		st = "Driver O.K. \n";
//
//		String url = "jdbc:hsqldb:hsql://localhost/xdb";
//		String user = "SA";
//		String passwd = "";
//
//		conn = DriverManager.getConnection(url, user, passwd);
//		st= st+"Connexion effective !";   
//
//		//			ES.affiche(st);
//
//
//		//Cr�ation d'un objet Statement
//		Statement state = conn.createStatement();
//		return state;
//	}


	public void fermerConnectionJDBC(){

		try{

			conn.close();

		}catch (Exception e){

			ES.affiche("Exception lev�ee dans fermerConnectionJDBC...\n");

		}

	}

}
