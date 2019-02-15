class Reloj {
	boolean tic;

	public Reloj() {
		this.tic = true;
	}

	public synchronized void tic() {
		while (!tic) {
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("TIC");
			tic = false;
			notify();
		}
	}

	public synchronized void tac() {
		while (tic) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("TAC");
			tic = true;
			notify();
		}
	}
}

class HiloTic extends Thread {
	private Reloj reloj;
	private int veces;

	public HiloTic(Reloj reloj, String nombre, int veces) {
		super(nombre);
		this.reloj = reloj;
		this.veces = veces;
	}

	public void run() {
		int vez = 0;
		while (vez < veces) {
			reloj.tac();
			vez++;
		}
	}
}

class HiloTac extends Thread {
	private Reloj reloj;
	private int veces;

	public HiloTac(Reloj reloj, String nombre, int veces) {
		super(nombre);
		this.reloj = reloj;
		this.veces = veces;
	}

	public void run() {
		int vez = 0;
		while (vez < veces) {
			reloj.tac();
			vez++;
		}
	}
}

public class LanzaHilos221 {
	public static void main(String[] args) {
		Reloj reloj = new Reloj();
		HiloTic tic = new HiloTic(reloj, "TIC", 10);
		HiloTac tac = new HiloTac(reloj, "TAC", 10);
		tic.start();
		tac.start();

	}
}