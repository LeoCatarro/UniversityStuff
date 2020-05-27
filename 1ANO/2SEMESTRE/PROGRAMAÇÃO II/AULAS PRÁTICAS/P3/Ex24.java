/*
	CAPITULO 3: EX.24
	Programa que recebe o peso de uma unidade de caixa de café em pounds e o numero que bags vendidas e mostra o preço total. Do seguinte modo:
	
	Number of bags sold:   32     
	Weight per bag:   5 lb    
	Price per pound:   $5.99           
	Sales tax:   7.25%        
	
	Total price:   $ 1027.884

	SABEMOS QUE:  totalPrice = unitWeight * numberOfUnits * 5.99;             totalPriceWithTax = totalPrice + totalPrice * 0.0725;
*/	

import java.util.Scanner;

public class Ex24{
	public static void main(String[] args){

	float unitWeight, numberOfUnits; 
	double totalPrice, totalPriceWithTax;
	Scanner uw, nof;

	System.out.println("Qual o peso por bag?");
	uw = new Scanner(System.in);
	unitWeight = uw.nextFloat();

	System.out.println("Qual o numero bags vendidas?");
	nof = new Scanner(System.in);
	numberOfUnits = nof.nextFloat();

	totalPrice = unitWeight * numberOfUnits * 5.99;
	totalPriceWithTax = (totalPrice) + (totalPrice * 0.0725); 

	System.out.println("Number of bags Sold:" + " " + numberOfUnits);
	System.out.println("Weight per bag:" + " " + unitWeight);
	System.out.println("Price per pound: $5.99");
	System.out.println("Sales tax: 7.25%");
	System.out.println("");
	System.out.println("Total price:" + " " + totalPriceWithTax);

	}
}