
public class Main {

	public static void main(String[]args){		
		Studierendenverwaltung sv = new Studierendenverwaltung();
//		System.out.println(sv.getStudierende().get(0).getMatrikelNr());
//		System.out.println(sv.getStudierende().get(0).getVeranstaltung());
		
		System.out.println(sv.getDozenten().get(0).getPersonalNr());
		System.out.println(sv.getDozenten().get(0).getVeranstaltung());
		
//		System.out.println(sv.getStundenplaene().get(0).getId());
//		System.out.println(sv.getStundenplaene().get(0).getVeranstaltung());
		
//		System.out.println(sv.getDozenten().get(0).getPersonalNr());
//		System.out.println(sv.getDozenten().get(0).getStundenplan());
		
//		System.out.println(sv.getStudierende().get(2).getMatrikelNr());
//		System.out.println(sv.getStudierende().get(2).getStundenplan());
	}
}
