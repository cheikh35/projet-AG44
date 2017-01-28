import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.lang.Math;

public class Route {

	int routeNumber;
	String routeName;
	String routeType;
	Point starting;
	Point arrival;
	float timeM;
	float timeS;
	int distance;
	
	public Route (String line, ArrayList<Point> points) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(Pattern.compile("[\t\n]")); // define the delimiter for parsing the line
		
		routeNumber = sc.nextInt();
		routeName = sc.next();
		routeType = sc.next();
		starting = points.get(sc.nextInt()-1);
		arrival = points.get(sc.nextInt()-1);
		
		distance = arrival.altitude - starting.altitude;
		timeM = this.calculTime();
		timeS = this.convertTime();
		
		sc.close();
	}

	public void showRoute () {
		System.out.println("Route number : "+routeNumber+", Name : "+routeName+", type : "+routeType+", from point "+starting.pointNumber+" to point "+arrival.pointNumber);
	}
	
	public float calculTime () {
			
		switch (this.routeType) {
			case "TK" : {
				return (float) (1. + 4.*Math.abs(this.distance)/100.);
			} 
			case "TS" : {
				return (float) (1. + 4.*Math.abs(this.distance)/100.);
			} 
			case "TSD" : {
				return (float) (1. + 3.*Math.abs(this.distance)/100.);
			} 
			case "TC" : {
				return (float) (2. + 3.*Math.abs(this.distance)/100.);
			} 
			case "TPH" : {
				return (float) (4. + 2.*Math.abs(this.distance)/100.);
			} 
			case "BUS" : {
				if (this.routeName.equals("navette1600-1800") || this.routeName.equals("navette1800-1600"))
					return (float)30.0;
				if (this.routeName.equals("navette1600-2000") || this.routeName.equals("navette2000-1600"))
					return (float)40.0;
			} 
			case "V" : {
				return (float) (5.*Math.abs(this.distance)/100.);
			} 
			case "B" : {
				return (float) (4.*Math.abs(this.distance)/100.);
			} 
			case "R" : {
				return (float) (3.*Math.abs(this.distance)/100.);
			} 
			case "N" : {
				return (float) (2.*Math.abs(this.distance)/100.);
			} 
			case "KL" : {
				return (float) ((1./6)*Math.abs(this.distance)/100.);
			} 
			case "SURF" : {
				return (float) (10.*Math.abs(this.distance)/100.);
			}
		}
		System.out.println("Error in calculing route time.");
		return 0;
	}
	
	public float convertTime() {
		return (float) (timeM*60.0);
	}
	
	
}
