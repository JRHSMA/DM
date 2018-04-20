import java.sql.Date; // oder java.util.Date ?

public abstract class Person {

	// TODO
	// Setter z.T. private?
	// G�ltigkeitspr�fungen in Settern
	// Wie auto-increment l�sen? bzw. �berhaupt notwendig hier?

	private static int anzPersonen;
	private int person_ID;
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

	private void setPerson_ID(int person_ID) {
		this.person_ID = person_ID;
	}

	public int getPerson_ID() {
		return person_ID;
	}

	public void setVorname(String vorname) {
		if (vorname.length() < 2)
			throw new RuntimeException("Vorname ung�ltig.");
		this.vorname = vorname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setNachname(String nachname) {
		if (nachname.length() < 2)
			throw new RuntimeException("Nachname ung�ltig.");
		this.nachname = nachname;
	}

	public String getNachname() {
		return nachname;
	}

	private void setGeburtsdatum(Date geburtsdatum) {
		// G�ltigkeitspr�fungen?
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
		return "Person_ID: " + person_ID + ", Vorname: " + vorname + ", Nachname: " + nachname + ", Geburtsdatum: "
				+ geburtsdatum + ", M�nnlich: " + maennlich;
	}
}
