
public class Hoert {

	private Studierender studierender;
	private Veranstaltung veranstaltung;
	
	public Hoert(Veranstaltung veranstaltung, Studierender studierender){
		setVeranstaltung(veranstaltung);
		setStudierender(studierender);
	}

	public Studierender getStudierender() {
		return studierender;
	}

	public void setStudierender(Studierender studierender) {
		this.studierender = studierender;
	}

	public Veranstaltung getVeranstaltung() {
		return veranstaltung;
	}

	public void setVeranstaltung(Veranstaltung veranstaltung) {
		this.veranstaltung = veranstaltung;
	}
	
	@Override
	public String toString() {
		return "MatrikelNr: " + studierender.getMatrikelNr() +" VeranstaltungID: "+ veranstaltung.getId();
	}
	
}
