import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



//This be the mfin' G to U and I
public class MainmitGUI implements ActionListener {
	private JFrame gui;
	private JPanel jp;
	private JButton menu11;
	private JButton menu12;
	private JButton menu13;
	public MainmitGUI() {
		start();
	}

	public static void main(String args[]) {
		new MainmitGUI();

	}

	public void start() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		gui= new JFrame();
		gui.setTitle("TITEL");
		JPanel jp = new JPanel(new GridLayout(3,0));
		menu11= new JButton("Aufgabe 1");
		menu11.setPreferredSize(new Dimension(360,240));
		menu12= new JButton("Aufgabe 2");
		menu12.setPreferredSize(new Dimension(360,240));
		menu13= new JButton("Aufgabe 3");
		menu13.setPreferredSize(new Dimension(360,240));
		jp.add(menu11);
		jp.add(menu12);
		jp.add(menu13);
		gui.setSize(screenSize.width, screenSize.height-30);
		gui.setVisible(true);
		gui.setContentPane(jp);
		gui.pack();
		gui.setResizable(false);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.repaint();
	}
	public void menu1(){
		
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object quelle = ev.getSource();
		// if ( == quelle) {

	}

}
