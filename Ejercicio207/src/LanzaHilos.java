import java.util.Scanner;

public class LanzaHilos {
	public static void main(String[] args) {
		// declaramos dos hilos de la clase HiloEsperaNseg pasando como parámetros 3 y 5
		// segundos
		HiloEspera HiloEsperahilo1 = new HiloEspera(3);
		HiloEspera HiloEsperahilo2 = new HiloEspera(5);
		// definimos los nombres de los hilos utilizando el método setName() de la clase
		// Thread
		HiloEsperahilo1.setName("hilo 1");
		HiloEsperahilo2.setName("hilo 2");

		// inicializamos los dos hilos
		HiloEsperahilo1.start();
		HiloEsperahilo2.start();
		// hacemos que el hilo principal espera 4 segundos con el método sleep()
		try {
			System.out.println("Soy el hilo principal y voy a esperar 4 segundos");
			Thread.currentThread().sleep(4000);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrumpido. ");
			return;
		}
		System.out.println("Soy el hilo principal y he terminado");
	}
}
