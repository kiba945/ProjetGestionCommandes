package org.afpaformation.g59.mesessais.menu;


import org.afpaformation.g59.mesessais.services.MonJPA;
import org.afpaformation.g59.mesessais.utils.ES;

public class MenuJPA extends MenuBase{

	/*** Méthode menuGeneral qui renvoie le choix du menu  ***/
	public static void menuGeneral(){
		int rep;

		do{
			rep = choix();
			switch(rep){
			case 1:  new MonJPA().test(); break;
			case 2:  break;
			case 3:  break;
			case 4:  break;
			case 5:  break;
			case 6:  break;
			case 7:  break;
			case 8:  break;
			case 9:  break;
			case 10:  break;
			case 11:  			break;
			case 0: break;
			//default:System.out.println("\n CHOIX INEXISTANT!!!\n");
			}
		}while(rep != 0);
	}

	/*** Méthode menuGeneral qui affiche un menu avec 2 choix
	 * et retourne un entier qui correspondant au choix ***/
	 static int choix(){
		String s = "\n\t\t\t *** MES JPA *** \n\n"+
				"01.  Test JPA \n"+
				"02.  \n"+
				"03.  \n"+
				"04.  \n"+
				"05.  \n"+
				"06.  \n"+
				"07.  \n"+
				"08.  \n"+
				"09.  \n"+
				"10. \n"+
				"11.  \n"+
				"\t\t FIN .................... 0\n\n"+
				"\t\t             Choix : ............ ";
		return ES.saisie(s, 0, 11);
	}		
}

