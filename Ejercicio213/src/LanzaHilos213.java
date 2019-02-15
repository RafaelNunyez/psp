class HiloRecursivo extends Thread {
	public static int veces;
	
	public HiloRecursivo (int veces) {
		// El número de iteraciones recursivas que faltan por hacer
		this.veces = veces;
	}
	
	@Override
	public void run() {
		if (veces > 0) {
			this.setName("hilo " + veces);
			System.out.println("Soy el " + getName() + " y voy a llamar al hilo " + (veces - 1));
			HiloRecursivo hilo = new HiloRecursivo(veces - 1);
			hilo.start();
		} else if (veces == 0) {
			this.setName("Hilo " + veces);
			System.out.println("Soy el " + getName());
		}
	}
}

public class LanzaHilos213 {
	public static void main(String[] args) {
		HiloRecursivo hilo = new HiloRecursivo(10);
		hilo.start();
	}
}