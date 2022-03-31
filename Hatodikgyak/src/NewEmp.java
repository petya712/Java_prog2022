import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewEmp extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTextField textkod;
	private JTextField textnev;
	private JTextField textszid;
	private JTextField textlak;
	private JTextField textfiz;
	private DbMethods dbm = new DbMethods();
	

	
	
	public NewEmp(int dbkez) {
		setBounds(100, 100, 400, 250);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lakóhely:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(22, 106, 87, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblSzlid = new JLabel("Szülidõ:");
		lblSzlid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSzlid.setBounds(22, 81, 87, 14);
		getContentPane().add(lblSzlid);
		
		JLabel lblNv = new JLabel("Név:");
		lblNv.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNv.setBounds(22, 55, 87, 14);
		getContentPane().add(lblNv);
		
		JLabel lblKd = new JLabel("Kód:");
		lblKd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKd.setBounds(22, 30, 87, 14);
		getContentPane().add(lblKd);
		
		JLabel lblFizets = new JLabel("Fizetés:");
		lblFizets.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFizets.setBounds(22, 131, 87, 14);
		getContentPane().add(lblFizets);
		
		textkod = new JTextField();
		textkod.setBounds(99, 28, 46, 20);
		getContentPane().add(textkod);
		textkod.setColumns(10);
		
		textnev = new JTextField();
		textnev.setColumns(10);
		textnev.setBounds(99, 53, 111, 20);
		getContentPane().add(textnev);
		
		textszid = new JTextField();
		textszid.setColumns(10);
		textszid.setBounds(99, 79, 111, 20);
		getContentPane().add(textszid);
		
		textlak = new JTextField();
		textlak.setColumns(10);
		textlak.setBounds(99, 104, 198, 20);
		getContentPane().add(textlak);
		
		textfiz = new JTextField();
		textfiz.setColumns(10);
		textfiz.setBounds(99, 131, 111, 20);
		getContentPane().add(textfiz);
		
		JButton btnBeszr = new JButton("Beszúr");
		btnBeszr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Checker c = new Checker();
				if (c.goodInt(textkod, "Kód"))
					if (c.filled(textnev, "Név"))
						if (c.goodDate(textszid,"Születési idõ"))
							if(c.filled(textlak, "Lakcím"))
								if(c.goodInt(textfiz,"Fizetés"))
									if(dbkez == 0){
										FileManager.Insert(RTF(textkod), RTF(textnev), RTF(textszid), RTF(textlak), RTF(textfiz));
									}else{
										dbm.Connect();
										dbm.Insert(RTF(textkod), RTF(textnev), RTF(textszid), RTF(textlak), RTF(textfiz));
										dbm.Disconnect();
									}
					reset();
			}
			});
		btnBeszr.setBounds(99, 177, 89, 23);
		getContentPane().add(btnBeszr);
		
		JButton btnBezr = new JButton("Bezár");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezr.setBounds(285, 177, 89, 23);
		getContentPane().add(btnBezr);
		

	}
	public void reset () {
		textkod.setText("");
		textnev.setText("");
		textszid.setText("");
		textlak.setText("");
		textfiz.setText("");
	}
	
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
