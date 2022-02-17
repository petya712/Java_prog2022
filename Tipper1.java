package random;
import java.util.Random;
import java.util.Scanner;



public class Tipper1 {
	
	static Scanner sc = new Scanner(System.in);
	static int db=0;
	static int rndNum =0;
	static boolean success = false;
	static int limit = 100;
	static int min = 0;
	static int max = limit;
	
	public static void inform(){
		System.out.println("A program general egy szamot 0 es "+limit+" kozott, ezt kell kitalalni!");
		System.out.println("Tippelj es kapsz informaciot a tippedrol!");
	}

	public static int randomNumber(){
		Random rand = new Random();
		return rand.nextInt(limit+1);
		
	}
	
	public static String readTip(){
		System.out.println("Kerem a tippet: ");
		String s =sc.nextLine();
		return s;
		
	}
	
	public static boolean check(String s){
		boolean out = true;
		if(s.length()==0){
			System.out.println("Nem adtal meg adatot!");
			out = false;
		}
		if(out){
			try{
				int x=Integer.valueOf(s);
			}catch (NumberFormatException nfe){
				System.out.println("Hibas a beirt adat.");
				out = false;
			}
		}
		return out;
		
	}
	
	public static int StoInt (String s){
		int x =-1;
		try{
			x=Integer.valueOf(s);
		}catch (NumberFormatException nfe){
			System.out.println("StoInt: "+nfe.getMessage());
		}
		return x;
	}
	
	public static boolean evaluate(String s){
		boolean ok = false;
		String mS = "";
		int tipp = StoInt(s);
		db++;
		if(rndNum == tipp){
			mS ="Eltalaltad!\nTippek szama: "+db;
			ok = true;
		}
		if(rndNum < tipp){
			mS="A szam kisebb a tipptol!";
			if(tipp <= max) max = tipp;
		}
		if(rndNum > tipp){
			mS="A szam nagyobb a tipptol!";
			if(tipp >= min) min = tipp;
		}
		if (tipp > max) mS="A tippelt szam nagyobb, mint a mar ismerheto legnagyobb ertek!";
		
		if(tipp < min) mS="A tippelt szam kisebb, mint a mar ismerheto legkissebb ertek!";
		
		if(tipp <0)	mS="A tippelt szam kisebb mint az also hatar!";
		if(tipp > limit) mS="A tippelt szam nagyobb mint a felso hatar!";
		System.out.println(mS);
		return ok;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//hazi hogy controller classba kiirni a methodusokat!
		
		inform();
rndNum = randomNumber();
while (!success){
	String tippS =readTip();
	if (check(tippS)){
		success = evaluate(tippS);
		
	}else db++;
}
	}

}
