public class LanzaHilos_ProductorConsumidor {
	public static void main(String[] args) {
		Almacen almacen=new Almacen();
		Productor p1=new Productor(almacen,1);
		Consumidor c1=new Consumidor(almacen,1);
		p1.start();
		c1.start();
		System.out.println("***Fin de LanzaHilos***");

	}
}

class Almacen {
	private int numeroEnAlmacen;
	private boolean numeroAlmacenado = false;

	public int consume() {
		if (numeroAlmacenado) {
			numeroAlmacenado = false;
			return numeroEnAlmacen;
		}
		return -1;
	}

	public void produce(int valor) {
		numeroEnAlmacen = valor;
		numeroAlmacenado = true;
	}
}

class Productor extends Thread {
	private Almacen almacen;
	private int id;

	public Productor(Almacen a, int n) {
		almacen = a;
		id = n;
		
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			almacen.produce(i);
			System.out.println("Productor " + id + " produce el valor " + i);

			/*try {
				sleep(500);
			} catch (InterruptedException e) {
			}*/

		}
	}
}

class Consumidor extends Thread {
	private Almacen almacen;
	private int id;
	
	public Consumidor(Almacen a, int n) {
		almacen = a;
		this.id = n;
	}
	
	public void run() {
		int valor=0;
		for (int i = 0; i < 3; i++) {
			valor=almacen.consume();
			System.out.println("Consumidor " + id + " en iteración "+ i+" consume el valor " + valor);

		}
	}
}