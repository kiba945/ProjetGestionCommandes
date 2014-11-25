package com.afpa59.patrice.iu.swing;

import java.awt.Container; // pour BorderLayout
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


//import com.afpa59.patrice.service.jdbc.ServiceArticle;

import com.afpa59.patrice.service.fichier.ServiceArticle;
import com.afpa59.patrice.service.fichier.ServiceClient;
import com.afpa59.patrice.service.fichier.ServiceCommande;
import com.afpa59.patrice.utils.ConnectionFichiersArticles;
import com.afpa59.patrice.utils.ConnectionFichiersClients;
import com.afpa59.patrice.utils.ConnectionFichiersCommandes;
import com.afpa59.patrice.utils.ES;


public class IuGeneral extends IuBase implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton b1, b2, b3,b4, b5;
	ServiceArticle s1;
	ServiceClient s2;
	ServiceCommande s3;

	static ConnectionFichiersArticles fichArt;
	static ConnectionFichiersClients fichClt;
	static ConnectionFichiersCommandes fichCde;


	static String nomPhysiqueArticle = "TableArticles";
	static String nomPhysiqueClient = "TableClients";
	static String nomPhysiqueCommande = "TableCommandes";


	public IuGeneral(){

		fichArt = new ConnectionFichiersArticles(nomPhysiqueArticle);
		fichClt = new ConnectionFichiersClients(nomPhysiqueClient);
		fichCde = new ConnectionFichiersCommandes(nomPhysiqueCommande);

		s1 = fichArt.getTab();
		s2 = fichClt.getTab();
		s3 = fichCde.getTab();

		String mes;

		mes = "*** CHARGEMENT du FICHIER des ARTICLES ***\n";
		if(s1 == null){

			s1 = new ServiceArticle(); 
			mes = mes + "*** TABLE ARTICLES VIDE !! ==>" +
					"CREATION par DEFAUT de la TABLE des ARTICLES ***\n";
		}


		mes = mes + "\n*** CHARGEMENT du FICHIER des CLIENTS ***\n";
		if(s2 == null){
			s2 = new ServiceClient();
			mes = mes + "*** TABLE des CLIENTS VIDE ***" +
					"CREATION par DEFAUT de la TABLE des CLIENTS ***\n";
		}


		mes = mes + "\n*** CHARGEMENT du FICHIER des COMMANDES ***\n";
		if(s3 == null){
			s3 = new ServiceCommande();
			mes = mes + "*** TABLE des COMMANDES VIDE ***";
		}		
		ES.affiche(mes);		


		Container container = getContentPane(); //getContentPane() permet de créer le container obligatoire

		setTitle("CLIENT AZIMUTH");

		b1= new JButton("GESTION des ARTICLES");
		b2= new JButton("GESTION des CLIENTS");
		b3= new JButton("GESTION des COMMANDES");
		b4= new JButton("<html> <center>FIN </center><br>"
				+ "ATTENTION: OBLIGATOIRE SI VOUS VOULEZ SAUVEGARDER LES FICHIERS </html>");

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		b1.setMnemonic('A');
		b2.setMnemonic('C');
		b3.setMnemonic('M');
		b4.setMnemonic('F');
		
		container.setLayout(new GridLayout(4,1,10,10));

		container.add(b1);
		container.add(b2);
		container.add(b3);
		container.add(b4);


		this.setSize(750,750); //LARGEUR * HAUTEUR de la frame
		this.setMinimumSize(new Dimension(550, 550)); // Définir une taille de fenetre minimum		
		this.setLocationRelativeTo(null); // pour centrer le container
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void actionPerformed(ActionEvent evt){

		if (evt.getSource() == b1) { new IuArticle(s1);} 

		if (evt.getSource() == b2) { new IuClient(s2);}

		if (evt.getSource() == b3) { new IuCommande(s1,s2,s3);}

		if (evt.getSource() == b4) { sauvegardeFichiers(nomPhysiqueArticle, nomPhysiqueClient, nomPhysiqueCommande);; System.exit(0);}

	}


	public void sauvegardeFichiers(String nomPhysiqueArticle, String nomPhysiqueClient, String nomPhysiqueCommande){

		ES.affiche(" Le fichier ARTICLES sera sauvegardé sous le nom suivant: "
				+ nomPhysiqueArticle);
		fichArt.ecrire(nomPhysiqueArticle);


		ES.affiche(" Le fichier CLIENTS sera sauvegardé sous le  nom suivant: "
				+ nomPhysiqueClient);
		fichClt.ecrire(nomPhysiqueClient);


		ES.affiche(" Le fichier COMMANDES sera sauvegardé sous le nom suivant: "
				+ nomPhysiqueCommande);
		fichCde.ecrire(nomPhysiqueCommande);
	}

}
