import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Graph3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graph3 frame = new Graph3();
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
	public Graph3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdatA = new JLabel("Adat A");
		lblAdatA.setBounds(10, 33, 39, 14);
		contentPane.add(lblAdatA);
		
		JLabel lblMvelet = new JLabel("M\u0171velet");
		lblMvelet.setBounds(68, 33, 55, 14);
		contentPane.add(lblMvelet);
		
		JLabel lblAdatB = new JLabel("Adat B");
		lblAdatB.setBounds(133, 33, 46, 14);
		contentPane.add(lblAdatB);
		
		textField = new JTextField();
		textField.setBounds(10, 58, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(67, 58, 46, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(133, 58, 46, 20);
		contentPane.add(textField_2);
		
		JButton btnSzmol = new JButton("Sz\u00E1mol");
		btnSzmol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String op = textField_1.getText();
				int A = Integer.parseInt(textField.getText());
				int B = Integer.parseInt(textField_2.getText());
				int ered = 0;
				
				switch(op){
				case "*":
					ered = A*B;
					break;
				case "/":
					ered = A/B;
					ered *=1f; //floattá konvertálni
					break;
				case "+":
					ered = A+B;
					break;
				case "-":
					ered = A-B;
					break;
				}
				
				Integer.toString(ered);
				textField_3.setText(""+ ered);
				 
			}
		});
		btnSzmol.setBounds(10, 111, 89, 23);
		contentPane.add(btnSzmol);
		
		JLabel lblEredmny = new JLabel("Eredm\u00E9ny:");
		lblEredmny.setBounds(133, 115, 62, 14);
		contentPane.add(lblEredmny);
		
		textField_3 = new JTextField();
		textField_3.setBounds(199, 112, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(10, 187, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnBezr = new JButton("Bez\u00E1r");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnBezr.setBounds(298, 187, 89, 23);
		contentPane.add(btnBezr);
	}

}
