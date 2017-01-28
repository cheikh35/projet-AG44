import java.util.ArrayList;


public class Graph {
	int nbVertices;
	int nbLevels;
	ArrayList <Vertex> vertices;
	ArrayList <Level> levels;
	Matrix A;
	Matrix N;
	
	Graph () {
		this.nbVertices = 0;
		this.nbLevels = 0;
		vertices = new ArrayList <Vertex> ();
		levels = new ArrayList <Level> ();
	}
	
	public void fillGraph (Matrix m) {
		if (m.getRows() != m.getColumns())
		{
			System.out.println("We need a square matrix. Can't fill the graph.");
		}
		else
		{
			for (int i=0; i<m.getRows(); i++)
			{
				vertices.add(new Vertex(this.nbVertices+1));
				this.nbVertices++;
			}
			
			for (int i=0; i<this.nbVertices; i++)
			{
				for (int j=0; j<this.nbVertices; j++) 
				{
					if (m.values[i][j]==1)
					{
						this.vertices.get(i).addVertex(this.vertices.get(j));
					}
				}
			}
			A = new Matrix(m);
		}
	}
	
	public void showGraph () {
		System.out.println("----- Beginnig of display -----");
		System.out.println();
		System.out.println("Place's adjacency lists display : ");
		for (Vertex v : vertices)
		{
			v.showList();
		}
		System.out.println();
		System.out.println("Levels display : ");
		for (Level l : levels)
		{
			l.showLevel();
		}
		System.out.println();
		System.out.println("Level's adjacency lists display : ");
		for (Level l : levels)
		{
			l.showList();
		}
		
		System.out.println();
	    System.out.println("Adjacency matrix of places :");
	    System.out.println();
	    
	    this.A.show();
	    
	    System.out.println();
	    System.out.println("Reduced matrix N of levels :");
	    System.out.println();
	    
	    this.N.show();
	    
		System.out.println();
		System.out.println("----- End of display -----");
		System.out.println();
	}

		public Stack dfsPreOrdering () {
		Stack s= new Stack (this.nbVertices);

		for (Vertex sommet : this.vertices)
		{
			sommet.marked = false;
		}
		for (Vertex sommet : this.vertices)
		{
			if (sommet.marked == false)
			{
				s = dfsVertex(s, sommet);
			}
		}
		
		return s;
		
	}
	
	public Stack dfsPostOrdering () {
		Stack s= new Stack (this.nbVertices);

		for (Vertex sommet : this.vertices)
		{
			sommet.marked = false;
		}
		for (Vertex sommet : this.vertices)
		{
			if (sommet.marked == false)
			{
				s = dfsVertexPost(s, sommet);
			}
		}
		
		return s;
		
	}
	
	public Stack dfsVertex (Stack s, Vertex v) {
		v.marked = true;
		s.push(v);
		for (Vertex sommet : v.adjacents)
		{
			if (sommet.marked == false)
			{
				s= dfsVertex (s, sommet);
			}
		}
		return s;	
	}

	public Stack dfsVertexPost (Stack s, Vertex v) {
		v.marked = true;
		for (Vertex sommet : v.adjacents)
		{
			if (sommet.marked == false)
			{
				s= dfsVertexPost (s, sommet);
			}
		}
		s.push(v);
		return s;	
	}
	
	public void dfsLevel (Stack s) {
		Graph transpose = new Graph();
		transpose.fillGraph(this.A.transpose());
		Vertex v; 
		ArrayList <Stack> list = new ArrayList<Stack>();
		
		s.demarkVertices();
		s.transposeStack(transpose);
		while (!s.isEmpty())
		{
			v = s.pop();
			if (v.marked == false)
			{
				list.add(dfsVertexPost(new Stack(transpose.nbVertices), v));		
			}
			
		}
		for (Stack s1 : list)
		{
			this.levels.add(new Level(this.nbLevels+1));
			this.nbLevels++;
			this.levels.get(nbLevels-1).fillLevel(s1);
		}
	}

	public void findLevels () {
		Stack s= new Stack (this.nbVertices);
	    s = this.dfsPostOrdering();    
	    this.dfsLevel(s);
		
	}
	
	public void createN() {
		Matrix n = new Matrix(this.nbLevels,this.nbLevels);
		for (Level l : levels)
		{
			for (Vertex v : l.places)
			{
				for (Vertex voisin : v.adjacents)
				{
					if (!l.places.contains(voisin))
					{
						n.values[this.whichLevel(voisin).graphIndex-1][l.graphIndex-1]++;
					}
				}
			}
		}
		this.N = new Matrix(n);
		
		for (int i=0; i<this.nbLevels; i++)
		{
			for (int j=0; j<this.nbLevels; j++) 
			{
				if (N.values[i][j]>0)
				{
					this.levels.get(i).addLevel(this.levels.get(j));
				}
			}
		}
	}

	public Level whichLevel (Vertex voisin) {
		int index =0;
		for (Level l : this.levels)
		{
			if(l.containsVertex(voisin))
			{
				index = l.graphIndex;
			}
		}
		return this.levels.get(index-1);
	}
	
	public void longestPath (Level first, Level last) {
		Level firstLevel = first; //whichLevel(this.vertices.get(0));
		Level lastLevel = last ; //whichLevel(this.vertices.get(this.nbVertices-1));
		int index = last.graphIndex;
		int [] path = new int [this.nbLevels];
		int i=0;
		
		for (Level l : this.levels)
		{
			l.distanceCost = Double.POSITIVE_INFINITY;
		}
		
		firstLevel.distanceCost = Double.valueOf(0);
		
		ArrayList <Level> liste = new ArrayList <Level> ();
		visit (liste, firstLevel);	
		
		
		System.out.print("The longest path between Level "+firstLevel.graphIndex+" and Level "+lastLevel.graphIndex+" is [");
		while (index != first.graphIndex)
		{
			path[i]=index;
			i++;
			index = this.levels.get(index-1).indexPredecessor;
		}
		path[i]=firstLevel.graphIndex;
		for (int j=i; j>=1; j--)
		{
			System.out.print(""+path[j]+" -> ");
		}
		System.out.print(""+path[0]+"] and it costs "+lastLevel.distanceCost.intValue()+".");
		System.out.println();
	}
	
	public void visit (ArrayList <Level> list, Level level)
	{
		list.add(level);
		for (Level u : level.adjacents)
		{
			if (!list.contains(u))
			{
				if ((u.distanceCost==Double.POSITIVE_INFINITY) || (u.distanceCost.doubleValue() < level.distanceCost.doubleValue() + N.values[level.graphIndex-1][u.graphIndex-1]))
				{
					u.distanceCost = Double.valueOf(level.distanceCost.doubleValue() + N.values[level.graphIndex-1][u.graphIndex-1]);
					u.indexPredecessor = level.graphIndex;
				}
				visit (list, u);
			}
		}
		list.remove(level);
	}
}
