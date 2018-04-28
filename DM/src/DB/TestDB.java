package DB;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestDB {
	
	public static void main(String[] args) {
		DB db = new DB("studierendenverwaltung", "root", "");
		/*
		 * db.setSQL("SELECT s.matrikelNr, s.semester, sg.name, p.vorname, p.nachname, p.geburtsdatum, p.maennlich"
					+ " FROM studierender s "
					+ " INNER JOIN person p ON s.idPerson = p.ID "
					+ " INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ " WHERE s.MatrikelNr = 1712189;");
		 */
		//db.abfrageEinfach01(1811428);
		//db.abfrageEinfach02(3);
		//db.abfrageEinfach03(true, "Unternehmens- und Wirtschaftsinformatik");
		//db.abfrageEinfach04(false, "Unternehmens- und Wirtschaftsinformatik");
		//db.abfrageEinfach05("DM");
		//db.abfrageEinfach06("MA1", 4);
		//db.abfrageEinfach07("Unternehmens- und Wirtschaftsinformatik", 3);
		//db.abfrageEinfach08("Informatik");
		//db.abfrageEinfach09("Frank");
		//db.abfrageEinfach10(3);
		
		//db.abfrageKomplex01("Daten Management", "A008");
		//db.abfrageKomplex02(1712189, "A008");
		//db.abfrageKomplex03("Unternehmens- und Wirtschaftsinformatik", "Donnerstag");
		//db.abfrageKomplex04(1);
		//db.abfrageKomplex05("DM", "Dienstag", 4);
		//db.abfrageKomplex06("Daten Management", 1712189);
		//db.abfrageKomplex07("Dopatka", 4);
		//db.abfrageKomplex08("Dopatka");
		//db.abfrageKomplex09(true, "Mittwoch");
		//db.abfrageKomplex10(true, "Daten Management", "A008");
		//db.abfrageKomplex11("DM", "Unternehmens- und Wirtschaftsinformatik", "Dopatka");
		//db.abfrageKomplex12("Daten Management", "Frank", "Dopatka", "A008");
		//db.abfrageKomplex13("Dienstag", true);
		//db.abfrageKomplex14("HSF", "Freitag");
		//db.abfrageKomplex15("Freitag",1);
		
		ArrayList<LinkedHashMap<String, String>> daten = db.lesenjava();
		for (LinkedHashMap<String, String> datensatz : daten) {
			System.out.println(datensatz);
		}
	}
}
