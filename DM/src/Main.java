import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		System.out.println("Please Select The Aufgabe You Want To See\n1.\n2.\n3.");
		Scanner sc = new Scanner(System.in);
		int auf;
		boolean inRight = false;
		while (inRight == false) {
			auf = sc.nextInt();
			if (auf < 1 || auf > 3) {
				System.out.println("FALSCH!!!\nNOCHMAL");
			} else {
				inRight = true;
			}
		}
	}

}
