import java.util.LinkedHashMap;

public class Veranstaltungsname {

	private int id;
	private String name;
	private String kuerzel;

	public Veranstaltungsname(int id, String name, String kuerzel) {
		setId(id);
		setName(name);
		setKuerzel(kuerzel);
	}

	public Veranstaltungsname(LinkedHashMap<String, String> datensatz) {
		this(Integer.parseInt(datensatz.get("id")), datensatz.get("name"), datensatz.get("kuerzel"));
	}

	public boolean aendern(String name, String kuerzel) {
		try {
			setName(name);
			setKuerzel(kuerzel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKuerzel() {
		return kuerzel;
	}

	public void setKuerzel(String kuerzel) {
		if (kuerzel.length() < 2 || kuerzel.length() > 4) // TODO stimmt?
			throw new RuntimeException("Kürzel ungültig.");
		this.kuerzel = kuerzel;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Veranstaltungsname: " + name + ", Veranstaltungskuerzel: " + kuerzel;
	}
}
