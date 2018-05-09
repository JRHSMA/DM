import java.util.LinkedHashMap;

//public enum Studiengang {
//	UNTERNEHMENS_UND_WIRTSCHAFSTINFORMATIK, 
//	MASCHINENBAU, 
//	INFORMATIK, 
//	MEDIZINISCHE_INFORMATIK, 
//	TRANSLATION_STUDIES_FOR_IT, 
//	BIOLOGISCHE_CHEMIE, 
//	DOZENTEN_NOTFALL_LÖSUNG;
//}

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
		return "Studiengang: " + name;
	}
}
