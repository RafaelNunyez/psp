
public class HiloEspera extends Thread {
	// parámetro espera
	private int espera;

	// constructor de la clase HiloEsperaNseg
	public HiloEspera(int espera) {
		// recibe segundos (espera) y se multiplica por 1000 para pasarlo a milisegundos
		this.espera = espera * 1000;
	}

	public void run() {
		System.out.println("Soy el " + getName() + " empezando.");
		System.out.println("Soy el " + getName() + " y voy a parar en " + espera + "ms");
		try {
			sleep(espera);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrumpido.");
			return;
		}
		System.out.println(Thread.currentThread().getName() + " acabado.");
	}
}