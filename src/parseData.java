
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class parseData {
	public static Thread t;
	
	public static String path = "C:\\Users\\DC\\Desktop\\GogaCode\\dataParse.txt";
	public static File file = new File(path);
	
	public static ArrayList<Object> list = new ArrayList<>();
	
	
	public static void main(String arge[]) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		
		ArrayList<Object> tempList = new ArrayList<>();
		ArrayList<String> dx1 = new ArrayList<>();
		ArrayList<String> dx2 = new ArrayList<>();
		
		while (scan.hasNextLine()) {	
			
			String line = scan.nextLine().trim();
			if (!line.isBlank()) {
				String fline = slice(line, 0);
				System.out.println("line: " + line);
				
				if (isNumber(fline)) {	//if starting new list, dump data to arrayList if not empty
					//dump data if aList is not empty
					if (!list.isEmpty()) {
						tempList.add(dx1);
						tempList.add(dx2);
						
						list.add(tempList);
					}
					
					//clear aLists for new entry
					tempList.clear();
					dx1.clear();
					dx2.clear();
					
					//start new aList
					tempList.add(parseLine(line));
				}
			}
			
		}
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
			//if slice is not a space, add;
			if (!slice(tempLine, i).equalsIgnoreCase(" ")) {
				fName += slice(tempLine, i);
			} else {	//if space, meaning end of first name, run another forloop for last name
				i++;	//skip the space in between
				
				for (int j = i; j < tempLine.length(); j++) {	//j = i to continue last index
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
		return fName + ", " + lName;
	}
	
	private static String slice(String string, int i){
		return string.substring(i, i + 1);
	}
	
	private static boolean isNumber(String fline) {
		try {
			int i = Integer.parseInt(fline);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	
}
