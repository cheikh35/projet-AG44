import java.util.ArrayList;


public class Plan {

	int nbPoints;
	int nbRoutes;
	ArrayList <Point> points;
	ArrayList <Route> routes;
	
	public Plan () {
		nbPoints = 0;
		nbRoutes = 0;
		points = new ArrayList <Point>();
		routes = new ArrayList <Route>();
	}
	
	public void showPlan () {
		for (Point p : points)
		{
			p.showPoint();
		}
		System.out.println();
		for (Route r : routes)
		{
			r.showRoute();
		}
		System.out.println();
	}
	
	public void shortestPath1 (int first, int last) {
		
		try {	
			Point firstPoint = this.points.get(first-1);
			Point lastPoint = this.points.get(last-1);
		
			for (Point p : this.points)
			{
				p.timeCost = Double.POSITIVE_INFINITY;
			}
		
			firstPoint.timeCost = Double.valueOf(0);
		
			for (int j=0; j<nbPoints-1; j++)
			{
				for (Route r : routes)
				{
					if (r.arrival.timeCost > r.starting.timeCost + r.timeS)
					{
						r.arrival.timeCost = r.starting.timeCost + r.timeS;
						r.arrival.predecessor = r.starting.pointNumber;
						r.arrival.predecessorType = r.routeType;
					}
				}
			}
		
			this.displayPath(firstPoint, lastPoint);
			
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("You can't reach the Point "+last+" from the Point "+first+" !");
			
		}
		
	}
	
	public void displayPath(Point firstPoint, Point lastPoint) {
		
		int index = lastPoint.pointNumber;
		String typeindex = lastPoint.predecessorType;
		int [] path = new int [this.nbPoints];
		String [] types = new String [this.nbPoints];
		int i=0;
		
		while (index != firstPoint.pointNumber)
		{
			path[i]=index;
			types[i]=typeindex;
			i++;
			typeindex = this.points.get(index-1).predecessorType;
			index = this.points.get(index-1).predecessor;
		}
				
		path[i]=index;
		types[i]=typeindex;
		
		System.out.print("The fastest path between Point "+firstPoint.pointNumber+" and Point "+lastPoint.pointNumber+" is [");
		
		for (int j=i; j>=1; j--)
		{
			System.out.print(""+path[j]+" -> ");
		}
		System.out.println(""+path[0]+"] and it costs "+lastPoint.timeCost.floatValue()+" second(s), ie "+(int)((lastPoint.timeCost.floatValue() /3600))+" hour(s) and "+(int)((lastPoint.timeCost.floatValue() /60) % 60)+" minute(s) and "+(int)(lastPoint.timeCost.floatValue() % 60)+ " second(s)");
		System.out.print("You should take these types of route : ");
		for (int j=i; j>=2; j--)
		{
			System.out.print(""+types[j]+" -> ");
		}
		System.out.println(""+types[1]+".");
		System.out.println();
		
	}
	
	public void shortestPath2 (int first, String pistes) {
		
	
		Point firstPoint = this.points.get(first-1);
		
		shortestPath3(firstPoint.pointNumber, pistes);
		
			
		System.out.println();
		
	}
	
	public int shortestPath3 (int first, String pistes) {
		
		try {	
			Point firstPoint = this.points.get(first-1);
		
			for (Point p : this.points)
			{
				p.timeCost = Double.POSITIVE_INFINITY;
			}
		
			firstPoint.timeCost = Double.valueOf(0);
		
			for (int j=0; j<nbPoints-1; j++)
			{
				for (Route r : routes)
				{
					if (r.arrival.timeCost > r.starting.timeCost + r.timeS && !pistes.contains(r.routeType))
					{
						r.arrival.timeCost = r.starting.timeCost + r.timeS;
						r.arrival.predecessor = r.starting.pointNumber;
						r.arrival.predecessorType = r.routeType;
					}
				}
			}
		
			for (Point p : this.points)
			{
				if (!p.timeCost.equals(Double.POSITIVE_INFINITY))
				{
					this.displayPath(firstPoint, p);
				}
			}
			
			return 0;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return -1;
		}
	}
	
}
