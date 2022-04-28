import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private Color paleYellow = new Color(255,255,170);
	private JPanel contentPane;
	private JLabel[][] Field = new JLabel[12][12];
	private int FieldInfo[][] = new int[12][12];
	private JTextField textFileName;
	private JLabel lblOK, lblNO;
	private File sourceFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(" Treasure Hunter");
		setSize(620,720);
		setLocationRelativeTo(null);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(paleYellow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOK = new JLabel("");
		lblOK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOK.setIcon(new ImageIcon("Pict\\ok.png"));
		lblOK.setBounds(522, 621, 24, 24);
		contentPane.add(lblOK);
		lblOK.setVisible(false);
		
		JLabel lblNO = new JLabel("");
		lblNO.setFont(new Font("Tahoma", Font.PLAIN,13));
		lblNO.setIcon(new ImageIcon("Pict\\no.png"));
		lblNO.setBounds(522,621, 24,24);
		contentPane.add(lblNO);
		lblNO.setVisible(false);
		
		JButton btnLoadingTrack = new JButton("Loading Track");
		btnLoadingTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int returnValue = jfc.showOpenDialog(Program.this);
				if(returnValue == JFileChooser.APPROVE_OPTION){
					sourceFile = jfc.getSelectedFile();
					textFileName.setText(sourceFile.getAbsolutePath());
					
					boolean ok = checkFile();
					if(ok) lblOK.setVisible(true);
						else lblNO.setVisible(true);
				}
			}
		});
		btnLoadingTrack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLoadingTrack.setBounds(0, 622, 129, 23);
		contentPane.add(btnLoadingTrack);
		
		textFileName = new JTextField();
		textFileName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFileName.setBounds(204, 623, 242, 20);
		contentPane.add(textFileName);
		textFileName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("File Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(139, 627, 68, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Status:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(456, 627, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		
		instantiation();
		trackDrawer();
	}
	
	public void instantiation(){
		for (int j=0; j<12; j++)
			for(int i=0; i<12; i++)
				Field[i][j] = new JLabel();
	}
	
	public void trackDrawer(){
		for( int i=0; i<12; i++){
			if (i==0) Field[i][0].setIcon(new ImageIcon("pict\\topLeftCorner.png"));
			else if (i==11) Field[i][0].setIcon(new ImageIcon("pict\\topRightCorner.png"));
			else Field[i][0].setIcon(new ImageIcon("pict\\topLine.png"));
			contentPane.add(Field[i][0]);
			Field[i][0].setBounds(10+i*50,10,50,50);
		}
		for(int j=1; j<11; j++){
			for (int i=0; i<12; i++){
				if (i==0) Field[i][j].setIcon(new ImageIcon("pict\\rightLine.png"));
				if (i==11) Field[i][j].setIcon(new ImageIcon("pict\\leftLine.png"));
				if (i>0 && i<11) Field[i][j].setIcon(new ImageIcon("pict\\trackBase.png"));
				contentPane.add(Field[i][j]);
				Field[i][j].setBounds(10+i*50,10+j*50,50,50);
			}
		}
		for (int i=0; i<12; i++){
			if (i==0) Field[i][11].setIcon(new ImageIcon("pict\\bottomLeftCorner.png"));
			else if (i==11) Field[i][11].setIcon(new ImageIcon("pict\\bottomRightCorner.png"));
			else Field[i][11].setIcon(new ImageIcon("pict\\bottomLine.png"));
			contentPane.add(Field[i][11]);
			Field[i][11].setBounds(10+i*50,560,50,50);
		}
	}
	
	public static void SM(String msg, int type){
		JOptionPane.showMessageDialog(null, msg,"Treasure Hunter Message",type);
	}
	
	public int goodInt(String s){
		try{
			int x = Integer.parseInt(s);
			return x;
		}catch (NumberFormatException e){
			return -5;
		}
	}
	
	public boolean checkFile(){
		boolean ok=true;
		String s="", sxk="", syk="", stp="";
		int index=0, index2=0, index3=0, xk=0, yk=0;
		try{
			BufferedReader in = new BufferedReader(new FileReader(sourceFile));
			s=in.readLine();
			while(ok && s!=null){
				index=s.indexOf("Entrance:");
				if (index!=-1 && ok){
					index2 = s.indexOf(",");
					index3 = s.indexOf(" ");
					sxk = s.substring(index3+1, index2);
					syk = s.substring(index2+1, s.length());
					xk = goodInt(sxk);
					yk = goodInt(syk);
					ok = (xk > -1 && xk< 12) ? true:false;
					ok = (ok && yk > -1 && yk< 12) ? true:false;
					ok = (ok && (xk==0 || xk==11)) ? false:true;
					ok = (ok && (yk==0 || yk==11)) ? false:true;
					if (ok) FieldInfo[xk][yk] = 4;
					if (!ok) SM("The Entrance is incorrectly specified in the file!",0);
				}
					if (ok){
						index=s.indexOf("Exit:");
						if (index!=-1){
							index2 = s.indexOf(",");
							index3 = s.indexOf(" ");
							sxk = s.substring(index3+1, index2);
							syk = s.substring(index2+1, s.length());
							xk = goodInt(sxk);
							yk = goodInt(syk);
							ok = (xk > -1 && xk< 12) ? true:false;
							ok = (ok && yk > -1 && yk< 12) ? true:false;
							ok = (ok && (xk==0 || xk==11) && (yk==0 || yk==11)) ? false:true;
							if (ok) FieldInfo[xk][yk] = 3;
							if (!ok) SM("The Exit is incorrectly specified in the file!",0);
						}
					}
					if (ok){
						index=s.indexOf("Treasure:");
						if (index!=-1){
							index2 = s.indexOf(",");
							index3 = s.indexOf(" ");
							sxk = s.substring(index3+1, index2);
							syk = s.substring(index2+1, s.length());
							xk = goodInt(sxk);
							yk = goodInt(syk);
							ok = (xk > 0 && xk< 11) ? true:false;
							ok = (ok && yk > 0 && yk< 11) ? true:false;
							if (ok) FieldInfo[xk][yk] = 2;
							if (!ok) SM("The Treasure is incorrectly specified in the file!",0);
					}
				}
					if (ok){
						index=s.indexOf("Traps:");
						if (index!=-1){
							index = s.indexOf(" ");
							stp = s.substring(index+1, s.length());
							String[] pks = stp.split(" ");
							for (String sc:pks){
								index2 = s.indexOf(",");
								sxk = sc.substring(0,index2);
								syk = sc.substring(index2+1, sc.length());
								xk = goodInt(sxk);
								yk = goodInt(syk);
								ok = (xk > 0 && xk< 11) ? true:false;
								ok = (ok && yk > 0 && yk< 11) ? true:false;
								if (ok) FieldInfo[xk][yk] = 1;
							}
							if (!ok) SM("The Traps are incorrectly specified in the file!",0);
			}
		}
					s=in.readLine();
	}
	in.close();
	}catch (IOException ioe){
		ok = false;
		SM("checkFile: "+ioe.getMessage(),0);
	}
	return ok;
}
	}
