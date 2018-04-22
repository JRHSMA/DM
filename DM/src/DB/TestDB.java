package DB;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestDB {

	public static void main(String[] args) {
		DB db = new DB("studierendenverwaltung", "root", "");
		db.setSQL("Select * From person;");
		ArrayList<LinkedHashMap<String, String>> daten = db.lesenjava();
		for (LinkedHashMap<String, String> datensatz : daten) {
			System.out.println(datensatz);
		}
	}
}
