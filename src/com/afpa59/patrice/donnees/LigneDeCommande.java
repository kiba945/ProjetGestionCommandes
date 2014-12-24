package com.afpa59.patrice.donnees;

//import com.afpa59.patrice.service.fichier.ServiceArticle;



public class LigneDeCommande extends Entite{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************************/
	/* D�claration des variables d'instance */
	/****************************************/
	private int quantite;
	
	/************************************/
	/*	D�claration des constructeurs	*/
	/************************************/
	
	
	/*** 1er constructeur ***/
	public LigneDeCommande(){}
	
	
	/*** 2�me constructeur avec des param�tres ***/
	public LigneDeCommande(int code, int quantite){
		super(code);
		this.quantite=quantite;
	}
	
	
	/************************************/
	/*		D�claration des GETTEURS	*/
	/************************************/
	public int getQuantite(){return quantite;}
	
	/************************************/
	/*		D�claration des SETTEURS	*/
	/************************************/
	public void setQuantite(int quantite){this.quantite=quantite;}
	
	/************************************/
	/*		D�calaration des m�thodes	*/
	/************************************/
	
	/*** M�thode toString() retourne une cha�ne de caract�re  ***/
	public String toString(){return (super.toString()+" Quantit�: "+quantite);}
	
	
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
