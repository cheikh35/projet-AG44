import java.util.Scanner;
import java.util.regex.Pattern;


public class Point {

	int pointNumber;
	String placeName;
	int altitude;
	Double timeCost;
	int predecessor=0;
	String predecessorType = "";
	
	public Point (String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(Pattern.compile("[\t\n]"));
		
		pointNumber = sc.nextInt();
		placeName = sc.next();
		altitude = sc.nextInt();
		timeCost = Double.valueOf(0);
		
		sc.close();
	}
	
	public void showPoint () {
		System.out.println("Point number : "+pointNumber+", Name : "+placeName+", at : "+altitude+" m");
	}
	
}
