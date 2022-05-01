import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PlaTM ptm;

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
		super("Csillagászati adatkezelő program 2022");

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPicture = new JLabel(new ImageIcon("IMG\\planets4.png"));
		setIconImage(new ImageIcon("IMG\\icon64.png").getImage());
		lblPicture.setBounds(171, 11, 253, 193);
		contentPane.add(lblPicture);
		
		JButton btnLista = new JButton("Bolygók listája");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ptm = FileManager.CsvReader();
				
				PlaList el = new PlaList(Program.this, ptm);
				el.setVisible(true);
				
			}
		});
		btnLista.setBounds(10, 45, 151, 23);
		contentPane.add(btnLista);
		
		JButton btnNewButton = new JButton("Új adatsor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewPla np = new NewPla(Program.this);
				np.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 79, 151, 23);
		contentPane.add(btnNewButton);
		
		JButton btnTrls = new JButton("Törlés");
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ptm = FileManager.CsvReader();
				PlaDel pd = new PlaDel(Program.this, ptm);
				pd.setVisible(true);
			}
		});
		btnTrls.setBounds(10, 113, 151, 23);
		contentPane.add(btnTrls);
		
		JButton btnMdosts = new JButton("Módosítás");
		btnMdosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ptm = FileManager.CsvReader();
				PlaMod pm = new PlaMod(Program.this,ptm);
				pm.setVisible(true);
			}
		});
		btnMdosts.setBounds(10, 147, 151, 23);
		contentPane.add(btnMdosts);
		
		JButton btnKilepes = new JButton("Kilépés");
		btnKilepes.setToolTipText("Program bezárása");
		btnKilepes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnKilepes.setBackground(Color.WHITE);
		btnKilepes.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnKilepes.setBounds(335, 227, 89, 23);
		contentPane.add(btnKilepes);
		
		
		JButton btnKrs = new JButton("Keresés");
		btnKrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ptm = FileManager.CsvReader();
				
				PlaStat pk = new PlaStat(Program.this, ptm);
				pk.setVisible(true);
				
			}
		});
		btnKrs.setBounds(10, 181, 151, 23);
		contentPane.add(btnKrs);
		
		JLabel lblNewLabel = new JLabel("Menü");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 62, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Diagram");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlanHistogram.createAndShowGUI();
			}
		});
		btnNewButton_1.setBounds(10, 215, 151, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("id4jqm");
		lblNewLabel_1.setBounds(10, 219, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		Object platmn[] = {"Jel","Kód","Név","Felfedezési_idő","Felfedező_neve","Távolsága_Földtől"};
		ptm = new PlaTM(platmn, 0);
	}
}