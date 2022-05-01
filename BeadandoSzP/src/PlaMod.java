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
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class PlaMod extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private PlaTM ptm;
	private Checker c = new Checker();
	private JTextField textkod;
	private JTextField textnev;
	private JTextField textfid;
	private JTextField textfnev;
	private JTextField texttav;
	

	
	public PlaMod(JFrame f, PlaTM bptm) {
		super(f, "Csillagászati adat módosítása",true);
		getContentPane().setBackground(new Color(153, 255, 255));
		ptm = bptm;
		
		setBounds(100, 100, 709, 391);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bezár");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(594, 318, 89, 23);
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
		
		JButton btnMod = new JButton("Módosítás");
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0, jel =0, x=0;
				for(x= 0; x < ptm.getRowCount();x++)
					if((Boolean)ptm.getValueAt(x, 0)) {db++; jel =x;}
					if(db==0) c.SM("Nincs kijelölve módosítandó rekord!", 0);
					
					if(db>1) c.SM("Több rekord van kijelölve!\nEgyszerre csak egy"+" rekord módosítható!", 0);
					
					if(db==1) {
						if (modDataPc() > 0) {
							boolean ok = true;
							if (c.filled(textkod)) ok = c.goodInt(textkod, "Kód");
							if (ok && c.filled(texttav)) ok = c.goodInt(texttav, "Fizetés");
							if (ok) {
								
								if (c.filled(textkod)) ptm.setValueAt(c.stringToInt(c.RTF(textkod)), jel, 1);
								if (c.filled(textnev)) ptm.setValueAt(c.RTF(textnev), jel, 2);
								if (c.filled(textfid)) ptm.setValueAt(c.RTF(textfid), jel, 3);
								if (c.filled(textfnev)) ptm.setValueAt(c.RTF(textfnev), jel, 4);
								if (c.filled(texttav)) ptm.setValueAt(c.stringToInt(c.RTF(texttav)), jel, 5);
								
								FileManager.Insert(ptm);
								
								c.SM("A rekord módosítva!", 1);
								reset(jel);
							}
						} else {
							c.SM("Nincs kitöltve egyetlen módosító adatmező sem!", 1);
						}
					}
			}
		});
		btnMod.setBounds(10, 318, 150, 23);
		getContentPane().add(btnMod);
		
		textkod = new JTextField();
		textkod.setBounds(10, 287, 31, 20);
		getContentPane().add(textkod);
		textkod.setColumns(10);
		
		textnev = new JTextField();
		textnev.setBounds(51, 287, 89, 20);
		getContentPane().add(textnev);
		textnev.setColumns(10);
		
		textfid = new JTextField();
		textfid.setBounds(150, 287, 119, 20);
		getContentPane().add(textfid);
		textfid.setColumns(10);
		
		textfnev = new JTextField();
		textfnev.setBounds(279, 287, 105, 20);
		getContentPane().add(textfnev);
		textfnev.setColumns(10);
		
		texttav = new JTextField();
		texttav.setBounds(394, 287, 146, 20);
		getContentPane().add(texttav);
		texttav.setColumns(10);
		
		JLabel lblKod = new JLabel("Kód");
		lblKod.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKod.setBounds(10, 262, 31, 14);
		getContentPane().add(lblKod);
		
		JLabel lblNev = new JLabel("Név");
		lblNev.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNev.setBounds(51, 262, 46, 14);
		getContentPane().add(lblNev);
		
		JLabel lblFlfdzIdo = new JLabel("Felfedezés dátuma");
		lblFlfdzIdo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFlfdzIdo.setBounds(150, 262, 122, 14);
		getContentPane().add(lblFlfdzIdo);
		
		JLabel lblFlfdzNev = new JLabel("Felfedező neve");
		lblFlfdzNev.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFlfdzNev.setBounds(279, 262, 86, 14);
		getContentPane().add(lblFlfdzNev);
		
		JLabel lblTav = new JLabel("Távolsága Földünktől (km)");
		lblTav.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTav.setBounds(394, 262, 166, 14);
		getContentPane().add(lblTav);
		
		JLabel lblInstruction = new JLabel("Jelöljön ki egy módosítani kívánt csillagászati adatot, majd töltse ki a mezőket!");
		lblInstruction.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInstruction.setBounds(20, 222, 520, 23);
		getContentPane().add(lblInstruction);
		@SuppressWarnings("unchecked")
		TableRowSorter<PlaTM> trs =
		(TableRowSorter<PlaTM>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
	public int modDataPc() {
		int pc = 0;
		if (c.filled(textkod)) pc++;
		if (c.filled(textnev)) pc++;
		if (c.filled(textfid)) pc++;
		if (c.filled(textfnev)) pc++;
		if (c.filled(texttav)) pc++;
		return pc;
	}
	
	public void reset (int i) {
		textkod.setText("");
		textnev.setText("");
		textfid.setText("");
		textfnev.setText("");
		texttav.setText("");
		ptm.setValueAt(false, i, 0);
	}
}