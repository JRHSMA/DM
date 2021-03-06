import java.util.ArrayList;

public class Studierender {

	private int matrikelNr;
	private int semester;
	private Studiengang studiengang;
	private Person person;
	private ArrayList<Veranstaltung> veranstaltung = new ArrayList<>();
	private ArrayList<Stundenplan> stundenplan = new ArrayList<>();

	public Studierender(int matrikelNr, int semester, Studiengang studiengang, Person person) {
		setMatrikelNr(matrikelNr);
		setSemester(semester);
		setStudiengang(studiengang);
		setPerson(person);
	}

	public boolean aendern(int semester, Studiengang studiengang, Person person) {
		try {
			setSemester(semester);
			setStudiengang(studiengang);
			setPerson(person);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void setMatrikelNr(int matrikelNr) {
		this.matrikelNr = matrikelNr;
	}

	public int getMatrikelNr() {
		return matrikelNr;
	}

	public void setSemester(int semester) {
		if (semester < 1 || semester > 10)
			throw new RuntimeException("Semester ung�ltig.");
		this.semester = semester;
	}

	public int getSemester() {
		return semester;
	}

	public Studiengang getStudiengang() {
		return studiengang;
	}

	public String getStudiengangName() {
		return studiengang.getName();
	}

	public void setStudiengang(Studiengang studiengang) {
		this.studiengang = studiengang;
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
		return "MatrikelNr: " + matrikelNr + ", Semester: " + semester + ", StudiengangID: " + studiengang.getId()
				+ ", PersonID: " + person.getId();
	}
}