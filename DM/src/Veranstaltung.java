
public class Veranstaltung {

	private String name;
	private int semester;
	private int dauer;
	private String kuerzel;

	public Veranstaltung(String name, int semester, int dauer, String kuerzel) {
		setName(name);
		setSemester(semester);
		setDauer(dauer);
		setKuerzel(kuerzel);
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

	public void setKuerzel(String kuerzel) {
		if (kuerzel.length() < 2 || kuerzel.length() > 4) // TODO stimmt?
			throw new RuntimeException("Kürzel ungültig.");
		this.kuerzel = kuerzel;
	}

	@Override
	public String toString() {
		return "Veranstaltungsname: " + name + ", Semester: " + semester + ", Dauer: " + dauer + ", Kürzel: " + kuerzel;
	}
}
