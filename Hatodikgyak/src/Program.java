import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private EmpTM etm;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.CsvReader();
				EmpList el = new EmpList(Program.this, etm);
				el.setVisible(true);
				
			}
		});
		btnLista.setBounds(0, 11, 89, 23);
		contentPane.add(btnLista);
		
		Object emptmn[] = {"Jel","Kód","Név","Szülidõ","Lakóhely","Fizetés"};
		etm = new EmpTM(emptmn, 0);
	}
}
