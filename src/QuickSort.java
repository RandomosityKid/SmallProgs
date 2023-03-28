public class QuickSort {
	public static void main(String arge[]) {
		String a = "Car";
		String b = "Apple";
		String c = "Banana";
		
		System.out.println(getMedian(a,b,c));
		
	}

	private static String getMedian(String a, String b, String c) {
		int x = a.compareToIgnoreCase(b);
		int y = b.compareToIgnoreCase(c);
		int z = a.compareToIgnoreCase(c);
		
		if(x*y > 0) return b;
		if(x*z > 0) return c;
		return a;
	}
}
