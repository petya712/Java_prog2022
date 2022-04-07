import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Program extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCurFolder;
	private File CurDir;
	private String CurDirText="";
	private String separator = System.getProperty("file.separator");
	private JTextField textNewDirName;

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
		setTitle("FileSystem Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCurrentFolder = new JLabel("Current folder:");
		lblCurrentFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCurrentFolder.setBounds(10, 27, 104, 14);
		contentPane.add(lblCurrentFolder);
		
		CurDir = new File(System.getProperty("user.dir"));
		CurDirText = CurDir.getAbsolutePath();
		
		
		textCurFolder = new JTextField(CurDirText);
		textCurFolder.setBackground(Color.WHITE);
		textCurFolder.setEditable(false);
		textCurFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCurFolder.setBounds(124, 25, 477, 20);
		contentPane.add(textCurFolder);
		textCurFolder.setColumns(10);
		
		JButton btnList = new JButton("List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl1 = new ContentList(Program.this, CurDir,1);
				ctl1.setVisible(true);
			}
		});
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnList.setBounds(10, 71, 89, 23);
		contentPane.add(btnList);
		
		JButton btnChangeUpFolder = new JButton("Change Up Folder");
		btnChangeUpFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					CurDir = CurDir.getParentFile();
					CurDirText = CurDir.getAbsolutePath();
					textCurFolder.setText(CurDirText);
				}catch(Exception ex){
					SM("You're on the top dir!\nYou can't go higher!",0);
				}
			}
		});
		
		
		btnChangeUpFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChangeUpFolder.setBounds(124, 72, 158, 23);
		contentPane.add(btnChangeUpFolder);
		
		JButton btnChangeChildFolder = new JButton("Change Child Folder");
		btnChangeChildFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl2 = new ContentList(Program.this, CurDir,2);
				ctl2.setVisible(true);
				String outDir = ctl2.getOutDir();
				CurDir = new File(CurDir.getPath()+ separator + outDir);
				CurDirText = CurDir.getAbsolutePath();
				textCurFolder.setText(CurDirText);
			}
		});
		btnChangeChildFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChangeChildFolder.setBounds(312, 72, 158, 23);
		contentPane.add(btnChangeChildFolder);
		
		JLabel lblNewFolderName = new JLabel("New Folder Name:");
		lblNewFolderName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewFolderName.setBounds(454, 120, 142, 14);
		contentPane.add(lblNewFolderName);
		
		textNewDirName = new JTextField();
		textNewDirName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNewDirName.setBounds(454, 145, 142, 20);
		contentPane.add(textNewDirName);
		textNewDirName.setColumns(10);
		
		JButton btnCreateNewFolder = new JButton("Create New Folder");
		btnCreateNewFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newDir = RTF(textNewDirName);
				if(newDir.length()==0) SM("New Dir name field is empty!",0);
				else{
					File temp = new File(CurDir.getPath() + separator+ newDir);
					if(temp.exists()) SM("A folder with this name already exists",0);
				else{
					temp.mkdir();
					SM("Folder created!",1);
					textNewDirName.setText(null);
					}
				}
				
			}
		});
		btnCreateNewFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreateNewFolder.setBounds(606, 144, 168, 23);
		contentPane.add(btnCreateNewFolder);
		
		JButton btnDeleteCurFolder = new JButton("Delete Cur. Folder");
		btnDeleteCurFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File newCurDir = CurDir.getParentFile();
				boolean ok = CurDir.delete();
				if(!ok) SM("The folder is not empty, cannot be deleted!",0);
				else{
					CurDir = newCurDir;
					CurDirText = CurDir.getAbsolutePath();
					textCurFolder.setText(CurDirText);
					SM("The current folder has become the parent folder!",1);
				}
			}
		});
		btnDeleteCurFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteCurFolder.setBounds(606, 178, 168, 23);
		contentPane.add(btnDeleteCurFolder);
	}
	
	public void SM(String msg, int type){
		JOptionPane.showMessageDialog(Program.this, msg, "FileSystem Manager message",type);
	}
	
	public String RTF(JTextField jtf){
		return jtf.getText();
	}
}
