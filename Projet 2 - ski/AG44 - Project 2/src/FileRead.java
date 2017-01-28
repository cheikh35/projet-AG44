import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileRead {

	Scanner scanner;
	Plan plan = new Plan ();
	
	public Plan readPlan (String name) {
		String line;
		int number;
		try {
			scanner = new Scanner(new File(name));
			
			line = scanner.nextLine();
			number = Integer.parseInt(line);
			
			for (int i=0; i<number; i++)
			{
				line = scanner.nextLine();
				plan.points.add(new Point(line));
				plan.nbPoints++;
			}	
			
			line = scanner.nextLine();
			number = Integer.parseInt(line);
			
			for (int i=0; i<number; i++)
			{
				line = scanner.nextLine();
				plan.routes.add(new Route(line, plan.points));
				plan.nbRoutes++;
			}
			
		} 
		catch (FileNotFoundException e) {
			System.out.println("The file cannot be founded.");
			
		}
		
		scanner.close();
		return plan;
	}
}
