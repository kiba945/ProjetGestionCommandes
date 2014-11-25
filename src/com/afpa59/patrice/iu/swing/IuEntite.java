package com.afpa59.patrice.iu.swing;

import com.afpa59.patrice.service.commun.ServiceEntite;



public interface IuEntite{
	
	/************************************/
	/*		D�claration des m�thodes	*/
	/************************************/
	// Signature des m�thodes seulement
	public void menuGeneral(ServiceEntite service);
	public void creer(ServiceEntite service);
	public void visualiser(ServiceEntite service);
	public void modifier(ServiceEntite service);
	public void supprimer(ServiceEntite service);
	public void visualiserTout(ServiceEntite service);
}
