package ora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Emp {

	private String nev;
	private int fiz;
	
	public Emp(String nev, int fiz){
		this.nev =nev;
		this.fiz=fiz;
	}
	
	public String getNev(){
		return nev;
	}
	
	public int getFiz(){
		return fiz;
	}
	public String toString(){
		return nev+": "+fiz;
	}
	
	public static void print(Emp[] t){
		System.out.println("");
		for (int i=0; i<t.length; i++)
			System.out.println(t[i]);
	}
	
	public static void sortNev(Emp[] t){
		int db = t.length;
		for(int i=0; i<db-1; i++)
			for(int j=i+1; j<db;j++)
			if(t[i].nev.compareTo(t[j].nev) >=0){
			Emp e = t[i];
			t[i] = t[j];
			t[j] = e;
		}
		print(t);
	}
	
	public static void sortFiz(Emp[] t){
		int db = t.length;
		for(int i=0; i<db-1; i++)
			for(int j=i+1; j<db; j++)
			if(t[i].fiz >= t[j].fiz){
			Emp e = t[i];
			t[i] = t[j];
			t[j] = e;
		}
		print(t);
	}
	
	public static void search(Emp[] t,String s){
		boolean b = false;
		for(int i =0; i<t.length ;i++)
			if(t[i].nev.equals(s)) b = true;
		System.out.println("");
		if (b) System.out.println(s+": létezik");
		else System.out.println(s+": nem létezik");
	}
	
	public static void searchFirstName(Emp[] t,String s ){
		System.out.println("");
		for(int i = 0; i<t.length; i++){
			boolean b = false;
			String[] n = nev[i].split(" ");
			for(int j = 1; j<n.length; j++)
				if(n[j].equals(s)) b = true;
			if(b) System.out.println(s+": "+nev[i]);
		}
	}
	
	public static void print(ArrayList<Emp> emp){
		System.out.println("");
		for(Emp e : emp) System.out.println(e);
	}
	
	public static void sortNev(ArrayList<Emp> emp){
		Collections.sort(emp, new Comparator<Emp>() {
			public int compare(Emp e1, Emp e2) {
				return e1.nev.compareTo(e2.nev);
			}
		});
		print(emp);
	}
	
	public static void sortFiz(ArrayList<Emp> emp){
		Collections.sort(emp, new Comparator<Emp>() {
			public int compare(Emp e1, Emp e2) {
				return e1.fiz - e2.fiz;
			}
		});
		print(emp);
	}
	
	public static void search(ArrayList<Emp> emp ,String s){
		boolean b = false;
		for(Emp e : emp)
			if(e.nev.contains(s)) b = true;
		
		System.out.println("");
		if(b) System.out.println(s+": létezik");
		else System.out.println(s+": nem létezik");
		
	}
}
