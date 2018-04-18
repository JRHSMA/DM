import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GUI implements ActionListener {

	private JFrame jf;
	private JButton[] menu = new JButton[5];
	private JButton[] fusszeile = new JButton[2];
	private JMenuBar menuBar;
	private JMenu menuT1;
	private JMenu menuT2;
	private JMenu menuT3;
	private JMenu menuT4;
	private JMenu menuT5;
	private JMenu subMenu1;
	private JMenuItem mi1M1;
	private JMenuItem mi2M1;
	private JMenuItem mItemsM4[];
	private String einfacheAbfragen[];
	private JMenuItem mItemsM5[];
	private String komplexeAbfragen[];

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
		fusszeile[0] = new JButton("Abbrechen");
		fusszeile[1] = new JButton("...");
			menuBar = new JMenuBar();

			menuT1 = new JMenu("Ändern");
			menuT2 = new JMenu("Hinzufügen");
			menuT3 = new JMenu("Löschen");
			menuT4 = new JMenu("Einfache Abfrage");
			menuT5 = new JMenu("Komplexe Abfrage");
			menuT1.setMnemonic(KeyEvent.VK_A);
			menuT2.setMnemonic(KeyEvent.VK_A);
			menuT3.setMnemonic(KeyEvent.VK_A);
			menuT4.setMnemonic(KeyEvent.VK_A);
			menuT5.setMnemonic(KeyEvent.VK_A);
			menuT1.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
			

			mi1M1 = new JMenuItem("An item in the submenu");
			mi1M1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
			menuT1.add(mi1M1);
			mi2M1 = new JMenuItem("Another item");
			menuT1.add(mi2M1);
			einfacheAbfragen();
			komplexeAbfragen();

			menuBar.add(menuT1);
			menuBar.add(menuT2);
			menuBar.add(menuT3);
			menuBar.add(menuT4);
			menuBar.add(menuT5);
		

		JPanel jp2 = new JPanel();
		jp.add(jp2, BorderLayout.SOUTH);
		jp.add(menuBar, BorderLayout.NORTH);
		for (int i = 0; i < fusszeile.length; i++) {
			jp2.add(fusszeile[i]);
		}

		jf.setContentPane(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		jf.setSize(960, 720);
	}

	private void einfacheAbfragen() {
		einfacheAbfragen=new String[10];
		mItemsM4 = new JMenuItem[10];
		einfacheAbfragen[1]="test1";
		einfacheAbfragen[2]="test2";
		einfacheAbfragen[3]="test3";
		einfacheAbfragen[4]="test4";
		einfacheAbfragen[5]="test5";
		einfacheAbfragen[6]="test6";
		einfacheAbfragen[7]="test7";
		einfacheAbfragen[8]="test8";
		einfacheAbfragen[9]="test9";
		einfacheAbfragen[0]="test0";
		for(int i=0;i<mItemsM4.length;i++){
			mItemsM4[i]=new JMenuItem(einfacheAbfragen[i] );
			mItemsM4[i].addActionListener(this);
			menuT4.add(mItemsM4[i]);
		}		
	}
	private void komplexeAbfragen() {
		komplexeAbfragen=new String[15];
		mItemsM5 = new JMenuItem[15];
		komplexeAbfragen[0]="test0";
		komplexeAbfragen[1]="test1";
		komplexeAbfragen[2]="test2";
		komplexeAbfragen[3]="test3";
		komplexeAbfragen[4]="test4";
		komplexeAbfragen[5]="test5";
		komplexeAbfragen[6]="test6";
		komplexeAbfragen[7]="test7";
		komplexeAbfragen[8]="test8";
		komplexeAbfragen[9]="test9";
		komplexeAbfragen[10]="test10";
		komplexeAbfragen[11]="test11";
		komplexeAbfragen[12]="test12";
		komplexeAbfragen[13]="test13";
		komplexeAbfragen[14]="test14";
		for(int i=0;i<mItemsM5.length;i++){
			mItemsM5[i]=new JMenuItem(komplexeAbfragen[i] );
			menuT5.add(mItemsM5[i]);
		}		
	}


	@Override
	public void actionPerformed(ActionEvent ev) {
		Object quelle = ev.getSource();
		if(mItemsM4[1]==quelle){
			einfacheAbfrageNr1();
			
		}


	}

	private void einfacheAbfrageNr1() {
		String eingabe = JOptionPane.showInputDialog(null,"Geben Sie Ihren Namen ein",
                "Eine Eingabeaufforderung",
                JOptionPane.PLAIN_MESSAGE);
		
	}
}