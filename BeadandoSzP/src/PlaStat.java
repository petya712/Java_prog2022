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
import java.awt.Color;

public class PlaStat extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private PlaTM ptm;
	private JTextField textSkod;
	private JTextField textSnev;
	private Checker c = new Checker();

	
	public PlaStat(JFrame f, PlaTM bptm) {
		super(f, "Bolygók keresése",true);
		getContentPane().setBackground(new Color(153, 255, 255));
		ptm = bptm;
		
		setBounds(100, 100, 668, 443);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bezár");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(553, 375, 89, 23);
		getContentPane().add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 630, 200);
		getContentPane().add(scrollPane);
		
		table = new JTable(ptm);
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
				boolean okkod = false;
				boolean oknev = false;
				if (c.filled(textSkod)) okkod = c.goodInt(textSkod, "Kód");
				if (c.filled(textSnev)) oknev = c.filled(textSnev, "Név");
				if(okkod || oknev) {
				ptm = FileManager.StatReader(textSkod.getText(),textSnev.getText());
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 11, 630, 200);
				getContentPane().add(scrollPane);
				
				table = new JTable(ptm);
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
				}else {
					c.SM("Sikertelen keresés!", 0);
				}
			}
		});
		btnKereses.setBounds(335, 295, 89, 23);
		getContentPane().add(btnKereses);
		
		textSkod = new JTextField();
		textSkod.setBounds(120, 264, 86, 20);
		getContentPane().add(textSkod);
		textSkod.setColumns(10);
		
		textSnev = new JTextField();
		textSnev.setColumns(10);
		textSnev.setBounds(120, 296, 182, 20);
		getContentPane().add(textSnev);
		
		JLabel lblKod = new JLabel("Kód:");
		lblKod.setBounds(10, 267, 46, 14);
		getContentPane().add(lblKod);
		
		JLabel lblBnev = new JLabel("Bolygó neve:");
		lblBnev.setBounds(10, 301, 71, 14);
		getContentPane().add(lblBnev);
		
		JLabel lblKrss = new JLabel("Keresés:");
		lblKrss.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKrss.setBounds(10, 227, 71, 14);
		getContentPane().add(lblKrss);
		
		JButton btnIsmrtButton = new JButton("Ismert bolygók száma");
		btnIsmrtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.BCount();
			}
		});
		btnIsmrtButton.setToolTipText("Jelenleg összes általunk ismert bolygók száma");
		btnIsmrtButton.setBounds(10, 375, 169, 23);
		getContentPane().add(btnIsmrtButton);
		
		JButton btnLista = new JButton("Teljes lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ptm = FileManager.CsvReader();
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 11, 630, 200);
				getContentPane().add(scrollPane);
				
				table = new JTable(ptm);
				scrollPane.setViewportView(table);
				
				TableColumn tc = null;
				for (int i = 0; i < 6; i++) {
				tc = table.getColumnModel().getColumn(i);
				if (i==0 || i==1) tc.setPreferredWidth(30);
				else if (i==4) tc.setPreferredWidth(150);
				else {tc.setPreferredWidth(100);}
				}

				table.setAutoCreateRowSorter(true);
				
			}
		});
		btnLista.setBounds(542, 224, 100, 23);
		getContentPane().add(btnLista);
		
		JLabel lblOsszIsm = new JLabel("Jelenleg összes általunk ismert bolygók számának lekérdezése");
		lblOsszIsm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOsszIsm.setBounds(10, 350, 445, 23);
		getContentPane().add(lblOsszIsm);
		@SuppressWarnings("unchecked")
		TableRowSorter<PlaTM> trs =
		(TableRowSorter<PlaTM>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
}