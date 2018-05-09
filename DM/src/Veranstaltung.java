
public class Veranstaltung {

	private String name;
	private int semester;
	private int dauer;
	private Veranstaltungsname veranstaltungsname;

	public Veranstaltung(String name, int semester, int dauer) {
		setName(name);
		setSemester(semester);
		setDauer(dauer);
	}

	public void setName(String name) {
		if (name.length() < 3)
			throw new RuntimeException("Name ungültig.");
		this.name = name;
	}

	public String getName() {
		return name;
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


	@Override
	public String toString() {
		return "Veranstaltungsname: " + name + ", Semester: " + semester + ", Dauer: " + dauer;
	}
}
