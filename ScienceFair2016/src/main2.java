// THIS IS THE DUPLICATE NOW

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class main2 {
	public static void main (String args[]) throws IOException {
		
		//--------FOR SPOT 1--------
		FileReader fileReader1 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot1.txt"); //sets the input file
		ArrayList spot1 = new ArrayList(); 																					 //creates a new array of objects, to hold multiple data types
		BufferedReader bufferedReader1 = new BufferedReader(fileReader1); 													 //creates a new buffered reader and reads from the file
		int counter1 = 0; 																									 //creates a counter variable to increment
		while (counter1 < 7) { 																								 //starts the loops based on counter value
			spot1.add(bufferedReader1.readLine()); 																			 //fills the object array with the information from the file by reading the lines
			counter1++; 																									 //increments counter variable
		} 																													 //end of loop
		bufferedReader1.close();																							 //close buffered reader
		
		//--------FOR SPOT 2--------
		FileReader fileReader2 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot2.txt");
		ArrayList spot2 = new ArrayList();
		BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
		int counter2 = 0;
		while (counter2 < 7) {
			spot2.add(bufferedReader2.readLine());
			counter2++;
		}	
		bufferedReader2.close();
		
		//--------FOR SPOT 3--------
		FileReader fileReader3 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot3.txt");
		ArrayList spot3 = new ArrayList();
		BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
		int counter3 = 0;
		while (counter3 < 7) {
			spot3.add(bufferedReader3.readLine());
			counter3++;
		}
		bufferedReader3.close();
		
		//--------FOR SPOT 4--------
		FileReader fileReader4 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot4.txt");
		ArrayList spot4 = new ArrayList();
		BufferedReader bufferedReader4 = new BufferedReader(fileReader4);
		int counter4 = 0;
		while (counter4 < 7) {
			spot4.add(bufferedReader4.readLine());
			counter4++;
		}
		bufferedReader4.close();
		
		//--------FOR SPOT 5--------
		FileReader fileReader5 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot5.txt");
		ArrayList spot5 = new ArrayList();
		BufferedReader bufferedReader5 = new BufferedReader(fileReader5);
		int counter5 = 0;
		while (counter5 < 7) {
			spot5.add(bufferedReader5.readLine());
			counter5++;
		}
		bufferedReader5.close();
		
		//--------FOR SPOT 6--------
		FileReader fileReader6 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot6.txt");
		ArrayList spot6 = new ArrayList();
		BufferedReader bufferedReader6 = new BufferedReader(fileReader6);
		int counter6 = 0;
		while (counter6 < 7) {
			spot6.add(bufferedReader6.readLine());
			counter6++;
		}
		bufferedReader6.close();
		
		//--------FOR SPOT 7--------
		FileReader fileReader7 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot7.txt");
		ArrayList spot7 = new ArrayList();
		BufferedReader bufferedReader7 = new BufferedReader(fileReader7);
		int counter7 = 0;
		while (counter7 < 7) {
			spot7.add(bufferedReader7.readLine());
			counter7++;
		}
		bufferedReader7.close();
		
		//--------FOR SPOT 8--------
		FileReader fileReader8 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot8.txt");
		ArrayList spot8 = new ArrayList();
		BufferedReader bufferedReader8 = new BufferedReader(fileReader8);
		int counter8 = 0;
		while (counter8 < 7) {
			spot8.add(bufferedReader8.readLine());
			counter8++;
		}
		bufferedReader8.close();
		
		//--------FOR SPOT 9-------
		FileReader fileReader9 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot9.txt");
		ArrayList spot9 = new ArrayList();
		BufferedReader bufferedReader9 = new BufferedReader(fileReader9);
		int counter9 = 0;
		while (counter9 < 7) {
			spot9.add(bufferedReader9.readLine());
			counter9++;
		}	
		bufferedReader9.close();
		
		//--------FOR SPOT 10-------
		FileReader fileReader10 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot10.txt");
		ArrayList spot10 = new ArrayList();
		BufferedReader bufferedReader10 = new BufferedReader(fileReader10);
		int counter10 = 0;
		while (counter10 < 7) {
			spot10.add(bufferedReader10.readLine());
			counter10++;
		}	
		bufferedReader10.close();
		
		//==========================START OF USER INTERFACE SECTION AND COMPUTATION SECTION==========================//
				
		Object[][] allArrays = new Object[10][7];										// creates a new object for all the arrays created as of now
		for (int counter=0; counter<7; counter++) {										// increments through the counter
			allArrays[0][counter] = spot1.get(counter);									// =========================================
			allArrays[1][counter] = spot2.get(counter);									// ==									  ==
			allArrays[2][counter] = spot3.get(counter);									// ==									  ==
			allArrays[3][counter] = spot4.get(counter);									// ==									  ==
			allArrays[4][counter] = spot5.get(counter);									// ==  populates the matrix with values   ==
			allArrays[5][counter] = spot6.get(counter);									// ==	from the arrays used before		  ==
			allArrays[6][counter] = spot7.get(counter);									// ==									  ==
			allArrays[7][counter] = spot8.get(counter);									// ==									  ==
			allArrays[8][counter] = spot9.get(counter);									// ==									  ==
			allArrays[9][counter] = spot10.get(counter);									// =========================================
		}																				// end of loop
		
		System.out.println("Enter your choice of criteria after the prompt...");		// prompt
		System.out.println("1. Closest to Entrance A");									// choices follow
		System.out.println("2. Closest to Entrance B");
		System.out.println("3. Closest to Entrance C");
		System.out.println("4. Closest to Entrance D");
		System.out.println("5. Closest to Entrance E");
		System.out.print("Enter your choice by number ===>>> ");						// actual prompt
		Scanner input = new Scanner(System.in);											// reads in value
		int choiceNumber = input.nextInt();												// assigns value to variable
		System.out.println();
		if (choiceNumber == 1) {														// if the first choice is selected
			System.out.println("The empty spot closest to Entrance A is " + "Spot " + (closestToA(allArrays))[0][0] + ", located on the " + (closestToA(allArrays))[0][1] + " floor");						// uses the module "closestToA" to find the closest empty spot to the request
		}																				// end of loop
		if (choiceNumber == 2) {
			System.out.println("The empty spot closest to Entrance B is " + "Spot " + (closestToB(allArrays))[0][0] + ", located on the " + (closestToB(allArrays))[0][1] + " floor");
		}
		if (choiceNumber == 3) {
			System.out.println("The empty spot closest to Entrance C is " + "Spot " + (closestToC(allArrays))[0][0] + ", located on the " + (closestToC(allArrays))[0][1] + " floor");
		}
		if (choiceNumber == 4) {
			System.out.println("The empty spot closest to Entrance D is " + "Spot " + (closestToD(allArrays))[0][0] + ", located on the " + (closestToD(allArrays))[0][1] + " floor");
		}
		if (choiceNumber == 5) {
			System.out.println("The empty spot closest to Entrance E is " + "Spot " + (closestToE(allArrays))[0][0] + ", located on the " + (closestToE(allArrays))[0][1] + " floor");
		}
	}																					//--------exit out of public static void main--------
	
	/*===========================================================================================================================================*/
	/*===========================================================================================================================================*/
	/*===========================================================================================================================================*/
	
	public static Object[][] closestToA(Object[][] allArrays) {							// module closestToA - finds the closest empty spot to Entrance A
		int closestToSpotA = (Integer.valueOf((String)allArrays[0][1]));				// initializes the variable to the first spot's location ---> can be changed later based on the specific parking structure and which would be the closest if all spots were empty
		Object[][] spotClosestToA = new Object[1][2];									// new object
		// finds the closest distance to Entrance A
		for (int counterA=0; counterA<10; counterA++) {									// incrementing a variable to loop through
			int comparator = (Integer.valueOf((String)allArrays[counterA][1]));			// initializes an incrementing variable
			if (closestToSpotA > comparator) {											// if the current spot is further from Entrance A than the current closest
				if (((String)allArrays[counterA][0]).equals("Empty")) {					// if the spot is empty
					closestToSpotA = comparator;										// the current closest distance becomes the closest spot
				}																		// end if
			}																			// end if
		}																				// end for
		// finds the spot that has the closest distance
		for (int secondCounter=0; secondCounter<10; secondCounter++) {					// incrementing variable
			int comparison = (Integer.valueOf((String)allArrays[secondCounter][1]));	// initializes a comparing variable
			if (closestToSpotA == comparison) {											// if the closest empty distance is equal to the current comparison
				spotClosestToA[0][0] = secondCounter + 1;								// make this the spot number (which is the index of the distance in the array, plus one)
			}																			// end if
			spotClosestToA[0][1]=(allArrays[secondCounter][6]);
		}																				// end for
		return spotClosestToA;															// return the spot closest to Entrance A
	}																					// exit module
	
	public static Object[][] closestToB(Object[][] allArrays) {							// module closestToB - finds the closest empty spot to Entrance B
		int closestToSpotB = (Integer.valueOf((String)allArrays[0][2]));
		Object[][] spotClosestToB = new Object[1][2];
		for (int counterB=0; counterB<10; counterB++) {
			int comparator = (Integer.valueOf((String)allArrays[counterB][2]));
			if (closestToSpotB > comparator) {
				if (((String)allArrays[counterB][0]).equals("Empty")) {
					closestToSpotB = comparator;
				}
			}
		}
		for (int secondCounter=0; secondCounter<10; secondCounter++) {
			int comparison = (Integer.valueOf((String)allArrays[secondCounter][2]));
			if (closestToSpotB == comparison) {
				spotClosestToB[0][0] = secondCounter + 1;
			}
			spotClosestToB[0][1]=(allArrays[secondCounter][6]);
		}
		return spotClosestToB;
	}
	
	public static Object[][] closestToC(Object[][] allArrays) {								// module closestToC - finds the closest empty spot to Entrance C
		int closestToSpotC = (Integer.valueOf((String)allArrays[0][3]));
		Object[][] spotClosestToC = new Object[1][2];
		for (int counterC=0; counterC<10; counterC++) {
			int comparator = (Integer.valueOf((String)allArrays[counterC][3]));
			if (closestToSpotC > comparator) {
				if (((String)allArrays[counterC][0]).equals("Empty")) {
					closestToSpotC = comparator;
				}
			}
		}
		for (int secondCounter=0; secondCounter<10; secondCounter++) {
			int comparison = (Integer.valueOf((String)allArrays[secondCounter][3]));
			if (closestToSpotC == comparison) {
				spotClosestToC[0][0] = secondCounter + 1;
			}
			spotClosestToC[0][1]=(allArrays[secondCounter][6]);
		}
		return spotClosestToC;
	}
	
	public static Object[][] closestToD(Object[][] allArrays) {								// module closestToD - finds the closest empty spot to Entrance D
		int closestToSpotD = (Integer.valueOf((String)allArrays[0][4]));
		Object[][] spotClosestToD = new Object[1][2];
		for (int counterD=0; counterD<10; counterD++) {
			int comparator = (Integer.valueOf((String)allArrays[counterD][4]));
			if (closestToSpotD > comparator) {
				if (((String)allArrays[counterD][0]).equals("Empty")) {
					closestToSpotD = comparator;
				}
			}
		}
		for (int secondCounter=0; secondCounter<10; secondCounter++) {
			int comparison = (Integer.valueOf((String)allArrays[secondCounter][4]));
			if (closestToSpotD == comparison) {
				spotClosestToD[0][0] = secondCounter + 1;
			}
			spotClosestToD[0][1]=(allArrays[secondCounter][6]);
		}
		return spotClosestToD;
	}
	
	public static Object[][] closestToE(Object[][] allArrays) {								// module closestToE - finds the closest empty spot to Entrance E
		int closestToSpotE = (Integer.valueOf((String)allArrays[0][5]));
		Object[][] spotClosestToE = new Object[1][2];
		for (int counterE=0; counterE<10; counterE++) {
			int comparator = (Integer.valueOf((String)allArrays[counterE][5]));
			if (closestToSpotE > comparator) {
				if (((String)allArrays[counterE][0]).equals("Empty")) {
					closestToSpotE = comparator;
				}
			}
		}
		for (int secondCounter=0; secondCounter<10; secondCounter++) {
			int comparison = (Integer.valueOf((String)allArrays[secondCounter][5]));
			if (closestToSpotE == comparison) {
				spotClosestToE[0][0] = secondCounter + 1;
			}
			spotClosestToE[0][1]=(allArrays[secondCounter][6]);
		}
		return spotClosestToE;
	}

}
