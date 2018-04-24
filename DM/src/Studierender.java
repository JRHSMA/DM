import java.sql.Date; // oder java.util.Date ?

public class Studierender extends Person {

	private int matrikelNr; // mit inc-methode wie person_ID?
	private int semester;
	private Studiengang studiengang; // wie ist das mit der ID, statt studiengang,
								// hier Studiengang_ID?

	public Studierender(String vorname, String nachname, Date geburtsdatum, boolean maennlich, int matrikelNr, int semester,
			Studiengang studiengang) {
		super(vorname, nachname, geburtsdatum, maennlich);
		setMatrikelNr(matrikelNr);
		setSemester(semester);
		setStudiengang(studiengang);
	}

	private void setMatrikelNr(int matrikelNr) {
		this.matrikelNr = matrikelNr;
	}

	public int getMatrikelNr() {
		return matrikelNr;
	}

	public void setSemester(int semester) {
		if (semester < 1 || semester > 10)
			throw new RuntimeException("Semester ungültig.");
		this.semester = semester;
	}

	public int getSemester() {
		return semester;
	}

	public void setStudiengang(Studiengang studiengang) {
		this.studiengang = studiengang;
	}

	public Studiengang getStudiengang() {
		return studiengang;
	}

	@Override
	public String toString() {
		return super.toString() + ", Status: Student, MatrikelNr: " + matrikelNr + ", Semester: " + semester
				+ ", Studiengang: " + studiengang;
	}
}