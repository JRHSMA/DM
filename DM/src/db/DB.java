package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

	public void datenzugriffSchliessen() {
		try {
			this.close();
		} catch (Exception e) {

		}
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

	public void setString(char c) {
		try {
			setString("" + c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setString '" + c + "': " + e.getMessage());
		}
	}

	public void setString(String s) {
		try {
			ps.setString(counter_prepared++, s);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setString '" + s + "': " + e.getMessage());
		}
	}

	public void setInt(char c) {
		try {
			setInt(Integer.parseInt("" + c));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setInt '" + c + "': " + e.getMessage());
		}
	}

	public void setInt(String s) {
		try {
			setInt(Integer.parseInt(s));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setInt '" + s + "': " + e.getMessage());
		}
	}

	public void setInt(int i) {
		try {
			ps.setInt(counter_prepared++, i);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setInt '" + i + "': " + e.getMessage());
		}
	}

	public void setDouble(String s) {
		try {
			setDouble(Double.parseDouble(s));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setDouble '" + s + "': " + e.getMessage());
		}
	}

	public void setDouble(double d) {
		try {
			ps.setDouble(counter_prepared++, d);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setDouble '" + d + "': " + e.getMessage());
		}
	}

	/**
	 * Einfache Abfragen Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	 */
	// Ausgabe mit AS Studiengang (führt iwie zu fehlern)
	public void abfrageEinfach01(int matrikelnr) {
		try {
			ps = con.prepareStatement(
					"SELECT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
							+ "FROM studierender s " + "INNER JOIN person p ON s.idPerson = p.ID "
							+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID " + "WHERE s.MatrikelNr = ?;");
			ps.setInt(1, matrikelnr);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageEinfach02(int semester) {
		try {
			ps = con.prepareStatement(
					"SELECT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
							+ " FROM studierender s " + "Inner JOIN person p On s.idPerson = p.ID "
							+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID " + "WHERE s.semester = ?;");
			ps.setInt(1, semester);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageEinfach03(boolean maennlich, String Studiengang) {
		try {
			ps = con.prepareStatement(
					"SELECT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
							+ "FROM studierender s " + "Inner JOIN person p On s.idPerson = p.ID "
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

	public void abfrageEinfach04(boolean maennlich, String Studiengang) {
		try {
			ps = con.prepareStatement(
					"SELECT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
							+ "FROM studierender s " + "Inner JOIN person p On s.idPerson = p.ID "
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

	public void abfrageEinfach05(String vKuerzel) {
		try {
			ps = con.prepareStatement(
					"SELECT DISTINCT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, vn.Name, vn.Kuerzel "
							+ "FROM studierender s " + "INNER JOIN person p ON s.idPerson = p.ID "
							+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
							+ "INNER JOIN hoert ON s.MatrikelNr = hoert.MatrikelNr "
							+ "INNER JOIN veranstaltung v ON hoert.ID = v.ID "
							+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID " + "WHERE vn.Kuerzel=?;");
			ps.setString(1, vKuerzel);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageEinfach06(int semester, String vKuerzel) {
		try {
			ps = con.prepareStatement(
					"SELECT DISTINCT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, vn.Name, vn.Kuerzel "
							+ "FROM studierender s " + "INNER JOIN person p ON s.idPerson = p.ID "
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

	public void abfrageEinfach07(String studiengang, int semester) {
		try {
			ps = con.prepareStatement("SELECT tag.Tag ,sl.Slot, vn.Name " + "FROM stundenplan sp "
					+ "INNER JOIN slot sl ON sp.idSlot = sl.ID " + "INNER JOIN tag ON sp.idTag = tag.ID "
					+ "INNER JOIN studiengang sg ON sp.idStudiengang = sg.ID "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID " + "WHERE sg.Name=? AND sp.Semester=?;");
			ps.setString(1, studiengang);
			ps.setInt(2, semester);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageEinfach08(String fakultaet) {
		try {
			ps = con.prepareStatement(
					"SELECT p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, d.PersonalNr, d.Kuerzel, f.Name "
							+ "FROM dozent d " + "INNER JOIN person p ON d.idPerson = p.ID "
							+ "INNER JOIN fakultaet f ON d.idFakultaet = f.ID " + "WHERE f.Name = ?;");
			ps.setString(1, fakultaet);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageEinfach09(String nachname) {
		try {
			ps = con.prepareStatement(
					"SELECT DISTINCT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
							+ "FROM studierender s " + "INNER JOIN person p ON s.idPerson = p.ID "
							+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
							+ "INNER JOIN hoert h ON s.MatrikelNr = h.MatrikelNr "
							+ "INNER JOIN veranstaltung v ON h.ID = v.ID "
							+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
							+ "INNER JOIN person p1 ON d.idPerson = p1.ID " + "WHERE p1.Nachname =?;");
			ps.setString(1, nachname);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageEinfach10(int semester) {
		try {
			ps = con.prepareStatement(
					"SELECT DISTINCT s.MatrikelNr, s.Semester,sg.Name, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich "
							+ "FROM studierender s " + "INNER JOIN person p On s.idPerson = p.ID "
							+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
							+ "Inner Join hoert h ON s.MatrikelNr = h.MatrikelNr "
							+ "INNER JOIN veranstaltung v On h.ID = v.ID "
							+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID " + "WHERE v.Semester=?;");
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
	public void abfrageKomplex01(String veranstaltungsname, String raum) {
		try {
			ps = con.prepareStatement(
					"SELECT DISTINCT d.PersonalNr,p.Vorname, p.Nachname,d.Kuerzel, p.Geburtsdatum, p.Maennlich, f.Name "
							+ "FROM dozent d " + "INNER JOIN person p ON d.idPerson = p.ID "
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
			ps = con.prepareStatement("SELECT DISTINCT vn.Name, vn.Kuerzel " + "FROM veranstaltungsname vn "
					+ "INNER JOIN veranstaltung v ON vn.ID = v.idvName " + "INNER JOIN hoert h ON v.ID = h.ID "
					+ "INNER JOIN studierender s ON h.MatrikelNr = s.MatrikelNr "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID " + "INNER JOIN hat ON sp.ID = hat.ID "
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
			ps = con.prepareStatement("SELECT DISTINCT vn.Name, v.Semester " + "FROM veranstaltung v "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
					+ "INNER JOIN studiengang sg ON sp.idStudiengang = sg.ID " + "INNER JOIN tag t ON sp.idTag = t.ID "
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
			ps = con.prepareStatement("SELECT DISTINCT r.Bezeichnung " + "FROM raum r "
					+ "INNER JOIN hat ON r.Bezeichnung = hat.Bezeichnung "
					+ "INNER JOIN stundenplan sp ON hat.ID = sp.ID "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr " + "WHERE d.PersonalNr =?;");
			ps.setInt(1, personalnr);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageKomplex05(String vKuerzel, String tag, int slot) {
		try {
			ps = con.prepareStatement("SELECT d.PersonalNr, p.Vorname, p.Nachname, f.Name " + "FROM person p "
					+ "INNER JOIN dozent d On p.ID = d.idPerson " + "INNER JOIN fakultaet f ON d.idFakultaet = f.ID "
					+ "INNER JOIN veranstaltung v ON d.PersonalNr = v.PersonalNr "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID " + "INNER JOIN tag ON sp.idTag = tag.ID "
					+ "INNER JOIN slot s ON sp.idSlot = s.ID " + "WHERE vn.Kuerzel =? AND tag.Tag=? AND s.Slot=?;");
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
			ps = con.prepareStatement("SELECT tag.Tag, sl.Slot, vn.Name, vn.Kuerzel " + "FROM dozent d "
					+ "INNER JOIN erhaelt e ON d.PersonalNr = e.PersonalNr "
					+ "INNER JOIN stundenplan sp ON e.ID= sp.ID " + "INNER JOIN tag ON sp.idTag = tag.ID "
					+ "INNER JOIN slot sl ON sp.idSlot = sl.ID "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID " + "WHERE d.PersonalNr= "
					+ "(SELECT DISTINCT d.PersonalNr " + "FROM dozent d "
					+ "INNER JOIN veranstaltung v ON d.PersonalNr = v.PersonalNr "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID " + "INNER JOIN hoert h ON v.ID = h.ID "
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

	public void abfrageKomplex07(String nachname, int slot, String tag) {
		try {
			ps = con.prepareStatement("SELECT r.Bezeichnung FROM raum r "
					+ "INNER JOIN hat ON r.Bezeichnung = hat.Bezeichnung "
					+ "INNER JOIN stundenplan sp ON hat.ID = sp.ID "
					+ "INNER JOIN slot s ON sp.idSlot = s.ID "
					+ "Inner JOIN tag t ON sp.idTag = t.id "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN person p ON d.idPerson = p.ID "
					+ "WHERE p.Nachname=? AND s.Slot=? AND t.tag=?;");
			ps.setString(1, nachname);
			ps.setInt(2, slot);
			ps.setString(3, tag);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageKomplex08(String nachname, String tag) {
		try {
			ps = con.prepareStatement("SELECT r.Bezeichnung, s.Slot "
					+ "FROM raum r "
					+ "INNER JOIN hat ON r.Bezeichnung = hat.Bezeichnung "
					+ "INNER JOIN stundenplan sp ON hat.ID = sp.ID "
					+ "INNER JOIN slot s ON sp.idSlot = s.ID "
					+ "Inner JOIN tag t ON sp.idTag = t.id "
					+ "INNER JOIN veranstaltung v ON sp.ID = v.idStundenplan "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN person p ON d.idPerson = p.ID "
					+ "WHERE p.Nachname=? AND t.tag=?;");
			ps.setString(1, nachname);
			ps.setString(2, tag);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageKomplex09(boolean cRaum, String tag) {
		try {
			ps = con.prepareStatement(
					"SELECT DISTINCT s.MatrikelNr, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, sg.Name "
							+ "FROM studierender s " + "INNER JOIN person p ON s.idPerson = p.ID "
							+ "INNER JOIN studiengang sg ON s.idStudiengang = sg.ID "
							+ "INNER JOIN hoert h ON s.MatrikelNr = h.MatrikelNr "
							+ "INNER JOIN veranstaltung v ON h.ID = v.ID "
							+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
							+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID "
							+ "INNER JOIN tag ON sp.idTag = tag.ID " + "INNER JOIN hat ON sp.ID = hat.ID "
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
			ps = con.prepareStatement(
					"SELECT s.MatrikelNr, p.Vorname, p.Nachname, p.Geburtsdatum, p.Maennlich, sg.Name "
							+ "FROM studierender s " + "INNER JOIN person p ON s.idPerson = p.ID "
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
			ps = con.prepareStatement(
					"SELECT DISTINCT t.Tag " + "FROM Tag t " + "INNER JOIN stundenplan sp ON t.ID = sp.idTag "
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

	public void abfrageKomplex12(String vName, String nachname, String raum) {
		try {
			ps = con.prepareStatement("SELECT s.MatrikelNr " + "FROM studierender s "
					+ "INNER JOIN hoert h ON s.MatrikelNr = h.MatrikelNr "
					+ "INNER JOIN veranstaltung v ON h.ID = v.ID "
					+ "INNER JOIN veranstaltungsname vn ON v.idvName = vn.ID "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN person p On d.idPerson = p.ID "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID " + "INNER JOIN hat ON sp.ID = hat.ID "
					+ "INNER JOIN raum r ON hat.Bezeichnung = r.Bezeichnung "
					+ "WHERE vn.Name = ? AND p.Nachname=? AND r.Bezeichnung =?;");
			ps.setString(1, vName);
			ps.setString(2, nachname);
			ps.setString(3, raum);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageKomplex13(String tag, boolean cRaum, String fakultaet) {
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
					+ "WHERE t.Tag =? AND r.Computerraum=? AND f.name=?;");
			ps.setString(1, tag);
			ps.setBoolean(2, cRaum);
			ps.setString(3, fakultaet);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public void abfrageKomplex14(String dKuerzel, String tag) {
		try {
			ps = con.prepareStatement("SELECT DISTINCT vn.Name " + "FROM veranstaltungsname vn "
					+ "INNER JOIN veranstaltung v ON vn.ID = v.idvName "
					+ "INNER JOIN dozent d ON v.PersonalNr = d.PersonalNr "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID " + "INNER JOIN tag t ON sp.idTag = t.ID "
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
			ps = con.prepareStatement("SELECT p.Vorname, p.Nachname, f.Name " + "FROM person p "
					+ "INNER JOIN dozent d ON p.ID = d.idPerson " + "INNER JOIN fakultaet f ON d.idFakultaet = f.ID "
					+ "INNER JOIN veranstaltung v ON d.PersonalNr = v.PersonalNr "
					+ "INNER JOIN stundenplan sp ON v.idStundenplan = sp.ID " + "INNER JOIN tag t ON sp.idTag = t.ID "
					+ "INNER JOIN slot s ON sp.idSlot = s.ID " + "WHERE t.Tag=? AND s.Slot=?;");
			ps.setString(1, tag);
			ps.setInt(2, slot);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	/**
	 * Insert-Into
	 */
	public boolean insertPerson(String vorname, String nachname, String geburtsdatum, boolean maennlich) {
		try {
			ps = con.prepareStatement("INSERT INTO person (id, vorname, nachname, geburtsdatum, maennlich) "
					+ "VALUES (NULL, ?, ?, ?, ?);");
			ps.setString(1, vorname);
			ps.setString(2, nachname);
			ps.setString(3, geburtsdatum);
			ps.setBoolean(4, maennlich);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertDozent(String kuerzel, int idFakultaet, int idPerson) {
		try {
			ps = con.prepareStatement(
					"INSERT INTO Dozent (personalNr, kuerzel, idFakultaet, idPerson) " + "VALUES (NULL, ?, ?,?);");
			ps.setString(1, kuerzel);
			ps.setInt(2, idFakultaet);
			ps.setInt(3, idPerson);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertFakultaet(String name) {
		try {
			ps = con.prepareStatement("INSERT INTO fakultaet (id, name) " + "VALUES (NULL, ?);");
			ps.setString(1, name);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertStudierender(int semester, int idStudiengang, int idPerson) {
		try {
			ps = con.prepareStatement("INSERT INTO studierender (matrikelNr, semester, idStudiengang, idPerson) "
					+ "VALUES (Null, ?, ?, ?);");
			ps.setInt(1, semester);
			ps.setInt(2, idStudiengang);
			ps.setInt(3, idPerson);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertStudiengang(String name) {
		try {
			ps = con.prepareStatement("INSERT INTO Studiengang (id, name) " + "VALUES (NULL, ?);");
			ps.setString(1, name);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertVeranstaltung(int semester, int dauer, int personalNr, int idStundenplan, int idvName) {
		try {
			ps = con.prepareStatement(
					"INSERT INTO veranstaltung (id, semester, dauer, personalNr, idStundenplan, idvName) "
							+ "VALUES (NULL,?,?,?,?,?);");
			ps.setInt(1, semester);
			ps.setInt(2, dauer);
			ps.setInt(3, personalNr);
			ps.setInt(4, idStundenplan);
			ps.setInt(5, idvName);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertVeranstaltungsname(String name, String kuerzel) {
		try {
			ps = con.prepareStatement("INSERT INTO veranstaltungsname (id, name, kuerzel) " + "VALUES (NULL,?,?);");
			ps.setString(1, name);
			ps.setString(2, kuerzel);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertRaum(String bezeichnung, boolean computerraum) {
		try {
			ps = con.prepareStatement("INSERT INTO raum (bezeichnung, computerraum) " + "VALUES (?,?);");
			ps.setString(1, bezeichnung);
			ps.setBoolean(2, computerraum);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertHoert(int id, int matrikelNr) {
		try {
			ps = con.prepareStatement("INSERT INTO hoert (id, matrikelNr) " + "VALUES (?,?);");
			ps.setInt(1, id);
			ps.setInt(2, matrikelNr);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertHat(String bezeichnung, int id) {
		try {
			ps = con.prepareStatement("INSERT INTO hat (bezeichnung, id) " + "VALUES (?,?);");
			ps.setString(1, bezeichnung);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertErhaelt(int personalNr, int id) {
		try {
			ps = con.prepareStatement("INSERT INTO erhaelt (personalNr, id) " + "VALUES (?,?);");
			ps.setInt(1, personalNr);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertBesitzt(int id, int matrikelNr) {
		try {
			ps = con.prepareStatement("INSERT INTO besitzt (id, matrikelNr) " + "VALUES (?,?);");
			ps.setInt(1, id);
			ps.setInt(2, matrikelNr);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertSlot(int slot) {
		try {
			ps = con.prepareStatement("INSERT INTO slot (id, slot) " + "VALUES (Null,?);");
			ps.setInt(1, slot);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertTag(String tag) {
		try {
			ps = con.prepareStatement("INSERT INTO tag (id, tag) " + "VALUES (Null,?);");
			ps.setString(1, tag);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertStundenplan(int semester, int idStudiengang, int idTag, int idSlot) {
		try {
			ps = con.prepareStatement(
					"INSERT INTO stundenplan (id, semester, idStudiengang, idTag, idSlot) " + "VALUES (Null,?,?,?,?);");
			ps.setInt(1, semester);
			ps.setInt(2, idStudiengang);
			ps.setInt(3, idTag);
			ps.setInt(4, idSlot);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Delete
	 */
	public boolean deletePerson(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM person WHERE id = ?;");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteDozent(int personalNr) {
		try {
			ps = con.prepareStatement("DELETE FROM dozent WHERE personalNr = ?;");
			ps.setInt(1, personalNr);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteFakultaet(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM fakultaet WHERE id = ?;");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteStudierender(int matrikelNr) {
		try {
			ps = con.prepareStatement("DELETE FROM studierender WHERE matrikelNr = ?;");
			ps.setInt(1, matrikelNr);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteStudiengang(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM studiengang WHERE id = ?;");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteVeranstaltung(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM veranstaltung WHERE id = ?;");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteVeranstaltungsname(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM veranstaltungsname WHERE id = ?;");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteRaum(String bezeichnung) {
		try {
			ps = con.prepareStatement("DELETE FROM raum WHERE bezeichnung = ?;");
			ps.setString(1, bezeichnung);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteHoert(int id, int matrikelNr) {
		try {
			ps = con.prepareStatement("DELETE FROM hoert WHERE id =? AND matrikelNr =?;");
			ps.setInt(1, id);
			ps.setInt(2, matrikelNr);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteHat(String bezeichnung, int id) {
		try {
			ps = con.prepareStatement("DELETE FROM hat WHERE bezeichnung =? AND id =?;");
			ps.setString(1, bezeichnung);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteErhaelt(int personalNr, int id) {
		try {
			ps = con.prepareStatement("DELETE FROM erhaelt WHERE personalNr =? AND id =?;");
			ps.setInt(1, personalNr);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteBesitzt(int id, int matrikelNr) {
		try {
			ps = con.prepareStatement("DELETE FROM besitzt WHERE id =? AND matrikelNr =?;");
			ps.setInt(1, id);
			ps.setInt(2, matrikelNr);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteSlot(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM slot WHERE id =?;");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteTag(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM tag WHERE id =?;");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteStundenplan(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM stundenplan WHERE id =?;");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Update
	 */
	public boolean updatePerson(String vorname, String nachname, String geburtsdatum, boolean maennlich, int id) {
		try {
			ps = con.prepareStatement(
					"UPDATE person SET vorname = ?, nachname = ?, geburtsdatum = ?, maennlich = ? WHERE id = ?;");
			ps.setString(1, vorname);
			ps.setString(2, nachname);
			ps.setString(3, geburtsdatum);
			ps.setBoolean(4, maennlich);
			ps.setInt(5, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateDozent(String kuerzel, int idFakultaet, int idPerson, int personalNr) {
		try {
			ps = con.prepareStatement(
					"UPDATE dozent SET kuerzel = ?, idFakultaet = ?, idPerson = ? WHERE personalNr = ?;");
			ps.setString(1, kuerzel);
			ps.setInt(2, idFakultaet);
			ps.setInt(3, idPerson);
			ps.setInt(4, personalNr);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateFakultaet(String name, int id) {
		try {
			ps = con.prepareStatement("UPDATE fakultaet SET name = ? WHERE id = ?;");
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateStudierender(int semester, int idStudiengang, int idPerson, int matrikelNr) {
		try {
			ps = con.prepareStatement(
					"UPDATE studierender SET semester = ?, idStudiengang = ?, idPerson = ? WHERE matrikelNr = ?;");
			ps.setInt(1, semester);
			ps.setInt(2, idStudiengang);
			ps.setInt(3, idPerson);
			ps.setInt(4, matrikelNr);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateStudiengang(String name, int id) {
		try {
			ps = con.prepareStatement("UPDATE studiengang SET name = ? WHERE id = ?;");
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateVeranstaltung(int semester, int dauer, int personalNr, int idStundenplan, int idvName,
			int id) {
		try {
			ps = con.prepareStatement(
					"UPDATE veranstaltung SET semester = ?, dauer = ?, personalNr = ?, idStundenplan = ?, idvName = ? WHERE id = ?;");
			ps.setInt(1, semester);
			ps.setInt(2, dauer);
			ps.setInt(3, personalNr);
			ps.setInt(4, idStundenplan);
			ps.setInt(5, idvName);
			ps.setInt(6, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateVeranstaltungsname(String name, String kuerzel, int id) {
		try {
			ps = con.prepareStatement("UPDATE veranstaltungsname SET name = ?, kuerzel = ? WHERE id = ?;");
			ps.setString(1, name);
			ps.setString(2, kuerzel);
			ps.setInt(3, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateRaum(boolean computerraum, String bezeichnung) {
		try {
			ps = con.prepareStatement("UPDATE raum SET computerraum = ? WHERE bezeichnung = ?;");
			ps.setBoolean(1, computerraum);
			ps.setString(2, bezeichnung);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateSlot(int slot, int id) {
		try {
			ps = con.prepareStatement("UPDATE slot SET slot = ? WHERE id = ?;");
			ps.setInt(1, slot);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateTag(String tag, int id) {
		try {
			ps = con.prepareStatement("UPDATE tag SET tag = ? WHERE id = ?;");
			ps.setString(1, tag);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateStundenplan(int semester, int idStudiengang, int idTag, int idSlot, int id) {
		try {
			ps = con.prepareStatement(
					"UPDATE stundenplan SET semester = ?, idStudiengang = ?, idTag = ?, idSlot = ? WHERE id = ?;");
			ps.setInt(1, semester);
			ps.setInt(2, idStudiengang);
			ps.setInt(3, idTag);
			ps.setInt(4, idSlot);
			ps.setInt(5, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Primärschlüssel der hinzugefügten Datensätze
	 */
	public int getPersonId(String vorname, String nachname, String geburtsdatum, boolean maennlich){
		try {
			ps = con.prepareStatement("SELECT id from person WHERE (vorname=?) AND (nachname=?) AND (geburtsdatum=?) AND (maennlich=?);");
			ps.setString(1, vorname);
			ps.setString(2, nachname);
			ps.setString(3, geburtsdatum);
			ps.setBoolean(4, maennlich);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine id gefunden/Mehrere ids gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}

	public int getDozentPersonalNr(String kuerzel){
		try {
			ps = con.prepareStatement("SELECT personalNr from dozent WHERE (kuerzel=?);");
			ps.setString(1, kuerzel);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine personalNr gefunden/Mehrere personalNr gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("personalNr"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public int getFakultaetId(String name){
		try {
			ps = con.prepareStatement("SELECT id from fakultaet WHERE (name=?);");
			ps.setString(1, name);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine id gefunden/Mehrere ids gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public int getStudierenderMatrikelNr(int semester, int idStudiengang, int idPerson){
		try {
			ps = con.prepareStatement("SELECT matrikelNr from studierender WHERE (semester=?) AND (idStudiengang=?) AND (idPerson=?);");
			ps.setInt(1, semester);
			ps.setInt(2, idStudiengang);
			ps.setInt(3, idPerson);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine matrikelNr gefunden/Mehrere matrikelNr gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("matrikelNr"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public int getVeranstaltungId(int semester, int dauer, int personalNr, int idStundenplan, int idvName){
		try {
			ps = con.prepareStatement("SELECT id from veranstaltung WHERE (semester=?) AND (dauer=?) AND (personalNr=?) AND (idStundenplan=?) AND (idvName=?);");
			ps.setInt(1, semester);
			ps.setInt(2, dauer);
			ps.setInt(3, personalNr);
			ps.setInt(4, idStundenplan);
			ps.setInt(5, idvName);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine id gefunden/Mehrere ids gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public int getVeranstaltungnameId(String name, String kuerzel){
		try {
			ps = con.prepareStatement("SELECT id from veranstaltungsname WHERE (name=?) AND (kuerzel=?);");
			ps.setString(1, name);
			ps.setString(2, kuerzel);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine id gefunden/Mehrere ids gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public int getSlotId(String slot){
		try {
			ps = con.prepareStatement("SELECT id from slot WHERE (slot=?);");
			ps.setString(1, slot);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine id gefunden/Mehrere ids gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public int getTagId(String tag){
		try {
			ps = con.prepareStatement("SELECT id from tag WHERE (tag=?);");
			ps.setString(1, tag);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine id gefunden/Mehrere ids gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public int getStudiengangId(String name){
		try {
			ps = con.prepareStatement("SELECT id from studiengang WHERE (name=?);");
			ps.setString(1, name);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine id gefunden/Mehrere ids gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
	
	public int getStundenplanId(int semester, int idStudiengang, int idTag, int idSlot){
		try {
			ps = con.prepareStatement("SELECT id from stundenplan WHERE (semester=?) AND (idStudiengang=?) AND (idTag=?) AND (idSlot=?);");
			ps.setInt(1, semester);
			ps.setInt(2, idStudiengang);
			ps.setInt(3, idTag);
			ps.setInt(4, idSlot);
			ps.execute();
			
			ArrayList<LinkedHashMap<String, String>> ergebnisse = this.lesenjava();
			
			if(ergebnisse.size()!=1){
				throw new RuntimeException("Keine id gefunden/Mehrere ids gefunden...");
			}
			
			return Integer.parseInt(ergebnisse.get(0).get("id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
		}
	}
}
