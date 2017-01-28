

public class Stack {
	
	private Vertex [] tab;
	private int last;
	
	public Stack (int max) {
		this.tab = new Vertex[max];
		this.last = -1;
	}
	
	public Stack (Stack s) {
		this.tab = s.tab;
		this.last = s.last;
	}
	
	public void push (Stack s) {
		if (s.size() + this.size() <= this.tab.length) 
		{
			int i;
			for (i=0; i<s.size(); i++)
			{
				this.tab[last+i+1] = s.tab[i];
			}
			last += i+1;
		}
		else
		{
			System.out.println("The stack in parameter is too big to be added to this stack");
		}
	}
	
	public void push (Vertex obj) {
		if (!this.isFull())
		{
			last++;
			this.tab[last]=obj;
		}
	}
	
	public Vertex pop () {
		if (!this.isEmpty())
		{
			Vertex obj = this.tab[last];
			this.tab[last] = null;
			last--;
			return obj;
		}
		else
		{
			System.out.println("The stack is already empty");
			return null;
		}
	}
	
	public boolean isFull () {
		return (this.size() == this.tab.length);
	}
	
	public boolean isEmpty () {
		return (this.size() == 0);
	}
	
	public int size() {
		return last+1;
	}
	
	public void showStackFIFO () {
		for (int i=0; i<=this.last; i++)
		{
			System.out.print(""+this.tab[i].graphIndex+" ");
		}
	}
	
	public void showStackLIFO () {
		for (int i=0; i<=this.last; i++)
		{
			System.out.print(""+this.tab[this.last-i].graphIndex+" ");
		}
	}
	
	public void demarkVertices () {
		for (int i=0; i<=this.last; i++)
		{
			this.tab[i].marked=false;
		}
	}
	
	public void transposeStack (Graph g) {
		for (int i=0; i<=this.last; i++)
		{
			this.tab[i]= g.vertices.get(this.tab[i].graphIndex-1);
		}
	}
	
	public Stack invertStack () {
		Stack s1, s2;
		s1 = new Stack(this.tab.length);
		s2 = new Stack(this);
		while (!s2.isEmpty()) 
		{
			s1.push(s2.pop());
		}
		return s1;
	}
}
