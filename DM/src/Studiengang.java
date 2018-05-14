import java.util.LinkedHashMap;

public class Studiengang {
	private int id;
	private String name;

	public Studiengang(int id, String name) {
		setId(id);
		setName(name);
	}

	public Studiengang(LinkedHashMap<String, String> datensatz) {
		this(Integer.parseInt(datensatz.get("id")), datensatz.get("name"));
	}

	public boolean aendern(String name) {
		try {
			setName(name);
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

	@Override
	public String toString() {
		return "StudiengangID: " + id + " Name: " + name;
	}
}
