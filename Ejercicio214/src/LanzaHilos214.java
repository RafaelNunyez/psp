public class LanzaHilos214 {
	public static void main (String args[]) {
		// Crea los hilos con el parámetro nombre
		HiloPrioridad1 h1 = new HiloPrioridad1("Hilo1");
		HiloPrioridad1 h2 = new HiloPrioridad1("Hilo2");
		HiloPrioridad1 h3 = new HiloPrioridad1("Hilo3");
		// Asigna la prioridad a cada uno de los hilos
		h1.setPriority(Thread.NORM_PRIORITY);
		h2.setPriority(Thread.MAX_PRIORITY);
		h3.setPriority(Thread.MIN_PRIORITY);
		// Ejecuta los hilos
		h1.start();
		h2.start();
		h3.start();
		// El hilo principal espera tres segundos
		try {
			Thread.sleep(3000);
		} catch (Exception e) {}
		// Detenemos los hilos invocando al método pararHilo
		h1.pararhilo();
		h2.pararhilo();
		h3.pararhilo();
		// Mostramos el valor del contador de cada uno de los hilos
		System.out.println("h1 (Prioridad normal): " + h1.getContador());
		System.out.println("h2 (Prioridad máxima): " + h2.getContador());
		System.out.println("h3 (Prioridad mínima): " + h3.getContador());
	}
}

class HiloPrioridad1 extends Thread {
	// Creamos variables internas al hilo
	private int c = 0;
	private boolean stophilo = false;
	// Constructor de la clase admite un parámetro de cadena de texto
	public HiloPrioridad1 (String nombre) {
		// Se usará para dar nombre al hilo creado, invocando al constructor de la clase superior
		super(nombre);
	}
	// Creamos el método getContador que nos devolverá el valor del contador
	public int getContador () {
		return c;
	}
	// Creamos el método pararHilo para detener el hilo
	public void pararhilo () {
		stophilo = true;
	}
	// Método que ejecutará al lanzar el hilo
	public void run () {
		// Mientras no esté parado el hilo, esperará 2ms
		while (!stophilo) {
			try {
				Thread.sleep(2);;
			} catch (Exception e) {}
			// Incrementa la variable interna contador
			c++;
		}
		// Al salir del bucle indica que el hilo finaliza
		System.out.println("Fin hilo " + this.getName());
	}
}