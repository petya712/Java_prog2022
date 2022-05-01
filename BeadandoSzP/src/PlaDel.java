import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class PlaDel extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private PlaTM ptm;
	private Checker c = new Checker();

	
	public PlaDel(JFrame f, PlaTM bptm) {
		super(f, "Csillagászati adat törlése",true);
		getContentPane().setBackground(new Color(153, 255, 255));
		ptm = bptm;
		
		setBounds(100, 100, 669, 345);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bezár");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(453, 276, 89, 23);
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
		
		JButton btnAdatsorTrlse = new JButton("Adatsor törlése");
		btnAdatsorTrlse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0, jel =0, x=0;
				for(x= 0; x < ptm.getRowCount();x++)
					if((Boolean)ptm.getValueAt(x, 0)) {db++; jel=x;}
					if(db==0) c.SM("Nincs kijelölve törlendõ rekord!", 0);
					
					if(db>1) c.SM("Több rekord van kijelölve!\nEgyszerre csak egy"+" rekord törölhetõ!", 0);
					
					if(db==1) {
						ptm.removeRow(jel);
						FileManager.Insert(ptm);
						
						dispose();
						c.SM("A rekord törölve!", 1);
					}
			}
		});
		btnAdatsorTrlse.setBounds(10, 276, 150, 23);
		getContentPane().add(btnAdatsorTrlse);
		
		JLabel lblInstruct = new JLabel("Válasszon ki egy törölni kívánt adatsort!");
		lblInstruct.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInstruct.setBounds(10, 227, 331, 23);
		getContentPane().add(lblInstruct);
		@SuppressWarnings("unchecked")
		TableRowSorter<PlaTM> trs =
		(TableRowSorter<PlaTM>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
}