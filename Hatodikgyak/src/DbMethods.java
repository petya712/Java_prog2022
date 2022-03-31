import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbMethods {

	private Statement s = null;
	private Connection conn = null;
	private ResultSet rs = null;
	
	public void Reg(){
		try{
			Class.forName("org.sqlite.JDBC");
			SM("Sikeres driver reg!",1);
		}catch (ClassNotFoundException e){
			SM("Hib�s driver reg!"+e.getMessage(),0);
		}
	}
	public void SM(String msg, int tipus){
		JOptionPane.showMessageDialog(null, msg,"Program �zenet",tipus);
	}
	
	public void Connect(){
		try{
			String url = "jdbc:sqlite:C:/Users/szabo129/sqlite3/empdb";
			conn = DriverManager.getConnection(url);
			SM("Connection OK!",1);
		}catch (SQLException e ){
			SM("JBDC Connect: "+e.getMessage(),0);
		}
	}
	
	public void Disconnect(){
		try{
			conn.close();
			SM("Disconnection OK!",1);
		}catch(SQLException e) {
			SM(e.getMessage(),0);
			
		}
	}
	
	public EmpTM ReadAllData(){
		Object emptmn[] = {"Jel","K�d","N�v","Sz�lid��","Lak�hely","Fizet�s"};
		EmpTM etm = new EmpTM(emptmn,0);
		String nev = "", szid="",lak="";
		int kod=0,fiz=0;
		String sqlp="select kod,nev,szulido,lakohely,fizetes from emp";
		try{
			s = conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()){
				kod = rs.getInt("kod");
				nev = rs.getString("nev");
				szid = rs.getString("szulido");
				lak = rs.getString("lakohely");
				fiz = rs.getInt("fizetes");
				etm.addRow(new Object[]{false, kod, nev, szid, lak, fiz});
			}
			rs.close();
		}catch(SQLException e){SM(e.getMessage(),0);}
		return etm;
	}
	
	public void Insert(String kod, String nev, String szid, String lak, String fiz){
		String sqlp = "insert into emp values("+kod+", '"+nev+"', '"+szid+"', '"+lak+"', "+fiz+")";
		try{
			s = conn.createStatement();
			s.execute(sqlp);
			SM("insert OK!",1);
		}catch (SQLException e){
			SM("JDBC insert: "+e.getMessage(),0);
		}
	}
	
	public void DeleteData(String kod){
		String sqlp = "delete from emp where kod="+kod;
		try{
			s = conn.createStatement();
			s.execute(sqlp);
			SM("Delete OK!",1);
		}catch (SQLException e){
			SM("JDBC Delete: "+e.getMessage(),0);
		}
	}
}
