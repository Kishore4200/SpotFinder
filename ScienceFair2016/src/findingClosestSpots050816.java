/***
 * @author Kishore Srinivas
 * @since 2016-04-04
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

public class findingClosestSpots050816 {
	public static void main (String args[]) {
		
	}

	public static Object[][] closestSpots(Object[][] allArrays, int exitNumber, int numberOfSpots, int attributeCount) {
		Object[][] closestSpots = new Object[3][3];
		ArrayList<Integer> displayedSpots = new ArrayList<Integer>();
		float closestToGivenExit = Float.valueOf((String)allArrays[0][2+exitNumber]);
		float farthestFromGivenExit = Float.valueOf((String)allArrays[0][2+exitNumber]);
		
		for (int i = 0; i < numberOfSpots; i++) {
			if (Float.valueOf((String)allArrays[i][2+exitNumber]) < closestToGivenExit &&							
					((String)allArrays[i][1]).toLowerCase().equals("empty")) {
				closestToGivenExit = Float.valueOf((String)allArrays[i][2+exitNumber]);
			}
			if (Float.valueOf((String)allArrays[i][2+exitNumber]) > farthestFromGivenExit &&							
					((String)allArrays[i][1]).toLowerCase().equals("empty")) {
				farthestFromGivenExit = Float.valueOf((String)allArrays[i][2+exitNumber]);
			}
		}
		int fillingCounter = 0;																				
		// finds the three closest empty spots and updates the two dimensional array
		for (double j = closestToGivenExit; j <= farthestFromGivenExit; j += 0.01) {							
			DecimalFormat df = new DecimalFormat("#.##");													
			String jS = df.format(j);																		
			for (int counter = 0; counter < numberOfSpots; counter++) {										
				if (((String)allArrays[counter][1]).toLowerCase().equals("empty") &&						
						(Float.valueOf((String)allArrays[counter][2+exitNumber]).equals(Float.valueOf(jS))) &&		
						displayedSpots.size() <= 3 &&														
						!displayedSpots.contains(counter) &&												
						fillingCounter < 3) {																
					// fills the whole row of the two dimensional array
					closestSpots[fillingCounter][0] = counter + 1;											
					closestSpots[fillingCounter][1] = allArrays[counter][attributeCount];								
					closestSpots[fillingCounter][2] = Float.valueOf((String)allArrays[counter][2+exitNumber]);			
					displayedSpots.add(counter);															
					fillingCounter++;																		
				}																							
			}																								
		}
		
		return closestSpots;
	}
	
	/*public static Object[][] closestToA(Object[][] allArrays) {												// module closestToA - finds the THREE closest spots to exit A
		float closestToExitA = Float.valueOf((String)allArrays[0][1]);										// initializes the closest spot as the first element in the array
		float farthestFromExitA = Float.valueOf((String)allArrays[0][1]);									// initializes the farthest spot as the first element in the array as well
		ArrayList<Integer> displayedSpots = new ArrayList<Integer>();										// creates an empty array list, in which the spots that have been already displayed will be stored, so they will not be displayed again
		Object[][] spotsClosestToA = new Object[3][3];														// creates an empty two dimensional array, which is what the whole module will return
		
		// finds the closest and farthest of all the spots
		for (int i = 0; i < 10; i++) {																		// incrementing variable
			if (Float.valueOf((String)allArrays[i][1]) < closestToExitA &&									// if the spot we are looking at is closer than the current closest
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {								// and if the spot is empty
				closestToExitA = Float.valueOf((String)allArrays[i][1]);									// update the closest to match
			}																								// end if
			if (Float.valueOf((String)allArrays[i][1]) > farthestFromExitA &&								// if the spot we are looking at is farther than the current farthest
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {								// and if the spot is empty
				farthestFromExitA = Float.valueOf((String)allArrays[i][1]);									// update the farthest to match
			}																								// end if
		}																									// end for
		int fillingCounter = 0;																				// initializes fillingCounter to 0 ==> this variable will be very important later
		// finds the three closest empty spots and updates the two dimensional array
		for (double j = closestToExitA; j <= farthestFromExitA; j += 0.01) {								// incrementing variable -> this will increment the j variable, which is the comparison for the spots
			DecimalFormat df = new DecimalFormat("#.##");													// creates a new decimal format, which will be used to limit the scope of the search for the closest spots
			String jS = df.format(j);																		// formats the incrementing variable to the desired form
			for (int counter = 0; counter < 10; counter++) {												// another incrementing variable -> this will peruse all the spots
				if (((String)allArrays[counter][0]).toLowerCase().equals("empty") &&						// if the spot is empty - just another measure of security, to ensure no non-empty spots slip by
						(Float.valueOf((String)allArrays[counter][1]).equals(Float.valueOf(jS))) &&			// and if the distance looking at equals the jS variable
						displayedSpots.size() <= 3 &&														// and if the size of the array list is less than or equal to 3 - that is, if we are still looking for spots
						!displayedSpots.contains(counter) &&												// and if this spot has not been displayed already
						fillingCounter < 3) {																// and if the two dimensional array has not been completely filled yet
					// fills the whole row of the two dimensional array
					spotsClosestToA[fillingCounter][0] = counter + 1;										// fills the first cell with the spot number
					spotsClosestToA[fillingCounter][1] = allArrays[counter][6];								// fills the second cell with the floor number
					spotsClosestToA[fillingCounter][2] = Float.valueOf((String)allArrays[counter][1]);		// fills the third cell with the distance from the entrance
					displayedSpots.add(counter);															// adds the spot to the list of already displayed spots
					fillingCounter++;																		// increments fillingCounter, so subsequent spots can be updated into the two dimensional array
				}																							// end if
			}																								// end for
		}																									// end for
		return spotsClosestToA;																				// returns the two dimensional array
	}																										// end module
	
	public static Object[][] closestToB(Object[][] allArrays) {												// module closestToB - finds the THREE closest spots to exit B
		float closestToExitB = Float.valueOf((String)allArrays[0][2]);
		float farthestFromExitB = Float.valueOf((String)allArrays[0][2]);
		ArrayList<Integer> displayedSpots = new ArrayList<Integer>();
		Object[][] spotsClosestToB = new Object[3][3];
		
		for (int i = 0; i < 10; i++) {
			if (Float.valueOf((String)allArrays[i][2]) < closestToExitB &&
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {
				closestToExitB = Float.valueOf((String)allArrays[i][2]);
			}
			if (Float.valueOf((String)allArrays[i][2]) > farthestFromExitB &&
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {
				farthestFromExitB = Float.valueOf((String)allArrays[i][2]);
			}
		}
		int fillingCounter = 0;
		for (double j = closestToExitB; j <= farthestFromExitB; j += 0.01) {
			DecimalFormat df = new DecimalFormat("#.##");
			String jS = df.format(j);
			for (int counter = 0; counter < 10; counter++) {
				if (((String)allArrays[counter][0]).toLowerCase().equals("empty") &&
						(Float.valueOf((String)allArrays[counter][2]).equals(Float.valueOf(jS))) &&
						displayedSpots.size() <= 3 &&
						!displayedSpots.contains(counter) &&
						fillingCounter < 3) {
					spotsClosestToB[fillingCounter][0] = counter + 1;
					spotsClosestToB[fillingCounter][1] = allArrays[counter][6];
					spotsClosestToB[fillingCounter][2] = Float.valueOf((String)allArrays[counter][2]);
					displayedSpots.add(counter);
					fillingCounter++;
				}
			}
		}
		return spotsClosestToB;
	}
	
	public static Object[][] closestToC(Object[][] allArrays) {												// module closestToC - finds the THREE closest spots to exit C
		float closestToExitC = Float.valueOf((String)allArrays[0][3]);
		float farthestFromExitC = Float.valueOf((String)allArrays[0][3]);
		ArrayList<Integer> displayedSpots = new ArrayList<Integer>();
		Object[][] spotsClosestToC = new Object[3][3];
		
		for (int i = 0; i < 10; i++) {
			if (Float.valueOf((String)allArrays[i][3]) < closestToExitC &&
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {
				closestToExitC = Float.valueOf((String)allArrays[i][3]);
			}
			if (Float.valueOf((String)allArrays[i][3]) > farthestFromExitC &&
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {
				farthestFromExitC = Float.valueOf((String)allArrays[i][3]);
			}
		}
		int fillingCounter = 0;
		for (double j = closestToExitC; j <= farthestFromExitC; j += 0.01) {
			DecimalFormat df = new DecimalFormat("#.##");
			String jS = df.format(j);
			for (int counter = 0; counter < 10; counter++) {
				if (((String)allArrays[counter][0]).toLowerCase().equals("empty") &&
						(Float.valueOf((String)allArrays[counter][3]).equals(Float.valueOf(jS))) &&
						displayedSpots.size() <= 3 &&
						!displayedSpots.contains(counter) &&
						fillingCounter < 3) {
					spotsClosestToC[fillingCounter][0] = counter + 1;
					spotsClosestToC[fillingCounter][1] = allArrays[counter][6];
					spotsClosestToC[fillingCounter][2] = Float.valueOf((String)allArrays[counter][3]);
					displayedSpots.add(counter);
					fillingCounter++;
				}
			}
		}
		return spotsClosestToC;
	}
	
	public static Object[][] closestToD(Object[][] allArrays) {												// module closestToD - finds the THREE closest spots to exit D
		float closestToExitD = Float.valueOf((String)allArrays[0][4]);
		float farthestFromExitD = Float.valueOf((String)allArrays[0][4]);
		ArrayList<Integer> displayedSpots = new ArrayList<Integer>();
		Object[][] spotsClosestToD = new Object[3][3];
		
		for (int i = 0; i < 10; i++) {
			if (Float.valueOf((String)allArrays[i][4]) < closestToExitD &&
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {
				closestToExitD = Float.valueOf((String)allArrays[i][4]);
			}
			if (Float.valueOf((String)allArrays[i][4]) > farthestFromExitD &&
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {
				farthestFromExitD = Float.valueOf((String)allArrays[i][4]);
			}
		}
		int fillingCounter = 0;
		for (double j = closestToExitD; j <= farthestFromExitD; j += 0.01) {
			DecimalFormat df = new DecimalFormat("#.##");
			String jS = df.format(j);
			for (int counter = 0; counter < 10; counter++) {
				if (((String)allArrays[counter][0]).toLowerCase().equals("empty") &&
						(Float.valueOf((String)allArrays[counter][4]).equals(Float.valueOf(jS))) &&
						displayedSpots.size() <= 3 &&
						!displayedSpots.contains(counter) &&
						fillingCounter < 3) {
					spotsClosestToD[fillingCounter][0] = counter + 1;
					spotsClosestToD[fillingCounter][1] = allArrays[counter][6];
					spotsClosestToD[fillingCounter][2] = Float.valueOf((String)allArrays[counter][4]);
					displayedSpots.add(counter);
					fillingCounter++;
				}
			}
		}
		return spotsClosestToD;
	}
	
	public static Object[][] closestToE(Object[][] allArrays) {												// module closestToE - finds the THREE closest spots to exit E
		float closestToExitE = Float.valueOf((String)allArrays[0][5]);
		float farthestFromExitE = Float.valueOf((String)allArrays[0][5]);
		ArrayList<Integer> displayedSpots = new ArrayList<Integer>();
		Object[][] spotsClosestToE = new Object[3][3];
		
		for (int i = 0; i < 10; i++) {
			if (Float.valueOf((String)allArrays[i][5]) < closestToExitE &&
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {
				closestToExitE = Float.valueOf((String)allArrays[i][5]);
			}
			if (Float.valueOf((String)allArrays[i][5]) > farthestFromExitE &&
					((String)allArrays[i][0]).toLowerCase().equals("empty")) {
				farthestFromExitE = Float.valueOf((String)allArrays[i][5]);
			}
		}
		int fillingCounter = 0;
		for (double j = closestToExitE; j <= farthestFromExitE; j += 0.01) {
			DecimalFormat df = new DecimalFormat("#.##");
			String jS = df.format(j);
			for (int counter = 0; counter < 10; counter++) {
				if (((String)allArrays[counter][0]).toLowerCase().equals("empty") &&
						(Float.valueOf((String)allArrays[counter][5]).equals(Float.valueOf(jS))) &&
						displayedSpots.size() <= 3 &&
						!displayedSpots.contains(counter) &&
						fillingCounter < 3) {
					spotsClosestToE[fillingCounter][0] = counter + 1;
					spotsClosestToE[fillingCounter][1] = allArrays[counter][6];
					spotsClosestToE[fillingCounter][2] = Float.valueOf((String)allArrays[counter][5]);
					displayedSpots.add(counter);
					fillingCounter++;
				}
			}
		}
		return spotsClosestToE;
	} */
}