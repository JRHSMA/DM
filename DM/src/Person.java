import java.sql.Date; // oder java.util.Date ?

public abstract class Person {

	// TODO
	// Setter z.T. private?
	// Gültigkeitsprüfungen in Settern
	// Wie auto-increment lösen? bzw. überhaupt notwendig hier?

	private static int anzPersonen;
	private int id;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private boolean maennlich;

	public Person(String vorname, String nachname, Date geburtsdatum, boolean maennlich) {
		incAnzPersonen();
		setPerson_ID(getAnzPersonen());
		setVorname(vorname);
		setNachname(nachname);
		setGeburtsdatum(geburtsdatum);
		setMaennlich(maennlich);
	}

	private static void incAnzPersonen() {
		anzPersonen++;
	}

	public static int getAnzPersonen() {
		return anzPersonen;
	}

	private void setPerson_ID(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setVorname(String vorname) {
		if (vorname.length() < 2) {
			throw new RuntimeException("Name muss mindestens drei Buchstaben besitzen");
		}
		
		if (vorname.matches(".*[0-9].*"))
			throw new RuntimeException("Name darf keine Zahl enthalten");
		else
			this.vorname = vorname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setNachname(String nachname) {
		if (nachname.length() < 2) {
			throw new RuntimeException("Name muss mindestens drei Buchstaben besitzen");
		}
		
		if (nachname.matches(".*[0-9].*"))
			throw new RuntimeException("Name darf keine Zahl enthalten");
		else
			this.nachname = nachname;
	}

	public String getNachname() {
		return nachname;
	}

	private void setGeburtsdatum(Date geburtsdatum) {
		//TODO
		// Gültigkeitsprüfungen?
		this.geburtsdatum = geburtsdatum;
	}

	public Date getGeburtsdatum() {
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
		return "Person_ID: " + id + ", Vorname: " + vorname + ", Nachname: " + nachname + ", Geburtsdatum: "
				+ geburtsdatum + ", Männlich: " + maennlich;
	}
}
