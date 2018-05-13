import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.Box;
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

import db.DB;

public class GUI implements ActionListener {

	private JFrame jf;
	private JButton[] fusszeile = new JButton[2];
	private JButton abfrageSchicken;
	private JMenuBar menuBar;
	private JMenu menuT1;
	private JMenu menuT2;
	private JMenuItem m2Item1;
	private JMenuItem m2Item2;
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
	private JMenuItem mItemsM4[];
	private String einfacheAbfragen[];
	private JMenuItem mItemsM5[];
	private String komplexeAbfragen[];
	private JPanel jpCenter;
	private JPanel eA;
	private JPanel kA;
	private int einAnfparameterAnzahl[] = new int[10];
	private int kompAnfparameterAnzahl[] = new int[15];
	private JLabel allLabels[];
	private JCheckBox computerRaum;
	private int abfragenCounter;
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
	private JComboBox fakultätAuswahl;
	@SuppressWarnings("rawtypes")
	private JComboBox studiengangAuswahl;
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
	private boolean istMännlich;
	private String veranstaltungsname;
	private boolean istCompRaum;

	private int tabellenNummer = -1;
	// -----------------------------
	// DB bearbeiten variablen -----
	// TODO variablen db
	private int allgDB;
	private String pKListe[] = new String[8];
	private JLabel überschrift;
	private JButton tabellen[] = new JButton[8];
	JPanel innerCenter2;

	// ------------------------------

	public GUI() {
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
		fusszeile[1].addActionListener(this);
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
		mItem1 = new JMenuItem("Hinzufügen");
		mItem1.setFont(new Font("Serif", Font.PLAIN, 18));
		mItem1.addActionListener(this);
		mItem2 = new JMenuItem("Ändern");
		mItem2.setFont(new Font("Serif", Font.PLAIN, 18));
		mItem2.addActionListener(this);
		mItem3 = new JMenuItem("Löschen");
		mItem3.setFont(new Font("Serif", Font.PLAIN, 18));
		mItem3.addActionListener(this);
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
		}

		if (fusszeile[0] == quelle) {
			tabellenNummer = -1;
			fusszeile[1].setVisible(false);
			clear();
		}
		if (fusszeile[1] == quelle) {
			System.out.println(tabellenNummer);

		}
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
					abfragenCounter = j;

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
					abfragenCounter = j;
				}
			}
		}
		String sourceToString = ev.getSource().toString();
		if (sourceToString.contains("selectedItemReminder=Männlich")) {
				istMännlich=true;
		}
		if (sourceToString.contains("selectedItemReminder=Weiblich")) {
			istMännlich=false;
		}
		
		if (ev.getSource() == slotAuswahl) {
			if (slotAuswahl.getSelectedItem() == "1. Slot") {
				slot = 1;
			}
			if (slotAuswahl.getSelectedItem() == "2. Slot") {
				slot = 2;
			}
			if (slotAuswahl.getSelectedItem() == "3. Slot") {
				slot = 3;
			}
			if (slotAuswahl.getSelectedItem() == "4. Slot") {
				slot = 4;
			}
			if (slotAuswahl.getSelectedItem() == "5. Slot") {
				slot = 5;
			}
			if (slotAuswahl.getSelectedItem() == "6. Slot") {
				slot = 6;
			}
		}
		for(int i=0;i<7;i++){
			String testString = "selectedItemReminder=";
			testString=testString+i+". Semester";
			if(sourceToString.contains(testString)){
				semester=i;
			}
		}
		
		if (ev.getSource() == semesterAuswahl) {
			if (semesterAuswahl.getSelectedItem() == "1. Semester") {
				semester = 1;
			}
			if (semesterAuswahl.getSelectedItem() == "2. Semester") {
				semester = 2;
			}
			if (semesterAuswahl.getSelectedItem() == "3. Semester") {
				semester = 3;
			}
			if (semesterAuswahl.getSelectedItem() == "4. Semester") {
				semester = 4;
			}
			if (semesterAuswahl.getSelectedItem() == "5. Semester") {
				semester = 5;
			}
			if (semesterAuswahl.getSelectedItem() == "6. Semester") {
				semester = 6;
			}
			if (semesterAuswahl.getSelectedItem() == "7. Semester") {
				semester = 7;
			}
		}
		if (ev.getSource() == fakultätAuswahl) {
			fakultät = (String) fakultätAuswahl.getSelectedItem();
		}
		if (ev.getSource() == studiengangAuswahl) {
			studiengang = (String) studiengangAuswahl.getSelectedItem();
		}

		if (abfrageSchicken == quelle) {
			DB db = new DB("studierendenverwaltung", "root", "");
			JTextArea bspTextArea = new JTextArea();
			jpCenter.add(bspTextArea);
			ArrayList<LinkedHashMap<String, String>> daten;

			if (istEinfach) {
				boolean zahl;
				switch (einAnfparameterAnzahl[einAbNr]) {
				case 1:
					zahl = istPersOderMatNr(einAbNr, 0, true);
					if (!zahl) {
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
					case 4: // TODO sollte funktionieren
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
						zahl = istPersOderMatNr(einAbNr, 0, true);
						if (!zahl) {
							stringEingaben(einAbNr, 0, true);
						}
					} else {
						zahl = istPersOderMatNr(einAbNr, 1, true);
						if (!zahl) {
							stringEingaben(einAbNr, 1, true);
						}

						// ////////////////////
						// Abfragen
						// ////////////////////
						switch (einAbNr) {
						case 2: // Abfrage 1
							// Übergabeparameter
							db.abfrageEinfach03(istMännlich,
									studiengang);
							break;
						case 3:
							db.abfrageEinfach04(istMännlich,
									studiengang);
							break;
						case 5:
							db.abfrageEinfach06(semester, vorlesungsKrzl);
							break;
						case 6:
							db.abfrageEinfach07(
									studiengang,
									semester);

							break;
						default:
						}

					}
					break;
				default:
				}
			} else {
				boolean zahl;
				switch (kompAnfparameterAnzahl[kompAbNr]) {
				case 1:
					zahl = istPersOderMatNr(kompAbNr, 0, false);
					if (!zahl) {
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
						zahl = istPersOderMatNr(kompAbNr, 0, false);
						if (!zahl) {
							stringEingaben(kompAbNr, 0, false);
						}
					} else {
						zahl = istPersOderMatNr(kompAbNr, 1, false);
						if (!zahl) {
							stringEingaben(kompAbNr, 1, false);
						}
					}

					// ////////////////////
					// Abfragen
					// ////////////////////
					switch (kompAbNr) {
					case 0:
						db.abfrageKomplex01(veranstaltungsname, raumName);
						break;
					case 1:
						db.abfrageKomplex02(matrikelNr, raumName);
						break;
					case 2:
						db.abfrageKomplex03(studiengang, tag);
						break;
					case 5:
						db.abfrageKomplex06(veranstaltungsname, matrikelNr);
						break;
					case 7:
						db.abfrageKomplex08(profName, tag);
						break;
					case 8:
						db.abfrageKomplex09(istCompRaum, tag);
						break;
					case 13:
						db.abfrageKomplex14(profKrzl, tag);
						break;
					case 14:
						db.abfrageKomplex15(tag, slot);
						break;
					default:
					}

					break;
				case 3:
					if (m5Labels[kompAbNr][0] == allLabels[0]
							|| m5Labels[kompAbNr][0] == allLabels[10]) {
						zahl = istPersOderMatNr(kompAbNr, 0, false);
						if (!zahl) {
							stringEingaben(kompAbNr, 0, false);
						}
					} else {
						if (m5Labels[kompAbNr][1] == allLabels[0]
								|| m5Labels[kompAbNr][1] == allLabels[10]) {
							zahl = istPersOderMatNr(kompAbNr, 1, false);
							if (!zahl) {
								stringEingaben(kompAbNr, 1, false);
							}
						} else {
							zahl = istPersOderMatNr(kompAbNr, 2, false);
							if (!zahl) {
								stringEingaben(kompAbNr, 2, false);
							}
						}
						// ////////////////////
						// Abfragen
						// ////////////////////
						switch (kompAbNr) {
						case 4:
							db.abfrageKomplex05(vorlesungsKrzl, tag, slot);
							break;
						case 6:
							db.abfrageKomplex07(profName, slot, tag);
							break;
						case 9:
							db.abfrageKomplex10(istMännlich,
									veranstaltungsname, raumName);
							break;
						case 10:
							db.abfrageKomplex11(vorlesungsKrzl, studiengang,
									profName);
							break;
						case 11:
							db.abfrageKomplex12(veranstaltungsname, profName,
									raumName);
							break;
						case 12:
							db.abfrageKomplex13(tag, istCompRaum, fakultät);
							break;
						default:
						}

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
				fusszeile[1].setVisible(true);
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

	private void allgTab(int i) {
		innerCenter2 = new JPanel();
		innerCenter2.setLayout(new GridLayout(10, 1));
		if (allgDB == 2 || allgDB == 3) {
			clear();
			JLabel pK = new JLabel("Bitte " + pKListe[i] + " eingeben");
			pK.setFont(new Font("Serif", Font.PLAIN, 18));
			jpCenter.add(pK, BorderLayout.NORTH);
			JTextField iD = new JTextField();
			innerCenter2.add(iD);
			jpCenter.add(innerCenter2, BorderLayout.CENTER);
		}
		if (allgDB == 1) {
			switch (i) {
			case 0: // person
				clear();
				JLabel personenAttribute[] = new JLabel[4];
				JTextField personEingabe[] = new JTextField[3];
				for (int j = 0; j < personenAttribute.length; j++) {
					personenAttribute[j] = new JLabel();
					personenAttribute[j].setFont(new Font("Serif", Font.PLAIN,
							18));
				}
				personEingabe[0] = new JTextField();
				personEingabe[1] = new JTextField();
				personEingabe[2] = new JTextField();
				JCheckBox istPMännlich = new JCheckBox();
				istPMännlich.setText("Männlich");
				personenAttribute[0].setText("Bitte Vorname eingeben.");
				personenAttribute[1].setText("Bitte Nachname eingeben.");
				personenAttribute[2].setText("Bitte Geburtsdatum auswählen.");
				personenAttribute[3].setText("Wenn männlich, bitte ankreuzen.");
				for (int k = 0; k < personEingabe.length; k++) {
					innerCenter2.add(personenAttribute[k]);
					innerCenter2.add(personEingabe[k]);
				}
				innerCenter2.add(personenAttribute[3]);
				innerCenter2.add(istPMännlich);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 1: // dozent
				clear();
				JLabel profAttribute[] = new JLabel[3];
				JTextField profEingabe[] = new JTextField[3];
				for (int m = 0; m < profAttribute.length; m++) {
					profAttribute[m] = new JLabel();
					profEingabe[m] = new JTextField();
					profAttribute[m].setFont(new Font("Serif", Font.PLAIN, 18));
				}
				profAttribute[0]
						.setText("Bitte Professor/Dozent-Kürzel eingeben.");
				profAttribute[1].setText("Bitte Fakultät-ID eingeben.");
				profAttribute[2].setText("Bitte Person-ID eingeben");
				for (int n = 0; n < profAttribute.length; n++) {
					innerCenter2.add(profAttribute[n]);
					innerCenter2.add(profEingabe[n]);
				}
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 2:// student
				clear();
				JLabel studAttribute[] = new JLabel[3];
				JTextField studEingabe[] = new JTextField[3];
				for (int m = 0; m < studAttribute.length; m++) {
					studAttribute[m] = new JLabel();
					studEingabe[m] = new JTextField();
					studAttribute[m].setFont(new Font("Serif", Font.PLAIN, 18));
				}
				// TODO semester dropdown
				studAttribute[0].setText("Bitte Semester eingeben.");
				studAttribute[1].setText("Bitte Studiengang-ID eingeben.");
				studAttribute[2].setText("Bitte Person-ID eingeben");
				for (int n = 0; n < studAttribute.length; n++) {
					innerCenter2.add(studAttribute[n]);
					innerCenter2.add(studEingabe[n]);
				}
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 3:// fakultät
				clear();
				JLabel fakAttribut = new JLabel();
				fakAttribut.setFont(new Font("Serif", Font.PLAIN, 18));
				JTextField fakEingabe = new JTextField();
				fakAttribut.setText("Bitte Fakultät-Name eingeben.");
				innerCenter2.add(fakAttribut);
				innerCenter2.add(fakEingabe);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 4:// Studiengang
				clear();
				JLabel studiengangAttribut = new JLabel();
				studiengangAttribut.setFont(new Font("Serif", Font.PLAIN, 18));
				JTextField studiengangEingabe = new JTextField();
				studiengangAttribut.setText("Bitte Studiengang-Name eingeben.");
				innerCenter2.add(studiengangAttribut);
				innerCenter2.add(studiengangEingabe);
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;
			case 5:// Veranstaltung
				clear();
				JLabel veranstaltungAttribute[] = new JLabel[5];
				JTextField veranstaltungEingabe[] = new JTextField[5];
				for (int j = 0; j < veranstaltungAttribute.length; j++) {
					veranstaltungAttribute[j] = new JLabel();
					veranstaltungAttribute[j].setFont(new Font("Serif",
							Font.PLAIN, 18));
					veranstaltungEingabe[j] = new JTextField();
				}
				// TODO semester dropdown
				veranstaltungAttribute[0].setText("Bitte Semester auswählen.");
				veranstaltungAttribute[1]
						.setText("Bitte Vorlesungsdauer eingeben (in Minuten).");
				veranstaltungAttribute[2]
						.setText("Bitte Personal-Nr. eingeben.");
				veranstaltungAttribute[3]
						.setText("Bitte Studenplan-ID eingeben.");
				veranstaltungAttribute[4]
						.setText("Bitte Vorlesungsnamen-ID eingeben.");
				for (int h = 0; h < veranstaltungAttribute.length; h++) {
					innerCenter2.add(veranstaltungAttribute[h]);
					innerCenter2.add(veranstaltungEingabe[h]);
				}
				jpCenter.add(innerCenter2, BorderLayout.CENTER);
				break;

			case 6: // veranstaltungsname
				clear();
				JLabel vNameAttribute[] = new JLabel[2];
				JTextField vNameEingabe[] = new JTextField[2];
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
				clear();
				JLabel raumName = new JLabel("Bitte Raum-Bezeichnung eingeben.");
				JLabel raumIstPc = new JLabel("Wenn PC-Raum, bitte ankreuzen.");
				raumName.setFont(new Font("Serif", Font.PLAIN, 18));
				raumIstPc.setFont(new Font("Serif", Font.PLAIN, 18));
				JCheckBox istPcRaum = new JCheckBox();
				istPcRaum.setText("PcRaum");
				JTextField raumEingabe = new JTextField();
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

	private void veranstaltungNameTab() {
		// TODO Auto-generated method stub

	}

	private void veranstaltungTab() {
		// TODO Auto-generated method stub

	}

	private void studiengangTab() {
		// TODO Auto-generated method stub

	}

	private void fakultätTab() {
		// TODO Auto-generated method stub

	}

	private void studentTab() {
		// TODO Auto-generated method stub

	}

	private void dozentTab() {
		// TODO Auto-generated method stub

	}

	private void personTab() {

	}

	
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
		} else {
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

	private boolean istPersOderMatNr(int AbNr, int i, boolean istEinfach) {
		boolean ist0oder10 = false;
		if (istEinfach) {
			if (m4Labels[AbNr][i] == allLabels[0]
					|| m4Labels[AbNr][i] == allLabels[10]) {
				ist0oder10 = true;
			}
		} else {
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
						"Eingabe muss eine 9-stellige Zahl sein",
						"Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
				if (istEinfach) {
					m4Parameter[AbNr][0].setText("");
				} else {
					m5Parameter[AbNr][0].setText("");
				}

			} else {
				if (helpString.length() != 7) {
					JOptionPane
							.showMessageDialog(
									jf,
									"Ihre eingegebene Zahl war nicht 9-stellig",
									"Zahl nicht 9-stellig",
									JOptionPane.WARNING_MESSAGE);
					if (istEinfach) {
						m4Parameter[AbNr][0].setText("");
					} else {
						m5Parameter[AbNr][0].setText("");
					}
				} else {
					if (AbNr == 0) {
						matrikelNr = helpInt;
					} else {
						persoNr = helpInt;
					}

				}
				return true;
			}
		}
		return false;
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
		String slotListe[] = { "1. Slot", "2. Slot", "3. Slot", "4. Slot", "5. Slot", "6. Slot" };
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
		String tageListe[] = { "Montag", "Dienstag", "Mittwoch", "Donnerstag",
				"Freitag" };
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
		String semesterListe[] = { "1. Semester", "2. Semester", "3. Semester", "4. Semester", "5. Semester", "6. Semester", "7. Semester" };
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
		String fakultätenListe[] = { "Biotechnologie", "Elektrotechnik",
				"Gestaltung", "Informatik", "Informationstechnik",
				"Maschinenbau", "Sozialwesen", "Verfahrens- und Chemietechnik",
				"Wirtschaftsingenieurwesen" };
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
		String studiengängeListe[] = {
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
				"Translation Studies for IT (Bachelor)",
				"Informatik (Bachelor)", "Informatik (Master)",
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
				"Technische Informatik (Bachelor)",
				"Soziale Arbeit (Bachelor)", "Soziale Arbeit (Master)",
				"Mechatronik - fakultätsübergreifend E,I,M,N (Bachelor)",
				"Chemical Engineering French (Master)",
				"Chemieingenieurwesen (Master)",
				"Chemische Technik (Bachelor)", "Verfahrenstechnik (Bachelor)",
				"Wirtschaftsingenieurwesen (Bachelor)",
				"Wirtschaftsingenieurwesen - Vorqualifikation BWL (Master)",
				"Wirtschaftsingenieurwesen - Vorqualifikation ING (Master)",
				"Wirtschaftsingenieurwesen - Vorqualifikation WI (Master)",
				"Wirtschaftsingenieurwesen International (Bachelor)" };
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

	// TODO dbs
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
		m5Labels[0][0] = allLabels[3];
		m5Labels[0][1] = allLabels[7];
		m5Labels[1][0] = allLabels[0];
		m5Labels[1][1] = allLabels[8];
		m5Labels[2][0] = allLabels[2];
		m5Labels[2][1] = allLabels[8];
		m5Labels[3][0] = allLabels[10];
		m5Labels[4][0] = allLabels[3];
		m5Labels[4][1] = allLabels[8];
		m5Labels[4][2] = allLabels[9];
		m5Labels[5][0] = allLabels[0];
		m5Labels[5][1] = allLabels[3];
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

	public int getMatrikelNr() {
		return matrikelNr;
	}

	public int getSemester() {
		return semester;
	}

	public String getStudiengang() {
		return studiengang;
	}

	public String getVorlesungsKrzl() {
		return vorlesungsKrzl;
	}

	public String getFakultät() {
		return fakultät;
	}

	public String getProfName() {
		return profName;
	}

	public String getProfKrzl() {
		return profKrzl;
	}

	public String getRaumName() {
		return raumName;
	}

	public String getTag() {
		return tag;
	}

	public int getSlot() {
		return slot;
	}

	public int getPersoNr() {
		return persoNr;
	}

	public boolean isMännlich() {
		return istMännlich;
	}

	public String getVeranstaltungsname() {
		return veranstaltungsname;
	}

	public boolean isCompRaum() {
		return istCompRaum;
	}

}