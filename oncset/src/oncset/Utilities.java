package oncset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Utilities {
	
	public void Save(JTextArea jta) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream("Data.txt"));
			out.print(jta.getText());
			out.close();
			SM("Data is written to a file!",1);
		} catch (IOException ioe) {
			SM("Save method: "+ioe.getMessage(), 0);
			
		}
		
	}
	
	public void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program message", tipus);
	}
	
	public String Open () {
		String out = "";
		String sep = System.getProperty("line.separator");
		try {
			BufferedReader in = new BufferedReader(new FileReader("Data.txt"));
			String s=in.readLine();
			while(s!=null) {
				out += s +sep;
				s=in.readLine();
			}
			in.close();
			SM("Data read from file!", 1);
		} catch (IOException ioe) {
			SM("Open method: "+ioe.getMessage(), 0);
		}
		
		return out;
	}

}
