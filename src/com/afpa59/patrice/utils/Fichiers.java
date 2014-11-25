package com.afpa59.patrice.utils;

import com.afpa59.patrice.service.commun.ServiceEntite;

public interface Fichiers {

	/**
	 * 	M�thode pour lire un Fichier
	 * 
	 * @param nomFichier de type String
	 */
	public abstract void lire(String nomFichier);

	/**
	 * 	M�thode pour �crire dans un Fichier
	 * 
	 * @param nomFichier de type String
	 * @param tab
	 */
	public abstract void ecrire(String nomFichier);

	/**
	 * @return
	 */
	public abstract ServiceEntite getTab();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

}