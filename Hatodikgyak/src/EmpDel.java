import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpDel extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private EmpTM etm;
	private Checker c = new Checker();
	//private DbMethods dbm = new DbMethods();

	
	public EmpDel(JFrame f, EmpTM betm) {
		super(f, "Dolgozók törlése",true);
		etm = betm;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bez\u00E1r");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(335, 227, 89, 23);
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
		
		JButton btnAdatsorTrlse = new JButton("Adatsor törlése");
		btnAdatsorTrlse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0, jel =0, x=0;
				for(x= 0; x < etm.getRowCount();x++)
					if((Boolean)etm.getValueAt(x, 0)) {db++; jel=x;}
					if(db==0) c.SM("Nincs kijelölve törlendő rekord!", 0);
					
					if(db>1) c.SM("Több rekord van kijelölve!\nEgyszerre csak egy"+" rekord törölhető!", 0);
					
					if(db==1) {
						etm.removeRow(jel);
						FileManager.Insert(etm);
						dispose();
						c.SM("A rekord törölve!", 1);
					}
			}
		});
		btnAdatsorTrlse.setBounds(20, 227, 150, 23);
		getContentPane().add(btnAdatsorTrlse);
		TableRowSorter<EmpTM> trs =
		(TableRowSorter<EmpTM>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
}
