/*public class mainProgram {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main (String args[]) throws IOException {
		
		//--------FOR SPOT 1--------
		FileReader fileReader1 = new FileReader("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\spot1.txt");// sets the input file
		ArrayList spot1 = new ArrayList(); 																					// creates a new array of objects, to hold multiple data types
		BufferedReader bufferedReader1 = new BufferedReader(fileReader1); 													// creates a new buffered reader and reads from the file
		int counter1 = 0; 																									// creates a counter variable to increment
		while (counter1 < 7) { 																								// starts the loops based on counter value
			spot1.add(bufferedReader1.readLine()); 							 												// fills the object array with the information from the file by reading the lines
			counter1++; 																									// increments counter variable
		} 																													// end of loop
		bufferedReader1.close();																							// close buffered reader
		
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
			allArrays[9][counter] = spot10.get(counter);								// =========================================
		}																				// end of loop
		
		if ((findingClosestSpots.closestToA(allArrays))[0][0] == null) {					// if the array with empty spots is empty - if there is no empty spot
			System.out.println((findingClosestSpots.closestToA(allArrays))[0][0]);
			JOptionPane.showMessageDialog(null, "Sorry, we do not have any empty spots");	// print to standard out
		}																					// end if
		else {																				// else - that is, if there is an empty spot (yay!)
			Object[] options = {" 1 ", " 2 ", " 3 ", " 4 ", " 5 "};							// the options which will be used to ask the user for their criteria
			JFrame frame = new JFrame("SpotFinder");										// a title - which will stay the same throughout the user experience
			int choiceNumber = JOptionPane.showOptionDialog(frame,							// a new JOptionPane
					"Select your choice from the following: " + "\n" + "-------------" + "\n\n" +	// prompt
					"1. Closest to Exit A" + "\n" +										// ============================================================
					"2. Closest to Exit B" + "\n" +										// ===													   ===
					"3. Closest to Exit C" + "\n" +										// === the various choices of criteria they can choose from ===
					"4. Closest to Exit D" + "\n" +										// ===													   ===
					"5. Closest to Exit E" + "\n\n" + 									// ============================================================
					"-------------" + "\n",
						"SpotFinder",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,															// present the options
						options[4]);		
			
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
	}																					// end main
}		*/																				// end class	