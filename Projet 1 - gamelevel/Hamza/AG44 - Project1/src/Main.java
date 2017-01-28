import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
				
		String str ;
		FileRead f = new FileRead();
		Graph g = new Graph();
		
		System.out.println("Entrer the file name containing the matrix : ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in); 
		str = sc.next();
		System.out.println();
		
		f.readMatrix(str);
		
		try {
			g.fillGraph(f.m);
			
			g.findLevels();	
		    g.createN();
		    
		    g.showGraph();	
		    
		    g.longestPath(g.whichLevel(g.vertices.get(0)),g.whichLevel(g.vertices.get(g.nbVertices-1)));
		}
		catch (NullPointerException e)
		{
			System.out.println("The graph can't be created");
			System.out.println("End of the program");
		}
	    
	    
	}

}
