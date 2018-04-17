import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI{
	
	private JFrame jf;
	private JButton[] menu = new JButton[5];
	private JButton [] fusszeile = new JButton[2];
	
	
	public void LayoutGUI(String titel){
		jf=new JFrame(titel);
		JPanel jp=new JPanel();
		jp.setLayout(new BorderLayout());
		JPanel jp1=new JPanel();
		jp1.setLayout(new FlowLayout());
		jp.add(jp1, BorderLayout.NORTH);
		basisLayout();
		for(int i = 0; i<menu.length; i++){
			jp1.add(menu[i]);
		}
		
		JPanel jp2=new JPanel();
		jp.add(jp2, BorderLayout.SOUTH);
		for(int i = 0; i<fusszeile.length; i++){
			jp2.add(fusszeile[i]);
		}
		
		jf.setContentPane(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack(); jf.setVisible(true);
		jf.setSize(960, 960);
	}
	
	private void basisLayout(){
		//TODO Eventhandler
		menu[0]=new JButton("Daten anlegen");
		menu[1]=new JButton("Daten ändern");
		menu[2]=new JButton("Daten löschen");
		menu[3]=new JButton("Einfache Abfragen");
		menu[4]=new JButton("Komplexe Abfragen");
		fusszeile[0]=new JButton("Abbrechen");
		fusszeile[1]=new JButton("...");
	}
}