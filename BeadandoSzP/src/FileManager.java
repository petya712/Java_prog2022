import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


import javax.swing.JOptionPane;

public class FileManager {
	
	public static PlaTM CsvReader(){
		Object platmn[] = {"Jel","Kód","Név","Felfedezési_idő","Felfedező_neve","Távolsága_Földtől"};
		PlaTM ptm = new PlaTM(platmn, 0);
		try{
			BufferedReader in  = new BufferedReader(new FileReader("adatok.txt"));
			String s=in.readLine();
			s=in.readLine();
			while(s!=null){
				String[] st = s.split(";");
				ptm.addRow(new Object[]{false,st[0],st[1],st[2],st[3],st[4] });
				s=in.readLine();
			}
			in.close();
		}catch(IOException ioe){
			System.out.println("CsvReader: "+ioe.getMessage());
		}
		return ptm;
	}
	
	public static PlaTM StatReader(String kod, String nev){
		Object platmn[] = {"Jel","Kód","Név","Felfedezési_idő","Felfedező_neve","Távolsága_Földtől"};
		PlaTM ptm = new PlaTM(platmn, 0);
		try{
			BufferedReader in  = new BufferedReader(new FileReader("adatok.txt"));
			in.mark(1500);
			String s=in.readLine();
			s=in.readLine();
			
			while(s!=null){
				String[] st = s.split(";");
				ptm.addRow(new Object[]{false,st[0],st[1],st[2],st[3],st[4] });
				s=in.readLine();
			}
			
			
			in.reset();
			int lengthofptm = ptm.getRowCount(); //Elmentem a fájlban lévő sorok számát a már beolvasott táblából
			s=in.readLine();
			
			System.out.println("Kód: "+kod); //segítő
			System.out.println("Név: "+nev);//segítő
			ptm.setRowCount(0); 	//A táblát kiűríti, indul a keresett elemek bepakolása
			for(int i = 0;lengthofptm>i;i++) {
				if(s!=null) {
					
					s=in.readLine();
					System.out.println("\n"+s); //segítő
					String[] st = s.split(";");
					//Van egyező kód ,de nincs név megadva
					if(s!=null && (!kod.equals("")) && (nev.equals("")) && st[0].matches(kod)) {
						System.out.println("\nMEGTALÁLTAM! Név=null "+s); //segítő
						ptm.addRow(new Object[]{false,st[0],st[1],st[2],st[3],st[4] });
						//Van ilyet tartalmazó név ,de nincs kód megadva
					}else if(s!=null && (!nev.equals("")) && (kod.equals("")) && st[1].contains(nev)) {
						System.out.println("\nMEGTALÁLTAM! Kód=null "+s); //segítő
						ptm.addRow(new Object[]{false,st[0],st[1],st[2],st[3],st[4] });
						//Van kitöltött egyező kód és ilyet tartalmazó név is
					}else if(s!=null && (!nev.equals("")) && (!kod.equals("")) &&(( st[0].matches(kod)) || (st[1].contains(nev)))){
							System.out.println("\nMEGTALÁLTAM! Egyiksem=null "+s); //segítő
							ptm.addRow(new Object[]{false,st[0],st[1],st[2],st[3],st[4] });
						}
					
				}
			}
			in.close();
		}catch(IOException ioe){
			SM("FM.StatReader: "+ioe.getMessage(),0);
		}
		return ptm;
	}
	
	public static void BCount() {
		
		try {
			BufferedReader in  = new BufferedReader(new FileReader("adatok.txt"));
			in.mark(1500);
			String s=in.readLine();
			s=in.readLine();
			int darab=0;
			
			while(s!=null){
				darab=darab+1;
				
				s=in.readLine();
			}
			in.reset();
			SM("Általunk ismert bolygók száma: "+darab,1);
			in.close();
		}catch(IOException ioe) {
			SM("BCount: "+ioe.getMessage(),0);
		}
	}
	
	public static void Insert(String kod,String nev,String fid, String fnev, String tav) {
		String x=";";
		try {
			PrintStream out = new PrintStream(new FileOutputStream("adatok.txt",true));
			out.println(kod+x+nev+x+fid+x+fnev+x+tav);
			out.close();
			SM("Adatok kiírva!", 1);
			
			
		}catch (IOException ioe) {
			SM("Insert hiba: "+ioe.getMessage(),0);
		}
		
	}
	
	
	
	public static void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg,"Program üzenet",tipus);
	}
	
	public static void Insert(PlaTM ptm) {
		String x=";";
		try {
			PrintStream out = new PrintStream(new FileOutputStream("adatok.txt"));
			out.println("Kód;Név;Felfedezési_idő;Felfedező_neve;Távolsága_Földtől");
			for(int i = 0; i < ptm.getRowCount(); i++) {
				String kod=ptm.getValueAt(i, 1).toString();
				String nev=ptm.getValueAt(i, 2).toString();
				String fid=ptm.getValueAt(i, 3).toString();
				String fnev=ptm.getValueAt(i, 4).toString();
				String tav=ptm.getValueAt(i, 5).toString();
				out.println(kod+x+nev+x+fid+x+fnev+x+tav);
			}
			out.close();
		}catch (IOException ioe) {
			SM("Insert hiba: "+ioe.getMessage(),0);
		}
	}

}