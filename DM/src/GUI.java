import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
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
	private JTextArea m4Parameter[];
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
	private Object allLabelsAndButtons[][] = new Object[25][6];
	// /InnerCenter Abfragen///
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

	/*
	 * private void subMenuEA() { einfacheAbfragen = new String[10]; mItemsM4 =
	 * new JMenuItem[10]; einfacheAbfragen[1] = "test1"; einfacheAbfragen[2] =
	 * "test2"; einfacheAbfragen[3] = "test3"; einfacheAbfragen[4] = "test4";
	 * einfacheAbfragen[5] = "test5"; einfacheAbfragen[6] = "test6";
	 * einfacheAbfragen[7] = "test7"; einfacheAbfragen[8] = "test8";
	 * einfacheAbfragen[9] = "test9"; einfacheAbfragen[0] = "test0"; for (int i
	 * = 0; i < mItemsM4.length; i++) { mItemsM4[i] = new
	 * JMenuItem(einfacheAbfragen[i]); mItemsM4[i].addActionListener(this);
	 * menuT2.add(mItemsM4[i]); } }
	 * 
	 * private void subMenuKA() { komplexeAbfragen = new String[15]; mItemsM5 =
	 * new JMenuItem[15]; komplexeAbfragen[0] = "test0"; komplexeAbfragen[1] =
	 * "test1"; komplexeAbfragen[2] = "test2"; komplexeAbfragen[3] = "test3";
	 * komplexeAbfragen[4] = "test4"; komplexeAbfragen[5] = "test5";
	 * komplexeAbfragen[6] = "test6"; komplexeAbfragen[7] = "test7";
	 * komplexeAbfragen[8] = "test8"; komplexeAbfragen[9] = "test9";
	 * komplexeAbfragen[10] = "test10"; komplexeAbfragen[11] = "test11";
	 * komplexeAbfragen[12] = "test12"; komplexeAbfragen[13] = "test13";
	 * komplexeAbfragen[14] = "test14"; for (int i = 0; i < mItemsM5.length;
	 * i++) { mItemsM5[i] = new JMenuItem(komplexeAbfragen[i]);
	 * mItemsM5[i].addActionListener(this); menuT5.add(mItemsM5[i]); } }
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		Object quelle = ev.getSource();
		if (menuT1 == quelle) {

			menuT1();
		}
		if (menuT2 == quelle) {
			menuT2();
		}
		if (mItem1 == quelle) {
			menuT2();
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
				}
			}
		}
		if (m5Buttons != null) {
			for (int j = 0; j < m5Buttons.length; j++) {
				if (m5Buttons[j] == quelle) {
					komAbf(j);
				}
			}
		}

	}

	private void giveRightLnB(boolean einfach, int abfrage) {
		if (einfach == true) {
			// add einfache abfrage buttons und labels
		} else {
			// add komplexe buttons und labels
		}
	}

	private void allLnB() {
		for (int i = 0; i < allLabelsAndButtons.length; i++) {
			if (i < 10) {
				if (einAnfparameterAnzahl[i] == 1) {
					allLabelsAndButtons[i][0] = "hier richtiger JLabel";
					allLabelsAndButtons[i][1] = "hier richtiges JTextArea";
				}
				if (einAnfparameterAnzahl[i] == 2) {
					allLabelsAndButtons[i][0] = "hier richtiger JLabel";
					allLabelsAndButtons[i][1] = "hier richtiges JTextArea";
					allLabelsAndButtons[i][2] = "hier richtiger JLabel";
					allLabelsAndButtons[i][3] = "hier richtiges JTextArea";
				}
			} else {
				if (kompAnfparameterAnzahl[i - 10] == 1) {
					allLabelsAndButtons[i][0] = "hier richtiger JLabel";
					allLabelsAndButtons[i][1] = "hier richtiges JTextArea";
				}
				if (kompAnfparameterAnzahl[i - 10] == 2) {
					allLabelsAndButtons[i][0] = "hier richtiger JLabel";
					allLabelsAndButtons[i][1] = "hier richtiges JTextArea";
					allLabelsAndButtons[i][2] = "hier richtiger JLabel";
					allLabelsAndButtons[i][3] = "hier richtiges JTextArea";
				}
				if (kompAnfparameterAnzahl[i - 10] == 3) {
					allLabelsAndButtons[i][0] = "hier richtiger JLabel";
					allLabelsAndButtons[i][1] = "hier richtiges JTextArea";
					allLabelsAndButtons[i][2] = "hier richtiger JLabel";
					allLabelsAndButtons[i][3] = "hier richtiges JTextArea";
					allLabelsAndButtons[i][4] = "hier richtiger JLabel";
					allLabelsAndButtons[i][5] = "hier richtiges JTextArea";
				}
			}
		}
	}

	private void einAbf(int i) {
		clear();
		JTextArea abfrage = new JTextArea();
		abfrage.setText(m4Text[i].getText());
		abfrage.setFont(new Font("Serif", Font.ITALIC, 18));
		abfrage.setLineWrap(true);
		abfrage.setWrapStyleWord(true);
		abfrage.setEditable(false);
		innerCenter = new JPanel();
		jpCenter.add(innerCenter, BorderLayout.CENTER);
		innerCenter.setLayout(new GridLayout(2, 10));
		allLnB();
		jpCenter.add(abfrage, BorderLayout.NORTH);
		jf.setSize(960, 720);
	}

	private void komAbf(int i) {
		clear();
		JTextArea abfrage = new JTextArea();
		JTextField test = new JTextField("test", 10);

		abfrage.setText(m5Text[i].getText());
		abfrage.setFont(new Font("Serif", Font.ITALIC, 18));
		abfrage.setLineWrap(true);
		abfrage.setWrapStyleWord(true);
		abfrage.setEditable(false);
		jpCenter.add(abfrage, BorderLayout.NORTH);
		jpCenter.add(test, BorderLayout.CENTER);

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
			m5Text[i].setFont(new Font("Serif", Font.ITALIC, 18));
			m5Text[i].setLineWrap(true);
			m5Text[i].setWrapStyleWord(true);
		}
		// TODO text schreiben
		m5Text[0]
				.setText("testtesttesttesttesttesttesttesttesttesttesttesaaaaaaaaaaaaaaaaaaaaaattesttesttesttesttes\nasdasdasd	");
		m5Text[1].setText("test0");
		m5Text[2].setText("test0");
		m5Text[3].setText("test0");
		m5Text[4].setText("test0");
		m5Text[5].setText("test0");
		m5Text[6].setText("test0");
		m5Text[7].setText("test0");
		m5Text[8].setText("test0");
		m5Text[9].setText("test0");
		m5Text[10].setText("test0");
		m5Text[11].setText("test0");
		m5Text[12].setText("test0");
		m5Text[13].setText("test0");
		m5Text[14].setText("test0");
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
	private void menuT2() {
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
		m4Parameter = new JTextArea[10];
		for (int i = 0; i < m4Text.length; i++) {
			m4Text[i] = new JTextArea();
			m4Text[i].setEditable(false);
			m4Text[i].setFont(new Font("Serif", Font.ITALIC, 18));
			m4Text[i].setLineWrap(true);
			m4Text[i].setWrapStyleWord(true);
		}
		m4Text[0]
				.setText("Zeige den Studiereden mit der Matrikelnummer |_____|.");
		m4Text[1].setText("Zeige alle Studierenden aus dem |_____| Semester.");
		m4Text[2]
				.setText("Zeige alle männlichen Studierenden aus dem Studiengang |_____|.");
		m4Text[3]
				.setText("Zeige alle weiblichen Studierenden aus dem Studiengang |_____|.");
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
		// /parameter

		for (int i = 0; i < m4Buttons.length; i++) {
			int c = i + 1;
			m4Buttons[i] = new JButton("Abfrage " + c);
			m4Buttons[i].addActionListener(this);
			eA.add(m4Buttons[i]);
			eA.add(m4Text[i]);
		}
		jf.setSize(960, 720);

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