import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class EmpStat extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private EmpTM etm;
	private JTextField textSkod;
	private JTextField textSnev;
	private Checker c = new Checker();

	
	public EmpStat(JFrame f, EmpTM betm) {
		super(f, "Dolgozók statisztikája",true);
		etm = betm;
		
		setBounds(100, 100, 450, 523);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bezár");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(335, 450, 89, 23);
		getContentPane().add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 205);
		getContentPane().add(scrollPane);
		
		table = new JTable(etm);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0 || i==1) tc.setPreferredWidth(30);
		else if (i==4) tc.setPreferredWidth(150);
		else {tc.setPreferredWidth(100);}
		}

		table.setAutoCreateRowSorter(true);
		
		JButton btnKereses = new JButton("Keresés");
		btnKereses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ok = true;
				if (c.filled(textSkod)) ok = c.goodInt(textSkod, "Kód");
				if(ok) {
				etm = FileManager.StatReader(textSkod.getText(),textSnev.getText());
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 11, 414, 205);
				getContentPane().add(scrollPane);
				
				table = new JTable(etm);
				scrollPane.setViewportView(table);
				
				TableColumn tc = null;
				for (int i = 0; i < 6; i++) {
				tc = table.getColumnModel().getColumn(i);
				if (i==0 || i==1) tc.setPreferredWidth(30);
				else if (i==4) tc.setPreferredWidth(150);
				else {tc.setPreferredWidth(100);}
				}

				table.setAutoCreateRowSorter(true);
				
				c.SM("Sikeres keresés!", 1);
				}
			}
		});
		btnKereses.setBounds(335, 295, 89, 23);
		getContentPane().add(btnKereses);
		
		textSkod = new JTextField();
		textSkod.setBounds(75, 267, 86, 20);
		getContentPane().add(textSkod);
		textSkod.setColumns(10);
		
		textSnev = new JTextField();
		textSnev.setColumns(10);
		textSnev.setBounds(75, 298, 182, 20);
		getContentPane().add(textSnev);
		
		JLabel lblNewLabel = new JLabel("Kód:");
		lblNewLabel.setBounds(10, 267, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Név:");
		lblNewLabel_1.setBounds(10, 301, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Keresés:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 227, 71, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Összes kiadás");
		btnNewButton.setBounds(22, 393, 119, 23);
		getContentPane().add(btnNewButton);
		TableRowSorter<EmpTM> trs =
		(TableRowSorter<EmpTM>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
}