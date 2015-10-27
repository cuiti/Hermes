package hermes.view;

import java.awt.*;
import javax.swing.*;

public class MonitorView extends JFrame{

	private JPanel panelPrincipal;
	
	public MonitorView() {
		panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hermes | Monitor para terapeuta");	
		setSize(880, 500);
		//setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MonitorView view = new MonitorView();
	}

}
