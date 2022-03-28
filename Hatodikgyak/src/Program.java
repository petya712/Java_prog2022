import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

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
				etm = FileManager.CsvReader();
				EmpList el = new EmpList(Program.this, etm);
				el.setVisible(true);
				
			}
		});
		btnLista.setBounds(0, 11, 116, 23);
		contentPane.add(btnLista);
		
		JButton btnNewButton = new JButton("Új adatsor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEmp ne = new NewEmp();
				ne.setVisible(true);
			}
		});
		btnNewButton.setBounds(0, 41, 116, 23);
		contentPane.add(btnNewButton);
		
		JButton btnTrls = new JButton("Törlés");
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etm = FileManager.CsvReader();
				EmpDel ed = new EmpDel(Program.this, etm);
				ed.setVisible(true);
			}
		});
		btnTrls.setBounds(0, 75, 116, 23);
		contentPane.add(btnTrls);
		
		JButton btnMdosts = new JButton("Módosítás");
		btnMdosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etm = FileManager.CsvReader();
				EmpMod em = new EmpMod(Program.this,etm);
				em.setVisible(true);
			}
		});
		btnMdosts.setBounds(0, 109, 116, 23);
		contentPane.add(btnMdosts);
		
		JButton btnKilepes = new JButton("Kilépés");
		btnKilepes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnKilepes.setBackground(Color.WHITE);
		btnKilepes.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnKilepes.setBounds(335, 227, 89, 23);
		contentPane.add(btnKilepes);
		
		
		
		Object emptmn[] = {"Jel","Kód","Név","Szülidő","Lakóhely","Fizetés"};
		etm = new EmpTM(emptmn, 0);
	}
}
