package com.afpa59.patrice.service.jdbc;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import com.afpa59.patrice.donnees.Commande;
import com.afpa59.patrice.utils.ConnectionJDBC;
import com.afpa59.patrice.utils.ES;


public class ServiceCommande extends ServiceBase implements Serializable{	// !!!!!!!!!!!! SUPPRESSION du extends ServiceBase

	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	private static final long serialVersionUID = 1L;
	public static int numero=1;
	private static Hashtable<String,Commande> tabCdes = new Hashtable<String,Commande>();


	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	/**
	 *  1er constructeur
	 */
	public ServiceCommande(){}

	/**
	 * 2ème constructeur
	 * 
	 * @param Cde
	 */
	public ServiceCommande(Hashtable<String,Commande> Cde){tabCdes = Cde;}


	/************************************/
	/*		Déclaration des GETTERS  	*/
	/************************************/
	/**
	 * Méthode getTabCdes
	 * 
	 * @return tabCdes la table des commandes
	 */
	public Hashtable<String,Commande> getTabCdes(){return tabCdes;}


	/************************************/
	/*		Déclaration des SETTERS	    */
	/************************************/
	public void setTabCdes(Hashtable<String,Commande> tableDesCommandes){tabCdes = tableDesCommandes;}


	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	/**
	 * Méthode creer()
	 * 
	 */
	@Override
	public void creer(){ }	

	/**
	 * Méthode creer qui ajoute une nouvelle commande dans la table
	 * 
	 * @param Cde
	 */
	public void creer(Commande Cde){

		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		try {

			Connection connec = JDBC.Connecter();
			state = connec.createStatement();

			String monInsert="INSERT INTO "
					+ "Commande"
					+ " ("
					+ "code_cde"
					+ ","
					+ "date_cde"
					+ ","
					+ "montantcommande"
					+ ","
					+ "code_client"
					+ ")"
					+ " VALUES "
					+ "("
					+ Cde.getCodeCde()
					+ ", "
					+ "'"
					+ Cde.getDateCde()
					+ "'"
					+ ", "
					+ 99.9
					+ ", "
					+ 1					
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
	 * Méthode visualiser qui a en paramétre un code 
	 * et affiche le Commande correspondant
	 * 
	 * @param code
	 */
	public void visualiser(String code){
		if(retourner(code) != null){  
			System.out.println(retourner(code).toString());
		}		
	}


	/** 
	 * Méthode modifier qui a en paramétre un code
	 * et qui modifie le Commande correspondant		
	 * 
	 * @param code
	 */
	@Override
	public void modifier(int code){	}	


	/**
	 * Méthode supprimer qui a en paramétre une commande
	 * et la supprime dans la table
	 * 
	 * @param Cde
	 */
	public void supprimer(Commande Cde){tabCdes.remove(Cde.getCodeCde());}


	/**
	 * Méthode supprimer qui a en paramétre un code
	 * et qui supprime la commande correspondante
	 * 
	 * @param code
	 */
	@Override
	public void supprimer(int code) {
		// TODO Auto-generated method stub

	}	

	//Retourner la commande du numero passé en paramètres

	/**
	 * Méthode retourner qui a en paramétre un code 
	 * et qui retourne la commande correspondante
	 * 
	 * @param numCde
	 * @return
	 */
	public Commande retourner(String numCde){

		if(tabCdes.get(numCde) !=null ){
			return tabCdes.get(numCde);

		}else{
			return null;
		}
	}

	/**
	 * Méthode cle qui retourne la réference clé de la table
	 * 
	 * @return String
	 */
	public String cle(){
		Enumeration<String> em = tabCdes.keys();
		String s = "";
		while(em.hasMoreElements()){
			s = em.nextElement() + " *** " + s;
		}
		return s;
	}	


	/**
	 * Méthode taille qui retourne la taille de la table
	 * 
	 * @return int la taille du tableau
	 */
	public int taille(){return tabCdes.size();}	

	/**
	 * Méthode toString() retourne une chaîne de caractère
	 * 
	 * @param code
	 * @return String
	 */
	@Override
	public String toString(){
		Enumeration<Commande> em = tabCdes.elements();
		String st = new String();
		while(em.hasMoreElements()){
			Commande Cde = em.nextElement();
			st = "\nCommande numero : "+Cde.getCodeCde()+" Date Cde : "+ Cde.getDateCde()+
					Cde.toString()+st;
		}
		return st;
	}	


	/**
	 * Lit la table Commande de la BD et créer la liste des commandes
	 * @param cde2 
	 * 
	 * @return un tableau de commandes
	 */
	public Hashtable<String,Commande> readData() throws IOException{

		int code;
		String codeCde;
		String dateCde;	//Attention à traiter au format DateUser
		Float montantCde;
		int codeClt;	//A réviser
		String mes;

		/***************************************************/
		tabCdes = new Hashtable<String,Commande>();
		Commande cde = new Commande();
		/***************************************************/

		ResultSet result;
		Statement state;
		ConnectionJDBC JDBC = new ConnectionJDBC();

		try {

			Connection connec = JDBC.Connecter();

			//L'objet ResultSet contient le résultat de la requête SQL
			String monSelect = "SELECT"
					+ " * "
					+ " FROM "
					+ "Commande";

			state = connec.createStatement();

			result = state.executeQuery(monSelect);					

			while(result.next()){


				/****Attention Ordre des Champs de la table dans la BD***/
				code = result.getInt(1);
				codeCde = result.getString(2);
				dateCde = result.getString(3);
				montantCde = result.getFloat(4);
				codeClt = result.getInt(5);
				/*******************************************************/

				tabCdes.put(codeCde, cde);

			}

			result.close();			
			connec.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			mes = "Connection impossible. Veuillez contacter l'adminstrateur.";
			ES.affiche(mes);
			e1.printStackTrace();
		}

		return tabCdes;
	}


	/**
	 * Ecrit tous les articles dans un tableau vers un printWriter
	 * @param tabArticle Un tableau d’articles
	 * @param out Un printWriter
	 */
//	public void writeData(PrintWriter out) throws IOException {
//		// TODO Auto-generated method stub
//
//
//		/* ************** DEBUGGING ************************** */
//		System.out.println("Je suis dans de la classse ServiceCommande"
//				+ " dans la méthode writeData()");	
//		//		System.out.println("Contenu du Code:" + tabCdes.elements().nextElement().getCode());
//		//		System.out.println("Contenu du Code:" + tabCdes.elements().nextElement().getUneCommande());
//		//		System.out.println("Contenu du Code:" + tabCdes.elements().nextElement().getCode());
//		//		System.out.println("Contenu tabCdes:" + tabCdes.elements().nextElement().toString());
//		System.out.println();
//		System.out.println("Taille de ma tabCde: " +tabCdes.size());
//
//
//		// écrire le nombre d'articles
//		//		out.println(tabCdes.size());
//
//		Enumeration<Commande> em = tabCdes.elements();
//		while(em.hasMoreElements()){		
//			Commande Cde = em.nextElement();
//
//
//			//			System.out.println(Cde.getDateCde());
//			//			System.out.println(Cde.getCodeCde());
//
//			Cde.writeData(out);
//		}
//
//		//		for (int i = 0; i < tabCdes.size(); i++){
//		//
//		//			
//		//			System.out.println(tabCdes.elements().nextElement().getDateCde());
//		//			
//		//			tabCdes.elements().nextElement().writeData(out);
//		//		}
//		/* *************************************************** */	
//	}


}
