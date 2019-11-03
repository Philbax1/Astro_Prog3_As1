package mainRunner;

import java.util.ArrayList;
import java.util.Scanner;

import readindata.ReadInData;



public class MainRunner {

	public static void main(String[] args) 
	{
		ReadInData r = new ReadInData();
		r.openFile();
		r.readFile();
		r.closeFile();
		
		System.out.print("");
		System.out.println("Enter catalogue number to view");
		Scanner input = new Scanner(System.in);
		String numberSearch = input.nextLine();
		
		String result = r.findNumber(numberSearch);
		
		System.out.println(result);
		
		
		/*Mathematical calculations*/
		Object[] calcResult = r.mathCalcStats();
		System.out.println("Mean: "+ calcResult[0]);
		System.out.println("Dimmest (Max): "+ calcResult[2] + " = " + calcResult[1]);
		System.out.println("Brightest (Min): "+ calcResult[4] + " = " + calcResult[3]);
		System.out.println("Median: "+ calcResult[5]);
		
		
		/*Search north or south*/
		Boolean correctChar = false;
		String nOrSSearch = "";
		
		while (correctChar == false) 
		{
			System.out.println("Search for N or S hemisphere?");
			nOrSSearch = input.nextLine();
			
			if (nOrSSearch.equalsIgnoreCase("N") || nOrSSearch.equalsIgnoreCase("S")) correctChar = true;
		}
		
		ArrayList<String> NorSresultArrayList = r.searchHemisphere(nOrSSearch);
		for (String s:NorSresultArrayList) 
		{
			System.out.println(s);
		}
		
		String userDescriptionFieldSearch = "";
		Boolean correctChar2  = false;
		while (correctChar2 == false) 
		{
			System.out.println("Search on fields: (1)Catalog, (2)Number, (3)Description, (4)RA, (5)Dec, (6)Mag & (7)Misc");
			userDescriptionFieldSearch = input.nextLine();
			if (userDescriptionFieldSearch.equals("1") || userDescriptionFieldSearch.equals("2") || userDescriptionFieldSearch.equals("3") || 
					userDescriptionFieldSearch.equals("4") || userDescriptionFieldSearch.equals("5") || userDescriptionFieldSearch.equals("6") || 
					userDescriptionFieldSearch.equals("7") ) correctChar2 = true;
		}
		
		
		System.out.println("Search for string in description");
		String descriptionSearch = input.nextLine();
		
		ArrayList<String> foundDescriptionArrayList = r.sortDescription(descriptionSearch, Integer.parseInt(userDescriptionFieldSearch));
		if (foundDescriptionArrayList.size()==0) System.out.println("Nothing found");

		for (String s:foundDescriptionArrayList) 
		{
			System.out.println(s);
		}
		
		input.close();
	}
}