/***
 * @author Kishore Srinivas
 * @since 2016-04-04
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class updatingSpotInfo {

	public static void main(String[] args) {
						 																							// Nothing to do in the main
	}

	public static void unreserveSpot(final int spotNumber, final int numberOfAttributes, final int numberOfSpots, final ArrayList<String> linesOfFile) throws java.io.IOException, java.io.FileNotFoundException {
		class UpdaterThread extends Thread {
			@Override
			public void run() {
				try {
					String fileToModify = "C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\allSpots.txt";
					int numberOfSleeps = 0;
					while (numberOfSleeps < 30) {
					FileInputStream fileReader = new FileInputStream("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\allSpots.txt");
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileReader));
					linesOfFile.clear();
					for (int a = 0; a < ((numberOfAttributes+1)*numberOfSpots); a++) {
						linesOfFile.add(bufferedReader.readLine());
					}
					bufferedReader.close();
					Thread.sleep(1000);
					numberOfSleeps++;
					}
					FileWriter fileWriter = new FileWriter(fileToModify);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					
					int spotFilled = -1;
					for (int i = 0; i < linesOfFile.size(); i++) {
						if (linesOfFile.get(i).toLowerCase().equals("spot " + spotNumber) && linesOfFile.get(i + 1).toLowerCase().contains("reserved")) {
							linesOfFile.set(i + 1, "Empty");
						} else if (linesOfFile.get(i).toLowerCase().contains("spot " + spotNumber) && !linesOfFile.get(i + 1).toLowerCase().contains("reserved")) {
							Object choices[] = {"Oh, ok", "Report this"};
							spotFilled = JOptionPane.showOptionDialog(null,
									"We are sorry but Spot " + spotNumber + ", which you had reserved has been improperly taken by another individual.",
									"SpotFinder",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null,
									choices,
									choices[1]);
							if (spotFilled == 0) {
								System.out.println(linesOfFile.size());
								for (int a = 0; a < linesOfFile.size(); a++) {
									bufferedWriter.write(linesOfFile.get(a));
									bufferedWriter.newLine();
								}
								bufferedWriter.close();
								System.exit(0);
							} else if (spotFilled == 1) {
								FileWriter fw = new FileWriter("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\improperlyTakenSpots.txt", true);
								BufferedWriter bw = new BufferedWriter(fw);
								PrintWriter pw = new PrintWriter(bw);
								java.util.Date date = new java.util.Date();
								pw.println("Spot " + spotNumber + " | " + date);
								JOptionPane.showMessageDialog(null, "Incident has been reported.");
							}
						}

						bufferedWriter.write(linesOfFile.get(i));
						bufferedWriter.newLine();
					}
					bufferedWriter.close();
					if (spotFilled < 0) {
						Object[] options = {"Okay", "Let me reserve it again"};									
	               		int reserveAgain = JOptionPane.showOptionDialog(null, 									
	               				"Your reservation of Spot " + spotNumber + " has been cancelled due to exceeding the time limit!",
	               				"SpotFinder",
	               				JOptionPane.YES_NO_CANCEL_OPTION,
	    						JOptionPane.QUESTION_MESSAGE,
	    						null,
	    						options,
	    						options[1]);
	               		if (reserveAgain == 1) {																
	               			updatingSpotInfo.reserveSpot(spotNumber, numberOfAttributes, numberOfSpots);															
	               		} else {
	               			System.exit(0);
	               		}
					} else {
						System.exit(0);
					}
				} catch (InterruptedException ex) {
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	Thread unreserveThread = new UpdaterThread();
	unreserveThread.start();
	}
	
	public static void reserveSpot(int spotNumber, int numberOfAttributes, int numberOfSpots) throws IOException {
		String fileToModify = "C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsInformation\\allSpots.txt";
		FileReader fileReader = new FileReader(fileToModify);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		ArrayList<String> linesOfFile = new ArrayList<String>();
		for (int a = 0; a < ((numberOfAttributes+1)*numberOfSpots); a++) {
			linesOfFile.add(bufferedReader.readLine());
		}
		bufferedReader.close();
		
		FileWriter fileWriter = new FileWriter(fileToModify);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		if (linesOfFile.get((spotNumber-1)*(numberOfAttributes+1)+1).toLowerCase().equals("empty")) {
			linesOfFile.set((spotNumber-1)*(numberOfAttributes+1)+1, "Reserved");
			for (int i = 0; i < linesOfFile.size(); i++) {
				bufferedWriter.write(linesOfFile.get(i));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			
			JOptionPane.showMessageDialog(null,																		
					"Spot " + spotNumber + " has been reserved." + "\n" +											
					"You have 5 minutes to park there, before you lose your reservation!" + "\n" +					
					"--------------");
			updatingSpotInfo.unreserveSpot(spotNumber, numberOfAttributes, numberOfSpots, linesOfFile);
		} else {																										
			Object[] options = {"Ok, then", "Let me try again..."};
			int retryReservation = JOptionPane.showOptionDialog(null,
				"---- Spot not empty ----" + "\n" +
				"Sorry, Spot " + spotNumber + " could not be reserved" + "\n\n",
					"SpotFinder",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[1]);
			if (retryReservation == 1) {
				updatingSpotInfo.reserveSpot(spotNumber, numberOfAttributes, numberOfSpots);
			}
		}
	}
}
