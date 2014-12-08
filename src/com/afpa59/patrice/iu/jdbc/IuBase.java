package com.afpa59.patrice.iu.jdbc;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.afpa59.patrice.iu.commun.IuEntite;
import com.afpa59.patrice.service.commun.ServiceEntite;


public class IuBase extends JFrame implements IuEntite{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/************************************/
	/*		Déclaration des méthodes	*/
	/************************************/
	
	public void menuGeneral(ServiceEntite service){}
	public void creer(ServiceEntite service){}
	public void visualiser(ServiceEntite service){}
	public void modifier(ServiceEntite service){}
	public void supprimer(ServiceEntite service){}
	public void visualiserTout(ServiceEntite service){}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Méthode qui affecte un raccourci à une option Menu
	 * @param menuItem
	 * @param raccourci
	 */
	public void SetRaccourci(JMenuItem menuItem, char raccourci) {
		
		menuItem.setMnemonic(raccourci);	//L'option menu dispose d'un mnémonique

		switch (raccourci) {	//et d'un'accélérateur

		case 'C':
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));//l'accélérateur CTRL/C
			break;
		case 'L':
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.CTRL_MASK));//l'accélérateur CTRL/L
			break;
		case 'V':
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));//l'accélérateur CTRL/V
			break;		
		case 'S':
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));//l'accélérateur CTRL/S
			break;
		case 'M':
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));//l'accélérateur CTRL/M
			break;
		case 'R':
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));//l'accélérateur CTRL/C
			break;
		case 'F':
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));//l'accélérateur CTRL/C
			break;
		}		
	}
}
