import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Graph2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graph2 frame = new Graph2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Graph2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblrjBeValamit = new JLabel("\u00CDrj be valamit...");
		lblrjBeValamit.setBounds(75, 50, 83, 14);
		contentPane.add(lblrjBeValamit);
		
		textField = new JTextField();
		textField.setBounds(75, 75, 241, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnKir = new JButton("Ki\u00EDr");
		btnKir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = textField.getText();
				JOptionPane.showMessageDialog(Graph2.this, "A beírt adat: "+s, "Üzenet", 1);
			}
		});
		btnKir.setBounds(69, 158, 89, 23);
		contentPane.add(btnKir);
		
		JButton btnBezr = new JButton("Bez\u00E1r");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnBezr.setBounds(227, 158, 89, 23);
		contentPane.add(btnBezr);
	}
}
