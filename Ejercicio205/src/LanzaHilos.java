import java.util.Scanner;

public class LanzaHilos {
	public static void main (String args[]) {
		Scanner tcl = new Scanner(System.in);
		
		System.out.print("Dime cuantos hilos quieres crear: ");
		int veces = tcl.nextInt();
		tcl.close();
		
		HiloConParametros[] hilos = new HiloConParametros[veces];
		
		for (int i = 0; i < veces; i++) {
			hilos[i] = new HiloConParametros(String.valueOf(i));
			hilos[i].start();
		}
	}
}
