import java.util.ArrayList;

public class Stundenplan {

	private int id;
	private int semester;
	private Studiengang studiengang;
	private Tag tag;
	private Slot slot;
	private ArrayList<Veranstaltung> veranstaltung = new ArrayList<>();
	private ArrayList<Raum> raum = new ArrayList<>();

	public Stundenplan(int id, int semester, Studiengang studiengang, Tag tag, Slot slot) {
		setId(id);
		setSemester(semester);
		setStudiengang(studiengang);
		setTag(tag);
		setSlot(slot);
	}

	public boolean aendern(int semester, Studiengang studiengang, Tag tag, Slot slot) {
		try {
			setSemester(semester);
			setStudiengang(studiengang);
			setTag(tag);
			setSlot(slot);
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

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Studiengang getStudiengang() {
		return studiengang;
	}

	public void setStudiengang(Studiengang studiengang) {
		this.studiengang = studiengang;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	
	public ArrayList<Veranstaltung> getVeranstaltung() {
		return veranstaltung;
	}

	public void setVeranstaltung(Veranstaltung veranstaltung) {
		this.veranstaltung.add(veranstaltung);
	}
	
	public ArrayList<Raum> getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum.add(raum);
	}

	@Override
	public String toString() {
		return "StundenplanID: " + id + ", Semester: " + semester + ", StudiengangID: " + studiengang.getId()
				+ ", TagID: " + tag.getId() + ", Slot: " + slot.getId();
	}
}
