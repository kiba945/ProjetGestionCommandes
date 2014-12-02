package org.afpaformation.g59.mesessais.appli;

import org.afpaformation.g59.mesessais.menu.MenuJDBC;
import org.afpaformation.g59.mesessais.menu.MenuJPA;
import org.afpaformation.g59.mesessais.utils.ES;


public class MesEssais {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choix;

		do{
			choix = menuGeneral();
			switch(choix){
			case 1:   break;
			case 2:  break;
			case 3:  break;
			case 4:  MenuJDBC.menuGeneral(); break;
			case 5: MenuJPA.menuGeneral(); break;
			case 6: break;
			case 7: break;
			case 8: break;
			case 9: break;
			case 10: break;
			default: break;

			}
		}while(choix != 0);
		ES.affiche("\n\t AU REVOIR ... A BIENTOT!!");
	}

	/*** Méthode menuGeneral qui affiche un menu avec 2 choix
	 * et retourne un entier qui correspondant au choix ***/
	static int menuGeneral(){
		String s = "\n\t\t\t   BIENVENUE \n\n"+
				"\t\t 01. Test CLASSES (import à faire)\n"+
				"\t\t 02. Test JFRAME (import à faire) \n"+
				"\t\t 03. Test STREAM (import à faire) \n"+
				"\t\t 04. Test JDBC  \n"+
				"\t\t 05. Test JPA  \n"+
				"\t\t 06. Test (à venir)  \n"+
				"\t\t 07. Test (à venir) \n"+
				"\t\t 08. Test (à venir)  \n"+
				"\t\t 09. Test (à venir)  \n"+
				"\t\t 10. Test (à venir)  \n"+
				"\t\t FIN .............. 0\n\n"+
				"\t\t             Choix : ............ ";
		return ES.saisie(s, 0, 10);
	}		
}

