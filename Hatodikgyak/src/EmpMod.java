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

public class EmpMod extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private EmpTM etm;
	private Checker c = new Checker();
	private JTextField textkod;
	private JTextField textnev;
	private JTextField textszid;
	private JTextField textlak;
	private JTextField textfiz;
	private DbMethods dbm = new DbMethods();
	

	
	public EmpMod(JFrame f, EmpTM betm, int dbkez) {
		super(f, "Dolgozók módosítása",true);
		etm = betm;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bezár");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(335, 227, 89, 23);
		getContentPane().add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 161);
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
		
		JButton btnMod = new JButton("Módosítás");
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0, jel =0, x=0;
				for(x= 0; x < etm.getRowCount();x++)
					if((Boolean)etm.getValueAt(x, 0)) {db++; jel =x;}
					if(db==0) c.SM("Nincs kijelölve módosítandó rekord!", 0);
					
					if(db>1) c.SM("Több rekord van kijelölve!\nEgyszerre csak egy"+" rekord módosítható!", 0);
					
					if(db==1) {
						if (modDataPc() > 0) {
							boolean ok = true;
							if (c.filled(textkod)) ok = c.goodInt(textkod, "Kód");
							if (ok && c.filled(textfiz)) ok = c.goodInt(textfiz, "Fizetés");
							if (ok) {
								if(dbkez == 1) {
									String mkod = etm.getValueAt(jel, 1).toString();
									dbm.Connect();
								
								if (c.filled(textnev)) dbm.Update(mkod, "nev",c.RTF(textnev));
								if (c.filled(textszid)) dbm.Update(mkod, "szulido",c.RTF(textszid));
								if (c.filled(textlak)) dbm.Update(mkod, "lakohely",c.RTF(textlak));
								if (c.filled(textfiz)) dbm.Update(mkod, "fizetes",c.RTF(textfiz));
								if (c.filled(textkod)) dbm.Update(mkod, "kod", c.RTF(textkod));
								dbm.Disconnect();
								}
								
								if (c.filled(textkod)) etm.setValueAt(c.stringToInt(c.RTF(textkod)), jel, 1);
								if (c.filled(textnev)) etm.setValueAt(c.RTF(textnev), jel, 2);
								if (c.filled(textszid)) etm.setValueAt(c.RTF(textszid), jel, 3);
								if (c.filled(textlak)) etm.setValueAt(c.RTF(textlak), jel, 4);
								if (c.filled(textfiz)) etm.setValueAt(c.stringToInt(c.RTF(textfiz)), jel, 5);
								
								if(dbkez == 0) FileManager.Insert(etm);
								
								c.SM("A rekord módosítva!", 1);
								reset(jel);
							}
						} else {
							c.SM("Nincs kitöltve egyetlen módosító adatmező sem!", 1);
						}
					}
			}
		});
		btnMod.setBounds(20, 227, 150, 23);
		getContentPane().add(btnMod);
		
		textkod = new JTextField();
		textkod.setBounds(20, 183, 31, 20);
		getContentPane().add(textkod);
		textkod.setColumns(10);
		
		textnev = new JTextField();
		textnev.setBounds(59, 183, 89, 20);
		getContentPane().add(textnev);
		textnev.setColumns(10);
		
		textszid = new JTextField();
		textszid.setBounds(160, 183, 86, 20);
		getContentPane().add(textszid);
		textszid.setColumns(10);
		
		textlak = new JTextField();
		textlak.setBounds(255, 183, 86, 20);
		getContentPane().add(textlak);
		textlak.setColumns(10);
		
		textfiz = new JTextField();
		textfiz.setBounds(348, 183, 76, 20);
		getContentPane().add(textfiz);
		textfiz.setColumns(10);
		TableRowSorter<EmpTM> trs =
		(TableRowSorter<EmpTM>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
	public int modDataPc() {
		int pc = 0;
		if (c.filled(textkod)) pc++;
		if (c.filled(textnev)) pc++;
		if (c.filled(textszid)) pc++;
		if (c.filled(textlak)) pc++;
		if (c.filled(textfiz)) pc++;
		return pc;
	}
	
	public void reset (int i) {
		textkod.setText("");
		textnev.setText("");
		textszid.setText("");
		textlak.setText("");
		textfiz.setText("");
		etm.setValueAt(false, i, 0);
	}
}