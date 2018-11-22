
public class HiloEspera extends Thread {
	private int espera;

	public HiloEspera(int espera) {
		this.espera = espera*1000;
	}

	public void run() {
		System.out.println("Soy el " + getName() + " empezando.");
		System.out.println("Soy el " + getName() + " y voy a parar " + espera + " ms");
		try {
			sleep(espera);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrumpido.");
			return;
		}
		System.out.println(Thread.currentThread().getName() + " acabado.");
	}
}