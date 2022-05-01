import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NewPla extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTextField textkod;
	private JTextField textnev;
	private JTextField textfid;
	private JTextField textfnev;
	private JTextField texttav;
	

	
	
	public NewPla(JFrame f) {
		super(f,"Új csillagászati adat beszúrása");
		getContentPane().setBackground(new Color(153, 255, 255));
		
		setBounds(100, 100, 500, 250);
		getContentPane().setLayout(null);
		
		JLabel lblFnev = new JLabel("Felfedező neve:");
		lblFnev.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFnev.setBounds(22, 106, 166, 14);
		getContentPane().add(lblFnev);
		
		JLabel lblFlid = new JLabel("Felfedezés ideje:");
		lblFlid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFlid.setBounds(22, 81, 166, 14);
		getContentPane().add(lblFlid);
		
		JLabel lblNv = new JLabel("Bolygó neve:");
		lblNv.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNv.setBounds(22, 55, 166, 14);
		getContentPane().add(lblNv);
		
		JLabel lblKd = new JLabel("Kód:");
		lblKd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKd.setBounds(22, 30, 87, 14);
		getContentPane().add(lblKd);
		
		JLabel lblTav = new JLabel("Távolsága Földünktől (km):");
		lblTav.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTav.setBounds(22, 131, 188, 14);
		getContentPane().add(lblTav);
		
		textkod = new JTextField();
		textkod.setBounds(241, 28, 46, 20);
		getContentPane().add(textkod);
		textkod.setColumns(10);
		
		textnev = new JTextField();
		textnev.setColumns(10);
		textnev.setBounds(241, 53, 111, 20);
		getContentPane().add(textnev);
		
		textfid = new JTextField();
		textfid.setColumns(10);
		textfid.setBounds(241, 79, 111, 20);
		getContentPane().add(textfid);
		
		textfnev = new JTextField();
		textfnev.setColumns(10);
		textfnev.setBounds(241, 104, 184, 20);
		getContentPane().add(textfnev);
		
		texttav = new JTextField();
		texttav.setColumns(10);
		texttav.setBounds(241, 129, 184, 20);
		getContentPane().add(texttav);
		
		JButton btnBeszr = new JButton("Beszúr");
		btnBeszr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Checker c = new Checker();
				if (c.goodInt(textkod, "Kód"))
					if (c.filled(textnev, "Név"))
						if (c.goodDate(textfid,"Felfedezési idõ"))
							if(c.filled(textfnev, "Felfedező neve"))
								if(c.goodInt(texttav,"Távolsága Földtől"))
									
										FileManager.Insert(RTF(textkod), RTF(textnev), RTF(textfid), RTF(textfnev), RTF(texttav));
									
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
		textfid.setText("");
		textfnev.setText("");
		texttav.setText("");
	}
	
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}