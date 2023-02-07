public class parseTest {
	public static void main(String arge[]) {
		String line = "1 	SMSARYAN AKOP  	SN ";
		
		line = parseLine(line);
		System.out.println(line);
	}

	private static String parseLine(String line) {
		//set variables
		String fName = "";
		String lName = "";
		
		//parsDigit
		char chrArray[] = line.toCharArray();
		String tempLine = "";
		for (int i = 0; i < chrArray.length; i++) {
			if (Character.isLetter(chrArray[i])) {
				tempLine = line.substring(i, chrArray.length);
				System.out.println("digitParsed: " + tempLine);
				break;
			}
		}
		
		//don't need to check for digit, no need for character array
		for (int i = 0; i < tempLine.length() - 1; i++) {
			//if slice is not a space, AND if name == true, add;
			if (!slice(tempLine, i).equalsIgnoreCase(" ")) {
				fName += slice(tempLine, i);
			} else {	//if space, meaning end of first name, run another forloop for last name
				i++;	//skip the space in between
				
				for (int j = i; j < tempLine.length(); j++) {
					//parse last name
					if (!slice(tempLine, j).equalsIgnoreCase(" ")) {
						lName += slice(tempLine, j);
					} else {
						break;	//finished parsing last name, end 
					}
				}
				break;	//break out of loop since previous loop already grabs the last name
			}
		}
//		System.out.println("\n" + tempLine);
//		System.out.println(fName);
//		System.out.println(lName);
		
		return fName + ", " + lName;
	}
	
	private static String slice(String string, int i){
		return string.substring(i, i + 1);
	}
}
