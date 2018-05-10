
public class Besitzt {

	private Stundenplan stundenplan;
	private Studierender studierender;
	
	public Besitzt(Stundenplan stundenplan, Studierender studierender){
		setStundenplan(stundenplan);
		setStudierender(studierender);
	}

	public Stundenplan getStundenplan() {
		return stundenplan;
	}

	public void setStundenplan(Stundenplan stundenplan) {
		this.stundenplan = stundenplan;
	}

	public Studierender getStudierender() {
		return studierender;
	}

	public void setStudierender(Studierender studierender) {
		this.studierender = studierender;
	}
	
	@Override
	public String toString() {
		return "Stundenplan: "+ stundenplan + " Studierender: "+ studierender;
	}
}
