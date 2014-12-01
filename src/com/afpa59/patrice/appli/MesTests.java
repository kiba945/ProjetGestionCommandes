package com.afpa59.patrice.appli;

import com.afpa59.patrice.iu.swing.IuArticle;
import com.afpa59.patrice.service.jdbc.ServiceArticle;

public class MesTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		ServiceArticle serviceA;
		IuArticle IUArt;
		
		serviceA = new ServiceArticle();
		
		serviceA.creer(9, "BAP - One Shot",1);
		
//		IUArt = new IuArticle(serviceA);
		
		
//		IUArt.creer(serviceA);
		
//		IUArt.visualiserTout(serviceA);
		
		
		
	}

}
