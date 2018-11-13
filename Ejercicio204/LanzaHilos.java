import java.util.*;

public class LanzaHilos {
	public static void main (String args[]) {
		Scanner tcl = new Scanner(System.in);
		
		System.out.print("Dime cuantos números quieres: ");
		int veces = tcl.nextInt();
		tcl.close();
		
		HiloConParametros hilo1 = new HiloConParametros("Fibonacci", veces);
		
		hilo1.start();
	}
}
