package ora;

public class Alkalmazott {
	private static String[] nev = new String[4];
	private static int[] fiz = new int[4];
	public static void main(String[] args){

		
		nev[0]="Kis Béla";
		fiz[0]= 250000;
		nev[1]="Jó Zoli Béla";
		fiz[1]= 240000;
		nev[2]="Valami Peti";
		fiz[2]= 3401000;
		nev[3]="Kis Andras";
		fiz[3]= 500000;
		
		sortNev();
		sortFiz();
		print();
		search("Kis Béla");
		search("Nagy Béla");
		searchFirstName("Béla");
	}
	
	public static void print(){
		System.out.println("");
		for(int i=0; i<4; i++)
			System.out.println(nev[i]+": "+fiz[i]);
	}
	
	public static void sortNev(){
		for(int i =0; i<3; i++)
			for(int j =i+1; j<4; j++)
				if(nev[i].compareTo(nev[j])>=0){
					String s = nev[i];
					nev[i] = nev[j];
					nev[j] = s;
					int f = fiz[i];
					fiz[i] = fiz[j];
					fiz[j] = f;
				}
	}
	
	public static void sortFiz(){
		for(int i =0; i<3; i++)
			for(int j =i+1; j<4; j++)
				if(fiz[i] >= fiz[j]){
					String s = nev[i];
					nev[i] = nev[j];
					nev[j] = s;
					int f = fiz[i];
					fiz[i] = fiz[j];
					fiz[j] = f;
				}
	}
	
	public static void search(String s){
		boolean b = false;
		for(int i =0; i<4 ;i++)
			if(nev[i].equals(s)) b = true;
		System.out.println("");
		if (b) System.out.println(s+": létezik");
		else System.out.println(s+": nem létezik");
	}

	public static void searchFirstName(String s){
		System.out.println("");
		for(int i = 0; i<4; i++){
			boolean b = false;
			String[] n = nev[i].split(" ");
			for(int j = 1; j<n.length; j++)
				if(n[j].equals(s)) b = true;
			if(b) System.out.println(s+": "+nev[i]);
		}
	}
}
