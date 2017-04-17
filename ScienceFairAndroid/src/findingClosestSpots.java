/***
 * @author Kishore Srinivas
 * @since 2016-04-04
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

public class findingClosestSpots {
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
}