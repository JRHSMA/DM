import java.util.ArrayList;
import java.util.LinkedHashMap;

import db.DB;

public class Studierendenverwaltung {

	// public static void main(String[] args) {
	// GUI stud = new GUI();
	// stud.LayoutGUI("Studierendenverwaltung");
	// }

	public static void main(String[] args) {
		DB datenzugriff = null;
		ArrayList<Fakultaet> fakultaeten = new ArrayList<>();
		ArrayList<Person> personen = new ArrayList<>();
		ArrayList<Dozent> dozenten = new ArrayList<>();
		ArrayList<Studiengang> studiengaenge = new ArrayList<>();
		ArrayList<Studierender> studierende = new ArrayList<>();
		ArrayList<Slot> slots = new ArrayList<>();
		ArrayList<Tag> tage = new ArrayList<>();
		ArrayList<Stundenplan> stundenplaene = new ArrayList<>();
		ArrayList<Veranstaltungsname> veranstaltungsnamen = new ArrayList<>();
		ArrayList<Veranstaltung> veranstaltungen = new ArrayList<>();
		ArrayList<Hoert> hoeren = new ArrayList<>();
		ArrayList<Raum> raeume = new ArrayList<>();
		ArrayList<Hat> hatten = new ArrayList<>();
		ArrayList<Besitzt> besitzen = new ArrayList<>();
		ArrayList<Erhaelt> erhalten = new ArrayList<>();

		DatenAusDbEinlesen(datenzugriff, fakultaeten, personen, dozenten, studiengaenge, studierende, slots, tage,
				stundenplaene, veranstaltungsnamen, veranstaltungen, hoeren, raeume, hatten, besitzen, erhalten);
		
	}

	public static void DatenAusDbEinlesen(DB datenzugriff, ArrayList<Fakultaet> fakultaeten, ArrayList<Person> personen,
			ArrayList<Dozent> dozenten, ArrayList<Studiengang> studiengaenge, ArrayList<Studierender> studierende,
			ArrayList<Slot> slots, ArrayList<Tag> tage, ArrayList<Stundenplan> stundenplaene,
			ArrayList<Veranstaltungsname> veranstaltungsnamen, ArrayList<Veranstaltung> veranstaltungen,
			ArrayList<Hoert> hoeren, ArrayList<Raum> raeume, ArrayList<Hat> hatten, ArrayList<Besitzt> besitzen,
			ArrayList<Erhaelt> erhalten) {

		try {
			// referenzielle Integrität Dozent
			// Fakultät
			ArrayList<LinkedHashMap<String, String>> daten = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM fakultaet;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				fakultaeten.add(new Fakultaet(datensatz));
			}
			System.out.println(fakultaeten);
			System.out.println("-----------------");

			// Person
			ArrayList<LinkedHashMap<String, String>> daten1 = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM person;");
			daten1 = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten1) {
				personen.add(new Person(datensatz));
			}
			System.out.println(personen);
			System.out.println("-----------------");

			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM dozent;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				int id = Integer.parseInt(datensatz.get("personalNr"));
				String name = datensatz.get("kuerzel");
				int idFakultaet = Integer.parseInt(datensatz.get("idFakultaet"));
				int idPerson = Integer.parseInt(datensatz.get("idPerson"));
				Fakultaet fakultaet = null;
				for (Fakultaet f : fakultaeten) {
					if (f.getId() == idFakultaet) {
						fakultaet = f;
						break;
					}
				}
				Person person = null;
				for (Person p : personen) {
					if (p.getId() == idPerson) {
						person = p;
						break;
					}
				}

				dozenten.add(new Dozent(id, name, fakultaet, person));
			}
			System.out.println(dozenten);
			System.out.println("-----------------");

			datenzugriff.setSQL("SELECT * FROM dozent;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				System.out.println(datensatz);
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + ":" + e.getMessage());
		} finally {
			datenzugriff.close();
		}
		System.out.println("Testausgabe Fakultät: " + dozenten.get(1).getFakultaet() + " Testausgabe Person: "
				+ dozenten.get(1).getPerson());

		try {
			// referenzielle Integrität Studierender
			// Studiengang
			ArrayList<LinkedHashMap<String, String>> daten = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM studiengang;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				studiengaenge.add(new Studiengang(datensatz));
			}
			System.out.println(studiengaenge);
			System.out.println("-----------------");

			// Person
			ArrayList<LinkedHashMap<String, String>> daten1 = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM person;");
			daten1 = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten1) {
				personen.add(new Person(datensatz));
			}
			System.out.println(personen);
			System.out.println("-----------------");

			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM studierender;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				int id = Integer.parseInt(datensatz.get("matrikelNr"));
				int semester = Integer.parseInt(datensatz.get("semester"));
				int idStudiengang = Integer.parseInt(datensatz.get("idStudiengang"));
				int idPerson = Integer.parseInt(datensatz.get("idPerson"));
				Studiengang studiengang = null;
				for (Studiengang sg : studiengaenge) {
					if (sg.getId() == idStudiengang) {
						studiengang = sg;
						break;
					}
				}
				Person person = null;
				for (Person p : personen) {
					if (p.getId() == idPerson) {
						person = p;
						break;
					}
				}
				studierende.add(new Studierender(id, semester, studiengang, person));
			}
			System.out.println(studierende);
			System.out.println("-----------------");

			datenzugriff.setSQL("SELECT * FROM studierender;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				System.out.println(datensatz);
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + ":" + e.getMessage());
		} finally {
			datenzugriff.close();
		}

		System.out.println("Testausgabe Studiengang: " + studierende.get(1).getStudiengang() + " Testausgabe Person: "
				+ studierende.get(1).getPerson());

		try {
			// referenzielle Integrität Stundenplan
			// Slot
			ArrayList<LinkedHashMap<String, String>> daten = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM slot;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				slots.add(new Slot(datensatz));
			}
			System.out.println(slots);
			System.out.println("-----------------");

			// Tag
			ArrayList<LinkedHashMap<String, String>> daten1 = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM tag;");
			daten1 = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten1) {
				tage.add(new Tag(datensatz));
			}
			System.out.println(tage);
			System.out.println("-----------------");

			// Studiengaenge ist schon initialisiert worden

			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM stundenplan;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				int id = Integer.parseInt(datensatz.get("id"));
				int semester = Integer.parseInt(datensatz.get("semester"));
				int idStudiengang = Integer.parseInt(datensatz.get("idStudiengang"));
				int idTag = Integer.parseInt(datensatz.get("idTag"));
				int idSlot = Integer.parseInt(datensatz.get("idSlot"));
				Studiengang studiengang = null;
				for (Studiengang sg : studiengaenge) {
					if (sg.getId() == idStudiengang) {
						studiengang = sg;
						break;
					}
				}

				Tag tag = null;
				for (Tag t : tage) {
					if (t.getId() == idTag) {
						tag = t;
						break;
					}
				}

				Slot slot = null;
				for (Slot s : slots) {
					if (s.getId() == idSlot) {
						slot = s;
						break;
					}
				}

				stundenplaene.add(new Stundenplan(id, semester, studiengang, tag, slot));
			}
			System.out.println(stundenplaene);
			System.out.println("-----------------");

			datenzugriff.setSQL("SELECT * FROM stundenplan;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				System.out.println(datensatz);
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + ":" + e.getMessage());
		} finally {
			datenzugriff.close();
		}
		System.out.println(
				"Testausgabe Stundenplan Studiengang: " + stundenplaene.get(1).getStudiengang() + " Testausgabe Tag: "
						+ stundenplaene.get(1).getTag() + " Testausgabe Slot: " + stundenplaene.get(1).getSlot());

		try {
			// referenzielle Integrität Veranstaltung
			// Veranstaltungsname
			ArrayList<LinkedHashMap<String, String>> daten = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM veranstaltungsname;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				veranstaltungsnamen.add(new Veranstaltungsname(datensatz));
			}
			System.out.println(veranstaltungsnamen);
			System.out.println("-----------------");

			// Dozent
			// Nicht benötigt, da Dozent oben schon angelegt in der ArrayList
			// dozenten

			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM veranstaltung;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				int id = Integer.parseInt(datensatz.get("id"));
				int semester = Integer.parseInt(datensatz.get("semester"));
				int dauer = Integer.parseInt(datensatz.get("dauer"));
				int personalNr = Integer.parseInt(datensatz.get("personalNr"));
				int idStundenplan = Integer.parseInt(datensatz.get("idStundenplan"));
				int idvName = Integer.parseInt(datensatz.get("idvName"));
				Veranstaltungsname veranstaltungsname = null;
				for (Veranstaltungsname vn : veranstaltungsnamen) {
					if (vn.getId() == idvName) {
						veranstaltungsname = vn;
						break;
					}
				}
				Dozent dozent = null;
				for (Dozent d : dozenten) {
					if (d.getPersonalNr() == personalNr) {
						dozent = d;
						break;
					}
				}
				Stundenplan stundenplan = null;
				for (Stundenplan st : stundenplaene) {
					if (st.getId() == idStundenplan) {
						stundenplan = st;
						break;
					}
				}

				veranstaltungen.add(new Veranstaltung(id, semester, dauer, dozent, stundenplan, veranstaltungsname));
			}
			System.out.println(veranstaltungen);
			System.out.println("-----------------");

			datenzugriff.setSQL("SELECT * FROM veranstaltung;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				System.out.println(datensatz);
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + ":" + e.getMessage());
		} finally {
			datenzugriff.close();
		}
		System.out.println("Testausgabe Veranstaltung Dozent: " + veranstaltungen.get(1).getDozent()
				+ " Testausgabe Stundenplan: " + veranstaltungen.get(1).getStundenplan()
				+ " Testausgabe Veranstaltungsname: " + veranstaltungen.get(1).getVeranstaltungsname());

		// Hoert
		try {
			ArrayList<LinkedHashMap<String, String>> daten = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM hoert;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				int id = Integer.parseInt(datensatz.get("id"));
				int matrikelNr = Integer.parseInt(datensatz.get("matrikelNr"));
				Veranstaltung veranstaltung = null;
				for (Veranstaltung v : veranstaltungen) {
					if (v.getId() == id) {
						veranstaltung = v;
						break;
					}
				}
				Studierender studierender = null;
				for (Studierender s : studierende) {
					if (s.getMatrikelNr() == matrikelNr) {
						studierender = s;
						break;
					}
				}
				hoeren.add(new Hoert(veranstaltung, studierender));
			}
			System.out.println(hoeren);
			System.out.println("-----------------");

			datenzugriff.setSQL("SELECT * FROM hoert;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				System.out.println(datensatz);
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + ":" + e.getMessage());
		} finally {
			datenzugriff.close();
		}

		System.out.println("Testausgabe Hoert Veranstaltung: " + hoeren.get(1).getVeranstaltung()
				+ " Testausgabe Studierender: " + hoeren.get(1).getStudierender());

		// hat
		try {
			ArrayList<LinkedHashMap<String, String>> daten = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM raum;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				raeume.add(new Raum(datensatz));
			}
			System.out.println(raeume);
			System.out.println("-----------------");

			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM hat;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				String bezeichnung = datensatz.get("bezeichnung");
				int id = Integer.parseInt(datensatz.get("id"));
				Raum raum = null;
				for (Raum r : raeume) {
					if (r.getBezeichnung().equals(bezeichnung)) {
						raum = r;
						break;
					}
				}
				Stundenplan stundenplan = null;
				for (Stundenplan st : stundenplaene) {
					if (st.getId() == id) {
						stundenplan = st;
						break;
					}
				}
				hatten.add(new Hat(raum, stundenplan));
			}
			System.out.println(hatten);
			System.out.println("-----------------");

			datenzugriff.setSQL("SELECT * FROM hat;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				System.out.println(datensatz);
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + ":" + e.getMessage());
		} finally {
			datenzugriff.close();
		}

		System.out.println("Testausgabe Hat Raum: " + hatten.get(1).getRaum() + " Testausgabe Stundenplan: "
				+ hatten.get(1).getStundenplan());

		// Besitzt
		try {
			ArrayList<LinkedHashMap<String, String>> daten = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM besitzt;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				int id = Integer.parseInt(datensatz.get("id"));
				int matrikelNr = Integer.parseInt(datensatz.get("matrikelNr"));
				Stundenplan stundenplan = null;
				for (Stundenplan st : stundenplaene) {
					if (st.getId() == id) {
						stundenplan = st;
						break;
					}
				}
				Studierender studierender = null;
				for (Studierender s : studierende) {
					if (s.getMatrikelNr() == matrikelNr) {
						studierender = s;
						break;
					}
				}
				besitzen.add(new Besitzt(stundenplan, studierender));
			}
			System.out.println(hoeren);
			System.out.println("-----------------");

			datenzugriff.setSQL("SELECT * FROM besitzt;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				System.out.println(datensatz);
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + ":" + e.getMessage());
		} finally {
			datenzugriff.close();
		}

		System.out.println("Testausgabe Besitzt Stundenplan: " + besitzen.get(1).getStundenplan()
				+ " Testausgabe Studierender: " + besitzen.get(1).getStudierender());

		// Erhaelt
		try {
			ArrayList<LinkedHashMap<String, String>> daten = null;
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM erhaelt;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				int personalNr = Integer.parseInt(datensatz.get("personalNr"));
				int id = Integer.parseInt(datensatz.get("id"));
				Dozent dozent = null;
				for (Dozent d : dozenten) {
					if (d.getPersonalNr() == personalNr) {
						dozent = d;
						break;
					}
				}
				Stundenplan stundenplan = null;
				for (Stundenplan st : stundenplaene) {
					if (st.getId() == id) {
						stundenplan = st;
						break;
					}
				}

				erhalten.add(new Erhaelt(dozent, stundenplan));
			}
			System.out.println(erhalten);
			System.out.println("-----------------");

			datenzugriff.setSQL("SELECT * FROM erhaelt;");
			daten = datenzugriff.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				System.out.println(datensatz);
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + ":" + e.getMessage());
		} finally {
			datenzugriff.close();
		}

		System.out.println("Testausgabe Erhaelt Dozent: " + erhalten.get(1).getDozent() + " Testausgabe Stundenplan: "
				+ erhalten.get(1).getStundenplan());
	}

	//person
	public static void personHinzufuegen(ArrayList<Person> personen, int id, String vorname, String nachname, String geburtsdatum, boolean maennlich) {
		// Person hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertPerson(vorname, nachname, geburtsdatum, maennlich);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
		// Person hinzufügen (java)
		if (dbEinfuegen) {
			try {
				personen.add(new Person(id, vorname, nachname, geburtsdatum, maennlich));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void personAendern(ArrayList<Person> personen, int id, String vorname, String nachname,
			String geburtsdatum, boolean maennlich) {
		// Person ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updatePerson(vorname, nachname, geburtsdatum, maennlich, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
		// Person ändern (java)
		if (dbAendern) {
			for (Person x : personen) {
				if (id == x.getId()) {
					if (x.aendern(vorname, nachname, geburtsdatum, maennlich)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void personLoeschen(ArrayList<Person> personen, int id) {
		// Person ändern DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deletePerson(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
		// Person ändern (java)
		if (dbLoeschen) {
			try {
				for (Person x : personen) {
					if (id == x.getId()) {
						personen.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Dozent
	public static void dozentHinzufuegen(ArrayList<Dozent> dozenten, int personalNr, String kuerzel, Fakultaet fakultaet, Person person) {
		// Dozent hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Dozent hinzufügen (java)
		if (dbEinfuegen) {
			try {
				dozenten.add(new Dozent(personalNr, kuerzel, fakultaet, person));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void dozentAendern(ArrayList<Dozent> dozenten, int personalNr, String kuerzel, Fakultaet fakultaet, Person person) {
		// Dozent ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
		// Dozent ändern (java)
		if (dbAendern) {
			for (Dozent x : dozenten) {
				if (personalNr == x.getPersonalNr()) {
					if (x.aendern(kuerzel, fakultaet, person)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void dozentLoeschen(ArrayList<Dozent> dozenten, int personalNr) {
		// Dozent löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbLoeschen = datenzugriff.deleteDozent(personalNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Dozent löschen (java)
		if (dbLoeschen) {
			try {
				for (Dozent x : dozenten) {
					if (personalNr == x.getPersonalNr()) {
						dozenten.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Fakultaet
	public static void fakultaetHinzufuegen(ArrayList<Fakultaet> fakultaeten,int id, String name) {
		// Fakultaet hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Fakultaet hinzufügen (java)
		if (dbEinfuegen) {
			try {
				fakultaeten.add(new Fakultaet(id, name));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
			// Test ausgabe
			for (Fakultaet x : fakultaeten) {
				if (id == x.getId()) {
					System.out.println("Test Ausgabe C1:");
					System.out.println(x);
					break;
				}
			}
		}
	}

	public static void fakultaetAendern(ArrayList<Fakultaet> fakultaeten,int id, String name) {
		// Dozent ändern DB
//		DB datenzugriff = null;
		boolean dbAendern = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}
		
		// Fakultaet ändern (java)
		if (dbAendern) {
			for (Fakultaet x : fakultaeten) {
				if (id == x.getId()) {
					if (x.aendern(name)) {
						System.out.println("Test Ausgabe C2:");
						System.out.println(x);
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void fakultaetLoeschen(ArrayList<Fakultaet> fakultaeten, int id) {
		// Dozent löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Dozent löschen (java)
		if (dbLoeschen) {
			try {
				for (Fakultaet x : fakultaeten) {
					if (id == x.getId()) {
						fakultaeten.remove(x);
						for(Fakultaet i: fakultaeten)
						System.out.println(i);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Studierender
	public static void studierenderHinzufuegen(ArrayList<Studierender> studierende, int matrikelNr, int semester, Studiengang studiengang, Person person) {
		// Studierender hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Studierender hinzufügen (java)
		if (dbEinfuegen) {
			try {
				studierende.add(new Studierender(matrikelNr, semester, studiengang, person));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void studierenderAendern(ArrayList<Studierender> studierende, int matrikelNr, int semester, Studiengang studiengang, Person person) {
		// Studierender ändern DB
//		DB datenzugriff = null;
		boolean dbAendern = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}
		
		// Studierender ändern (java)
		if (dbAendern) {
			for (Studierender x : studierende) {
				if (matrikelNr == x.getMatrikelNr()) {
					if (x.aendern(semester, studiengang, person)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void studierenderLoeschen(ArrayList<Studierender> studierende, int matrikelNr) {
		// Studierender löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Studierender löschen (java)
		if (dbLoeschen) {
			try {
				for (Studierender x : studierende) {
					if (matrikelNr == x.getMatrikelNr()) {
						studierende.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Veranstaltung
	public static void veranstaltungHinzufuegen(ArrayList<Veranstaltung>veranstaltungen, int id, int semester, int dauer, Dozent dozent, Stundenplan stundenplan, Veranstaltungsname veranstaltungsname) {
		// Veranstaltung hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Veranstaltung hinzufügen (java)
		if (dbEinfuegen) {
			try {
				veranstaltungen.add(new Veranstaltung(id, semester,dauer, dozent, stundenplan, veranstaltungsname));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void veranstaltungAendern(ArrayList<Veranstaltung>veranstaltungen, int id, int semester, int dauer, Dozent dozent, Stundenplan stundenplan, Veranstaltungsname veranstaltungsname) {
		// Veranstaltung ändern DB
//		DB datenzugriff = null;
		boolean dbAendern = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}
		
		// Veranstaltung ändern (java)
		if (dbAendern) {
			for (Veranstaltung x : veranstaltungen) {
				if (id == x.getId()) {
					if (x.aendern(semester, dauer, dozent, stundenplan, veranstaltungsname)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void veranstaltungLoeschen(ArrayList<Veranstaltung>veranstaltungen, int id) {
		// Veranstaltung löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Veranstaltung löschen (java)
		if (dbLoeschen) {
			try {
				for (Veranstaltung x : veranstaltungen) {
					if (id == x.getId()) {
						veranstaltungen.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Veranstaltungsname
	public static void veranstaltungsnameHinzufuegen(ArrayList<Veranstaltungsname>veranstaltungsnamen, int id, String name, String kuerzel) {
		// Veranstaltungsname hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Veranstaltungsname hinzufügen (java)
		if (dbEinfuegen) {
			try {
				veranstaltungsnamen.add(new Veranstaltungsname(id, name, kuerzel));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void veranstaltungsnameAendern(ArrayList<Veranstaltungsname>veranstaltungsnamen, int id, String name, String kuerzel) {
		// Veranstaltungsname ändern DB
//		DB datenzugriff = null;
		boolean dbAendern = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}
		
		// Veranstaltungsname ändern (java)
		if (dbAendern) {
			for (Veranstaltungsname x : veranstaltungsnamen) {
				if (id == x.getId()) {
					if (x.aendern(name, kuerzel)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void veranstaltungsnameLoeschen(ArrayList<Veranstaltungsname>veranstaltungsnamen, int id) {
		// Veranstaltungsname löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Veranstaltungsname löschen (java)
		if (dbLoeschen) {
			try {
				for (Veranstaltungsname x : veranstaltungsnamen) {
					if (id == x.getId()) {
						veranstaltungsnamen.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Slot
	public static void slotHinzufuegen(ArrayList<Slot>slots, int id, String slot) {
		// Slot hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Slot hinzufügen (java)
		if (dbEinfuegen) {
			try {
				slots.add(new Slot(id, slot));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void slotAendern(ArrayList<Slot>slots, int id, String slot) {
		// Slot ändern DB
//		DB datenzugriff = null;
		boolean dbAendern = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}
		
		// Slot ändern (java)
		if (dbAendern) {
			for (Slot x : slots) {
				if (id == x.getId()) {
					if (x.aendern(slot)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void slotLoeschen(ArrayList<Slot>slots, int id) {
		// Slot löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Slot löschen (java)
		if (dbLoeschen) {
			try {
				for (Slot x : slots) {
					if (id == x.getId()) {
						slots.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Tag
	public static void tagHinzufuegen(ArrayList<Tag>tage, int id, String tag) {
		// Tag hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Tag hinzufügen (java)
		if (dbEinfuegen) {
			try {
				tage.add(new Tag(id, tag));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void tagAendern(ArrayList<Tag>tage, int id, String tag) {
		// Tag ändern DB
//		DB datenzugriff = null;
		boolean dbAendern = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}
		
		// Tag ändern (java)
		if (dbAendern) {
			for (Tag x : tage) {
				if (id == x.getId()) {
					if (x.aendern(tag)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void tagLoeschen(ArrayList<Tag>tage, int id) {
		// Tag löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Tag löschen (java)
		if (dbLoeschen) {
			try {
				for (Tag x : tage) {
					if (id == x.getId()) {
						tage.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Studiengang
	public static void studiengangHinzufuegen(ArrayList<Studiengang>studiengaenge, int id, String name) {
		// Studiengang hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Studiengang hinzufügen (java)
		if (dbEinfuegen) {
			try {
				studiengaenge.add(new Studiengang(id, name));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void studiengangAendern(ArrayList<Studiengang>studiengaenge, int id, String name) {
		// Studiengang ändern DB
//		DB datenzugriff = null;
		boolean dbAendern = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}
		
		// Studiengang ändern (java)
		if (dbAendern) {
			for (Studiengang x : studiengaenge) {
				if (id == x.getId()) {
					if (x.aendern(name)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void studiengangLoeschen(ArrayList<Studiengang>studiengaenge, int id) {
		// Studiengang löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Studiengang löschen (java)
		if (dbLoeschen) {
			try {
				for (Studiengang x : studiengaenge) {
					if (id == x.getId()) {
						studiengaenge.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Stundenplan
	public static void stundenplanHinzufuegen(ArrayList<Stundenplan>stundenplaene, int id, int semester, Studiengang studiengang, Tag tag, Slot slot) {
		// Stundenplan hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Stundenplan hinzufügen (java)
		if (dbEinfuegen) {
			try {
				stundenplaene.add(new Stundenplan(id, semester, studiengang, tag, slot));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void stundenplanAendern(ArrayList<Stundenplan>stundenplaene, int id, int semester, Studiengang studiengang, Tag tag, Slot slot) {
		// Stundenplan ändern DB
//		DB datenzugriff = null;
		boolean dbAendern = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}
		
		// Stundenplan ändern (java)
		if (dbAendern) {
			for (Stundenplan x : stundenplaene) {
				if (id == x.getId()) {
					if (x.aendern(semester, studiengang, tag, slot)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void stundenplanLoeschen(ArrayList<Stundenplan>stundenplaene, int id) {
		// Stundenplan löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Stundenplan löschen (java)
		if (dbLoeschen) {
			try {
				for (Stundenplan x : stundenplaene) {
					if (id == x.getId()) {
						stundenplaene.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Raum
	public static void raumHinzufuegen(ArrayList<Raum>raeume, String bezeichnung, boolean computerraum) {
		// Raum hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Raum hinzufügen (java)
		if (dbEinfuegen) {
			try {
				raeume.add(new Raum(bezeichnung, computerraum));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
			// Test ausgabe
			for (Raum x : raeume) {
				if (bezeichnung.equals(x.getBezeichnung())) {
					break;
				}
			}
		}
	}

	public static void raumAendern(ArrayList<Raum>raeume, String bezeichnung, boolean computerraum) {
		// Raum ändern DB
//		DB datenzugriff = null;
		boolean dbAendern = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbAendern = datenzugriff.updateDozent(kuerzel, fakultaet.getId(), person.getId(), personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}
		
		// Raum ändern (java)
		if (dbAendern) {
			for (Raum x : raeume) {
				if (bezeichnung.equals(x.getBezeichnung())) {
					if (x.aendern(computerraum)) {
						break;
					} else {
						// TODO Fehler Meldung schreiben
					}
				}
			}
		}
	}

	public static void raumLoeschen(ArrayList<Raum>raeume, String bezeichnung) {
		// Raum löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Raum löschen (java)
		if (dbLoeschen) {
			try {
				for (Raum x : raeume) {
					if (x.getBezeichnung().equals(bezeichnung)) {
						raeume.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Besitzt
	public static void besitztHinzufuegen(ArrayList<Besitzt>besitzen,Stundenplan stundenplan, Studierender studierender) {
		// Besitzt hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Besitzt hinzufügen (java)
		if (dbEinfuegen) {
			try {
				besitzen.add(new Besitzt(stundenplan, studierender));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void besitztLoeschen(ArrayList<Besitzt>besitzen,Stundenplan stundenplan, Studierender studierender) {
		// Besitzt löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Besitzt löschen (java)
		if (dbLoeschen) {
			try {
				for (Besitzt x : besitzen) {
					if (x.getStundenplan().equals(stundenplan) && x.getStudierender().equals(studierender)) {
						besitzen.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Erhaelt
	public static void erhaeltHinzufuegen(ArrayList<Erhaelt>erhalten, Dozent dozent, Stundenplan stundenplan) {
		// Erhaelt hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Erhaelt hinzufügen (java)
		if (dbEinfuegen) {
			try {
				erhalten.add(new Erhaelt(dozent, stundenplan));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void erhaeltLoeschen(ArrayList<Erhaelt>erhalten, Dozent dozent, Stundenplan stundenplan) {
		// Erhaelt löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Erhaelt löschen (java)
		if (dbLoeschen) {
			try {
				for (Erhaelt x : erhalten) {
					if (x.getDozent().equals(dozent) && x.getStundenplan().equals(stundenplan)) {
						erhalten.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Hoert
	public static void hoertHinzufuegen(ArrayList<Hoert>hoeren, Veranstaltung veranstaltung, Studierender studierender) {
		// Hoert hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Hoert hinzufügen (java)
		if (dbEinfuegen) {
			try {
				hoeren.add(new Hoert(veranstaltung, studierender));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
			// Test ausgabe
			for (Hoert x : hoeren) {
				if (x.getVeranstaltung().equals(veranstaltung) && x.getStudierender().equals(studierender)) {
					break;
				}
			}
		}
	}

	public static void hoertLoeschen(ArrayList<Hoert>hoeren, Veranstaltung veranstaltung, Studierender studierender) {
		// Hoert löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Hoert löschen (java)
		if (dbLoeschen) {
			try {
				for (Hoert x : hoeren) {
					if (x.getVeranstaltung().equals(veranstaltung) && x.getStudierender().equals(studierender)) {
						hoeren.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	
	//Hat
	public static void hatHinzufuegen(ArrayList<Hat>hatten, Raum raum, Stundenplan stundenplan) {
		// Hat hinzufügen DB
//		DB datenzugriff = null;
		boolean dbEinfuegen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB einfügen erfolgreich
//			dbEinfuegen = datenzugriff.insertDozent(kuerzel, fakultaet.getId(), person.getId());
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Hat hinzufügen (java)
		if (dbEinfuegen) {
			try {
				hatten.add(new Hat(raum, stundenplan));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public static void hatLoeschen(ArrayList<Hat>hatten, Raum raum, Stundenplan stundenplan) {
		// Hat löschen DB
//		DB datenzugriff = null;
		boolean dbLoeschen = false;
//		try{
//			datenzugriff = new DB("studierendenverwaltung", "root", "");
//			// boolean um zu testen ob DB ändern erfolgreich
//			dbLoeschen = datenzugriff.deleteDozent(personalNr);
//		}
//		catch(Exception e){
//			// TODO
//		}
//		finally{
//			datenzugriff.datenzugriffSchließen();
//			datenzugriff = null;
//		}

		// Hat löschen (java)
		if (dbLoeschen) {
			try {
				for (Hat x : hatten) {
					if (x.getRaum().equals(raum) && x.getStundenplan().equals(stundenplan)) {
						hatten.remove(x);
					}
				}
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}
	//TODO weitere klassen
	
}
