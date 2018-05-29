import java.util.ArrayList;

public class Dozent {

	private int personalNr;
	private String kuerzel;
	private Person person;
	private Fakultaet fakultaet;
	private ArrayList<Veranstaltung> veranstaltung = new ArrayList<>();
	private ArrayList<Stundenplan> stundenplan = new ArrayList<>();

	public Dozent(int personalNr, String kuerzel, Fakultaet fakultaet, Person person) {
		setPersonalNr(personalNr);
		setKuerzel(kuerzel);
		setFakultaet(fakultaet);
		setPerson(person);
	}

	public boolean aendern(String kuerzel, Fakultaet fakultaet, Person person) {
		try {
			setKuerzel(kuerzel);
			setFakultaet(fakultaet);
			setPerson(person);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void setPersonalNr(int personalNr) {
		this.personalNr = personalNr;
	}

	public int getPersonalNr() {
		return personalNr;
	}

	private void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}

	public String getKuerzel() {
		return kuerzel;
	}

	public Person getPerson() {
		return person;
	}

	public int getPersonID() {
		return person.getId();
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Fakultaet getFakultaet() {
		return fakultaet;
	}

	public String getFakultaetName() {
		return fakultaet.getName();
	}

	public void setFakultaet(Fakultaet fakultaet) {
		this.fakultaet = fakultaet;
	}
	
	public ArrayList<Veranstaltung> getVeranstaltung() {
		return veranstaltung;
	}

	public void setVeranstaltung(Veranstaltung veranstaltung) {
		this.veranstaltung.add(veranstaltung);
	}
	
	public ArrayList<Stundenplan> getStundenplan() {
		return stundenplan;
	}

	public void setStundenplan(Stundenplan stundenplan) {
		this.stundenplan.add(stundenplan);
	}

	@Override
	public String toString() {
		return "PersonalNr: " + personalNr + ", Kürzel: " + kuerzel + ", FakultätID: " + fakultaet.getId() + ", PersonID: "
				+ person.getId();
	}
}
