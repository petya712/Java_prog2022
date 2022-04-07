import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

public class ContentList extends JDialog {
	private String outDir="";
	private JTable table;
	private ListTM ltm;

	
	
	public ContentList(JFrame f, File inDir, int mod) {
		super(f, " List of folder entries", true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.setBounds(335, 227, 89, 23);
		getContentPane().add(btnExit);
		
		Object emptmn[]={"Check", "Object name", "Dir"};
		ltm = new ListTM(emptmn, 0);
		
		File[] list=inDir.listFiles();
		for(int i=0;i<list.length;i++){
			boolean isd = list[i].isDirectory();
			if (mod==1) ltm.addRow(new Object[]{false, list[i].getName(),isd});
			if (mod==2 && isd) ltm.addRow(new Object[]{false, list[i].getName(),isd});
		}
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 202);
		getContentPane().add(scrollPane);
		
		table = new JTable(ltm);
		scrollPane.setViewportView(table);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, jel=0, x=0;
				for(x = 0; x < ltm.getRowCount();x++)
				if ((Boolean)ltm.getValueAt(x, 0)){db++; jel=x;}
				if (db==0) SM("No folder selected!",0);
				if (db>1) SM("Multiple folders selected! Just one",0);
				if (db==1){
					outDir = ltm.getValueAt(jel, 1).toString();
					dispose();
				}
			}
		});
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChange.setBounds(20, 228, 89, 23);
		getContentPane().add(btnChange);
		if (mod == 2) btnChange.setVisible(true);
		else btnChange.setVisible(false);
		
		TableColumn tc = null;
		for (int i = 0; i < 3; i++){
			tc = table.getColumnModel().getColumn(i);
			if(i==0 || i==2) tc.setPreferredWidth(20);
			else {tc.setPreferredWidth(250);}
		}

	}
	
	public String getOutDir(){
		return outDir;
	}
	
	public void SM(String msg, int type){
		JOptionPane.showMessageDialog(ContentList.this, msg,"FileSystem Manger üzenet", type);
	}
}
