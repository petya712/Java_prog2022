package ora;

import java.util.ArrayList;

public class EmpPrg {
	private static ArrayList<Emp> emp = new ArrayList<Emp>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		emp.add(new Emp("Kis B�la,", 255000));
		emp.add(new Emp("Kis Iz�,", 555000));
		emp.add(new Emp("Kis Hoz�,", 155000));
		emp.add(new Emp("Kis Valami,", 355000));
		
		Emp.print(emp);
		Emp.sortNev(emp);
		Emp.sortFiz(emp);
		Emp.search(emp, "Kis B�la");
	}

}
