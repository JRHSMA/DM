package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DB {
	private PreparedStatement ps = null;
	private Connection con = null;
	private int counter_prepared = 1;

	public DB(String db, String user, String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String kommando = "jdbc:mysql://localhost/" + "studierendenverwaltung" + "?user=" + "root" + "&passwort="
					+ "";
			con = DriverManager.getConnection(kommando);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("der DB-Zugang ist nicht Vorhanden!");
		}
	}

	public void close() {
		finalize();
	}

	@Override
	public void finalize() {
		try {
			ps.close();
		} catch (SQLException e) {
		}
		ps = null;
		try {
			con.close();
		} catch (SQLException e) {
		}
		con = null;
	}

	public void setSQL(String sql) {
		try {
			ps = con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public ArrayList<LinkedHashMap<String, String>> lesenjava() {
		try {
			return konvertiereJava(ps.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB lesenjava " + e.getMessage());
		}
	}

	private ArrayList<LinkedHashMap<String, String>> konvertiereJava(ResultSet rs) throws SQLException {

		ArrayList<LinkedHashMap<String, String>> daten = new ArrayList<>();
		int anz_spalten = rs.getMetaData().getColumnCount();
		if (anz_spalten == 0)
			return daten;
		while (rs.next()) {
			LinkedHashMap<String, String> datensatz = new LinkedHashMap<>();
			for (int i = 1; i <= anz_spalten; i++) {
				String name = rs.getMetaData().getColumnName(i);
				String wert = rs.getString(name);
				if (wert != null)
					datensatz.put(name, wert);
				else
					datensatz.put(name, "");
			}
			daten.add(datensatz);
		}
		return daten;
	}

	/**
	 * Prepeard Statements
	 */
	
	public void setString(char c){
		try {
			setString(""+c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setString '"+c+"': "+e.getMessage());
		}
	}
	public void setString(String s){
		try {
			ps.setString(counter_prepared++,s);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setString '"+s+"': "+e.getMessage());
		}
	}

	public void setInt(char c){
		try {
			setInt(Integer.parseInt(""+c));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setInt '"+c+"': "+e.getMessage());
		}
	}
	public void setInt(String s){
		try {
			setInt(Integer.parseInt(s));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setInt '"+s+"': "+e.getMessage());
		}
	}
	public void setInt(int i){
		try {
			ps.setInt(counter_prepared++,i);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setInt '"+i+"': "+e.getMessage());
		}
	}

	public void setDouble(String s){
		try {
			setDouble(Double.parseDouble(s));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setDouble '"+s+"': "+e.getMessage());
		}
	}
	public void setDouble(double d){
		try {
			ps.setDouble(counter_prepared++,d);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setDouble '"+d+"': "+e.getMessage());
		}
	}
	
	/**
	 * Einfache Abfragen
	 */
	
	//TODO Datenbank verbindung schließen (offen noch wo genau)
	
	//Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	public void abfrageEinfach01(int matrikelnr) {
		try {
			ps = con.prepareStatement("SELECT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
					+ "FROM studierender s "
					+ "INNER JOIN person p ON s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "WHERE s.MatrikelNr = ?;");
			ps.setInt(1, matrikelnr);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	//Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	public void abfrageEinfach02(int semester) {
		try {
			ps = con.prepareStatement("SELECT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
					+ " FROM studierender s "
					+ "Inner JOIN person p On s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "WHERE s.semester = ?;");
			ps.setInt(1, semester);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	//Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	public void abfrageEinfach03(boolean maennlich, String Studiengang) {
		try {
			ps = con.prepareStatement("SELECT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
					+ "FROM studierender s "
					+ "Inner JOIN person p On s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "WHERE p.Maennlich=? AND sg.Name = ?;");
			ps.setBoolean(1, maennlich);
			ps.setString(2, Studiengang);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	//Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	public void abfrageEinfach04(boolean maennlich, String Studiengang) {
		try {
			ps = con.prepareStatement("SELECT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
					+ "FROM studierender s "
					+ "Inner JOIN person p On s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "WHERE p.Maennlich=? AND sg.Name = ?;");
			ps.setBoolean(1, maennlich);
			ps.setString(2, Studiengang);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	//Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	public void abfrageEinfach05(String vKuerzel) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, vn.Name, vn.Kuerzel "
					+ "FROM studierender s "
					+ "INNER JOIN person p ON s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "INNER JOIN hoert ON s.MatrikelNr = hoert.MatrikelNr "
					+ "INNER JOIN veranstaltung v ON hoert.ID = v.ID "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "WHERE vn.Kuerzel=?;");
			ps.setString(1, vKuerzel);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	//Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	public void abfrageEinfach06(String vKuerzel, int semester) {
		try {
			ps = con.prepareStatement("SELECT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, vn.Name, vn.Kuerzel "
					+ "FROM studierender s "
					+ "INNER JOIN person p ON s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "INNER JOIN hoert h ON s.MatrikelNr = h.MatrikelNr "
					+ "INNER JOIN veranstaltung v ON h.ID = v.ID "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "WHERE vn.Kuerzel=? AND s.Semester = ?;");
			ps.setString(1, vKuerzel);
			ps.setInt(2, semester);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	//Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	public void abfrageEinfach07(String studiengang, int semester) {
		try {
			ps = con.prepareStatement("SELECT tag.Tag ,sl.Slot, vn.Name "
					+ "FROM stundenplan sp "
					+ "INNER JOIN slot sl ON sp.idSlot = sl.ID "
					+ "INNER JOIN tag ON sp.idTag = tag.ID "
					+ "INNER JOIN studiengang sg ON sp.idStudiengang = sg.ID "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "WHERE sg.Name=? AND sp.Semester=?;");
			ps.setString(1, studiengang);
			ps.setInt(2, semester);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	//Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	public void abfrageEinfach08(String fakultaet) {
		try {
			ps = con.prepareStatement("SELECT p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, d.PersonalNr, d.Kuerzel, f.Name "
					+ "FROM dozent d "
					+ "INNER JOIN person p ON d.idPerson = p.ID "
					+ "INNER JOIN fakultaet f ON d.idFakultaet = f.ID "
					+ "WHERE f.Name = ?;");
			ps.setString(1, fakultaet);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public void abfrageEinfach09(String vorname) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
					+ "FROM studierender s "
					+ "INNER JOIN person p ON s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "INNER JOIN hoert h ON s.MatrikelNr = h.MatrikelNr "
					+ "INNER JOIN veranstaltung v ON h.ID = v.ID "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN person p1 ON d.idPerson = p1.ID "
					+ "WHERE p1.Vorname =?;");
			ps.setString(1, vorname);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public void abfrageEinfach10(int semester) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
					+ "FROM studierender s "
					+ "INNER JOIN person p On s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "Inner Join hoert h ON s.MatrikelNr = h.MatrikelNr "
					+ "INNER JOIN veranstaltung v On h.ID = v.ID "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "WHERE v.Semester=?;");
			ps.setInt(1, semester);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	
	/**
	 * Komplexe Abfragen
	 */
	
	//TODO Datenbank verbindung schließen (offen noch wo genau)
	
	public void abfrageKomplex01(String veranstaltungsname, String raum) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT d.PersonalNr,p.Vorname, p.Nachname,d.Kuerzel, p.Geburtsdatum, p.Maennlich, f.Name "
					+ "FROM dozent d "
					+ "INNER JOIN person p ON d.idPerson = p.ID "
					+ "INNER JOIN fakultaet f ON d.idFakultaet = f.ID "
					+ "INNER JOIN veranstaltung v ON d.PersonalNr = v.PersonalNr "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN hat ON sp.ID = hat.ID "
					+ "INNER JOIN raum r ON hat.Bezeichnung = r.Bezeichnung "
					+ "WHERE vn.Name=? AND r.bezeichnung=?;");
			ps.setString(1, veranstaltungsname);
			ps.setString(2, raum);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex02(int matrikelnr, String raum) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT vn.Name, vn.Kuerzel "
					+ "FROM veranstaltungsname vn "
					+ "INNER JOIN veranstaltung v ON vn.ID = v.idvName "
					+ "INNER JOIN hoert h ON v.ID = h.ID "
					+ "INNER JOIN studierender s ON h.MatrikelNr = s.MatrikelNr "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN hat ON sp.ID = hat.ID "
					+ "INNER JOIN raum r ON hat.Bezeichnung = r.Bezeichnung "
					+ "WHERE s.MatrikelNr = ? AND r.Bezeichnung =?;");
			ps.setInt(1, matrikelnr);
			ps.setString(2, raum);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex03(String studiengang, String tag) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT vn.Name, v.Semester "
					+ "FROM veranstaltung v "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN studiengang sg ON sp.idStudiengang = sg.ID "
					+ "INNER JOIN tag t ON sp.idTag = t.ID "
					+ "WHERE sg.Name=? AND t.Tag=?;");
			ps.setString(1, studiengang);
			ps.setString(2, tag);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex04(int personalnr) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT r.Bezeichnung "
					+ "FROM raum r "
					+ "INNER JOIN hat ON r.Bezeichnung = hat.Bezeichnung "
					+ "INNER JOIN stundenplan sp ON hat.ID = sp.ID "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "WHERE d.PersonalNr =?;");
			ps.setInt(1, personalnr);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex05(String vKuerzel, String tag, int slot) {
		try {
			ps = con.prepareStatement("SELECT d.PersonalNr, p.Vorname, p.Nachname, f.Name "
					+ "FROM person p "
					+ "INNER JOIN dozent d On p.ID = d.idPerson "
					+ "INNER JOIN fakultaet f ON d.idFakultaet = f.ID "
					+ "INNER JOIN veranstaltung v ON d.PersonalNr = v.PersonalNr "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN tag ON sp.idTag = tag.ID "
					+ "INNER JOIN slot s ON sp.idSlot = s.ID "
					+ "WHERE vn.Kuerzel =? AND tag.Tag=? AND s.Slot=?;");
			ps.setString(1, vKuerzel);
			ps.setString(2, tag);
			ps.setInt(3, slot);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex06(String vName, int matrikelnr) {
		try {
			ps = con.prepareStatement("SELECT tag.Tag, sl.Slot, vn.Name, vn.Kuerzel "
					+ "FROM dozent d "
					+ "INNER JOIN erhaelt e ON d.PersonalNr = e.PersonalNr "
					+ "INNER JOIN stundenplan sp ON e.ID= sp.ID "
					+ "INNER JOIN tag ON sp.idTag = tag.ID "
					+ "INNER JOIN slot sl ON sp.idSlot = sl.ID "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "WHERE d.PersonalNr= "
					+ "(SELECT DISTINCT d.PersonalNr "
					+ "FROM dozent d "
					+ "INNER JOIN veranstaltung v ON d.PersonalNr = v.PersonalNr "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN hoert h ON v.ID = h.ID "
					+ "INNER JOIN studierender s ON h.MatrikelNr = s.MatrikelNr "
					+ "WHERE vn.Name=? AND s.MatrikelNr = ?);");
			ps.setString(1, vName);
			ps.setInt(2, matrikelnr);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex07(String nachname, int slot) {
		try {
			ps = con.prepareStatement("SELECT r.Bezeichnung "
					+ "FROM raum r "
					+ "INNER JOIN hat ON r.Bezeichnung = hat.Bezeichnung "
					+ "INNER JOIN stundenplan sp ON hat.ID = sp.ID "
					+ "INNER JOIN slot s ON sp.idSlot = s.ID "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN person p ON d.idPerson = p.ID "
					+ "WHERE p.Nachname=? AND s.Slot=?;");
			ps.setString(1, nachname);
			ps.setInt(2, slot);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex08(String nachname) {
		try {
			ps = con.prepareStatement("SELECT r.Bezeichnung, s.Slot "
					+ "FROM raum r "
					+ "INNER JOIN hat ON r.Bezeichnung = hat.Bezeichnung "
					+ "INNER JOIN stundenplan sp ON hat.ID = sp.ID "
					+ "INNER JOIN slot s ON sp.idSlot = s.ID "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN person p ON d.idPerson = p.ID "
					+ "WHERE p.Nachname=?;");
			ps.setString(1, nachname);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex09(boolean cRaum, String tag) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT s.MatrikelNr, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, sg.Name "
					+ "FROM studierender s "
					+ "INNER JOIN person p ON s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "INNER JOIN hoert h ON s.MatrikelNr = h.MatrikelNr "
					+ "INNER JOIN veranstaltung v ON h.ID = v.ID "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN tag ON sp.idTag = tag.ID "
					+ "INNER JOIN hat ON sp.ID = hat.ID "
					+ "INNER JOIN raum r ON hat.Bezeichnung = r.Bezeichnung "
					+ "WHERE r.Computerraum=? AND tag.Tag=?;");
			ps.setBoolean(1, cRaum);
			ps.setString(2, tag);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex10(boolean maennlich, String vName, String raum) {
		try {
			ps = con.prepareStatement("SELECT s.MatrikelNr, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, sg.Name "
					+ "FROM studierender s "
					+ "INNER JOIN person p ON s.idPerson = p.ID "
					+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
					+ "INNER JOIN hoert h ON s.MatrikelNr = h.MatrikelNr "
					+ "INNER JOIN veranstaltung v ON h.ID = v.ID "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN hat ON sp.ID = hat.ID "
					+ "INNER JOIN raum r ON hat.Bezeichnung = r.Bezeichnung "
					+ "WHERE p.Maennlich =? AND vn.Name = ? AND r.Bezeichnung = ?;");
			ps.setBoolean(1, maennlich);
			ps.setString(2, vName);
			ps.setString(3, raum);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex11(String vKuerzel, String studiengang, String nachname) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT t.Tag "
					+ "FROM Tag t "
					+ "INNER JOIN stundenplan sp ON t.ID = sp.idTag "
					+ "INNER JOIN studiengang sg ON sp.idStudiengang = sg.ID "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN person p ON d.idPerson = p.ID "
					+ "WHERE vn.Kuerzel=? AND sg.Name =? AND p.Nachname=?;");
			ps.setString(1, vKuerzel);
			ps.setString(2, studiengang);
			ps.setString(3, nachname);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex12(String vName, String vorname, String nachname, String raum) {
		try {
			ps = con.prepareStatement("SELECT s.MatrikelNr "
					+ "FROM studierender s "
					+ "INNER JOIN hoert h ON s.MatrikelNr = h.MatrikelNr "
					+ "INNER JOIN veranstaltung v ON h.ID = v.ID "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN person p On d.idPerson = p.ID "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN hat ON sp.ID = hat.ID "
					+ "INNER JOIN raum r ON hat.Bezeichnung = r.Bezeichnung "
					+ "WHERE vn.Name = ? AND p.Vorname =? AND p.Nachname=? AND r.Bezeichnung =?;");
			ps.setString(1, vName);
			ps.setString(2, vorname);
			ps.setString(3, nachname);
			ps.setString(4, raum);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex13(String tag, boolean cRaum) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT p.Vorname, p.Nachname, d.Kuerzel, f.Name "
					+ "FROM dozent d "
					+ "INNER JOIN person p ON d.idPerson = p.ID "
					+ "INNER JOIN fakultaet f ON d.idFakultaet = f.ID "
					+ "INNER JOIN veranstaltung v ON d.PersonalNr = v.PersonalNr "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN tag t ON sp.idTag= t.ID "
					+ "INNER JOIN hat ON sp.ID = hat.ID "
					+ "INNER JOIN raum r ON hat.Bezeichnung = r.Bezeichnung "
					+ "WHERE t.Tag =? AND r.Computerraum=?;");
			ps.setString(1, tag);
			ps.setBoolean(2, cRaum);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex14(String dKuerzel, String tag) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT vn.Name "
					+ "FROM veranstaltungsname vn "
					+ "INNER JOIN veranstaltung v ON vn.ID = v.idvName "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN tag t ON sp.idTag = t.ID "
					+ "WHERE d.Kuerzel=? AND t.Tag=?;");
			ps.setString(1, dKuerzel);
			ps.setString(2, tag);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	public void abfrageKomplex15(String tag, int slot) {
		try {
			ps = con.prepareStatement("SELECT p.Vorname, p.Nachname, f.Name "
					+ "FROM person p "
					+ "INNER JOIN dozent d ON p.ID = d.idPerson "
					+ "INNER JOIN fakultaet f ON d.idFakultaet = f.ID "
					+ "INNER JOIN veranstaltung v ON d.PersonalNr = v.PersonalNr "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN tag t ON sp.idTag = t.ID "
					+ "INNER JOIN slot s ON sp.idSlot = s.ID "
					+ "WHERE t.Tag=? AND s.Slot=?;");
			ps.setString(1, tag);
			ps.setInt(2, slot);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	
}
