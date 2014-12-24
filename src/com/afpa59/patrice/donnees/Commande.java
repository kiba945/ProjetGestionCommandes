package com.afpa59.patrice.donnees;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.afpa59.patrice.service.fichier.ServiceArticle;
import com.afpa59.patrice.utils.DateUser;

public class Commande extends Entite{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	private String codeCde;
	private DateUser dateCde = new DateUser();
	private DateUser dateFact;
	private boolean etatFacture;

	/***********************CLIENT*********************************/
	private int codeClt;
	
	public int getCodeClt() {
		return codeClt;
	}
	public void setCodeClt(int codeClt) {
		this.codeClt = codeClt;
	}

	/**************************************************************/
	
	private ArrayList<LigneDeCommande> listeCde = new ArrayList<LigneDeCommande>();


	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	/*** 1er constructeur ***/
	public Commande(){}
	/*** 2ème constructeur avec des paramètres ***/
//	public Commande(String code, DateUser dateCde, ArrayList<LigneDeCommande> Cde){
//		//		super.getCode();
//		this.codeCde=code;
//		this.dateCde=dateCde;
//		this.listeCde=Cde;
//	}
	/*** 3ème constructeur avec des paramètres ***/
	public Commande(String code, DateUser dateCde, int codeClient, ArrayList<LigneDeCommande> Cde){
		//		super.getCode();
		this.codeCde=code;
		this.dateCde=dateCde;
		this.codeClt=codeClient;
		this.listeCde=Cde;
	}

	/************************************/
	/*		Déclaration des GETTEURS	*/
	/************************************/
	public String getCodeCde(){return codeCde;}
	public DateUser getDateCde(){return dateCde;}
	public DateUser getDateFact(){return dateFact;}
	public boolean getEtatFacture(){return etatFacture;}

	public ArrayList<LigneDeCommande> getUneCommande(){return listeCde;}

	/************************************/
	/*		Déclaration des SETTEURS	*/
	/************************************/
	public void setCodeCde(String code){this.codeCde=code;}
	public void setDateCde(DateUser dateCde){this.dateCde=dateCde;}
	public void setUneCommande(ArrayList<LigneDeCommande> ldc){this.listeCde=ldc;}
	public void setDateFact(DateUser dateFact){this.dateFact = dateFact;}
	public void setEtatFacture(boolean etatFacture){this.etatFacture = etatFacture;}


	/************************************/
	/*		Décalaration des méthodes	*/
	/************************************/
	/*** Méthode toString() retourne une chaîne de caractère  ***/
	public String toString(){
		String st = new String();
		for(int i=0;i<taille();i++){
			st = st + listeCde.get(i).toString()+ "**\n";
		}
		return st;
	}

	public void ajouter(LigneDeCommande lg){listeCde.add(lg);}

	public void supprimer(LigneDeCommande lg){listeCde.remove(lg);}

	public LigneDeCommande retourner(int lg){
		if(taille()>0){
			return listeCde.get(lg);
		}
		return null;
	}

	public String cle() {return null;}

	/*** Méthode taille() retourne la taille de la liste des commandes  ***/
	public int taille(){return listeCde.size();}

	public String facturer(ServiceArticle tabArt){
		float tva = 20.60F;
		float prixTotalCde = 0;
		String entete = "******************** FACTURE DE LA COMMANDE N° "+codeCde+" ********************\n"+
				"Code ** Libelle ** Quantite ** PUHT ** PTHT ** PUTTC("+tva+"%)\n"+
				"________________________________________________________\n";
		String details = "";
		String pied = "";

		for(int i=0;i<taille();i++){
//			details = details+listeCde.get(i).facturer(tabArt);
//			prixTotalCde=prixTotalCde+listeCde.get(i).prixTotal(tabArt);
		}

		pied=pied+"\n ** TOTAL HORS TAXE : "+prixTotalCde+
				"\n ** TVA("+tva+"%) : "+(int)((prixTotalCde*(tva/100))*100.0F)/100.0F+
				"\n ** TOTAL TOUTE TAXE : "+(int)((prixTotalCde*(1+(tva/100)))*100.0F)/100.0F+"\n";

		return entete+details+pied;
	}

	public String etatCommandeFacturee(){
		String st;
		if(etatFacture){
			st=" *** Date facture : "+dateFact+"\n";
		}else{
			st=" *** Non encore Facturee...\n";
		}
		return st;
	}

	/**
	 * Méthode qui lit une ligne du fichier qui correspond à une ligne Article
	 * 
	 * @param ligArticle du type String
	 */
	public void readData(String ligCommande) throws IOException{

		int codeArt;
		int qteArt;
		//		int numlig;

		StringTokenizer t = new StringTokenizer(ligCommande, "|");

		/* **************** DEBUG ********************** */
		System.out.println("Je suis dans de la classse Commande"
				+ " dans la méthode readData()");
		codeCde= t.nextToken();

		//		numlig=  Integer.parseInt(t.nextToken());

		while(t.hasMoreTokens()){	
			codeArt= Integer.parseInt(t.nextToken());
			qteArt= Integer.parseInt(t.nextToken());
			ajouter(new LigneDeCommande(codeArt,qteArt));
		}
		

		//		ESconsole.affiche(" code cde: "
		//				+ codeCde
		//				+ " num ligne: "
		//				+ numlig
		//				+ " code Article: "
		//				+ codeArt
		//				+ " qte commandée: "
		//				+ qteArt
		//				+ " ");

		//		listeCde.set(numlig, new LigneDeCommande(codeArt,qteArt));


		/* **************** DEBUG ********************** */
	}	

	/**
	 * Méthode qui écrit une ligne d'une commande dans un PrintWriter
	 * 
	 * @param out du type PrintWriter
	 */
	public void writeData(PrintWriter out) throws IOException{
		//		super.writeData(out);

		/* **************** DEBUG ********************** */
		String st="";
		System.out.println("Je suis dans de la classe Commande"
				+ " dans la méthode writeData()");		



		out.print(dateCde);
		out.print(codeCde);
		
		//		st = codeCde;

		//		ESconsole.affiche(st);

		for (int i = 0; i < listeCde.size(); i++) {

			//			ESconsole.affiche("taille de listCde: "+listeCde.size());

			st = st 
					//					+ "|" 
					//					+ i		// numero de la ligne
					+ "|" 
					+ listeCde.get(i).getCode()
					+ "|"  
					+ listeCde.get(i).getQuantite();
		}		

		out.println(st);

		//		ESconsole.affiche(st);
		/* **************** DEBUG ********************** */
	}



}