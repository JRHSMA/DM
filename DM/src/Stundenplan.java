
public class Stundenplan {

	private int id;
	private int semester;
	private Studiengang studiengang;
	private Tag tag;
	private Slot slot;

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

	@Override
	public String toString() {
		return "StundenplanID: " + id + ", Semester: " + semester + ", StudiengangID: " + studiengang.getId()
				+ ", TagID: " + tag.getId() + ", Slot: " + slot.getId();
	}
}
