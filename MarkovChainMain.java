import java.util.Scanner;

public class MarkovChainMain {

	public static void main(String[] args) {
		
		MarkovChain chain;
		Scanner input = new Scanner(System.in);
		int choice;
		String[] text = {"Exampletext.txt", "VXsource.txt", "greentext.txt", "nosleep1.txt"};
		String a;
		
		do {
			System.out.println("Choose File to Reconstruct: ");
			System.out.println("1. Trip Log\n2. VX jargon\n3. Green Text\n4. No Sleep 1");
			 choice = input.nextInt() - 1;			
			
				switch (choice) {
				case 0:
					chain = new MarkovChain(text[choice]);
					break;
				case 1:
					chain = new MarkovChain(text[choice]);
					break;
				case 2:
					chain = new MarkovChain(text[choice]);
					break;
				case 3:
					chain = new MarkovChain(text[choice]);
					break;
				default: 
					System.out.println("Incorrect Input");
					break;
				}
				
			System.out.println("\nCreate another sentence? (Y/N)");
			 input.nextLine();
			 a = input.nextLine();
			 System.out.println();
		} while (a.equalsIgnoreCase("y"));
		
		System.out.println("Goodbye.");
		
	}
	
}
