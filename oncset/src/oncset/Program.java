package oncset;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Program extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Utilities ut = new Utilities();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
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
	public Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 380);
		setSize(450, 380);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(10, 242, 414, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 220);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int key =arg0.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					textArea.append(textField.getText()+System.getProperty("line.separator"));
					textField.setText("");
					Toolkit.getDefaultToolkit();
				}
			}
		});
		
		textField.setBounds(51, 11, 342, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBackground(Color.WHITE);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append(textField.getText()+System.getProperty("line.separator"));
				textField.setText("");
			}
		});
		btnSend.setBounds(10, 54, 89, 23);
		panel.add(btnSend);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				textField.setText("");
			}
		});
		btnReset.setBackground(Color.WHITE);
		btnReset.setBounds(109, 54, 89, 23);
		panel.add(btnReset);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(ut.Open());
			}
		});
		btnOpen.setBackground(Color.LIGHT_GRAY);
		btnOpen.setBounds(208, 54, 89, 23);
		panel.add(btnOpen);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ut.Save(textArea);
				textArea.setText(null);
			}
		});
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setBounds(307, 54, 89, 23);
		panel.add(btnSave);
		
		JLabel lblNewLabel = new JLabel("Text");
		lblNewLabel.setBounds(10, 14, 31, 14);
		panel.add(lblNewLabel);
		
		
	}
}
