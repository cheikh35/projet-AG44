import java.util.ArrayList;


public class Level {
	
	int nbVertices;
	int nbAdjacents;
	int graphIndex;
	ArrayList <Vertex> places;
	ArrayList <Level> adjacents;
	
	Double distanceCost;
	int indexPredecessor;
	
	public Level () {
		this.nbVertices = 0;
		this.nbAdjacents = 0;
		this.distanceCost = Double.valueOf(0);
		this.places = new ArrayList <Vertex> ();
		this.adjacents = new ArrayList <Level> ();
	}
	
	public Level (int index) {
		this();
		this.graphIndex = index;
		this.indexPredecessor = graphIndex;
	}
	
	public Level (Level l) {
		this();
		this.nbVertices = l.nbVertices;
		this.nbAdjacents = l.nbAdjacents;
		this.graphIndex = l.graphIndex;
		this.indexPredecessor = l.graphIndex;
		this.places.addAll(l.places);
		this.adjacents.addAll(l.adjacents);
	}
	
	public void showLevel () {
		System.out.print("Level "+ this.graphIndex + " : { ");
		for (Vertex v : this.places)
		{
			System.out.print(""+v.graphIndex+" ");
		}
		System.out.println("}");
	}
	
	public void fillLevel (Stack s) {
		while (!s.isEmpty())
		{
			places.add(s.pop());
		}
	}
	
	public boolean containsVertex (Vertex v) {
		for (Vertex v1 : this.places)
		{
			if (v1.graphIndex==v.graphIndex)
			{
				return true;
			}
		}
		return false;
	}

	public void addLevel(Level level) {
		if (!this.adjacents.contains(level))
		{
			this.adjacents.add(level);
			nbAdjacents++;
		}
	}
	
	public void showList() {
		System.out.print("Level n°"+ graphIndex +" adjacent to : { ");		
		for (Level l : adjacents)
		{
			System.out.print(""+l.graphIndex+" ");
		}
		System.out.println("}");
	}
}
