package com.afpa59.patrice.iu.console;

import com.afpa59.patrice.iu.commun.IuEntite;
import com.afpa59.patrice.service.commun.ServiceEntite;


public class IuBase implements IuEntite{
	/************************************/
	/*		D�claration des m�thodes	*/
	/************************************/
	
	public void menuGeneral(ServiceEntite service){}
	public void creer(ServiceEntite service){}
	public void visualiser(ServiceEntite service){}
	public void modifier(ServiceEntite service){}
	public void supprimer(ServiceEntite service){}
	public void visualiserTout(ServiceEntite service){}
}
