import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class PlaList extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private PlaTM ptm;

	
	public PlaList(JFrame f, PlaTM bptm) {
		super(f, "Bolygók listája",true);
		getContentPane().setBackground(new Color(153, 255, 255));
		ptm = bptm;
		
		setBounds(100, 100, 670, 300);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bezár");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(555, 227, 89, 23);
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
		
		JLabel lblBlygList = new JLabel("Általunk ismert bolygók listája");
		lblBlygList.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBlygList.setBounds(20, 231, 327, 19);
		getContentPane().add(lblBlygList);
		@SuppressWarnings("unchecked")
		TableRowSorter<PlaTM> trs =
		(TableRowSorter<PlaTM>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
}