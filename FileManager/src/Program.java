import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Program extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCurFolder;
	private File CurDir;
	private String CurDirText="";
	private String separator = System.getProperty("file.separator");
	private JTextField textNewDirName;
	private JTextField textNewFileName;
	private JTextField textSource;
	private JTextField textDestination;
	private String selectedFile="";
	private File sourceFile, destFile;

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
		setBounds(100, 100, 800, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCurrentFolder = new JLabel("Current folder:");
		lblCurrentFolder.setBounds(10, 27, 104, 14);
		lblCurrentFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblCurrentFolder);
		
		CurDir = new File(System.getProperty("user.dir"));
		CurDirText = CurDir.getAbsolutePath();
		
		
		textCurFolder = new JTextField(CurDirText);
		textCurFolder.setBounds(124, 25, 477, 20);
		textCurFolder.setBackground(Color.WHITE);
		textCurFolder.setEditable(false);
		textCurFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(textCurFolder);
		textCurFolder.setColumns(10);
		
		JButton btnList = new JButton("List");
		btnList.setBounds(10, 71, 89, 23);
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl1 = new ContentList(Program.this, CurDir,1);
				ctl1.setVisible(true);
			}
		});
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnList);
		
		JButton btnChangeUpFolder = new JButton("Change Up Folder");
		btnChangeUpFolder.setBounds(124, 72, 158, 23);
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
		contentPane.add(btnChangeUpFolder);
		
		JButton btnChangeChildFolder = new JButton("Change Child Folder");
		btnChangeChildFolder.setBounds(312, 72, 158, 23);
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
		contentPane.add(btnChangeChildFolder);
		
		JLabel lblNewFolderName = new JLabel("New Folder Name:");
		lblNewFolderName.setBounds(454, 120, 142, 14);
		lblNewFolderName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNewFolderName);
		
		textNewDirName = new JTextField();
		textNewDirName.setBounds(454, 145, 142, 20);
		textNewDirName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(textNewDirName);
		textNewDirName.setColumns(10);
		
		JButton btnCreateNewFolder = new JButton("Create New Folder");
		btnCreateNewFolder.setBounds(606, 144, 168, 23);
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
		contentPane.add(btnCreateNewFolder);
		
		JButton btnDeleteCurFolder = new JButton("Delete Cur. Folder");
		btnDeleteCurFolder.setBounds(606, 178, 168, 23);
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
		contentPane.add(btnDeleteCurFolder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 149, 413, 130);
		contentPane.add(scrollPane);
		
		JTextArea textNewFileContent = new JTextArea();
		scrollPane.setViewportView(textNewFileContent);
		
		JLabel lblNewFileName = new JLabel("New File Name:");
		lblNewFileName.setBounds(454, 232, 142, 14);
		lblNewFileName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNewFileName);
		
		textNewFileName = new JTextField();
		textNewFileName.setBounds(454, 257, 142, 20);
		textNewFileName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNewFileName.setColumns(10);
		contentPane.add(textNewFileName);
		
		JButton btnCreateNewFile = new JButton("Create New File");
		btnCreateNewFile.setBounds(606, 257, 168, 23);
		btnCreateNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = textNewFileName.getText();
				if(fileName.length()==0) SM("File name is missing!",0);
				else {
					CreateFile(textNewFileContent, fileName);
					textNewFileName.setText(null);
					textNewFileContent.setText(null);
					SM("File created!",1);
				}
			}
		});
		btnCreateNewFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnCreateNewFile);
		
		JLabel lblNewFileContent = new JLabel("New File Content:");
		lblNewFileContent.setBounds(10, 121, 142, 14);
		lblNewFileContent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNewFileContent);
		
		JButton btnDeleteFile = new JButton("Delete File");
		btnDeleteFile.setBounds(606, 291, 168, 23);
		btnDeleteFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl3 = new ContentList(Program.this, CurDir, 3);
				ctl3.setVisible(true);
			}
		});
		btnDeleteFile.setForeground(Color.RED);
		btnDeleteFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnDeleteFile);
		
		JLabel lblNewLabel = new JLabel("Source: Folder + File");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 423, 272, 14);
		contentPane.add(lblNewLabel);
		
		textSource = new JTextField("");
		textSource.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textSource.setEditable(false);
		textSource.setColumns(10);
		textSource.setBackground(Color.WHITE);
		textSource.setBounds(10, 446, 477, 20);
		contentPane.add(textSource);
		
		JLabel lblDestinationFolderfile = new JLabel("Destination Folder (+File)");
		lblDestinationFolderfile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDestinationFolderfile.setBounds(10, 477, 272, 14);
		contentPane.add(lblDestinationFolderfile);
		
		textDestination = new JTextField("");
		textDestination.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textDestination.setEditable(false);
		textDestination.setColumns(10);
		textDestination.setBackground(Color.WHITE);
		textDestination.setBounds(10, 502, 477, 20);
		contentPane.add(textDestination);
		
		JLabel lblCopyFile = new JLabel("Copy File:");
		lblCopyFile.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCopyFile.setBounds(10, 286, 272, 14);
		contentPane.add(lblCopyFile);
		
		JLabel lblSelectThe = new JLabel("1. Select the source folder as the Current Dir");
		lblSelectThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectThe.setBounds(10, 300, 413, 14);
		contentPane.add(lblSelectThe);
		
		JLabel lblSelectThe_2 = new JLabel("2. Select the file from the folder, with Select File button");
		lblSelectThe_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectThe_2.setBounds(10, 325, 413, 14);
		contentPane.add(lblSelectThe_2);
		
		JLabel lblSelectThe_2_1 = new JLabel("3. Select the destionation folder for the current folder");
		lblSelectThe_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectThe_2_1.setBounds(10, 350, 413, 14);
		contentPane.add(lblSelectThe_2_1);
		
		JLabel lblSelectThe_2_1_1 = new JLabel("4. Press the Select button to select the current folder as the destination folder");
		lblSelectThe_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectThe_2_1_1.setBounds(10, 375, 460, 14);
		contentPane.add(lblSelectThe_2_1_1);
		
		JLabel lblSelectThe_2_1_1_1 = new JLabel("5. Press Copy File button to copy");
		lblSelectThe_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectThe_2_1_1_1.setBounds(10, 400, 460, 14);
		contentPane.add(lblSelectThe_2_1_1_1);
		
		JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl4 = new ContentList(Program.this,CurDir, 4);
				ctl4.setVisible(true);
				selectedFile = ctl4.getOutFile();
				sourceFile = new File(CurDir.getPath()+ separator + selectedFile);
				textSource.setText(sourceFile.getAbsolutePath());
			}
		});
		btnSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectFile.setBounds(512, 446, 168, 23);
		contentPane.add(btnSelectFile);
		
		JButton btnSelectDestFolder = new JButton("Select Dest. Folder");
		btnSelectDestFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destFile = new File(CurDir.getPath()+ separator + selectedFile);
				textDestination.setText(destFile.getAbsolutePath());
			}
		});
		btnSelectDestFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectDestFolder.setBounds(512, 502, 168, 23);
		contentPane.add(btnSelectDestFolder);
		
		JButton btnCopyFile = new JButton("Copy File");
		btnCopyFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textSource.getText().length() == 0)
					SM("No source file selected!",0);
				else if(textDestination.getText().length() == 0)
					SM("No destinationi folder selected!",0);
				else {
					copyFile(sourceFile, destFile);
					SM("File copied!",1);
				}
			}
		});
		btnCopyFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCopyFile.setBounds(685, 474, 89, 23);
		contentPane.add(btnCopyFile);
	}
	
	public void SM(String msg, int type){
		JOptionPane.showMessageDialog(Program.this, msg, "FileSystem Manager message",type);
	}
	
	public String RTF(JTextField jtf){
		return jtf.getText();
	}
	
	public void CreateFile(JTextArea jta, String fileName) {
		fileName = CurDir.getAbsolutePath()+separator+fileName;
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fileName));
			out.print(jta.getText());
			out.close();
			SM("Data is written to a file!",1);
		}catch (IOException ioe) {
			SM("CreateFile method: "+ioe.getMessage(),0);
		}
	}
	
	public void copyFile(File source, File dest) {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while((length = is.read(buffer)) > 0) {
				os.write(buffer,0,length);
			}
			is.close();
			os.close();
		} catch(Exception e) {SM("copyFile: "+e.getMessage(),0);}
		
	}
}
