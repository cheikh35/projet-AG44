import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		System.out.println("Entrer the file name containing the ski station's data : ");
		
		Scanner sc = new Scanner(System.in); 
		String str = sc.next();
		System.out.println();
				
		try {
			Plan p = new Plan();
			int first=0, last=0;
			String choix="";
			String pistes="";
			
			p = (new FileRead()).readPlan(str);
			
			p.showPlan();
			
			
			System.out.println("Select your mode :");
			System.out.println("1. Fastest trip between two Points");
			System.out.println("2. All reachable Points from one, with specific level of skying");
			System.out.println("Your choice : ");
			choix = sc.next();
			
			while (Integer.valueOf(choix)!=1 && Integer.valueOf(choix)!=2)
			{
				System.out.println("Your choice : ");
				choix = sc.next();
			}
			
			System.out.println();
			
			if (Integer.valueOf(choix)==1)
			{
				System.out.println("What is the starting point ? ");
				first = sc.nextInt();
				System.out.println("What is the arrival point ? ");
				last = sc.nextInt();
				
				while (first<1 || first >p.nbPoints || last<1 || last >p.nbPoints) {
					System.out.println("Error, retry :");
					System.out.println("What is the starting point ? ");
					first = sc.nextInt();
					System.out.println("What is the arrival point ? ");
					last = sc.nextInt();
				}
				
				p.shortestPath1(first, last);
				
			}
			else
			{
				System.out.println("Can you use all the pistes of the station ? (yes/no) ");
				choix = sc.next();
				
				while (!choix.equals("yes") && !choix.equals("no")) {
					System.out.println("Error, retry :");
					System.out.println("Can you use all the pistes of the station ? (yes/no) ");
					choix = sc.next();
				}
				
				if (choix.equalsIgnoreCase("no")) {
					System.out.println("Write in the same line all the type of pistes you can't use with your level : ");
					pistes = sc.next();
					pistes = pistes.concat(sc.nextLine()); // Because it only catch the first word with sc.next(), so we concatenate with the rest in sc.nextLine()
				}
				System.out.println();
				
				System.out.println("What is the starting point ? ");
				first = sc.nextInt();
				
				p.shortestPath2(first, pistes);
			}
						
			
			System.out.println();
					
		}
		catch (NullPointerException e) {
			System.out.println("Invalid name of file.");
		}
		sc.close();
	}

}
