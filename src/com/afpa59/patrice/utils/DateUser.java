package com.afpa59.patrice.utils;

public class DateUser {
	
	private int jour;
	private int mois;
	private int annee;
	
	public DateUser(int jour, int mois, int annee){this.jour=jour; this.mois=mois; this.annee=annee;}

	@SuppressWarnings("deprecation")
	public DateUser(){
		java.util.Date d = new java.util.Date();
		jour=d.getDate();
		mois=d.getMonth()+1;
		annee=d.getYear()+1900;
	}
	
	public void setJour(int j){jour=j;}
	public void setMois(int m){mois=m;}
	public void setAnnee(int a){annee=a;}
	
	public int getJour(){return jour;}
	public int getMois(){return mois;}
	public int getAnnee(){return annee;}
	
	public String toString(){return (jour+ "/" + mois + "/" + annee + "\n");}
	
	public static boolean validDate(int jour, int mois, int annee){
		switch(mois){
		case 4: case 6: case 9: case 11: return (jour<31);
			case 2: return ((bissextile(annee) && jour<30) || (!bissextile(annee) && jour<29));
		default: return (true);
		}
	}
	
	public static boolean bissextile(int annee){return ((annee%400 == 0) || ((annee%100 != 0)&& (annee%4 == 0)));}
	
	public void hier1(){
		jour--;
		if(jour<1){
			switch(mois){
			case 1:	jour=31; mois=12; annee--; break;
			case 3: if(! bissextile(annee)){
						jour=28; mois--; break;
					}else{
						jour=29; mois--; break;
					}
			case 4: case 6:	case 9: case 11: jour=31; break;
				default: jour=30; mois--; break;
			}
		}
	}
	
	public DateUser hier2(){
		DateUser dateH = new DateUser(jour,mois,annee);
		dateH.hier1();
		return dateH;
	}
	
	public void lendemain(){
		jour++;
		switch(jour){
			case 32: jour = 1; mois++;
			if(mois>12){
				mois=1;
				annee++;
			}break;
			case 31: if(mois == 4 || mois == 6 || mois == 9 || mois == 11){
				jour=1;mois++;
					}break;
			case 30: if(mois == 2){
						jour=1;mois++;
					}break;
			case 29: if(mois == 2 && !bissextile(annee)){
						jour=1;mois++;
					}break;
		}
	}
	
	public DateUser lendemain2(){
		DateUser dateD = new DateUser(jour,mois,annee);
		dateD.lendemain();
		return dateD;
	}
	
	public boolean avant(DateUser d){
		if(d.jour==jour && mois==d.mois && annee==d.annee){
			return false;
		}else if(d.jour<jour || d.mois<mois || d.annee<annee){
			return true;
		}
			return false;
	}
	
	public int age(){
		DateUser dateS = new DateUser();
		int age=0;
		age = dateS.annee-annee;
		return age;
	}
}