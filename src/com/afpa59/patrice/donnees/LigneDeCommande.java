package com.afpa59.patrice.donnees;

//import com.afpa59.patrice.service.fichier.ServiceArticle;



public class LigneDeCommande extends Entite{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************************/
	/* Déclaration des variables d'instance */
	/****************************************/
	private int quantite;
	
	/************************************/
	/*	Déclaration des constructeurs	*/
	/************************************/
	
	
	/*** 1er constructeur ***/
	public LigneDeCommande(){}
	
	
	/*** 2ème constructeur avec des paramètres ***/
	public LigneDeCommande(int code, int quantite){
		super(code);
		this.quantite=quantite;
	}
	
	
	/************************************/
	/*		Déclaration des GETTEURS	*/
	/************************************/
	public int getQuantite(){return quantite;}
	
	/************************************/
	/*		Déclaration des SETTEURS	*/
	/************************************/
	public void setQuantite(int quantite){this.quantite=quantite;}
	
	/************************************/
	/*		Décalaration des méthodes	*/
	/************************************/
	
	/*** Méthode toString() retourne une chaîne de caractère  ***/
	public String toString(){return (super.toString()+" Quantité: "+quantite);}
	
	
//	public String facturer(ServiceArticle tabArt){
//		Article art = tabArt.retourner(super.getCode());
//		float tva = 20.60F;
//		String facture = "";
//		if(art == null){
//			facture = facture+ super.getCode() + "           *** ARTICLE EPUISEE!!!! ***";
//		}else{
//			facture = facture + super.getCode() + " *** " + art.getDesignation() + " *** "+
//					quantite+" *** "+ art.getPrix() + " *** " + prixTotal(tabArt) +
//					" *** "+ (int)((prixTotal(tabArt)*(1+tva/100))*100.0F)/100.0F;
//		}
//		return facture+"\n";
//	}
//	
//	public float prixTotal(ServiceArticle tabArt){
//		Article art = tabArt.retourner(super.getCode());
//		if(art == null)
//			return 0F;
//		return (int)((art.prixFacture(quantite))*100.0F)/100.0F;
//	}

}
