import java.util.ArrayList;
import java.util.LinkedHashMap;

import db.DB;

public class Studierendenverwaltung {

	// public static void main(String[] args) {
	// GUI stud = new GUI();
	// stud.LayoutGUI("Studierendenverwaltung");
	// }
	private ArrayList<Fakultaet> fakultaeten;
	private ArrayList<Person> personen;
	private ArrayList<Dozent> dozenten;
	private ArrayList<Studiengang> studiengaenge;
	private ArrayList<Studierender> studierende;
	private ArrayList<Slot> slots;
	private ArrayList<Tag> tage;
	private ArrayList<Stundenplan> stundenplaene;
	private ArrayList<Veranstaltungsname> veranstaltungsnamen;
	private ArrayList<Veranstaltung> veranstaltungen;
	private ArrayList<Hoert> hoeren;
	private ArrayList<Raum> raeume;
	private ArrayList<Hat> hatten;
	private ArrayList<Besitzt> besitzen;
	private ArrayList<Erhaelt> erhalten;
	private DB datenzugriff = null;
	
	public Studierendenverwaltung(){
		fakultaeten = new ArrayList<>();
		personen = new ArrayList<>();
		dozenten = new ArrayList<>();
		studiengaenge = new ArrayList<>();
		studierende = new ArrayList<>();
		slots = new ArrayList<>();
		tage = new ArrayList<>();
		stundenplaene = new ArrayList<>();
		veranstaltungsnamen = new ArrayList<>();
		veranstaltungen = new ArrayList<>();
		hoeren = new ArrayList<>();
		raeume = new ArrayList<>();
		hatten = new ArrayList<>();
		besitzen = new ArrayList<>();
		erhalten = new ArrayList<>();
		
		DatenAusDbEinlesen();
		
	}

	public static void main(String[] args) {
		Studierendenverwaltung s = new Studierendenverwaltung();
		s.personHinzufuegen("test", "test", "2018-01-01", true);
	}

	public void DatenAusDbEinlesen() {

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
	public void personHinzufuegen(String vorname, String nachname, String geburtsdatum, boolean maennlich) {
		// Person hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int id = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertPerson(vorname, nachname, geburtsdatum, maennlich);
			// id aus DB holen
			id = datenzugriff.getPersonId(vorname, nachname, geburtsdatum, maennlich);
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
				if(id<1){
					throw new RuntimeException("Ungültige id");
				}
				personen.add(new Person(id, vorname, nachname, geburtsdatum, maennlich));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void personAendern( int id, String vorname, String nachname,
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

	public void personLoeschen(int id) {
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
	public void dozentHinzufuegen(int personalNr, String kuerzel, Fakultaet fakultaet, Person person) {
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

	public void dozentAendern(int personalNr, String kuerzel, Fakultaet fakultaet, Person person) {
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

	public void dozentLoeschen(int personalNr) {
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
	public void fakultaetHinzufuegen(int id, String name) {
		// Fakultaet hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertFakultaet(name);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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

	public void fakultaetAendern( int id, String name) {
		// Fakultaet ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateFakultaet(name, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
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

	public void fakultaetLoeschen(int id) {
		// Fakultaet löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteFakultaet(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Fakultaet löschen (java)
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
	public void studierenderHinzufuegen(int matrikelNr, int semester, Studiengang studiengang, Person person) {
		// Studierender hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertStudierender(semester, studiengang.getId(), person.getId());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Studierender hinzufügen (java)
		if (dbEinfuegen) {
			try {
				studierende.add(new Studierender(matrikelNr, semester, studiengang, person));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void studierenderAendern(int matrikelNr, int semester, Studiengang studiengang, Person person) {
		// Studierender ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateStudierender(semester, studiengang.getId(), person.getId(), matrikelNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
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

	public void studierenderLoeschen( int matrikelNr) {
		// Studierender löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteStudierender(matrikelNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void veranstaltungHinzufuegen(int id, int semester, int dauer, Dozent dozent, Stundenplan stundenplan, Veranstaltungsname veranstaltungsname) {
		// Veranstaltung hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertVeranstaltung(semester, dauer, dozent.getPersonalNr(), stundenplan.getId(), veranstaltungsname.getId());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Veranstaltung hinzufügen (java)
		if (dbEinfuegen) {
			try {
				veranstaltungen.add(new Veranstaltung(id, semester,dauer, dozent, stundenplan, veranstaltungsname));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void veranstaltungAendern(int id, int semester, int dauer, Dozent dozent, Stundenplan stundenplan, Veranstaltungsname veranstaltungsname) {
		// Veranstaltung ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateVeranstaltung(semester, dauer, dozent.getPersonalNr(), stundenplan.getId(), veranstaltungsname.getId(), id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
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

	public void veranstaltungLoeschen(int id) {
		// Veranstaltung löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteVeranstaltung(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void veranstaltungsnameHinzufuegen(int id, String name, String kuerzel) {
		// Veranstaltungsname hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertVeranstaltungsname(name, kuerzel);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Veranstaltungsname hinzufügen (java)
		if (dbEinfuegen) {
			try {
				veranstaltungsnamen.add(new Veranstaltungsname(id, name, kuerzel));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void veranstaltungsnameAendern(int id, String name, String kuerzel) {
		// Veranstaltungsname ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateVeranstaltungsname(name, kuerzel, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
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

	public  void veranstaltungsnameLoeschen(int id) {
		// Veranstaltungsname löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteVeranstaltungsname(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void slotHinzufuegen(int id, String slot) {
		// Slot hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertSlot(Integer.parseInt(slot));
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Slot hinzufügen (java)
		if (dbEinfuegen) {
			try {
				slots.add(new Slot(id, slot));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void slotAendern(int id, String slot) {
		// Slot ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateSlot(Integer.parseInt(slot), id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
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

	public void slotLoeschen(int id) {
		// Slot löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteSlot(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void tagHinzufuegen(int id, String tag) {
		// Tag hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertTag(tag);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Tag hinzufügen (java)
		if (dbEinfuegen) {
			try {
				tage.add(new Tag(id, tag));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void tagAendern(int id, String tag) {
		// Tag ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateTag(tag, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
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

	public void tagLoeschen(int id) {
		// Tag löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteTag(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void studiengangHinzufuegen(int id, String name) {
		// Studiengang hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertStudiengang(name);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Studiengang hinzufügen (java)
		if (dbEinfuegen) {
			try {
				studiengaenge.add(new Studiengang(id, name));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void studiengangAendern(int id, String name) {
		// Studiengang ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateStudiengang(name, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
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

	public void studiengangLoeschen(int id) {
		// Studiengang löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteStudiengang(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void stundenplanHinzufuegen(int id, int semester, Studiengang studiengang, Tag tag, Slot slot) {
		// Stundenplan hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertStundenplan(semester, studiengang.getId(), tag.getId(), slot.getId());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Stundenplan hinzufügen (java)
		if (dbEinfuegen) {
			try {
				stundenplaene.add(new Stundenplan(id, semester, studiengang, tag, slot));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void stundenplanAendern(int id, int semester, Studiengang studiengang, Tag tag, Slot slot) {
		// Stundenplan ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateStundenplan(semester, studiengang.getId(), tag.getId(), slot.getId(), id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
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

	public void stundenplanLoeschen(int id) {
		// Stundenplan löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteStundenplan(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void raumHinzufuegen(String bezeichnung, boolean computerraum) {
		// Raum hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertRaum(bezeichnung, computerraum);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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

	public void raumAendern(String bezeichnung, boolean computerraum) {
		// Raum ändern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB ändern erfolgreich
			dbAendern = datenzugriff.updateRaum(computerraum, bezeichnung);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}
		
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

	public void raumLoeschen(String bezeichnung) {
		// Raum löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteRaum(bezeichnung);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void besitztHinzufuegen(Stundenplan stundenplan, Studierender studierender) {
		// Besitzt hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertBesitzt(stundenplan.getId(), studierender.getMatrikelNr());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Besitzt hinzufügen (java)
		if (dbEinfuegen) {
			try {
				besitzen.add(new Besitzt(stundenplan, studierender));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void besitztLoeschen(Stundenplan stundenplan, Studierender studierender) {
		// Besitzt löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteBesitzt(stundenplan.getId(), studierender.getMatrikelNr());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void erhaeltHinzufuegen(Dozent dozent, Stundenplan stundenplan) {
		// Erhaelt hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertErhaelt(dozent.getPersonalNr(), stundenplan.getId());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Erhaelt hinzufügen (java)
		if (dbEinfuegen) {
			try {
				erhalten.add(new Erhaelt(dozent, stundenplan));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void erhaeltLoeschen(Dozent dozent, Stundenplan stundenplan) {
		// Erhaelt löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteErhaelt(dozent.getPersonalNr(), stundenplan.getId());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void hoertHinzufuegen(Veranstaltung veranstaltung, Studierender studierender) {
		// Hoert hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertHoert(veranstaltung.getId(), studierender.getMatrikelNr());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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

	public void hoertLoeschen( Veranstaltung veranstaltung, Studierender studierender) {
		// Hoert löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteHoert(veranstaltung.getId(), studierender.getMatrikelNr());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
	public void hatHinzufuegen(Raum raum, Stundenplan stundenplan) {
		// Hat hinzufügen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einfügen erfolgreich
			dbEinfuegen = datenzugriff.insertHat(raum.getBezeichnung(), stundenplan.getId());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

		// Hat hinzufügen (java)
		if (dbEinfuegen) {
			try {
				hatten.add(new Hat(raum, stundenplan));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void hatLoeschen(Raum raum, Stundenplan stundenplan) {
		// Hat löschen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB löschen erfolgreich
			dbLoeschen = datenzugriff.deleteHat(raum.getBezeichnung(), stundenplan.getId());
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchließen();
			datenzugriff = null;
		}

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
