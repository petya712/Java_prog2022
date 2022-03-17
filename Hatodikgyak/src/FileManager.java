import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
	
	public static EmpTM CsvReader(){
		Object emptmn[] = {"Jel","K�d","N�v","Sz�lid�","Lak�hely","Fizet�s"};
		EmpTM etm = new EmpTM(emptmn, 0);
		try{
			BufferedReader in  = new BufferedReader(new FileReader("adatok.txt"));
			String s=in.readLine();
			s=in.readLine();
			while(s!=null){
				String[] st = s.split(";");
				etm.addRow(new Object[]{false,st[0],st[1],st[2],st[3],st[4]});
				s=in.readLine();
			}
			in.close();
		}catch(IOException ioe){
			System.out.println("CsvReader: "+ioe.getMessage());
		}
		return etm;
	}

}
