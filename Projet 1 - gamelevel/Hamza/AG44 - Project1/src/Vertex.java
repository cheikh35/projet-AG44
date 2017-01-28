import java.util.ArrayList;


public class Vertex {

	int nbAdjacents;
	int graphIndex;
	ArrayList <Vertex> adjacents;
	boolean marked=false;
	
	public Vertex () {
		this.nbAdjacents = 0;
		this.adjacents = new ArrayList <Vertex>();
	}
	
	public Vertex (int index) {
		this();
		this.graphIndex = index;
	}
	
	public void addVertex (Vertex v) {
		if (!this.adjacents.contains(v))
		{
			this.adjacents.add(v);
			nbAdjacents++;
		}
	}
	
	public void removeVertex (Vertex v) {
		this.adjacents.remove(this.searchVertex(v));
	}
	
	public int searchVertex (Vertex v) {
		int n;
		
		for (n=0; n<this.nbAdjacents; n++) 
		{
			if (this.adjacents.get(n).graphIndex == v.graphIndex)
			{
				return n;
			}
		}
		
		System.out.println("This place is not adjacent to the caller place");
		return -1;
	}

	public void showList() {
		System.out.print("Place n°"+ graphIndex +" adjacent to : { ");		
		for (Vertex v : adjacents)
		{
			System.out.print(""+v.graphIndex+" ");
		}
		System.out.println("}");
	}
}
