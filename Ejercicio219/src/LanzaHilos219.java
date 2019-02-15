import java.util.concurrent.Semaphore;

class Contador {
	public static int num = 0;

	public static void incrementar() {
		num = num + 1;
	}

	public static void decrementar() {
		num = num - 1;
	}
}

class HiloSumador extends Thread {
	public Contador contador;
	private int cuenta;
	private Semaphore sem;

	HiloSumador (int hasta, Semaphore sem) {
	    this.cuenta = hasta ;
	    this.sem = sem;
    }

	public void run() {
		for (int i = 0; i < cuenta; i++) {
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			contador.incrementar();
			sem.release();
		}
	}
}

class HiloRestador extends Thread {
	public Contador contador;
	private int cuenta;
	private Semaphore sem;

	HiloRestador (int hasta, Semaphore sem) {
	    this.cuenta = hasta ;
	    this.sem = sem;
    }

	public void run() {
		for (int i = 0; i < cuenta; i++) {
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			contador.decrementar();
			sem.release();
		}
	}
}

public class LanzaHilos219 {
	private static Semaphore semaphore = new Semaphore(1);
	
	public static void main(String[] args) {
		Contador contador = new Contador();

		HiloSumador h1 = new HiloSumador(104, semaphore);
		HiloRestador h2 = new HiloRestador(10, semaphore);
		HiloSumador h3 = new HiloSumador(73, semaphore);
		HiloRestador h4 = new HiloRestador(134, semaphore);
		HiloSumador h5 = new HiloSumador(234, semaphore);
		HiloRestador h6 = new HiloRestador(78, semaphore);

		System.out.println("Voy a lanzar los hilos...");
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
		h6.start();

		try {
			h1.join();
			h2.join();
			h3.join();
			h4.join();
			h5.join();
			h6.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(contador.num);
	}
}
