
public class Hat {

	private Raum raum;
	private Stundenplan stundenplan;
	
	public Hat(Raum raum, Stundenplan stundenplan){
		setRaum(raum);
		setStundenplan(stundenplan);
	}

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}

	public Stundenplan getStundenplan() {
		return stundenplan;
	}

	public void setStundenplan(Stundenplan stundenplan) {
		this.stundenplan = stundenplan;
	}
	
	@Override
	public String toString() {
		return "Raum: " + raum.getBezeichnung() +", StundenplanID: "+ stundenplan.getId();
	}
}
