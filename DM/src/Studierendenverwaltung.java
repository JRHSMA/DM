import java.util.ArrayList;
import java.util.LinkedHashMap;

import db.DB;

public class Studierendenverwaltung {

	// public static void main(String[] args) {
	// GUI stud = new GUI();
	// stud.LayoutGUI("Studierendenverwaltung");
	// }
	
	// TODO static wegmachen!
	private ArrayList<Fakultaet> fakultaeten;
	private static ArrayList<Person> personen;
	private static ArrayList<Dozent> dozenten;
	private ArrayList<Studiengang> studiengaenge;
	private static ArrayList<Studierender> studierende;
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

	// TODO main l�schen wenn fertig mit testen
	public static void main(String[] args) {
		Studierendenverwaltung s = new Studierendenverwaltung();
		//person hinzuf�gen
		s.personHinzufuegen("testtest", "test", "2018-01-01", true);
		for(Person p : personen){
			System.out.println(p);
		}
		// dozent hinzuf�gen
		// TODO keine fehlermeldung etc. wenn kuerzel zu lang
		s.dozentHinzufuegen("XXX", 1, 36);
		for(Dozent d : dozenten){
			System.out.println(d);
		}
		// studierender hinzuf�gen
		s.studierenderHinzufuegen(3, 1, 33);
		for(Studierender stud : studierende){
			System.out.println(stud);
		}
	}

	public void DatenAusDbEinlesen() {

		try {
			// referenzielle Integrit�t Dozent
			// Fakult�t
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
		System.out.println("Testausgabe Fakult�t: " + dozenten.get(1).getFakultaet() + " Testausgabe Person: "
				+ dozenten.get(1).getPerson());

		try {
			// referenzielle Integrit�t Studierender
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
			// referenzielle Integrit�t Stundenplan
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
			// referenzielle Integrit�t Veranstaltung
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
			// Nicht ben�tigt, da Dozent oben schon angelegt in der ArrayList
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
		// Person hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int id = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertPerson(vorname, nachname, geburtsdatum, maennlich);
			// id aus DB holen
			id = datenzugriff.getPersonId(vorname, nachname, geburtsdatum, maennlich);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Person hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(id<1){
					throw new RuntimeException("Ung�ltige id");
				}
				personen.add(new Person(id, vorname, nachname, geburtsdatum, maennlich));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void personAendern( int id, String vorname, String nachname,
			String geburtsdatum, boolean maennlich) {
		// Person �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updatePerson(vorname, nachname, geburtsdatum, maennlich, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Person �ndern (java)
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
		// Person �ndern DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deletePerson(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Person �ndern (java)
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
	// TODO keine Objekte �bergeben!
	public void dozentHinzufuegen(String kuerzel, int idFakultaet, int idPerson) {
		// Fakultaets- und Personenobjekt holen f�r Konstruktor
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
		
		// Dozent hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int personalNr = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertDozent(kuerzel, idFakultaet, idPerson);
			personalNr = datenzugriff.getDozentPersonalNr(kuerzel);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Dozent hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(personalNr<1){
					throw new RuntimeException("Ung�ltige personalNr");
				}
				dozenten.add(new Dozent(personalNr, kuerzel, fakultaet, person));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void dozentAendern(int personalNr, String kuerzel, int idFakultaet, int idPerson) {
		// Fakultaets- und Personenobjekt holen f�r Konstruktor
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
		
		// Dozent �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateDozent(kuerzel, idFakultaet, idPerson, personalNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Dozent �ndern (java)
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
		// Dozent l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbLoeschen = datenzugriff.deleteDozent(personalNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Dozent l�schen (java)
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
	public void fakultaetHinzufuegen(String name) {
		// Fakultaet hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int id = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertFakultaet(name);
			id = datenzugriff.getFakultaetId(name);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Fakultaet hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(id<1){
					throw new RuntimeException("Ung�ltige id");
				}
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

	public void fakultaetAendern(int id, String name) {
		// Fakultaet �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateFakultaet(name, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Fakultaet �ndern (java)
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
		// Fakultaet l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteFakultaet(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Fakultaet l�schen (java)
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
	public void studierenderHinzufuegen(int semester, int idStudiengang, int idPerson) {
		// Studiengangs- und Personenobjekt holen f�r Konstruktor
		Studiengang studiengang = null;
		for (Studiengang s : studiengaenge) {
			if (s.getId() == idStudiengang) {
				studiengang = s;
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
		
		// Studierender hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int matrikelNr = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertStudierender(semester, idStudiengang, idPerson);
			matrikelNr = datenzugriff.getStudierenderMatrikelNr(semester, idStudiengang, idPerson);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Studierender hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(matrikelNr<1){
					throw new RuntimeException("Ung�ltige matrikelNr");
				}
				studierende.add(new Studierender(matrikelNr, semester, studiengang, person));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void studierenderAendern(int matrikelNr, int semester, int idStudiengang, int idPerson) {
		// Studiengangs- und Personenobjekt holen f�r Konstruktor
		Studiengang studiengang = null;
		for (Studiengang s : studiengaenge) {
			if (s.getId() == idStudiengang) {
				studiengang = s;
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
		
		// Studierender �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateStudierender(semester, idStudiengang, idPerson, matrikelNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Studierender �ndern (java)
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

	public void studierenderLoeschen(int matrikelNr) {
		// Studierender l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteStudierender(matrikelNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Studierender l�schen (java)
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
	// TODO keine Objekte �bergeben!
	public void veranstaltungHinzufuegen(int semester, int dauer, int personalNr, int idStundenplan, int idvName) {
		// Dozenten-, Stundenplan- und Veranstaltungsnamenobjekt holen f�r Konstruktor
		Dozent dozent = null;
		for(Dozent d : dozenten){
			if(d.getPersonalNr() == personalNr){
				dozent = d;
			}
		}
		
		Stundenplan stundenplan = null;
		for(Stundenplan s : stundenplaene){
			if(s.getId() == idStundenplan){
				stundenplan = s;
			}
		}
		
		Veranstaltungsname veranstaltungsname = null;
		for(Veranstaltungsname v : veranstaltungsnamen){
			if(v.getId() == idvName){
				veranstaltungsname = v;
			}
		}
		
		// Veranstaltung hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int id = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertVeranstaltung(semester, dauer, personalNr, idStundenplan, idvName);
			// TODO paramter mit getIDs austauschen
			id = datenzugriff.getVeranstaltungId(semester, dauer, personalNr, idStundenplan, idvName);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Veranstaltung hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(id<1){
					throw new RuntimeException("Ung�ltige id");
				}
				veranstaltungen.add(new Veranstaltung(id, semester,dauer, dozent, stundenplan, veranstaltungsname));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void veranstaltungAendern(int id, int semester, int dauer, int personalNr, int idStundenplan, int idvName) {
		// Dozenten-, Stundenplan- und Veranstaltungsnamenobjekt holen f�r Konstruktor
		Dozent dozent = null;
		for(Dozent d : dozenten){
			if(d.getPersonalNr() == personalNr){
				dozent = d;
			}
		}
		
		Stundenplan stundenplan = null;
		for(Stundenplan s : stundenplaene){
			if(s.getId() == idStundenplan){
				stundenplan = s;
			}
		}
		
		Veranstaltungsname veranstaltungsname = null;
		for(Veranstaltungsname v : veranstaltungsnamen){
			if(v.getId() == idvName){
				veranstaltungsname = v;
			}
		}

		// Veranstaltung �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateVeranstaltung(semester, dauer, personalNr, idStundenplan, idvName, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Veranstaltung �ndern (java)
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
		// Veranstaltung l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteVeranstaltung(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Veranstaltung l�schen (java)
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
	public void veranstaltungsnameHinzufuegen(String name, String kuerzel) {
		// Veranstaltungsname hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int id = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertVeranstaltungsname(name, kuerzel);
			id = datenzugriff.getVeranstaltungnameId(name, kuerzel);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Veranstaltungsname hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(id<1){
					throw new RuntimeException("Ung�ltige id");
				}
				veranstaltungsnamen.add(new Veranstaltungsname(id, name, kuerzel));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void veranstaltungsnameAendern(int id, String name, String kuerzel) {
		// Veranstaltungsname �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateVeranstaltungsname(name, kuerzel, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Veranstaltungsname �ndern (java)
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
		// Veranstaltungsname l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteVeranstaltungsname(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Veranstaltungsname l�schen (java)
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
	public void slotHinzufuegen(String slot) {
		// Slot hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int id = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertSlot(Integer.parseInt(slot));
			id = datenzugriff.getSlotId(slot);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Slot hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(id<1){
					throw new RuntimeException("Ung�ltige id");
				}
				slots.add(new Slot(id, slot));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void slotAendern(int id, String slot) {
		// Slot �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateSlot(Integer.parseInt(slot), id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Slot �ndern (java)
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
		// Slot l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteSlot(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Slot l�schen (java)
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
	public void tagHinzufuegen(String tag) {
		// Tag hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int id = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertTag(tag);
			id = datenzugriff.getTagId(tag);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Tag hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(id<1){
					throw new RuntimeException("Ung�ltige id");
				}
				tage.add(new Tag(id, tag));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void tagAendern(int id, String tag) {
		// Tag �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateTag(tag, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Tag �ndern (java)
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
		// Tag l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteTag(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Tag l�schen (java)
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
	public void studiengangHinzufuegen(String name) {
		// Studiengang hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int id = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertStudiengang(name);
			id = datenzugriff.getStudiengangId(name);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Studiengang hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(id<1){
					throw new RuntimeException("Ung�ltige id");
				}
				studiengaenge.add(new Studiengang(id, name));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void studiengangAendern(int id, String name) {
		// Studiengang �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateStudiengang(name, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Studiengang �ndern (java)
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
		// Studiengang l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteStudiengang(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Studiengang l�schen (java)
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
	public void stundenplanHinzufuegen(int semester, int idStudiengang, int idTag, int idSlot) {
		// Studiengangs-, Tag- und Slotobjekt holen f�r Konstruktor
		Studiengang studiengang = null;
		for(Studiengang s : studiengaenge){
			if(s.getId() == idStudiengang){
				studiengang = s;
			}
		}
		
		Tag tag = null;
		for(Tag t : tage){
			if(t.getId() == idTag){
				tag = t;
			}
		}
		
		Slot slot = null;
		for(Slot sl : slots){
			if(sl.getId() == idSlot){
				slot = sl;
			}
		}
			
		// Stundenplan hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		int id = -1;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertStundenplan(semester, idStudiengang, idTag, idSlot);
			id = datenzugriff.getStundenplanId(semester, idStudiengang, idTag, idSlot);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Stundenplan hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				if(id<1){
					throw new RuntimeException("Ung�ltige id");
				}
				stundenplaene.add(new Stundenplan(id, semester, studiengang, tag, slot));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void stundenplanAendern(int id, int semester, int idStudiengang, int idTag, int idSlot) {
		// Studiengangs-, Tag- und Slotobjekt holen f�r Konstruktor
		Studiengang studiengang = null;
		for(Studiengang s : studiengaenge){
			if(s.getId() == idStudiengang){
				studiengang = s;
			}
		}
		
		Tag tag = null;
		for(Tag t : tage){
			if(t.getId() == idTag){
				tag = t;
			}
		}
		
		Slot slot = null;
		for(Slot sl : slots){
			if(sl.getId() == idSlot){
				slot = sl;
			}
		}
		
		// Stundenplan �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateStundenplan(semester, idStudiengang, idTag, idSlot, id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Stundenplan �ndern (java)
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
		// Stundenplan l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteStundenplan(id);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Stundenplan l�schen (java)
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
		// Raum hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertRaum(bezeichnung, computerraum);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Raum hinzuf�gen (java)
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
		// Raum �ndern DB
		DB datenzugriff = null;
		boolean dbAendern = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB �ndern erfolgreich
			dbAendern = datenzugriff.updateRaum(computerraum, bezeichnung);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}
		
		// Raum �ndern (java)
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
		// Raum l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteRaum(bezeichnung);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Raum l�schen (java)
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
	public void besitztHinzufuegen(int idStundenplan, int matrikelNr) {
		// Stundenplan- und Studierenderobjekt holen f�r Konstruktor
		Stundenplan stundenplan = null;
		for(Stundenplan sp : stundenplaene){
			if(sp.getId() == idStundenplan){
				stundenplan = sp;
			}
		}
		
		Studierender studierender = null;
		for(Studierender stud : studierende){
			if(stud.getMatrikelNr() == matrikelNr){
				studierender = stud;
			}
		}
				
		// Besitzt hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertBesitzt(idStundenplan, matrikelNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Besitzt hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				besitzen.add(new Besitzt(stundenplan, studierender));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void besitztLoeschen(int idStundenplan, int matrikelNr) {
		// Stundenplan- und Studierenderobjekt holen f�r Konstruktor
		Stundenplan stundenplan = null;
		for(Stundenplan sp : stundenplaene){
			if(sp.getId() == idStundenplan){
				stundenplan = sp;
			}
		}
		
		Studierender studierender = null;
		for(Studierender stud : studierende){
			if(stud.getMatrikelNr() == matrikelNr){
				studierender = stud;
			}
		}
				
		// Besitzt l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteBesitzt(idStundenplan, matrikelNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Besitzt l�schen (java)
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
	public void erhaeltHinzufuegen(int personalNr, int idStundenplan) {
		// Dozenten- und Stundenplanobjekt holen f�r Konstruktor
		Dozent dozent = null;
		for(Dozent d : dozenten){
			if(d.getPersonalNr() == personalNr){
				dozent = d;
			}
		}
		
		Stundenplan stundenplan = null;
		for(Stundenplan s : stundenplaene){
			if(s.getId() == idStundenplan){
				stundenplan = s;
			}
		}
		
		// Erhaelt hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertErhaelt(personalNr, idStundenplan);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Erhaelt hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				erhalten.add(new Erhaelt(dozent, stundenplan));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void erhaeltLoeschen(int personalNr, int idStundenplan) {
		// Dozenten- und Stundenplanobjekt holen f�r Konstruktor
		Dozent dozent = null;
		for(Dozent d : dozenten){
			if(d.getPersonalNr() == personalNr){
				dozent = d;
			}
		}
		
		Stundenplan stundenplan = null;
		for(Stundenplan s : stundenplaene){
			if(s.getId() == idStundenplan){
				stundenplan = s;
			}
		}
		
		// Erhaelt l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteErhaelt(personalNr, idStundenplan);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Erhaelt l�schen (java)
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
	public void hoertHinzufuegen(int idVeranstaltung, int matrikelNr) {
		// Veranstaltungs- und Studierenderobjekt holen f�r Konstruktor
		Veranstaltung veranstaltung = null;
		for(Veranstaltung v : veranstaltungen){
			if(v.getId() == idVeranstaltung){
				veranstaltung = v;
			}
		}
		
		Studierender studierender = null;
		for(Studierender s : studierende){
			if(s.getMatrikelNr() == matrikelNr){
				studierender = s;
			}
		}
		
		// Hoert hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertHoert(idVeranstaltung, matrikelNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Hoert hinzuf�gen (java)
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

	public void hoertLoeschen(int idVeranstaltung, int matrikelNr) {
		// Veranstaltungs- und Studierenderobjekt holen f�r Konstruktor
		Veranstaltung veranstaltung = null;
		for(Veranstaltung v : veranstaltungen){
			if(v.getId() == idVeranstaltung){
				veranstaltung = v;
			}
		}
		
		Studierender studierender = null;
		for(Studierender s : studierende){
			if(s.getMatrikelNr() == matrikelNr){
				studierender = s;
			}
		}
		
		// Hoert l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteHoert(idVeranstaltung, matrikelNr);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Hoert l�schen (java)
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
	public void hatHinzufuegen(String bezeichnung, int idStundenplan) {
		// Raum- und Stundenplanobjekt holen f�r Konstruktor
		Raum raum = null;
		for(Raum r : raeume){
			if(r.getBezeichnung().equals(bezeichnung)){
				raum = r;
			}
		}
		
		Stundenplan stundenplan = null;
		for(Stundenplan s : stundenplaene){
			if(s.getId() == idStundenplan){
				stundenplan = s;
			}
		}
		
		// Hat hinzuf�gen DB
		DB datenzugriff = null;
		boolean dbEinfuegen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB einf�gen erfolgreich
			dbEinfuegen = datenzugriff.insertHat(bezeichnung, idStundenplan);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Hat hinzuf�gen (java)
		if (dbEinfuegen) {
			try {
				hatten.add(new Hat(raum, stundenplan));
			} catch (Exception e) {
				// TODO Fehler Meldung schreiben
			}
		}
	}

	public void hatLoeschen(String bezeichnung, int idStundenplan) {
		// Raum- und Stundenplanobjekt holen f�r Konstruktor
		Raum raum = null;
		for(Raum r : raeume){
			if(r.getBezeichnung().equals(bezeichnung)){
				raum = r;
			}
		}
		
		Stundenplan stundenplan = null;
		for(Stundenplan s : stundenplaene){
			if(s.getId() == idStundenplan){
				stundenplan = s;
			}
		}
		
		// Hat l�schen DB
		DB datenzugriff = null;
		boolean dbLoeschen = false;
		try{
			datenzugriff = new DB("studierendenverwaltung", "root", "");
			// boolean um zu testen ob DB l�schen erfolgreich
			dbLoeschen = datenzugriff.deleteHat(bezeichnung, idStundenplan);
		}
		catch(Exception e){
			// TODO
		}
		finally{
			datenzugriff.datenzugriffSchliessen();
			datenzugriff = null;
		}

		// Hat l�schen (java)
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
