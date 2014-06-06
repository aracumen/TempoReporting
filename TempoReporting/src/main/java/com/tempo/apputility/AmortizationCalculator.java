package com.tempo.apputility;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.tempo.common.SeleniumObject;

/*
 * Class to calculate Amortization Schedule to verify the return value from Application
 */
public class AmortizationCalculator extends SeleniumObject {

	//Map to store Amortization Schedule table
	public static Map<Integer, Row> map = new TreeMap<Integer, Row> ();
	
	//Inner class to store values of Amortization Schedule table
	public static class Row 
	{
		private int month;
	    private int emi;
	    private int monthlyInterest;
	    private int outstandingPrinciple;
	    
	    //Constructor
	    public Row(int month, int emi2, int monthlyInterest2, int outstandingPrincipal2)
	    {
	      this.month = month;
	      this.emi = emi2;
	      this.monthlyInterest = monthlyInterest2;
	      this.outstandingPrinciple = outstandingPrincipal2;
	    }
	    
	    //getter method to get month
	    public int getMonth()
	    {
	    	return month;
	    }
	    
	    //getter method to get EMI
	    public double getEMI()
	    {
	    	return emi;
	    }
	    
	    //getter method to get monthly interest
	    public double getMonthlyInterest()
	    {
	    	return monthlyInterest;
	    }
	    
	    //getter method to get outstanding principle
	    public double getOutstandingPrinciple()
	    {
	    	return outstandingPrinciple;
	    }
	}
	
	/*
	 * Method to calculate amortization schedule
	 */
	public static Map<Integer, Row> calculateAmortization(double loanAmount, double rateOfInterest, double years) {
		map.clear();
		int newbal;
		double im = rateOfInterest / (12 * 100);
		double nm = years * 12;
		int mp, ip, pp;
		int i;

		//to get monthly payment
		mp = (int) (loanAmount * (im / (1 - (Math.pow(1/(1+ im), nm)))));
		
		printHeader();
		
		// print amortization schedule for all months except the last month
		for (i = 1; i < nm; i++) {
			ip = (int) (loanAmount * im);// interest paid
			pp = mp - ip; // princial paid
			newbal = (int) (loanAmount - pp); // new balance
			map = createTable(i, mp, ip, newbal);
			loanAmount =  newbal; // update old balance
		}
		// last month
		pp = (int) loanAmount;
		ip = (int) (loanAmount * im);
		mp = pp + ip;
		newbal = 0;
		map = createTable(i, mp, ip, newbal);
		printconsole();
		return map;

	}

	/*
	 * method to create Amortization schedule table
	 */
	public static Map<Integer, Row> createTable(int month, int emi, int monthlyInterest,
			int outstandingPrinciple) {
		
		//emi = parse(emi);
		//monthlyInterest = parse(monthlyInterest);
		//outstandingPrinciple = parse(outstandingPrinciple);
		
		Row r = new Row(month,emi,monthlyInterest,outstandingPrinciple);
		map.put(r.month, r);
		return map;

	}
	
	public static void printconsole()
	{
		for (Row row: map.values())
		{
			System.out.println("\t" +row.month + "\t" + row.emi + "\t" + row.monthlyInterest + "\t" + row.outstandingPrinciple);
		}
	}

	public static void printHeader() {
		int i;
		System.out.println("\nAmortization Schedule for  Borrower");
		for (i = 0; i < 62; i++)
			System.out.print("-");
		System.out.println("");
		System.out.println("\t" + "\t"  + "      " + "Monthly" + "\t" + "Outstanding");
		System.out.println("\t"+ "Month" + "\t" + "EMI" + "   " + "Interest" + "  " + "Principle");
	}
	
	public static double parse(double val){   
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");
	     return Double.valueOf(twoDForm.format(val));
	     
	}
	
	public static void main(String[] args) {
		double p;
		double iy;
		double n;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter amount of loan:");
		p = sc.nextDouble();
		System.out.print("Enter interest rate per year:");
		iy = sc.nextDouble();
		System.out.print("Enter number of years:");
		n = sc.nextDouble();
		calculateAmortization(p, iy, n);

	}


	

}