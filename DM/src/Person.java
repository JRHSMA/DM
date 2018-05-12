import java.sql.Date; // oder java.util.Date ?
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Person {

	private int id;
	private String vorname;
	private String nachname;
	private String geburtsdatum;
	private boolean maennlich;

	public Person(int id, String vorname, String nachname, String geburtsdatum, boolean maennlich) {
		setId(id);
		setVorname(vorname);
		setNachname(nachname);
		setGeburtsdatum(geburtsdatum);
		setMaennlich(maennlich);
	}
	
	public Person(LinkedHashMap<String, String> datensatz){
		this(
		Integer.parseInt(datensatz.get("id")),
		datensatz.get("vorname"),
		datensatz.get("nachname"),
		datensatz.get("geburtsdatum"),
		Boolean.parseBoolean(datensatz.get("maennlich"))
		);
	}
	
	public boolean hinzufuegen(ArrayList<Person>person, int id, String vorname, String nachname, String geburtsdatum, boolean maennlich){
		try{
			person.add(new Person(id, vorname, nachname, geburtsdatum, maennlich));
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	public boolean aendern(ArrayList<Person>person, int id, String vorname, String nachname, String geburtsdatum, boolean maennlich){
		try{
			Person datensatz = null;
			for(Person x: person){
				if(id == x.getId()){
					datensatz = x;
					break;
				}
			}
			if(datensatz != null){
				datensatz.setVorname(vorname);
				datensatz.setNachname(nachname);
				datensatz.setGeburtsdatum(geburtsdatum);
				datensatz.setMaennlich(maennlich);
				return true;
			} else{
				System.out.println("Es wurde kein Datensatz zum Ändern gefunden");
				return false;
			}
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean loeschen(ArrayList<Person>person, int id){
		try{
			for(Person x: person){
				if(id == x.getId()){
					person.remove(x);
					return true;
				}
			}
		} catch (Exception e){
			return false;
		}
		return false;
	}	

	private void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setVorname(String vorname) {
		if (vorname.length() < 2) {
			throw new RuntimeException("Der Vorname muss mindestens drei Buchstaben besitzen");
		}
		if (vorname.length() > 50) {
			throw new RuntimeException("Der Vorname darf nicht mehr als 50 Buchstaben besitzen");
		}

		if (vorname.matches(".*[0-9].*"))
			throw new RuntimeException("Vorname darf keine Zahl enthalten");

		this.vorname = vorname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setNachname(String nachname) {
		if (nachname.length() < 2) {
			throw new RuntimeException("Der Nachname muss mindestens drei Buchstaben besitzen");
		}
		if (nachname.length() > 50) {
			throw new RuntimeException("Der Nachname darf nicht mehr als 50 Buchstaben besitzen");
		}

		if (nachname.matches(".*[0-9].*"))
			throw new RuntimeException("Nachname darf keine Zahl enthalten");

		this.nachname = nachname;
	}

	public String getNachname() {
		return nachname;
	}

	private void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	private void setMaennlich(boolean maennlich) {
		this.maennlich = maennlich;
	}

	public boolean isMaennlich() {
		return maennlich;
	}

	@Override
	public String toString() {
		return "idPerson: " + id + ", Vorname: " + vorname + ", Nachname: " + nachname + ", Geburtsdatum: "
				+ geburtsdatum + ", Männlich: " + maennlich;
	}
}
