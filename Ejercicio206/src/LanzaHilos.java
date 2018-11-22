import java.util.Scanner;

public class LanzaHilos {
	public static void main (String args[]) {
		Scanner tcl = new Scanner(System.in);
		
		System.out.print("Dime cuantos hilos quieres crear: ");
		int veces = tcl.nextInt();
		tcl.close();
		
		HiloEspera[] hilos = new HiloEspera[veces];
		
		for (int i = 0; i < veces; i++) {
			hilos[i] = new HiloEspera(String.valueOf(i + 1));
			hilos[i].start();
		}
	}
}
