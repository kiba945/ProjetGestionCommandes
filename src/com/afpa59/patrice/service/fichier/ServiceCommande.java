package com.afpa59.patrice.service.fichier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import com.afpa59.patrice.donnees.Commande;
import com.afpa59.patrice.utils.DateUser;


public class ServiceCommande extends ServiceBase implements Serializable{	// !!!!!!!!!!!! SUPPRESSION du extends ServiceBase
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int numero=1;

	private static Hashtable<String,Commande> tabCdes = new Hashtable<String,Commande>();

	public ServiceCommande(){}
	public ServiceCommande(Hashtable<String,Commande> Cde){tabCdes = Cde;}

	public Hashtable<String,Commande> getTabCdes(){return tabCdes;}

	public void setTabCdes(Hashtable<String,Commande> tableDesCommandes){tabCdes = tableDesCommandes;}

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

	public String cle(){
		Enumeration<String> em = tabCdes.keys();
		String s = "";
		while(em.hasMoreElements()){
			s = em.nextElement() + " *** " + s;
		}
		return s;
	}	

	public int taille(){return tabCdes.size();}


	public void creer(){ }	

	//Ajouter une nouvelle commande dans la base
	public void creer(Commande Cde){tabCdes.put(Cde.getCodeCde(), Cde);}


	/*** Méthode visualiser qui a en paramétre un code 
	 * et affiche le Commande correspondant ***/	

	public void visualiser(String code){
		if(retourner(code) != null){  
			System.out.println(retourner(code).toString());
		}		
	}


	/*** Méthode modifier qui a en paramétre un code
	 *  et qui modifie le Commande correspondant ***/		

	public void modifier(int code){	}	

	//Supprimer une commande de la base par le code client
	public void supprimer(Commande Cde){tabCdes.remove(Cde.getCodeCde());}


	public void supprimer(int code) {
		// TODO Auto-generated method stub

	}	

	//Retourner la commande du numero passé en paramètres
	public Commande retourner(String numCde){

		if(tabCdes.get(numCde) !=null ){
			return tabCdes.get(numCde);

		}else{
			return null;
		}
	}
	
	
	/**
	* Lit un tableau de commandes à partir d’un lecteur bufférisé
	* 
	* @param in Le lecteur bufférisé
	* @return un tableau de commandes
	*/
	public Hashtable<String,Commande> readData(BufferedReader in) throws IOException{
		
		String s;
		DateUser date;
		int jourCde, moisCde, anneeCde;
		
		while((s=in.readLine())!=null){
			
			Commande cde = new Commande();
			
			/* ************** DEBUGGING ************************** */
			System.out.println("Je suis dans de la classse ServiceCommande"
					+ " dans la méthode readData()");	
						
			
			String dateCde[] = s.split("/");
			
			jourCde = Integer.parseInt(dateCde[0]);
			moisCde = Integer.parseInt(dateCde[1]);
			anneeCde = Integer.parseInt(dateCde[2]);
			
			date = new DateUser(jourCde,moisCde,anneeCde);
			
			cde.setDateCde(date);
			
			s = in.readLine();
			
//			ESconsole.affiche("date:"+date);
//			ESconsole.affiche("nouvelle ligne:"+s);
			
			cde.readData(s);

			tabCdes.put(cde.getCodeCde(), cde);
			setTabCdes(tabCdes);
			
			/* *************************************************** */	
		}
		
		
		return tabCdes;
	}
	
	
	/**
	* Ecrit tous les articles dans un tableau vers un printWriter
	* @param tabArticle Un tableau d’articles
	* @param out Un printWriter
	 */
	public void writeData(PrintWriter out) throws IOException {
		// TODO Auto-generated method stub
		
		
		/* ************** DEBUGGING ************************** */
		System.out.println("Je suis dans de la classse ServiceCommande"
				+ " dans la méthode writeData()");	
//		System.out.println("Contenu du Code:" + tabCdes.elements().nextElement().getCode());
//		System.out.println("Contenu du Code:" + tabCdes.elements().nextElement().getUneCommande());
//		System.out.println("Contenu du Code:" + tabCdes.elements().nextElement().getCode());
//		System.out.println("Contenu tabCdes:" + tabCdes.elements().nextElement().toString());
		System.out.println();
		System.out.println("Taille de ma tabCde: " +tabCdes.size());
				
		
		// écrire le nombre d'articles
//		out.println(tabCdes.size());
		
		Enumeration<Commande> em = tabCdes.elements();
		while(em.hasMoreElements()){		
			Commande Cde = em.nextElement();
			
			
//			System.out.println(Cde.getDateCde());
//			System.out.println(Cde.getCodeCde());
			
			Cde.writeData(out);
		}
		
//		for (int i = 0; i < tabCdes.size(); i++){
//
//			
//			System.out.println(tabCdes.elements().nextElement().getDateCde());
//			
//			tabCdes.elements().nextElement().writeData(out);
//		}
		/* *************************************************** */	
	}
	public void writeData() {
		// TODO Auto-generated method stub
		
	}


}
