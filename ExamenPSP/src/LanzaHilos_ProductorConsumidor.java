public class LanzaHilos_ProductorConsumidor {
	public static void main(String[] args) {
		Almacen almacen = new Almacen();
		// PREGUNTA 5
		Productor p1 = new Productor(almacen, 1, 4);
		Consumidor c1 = new Consumidor(almacen, 1, 7);
		p1.start();
		c1.start();
		// PREGUNTA 3
		// Llamamos al método join() con cada uno de los hilos para que los hilos eviten que el hilo principal pueda terminar de ejecutarse
		// hasta que no terminen p1 y c1
		try {
			p1.join();
			// PREGUNTA 10
			//c1.join();
			
				c1.stop();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("***Fin de LanzaHilos***");
	}
}

class Almacen {
	private int numeroEnAlmacen;
	private boolean numeroAlmacenado = false;

	// PREGUNTA 6
	public synchronized int consume() {
		// PREGUNTA 6
		while (!numeroAlmacenado) {
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (numeroAlmacenado) {
			numeroAlmacenado = false;
			return numeroEnAlmacen;
		}
		// PREGUNTA 6
		notify();
		
		return -1;
	}

	// PREGUNTA 6
	public synchronized void produce(int valor) {
		// PREGUNTA 6
		while (numeroAlmacenado) {
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// PREGUNTA 8
		System.out.println(Thread.currentThread().getName() + " produce el valor " + valor);
		numeroEnAlmacen = valor;
		numeroAlmacenado = true;
		// PREGUNTA 6
		notifyAll();
	}
}

class Productor extends Thread {
	private Almacen almacen;
	// PREGUNTA 5
	private int iteraciones;

	// PREGUNTA 5
	public Productor(Almacen a, int n, int iteraciones) {
		// PREGUNTA 4
		// Modificamos el constructor llamando al constructor del método Thread que asigna un nombre al hilo para poder obtenerlo con getName()
		// En este caso, nos hará falta el String "Productor " y sumarle el valor n, java se encargará de la conversión
		super("Productor " + n);
		almacen = a;
		this.iteraciones = iteraciones;
	}

	public void run() {
		// PREGUNTA 5
		for (int i = 0; i < iteraciones; i++) {
			almacen.produce(i);
			// PREGUNTA 4
			// Cambiamos el valor del println al establecido en la pregunta
			
			// PREGUNTA 8
			// Se tiene que comentar esta línea de abajo y ponerla en el método produce() de Almacen
			//System.out.println(getName() + " produce el valor " + i);

			try {
				sleep(500);
			} catch (InterruptedException e) {
			}

		}
	}
}

class Consumidor extends Thread {
	private Almacen almacen;
	private int id;
	// PREGUNTA 5
	private int iteraciones;
	
	// PREGUNTA 5
	public Consumidor(Almacen a, int n, int iteraciones) {
		almacen = a;
		this.id = n;
		this.iteraciones = iteraciones;
	}
	
	public void run() {
		int valor = 0;
		// PREGUNTA 5
		for (int i = 0; i < iteraciones; i++) {
			valor = almacen.consume();
			System.out.println("Consumidor " + id + " en iteración " + i + " consume el valor " + valor);

		}
	}
}
