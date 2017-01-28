import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FileRead {

	FileReader fin;
	Matrix m;
	
	public Matrix readMatrix (String name) {
		
		try {
			this.fin = new FileReader (new File(name));
			int buffer=0, n=0, j=0, k=0;
			String c;
			if ((buffer = this.fin.read()) !=-1) 
			{
				try {
					c = String.valueOf((char) buffer);
					while ((buffer= this.fin.read()) !=10) {
						c= c.concat(String.valueOf((char) buffer));
					}
					c = c.substring(0,c.length()-1);
					//System.out.println(c);
					n = Integer.parseInt(""+c);
				}
				catch (NumberFormatException e) {
					e.printStackTrace();           
				}
				
				this.m = new Matrix(n,n);
				
				while (((buffer = this.fin.read()) !=-1) && j<n)
				{
					while (buffer <48 && buffer >57)
					{
						buffer = this.fin.read();
						if (buffer ==-1)
						{
							break;
						}
					}
					try {
						
						if (buffer >=48 && buffer <=57)
						{
							c = String.valueOf((char) buffer);
							this.m.values[j][k] = Integer.parseInt(c);
							k++;
						}
							
						if (k==n)
						{
							k=0;
							j++;
						}
					}
					catch (NumberFormatException e) {
						e.printStackTrace();           
					}
					
					
				}
				
			}
			else
			{
				System.out.println("Empty file");
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("The specified file is not found");
			//e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
			if (fin != null)
			fin.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
		
		return m;
	}
}
