import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.DB;

public class GUI implements ActionListener {

	private JFrame jf;
	private JButton[] fusszeile = new JButton[5];
	private JButton abfrageSchicken;
	private JMenuBar menuBar;
	private JMenu menuT1;
	private JMenu menuT2;
	private JMenuItem m2Item1;
	private JMenuItem m2Item2;
	private JMenuItem mItem0;
	private JMenuItem mItem1;
	private JMenuItem mItem2;
	private JMenuItem mItem3;
	private JButton m4Buttons[];
	private JTextArea m4Text[];
	private JLabel m4Labels[][];
	private JLabel m5Labels[][];
	private JTextField m4Parameter[][];
	private JTextField m5Parameter[][];
	private JTextArea m5Text[];
	private JButton m5Buttons[];
	private JPanel jpCenter;
	private JPanel eA;
	private JPanel kA;
	private int einAnfparameterAnzahl[] = new int[10];
	private int kompAnfparameterAnzahl[] = new int[15];
	private JLabel allLabels[];
	private JCheckBox computerRaum;
	private JPanel innerCenter;
	private int kompAbNr;
	private int einAbNr;
	private boolean istEinfach = false;

	@SuppressWarnings("rawtypes")
	private JComboBox geschAuswahl;
	@SuppressWarnings("rawtypes")
	private JComboBox slotAuswahl;
	@SuppressWarnings("rawtypes")
	private JComboBox tagAuswahl;
	@SuppressWarnings("rawtypes")
	private JComboBox semesterAuswahl;
	@SuppressWarnings("rawtypes")
	private JComboBox semesterAuswahl2;
	@SuppressWarnings("rawtypes")
	private JComboBox fakultätAuswahl;
	@SuppressWarnings("rawtypes")
	private JComboBox fakultätAuswahl2;
	@SuppressWarnings("rawtypes")
	private JComboBox studiengangAuswahl;
	@SuppressWarnings("rawtypes")
	private JComboBox studiengangAuswahl2;
	// Parameters-------------------
	private int matrikelNr;
	private int semester;
	private String studiengang;
	private String vorlesungsKrzl;
	private String fakultät;
	private String profName;
	private String profKrzl;
	private String raumName;
	private String tag;

	private int slot;
	private int persoNr;
	private boolean istMännlich = true;
	private String veranstaltungsname;
	private boolean istCompRaum = false;

	private int tabellenNummer = -1;
	// -----------------------------
	// TODO DB bearbeiten variablen -----
	private Studierendenverwaltung sv;
	// variablen db
	private int allgDB;
	JLabel inDieserTabelle = new JLabel();
	private String pKListe[] = new String[8];
	private JLabel überschrift;
	private JButton tabellen[] = new JButton[8];
	JPanel innerCenter2;
	private String semesterListe[] = { "1. Semester", "2. Semester",
			"3. Semester", "4. Semester", "5. Semester", "6. Semester",
			"7. Semester" };
	private String tageListe[] = { "Montag", "Dienstag", "Mittwoch",
			"Donnerstag", "Freitag" };
	private String fakultätenListe[] = { "Biotechnologie", "Elektrotechnik",
			"Gestaltung", "Informatik", "Informationstechnik", "Maschinenbau",
			"Sozialwesen", "Verfahrens- und Chemietechnik",
			"Wirtschaftsingenieurwesen" };;
	private String studiengängeListe[] = {
			"Biologische Chemie (Bachelor)",
			"Biotechnology - Biomedical Science and Technology (Master)",
			"Biotechnology - Bioprocess Development (Master)",
			"Automation Technology (Bachelor)",
			"Automatisierungs und Energiesysteme (Master)",
			"Automatisierungstechnik (Bachelor)",
			"Elektro und Informationstechnik für das höhere Lehramt (Bachelor)",
			"Elektro und Informationstechnik für das höhere Lehramt (Master)",
			"Elektro und Informationstechnik für das höhere Lehramt - Zweitfach Mathematik (Master)",
			"Energietechnik und erneuerbare Energien (Bachelor)",
			"Power Engineering and Renewable Energies (Bachelor)",
			"Translation Studies for IT (Bachelor)", "Informatik (Bachelor)",
			"Informatik (Master)",
			"Informatik - Vertiefungsrichtung Medizin (Master)",
			"Medizinische Informatik (Bachelor)",
			"Unternehmens- und Wirtschaftsinformatik (Bachelor)",
			"Maschinenbau (Bacheor)", "Maschinenbau (Master)",
			"Maschinenbau / Konstruktion (Bachelor)",
			"Maschinenbau / Produktion (Bachelor)",
			"Informationstechnik (Master)",
			"Informationstechnik / Elektronik (Bachelor)",
			"Medizintechnik (Bachelor)", "Medizintechnik (Master)",
			"Nachrichtentechnik / Elektronik (Bachelor)",
			"Technische Informatik (Bachelor)", "Soziale Arbeit (Bachelor)",
			"Soziale Arbeit (Master)",
			"Mechatronik - fakultätsübergreifend E,I,M,N (Bachelor)",
			"Chemical Engineering French (Master)",
			"Chemieingenieurwesen (Master)", "Chemische Technik (Bachelor)",
			"Verfahrenstechnik (Bachelor)",
			"Wirtschaftsingenieurwesen (Bachelor)",
			"Wirtschaftsingenieurwesen - Vorqualifikation BWL (Master)",
			"Wirtschaftsingenieurwesen - Vorqualifikation ING (Master)",
			"Wirtschaftsingenieurwesen - Vorqualifikation WI (Master)",
			"Wirtschaftsingenieurwesen International (Bachelor)" };

	// ----------------------------
	// neue Daten hinzufügen
	private JTextField personEingabe[] = new JTextField[4];
	private JTextField profEingabe[] = new JTextField[3];
	private JTextField studEingabe[] = new JTextField[4];
	private JTextField veranstaltungEingabe[] = new JTextField[5];
	private JTextField vNameEingabe[] = new JTextField[2];
	private JTextField fakEingabe = new JTextField();
	private JTextField studiengangEingabe = new JTextField();
	private boolean rIstPcRaum = false;
	private JCheckBox istPcRaum = new JCheckBox();
	private JTextField raumEingabe = new JTextField();
	// ---------------
	// ändern/löschen attributlabels
	private JLabel personenLabels[];
	private JLabel profLabels[];
	private JLabel studentLabels[];
	private JLabel fakultätLabel;
	private JLabel studiengangLabel;
	private JLabel veranstaltungLabels[];
	private JLabel vorlesungsnameLabels[];
	private JLabel raumLabels;
	private JTextField userInputID;
	private JLabel pK;

	// ------------------------
	public GUI() {
		sv = new Studierendenverwaltung();
		LayoutGUI("test");
	}

	public static void main(String args[]) {
		new GUI();
	}

	public void LayoutGUI(String titel) {
		jf = new JFrame(titel);
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		JPanel jp1 = new JPanel();
		jp1.setLayout(new FlowLayout());
		jp.add(jp1, BorderLayout.NORTH);
		jpCenter = new JPanel();
		jpCenter.setLayout(new BorderLayout());
		jp.add(jpCenter, BorderLayout.CENTER);

		fusszeile[0] = new JButton("Abbrechen");
		fusszeile[0].addActionListener(this);
		fusszeile[1] = new JButton("Senden");
		fusszeile[1].setVisible(false);
		fusszeile[3] = new JButton("Senden");
		fusszeile[3].setVisible(false);
		fusszeile[3].addActionListener(this);
		fusszeile[4] = new JButton("Löschem");
		fusszeile[4].setVisible(false);
		fusszeile[4].addActionListener(this);
		fusszeile[2] = new JButton("Zurück");
		fusszeile[2].setVisible(false);
		fusszeile[2].addActionListener(this);
		menuBar = new JMenuBar();

		menuT1 = new JMenu("DatenBank bearbeiten");
		menuT1.setFont(new Font("Serif", Font.PLAIN, 25));
		menuT2 = new JMenu("Abfragen");
		menuT2.setFont(new Font("Serif", Font.PLAIN, 25));
		menuT1.addActionListener(this);
		menuT2.addActionListener(this);

		menuBar.add(menuT1);
		menuBar.add(menuT2);

		JPanel jp2 = new JPanel();
		jp.add(jp2, BorderLayout.SOUTH);
		jp.add(menuBar, BorderLayout.NORTH);
		for (int i = 0; i < fusszeile.length; i++) {
			jp2.add(fusszeile[i]);
		}
		alleAttribute();
		allLabels();
		primeKeyTab();
		subMenuDB();
		subMenuAbfragen();

		jf.setContentPane(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		jf.setSize(960, 720);
	}

	private void subMenuAbfragen() {
		m2Item1 = new JMenuItem("Einfache Abfragen");
		m2Item1.setFont(new Font("Serif", Font.PLAIN, 18));
		m2Item1.addActionListener(this);
		m2Item2 = new JMenuItem("Komplexe Abfragen");
		m2Item2.setFont(new Font("Serif", Font.PLAIN, 18));
		m2Item2.addActionListener(this);
		menuT2.add(m2Item1);
		menuT2.add(m2Item2);
	}

	private void subMenuDB() {
		mItem0 = new JMenuItem("Anzeigen");
		mItem0.setFont(new Font("Serif", Font.PLAIN, 18));
		mItem0.addActionListener(this);
		mItem1 = new JMenuItem("Hinzufügen");
		mItem1.setFont(new Font("Serif", Font.PLAIN, 18));
		mItem1.addActionListener(this);
		mItem2 = new JMenuItem("Ändern");
		mItem2.setFont(new Font("Serif", Font.PLAIN, 18));
		mItem2.addActionListener(this);
		mItem3 = new JMenuItem("Löschen");
		mItem3.setFont(new Font("Serif", Font.PLAIN, 18));
		mItem3.addActionListener(this);
		menuT1.add(mItem0);
		menuT1.add(mItem1);
		menuT1.add(mItem2);
		menuT1.add(mItem3);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object quelle = ev.getSource();
		if (m2Item1 == quelle) {
			fusszeile[1].setVisible(false);
			menuT4();
		}
		if (m2Item2 == quelle) {
			fusszeile[1].setVisible(false);
			menuT5();
		}
		if (mItem1 == quelle) {
			fusszeile[1].setVisible(false);
			menuT1();
		}
		if (mItem2 == quelle) {
			fusszeile[1].setVisible(false);
			menuT2();
		}
		if (mItem3 == quelle) {
			fusszeile[1].setVisible(false);
			menuT3();
		}// TODO
		if (fusszeile[3] == quelle) {
			JTextField personenParameter[];
			JTextField profParameter[];
			JTextField studentParameter[];
			JTextField fakultätParameter;
			JTextField studiengangParameter;
			JTextField veranstaltungParameter[];
			JTextField[] vorlesungsnameParameter;
			JTextField raumParameter;
			fusszeile[3].setVisible(false);
			fusszeile[4].setVisible(true);
			JPanel innerCenter3 = new JPanel();
			int counter;
			int index;
			boolean firstHit;
			innerCenter3.setLayout(new GridLayout(15, 4));
			switch (tabellenNummer) {
			case 0:
				personenParameter = new JTextField[4];
				initAllTFields(personenParameter, false);
				ArrayList<Person> allepersonen = sv.getPersonen();
				String istMännlich = "nein";
				counter = -1;
				index = 0;
				firstHit = false;
				for (Person pAll : allepersonen) {
					counter++;
					if (pAll.getId() == Integer.parseInt(userInputID.getText())
							&& firstHit == false) {
						index = counter;
						firstHit = true;
					}
				}
				if (firstHit == false) {
					JOptionPane.showMessageDialog(jf,
							"Kein Treffer. Bitte andere ID eingeben",
							"Kein Treffer", JOptionPane.ERROR_MESSAGE);
				} else {
					clear();
					Person p = allepersonen.get(index);
					personenParameter[0].setText(p.getVorname());
					personenParameter[1].setText(p.getNachname());
					personenParameter[2].setText(p.getGeburtsdatum());
					if (p.isMaennlich() == true) {
						istMännlich = "ja";
					}
					personenParameter[3].setText(istMännlich);
					for (int para = 0; para < personenParameter.length; para++) {
						innerCenter3.add(personenLabels[para]);
						innerCenter3.add(personenParameter[para]);
					}
				}
				jpCenter.add(innerCenter3, BorderLayout.CENTER);

				break;
			case 1:
				profParameter = new JTextField[3];
				initAllTFields(profParameter, false);
				ArrayList<Dozent> alleprofs = sv.getDozenten();
				counter = -1;
				index = 0;
				firstHit = false;
				for (Dozent prof : alleprofs) {
					counter++;
					if (prof.getPersonalNr() == Integer.parseInt(userInputID
							.getText()) && firstHit == false) {
						index = counter;
						firstHit = true;
					}
				}
				if (firstHit == false) {
					JOptionPane.showMessageDialog(jf,
							"Kein Treffer. Bitte andere ID eingeben",
							"Kein Treffer", JOptionPane.ERROR_MESSAGE);
				} else {
					clear();
					Dozent prof = alleprofs.get(index);
					profParameter[0].setText(prof.getKuerzel());
					profParameter[1].setText(prof.getFakultaetName());
					profParameter[2].setText("" + prof.getPersonID());

					for (int para = 0; para < profParameter.length; para++) {
						innerCenter3.add(profLabels[para]);
						innerCenter3.add(profParameter[para]);
					}
				}
				jpCenter.add(innerCenter3, BorderLayout.CENTER);
				break;
			case 2:
				studentParameter = new JTextField[3];
				initAllTFields(studentParameter, false);
				ArrayList<Studierender> allestudenten = sv.getStudierende();
				Studierender student = allestudenten.get(1);
				studentParameter[0].setText("" + student.getSemester());
				studentParameter[1].setText(student.getStudiengangName());
				studentParameter[2].setText("" + student.getPersonID());

				for (int para = 0; para < studentParameter.length; para++) {
					innerCenter2.add(studentLabels[para]);
					innerCenter2.add(studentParameter[para]);
				}

				break;
			case 3:
				fakultätParameter = new JTextField();
				fakultätParameter.setEditable(false);
				ArrayList<Fakultaet> allefakultäten = sv.getFakultaeten();
				Fakultaet fak = allefakultäten.get(1);
				fakultätParameter.setText(fak.getName());

				innerCenter2.add(fakultätLabel);
				innerCenter2.add(fakultätParameter);
				break;
			case 4:
				studiengangParameter = new JTextField();
				studiengangParameter.setEditable(false);
				ArrayList<Studiengang> allestudiengänge = sv.getStudiengaenge();
				Studiengang studiengang = allestudiengänge.get(1);
				studiengangParameter.setText(studiengang.getName());

				innerCenter2.add(studiengangLabel);
				innerCenter2.add(studiengangParameter);
				break;
			case 5:
				veranstaltungParameter = new JTextField[5];
				initAllTFields(veranstaltungParameter, false);
				ArrayList<Veranstaltung> alleveranstaltungen = sv
						.getVeranstaltungen();
				Veranstaltung veranstaltung = alleveranstaltungen.get(1);
				veranstaltungParameter[0].setText(""
						+ veranstaltung.getSemester());
				veranstaltungParameter[1]
						.setText("" + veranstaltung.getDauer());
				veranstaltungParameter[2].setText(veranstaltung
						.getDozentPersoNr());
				veranstaltungParameter[3].setText(veranstaltung
						.getStundenplanID());
				veranstaltungParameter[4].setText(veranstaltung
						.getVeranstaltungsnameID());

				for (int para = 0; para < veranstaltungParameter.length; para++) {
					innerCenter2.add(veranstaltungLabels[para]);
					innerCenter2.add(veranstaltungParameter[para]);
				}

				break;
			case 6:
				vorlesungsnameParameter = new JTextField[2];
				initAllTFields(vorlesungsnameParameter, false);
				ArrayList<Veranstaltungsname> allevNamen = sv
						.getVeranstaltungsnamen();
				Veranstaltungsname vName = allevNamen.get(1);
				vorlesungsnameParameter[0].setText(vName.getName());
				vorlesungsnameParameter[1].setText(vName.getKuerzel());

				for (int para = 0; para < vorlesungsnameParameter.length; para++) {
					innerCenter2.add(vorlesungsnameLabels[para]);
					innerCenter2.add(vorlesungsnameParameter[para]);
				}
				break;
			case 7:
				String raumIsPc;
				raumParameter = new JTextField();
				raumParameter.setEditable(false);
				ArrayList<Raum> alleraume = sv.getRaeume();
				Raum raum = alleraume.get(1);
				if (raum.isComputerraum() == true) {
					raumIsPc = "ja";
				} else {
					raumIsPc = "nein";
				}
				raumParameter.setText(raumIsPc);

				innerCenter2.add(raumLabels);
				innerCenter2.add(raumParameter);
				break;
			default:

			}
		}// TODO delete
		if (fusszeile[4] == quelle) {
			Object[] options = { "Abbrechen", "Trotzdem löschen" };
			int n = JOptionPane.showOptionDialog(jf,
					"Wenn Sie dieses Datentupel löschen, können Sie dieses nicht wiederherstellen...", "A Silly Question",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, // do not use a custom Icon
					options, // the titles of buttons
					options[0]); // default button title
			if(n==1){
//				sv.personLoeschen(id);
			}
		}

		if (fusszeile[0] == quelle) {
			tabellenNummer = -1;
			fusszeile[1].setVisible(false);
			clear();
		}// TODO geburtsdatum format testen??

		if (m4Buttons != null) {
			for (int j = 0; j < m4Buttons.length; j++) {
				if (m4Buttons[j] == quelle) {
					einAbf(j);
					switch (einAnfparameterAnzahl[j]) {
					case 1:
						innerCenter.add(m4Labels[j][0]);
						innerCenter.add(m4Parameter[j][0]);
						allDropDrowns(j, 0, true);
						break;
					case 2:
						innerCenter.add(m4Labels[j][0]);
						innerCenter.add(m4Parameter[j][0]);
						allDropDrowns(j, 0, true);
						innerCenter.add(m4Labels[j][1]);
						innerCenter.add(m4Parameter[j][1]);
						allDropDrowns(j, 1, true);
						break;
					default:
					}

				}
			}

		}
		if (m5Buttons != null) {
			for (int j = 0; j < m5Buttons.length; j++) {
				if (m5Buttons[j] == quelle) {
					komAbf(j);
					switch (kompAnfparameterAnzahl[j]) {
					case 1:
						innerCenter.add(m5Labels[j][0]);
						if (m5Labels[j][0].getText() == "") {
							innerCenter.remove(m5Labels[j][0]);
							innerCenter.remove(m5Parameter[j][0]);
							computerRaum = new JCheckBox("Ist Computerraum");
							computerRaum.addActionListener(this);
							innerCenter.add(computerRaum);

						}
						innerCenter.add(m5Parameter[j][0]);
						allDropDrowns(j, 0, false);
						break;
					case 2:
						innerCenter.add(m5Labels[j][0]);
						innerCenter.add(m5Parameter[j][0]);
						allDropDrowns(j, 0, false);
						if (m5Labels[j][0].getText() == "") {
							innerCenter.remove(m5Labels[j][0]);
							innerCenter.remove(m5Parameter[j][0]);
							computerRaum = new JCheckBox("Ist Computerraum");
							computerRaum.addActionListener(this);
							innerCenter.add(computerRaum);

						}

						innerCenter.add(m5Labels[j][1]);
						innerCenter.add(m5Parameter[j][1]);
						allDropDrowns(j, 1, false);
						if (m5Labels[j][1].getText() == "") {
							innerCenter.remove(m5Labels[j][1]);
							innerCenter.remove(m5Parameter[j][1]);
							computerRaum = new JCheckBox("Ist Computerraum");
							computerRaum.addActionListener(this);
							innerCenter.add(computerRaum);

						}
						break;
					case 3:
						innerCenter.add(m5Labels[j][0]);
						innerCenter.add(m5Parameter[j][0]);
						allDropDrowns(j, 0, false);
						if (m5Labels[j][0].getText() == "") {
							innerCenter.remove(m5Labels[j][0]);
							innerCenter.remove(m5Parameter[j][0]);
							computerRaum = new JCheckBox("Ist Computerraum");
							computerRaum.addActionListener(this);
							innerCenter.add(computerRaum);

						}
						innerCenter.add(m5Labels[j][1]);
						innerCenter.add(m5Parameter[j][1]);
						allDropDrowns(j, 1, false);
						if (m5Labels[j][1].getText() == "") {
							innerCenter.remove(m5Labels[j][1]);
							innerCenter.remove(m5Parameter[j][1]);
							computerRaum = new JCheckBox("Ist Computerraum");
							computerRaum.addActionListener(this);
							innerCenter.add(computerRaum);
						}
						innerCenter.add(m5Labels[j][2]);
						innerCenter.add(m5Parameter[j][2]);
						allDropDrowns(j, 2, false);
						if (m5Labels[j][2].getText() == "") {
							innerCenter.remove(m5Labels[j][2]);
							innerCenter.remove(m5Parameter[j][2]);
							computerRaum = new JCheckBox("Ist Computerraum");
							computerRaum.addActionListener(this);
							innerCenter.add(computerRaum);
						}
						break;
					default:
					}

				}
			}
		}
		String sourceToString = ev.getSource().toString();
		// GENDERAUSWAHL
		if (sourceToString.contains("selectedItemReminder=Männlich")) {
			istMännlich = true;
		}
		if (sourceToString.contains("selectedItemReminder=Weiblich")) {
			istMännlich = false;
		}
		// SLOTAUSWAHL
		for (int i = 1; i < 7; i++) {
			String testString = "selectedItemReminder=";
			testString = testString + i + ". Slot";
			if (sourceToString.contains(testString)) {
				slot = i;
			}
		}
		// TAGAUSWAHL
		for (int i = 0; i < tageListe.length; i++) {
			String testString = "selectedItemReminder=";
			testString = testString + tageListe[i];
			if (sourceToString.contains(testString)) {
				tag = tageListe[i];
			}
		}

		// SEMESTERAUSWAHL
		for (int i = 1; i < 8; i++) {
			String testString = "selectedItemReminder=";
			testString = testString + i + ". Semester";
			if (sourceToString.contains(testString)) {
				semester = i;
			}
		}
		// STUDIENGANGAUSWAHL
		for (int i = 0; i < studiengängeListe.length; i++) {
			String testString = "selectedItemReminder=";
			testString = testString + studiengängeListe[i];
			if (sourceToString.contains(testString)) {
				studiengang = studiengängeListe[i];
			}
		}
		// FAKULTÄTAUSWAHL
		for (int i = 0; i < fakultätenListe.length; i++) {
			String testString = "selectedItemReminder=";
			testString = testString + fakultätenListe[i];
			if (sourceToString.contains(testString)) {
				fakultät = fakultätenListe[i];
			}
		}

		if (abfrageSchicken == quelle) {
			DB db = new DB("studierendenverwaltung", "root", "");
			JTextArea bspTextArea = new JTextArea();
			jpCenter.add(bspTextArea);
			ArrayList<LinkedHashMap<String, String>> daten;

			if (istEinfach) {
				switch (einAnfparameterAnzahl[einAbNr]) {
				case 1:
					if (m4Labels[einAbNr][0] == allLabels[0]
							|| m4Labels[kompAbNr][0] == allLabels[10]) {
						istPersOderMatNr(einAbNr, 0, true, true);
					} else {
						stringEingaben(einAbNr, 0, true);
					}

					// ////////////////////
					// Abfragen
					// ////////////////////
					switch (einAbNr) {
					case 0: // Abfrage 1
						// Übergabeparameter
						db.abfrageEinfach01(matrikelNr);
						break;
					case 1:
						db.abfrageEinfach02(semester);
						break;
					case 4:
						db.abfrageEinfach05(vorlesungsKrzl);
						break;
					case 7:
						db.abfrageEinfach08(fakultät);
						break;
					case 8:
						db.abfrageEinfach09(profName);
						break;
					case 9:
						db.abfrageEinfach10(semester);
						break;
					default:
					}

					break;
				case 2:
					if (m4Labels[einAbNr][0] == allLabels[0]
							|| m4Labels[kompAbNr][0] == allLabels[10]) {
						istPersOderMatNr(einAbNr, 0, true, true);
					} else {
						stringEingaben(einAbNr, 0, true);
					}

					if (m4Labels[einAbNr][1] == allLabels[0]
							|| m4Labels[kompAbNr][1] == allLabels[10]) {
						istPersOderMatNr(einAbNr, 1, true, true);
					} else {
						stringEingaben(einAbNr, 1, true);
					}

					// ////////////////////
					// Abfragen
					// ////////////////////
					switch (einAbNr) {
					case 2: // Abfrage 1
						// Übergabeparameter
						db.abfrageEinfach03(istMännlich, studiengang);
						break;
					case 3:
						db.abfrageEinfach04(istMännlich, studiengang);
						break;
					case 5:
						db.abfrageEinfach06(semester, vorlesungsKrzl);
						break;
					case 6:
						db.abfrageEinfach07(studiengang, semester);

						break;
					default:
					}

					break;
				default:
				}
			} else {
				switch (kompAnfparameterAnzahl[kompAbNr]) {
				case 1:
					if (m5Labels[kompAbNr][0] == allLabels[0]
							|| m5Labels[kompAbNr][0] == allLabels[10]) {
						istPersOderMatNr(kompAbNr, 0, false, true);
					} else {
						stringEingaben(kompAbNr, 0, false);
					}

					// ////////////////////
					// Abfragen
					// ////////////////////
					switch (kompAbNr) {
					case 3:
						db.abfrageKomplex04(persoNr);
						break;
					default:
						System.out.println(kompAbNr);
					}

					break;
				case 2:
					if (m5Labels[kompAbNr][0] == allLabels[0]
							|| m5Labels[kompAbNr][0] == allLabels[10]) {
						istPersOderMatNr(kompAbNr, 0, false, true);
					} else {
						stringEingaben(kompAbNr, 0, false);
					}
					if (m5Labels[kompAbNr][1] == allLabels[0]
							|| m5Labels[kompAbNr][1] == allLabels[10]) {
						istPersOderMatNr(kompAbNr, 1, false, true);
					} else {
						stringEingaben(kompAbNr, 1, false);
					}

					// ////////////////////
					// Abfragen /
					// ////////////////////
					switch (kompAbNr) {
					case 0:
						db.abfrageKomplex01(veranstaltungsname, raumName);
						break;
					case 1:
						db.abfrageKomplex02(matrikelNr, raumName);
						break;
					case 2:
						if (studiengang == null) {
							studiengang = "Biologische Chemie (Bachelor)";
						}
						if (tag == null) {
							tag = "Montag";
						}
						db.abfrageKomplex03(studiengang, tag);
						break;
					case 5:
						db.abfrageKomplex06(veranstaltungsname, matrikelNr);
						break;
					case 7:
						if (tag == null) {
							tag = "Montag";
						}
						db.abfrageKomplex08(profName, tag);
						break;
					case 8:
						if (tag == null) {
							tag = "Montag";
						}
						db.abfrageKomplex09(istCompRaum, tag);
						break;
					case 13:
						if (tag == null) {
							tag = "Montag";
						}
						db.abfrageKomplex14(profKrzl, tag);
						break;
					case 14:
						if (tag == null) {
							tag = "Montag";
						}
						if (slot == 0) {
							slot = 1;
						}
						db.abfrageKomplex15(tag, slot);
						break;
					default:
					}

					break;
				case 3:
					if (m5Labels[kompAbNr][0] == allLabels[0]
							|| m5Labels[kompAbNr][0] == allLabels[10]) {
						istPersOderMatNr(kompAbNr, 0, false, true);

					} else {
						stringEingaben(kompAbNr, 0, false);

					}

					if (m5Labels[kompAbNr][1] == allLabels[0]
							|| m5Labels[kompAbNr][1] == allLabels[10]) {
						istPersOderMatNr(kompAbNr, 1, false, true);

					} else {
						stringEingaben(kompAbNr, 1, false);

					}
					if (m5Labels[kompAbNr][2] == allLabels[0]
							|| m5Labels[kompAbNr][2] == allLabels[10]) {
						istPersOderMatNr(kompAbNr, 2, false, true);

					} else {
						stringEingaben(kompAbNr, 2, false);

					}

					// ////////////////////
					// Abfragen
					// ////////////////////
					switch (kompAbNr) {
					case 4:
						if (tag == null) {
							tag = "Montag";
						}
						if (slot == 0) {
							slot = 1;
						}
						db.abfrageKomplex05(vorlesungsKrzl, tag, slot);
						break;
					case 6:
						if (tag == null) {
							tag = "Montag";
						}
						if (slot == 0) {
							slot = 1;
						}
						db.abfrageKomplex07(profName, slot, tag);
						break;
					case 9:
						db.abfrageKomplex10(istMännlich, veranstaltungsname,
								raumName);
						break;
					case 10:
						if (studiengang == null) {
							studiengang = "Biologische Chemie (Bachelor)";
						}
						db.abfrageKomplex11(vorlesungsKrzl, studiengang,
								profName);
						break;
					case 11:
						db.abfrageKomplex12(veranstaltungsname, profName,
								raumName);
						break;
					case 12:
						if (tag == null) {
							tag = "Montag";
						}
						if (fakultät == null) {
							fakultät = "Biotechnologie";
						}
						db.abfrageKomplex13(tag, istCompRaum, fakultät);
						break;
					default:
					}

					break;
				default:
				}

			}

			daten = db.lesenjava();
			for (LinkedHashMap<String, String> datensatz : daten) {
				bspTextArea.setText(bspTextArea.getText() + "\n"
						+ datensatz.toString());
				System.out.println(datensatz);
			}
			try {
				db.close();
			} catch (Exception e) {
			}
			db = null;

		}

		if (ev.getSource() == computerRaum) {
			istCompRaum = computerRaum.getModel().isSelected();
		}
		for (int i = 0; i < tabellen.length; i++) {
			if (tabellen[i] == quelle) {
				tabellenNummer = i;
				allgTab(i);

			}
		}

	}

	private void primeKeyTab() {
		pKListe[0] = "Personen-ID";
		pKListe[1] = "PersonalNr";
		pKListe[2] = "MatrikelNr";
		pKListe[3] = "Fakultät-ID";
		pKListe[4] = "Studiengang-ID";
		pKListe[5] = "Veranstaltung-ID";
		pKListe[6] = "Veranstaltungsname-ID";
		pKListe[7] = "Raum-Bezeichnung";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void allgTab(int i) {
		alleAttribute();

		innerCenter2 = new JPanel();
		innerCenter2.setLayout(new GridLayout(15, 4));
		// 3 means löschen //TODO WÖRK
		if (allgDB == 3) {

			cleanAndTitel();
			pK = new JLabel("Bitte " + pKListe[i] + " eingeben");
			pK.setFont(new Font("Serif", Font.PLAIN, 18));
			userInputID = new JTextField();
			innerCenter2.setLayout(new GridLayout(15, 4, 5, 5));
			fusszeile[3].setVisible(true);
			innerCenter2.add(pK, BorderLayout.NORTH);
			innerCenter2.add(userInputID);
			jpCenter.add(innerCenter2, BorderLayout.CENTER);
		}
		// 2 means ändern
		if (allgDB == 2) {
			cleanAndTitel();
			JLabel pK = new JLabel("Bitte " + pKListe[i] + " eingeben");
			pK.setFont(new Font("Serif", Font.PLAIN, 18));
			JTextField iD = new JTextField();
			switch (i) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			default:

			}

			innerCenter2.add(pK, BorderLayout.NORTH);
			innerCenter2.add(iD);
			jpCenter.add(innerCenter2, BorderLayout.CENTER);
		}
		// 1 means hinzufügen
		if (allgDB == 1) {
			fusszeile[1].setVisible(true);
			fusszeile[1].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent eve) {
					Object quelle = eve.getSource();
					if (fusszeile[1] == quelle) {
						switch (tabellenNummer) {
						case 0: // person funkt
							boolean pIsMännlich = false;
							boolean hatVorname;
							boolean hatNachname = false;
							boolean hatBday = false;
							if (personEingabe[3].getText().equals("true")) {
								pIsMännlich = true;
							}
							if (personEingabe[0].getText().equals("")
									|| personEingabe == null) {
								hatVorname = false;
							} else {
								hatVorname = true;
							}
							if (personEingabe[1].getText().equals("")
									|| personEingabe == null) {
								hatNachname = false;
							} else {
								hatNachname = true;
							}
							if (personEingabe[2].getText().equals("")
									|| personEingabe == null) {
								hatBday = false;
							} else {
								hatBday = true;
							}
							if (hatVorname == false && hatNachname == false) {
								if (hatBday == true) {
									JOptionPane
											.showMessageDialog(
													jf,
													"Bitte einen Vornamen und Nachnamen eingeben",
													"Keinen Vornamen und Nachnamen",
													JOptionPane.ERROR_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(jf,
											"Bitte Information eingeben",
											"Keine Eingabe",
											JOptionPane.ERROR_MESSAGE);
								}

							}
							if (hatVorname == true && hatNachname == false) {
								if (hatBday == true) {
									JOptionPane.showMessageDialog(jf,
											"Bitte einen Nachnamen eingeben",
											"Keinen Nachnamen",
											JOptionPane.ERROR_MESSAGE);
								} else {
									JOptionPane
											.showMessageDialog(
													jf,
													"Bitte Nachnamen und Geburtsdatum eingeben",
													"Kein Nachname und Geburtsdatum",
													JOptionPane.ERROR_MESSAGE);
								}

							}
							if (hatVorname == false && hatNachname == true) {
								if (hatBday == true) {
									JOptionPane.showMessageDialog(jf,
											"Bitte einen Vornamen eingeben",
											"Keinen Vornamen",
											JOptionPane.ERROR_MESSAGE);
								} else {
									JOptionPane
											.showMessageDialog(
													jf,
													"Bitte Vornamen und Geburtsdatum eingeben",
													"Kein Vorname und Geburtsdatum",
													JOptionPane.ERROR_MESSAGE);
								}

							}
							if (hatVorname == true && hatNachname == true) {
								if (hatBday == false) {
									JOptionPane.showMessageDialog(jf,
											"Bitte ein Geburtsdatum eingeben",
											"Kein Geburtsdatum",
											JOptionPane.ERROR_MESSAGE);
								}
							}
							if (hatVorname == true && hatNachname == true
									&& hatBday == true) {
								String pVorname = personEingabe[0].getText();
								String pNachname = personEingabe[1].getText();
								String pBday = personEingabe[2].getText();
								System.out.println(pVorname + " " + pNachname
										+ " " + pBday + " " + pIsMännlich);
								// TODO wenn alles ready dann syso weg und
								// kommentar
								// stattdessen
								// sv.personHinzufuegen(pVorname, pNachname,
								// pBday,
								// pIsMännlich);

							}

							break;
						case 1:// dozent funk
							int helpInt = -1;
							boolean istZahl = false;
							boolean hatName = false;
							try {
								helpInt = Integer.parseInt(profEingabe[1]
										.getText());
								istZahl = true;
							} catch (Exception e) {
								istZahl = false;
							}
							if (profEingabe[0].getText().equals("")
									|| profEingabe == null) {
								hatName = false;
							} else {
								hatName = true;
							}

							if (istZahl == false && hatName == false) {
								JOptionPane
										.showMessageDialog(
												jf,
												"Fehlende/Falsche Eingaben.\nBitte geben Sie eine Personen-ID und einen Professor/Dozent-Kürzel ein.",
												"Falsche Eingabe",
												JOptionPane.ERROR_MESSAGE);
								profEingabe[1].setText("");
							}
							if (istZahl == true && hatName == false) {
								JOptionPane
										.showMessageDialog(
												jf,
												"Bitte einen Professor/Dozent-Kürzel eingeben",
												"Kein Professor/Dozent-Kürzel",
												JOptionPane.ERROR_MESSAGE);

							}
							if (istZahl == false && hatName == true) {
								JOptionPane
										.showMessageDialog(
												jf,
												"Falsche Eingaben.\nBitte geben Sie eine Personen-ID ein.",
												"Falsche Eingabe",
												JOptionPane.ERROR_MESSAGE);
								profEingabe[1].setText("");
							}

							if (istZahl == true && hatName == true) {
								String profKürzel = profEingabe[0].getText();
								int profPersonalID = helpInt;
								String profFakultät;
								if (profEingabe[1].getText() == null
										|| profEingabe[2].getText().equals("")) {
									profFakultät = "Biotechnologie";
								} else {
									int listenZahl = Integer
											.parseInt(profEingabe[2].getText());
									profFakultät = fakultätenListe[listenZahl];
								}
								System.out.println(profKürzel + " "
										+ profFakultät + " " + profPersonalID);
								// TODO wenn alles ready dann syso weg und
								// kommentar
								// stattdessen
								// sv.dozentHinzufuegen(profKürzel,
								// profFakultät,
								// profPersonalID);

							}
							break;

						case 2: // Studierender funkt
							int sHelpInt1 = -1;
							boolean sIstZahl1 = false;
							int sHelpInt2 = -1;
							boolean sIstZahl2 = false;
							try {
								sHelpInt1 = Integer.parseInt(studEingabe[0]
										.getText());
								sIstZahl1 = true;
							} catch (NumberFormatException e) {
								sIstZahl1 = false;
							}
							try {
								sHelpInt2 = Integer.parseInt(studEingabe[1]
										.getText());
								sIstZahl2 = true;
							} catch (NumberFormatException e) {
								sIstZahl2 = false;
							}
							if (sIstZahl1 == false && sIstZahl2 == false) {
								JOptionPane
										.showMessageDialog(
												jf,
												"Eingaben müssen Zahlen sein.\nBitte geben Sie eine Matrikelnummer und eine Personen-ID ein.",
												"Falsche Eingabe",
												JOptionPane.ERROR_MESSAGE);
								studEingabe[0].setText("");
							}
							if (sIstZahl1 == false & sIstZahl2 == true) {
								JOptionPane
										.showMessageDialog(
												jf,
												"Eingabe muss eine Zahl sein.\nBitte geben Sie eine Matrikelnummer ein.",
												"Falsche Eingabe",
												JOptionPane.ERROR_MESSAGE);
								studEingabe[0].setText("");
							}
							if (sIstZahl2 == false && sIstZahl1 == true) {
								JOptionPane
										.showMessageDialog(
												jf,
												"Eingabe muss eine Zahl sein.\nBitte geben Sie eine Personen-ID ein.",
												"Falsche Eingabe",
												JOptionPane.ERROR_MESSAGE);
								studEingabe[1].setText("");
							}
							if (sIstZahl1 == true && sIstZahl2 == true) {
								int sMatrikelNr = sHelpInt1;
								int sPersonID = sHelpInt2;
								int sSemester;
								String sStudiengang;
								if (studEingabe[3].getText().equals("")
										|| studEingabe[3].getText() == null) {
									sSemester = 1;
								} else {
									sSemester = Integer.parseInt(studEingabe[3]
											.getText()) + 1;
								}
								if (studEingabe[2].getText() == null
										|| studEingabe[2].getText().equals("")) {
									sStudiengang = "Biologische Chemie (Bachelor)";
								} else {
									sStudiengang = studiengängeListe[Integer
											.parseInt(studEingabe[2].getText())];
								}

								System.out.println(sMatrikelNr + " "
										+ sSemester + " " + sStudiengang + " "
										+ sPersonID);
								// TODO wenn alles ready dann syso weg und
								// kommentar
								// stattdessen
								// sv.studierenderHinzufuegen(sMatrikelNr,sSemester,
								// sStudiengang, sPersonID)
							}
							break;
						case 3:

							if (fakEingabe.getText().equals("")
									|| fakEingabe == null) {
								JOptionPane.showMessageDialog(jf,
										"Bitte einen Studiengangname eingeben",
										"Kein Studiengangname",
										JOptionPane.ERROR_MESSAGE);
							} else {
								String fakName = fakEingabe.getText();
								System.out.println(fakName);
								// TODO wenn alles ready dann syso weg und
								// kommentar
								// stattdessen
								// sv.fakultaetHinzufuegen(fakName)
							}
							break;
						case 4:

							if (studiengangEingabe.getText().equals("")
									|| studiengangEingabe == null) {
								JOptionPane.showMessageDialog(jf,
										"Bitte einen Studiengangname eingeben",
										"Kein Studiengangname",
										JOptionPane.ERROR_MESSAGE);
							} else {
								String studiengangName = studiengangEingabe
										.getText();
								System.out.println(studiengangName);
								// TODO wenn alles ready dann syso weg und
								// kommentar
								// stattdessen
								// sv.studiengangHinzufuegen(studiengangName);
							}
							break;
						case 5:
							int vSemester;
							int vHelpInt1 = -1;
							boolean vIstZahl1 = false;
							int vHelpInt2 = -1;
							boolean vIstZahl2 = false;
							int vHelpInt3 = -1;
							boolean vIstZahl3 = false;
							try {
								vHelpInt1 = Integer
										.parseInt(veranstaltungEingabe[1]
												.getText());
								vIstZahl1 = true;
							} catch (NumberFormatException e) {
								vIstZahl1 = false;
							}
							if (vIstZahl1 == false) {
								JOptionPane.showMessageDialog(jf,
										"Eingabe muss eine Zahl sein",
										"Falsche Eingabe",
										JOptionPane.ERROR_MESSAGE);
								veranstaltungEingabe[1].setText("");
							}
							try {
								vHelpInt2 = Integer
										.parseInt(veranstaltungEingabe[2]
												.getText());
								vIstZahl2 = true;
							} catch (Exception e) {
								vIstZahl2 = false;
							}
							if (vIstZahl2 == false) {
								JOptionPane.showMessageDialog(jf,
										"Eingabe muss eine Zahl sein",
										"Falsche Eingabe",
										JOptionPane.ERROR_MESSAGE);
								veranstaltungEingabe[2].setText("");
							}
							try {
								vHelpInt3 = Integer
										.parseInt(veranstaltungEingabe[3]
												.getText());
								vIstZahl3 = true;
							} catch (NumberFormatException e) {
								vIstZahl3 = false;
							}
							if (vIstZahl3 == false) {
								JOptionPane.showMessageDialog(jf,
										"Eingabe muss eine Zahl sein",
										"Falsche Eingabe",
										JOptionPane.ERROR_MESSAGE);
								veranstaltungEingabe[3].setText("");
							}
							if (vIstZahl1 == true && vIstZahl2 == true
									&& vIstZahl3 == true) {
								if (veranstaltungEingabe[4].getText()
										.equals("")
										|| veranstaltungEingabe[4].getText() == null) {
									vSemester = 1;
								} else {
									vSemester = Integer
											.parseInt(veranstaltungEingabe[4]
													.getText()) + 1;
								}
								int vDauer = vHelpInt1;
								int vPersonalNr = vHelpInt2;
								int vStundenplanNr = vHelpInt3;
								if (veranstaltungEingabe[0].getText()
										.equals("")
										|| veranstaltungEingabe[0] == null) {
									JOptionPane
											.showMessageDialog(
													jf,
													"Bitte einen Veranstaltungsname eingeben",
													"Kein Veranstaltungsname",
													JOptionPane.ERROR_MESSAGE);
								} else {
									String vVorlesungsname = veranstaltungEingabe[0]
											.getText();
									System.out.println(vSemester + " " + vDauer
											+ " " + vPersonalNr + " "
											+ vStundenplanNr + " "
											+ vVorlesungsname);
									// TODO wenn alles ready dann syso weg und
									// kommentar
									// stattdessen
									// veranstaltungHinzufuegen(vSemester,vDauer,
									// vPersonalNr, vStundenplanNr,
									// vVorlesungsname)
								}

							}

							break;
						case 6:
							boolean vnNameIN = false;
							boolean vnKürzelIN = false;
							if (vNameEingabe[0].getText().equals("")
									|| vNameEingabe[0] == null) {
								JOptionPane.showMessageDialog(jf,
										"Bitte einen Vorlesungsnamen eingeben",
										"Kein Vorlesungsname",
										JOptionPane.ERROR_MESSAGE);
							} else {
								vnNameIN = true;
							}
							if (vNameEingabe[1].getText().equals("")
									|| vNameEingabe[1] == null) {
								JOptionPane
										.showMessageDialog(
												jf,
												"Bitte einen Vorlesungsnamenkürzel eingeben",
												"Kein Vorlesungsnamekürzel",
												JOptionPane.ERROR_MESSAGE);
							} else {
								vnKürzelIN = true;
							}
							if (vnKürzelIN == true && vnNameIN == true) {
								String vnName = vNameEingabe[0].getText();
								String vnKürzel = vNameEingabe[1].getText();
								System.out.println(vnName + " " + vnKürzel);
								// TODO wenn alles ready dann syso weg und
								// kommentar
								// stattdessen
								// veranstaltungsnameHinzufuegen(String name,
								// String
								// kuerzel)
							}

							break;
						case 7:

							if (raumEingabe.getText().equals("")
									|| raumEingabe == null) {
								JOptionPane.showMessageDialog(jf,
										"Bitte einen Raumnamen eingeben",
										"Kein Raumnamen",
										JOptionPane.ERROR_MESSAGE);
							} else {
								String rName = raumEingabe.getText();
								boolean isPcRaum = rIstPcRaum;
								System.out.println(rName + " " + isPcRaum);
								// TODO wenn alles ready dann syso weg und
								// kommentar
								// stattdessen
								// raumHinzufuegen(rName,isPcRaum)
							}

							break;
						default:
						}

					}

				}
			});
			switch (i) {
			case 0: // person
				cleanAndTitel();
				JLabel personenAttribute[] = new JLabel[4];
				for (int j = 0; j < personenAttribute.length; j++) {
					personenAttribute[j] = new JLabel();
					personEingabe[j] = new JTextField();
					personenAttribute[j].setFont(new Font("Serif", Font.PLAIN,
							18));
				}
				JCheckBox istPMännlich = new JCheckBox();
				istPMännlich.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == istPMännlich) {
							personEingabe[3].setText("true");
						}

					}
				});
				istPMännlich.setText("Männlich");
				personenAttribute[0].setText("Bitte Vorname eingeben.");
				personenAttribute[1].setText("Bitte Nachname eingeben.");
				personenAttribute[2].setText("Bitte Geburtsdatum auswählen.");
				personenAttribute[3].setText("Wenn männlich, bitte ankreuzen.");
				for (int k = 0; k < personEingabe.length; k++) {
					innerCenter2.add(personenAttribute[k]);
					innerCenter2.add(personEingabe[k]);
				}
				innerCenter2.remove(personEingabe[3]);
				innerCenter2.add(personenAttribute[3]);
				innerCenter2.add(istPMännlich);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 1: // dozent
				cleanAndTitel();
				JLabel profAttribute[] = new JLabel[3];
				fakultätAuswahl2 = new JComboBox(fakultätenListe);
				fakultätAuswahl2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == fakultätAuswahl2) {
							for (int i = 0; i < fakultätenListe.length; i++) {
								if (fakultätAuswahl2.getSelectedItem() == fakultätenListe[i]) {
									profEingabe[2].setText("" + i);
								}
							}

						}

					}
				});
				for (int m = 0; m < profAttribute.length; m++) {
					profAttribute[m] = new JLabel();
					profEingabe[m] = new JTextField();
					profAttribute[m].setFont(new Font("Serif", Font.PLAIN, 18));
				}
				profAttribute[0]
						.setText("Bitte Professor/Dozent-Kürzel eingeben.");
				profAttribute[2].setText("Bitte Fakultät auswählen.");
				profAttribute[1].setText("Bitte Personen-ID eingeben");
				for (int n = 0; n < profAttribute.length; n++) {
					innerCenter2.add(profAttribute[n]);
					innerCenter2.add(profEingabe[n]);
				}
				innerCenter2.remove(profEingabe[2]);
				innerCenter2.add(fakultätAuswahl2);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 2:// student
				cleanAndTitel();
				JLabel studAttribute[] = new JLabel[4];

				for (int m = 0; m < studAttribute.length; m++) {
					studAttribute[m] = new JLabel();
					studEingabe[m] = new JTextField();
					studAttribute[m].setFont(new Font("Serif", Font.PLAIN, 18));
				}
				semesterAuswahl2 = new JComboBox(semesterListe);
				semesterAuswahl2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == semesterAuswahl2) {
							for (int i = 0; i < semesterListe.length; i++) {
								if (semesterAuswahl2.getSelectedItem() == semesterListe[i]) {
									studEingabe[3].setText("" + i);
								}
							}
						}

					}
				});
				studiengangAuswahl2 = new JComboBox(studiengängeListe);
				studiengangAuswahl2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == studiengangAuswahl2) {
							for (int i = 0; i < studiengängeListe.length; i++) {
								if (studiengangAuswahl2.getSelectedItem() == studiengängeListe[i]) {
									studEingabe[2].setText("" + i);
								}
							}
						}
					}
				});
				studAttribute[0].setText("Bitte MatrikelNummer eingeben.");
				innerCenter2.add(studAttribute[0]);
				innerCenter2.add(semesterAuswahl2);
				studAttribute[1].setText("Bitte Personen-ID auswählen");
				studAttribute[2].setText("Bitte Studiengang auswählen.");
				studAttribute[3].setText("Bitte Semester auswählen");
				for (int n = 0; n < 2; n++) {
					innerCenter2.add(studAttribute[n]);
					innerCenter2.add(studEingabe[n]);
				}
				innerCenter2.add(studAttribute[2]);
				innerCenter2.remove(studEingabe[2]);
				innerCenter2.add(studiengangAuswahl2);
				innerCenter2.add(studAttribute[3]);
				innerCenter2.remove(studEingabe[3]);
				innerCenter2.add(semesterAuswahl2);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 3:// fakultät
				cleanAndTitel();
				JLabel fakAttribut = new JLabel();
				fakAttribut.setFont(new Font("Serif", Font.PLAIN, 18));
				fakAttribut.setText("Bitte Fakultät-Name eingeben.");
				innerCenter2.add(fakAttribut);
				innerCenter2.add(fakEingabe);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 4:// Studiengang
				cleanAndTitel();
				JLabel studiengangAttribut = new JLabel();
				studiengangAttribut.setFont(new Font("Serif", Font.PLAIN, 18));
				studiengangAttribut.setText("Bitte Studiengang-Name eingeben.");
				innerCenter2.add(studiengangAttribut);
				innerCenter2.add(studiengangEingabe);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 5:// Veranstaltung
				cleanAndTitel();
				JLabel veranstaltungAttribute[] = new JLabel[5];
				for (int j = 0; j < veranstaltungAttribute.length; j++) {
					veranstaltungAttribute[j] = new JLabel();
					veranstaltungAttribute[j].setFont(new Font("Serif",
							Font.PLAIN, 18));
					veranstaltungEingabe[j] = new JTextField();
				}
				semesterAuswahl2 = new JComboBox(semesterListe);
				semesterAuswahl2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == semesterAuswahl2) {
							for (int i = 0; i < semesterListe.length; i++) {
								if (semesterAuswahl2.getSelectedItem() == semesterListe[i]) {
									veranstaltungEingabe[4].setText("" + i);
								}
							}
						}

					}
				});

				veranstaltungAttribute[4].setText("Bitte Semester auswählen.");
				innerCenter2.add(veranstaltungAttribute[0]);
				innerCenter2.add(semesterAuswahl2);

				veranstaltungAttribute[1]
						.setText("Bitte Vorlesungsdauer eingeben (in Minuten).");
				veranstaltungAttribute[2]
						.setText("Bitte Personal-Nr. des Professors/Dozenten eingeben.");
				veranstaltungAttribute[3]
						.setText("Bitte Studenplan-ID eingeben.");
				veranstaltungAttribute[0]
						.setText("Bitte Vorlesungsnamen eingeben.");
				for (int h = 0; h < veranstaltungAttribute.length; h++) {
					innerCenter2.add(veranstaltungAttribute[h]);
					innerCenter2.add(veranstaltungEingabe[h]);
				}
				innerCenter2.remove(veranstaltungEingabe[4]);
				innerCenter2.add(semesterAuswahl2);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;

			case 6: // veranstaltungsname
				cleanAndTitel();
				JLabel vNameAttribute[] = new JLabel[2];
				for (int r = 0; r < vNameAttribute.length; r++) {
					vNameAttribute[r] = new JLabel();
					vNameAttribute[r]
							.setFont(new Font("Serif", Font.PLAIN, 18));
					vNameEingabe[r] = new JTextField();
				}
				vNameAttribute[0].setText("Bitte Vorlesungs-Name eingeben.");
				vNameAttribute[1].setText("Bitte Vorlesungs-Kürzel eingeben.");
				for (int t = 0; t < vNameAttribute.length; t++) {
					innerCenter2.add(vNameAttribute[t]);
					innerCenter2.add(vNameEingabe[t]);
				}
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 7:// raum
				cleanAndTitel();
				JLabel raumName = new JLabel("Bitte Raum-Bezeichnung eingeben.");
				JLabel raumIstPc = new JLabel("Wenn PC-Raum, bitte ankreuzen.");
				raumName.setFont(new Font("Serif", Font.PLAIN, 18));
				raumIstPc.setFont(new Font("Serif", Font.PLAIN, 18));
				istPcRaum.setText("PcRaum");
				istPcRaum.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (istPcRaum.isSelected()) {
							rIstPcRaum = true;
						}

					}
				});
				innerCenter2.add(raumName);
				innerCenter2.add(raumEingabe);
				innerCenter2.add(raumIstPc);
				innerCenter2.add(istPcRaum);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;

			default:
			}
		}

	}

	private void alleAttribute() {
		personenLabels = new JLabel[4];
		initAllLabels(personenLabels);
		profLabels = new JLabel[3];
		initAllLabels(profLabels);
		studentLabels = new JLabel[3];
		initAllLabels(studentLabels);
		fakultätLabel = new JLabel();
		studiengangLabel = new JLabel();
		veranstaltungLabels = new JLabel[5];
		initAllLabels(veranstaltungLabels);
		vorlesungsnameLabels = new JLabel[2];
		initAllLabels(vorlesungsnameLabels);
		raumLabels = new JLabel();

		personenLabels[0].setText("Vorname:");
		personenLabels[1].setText("Nachname:");
		personenLabels[2].setText("Geburtsdatum:");
		personenLabels[3].setText("Ist Männlich?");

		profLabels[0].setText("Kürzel:");
		profLabels[1].setText("Fakultät:");
		profLabels[2].setText("Person-ID:");

		studentLabels[0].setText("Semester:");
		studentLabels[1].setText("Studiengang:");
		studentLabels[2].setText("Person-ID:");

		fakultätLabel.setText("Fakultätsname:");

		studiengangLabel.setText("Studiengangname:");

		veranstaltungLabels[0].setText("Semester:");
		veranstaltungLabels[1].setText("Dauer:");
		veranstaltungLabels[2].setText("Personal-Nummer:");
		veranstaltungLabels[3].setText("Studenplan-ID:");
		veranstaltungLabels[4].setText("Vorlesungsnamen-ID:");

		vorlesungsnameLabels[0].setText("Vorlesungsname:");
		vorlesungsnameLabels[1].setText("Vorlesungskürzel:");

		raumLabels.setText("Ist PC-Raum?");

	}

	private void initAllLabels(JLabel[] Labels) {
		for (int i = 0; i < Labels.length; i++) {
			Labels[i] = new JLabel();
			Labels[i].setFont(new Font("Serif", Font.PLAIN, 18));
		}

	}

	private void initAllTFields(JTextField[] TField, boolean editable) {
		for (int i = 0; i < TField.length; i++) {
			TField[i] = new JTextField();
			TField[i].setFont(new Font("Serif", Font.PLAIN, 18));
			TField[i].setEditable(editable);

		}

	}

	private void cleanAndTitel() {
		clear();
		inDieserTabelle.setText("In der Tabelle: "
				+ tabellen[tabellenNummer].getText() + "   ");
		inDieserTabelle.setFont(new Font("Serif", Font.PLAIN, 15));
		inDieserTabelle.setHorizontalAlignment(SwingConstants.RIGHT);
		jpCenter.add(inDieserTabelle, BorderLayout.NORTH);

	}

	/*
	 * private void veranstaltungNameTab() { // Auto-generated method stub
	 * 
	 * }
	 * 
	 * private void veranstaltungTab() { // Auto-generated method stub
	 * 
	 * }
	 * 
	 * private void studiengangTab() { // Auto-generated method stub
	 * 
	 * }
	 * 
	 * private void fakultätTab() { // Auto-generated method stub
	 * 
	 * }
	 * 
	 * private void studentTab() { // Auto-generated method stub
	 * 
	 * }
	 * 
	 * private void dozentTab() { // Auto-generated method stub
	 * 
	 * }
	 * 
	 * private void personTab() {
	 * 
	 * }
	 */
	//
	private void stringEingaben(int AbNr, int i, boolean istEinfach) {
		if (istEinfach) {
			if (m4Labels[AbNr][i] == allLabels[3]) {
				vorlesungsKrzl = m4Parameter[AbNr][i].getText();
			}
			if (m4Labels[AbNr][i] == allLabels[5]) {
				profName = m4Parameter[AbNr][i].getText();
			}
			if (m4Labels[AbNr][i] == allLabels[6]) {
				profKrzl = m4Parameter[AbNr][i].getText();
			}
			if (m4Labels[AbNr][i] == allLabels[7]) {
				raumName = m4Parameter[AbNr][i].getText();
			}
			if (m4Labels[AbNr][i] == allLabels[12]) {
				veranstaltungsname = m4Parameter[AbNr][i].getText();
			}
		}
		if (!istEinfach) {
			if (m5Labels[AbNr][i] == allLabels[3]) {
				vorlesungsKrzl = m5Parameter[AbNr][i].getText();
			}
			if (m5Labels[AbNr][i] == allLabels[5]) {
				profName = m5Parameter[AbNr][i].getText();
			}
			if (m5Labels[AbNr][i] == allLabels[6]) {
				profKrzl = m5Parameter[AbNr][i].getText();
			}
			if (m5Labels[AbNr][i] == allLabels[7]) {
				raumName = m5Parameter[AbNr][i].getText();
			}
			if (m5Labels[AbNr][i] == allLabels[12]) {
				veranstaltungsname = m5Parameter[AbNr][i].getText();
			}
		}

	}

	private void istPersOderMatNr(int AbNr, int i, boolean istEinfach,
			boolean istAbfrage) {
		boolean ist0oder10 = false;

		if (istEinfach) {
			if (m4Labels[AbNr][i] == allLabels[0]
					|| m4Labels[AbNr][i] == allLabels[10]) {
				ist0oder10 = true;
			}
		}
		if (!istEinfach) {
			if (m5Labels[AbNr][i] == allLabels[0]
					|| m5Labels[AbNr][i] == allLabels[10]) {
				ist0oder10 = true;
			}
		}
		if (ist0oder10) {
			int helpInt = 0;
			boolean istZahl = false;
			String helpString;
			if (istEinfach) {
				helpString = m4Parameter[AbNr][i].getText();
			} else {
				helpString = m5Parameter[AbNr][i].getText();
			}

			try {
				helpInt = Integer.parseInt(helpString);
				istZahl = true;
			} catch (NumberFormatException e) {
				istZahl = false;
			}
			if (istZahl == false) {
				JOptionPane.showMessageDialog(jf,
						"Eingabe muss eine Zahl sein", "Falsche Eingabe",
						JOptionPane.ERROR_MESSAGE);
				if (istEinfach) {
					m4Parameter[AbNr][0].setText("");
				} else {
					m5Parameter[AbNr][0].setText("");
				}

			}

			if (m4Labels[AbNr][i] == allLabels[0]
					|| m5Labels[AbNr][i] == allLabels[0]) {
				matrikelNr = helpInt;
			} else {
				persoNr = helpInt;
			}

		}

	}

	private void allDropDrowns(int i, int j, boolean istEinfach) {
		dropdownSemester(i, j, istEinfach);
		dropdownStudiengang(i, j, istEinfach);
		dropdownFakultät(i, j, istEinfach);
		dropdownTag(i, j, istEinfach);
		dropdownSlot(i, j, istEinfach);
		dropdownGeschlecht(i, j, istEinfach);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void dropdownGeschlecht(int i, int j, boolean istEinfach) {
		String geschListe[] = { "Männlich", "Weiblich" };
		geschAuswahl = new JComboBox(geschListe);
		geschAuswahl.addActionListener(this);
		if (istEinfach) {
			if (m4Labels[i][j] == allLabels[11]) {
				innerCenter.remove(m4Parameter[i][j]);
				innerCenter.add(geschAuswahl);
			}

		} else {
			if (m5Labels[i][j] == allLabels[11]) {
				innerCenter.remove(m5Parameter[i][j]);
				innerCenter.add(geschAuswahl);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void dropdownSlot(int i, int j, boolean istEinfach) {
		String slotListe[] = { "1. Slot", "2. Slot", "3. Slot", "4. Slot",
				"5. Slot", "6. Slot" };
		slotAuswahl = new JComboBox(slotListe);
		slotAuswahl.addActionListener(this);
		if (istEinfach) {
			if (m4Labels[i][j] == allLabels[9]) {

				innerCenter.remove(m4Parameter[i][j]);
				innerCenter.add(slotAuswahl);
			}
		} else {
			if (m5Labels[i][j] == allLabels[9]) {

				innerCenter.remove(m5Parameter[i][j]);
				innerCenter.add(slotAuswahl);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void dropdownTag(int i, int j, boolean istEinfach) {

		tagAuswahl = new JComboBox(tageListe);
		tagAuswahl.addActionListener(this);
		if (istEinfach) {
			if (m4Labels[i][j] == allLabels[8]) {

				innerCenter.remove(m4Parameter[i][j]);
				innerCenter.add(tagAuswahl);
			}
		} else {
			if (m5Labels[i][j] == allLabels[8]) {

				innerCenter.remove(m5Parameter[i][j]);
				innerCenter.add(tagAuswahl);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void dropdownSemester(int i, int j, boolean istEinfach) {
		semesterAuswahl = new JComboBox(semesterListe);
		semesterAuswahl.addActionListener(this);
		if (istEinfach) {
			if (m4Labels[i][j] == allLabels[1]) {

				innerCenter.remove(m4Parameter[i][j]);
				innerCenter.add(semesterAuswahl);
			}
		} else {
			if (m5Labels[i][j] == allLabels[1]) {

				innerCenter.remove(m5Parameter[i][j]);
				innerCenter.add(semesterAuswahl);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void dropdownFakultät(int i, int j, boolean istEinfach) {
		fakultätAuswahl = new JComboBox(fakultätenListe);
		fakultätAuswahl.addActionListener(this);
		if (istEinfach) {
			if (m4Labels[i][j] == allLabels[4]) {

				innerCenter.remove(m4Parameter[i][j]);
				innerCenter.add(fakultätAuswahl);
			}
		} else {
			if (m5Labels[i][j] == allLabels[4]) {

				innerCenter.remove(m5Parameter[i][j]);
				innerCenter.add(fakultätAuswahl);
			}
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void dropdownStudiengang(int i, int j, boolean istEinfach) {
		studiengangAuswahl = new JComboBox(studiengängeListe);
		studiengangAuswahl.addActionListener(this);
		if (istEinfach) {
			if (m4Labels[i][j] == allLabels[2]) {

				innerCenter.remove(m4Parameter[i][j]);
				innerCenter.add(studiengangAuswahl);
			}
		} else {
			if (m5Labels[i][j] == allLabels[2]) {

				innerCenter.remove(m5Parameter[i][j]);
				innerCenter.add(studiengangAuswahl);
			}
		}

	}

	private void einAbf(int i) {
		istEinfach = true;
		einAbNr = i;
		clear();
		abfrageSchicken = new JButton("Abfrage schicken");
		abfrageSchicken.addActionListener(this);
		JTextArea abfrage = new JTextArea();
		abfrage.setText(m4Text[i].getText());
		abfrage.setFont(new Font("Serif", Font.PLAIN, 18));
		abfrage.setLineWrap(true);
		abfrage.setWrapStyleWord(true);
		abfrage.setEditable(false);
		innerCenter = new JPanel();
		jpCenter.add(innerCenter, BorderLayout.CENTER);
		innerCenter.setLayout(new GridLayout(15, 1));
		jpCenter.add(abfrageSchicken, BorderLayout.SOUTH);
		jpCenter.add(abfrage, BorderLayout.NORTH);
		jf.setSize(960, 720);
	}

	private void komAbf(int i) {
		istEinfach = false;
		kompAbNr = i;
		clear();
		abfrageSchicken = new JButton("Abfrage schicken");
		abfrageSchicken.addActionListener(this);
		JTextArea abfrage = new JTextArea();
		abfrage.setText(m5Text[i].getText());
		abfrage.setFont(new Font("Serif", Font.PLAIN, 18));
		abfrage.setLineWrap(true);
		abfrage.setWrapStyleWord(true);
		abfrage.setEditable(false);
		innerCenter = new JPanel();
		jpCenter.add(innerCenter, BorderLayout.CENTER);
		innerCenter.setLayout(new GridLayout(15, 1));
		jpCenter.add(abfrageSchicken, BorderLayout.SOUTH);
		jpCenter.add(abfrage, BorderLayout.NORTH);
		jf.setSize(960, 720);
	}

	private void menuT5() {
		kompAnfparameterAnzahl[0] = 2;
		kompAnfparameterAnzahl[1] = 2;
		kompAnfparameterAnzahl[2] = 2;
		kompAnfparameterAnzahl[3] = 1;
		kompAnfparameterAnzahl[4] = 3;
		kompAnfparameterAnzahl[5] = 2;
		kompAnfparameterAnzahl[6] = 3;
		kompAnfparameterAnzahl[7] = 2;
		kompAnfparameterAnzahl[8] = 2;
		kompAnfparameterAnzahl[9] = 3;
		kompAnfparameterAnzahl[10] = 3;
		kompAnfparameterAnzahl[11] = 3;
		kompAnfparameterAnzahl[12] = 3;
		kompAnfparameterAnzahl[13] = 2;
		kompAnfparameterAnzahl[14] = 2;
		clear();
		kA = new JPanel();
		kA.setLayout(new GridLayout(15, 2));
		jpCenter.add(kA, BorderLayout.CENTER);
		m5Buttons = new JButton[15];
		m5Text = new JTextArea[15];
		for (int i = 0; i < m5Text.length; i++) {
			m5Text[i] = new JTextArea();
			m5Text[i].setEditable(false);
			m5Text[i].setFont(new Font("Serif", Font.PLAIN, 15));
			m5Text[i].setLineWrap(true);
			m5Text[i].setWrapStyleWord(true);
		}
		m5Text[0]
				.setText("Zeige den Dozenten, der die Veranstaltung |_____| im Raum |_____| hält.	");
		m5Text[1]
				.setText("Zeige alle Veranstaltungen von dem Studierenden mit der MatrikelNr |_____| , die im Raum |_____| stattfinden.");
		m5Text[2]
				.setText("Zeige alle Veranstaltungen der Studierenden vom Studiengang |_____|, die am |_____|(Tag) stattfinden.");
		m5Text[3]
				.setText("Zeige die Bezeichnung aller Räume in denen Veranstaltungen vom Dozenten mit der Personalnummer |_____| stattfinden.");
		m5Text[4]
				.setText("Zeige den Namen des Dozenten, der die Veranstaltung mit dem Veranstaltungskuerzel |_____| am |_____|(Tag) im Slot |_____| hält.");
		m5Text[5]
				.setText("Zeige den Stundenplan des Dozenten, den der Student mit der MatrikelNr |_____| in der Veranstaltung |_____| hat.");
		m5Text[6]
				.setText("Zeige den Raum, in der die Veranstaltung vom Dozenten/Professor |_____| am |_____|(Tag) im Slot |_____| gehalten wird.");
		m5Text[7]
				.setText("Zeige alle Räume, die durch den Dozent/Professor |_____| am |_____|(Tag) belegt sind.");
		m5Text[8]
				.setText("Zeige alle Studierenden, die am |_____|(Tag) eine Veranstaltung in einem Computerraum haben/nicht haben.");
		m5Text[9]
				.setText("Zeige alle |_____| Studierenden, die die Veranstaltung |_____| im Raum |_____| besuchen.");
		m5Text[10]
				.setText("Zeige die Tage an denen die Veranstaltung mit dem Veranstaltungskuerzel |_____|, die im Studiengang |_____| vom Dozenten/Professor |_____| gehalten wird, stattfindet.");
		m5Text[11]
				.setText("Zeige die Matrikelnummer aller Studierenden, die die Veranstaltung |_____| beim Dozenten/Professor |_____| im Raum |_____| besuchen.");
		m5Text[12]
				.setText("Zeige alle Dozenten der Fakultät |_____|, die eine Veranstaltung am |_____| halten und einen Computerraum belegen/nicht belegen.");
		m5Text[13]
				.setText("Zeige alle Vorlesungen, die am |_____|(Tag) von dem Dozenten/Professor mit dem Kuerzel |_____| gehalten werden.");
		m5Text[14]
				.setText("Zeige alle Dozenten, die am |_____|(Tag) im Slot |_____| eine Veranstaltung halten.");
		giveRightLabels();
		for (int i = 0; i < m5Buttons.length; i++) {
			int c = i + 1;
			m5Buttons[i] = new JButton("Abfrage " + c);
			m5Buttons[i].addActionListener(this);
			kA.add(m5Buttons[i]);
			kA.add(m5Text[i]);
		}
		jf.setSize(960, 720);

	}

	// einfache abfragen
	private void menuT4() {
		for (int i = 0; i < einAnfparameterAnzahl.length; i++) {
			einAnfparameterAnzahl[i] = 1;
		}
		einAnfparameterAnzahl[2] = 2;
		einAnfparameterAnzahl[3] = 2;
		einAnfparameterAnzahl[5] = 2;
		einAnfparameterAnzahl[6] = 2;
		clear();
		eA = new JPanel();
		eA.setLayout(new GridLayout(10, 2));
		jpCenter.add(eA, BorderLayout.CENTER);
		m4Buttons = new JButton[10];
		m4Text = new JTextArea[10];
		for (int i = 0; i < m4Text.length; i++) {
			m4Text[i] = new JTextArea();
			m4Text[i].setEditable(false);
			m4Text[i].setFont(new Font("Serif", Font.PLAIN, 18));
			m4Text[i].setLineWrap(true);
			m4Text[i].setWrapStyleWord(true);
		}
		m4Text[0]
				.setText("Zeige den Studiereden mit der Matrikelnummer |_____|.");
		m4Text[1].setText("Zeige alle Studierenden aus dem |_____| Semester.");
		m4Text[2]
				.setText("Zeige alle |_____|Studierenden aus dem Studiengang |_____|.");
		m4Text[3]
				.setText("Zeige alle |_____| Studierenden aus dem Studiengang |_____|.");
		m4Text[4]
				.setText("Zeige alle Studierenden, die die Vorlesung mit dem Kürzel |_____| besuchen.");
		m4Text[5]
				.setText("Zeige alle Studierenden aus dem |_____| Semester, die die Vorlesung mit dem Kürzel |_____| besuchen.");
		m4Text[6]
				.setText("Zeige den Studenplan der Studierenden vom Studiengang |_____| im |_____| Semester.");
		m4Text[7].setText("Zeige alle Dozenten der Fakultät |_____|.");
		m4Text[8]
				.setText("Zeige alle Studierenden mit dem Dozenten Prof. |_____|.");
		m4Text[9]
				.setText("Zeige alle Studierenden die eine Veranstaltung aus dem |_____| Semester besuchen.");
		giveRightLabels();

		for (int i = 0; i < m4Buttons.length; i++) {
			int c = i + 1;
			m4Buttons[i] = new JButton("Abfrage " + c);
			m4Buttons[i].addActionListener(this);
			eA.add(m4Buttons[i]);
			eA.add(m4Text[i]);
		}
		jf.setSize(960, 720);

	}

	// db löschen
	private void menuT3() {
		menuDB("löschen");
	}

	// db ändern
	private void menuT2() {
		menuDB("ändern");
	}

	// db hinzufügen
	private void menuT1() {
		menuDB("hinzufügen");
	}

	// db allgemein frame
	private void menuDB(String db) {
		if (db == "hinzufügen")
			allgDB = 1;
		if (db == "ändern")
			allgDB = 2;
		if (db == "löschen")
			allgDB = 3;
		clear();
		überschrift = new JLabel("Bitte Tabelle auswählen");
		überschrift.setFont(new Font("Serif", Font.PLAIN, 18));
		innerCenter = new JPanel();
		jpCenter.add(innerCenter, BorderLayout.CENTER);
		innerCenter.setLayout(new GridLayout(8, 1));
		for (int i = 0; i < tabellen.length; i++) {
			tabellen[i] = new JButton();
			tabellen[i].addActionListener(this);
			innerCenter.add(tabellen[i]);
		}
		tabellen[0].setText("Person");
		tabellen[1].setText("Dozent");
		tabellen[2].setText("Studierender");
		tabellen[3].setText("Fakuktät");
		tabellen[4].setText("Studiengang");
		tabellen[5].setText("Veranstaltung");
		tabellen[6].setText("Veranstaltungsname");
		tabellen[7].setText("Raum");

		jpCenter.add(überschrift, BorderLayout.NORTH);
		jf.setSize(960, 720);
	}

	private void giveRightLabels() {
		m4Labels = new JLabel[10][2];
		m4Parameter = new JTextField[10][2];
		for (int i = 0; i < m4Parameter.length; i++) {
			m4Parameter[i][0] = new JTextField(30);
			m4Parameter[i][1] = new JTextField(30);
		}
		m5Labels = new JLabel[15][3];
		m5Parameter = new JTextField[15][3];
		for (int i = 0; i < m5Parameter.length; i++) {
			m5Parameter[i][0] = new JTextField(30);
			m5Parameter[i][1] = new JTextField(30);
			m5Parameter[i][2] = new JTextField(30);
		}
		// einfache abfragen labels
		m4Labels[0][0] = allLabels[0];
		m4Labels[1][0] = allLabels[1];
		m4Labels[2][0] = allLabels[11];
		m4Labels[2][1] = allLabels[2];
		m4Labels[3][0] = allLabels[11];
		m4Labels[3][1] = allLabels[2];
		m4Labels[4][0] = allLabels[3];
		m4Labels[5][0] = allLabels[1];
		m4Labels[5][1] = allLabels[3];
		m4Labels[6][0] = allLabels[2];
		m4Labels[6][1] = allLabels[1];
		m4Labels[7][0] = allLabels[4];
		m4Labels[8][0] = allLabels[5];
		m4Labels[9][0] = allLabels[1];
		// Komplexe abfragen labels
		m5Labels[0][0] = allLabels[12];
		m5Labels[0][1] = allLabels[7];
		m5Labels[1][0] = allLabels[0];
		m5Labels[1][1] = allLabels[7];
		m5Labels[2][0] = allLabels[2];
		m5Labels[2][1] = allLabels[8];
		m5Labels[3][0] = allLabels[10];
		m5Labels[4][0] = allLabels[3];
		m5Labels[4][1] = allLabels[8];
		m5Labels[4][2] = allLabels[9];
		m5Labels[5][0] = allLabels[0];
		m5Labels[5][1] = allLabels[12];
		m5Labels[6][0] = allLabels[5];
		m5Labels[6][1] = allLabels[8];
		m5Labels[6][2] = allLabels[9];
		m5Labels[7][0] = allLabels[5];
		m5Labels[7][1] = allLabels[8];
		m5Labels[8][0] = allLabels[8];
		m5Labels[8][1] = allLabels[13];
		m5Labels[9][0] = allLabels[11];
		m5Labels[9][1] = allLabels[12];
		m5Labels[9][2] = allLabels[7];
		m5Labels[10][0] = allLabels[3];
		m5Labels[10][1] = allLabels[2];
		m5Labels[10][2] = allLabels[5];
		m5Labels[11][0] = allLabels[12];
		m5Labels[11][1] = allLabels[5];
		m5Labels[11][2] = allLabels[7];
		m5Labels[12][0] = allLabels[4];
		m5Labels[12][1] = allLabels[8];
		m5Labels[12][2] = allLabels[13];
		m5Labels[13][0] = allLabels[8];
		m5Labels[13][1] = allLabels[6];
		m5Labels[14][0] = allLabels[8];
		m5Labels[14][1] = allLabels[9];

	}

	private void allLabels() {
		allLabels = new JLabel[14];
		for (int i = 0; i < allLabels.length; i++) {
			allLabels[i] = new JLabel();
		}
		allLabels[0].setText("Bitte die Matrikelnummer eingeben");
		allLabels[1].setText("Bitte das Semester eingeben");
		allLabels[2].setText("Bitte den Studiengang auswählen");
		allLabels[3].setText("Bitte den Vorlesungskürzel eingeben");
		allLabels[4].setText("Bitte die Fakultät eingeben");
		allLabels[5].setText("Bitte Nachname des Professors/Dozenten eingeben");
		allLabels[6].setText("Bitte Professor/Dozent Kürzel eingeben");
		allLabels[7].setText("Bitte Raumnummer eingeben");
		allLabels[8].setText("Bitte den Tag eingeben");
		allLabels[9].setText("Bitte den Slot eingeben");
		allLabels[10].setText("Bitte die Personalnummer eingeben");
		allLabels[11].setText("Bitte das Geschlecht angeben");
		allLabels[12].setText("Bitte den Veranstaltungsname eingeben");
		allLabels[13].setText("");
		for (int k = 0; k < allLabels.length; k++) {
			allLabels[k].setFont(new Font("Serif", Font.PLAIN, 18));
		}
	}

	private void clear() {
		jpCenter.removeAll();
		jpCenter.revalidate();
		jpCenter.repaint();

	}

}