
public class Raum {

	private String bezeichnung;
	private boolean computerraum;

	public Raum(String bezeichnung, boolean computerraum) {
		setBezeichnung(bezeichnung);
		setComputerraum(computerraum);
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setComputerraum(boolean computerraum) {
		this.computerraum = computerraum;
	}
	public boolean isComputerraum() {
		return computerraum;
	}
	
	@Override
	public String toString(){
		return "Raumbezeichnung: " + bezeichnung + ", Computerraum: " + computerraum;
	}
}