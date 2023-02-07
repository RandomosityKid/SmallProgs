
import java.time.*;
import java.util.Scanner;

public class textBasedTimer {
	public static Scanner in = new Scanner(System.in);;
	public static void main(String[] args) {
		
		while (true) {			
			update("9:13");
		}
		
	}

	private static void update(String startTime) {
		LocalTime tNow = LocalTime.now();
//		System.out.println("\n\n" + tNow);
		
		int hchr = Integer.parseInt(startTime.substring(0, startTime.indexOf(":")));
		int hcmin = Integer.parseInt(startTime.substring(startTime.indexOf(":")+1, startTime.length()));
		
		LocalTime hcode = LocalTime.of(hchr, hcmin);
//		System.out.println(hcode);
		
		
		LocalTime nTime = tNow.minusHours(hchr);
//		System.out.println(nTime);
		nTime = nTime.minusMinutes(hcmin);
		
//		System.out.println(nTime);
		

		double hours = nTime.getHour() + (double)nTime.getMinute()/60 + (double)nTime.getSecond()/3600;
//		System.out.println("Min: " + (double)nTime.getMinute());
//		System.out.println("Div: " + (double)nTime.getMinute()/60);
//		System.out.println("Hours: " + hours + "\n");
		
//		System.out.println(nTime.getSecond());

		double result = hours * 18;
		
		double phResult = result * 56.13;
		
//		System.out.println("R1: " + result);
//		System.out.println("R2: " + phResult + "\n");

		System.out.printf("%5s %5.2f %n","R1:",result);
		System.out.printf("%5s %5.2f %n%n","R2:",phResult);
	}
}
