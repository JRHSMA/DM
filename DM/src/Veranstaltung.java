
public class Veranstaltung {

	private int id;
	private int semester;
	private int dauer;
	private Veranstaltungsname veranstaltungsname;
	private Dozent dozent;
	private Stundenplan stundenplan;

	public Veranstaltung(int id, int semester, int dauer, Dozent dozent, Stundenplan stundenplan, Veranstaltungsname veranstaltungsname) {
		setId(id);
		setSemester(semester);
		setDauer(dauer);
		setDozent(dozent);
		setStundenplan(stundenplan);
		setVeranstaltungsname(veranstaltungsname);
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setSemester(int semester) {
		if (semester < 1 || semester > 7)
			throw new RuntimeException("Semester ungültig.");
		this.semester = semester;
	}

	public int getSemester() {
		return semester;
	}

	public void setDauer(int dauer) {
		if (dauer < 1)
			throw new RuntimeException("Dauer ungültig");
		this.dauer = dauer;
	}

	public int getDauer() {
		return dauer;
	}	

	public Veranstaltungsname getVeranstaltungsname() {
		return veranstaltungsname;
	}

	public void setVeranstaltungsname(Veranstaltungsname veranstaltungsname) {
		this.veranstaltungsname = veranstaltungsname;
	}
	
	
	public Dozent getDozent() {
		return dozent;
	}

	public void setDozent(Dozent dozent) {
		this.dozent = dozent;
	}

	public Stundenplan getStundenplan() {
		return stundenplan;
	}

	public void setStundenplan(Stundenplan stundenplan) {
		this.stundenplan = stundenplan;
	}

	@Override
	public String toString() {
		return "VeranstaltungsID: " + id + ", Semester: " + semester + ", Dauer: " + dauer;
	}
}
