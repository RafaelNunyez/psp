class Contador {
	public static int num = 0;
	
	public Contador (int num) {
		this.num = num;
	}
	
	public static void incrementar () {
		num = num + 1;
	}
	
	public static void decrementar () {
		num = num - 1;
	}
	
	public static void mostrarValor () {
		System.out.println("El valor actual del contador es: ");
	}
	
	public int valor () {
		return num;
	}
}

class HiloSumador extends Thread {
	public String nombre;
    public Contador contador;
    public int n;

    public HiloSumador() {}

    public HiloSumador(String nombre, Contador contador, int n) {
        this.nombre = nombre;
        setName(nombre);
        this.contador = contador;
        this.n = n;
    }
    
    public void run() {
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Contador.incrementar();
        }
        System.out.println("Al finalizar " + getName() + " el contador vale " + contador.valor());
    }
}

class HiloRestador extends Thread {public String nombre;
	public Contador contador;
	public int n;

	public HiloRestador() {
	}

	public HiloRestador(String nombre, Contador contador, int n) {
		this.nombre = nombre;
		setName(nombre);
		this.contador = contador;
		this.n = n;
	}

	public void run() {
		for (int i = 0; i < n; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Contador.decrementar();
		}
		System.out.println("Al finalizar " + getName() + " el contador vale " + contador.valor());
	}
}

public class LanzaHilos216 {
	public static void main (String[] args) {
		Contador contador = new Contador(100);
		
		HiloSumador h1 = new HiloSumador("HiloSumador", contador, 300);
		HiloRestador h2 = new HiloRestador("HiloRestador", contador, 300);
		
		contador.mostrarValor();
		System.out.println("Voy a lanzar los hilos...");
		h1.start();
		h2.start();
		
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) { e.printStackTrace(); }
		
		contador.mostrarValor();
	}
}
