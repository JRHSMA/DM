import java.util.LinkedHashMap;

public class Slot {

	private int id;
	private String slot;
	
	public Slot(int id, String slot){
		setId(id);
		setSlot(slot);
	}
	
	public Slot(LinkedHashMap<String, String> datensatz){
		this(
		Integer.parseInt(datensatz.get("id")),
		datensatz.get("slot")
		);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	@Override
	public String toString() {
		return "Slot: " + slot;
	}
}
