package org.afpaformation.g59.mesessais.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.afpaformation.g59.mesessais.donnees.Article;

public class MonJPA {
	
//    private static final String JPQL_SELECT_PAR_EMAIL = 
//    		"SELECT u FROM Utilisateur u WHERE u.email=:email";
//    private static final String PARAM_EMAIL =
//    		"email";
//	
//	
//	@Entity
//	public class Utilisateur {
//
//	    @Id
//	    @GeneratedValue( strategy = GenerationType.IDENTITY )
//	    private Long      id;
//	    private String    email;
//	    @Column( name = "mot_de_passe" )
//	    private String    motDePasse;
//	    private String    nom;
//	    @Column( name = "date_inscription" )
//	    private Timestamp dateInscription;
//
//	}

	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Bonsoir");
		
	    // Injection du manager, qui s'occupe de la connexion avec la BDD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
		
		EntityManager em =emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Article article = new Article(1, "toto", 5);
		em.persist(article);
		em.flush();
		et.commit();
		
		em.close();
		
	}
}
