public class Matrix {

	private int rows;
	private int columns;
	public int [][] values;
	
	public Matrix () {
		this.rows = 0;
		this.columns = 0;
		
	}
	
	public Matrix (int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.values = new int [this.rows][this.columns];
		for (int i=0; i<this.rows; i++)
		{
			for (int j=0; j<this.columns; j++)
			{
				this.values[i][j]= 0;
			}
		}
	}
	
	public Matrix (Matrix A) {
		this.rows = A.rows;
		this.columns = A.columns;
		values = new int [this.rows][this.columns];
		for (int i=0; i<this.rows; i++)
		{
			for (int j=0; j<this.columns; j++)
			{
				values[i][j]= A.values[i][j];
			}
		}
		
		
	}
	
	public void assignment (int n) {
		for (int i=0; i<this.rows; i++)
		{
			for (int j=0; j<this.columns; j++)
			{
				values[i][j]= n;
			}
		}
	}
	
	public Matrix add (Matrix B) {
		if ((this.rows==B.rows) && (this.columns==B.columns))
		{
			Matrix sum = new Matrix (this.rows, this.columns);
			for (int i=0; i<sum.rows; i++)
			{
				for (int j=0; j<sum.columns; j++)
				{
					sum.values[i][j]= this.values[i][j] + B.values[i][j];
				}
			}
			
			return sum;
		}
		else
		{
			System.out.println ("It's impossible add these matrices");
			Matrix sum = new Matrix();
			
			return sum;
		}	
	}
	
	public Matrix subtract (Matrix B) {
		if ((this.rows==B.rows) && (this.columns==B.columns))
		{
			Matrix subtraction = new Matrix (this.rows, this.columns);
			for (int i=0; i<subtraction.rows; i++)
			{
				for (int j=0; j<subtraction.columns; j++)
				{
					subtraction.values[i][j]= this.values[i][j] - B.values[i][j];
				}
			}
			
			return subtraction;
		}
		else
		{
			System.out.println ("It's impossible subtract these matrices");
			Matrix subtraction = new Matrix();
			
			return subtraction;
		}	
	}
	
	public Matrix multiplication (Matrix B) {
		if (this.columns==B.rows)
		{
			Matrix product = new Matrix (this.rows, this.columns);
			for (int i=0; i<product.rows; i++)
			{
				for (int j=0; j<product.columns; j++)
				{
					for (int k=0; k<this.columns; k++)
					{
						product.values[i][j] += this.values[i][k]*B.values[k][j];
					}
				}
			}

			return product;
		}
		else
		{
			System.out.println ("It's impossible multiply these matrices");
			Matrix product = new Matrix();
			
			return product;
		}
	}
	
	public Matrix multiplication (int k) {
		Matrix product = new Matrix (this.rows, this.columns);
		for (int i=0; i<this.rows; i++)
		{
			for (int j=0; j<this.columns; j++)
			{
				product.values[i][j] = this.values[i][j]*k;
			}
		}
		return product;
	}
	
	public int getRows () {
		return this.rows;
	}
	
	public int getColumns () {
		return this.columns;
	}
	
	public int[] getLine (int line) {
		if (line<this.rows)
		{
			int [] tab = new int [this.rows];
			for (int j=0; j<this.columns; j++)
			{
				tab[j]= this.values[line][j];
			}
			return tab;
		}
		else
		{
			System.out.println("This line doesn't exist in the matrix");
			int [] tab = new int [0];
			return tab;
		}
	}

	public int[] getColumn (int column) {
		if (column<this.columns)
		{
			int [] tab = new int [this.columns];
			for (int i=0; i<this.columns; i++)
			{
				tab[i]= this.values[i][column];
			}
			return tab;
		}
		else
		{
			System.out.println("This column doesn't exist in the matrix");
			int [] tab = new int [0];
			return tab;
		}
	}
	
	public void show() {
        for (int i=0; i<this.rows; i++) 
        {
            for (int j=0; j<this.columns; j++) 
            {
            	System.out.print(values[i][j]+ " ");
            	
            }
            System.out.println();    
        }
    }

	public Matrix transpose() {
		
        Matrix T = new Matrix(this.columns, this.rows);
        for (int i=0; i<this.rows; i++)
        {
        	for (int j=0; j<this.columns; j++)
        	{
        		T.values[j][i] = this.values[i][j];
        	}
        }
           
        return T;
    }
}	
