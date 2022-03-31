import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private EmpTM etm;
	private DbMethods dbm = new DbMethods();
	private int dbkez = 0;

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
		dbm.Reg();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dbkez == 0) etm = FileManager.CsvReader();
				else{
					dbm.Connect();
					etm = dbm.ReadAllData();
					dbm.Disconnect();
				}
				EmpList el = new EmpList(Program.this, etm);
				el.setVisible(true);
				
			}
		});
		btnLista.setBounds(0, 11, 116, 23);
		contentPane.add(btnLista);
		
		JButton btnNewButton = new JButton("Új adatsor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEmp ne = new NewEmp(dbkez);
				ne.setVisible(true);
			}
		});
		btnNewButton.setBounds(0, 41, 116, 23);
		contentPane.add(btnNewButton);
		
		JButton btnTrls = new JButton("Törlés");
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dbkez == 0) etm = FileManager.CsvReader();
				else{
					dbm.Connect();
					etm = dbm.ReadAllData();
					dbm.Disconnect();
				}
				EmpDel ed = new EmpDel(Program.this, etm,dbkez);
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
		
		JButton btnProba = new JButton("Proba");
		btnProba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.Connect();
				dbm.Disconnect();
			}
		});
		btnProba.setBounds(286, 54, 89, 23);
		contentPane.add(btnProba);
		
		JCheckBox chckbxDbKezels = new JCheckBox("DB kezel\u00E9s");
		chckbxDbKezels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxDbKezels.isSelected()) dbkez = 1;
				else dbkez = 0;
			}
		});
		chckbxDbKezels.setBounds(286, 109, 97, 23);
		contentPane.add(chckbxDbKezels);
		
		
		
		Object emptmn[] = {"Jel","Kód","Név","Szülidõ‘","Lakóhely","Fizetés"};
		etm = new EmpTM(emptmn, 0);
	}
}
