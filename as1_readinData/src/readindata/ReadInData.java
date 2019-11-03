package readindata;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ReadInData 
{
	static int FILELENGTH = 7840;
	private Scanner astroFile;
	static String[][] astroArray = new String[FILELENGTH][7];
	
	public String textFieldNames(int i) {
		return astroArray[i][0] + astroArray[i][1] + "\t" + astroArray[i][3] + "\t" + astroArray[i][4] + "\t" + astroArray[i][5] 
				+ "\t" + astroArray[i][6] + "\t" + astroArray[i][2];
	}
		
		public void openFile() //opens dataset
		{
			try 
			{
				astroFile = new Scanner(new File("AstronomicObjectDetection.txt"));
			} 
			catch (Exception e) {
				System.out.println("Could not find file");
			}
		}
		
		
		//reads dataset and saves to array astroArray
		
		public void readFile() 
		{
			int counter = 0;
			
			while (astroFile.hasNext()) 
			{
				String s = astroFile.nextLine();
				String[] fieldNames = s.split("\\t", 0);
				
				for (int i = 0; i < fieldNames.length; i++) {
					astroArray[counter][i] = fieldNames[i];
				}
				
				counter++;
			}
		}
		
		
		//closes dataset
		
		public void closeFile() //closes dataset
		{
			astroFile.close();
		}
		
		
		//prints all data in array
		
		public ArrayList<String> showAllData() /*Search for N or S hemisphere*/
		{
			ArrayList<String> allDataArrayList = new ArrayList<String>();
			
			for (int i = 0; i < astroArray.length; i++) 
			{
				allDataArrayList.add(textFieldNames(i));
			}
			return allDataArrayList;
		} 
		
		
		//Finds specific entry searched for
		
		public String findNumber(String numberSearch) //takes user input and finds entry
		{
			int indexFind = -1;
			
			for (int i = 0; i < astroArray.length; i++) 
			{
				if (astroArray[i][1].equals(numberSearch)) 
				{
					indexFind = i;
				}
			}
			
			if (indexFind == -1) 
			{
				return("Not found");
			}
			else 
			{
				return(textFieldNames(indexFind));
			}
		}
		
		
		
		public Object[] mathCalcStats() 
		{
			float mean = 0;
			String maxName = "";
			float max = -100;
			String minName= "";
			float min = 10000;
			float[] sortArray = new float[astroArray.length];
			float median;
			int middle = sortArray.length/2;
			
			for (int i = 0; i < astroArray.length; i++) /*mean */
			{
				float mag = Float.parseFloat(astroArray[i][5]);
				mean = mean + mag;
				
				if (mag > max) {max = mag; maxName = astroArray[i][0] + astroArray[i][1];}
				
				if (mag < min && mag > 0) {min = mag; minName = astroArray[i][0] + astroArray[i][1];}
				
				sortArray[i]=mag;				
			}
			
			mean = mean / astroArray.length;
			
			Arrays.sort(sortArray);
			
			if(sortArray.length%2==0) median = (sortArray[middle] + sortArray[middle - 1])/2;
			else median = sortArray[middle];
			
			Object[] resultArray = {mean, max, maxName, min, minName, median}; 
			return resultArray;
		}
		
		public ArrayList<String> searchHemisphere(String nOrSSearch) /*Search for N or S hemisphere*/
		{
			ArrayList<String> NorSresultArrayList = new ArrayList<String>();
			
			for (int i = 0; i < astroArray.length; i++) 
			{
				if ((nOrSSearch.equalsIgnoreCase("N") && Float.parseFloat(astroArray[i][4]) >= 0) ||
						(nOrSSearch.equalsIgnoreCase("S") && Float.parseFloat(astroArray[i][4]) <= 0)) 
				{
					NorSresultArrayList.add(textFieldNames(i));
				}
			}
			return NorSresultArrayList;
		}
		
		public ArrayList<String> sortDescription(String descriptionSearch, int field)
		{
			ArrayList<String> foundDescriptionArrayList = new ArrayList<String>();
			
			for (int i = 0; i < astroArray.length; i++) 
			{
				if (astroArray[i][field].contains(descriptionSearch))
				{
					foundDescriptionArrayList.add(textFieldNames(i));
				}
			}
			
			return foundDescriptionArrayList;
		}
		
		
}