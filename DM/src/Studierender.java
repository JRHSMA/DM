import java.sql.Date; // oder java.util.Date ?
import java.util.LinkedHashMap;

public class Studierender {

	private int matrikelNr;
	private int semester;
	private Studiengang studiengang;
	private Person person;

	public Studierender(int matrikelNr, int semester, Studiengang studiengang, Person person) {
		setMatrikelNr(matrikelNr);
		setSemester(semester);
		setStudiengang(studiengang);
		setPerson(person);
	}
	
//	public Studierender(LinkedHashMap<String, String> datensatz){
//		this(
//		Integer.parseInt(datensatz.get("matrikelNr")),
//		Integer.parseInt(datensatz.get("semester")),
//		Integer.parseInt(datensatz.get("idStudiengang"))
//		//Integer.parseInt(datensatz.get("idPerson"))
//		);
//	}

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

	public Studiengang getStudiengang() {
		return studiengang;
	}

	public void setStudiengang(Studiengang studiengang) {
		this.studiengang = studiengang;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return ", Status: Student, MatrikelNr: " + matrikelNr + ", Semester: " + semester;
	}
}