import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI implements ActionListener {

	private JFrame jf;
	private JButton[] fusszeile = new JButton[2];
	private JMenuBar menuBar;
	private JMenu menuT1;
	private JMenu menuT2;
	private JMenu menuT5;
	private JMenu subMenu1;
	private JMenuItem mItem1;
	private JMenuItem mItem2;
	private JMenuItem mItem3;
	private JMenuItem mItem4;
	private JMenuItem mItem5;
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
	private JLabel	 allLabels[] ;
	private JCheckBox computerRaum ;
	private int abfragenCounter;
	private JPanel innerCenter;

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
		fusszeile[1] = new JButton("...");
		menuBar = new JMenuBar();

		menuT1 = new JMenu("DatenBank bearbeiten");
		menuT2 = new JMenu("Abfragen");
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
		subMenuDB();
		subMenuAbfragen();

		jf.setContentPane(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		jf.setSize(960, 720);
	}

	private void subMenuAbfragen() {
		mItem1 = new JMenuItem("Einfache Abfragen");
		mItem1.addActionListener(this);
		mItem2 = new JMenuItem("Komplexe Abfragen");
		mItem2.addActionListener(this);
		menuT2.add(mItem1);
		menuT2.add(mItem2);
	}

	private void subMenuDB() {
		mItem3 = new JMenuItem("Hinzufügen");
		mItem3.addActionListener(this);
		mItem4 = new JMenuItem("Ändern");
		mItem4.addActionListener(this);
		mItem5 = new JMenuItem("Löschen");
		mItem5.addActionListener(this);
		menuT1.add(mItem3);
		menuT1.add(mItem4);
		menuT1.add(mItem5);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object quelle = ev.getSource();
		if (menuT1 == quelle) {

			menuT1();
		}
		if (menuT2 == quelle) {
			menuT4();
		}
		if (mItem1 == quelle) {
			menuT4();
		}
		if (mItem2 == quelle) {
			menuT5();
		}

		if (fusszeile[0] == quelle) {
			clear();
		}
		if (m4Buttons != null) {
			for (int j = 0; j < m4Buttons.length; j++) {
				if (m4Buttons[j] == quelle) {
					einAbf(j);
					switch(einAnfparameterAnzahl[j]){
					case 1:
						innerCenter.add(m4Labels[j][0]);
						innerCenter.add(m4Parameter[j][0]);
						break;
					case 2:
						innerCenter.add(m4Labels[j][0]);
						innerCenter.add(m4Parameter[j][0]);
						
						innerCenter.add(m4Labels[j][1]);
						innerCenter.add(m4Parameter[j][1]);
						break;
					default:
					}
					abfragenCounter=j;
				}
			}
		}
		if (m5Buttons != null) {
			for (int j = 0; j < m5Buttons.length; j++) {
				if (m5Buttons[j] == quelle) {
					komAbf(j);
					switch(kompAnfparameterAnzahl[j]){
					case 1:
						innerCenter.add(m5Labels[j][0]);
						if(m5Labels[j][0].getText()==""){
							innerCenter.remove(m5Labels[j][0]);
							innerCenter.remove(m5Parameter[j][0]);
							computerRaum= new JCheckBox("Ist Computerraum");
							innerCenter.add(computerRaum);
						}
						innerCenter.add(m5Parameter[j][0]);
						break;
					case 2:
						innerCenter.add(m5Labels[j][0]);
						innerCenter.add(m5Parameter[j][0]);
						if(m5Labels[j][0].getText()==""){
							innerCenter.remove(m5Labels[j][0]);
							innerCenter.remove(m5Parameter[j][0]);
							computerRaum= new JCheckBox("Ist Computerraum");
							innerCenter.add(computerRaum);
						}
						
						innerCenter.add(m5Labels[j][1]);
						innerCenter.add(m5Parameter[j][1]);
						if(m5Labels[j][1].getText()==""){
							innerCenter.remove(m5Labels[j][1]);
							innerCenter.remove(m5Parameter[j][1]);
							computerRaum= new JCheckBox("Ist Computerraum");
							innerCenter.add(computerRaum);
						}
						break;
					case 3:
						innerCenter.add(m5Labels[j][0]);
						innerCenter.add(m5Parameter[j][0]);
						if(m5Labels[j][0].getText()==""){
							innerCenter.remove(m5Labels[j][0]);
							innerCenter.remove(m5Parameter[j][0]);
							computerRaum= new JCheckBox("Ist Computerraum");
							innerCenter.add(computerRaum);
						}
						innerCenter.add(m5Labels[j][1]);
						innerCenter.add(m5Parameter[j][1]);
						if(m5Labels[j][1].getText()==""){
							innerCenter.remove(m5Labels[j][1]);
							innerCenter.remove(m5Parameter[j][1]);
							computerRaum= new JCheckBox("Ist Computerraum");
							innerCenter.add(computerRaum);
						}
						innerCenter.add(m5Labels[j][2]);
						innerCenter.add(m5Parameter[j][2]);
						if(m5Labels[j][2].getText()==""){
							innerCenter.remove(m5Labels[j][2]);
							innerCenter.remove(m5Parameter[j][2]);
							computerRaum= new JCheckBox("Ist Computerraum");
							innerCenter.add(computerRaum);
						}
						break;
					default:
					}
					abfragenCounter=j;
				}
			}
		}

	}



	private void einAbf(int i) {
		clear();
		JTextArea abfrage = new JTextArea();
		abfrage.setText(m4Text[i].getText());
		abfrage.setFont(new Font("Serif", Font.PLAIN, 18));
		abfrage.setLineWrap(true);
		abfrage.setWrapStyleWord(true);
		abfrage.setEditable(false);
		innerCenter = new JPanel();
		jpCenter.add(innerCenter, BorderLayout.CENTER);
		innerCenter.setLayout(new GridLayout(15,1));
		jpCenter.add(abfrage, BorderLayout.NORTH);
		jf.setSize(960, 720);
	}

	private void komAbf(int i) {
		clear();
		JTextArea abfrage = new JTextArea();
		abfrage.setText(m5Text[i].getText());
		abfrage.setFont(new Font("Serif", Font.PLAIN, 18));
		abfrage.setLineWrap(true);
		abfrage.setWrapStyleWord(true);
		abfrage.setEditable(false);
		innerCenter = new JPanel();
		jpCenter.add(innerCenter, BorderLayout.CENTER);
		innerCenter.setLayout(new GridLayout(15,1));
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
		kompAnfparameterAnzahl[8] = 3;
		kompAnfparameterAnzahl[9] = 2;
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
		m5Text[0].setText(
				"Zeige den Dozenten, der die Veranstaltung |_____| im Raum |_____| hält.	");
		m5Text[1].setText("Zeige alle Veranstaltungen von dem Studierenden mit der MatrikelNr |_____| , die im Raum |_____| stattfinden.");
		m5Text[2].setText("Zeige alle Veranstaltungen der Studierenden vom Studiengang |_____|, die am |_____|(Tag) stattfinden.");
		m5Text[3].setText("Zeige die Bezeichnung aller Räume in denen Veranstaltungen vom Dozenten mit der Personalnummer |_____| stattfinden.");
		m5Text[4].setText("Zeige den Namen des Dozenten, der die Veranstaltung mit dem Veranstaltungskuerzel |_____| am |_____|(Tag) im Slot |_____| hält.");
		m5Text[5].setText("Zeige den Stundenplan des Dozenten, den der Student mit der MatrikelNr |_____| in der Veranstaltung |_____| hat.");
		m5Text[6].setText("Zeige den Raum, in der die Veranstaltung vom Dozenten/Professor |_____| am |_____|(Tag) im Slot |_____| gehalten wird.");
		m5Text[7].setText("Zeige alle Räume, die durch den Dozent/Professor |_____| am |_____|(Tag) belegt sind.");
		m5Text[8].setText("Zeige alle Studierenden, die am |_____|(Tag) eine Veranstaltung in einem Computerraum haben/nicht haben.");
		m5Text[9].setText("Zeige alle |_____| Studierenden, die die Veranstaltung |_____| im Raum |_____| besuchen.");
		m5Text[10].setText("Zeige die Tage an denen die Veranstaltung mit dem Veranstaltungskuerzel |_____|, die im Studiengang |_____| vom Dozenten/Professor |_____| gehalten wird, stattfindet.");
		m5Text[11].setText("Zeige die Matrikelnummer aller Studierenden, die die Veranstaltung |_____| beim Dozenten/Professor |_____| im Raum |_____| besuchen.");
		m5Text[12].setText("Zeige alle Dozenten der Fakultät |_____|, die eine Veranstaltung am |_____| halten und einen Computerraum belegen/nicht belegen.");
		m5Text[13].setText("Zeige alle Vorlesungen, die am |_____|(Tag) von dem Dozenten/Professor mit dem Kuerzel |_____| gehalten werden.");
		m5Text[14].setText("Zeige alle Dozenten, die am |_____|(Tag) im Slot |_____| eine Veranstaltung halten.");
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
		m4Text[0].setText("Zeige den Studiereden mit der Matrikelnummer |_____|.");
		m4Text[1].setText("Zeige alle Studierenden aus dem |_____| Semester.");
		m4Text[2].setText("Zeige alle männlichen Studierenden aus dem Studiengang |_____|.");
		m4Text[3].setText("Zeige alle weiblichen Studierenden aus dem Studiengang |_____|.");
		m4Text[4].setText("Zeige alle Studierenden, die die Vorlesung mit dem Kürzel |_____| besuchen.");
		m4Text[5].setText(
				"Zeige alle Studierenden aus dem |_____| Semester, die die Vorlesung mit dem Kürzel |_____| besuchen.");
		m4Text[6].setText("Zeige den Studenplan der Studierenden vom Studiengang |_____| im |_____| Semester.");
		m4Text[7].setText("Zeige alle Dozenten der Fakultät |_____|.");
		m4Text[8].setText("Zeige alle Studierenden mit dem Dozenten Prof. |_____|.");
		m4Text[9].setText("Zeige alle Studierenden die eine Veranstaltung aus dem |_____| Semester besuchen.");
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
	private void giveRightLabels(){
		m4Labels=new JLabel[10][2];
		m4Parameter=new JTextField[10][2];
		for(int i=0;i<m4Parameter.length;i++){
			m4Parameter[i][0]=new JTextField(30);
			m4Parameter[i][1]=new JTextField(30);
		}
		m5Labels=new JLabel[15][3];
		m5Parameter=new JTextField[15][3];
		for(int i=0;i<m5Parameter.length;i++){
			m5Parameter[i][0]=new JTextField(30);
			m5Parameter[i][1]=new JTextField(30);
			m5Parameter[i][2]=new JTextField(30);
		}
		m4Labels[0][0]=allLabels[0];
		m4Labels[1][0]=allLabels[1];
		m4Labels[2][0]=allLabels[2];
		m4Labels[3][0]=allLabels[2];
		m4Labels[4][0]=allLabels[3];
		m4Labels[5][0]=allLabels[1];
		m4Labels[5][1]=allLabels[3];
		m4Labels[6][0]=allLabels[2];
		m4Labels[6][1]=allLabels[1];
		m4Labels[7][0]=allLabels[4];
		m4Labels[8][0]=allLabels[5];
		m4Labels[9][0]=allLabels[1];
		
		m5Labels[0][0]=allLabels[3];
		m5Labels[0][1]=allLabels[7];
		m5Labels[1][0]=allLabels[0];
		m5Labels[1][1]=allLabels[8];
		m5Labels[2][0]=allLabels[2];
		m5Labels[2][1]=allLabels[8];
		m5Labels[3][0]=allLabels[10];
		m5Labels[4][0]=allLabels[3];
		m5Labels[4][1]=allLabels[8];
		m5Labels[4][2]=allLabels[9];
		m5Labels[5][0]=allLabels[0];
		m5Labels[5][1]=allLabels[3];
		m5Labels[6][0]=allLabels[5];
		m5Labels[6][1]=allLabels[8];
		m5Labels[6][2]=allLabels[9];
		m5Labels[7][0]=allLabels[5];
		m5Labels[7][1]=allLabels[8];
		m5Labels[8][0]=allLabels[8];
		m5Labels[8][1]=allLabels[13];
		m5Labels[9][0]=allLabels[11];
		m5Labels[9][1]=allLabels[12];
		m5Labels[9][2]=allLabels[7];
		m5Labels[10][0]=allLabels[3];
		m5Labels[10][1]=allLabels[2];
		m5Labels[10][2]=allLabels[5];
		m5Labels[11][0]=allLabels[12];
		m5Labels[11][1]=allLabels[5];
		m5Labels[11][2]=allLabels[7];
		m5Labels[12][0]=allLabels[4];
		m5Labels[12][1]=allLabels[8];
		m5Labels[12][2]=allLabels[13];
		m5Labels[13][0]=allLabels[8];
		m5Labels[13][1]=allLabels[6];
		m5Labels[14][0]=allLabels[8];
		m5Labels[14][1]=allLabels[9];
		
	}

	private void allLabels() {
		allLabels=new JLabel[14];
		for(int i=0;i<allLabels.length;i++){
			allLabels[i]=new JLabel();
		}
		allLabels[0].setText("Bitte die Matrikelnummer eingeben");
		allLabels[1].setText("Bitte das Semester eingeben");
		allLabels[2].setText("Bitte den Studiengang eingeben");
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
		for(int k=0;k<allLabels.length;k++){
			allLabels[k].setFont(new Font("Serif", Font.PLAIN, 18));
		}
	}

	private void menuT1() {
		clear();
		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel panel1 = new JPanel();

		tabbedPane.addTab("Tab 1", panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		jpCenter.add(tabbedPane, BorderLayout.NORTH);
	};

	private void clear() {
		jpCenter.removeAll();
		jpCenter.revalidate();
		jpCenter.repaint();

	}

}