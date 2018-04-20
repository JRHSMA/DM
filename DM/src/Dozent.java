import java.sql.Date;

public class Dozent extends Person{

	private int personalNr; // mit inc-methode wie person_ID?
	private String fakultaet; // ID?
	private String kuerzel;
	
	public Dozent(String vorname, String nachname, Date geburtsdatum, boolean maennlich){
		super(vorname, nachname, geburtsdatum, maennlich);
		setPersonalNr(personalNr);
		setFakultaet(fakultaet);
		setKuerzel(kuerzel);
	}

	private void setPersonalNr(int personalNr) {
		this.personalNr = personalNr;
	}
	public int getPersonalNr(){
		return personalNr;
	}

	public void setFakultaet(String fakultaet) {
		this.fakultaet = fakultaet;
	}
	public String getFakultaet(){
		return fakultaet;
	}

	private void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}
	public String getKuerzel(){
		return kuerzel;
	}
	
	@Override
	public String toString(){
		return super.toString() + ", Status: Dozent, PersonalNr: " + personalNr + ", Fakultät: " + fakultaet + ", Kürzel: " + kuerzel;
	}
}
