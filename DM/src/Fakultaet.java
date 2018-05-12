import java.util.ArrayList;
import java.util.LinkedHashMap;

//public enum Fakultaet {
//	BIOECHNOLOGIE, 
//	ELEKTROTECHNIK, 
//	GESTALTUNG, 
//	INFORMATIK, 
//	INFORMATIONSTECHNIK, 
//	MASCHINENBAU, 
//	SOZIALWESEN, 
//	VERFAHRENS_UND_CHEMIETECHNIK, 
//	WIRTSCHAFTSINGENIEURSWESEN;
//}

public class Fakultaet{
	private int id;
	private String name;
	
	public Fakultaet(int id, String name){
		setId(id);
		setName(name);
	}
	
	public Fakultaet(LinkedHashMap<String, String> datensatz){
		this(
		Integer.parseInt(datensatz.get("id")),
		datensatz.get("name")
		);
	}
	
//	public boolean hinzufuegen(ArrayList<Person>person, int id, String vorname, String nachname, String geburtsdatum, boolean maennlich){
//		try{
//			person.add(new Person(id, vorname, nachname, geburtsdatum, maennlich));
//			return true;
//		} catch (Exception e){
//			return false;
//		}
//	}
//	
//	public boolean aendern(ArrayList<Fakultaet>fakultaet, int id, String name){
//		try{
//			Fakultaet datensatz = null;
//			for(Fakultaet x: fakultaet){
//				if(id == x.getId()){
//					datensatz = x;
//					break;
//				}
//			}
//			if(datensatz != null){
//				datensatz.setVorname(vorname);
//				datensatz.setNachname(nachname);
//				datensatz.setGeburtsdatum(geburtsdatum);
//				datensatz.setMaennlich(maennlich);
//				return true;
//			} else{
//				System.out.println("Es wurde kein Datensatz zum Ändern gefunden");
//				return false;
//			}
//		}
//		catch(Exception e){
//			return false;
//		}
//	}
//	
//	public boolean loeschen(ArrayList<Person>person, int id){
//		try{
//			for(Person x: person){
//				if(id == x.getId()){
//					person.remove(x);
//					return true;
//				}
//			}
//		} catch (Exception e){
//			return false;
//		}
//		return false;
//	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return "Fakultät: "+ name;
	}
	
}
