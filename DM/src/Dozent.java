import java.sql.Date;
import java.util.LinkedHashMap;

public class Dozent {

	private int personalNr;
	private String kuerzel;
	private Person person;
	private Fakultaet fakultaet;

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

	public void setPerson(Person person) {
		this.person = person;
	}

	public Fakultaet getFakultaet() {
		return fakultaet;
	}
	public void setFakultaet(Fakultaet fakultaet) {
		this.fakultaet = fakultaet;
	}
	@Override
	public String toString() {
		return "Status: Dozent, PersonalNr: " + personalNr + " Kürzel: " + kuerzel +" Fakultät: "+ fakultaet +" Person: "+person;
	}
}
