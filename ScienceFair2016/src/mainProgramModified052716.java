/***
 * @author Kishore Srinivas
 * @since 2016-04-04
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class mainProgramModified052716 {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main (String args[]) throws IOException {
		
		FileInputStream fileReader = new FileInputStream("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\allSpots.txt");		// creates fileReader, a new FileInputStream for the file containing all the spots' information		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileReader));													// creates bufferedReader, a new BufferedReader from a new InputStreamReader of fileReader
		int attributesCount = 0;																												// initializes the attributesCount variable to 0 (this variable will count the number of attributes entered into the input file for each spot)
		int spotsCount = 0;																														// initializes the spotsCount variable to 0 (this variable will count the number of spots entered into the input file)
		
		// assigns values to attributesCount and spotsCount
		bufferedReader.readLine();																												// reads and casts aside the first line of the bufferedReader - it is unnecessary for calculating the attributesCount and spotsCount
		boolean loopFinished = false;																											// initializes loopFinished to false
		while (loopFinished == false) {																											// while loopFinished still equals false
			if (!(bufferedReader.readLine().contains("SPOT"))) {																				// if the line coming into bufferedReader does not contain the regex "SPOT" --> this means it is still counting the attributes of the first spot, and has not reached the next spot yet
				attributesCount++;																												// increment attributesCount by 1
			} else {																															// else ==> if the line does contain the regex "SPOT" --> meaning it has reached the values for the next spot
				loopFinished = true;																											// set loopFinished to true, effectively exiting the while() loop
			}
		}
		// resets the file reading mechanism to the start of the file again
		bufferedReader = new BufferedReader(new InputStreamReader(fileReader));																	// re-initializes bufferedReader so it can start over from the beginning of the file
		fileReader.getChannel().position(0);																									// positions fileReader at position 0 to restart
		bufferedReader.mark(0);																													// marks bufferedReader at index 0
		bufferedReader.reset();																													// resets bufferedReader
		// finds the number of spots by the number of occurrences of the regex "SPOT"
		try {
			DataInputStream in = new DataInputStream(fileReader);																				// creates in, a new DataInputStream of fileReader
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));																	// creates br, a new BufferedReader from a new InputStreamReader of in
		    String strLine;																														// declares string strLine
		    while ((strLine = br.readLine()) != null)   {																						// assigns strLine to br.readLine(), and while that is not null --> while it contains something
		    	int startIndex = strLine.indexOf("SPOT");																						// initializes startIndex to the index of the regex "SPOT" in strLine
		    	while (startIndex != -1) {																										// while startIndex does not equal -1 --> while "SPOT" is found in strLine
		    		spotsCount++;																												// increment spotsCount by 1
		    		startIndex = strLine.indexOf("SPOT", startIndex +"SPOT".length());															// replaces startIndex with the index of the next occurrence of the regex
		    	}
	    	}
		} catch (Exception e) { 																												// catch exception if any
		    System.err.println("Error: " + e.getMessage());																						// print out the error message
		}
		
		Object[][] allArrays = populateLargeArray(bufferedReader, fileReader, spotsCount, attributesCount);
		
		
//============================================ START OF COMPUTATION SECTION ====================================================
		
		if ((findingClosestSpots.closestSpots(allArrays, 0, spotsCount, attributesCount))[0][0] == null) {										// passes the necessary parameters into the closestSpots() method in the findingClosestSpots class, and checks if the first element of the output is null --> indicates that no spots were found by the method and there are no empty spots left
			JOptionPane.showMessageDialog(null, "Sorry, we do not have any empty spots");														// display a JOptionPane messageDialog saying no empty spots are left
		}																					
		else {																																	// else ==> if the closestSpots() method returns something other than null --> meaning there are empty spots left											
			Object[] options = {" 1 ", " 2 ", " 3 ", " 4 ", " 5 "};																				// creates an Object[] options, which will be the options displayed on the following JOptionPane optionDialog window				
			JFrame frame = new JFrame("SpotFinder");																							// creates a new JFrame with the title "SpotFinder"							
			
			// asks the user for their choice and puts the index of their choice into the integer choiceNumber
			int choiceNumber = JOptionPane.showOptionDialog(frame,																				// creates a new JOptionPane optionDialog window, passing its output (the user's selection) to the integer choiceNumber		
					"Select your choice from the following: " + "\n" + "-------------" + "\n\n" +												// message shown starts
					"1. Closest to Exit 1" + "\n" +																								
					"2. Closest to Exit 2" + "\n" +										
					"3. Closest to Exit 3" + "\n" +										
					"4. Closest to Exit 4" + "\n" +										
					"5. Closest to Exit 5" + "\n\n" + 									
					"-------------" + "\n",																										// message shown ends
						"SpotFinder",																											// the title of the frame
						JOptionPane.YES_NO_CANCEL_OPTION,																						// invokes the YES_NO_CANCEL_OPTION method of the JOptionPane optionDialog window
						JOptionPane.QUESTION_MESSAGE,																							// invokes the QUESTION_MESSAGE method of the JOptionPane optionDialog window
						null,																													// sets the custom frame to null --> this will cause it to use the default frame, and not create a new one
						options,																												// starts displaying the options																
						options[4]);																											// displays through the element of index 4 of options
		
			Object[] options2 = {"Yes, please", "Show me the next spot"};						
			boolean messageDisplayed = false;													
			boolean foundSpotToReserve = false;													
			for (int threeSpots = 0; threeSpots < 3; threeSpots++) {							
				if (!((findingClosestSpots.closestSpots(allArrays, choiceNumber, spotsCount, attributesCount))[threeSpots][0]==null) &&
						foundSpotToReserve == false &&												
						messageDisplayed == false) {											
					ImageIcon imgIcon = new ImageIcon("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsMaps\\mapSpot" + 		
						(findingClosestSpots.closestSpots(allArrays, choiceNumber, spotsCount, attributesCount))[threeSpots][0] + 
						".jpg");																												
					JOptionPane.showMessageDialog(null,																							
							"Closest empty spot: Spot " + (findingClosestSpots.closestSpots
									(allArrays, choiceNumber, spotsCount, attributesCount))[threeSpots][0] + "\n" +			
							"Location: Floor " + (findingClosestSpots.closestSpots
									(allArrays, choiceNumber, spotsCount, attributesCount))[threeSpots][1] + "\n" +					
							"Distance from Exit " + (choiceNumber+1) + ": " + 
								(findingClosestSpots.closestSpots(allArrays, choiceNumber, spotsCount, attributesCount))[threeSpots][2] + 
								" meters" + "\n\n",	
							"SpotFinder",																										
							JOptionPane.INFORMATION_MESSAGE, imgIcon);																			
					int reserveRequest = JOptionPane.showOptionDialog(null,																	
							"If you would like to reserve this spot, select yes." + "\n" +														
							"If you would like to see the next closest spot, select the other button.",											
							"SpotFinder",																										
							JOptionPane.YES_NO_CANCEL_OPTION,																					
							JOptionPane.QUESTION_MESSAGE,																						
							null,																												
						options2,																												
							options2[1]);																										
					if (reserveRequest == 0) {																									
						updatingSpotInfo.reserveSpot(Integer.valueOf(
								((findingClosestSpots.closestSpots(allArrays, choiceNumber, spotsCount, attributesCount))[threeSpots][0])
								.toString()), 
								attributesCount, spotsCount);																					
						foundSpotToReserve = true;																								
					} else {																													
						JOptionPane.showMessageDialog(null, "---- Spot not reserved ----");														
					}																												
				} else if (foundSpotToReserve == true) {																						
					//nothing to do
				} else {																														
					JOptionPane.showMessageDialog(null, "No further spots");																	
					messageDisplayed = true;																									
				}																																
			}
		}
	}
	
	public static Object[][] populateLargeArray(BufferedReader bufferedReader, FileInputStream fileReader, int spotsCount, int attributesCount) throws IOException {
		bufferedReader = new BufferedReader(new InputStreamReader(fileReader));
		fileReader.getChannel().position(0);
		bufferedReader.mark(0);
		bufferedReader.reset();
		// populates the 2D array with the spots' information
		Object[][] allArrays = new Object[spotsCount][attributesCount+1];																		// initializes 2D Object allArrays, with spotsCount number of columns and attributesCount+1 number of rows
		for (int a = 0; a < spotsCount; a++) {																									// during the time that a is less than the number of spots
			for (int b = 0; b <= attributesCount; b++) {																						// during the time that b is less than or equal to the number of spots
				allArrays[a][b] = bufferedReader.readLine().toString();																			// set allArrays[a][b] to the next, and corresponding, line from bufferedReader
			}
		}
		return allArrays;
	}
		/*/*
		
		//========================== START OF USER INTERFACE SECTION AND COMPUTATION SECTION ==========================//	
			
			if (choiceNumber == 0) {																// if the first choice is selected						
				Object[] options2 = {"Yes, please", "Show me the next spot"};						// sets the options to be displayed
				boolean messageDisplayed = false;													// boolean message has not been displayed
				boolean foundSpotToReserve = false;													// boolean spot to reserve has not been found
				for (int threeSpots = 0; threeSpots < 3; threeSpots++) {							// incrementing variable for the three spots
					if (!((findingClosestSpots.closestToA(allArrays))[threeSpots][0]==null) && 		// if the input this section is not null
							foundSpotToReserve == false &&											// and if we have not found the spot we want to reserve yet	
							messageDisplayed == false) {											// and if the message has not yet been displayed
						ImageIcon imgIcon = new ImageIcon("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsMaps\\mapSpot" + 		
							(findingClosestSpots.closestToA(allArrays))[threeSpots][0] + 
							".jpg");																// create the image as the map of that particular spot
						JOptionPane.showMessageDialog(null,																					// informs the user of the spot pick
								"Closest empty spot: Spot " + (findingClosestSpots.closestToA(allArrays))[threeSpots][0] + "\n" +			// shows the spot number
								"Location: Floor " + (findingClosestSpots.closestToA(allArrays))[threeSpots][1] + "\n" +					// shows the spot floor
								"Distance from Exit A: " + (findingClosestSpots.closestToA(allArrays))[threeSpots][2] + " meters" + "\n\n",	// shows the distance from the requested entrance
								"SpotFinder",																								// title for the JOptionPane
								JOptionPane.INFORMATION_MESSAGE, imgIcon);																	// displays the map
						int reserveRequest = JOptionPane.showOptionDialog(null,															// gives the option to reserve the spot
								"If you would like to reserve this spot, select yes." + "\n" +											// prompt
								"If you would like to see the next closest spot, select the other button.",								// prompt
								"SpotFinder",																							// title for the JOptionPane
								JOptionPane.YES_NO_CANCEL_OPTION,																		// options
								JOptionPane.QUESTION_MESSAGE,																			// further necessary syntax
								null,																									// other necessary syntax
								options2,																								// the options prepared before
								options2[1]);																							// the options prepared before
						if (reserveRequest == 0) {																							// if they request to reserve the spot
							updatingSpotInfo.reserveSpot(String.valueOf((findingClosestSpots.closestToA(allArrays))[threeSpots][0]));		// run the module for reserve that spot
							foundSpotToReserve = true;																						// turn the variable to true, meaning we have found the spot to reserve, and no further spots will be shown
						} else {																										// else
							JOptionPane.showMessageDialog(null, "---- Spot not reserved ----");											// inform the user that the spot has not been reserved
						}																												
					} else if (foundSpotToReserve == true) {																				// else if - for the big if
						//nothing to do
					} else {																												// else - for the big if
						JOptionPane.showMessageDialog(null, "No further spots");															// inform that no other spots exist
						messageDisplayed = true;																							// let the program know the message has been displayed, so it won't display it again
					}																														// end else - of the big if
				}																														// end for
			}																																// end module
																												
		if (choiceNumber == 1) {																	// if the second choice is selected	
			Object[] options2 = {"Yes, please", "Show me the next spot"};
			boolean messageDisplayed = false;
			boolean foundSpotToReserve = false;
			for (int threeSpots = 0; threeSpots < 3; threeSpots++) {
				if (!((findingClosestSpots.closestToB(allArrays))[threeSpots][0]==null) && 
						foundSpotToReserve == false &&
						messageDisplayed == false) {
					ImageIcon imgIcon = new ImageIcon("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsMaps\\mapSpot" + (findingClosestSpots.closestToB(allArrays))[threeSpots][0] + ".jpg");
					JOptionPane.showMessageDialog(null,
							"Closest empty spot: Spot " + (findingClosestSpots.closestToB(allArrays))[threeSpots][0] + "\n" +
							"Location: Floor " + (findingClosestSpots.closestToB(allArrays))[threeSpots][1] + "\n" +
							"Distance from Exit A: " + (findingClosestSpots.closestToB(allArrays))[threeSpots][2] + " meters" + "\n\n",
							"SpotFinder",
							JOptionPane.INFORMATION_MESSAGE, imgIcon);
					int reserveRequest = JOptionPane.showOptionDialog(null,
							"If you would like to reserve this spot, select yes." + "\n" +
							"If you would like to see the next closest spot, select the other button.",
							"SpotFinder",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options2,
							options2[1]);
					if (reserveRequest == 0) {
						updatingSpotInfo.reserveSpot(String.valueOf((findingClosestSpots.closestToB(allArrays))[threeSpots][0]));
						foundSpotToReserve = true;
					} else {
						JOptionPane.showMessageDialog(null, "---- Spot not reserved ----");
					}
				} else if (foundSpotToReserve == true) {
					//nothing to do
				} else {
					JOptionPane.showMessageDialog(null, "No further spots");
					messageDisplayed = true;
				}
			}	
			}
				
			if (choiceNumber == 2) {																// if the third choice is selected
				Object[] options2 = {"Yes, please", "Show me the next spot"};
				boolean messageDisplayed = false;
				boolean foundSpotToReserve = false;
				for (int threeSpots = 0; threeSpots < 3; threeSpots++) {
					if (!((findingClosestSpots.closestToC(allArrays))[threeSpots][0]==null) && 
							foundSpotToReserve == false &&
							messageDisplayed == false) {
						ImageIcon imgIcon = new ImageIcon("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsMaps\\mapSpot" + (findingClosestSpots.closestToC(allArrays))[threeSpots][0] + ".jpg");
						JOptionPane.showMessageDialog(null,
								"Closest empty spot: Spot " + (findingClosestSpots.closestToC(allArrays))[threeSpots][0] + "\n" +
								"Location: Floor " + (findingClosestSpots.closestToC(allArrays))[threeSpots][1] + "\n" +
								"Distance from Exit A: " + (findingClosestSpots.closestToC(allArrays))[threeSpots][2] + " meters" + "\n\n",
								"SpotFinder",
								JOptionPane.INFORMATION_MESSAGE, imgIcon);
						int reserveRequest = JOptionPane.showOptionDialog(null,
								"If you would like to reserve this spot, select yes." + "\n" +
								"If you would like to see the next closest spot, select the other button.",
								"SpotFinder",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options2,
								options2[1]);
						if (reserveRequest == 0) {
							updatingSpotInfo.reserveSpot(String.valueOf((findingClosestSpots.closestToC(allArrays))[threeSpots][0]));
							foundSpotToReserve = true;
						} else {
							JOptionPane.showMessageDialog(null, "---- Spot not reserved ----");
						}
					} else if (foundSpotToReserve == true) {
						//nothing to do
					} else {
						JOptionPane.showMessageDialog(null, "No further spots");
						messageDisplayed = true;
					}
				}		
			}
			
			if (choiceNumber == 3) {																// if the fourth choice is selected
				Object[] options2 = {"Yes, please", "Show me the next spot"};
				boolean messageDisplayed = false;
				boolean foundSpotToReserve = false;
				for (int threeSpots = 0; threeSpots < 3; threeSpots++) {
					if (!((findingClosestSpots.closestToD(allArrays))[threeSpots][0]==null) && 
							foundSpotToReserve == false &&
							messageDisplayed == false) {
						ImageIcon imgIcon = new ImageIcon("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsMaps\\mapSpot" + (findingClosestSpots.closestToD(allArrays))[threeSpots][0] + ".jpg");
						JOptionPane.showMessageDialog(null,
								"Closest empty spot: Spot " + (findingClosestSpots.closestToD(allArrays))[threeSpots][0] + "\n" +
								"Location: Floor " + (findingClosestSpots.closestToD(allArrays))[threeSpots][1] + "\n" +
								"Distance from Exit A: " + (findingClosestSpots.closestToD(allArrays))[threeSpots][2] + " meters" + "\n\n",
								"SpotFinder",
								JOptionPane.INFORMATION_MESSAGE, imgIcon);
						int reserveRequest = JOptionPane.showOptionDialog(null,
								"If you would like to reserve this spot, select yes." + "\n" +
								"If you would like to see the next closest spot, select the other button.",
								"SpotFinder",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options2,
								options2[1]);
						if (reserveRequest == 0) {
							updatingSpotInfo.reserveSpot(String.valueOf((findingClosestSpots.closestToD(allArrays))[threeSpots][0]));
							foundSpotToReserve = true;
						} else {
							JOptionPane.showMessageDialog(null, "---- Spot not reserved ----");
						}
					} else if (foundSpotToReserve == true) {
						//nothing to do
					} else {
						JOptionPane.showMessageDialog(null, "No further spots");
						messageDisplayed = true;
					}
				}		
			}
			
			if (choiceNumber == 4) {																// if the fifth choice is selected
				Object[] options2 = {"Yes, please", "Show me the next spot"};
				boolean messageDisplayed = false;
				boolean foundSpotToReserve = false;
				for (int threeSpots = 0; threeSpots < 3; threeSpots++) {
					if (!((findingClosestSpots.closestToE(allArrays))[threeSpots][0]==null) && 
							foundSpotToReserve == false &&
							messageDisplayed == false) {
						ImageIcon imgIcon = new ImageIcon("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsMaps\\mapSpot" + (findingClosestSpots.closestToE(allArrays))[threeSpots][0] + ".jpg");
						JOptionPane.showMessageDialog(null,
								"Closest empty spot: Spot " + (findingClosestSpots.closestToE(allArrays))[threeSpots][0] + "\n" +
								"Location: Floor " + (findingClosestSpots.closestToE(allArrays))[threeSpots][1] + "\n" +
								"Distance from Exit A: " + (findingClosestSpots.closestToE(allArrays))[threeSpots][2] + " meters" + "\n\n",
								"SpotFinder",
								JOptionPane.INFORMATION_MESSAGE, imgIcon);
						int reserveRequest = JOptionPane.showOptionDialog(null,
								"If you would like to reserve this spot, select yes." + "\n" +
								"If you would like to see the next closest spot, select the other button.",
								"SpotFinder",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options2,
								options2[1]);
						if (reserveRequest == 0) {
							updatingSpotInfo.reserveSpot(String.valueOf((findingClosestSpots.closestToE(allArrays))[threeSpots][0]));
							foundSpotToReserve = true;
						} else {
							JOptionPane.showMessageDialog(null, "---- Spot not reserved ----");
						}
					} else if (foundSpotToReserve == true) {
						//nothing to do
					} else {
						JOptionPane.showMessageDialog(null, "No further spots");
						messageDisplayed = true;
					}
				}		
			}												
		}																				// end the big else		
	}											*/										// end main
}																						// end class	