import java.util.Scanner;

public class LanzaHilos {
	public static void main(String[] args) {
		HiloEspera HiloEsperahilo1 = new HiloEspera(3);
		HiloEspera HiloEsperahilo2 = new HiloEspera(5);
		HiloEsperahilo1.setName("hilo 1");
		HiloEsperahilo2.setName("hilo 2");

		HiloEsperahilo1.start();
		HiloEsperahilo2.start();
		try {
			System.out.println("Soy el hilo principal y voy a esperar a que esperen los hilos");
			HiloEsperahilo1.join();
			HiloEsperahilo2.join();
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrumpido. ");
			return;
		}
		System.out.println("Soy el hilo principal y he terminado");
}
}
