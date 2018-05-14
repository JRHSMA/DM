
public class Erhaelt {

	private Dozent dozent;
	private Stundenplan stundenplan;
	
	public Erhaelt(Dozent dozent, Stundenplan stundenplan){
		setDozent(dozent);
		setStundenplan(stundenplan);
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
		return "PersonalNr: "+ dozent.getPersonalNr() + " StundenplanID: "+ stundenplan.getId();
	}
}
