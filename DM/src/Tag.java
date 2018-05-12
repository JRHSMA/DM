import java.util.LinkedHashMap;

//public enum Tag {
//	MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG;
//}

public class Tag{
	private int id;
	private String tag;
	
	public Tag(int id, String tag){
		setId(id);
		setTag(tag);
	}
	
	public Tag(LinkedHashMap<String, String> datensatz){
		this(
		Integer.parseInt(datensatz.get("id")),
		datensatz.get("tag")
		);
	}
	
	public boolean aendern(String tag) {
		try {
			setTag(tag);
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return "Tag: " + tag;
	}
}
