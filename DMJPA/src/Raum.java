import java.util.LinkedHashMap;

public class Raum {

	private String bezeichnung;
	private boolean computerraum;

	public Raum(String bezeichnung, boolean computerraum) {
		setBezeichnung(bezeichnung);
		setComputerraum(computerraum);
	}

	public Raum(LinkedHashMap<String, String> datensatz) {
		this(datensatz.get("bezeichnung"), booleanFuerJavaDatenstrucktur(datensatz.get("computerraum")));
	}

	public static boolean booleanFuerJavaDatenstrucktur(String computerraum){
		int i = Integer.parseInt((computerraum));
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean aendern(boolean computerraum) {
		try {
			setComputerraum(computerraum);
			return true;
		} catch (Exception e) {
			return false;
		}
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
	public String toString() {
		return "Raumbezeichnung: " + bezeichnung + ", Computerraum: " + computerraum;
	}
}
