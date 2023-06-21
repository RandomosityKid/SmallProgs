
import static java.lang.Double.sum;

public class looping {
	private static double n1 = 0;
	private static double n2 = 0;
	private static double n3 = 0;
	public static void main(String arge[]) {
		randN();
		while (true) {		
			
			if (true) {
				
			}
			
			n1 = sum(randN(), n3);
			n2 = n1;
			n3 = n2;
			
			System.out.println(n3);
		}
	}

	private static double randN() {
		return -1.0 + (Math.random() * 2);
	}
}
