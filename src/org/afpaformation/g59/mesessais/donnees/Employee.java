package org.afpaformation.g59.mesessais.donnees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Employee{
	
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee() {}
	public Employee(String n, double s, int year, int month, int day){
		name = n;
		salary = s;
		GregorianCalendar calendar
		= new GregorianCalendar(year, month - 1, day);
		// GregorianCalendar utilise 0 = janvier
		hireDay = calendar.getTime();
	}
	
	public String getName(){ return name;}
	public double getSalary(){ return salary;}
	public Date getHireDay(){ return hireDay;}
	
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	
	public String toString(){
		return getClass().getName()
				+ "[name=" + name
				+ ",salary=" + salary
				+ ",hireDay=" + hireDay
				+ "]";
	}
	
	
	
	/**
		Ecrit les données des employés dans un PrintWriter
		@param out Le PrintWriter
	 */
	public void writeData(PrintWriter out) throws IOException{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(hireDay);
		out.println(name + "|"
				+ salary + "|"
				+ calendar.get(Calendar.YEAR) + "|"
				+ (calendar.get(Calendar.MONTH) + 1) + "|"
				+ calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	
	
	/**
		Lit les données des employés depuis un lecteur bufférisé
		@param in Le lecteur bufférisé
	 */
	public void readData(BufferedReader in) throws IOException{
		String s = in.readLine();
		StringTokenizer t = new StringTokenizer(s, "|");
		name = t.nextToken();
		salary = Double.parseDouble(t.nextToken());
		int y = Integer.parseInt(t.nextToken());
		int m = Integer.parseInt(t.nextToken());
		int d = Integer.parseInt(t.nextToken());
		GregorianCalendar calendar
		= new GregorianCalendar(y, m - 1, d);
		// GregorianCalendar utilise 0 = janvier
		hireDay = calendar.getTime();
	}
}
