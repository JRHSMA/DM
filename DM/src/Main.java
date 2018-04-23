import java.util.Scanner;

public class Main {
	public static void main(String args[]){
//		aufgaben();
	}
	public void aufgaben() {
		System.out.println("Please Select The Aufgabe You Want To See\n1.\n2.\n3.");
		Scanner sc = new Scanner(System.in);
		int auf=0;
		boolean inRight = false;
		while (inRight == false) {
			auf = sc.nextInt();
			if (auf < 1 || auf > 3) {
				System.out.println("FALSCH!!!\nNOCHMAL");
			} else {
				inRight = true;
			}
		}
		if(auf==1){
			aufgabe1();
		}
		if(auf==2){
			aufgabe2();
		}
		if(auf==3){
			aufgabe3();
		}
	}
	private void aufgabe1(){
		System.out.println("Was wollen Sie machen?\n1.Neuer Eintrag\n2.Eintrag bearbeiten\nEintrag löschen");
	}
	private void aufgabe2(){
		System.out.println("Welche einfache Abfrage soll aufgerufen werden?");
	}
	private void aufgabe3(){
		System.out.println("Welche komplexe Abfrage soll aufgerufen werden?");
	}

}
