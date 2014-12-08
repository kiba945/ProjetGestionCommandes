package com.afpa59.patrice.appli;

import java.io.IOException;

import com.afpa59.patrice.iu.jdbc.IuArticle;
import com.afpa59.patrice.service.jdbc.ServiceArticle;

public class MesTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		ServiceArticle serviceA;
		IuArticle IUArt;
		
		serviceA = new ServiceArticle();
		
		try {
			serviceA.readData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		serviceA.creer(9, "BAP - One Shot",1);
		
		IUArt = new IuArticle(serviceA);
		
//		IUArt.creer(serviceA);
		
//		IUArt.visualiserTout(serviceA);
		
		
		
	}

}
