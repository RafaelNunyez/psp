class HiloEspera extends Thread {
	private int espera;
	
	public HiloEspera(int espera) {
		// recibe segundos (espera) y se multiplica por 1000 para pasarlo a milisegundos
		this.espera = espera * 1000;
	}
	
	@Override
	public void run() {
		System.out.println("Soy el " + Thread.currentThread().getName() + " empezando.");
		
		if (this.isDaemon()) System.out.println(getName() + " es daemon");
		try {
			Thread.sleep(espera);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrumpido.");
			return;
		}
		System.out.println(Thread.currentThread().getName() + " acabado.");
	}
}

public class CreaHilos {
	public static void main(String args[]) {
		HiloEspera hilo1 = new HiloEspera(10);
		HiloEspera hilo2 = new HiloEspera(7);
		HiloEspera daemon = new HiloEspera(10);
		hilo1.setName("hilo 1");
		hilo2.setName("hilo 2");
		daemon.setName("daemon");
		hilo1.start();
		hilo2.start();
		daemon.setDaemon(true);
		daemon.start();
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrumpido. ");
			return;
		}
		hilo1.interrupt();
	}
}