
public class HiloEspera extends Thread {
	public HiloEspera (String nombre) {
		//Se llama al constructor Thread pasando como parámetro nombre
		//Y al constructor de esta clase el número de veces
		
		//super(nombre);
		this.setName(nombre);
	}
	
	public void run() {
		System.out.println("Soy el " + Thread.currentThread().getName() + " empezando.");
		try {
			this.sleep(3000);
			// También se puede hacer así y lo aplicará al hilo actual.
			// Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println(getName() + " interrumpido.");
			return;
		}
		System.out.println(getName() + " acabado.");
	}
}