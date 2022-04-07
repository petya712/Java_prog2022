import javax.swing.table.DefaultTableModel;

public class ListTM extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	public ListTM (Object fildNames[], int rows){
		super(fildNames,rows);
	}
	
	public boolean isCellEditable(int row, int col){
		if (col == 0){return true;}
		return false;
	}
	
	public Class<?> getColumnClass(int index){
		if (index == 0 || index == 2) return(Boolean.class);
		return(String.class);
	}

}
