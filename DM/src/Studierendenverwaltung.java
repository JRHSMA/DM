import java.util.ArrayList;
import java.util.LinkedHashMap;

import db.DB;

public class Studierendenverwaltung {

//	public static void main(String[] args) {
//		GUI stud = new GUI();
//		stud.LayoutGUI("Studierendenverwaltung");
//	}
	
	public static void main(String[] args) {
		DB datenzugriff=null;
		ArrayList<Fakultaet> fakultaeten=new ArrayList<>();
		ArrayList<Dozent> dozenten=new ArrayList<>();
		try{
			//TODO beziehung zu Person
			//referenzielle Integrität Fakultät und Dozent
			ArrayList<LinkedHashMap<String, String>> daten=null;
			datenzugriff=new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM fakultaet;");
			daten = datenzugriff.lesenjava();
			for(LinkedHashMap<String, String> datensatz:daten){
				fakultaeten.add(new Fakultaet(datensatz));
			}
			System.out.println(fakultaeten);
			System.out.println("-----------------");
			
			datenzugriff=new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM dozent;");
			daten = datenzugriff.lesenjava();
			for(LinkedHashMap<String, String> datensatz:daten){
				int id=Integer.parseInt(datensatz.get("personalNr"));
				String name=datensatz.get("kuerzel");
				int idFakultaet=Integer.parseInt(datensatz.get("idFakultaet"));
				Fakultaet fakultaet=null;
				for(Fakultaet f:fakultaeten){
					if (f.getId()==idFakultaet){
						fakultaet=f;
						break;
					}
				}
				dozenten.add(new Dozent(id, name, fakultaet));
			}
			System.out.println(dozenten);
			System.out.println("-----------------");
			
			datenzugriff.setSQL("SELECT * FROM dozent;");
			daten = datenzugriff.lesenjava();
			for(LinkedHashMap<String, String> datensatz:daten){
				System.out.println(datensatz);
			}
		}
		catch(Exception e){
			System.err.println(e.getClass()+":"+e.getMessage());
		}
		finally{
			datenzugriff.close();
		}
		System.out.println("Testausgabe: "+dozenten.get(1).getFakultaet());
		
		
		ArrayList<Studiengang> studiengaenge=new ArrayList<>();
		ArrayList<Studierender> studierende=new ArrayList<>();
		try{
			//TODO beziehung zu Person
			//referenzielle Integrität Studierender und Studiengang
			ArrayList<LinkedHashMap<String, String>> daten=null;
			datenzugriff=new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM studiengang;");
			daten = datenzugriff.lesenjava();
			for(LinkedHashMap<String, String> datensatz:daten){
				studiengaenge.add(new Studiengang(datensatz));
			}
			System.out.println(studiengaenge);
			System.out.println("-----------------");
			
			datenzugriff=new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM studierender;");
			daten = datenzugriff.lesenjava();
			for(LinkedHashMap<String, String> datensatz:daten){
				int id=Integer.parseInt(datensatz.get("matrikelNr"));
				int semester=Integer.parseInt(datensatz.get("semester"));
				int idStudiengang=Integer.parseInt(datensatz.get("idStudiengang"));
				Studiengang studiengang=null;
				for(Studiengang sg:studiengaenge){
					if (sg.getId()==idStudiengang){
						studiengang=sg;
						break;
					}
				}
				studierende.add(new Studierender(id, semester, studiengang));
			}
			System.out.println(studierende);
			System.out.println("-----------------");
			
			datenzugriff.setSQL("SELECT * FROM studierender;");
			daten = datenzugriff.lesenjava();
			for(LinkedHashMap<String, String> datensatz:daten){
				System.out.println(datensatz);
			}
		}
		catch(Exception e){
			System.err.println(e.getClass()+":"+e.getMessage());
		}
		finally{
			datenzugriff.close();
		}
		
		
		ArrayList<Veranstaltungsname> veranstaltungsnamen=new ArrayList<>();
		ArrayList<Veranstaltung> veranstaltungen=new ArrayList<>();
		try{
			//referenzielle Integrität Veranstaltung und Veranstaltungsname
			ArrayList<LinkedHashMap<String, String>> daten=null;
			datenzugriff=new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM veranstaltungsname;");
			daten = datenzugriff.lesenjava();
			for(LinkedHashMap<String, String> datensatz:daten){
				veranstaltungsnamen.add(new Veranstaltungsname(datensatz));
			}
			System.out.println(veranstaltungsnamen);
			System.out.println("-----------------");
			
			datenzugriff=new DB("studierendenverwaltung", "root", "");
			datenzugriff.setSQL("SELECT * FROM veranstaltung;");
			daten = datenzugriff.lesenjava();
			for(LinkedHashMap<String, String> datensatz:daten){
				int id=Integer.parseInt(datensatz.get("id"));
				int semester=Integer.parseInt(datensatz.get("semester"));
				int dauer=Integer.parseInt(datensatz.get("dauer"));
				int idvName=Integer.parseInt(datensatz.get("idvName"));
				Veranstaltungsname veranstaltungsname=null;
				for(Veranstaltungsname vn:veranstaltungsnamen){
					if (vn.getId()==idvName){
						veranstaltungsname=vn;
						break;
					}
				}
				
				//TODO mehrere schleifen für konstruktor, da mehrere referenzielle integritäten
				studierende.add(new Studierender(id, semester, studiengang));
			}
			System.out.println(studierende);
			System.out.println("-----------------");
			
			datenzugriff.setSQL("SELECT * FROM veranstaltung;");
			daten = datenzugriff.lesenjava();
			for(LinkedHashMap<String, String> datensatz:daten){
				System.out.println(datensatz);
			}
		}
		catch(Exception e){
			System.err.println(e.getClass()+":"+e.getMessage());
		}
		finally{
			datenzugriff.close();
		}
	}
}
