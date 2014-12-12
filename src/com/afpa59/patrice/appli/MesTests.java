package com.afpa59.patrice.appli;

import java.io.IOException;

import com.afpa59.patrice.iu.jdbc.IuArticle;
import com.afpa59.patrice.iu.jdbc.IuClient;
import com.afpa59.patrice.iu.jdbc.IuCommande;

import com.afpa59.patrice.service.jdbc.ServiceClient;
import com.afpa59.patrice.service.jdbc.ServiceArticle;
import com.afpa59.patrice.service.jdbc.ServiceCommande;

public class MesTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServiceArticle serviceA;
		ServiceClient serviceClt;
		ServiceCommande serviceCde;
		
		IuArticle IuArt;
		IuClient IuClt;	
		IuCommande IuCde;
		

		/************** TEST MODULE GESTION ARTICLES***************************/	
		serviceA = new ServiceArticle();

		try {
			serviceA.readData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("EXCEPTION Test MODULE GESTION Articles");
			e.printStackTrace();
		}

		IuArt = new IuArticle(serviceA);
		/********************************************************************/		

		/************** TEST MODULE GESTION CLIENTS***************************/	
		serviceClt = new ServiceClient();

		try {
			serviceClt.readData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("EXCEPTION Test MODULE GESTION Clients");
			e.printStackTrace();
			
		}

		IuClt = new IuClient(serviceClt);
		/**********************************************************************/		

		/************** TEST MODULE GESTION COMMANDES***************************/	
		serviceCde = new ServiceCommande();
		

		try {
			serviceCde.readData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("EXCEPTION Test MODULE GESTION Commandes");
			e.printStackTrace();
			
		}

		IuCde = new IuCommande(serviceA, serviceClt, serviceCde);
		/**********************************************************************/		

	}

}
