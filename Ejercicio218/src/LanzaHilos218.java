class Contador {
	public static int num = 0;
	
	public Contador (int num) {
		this.num = num;
	}
	
	public void incrementar () {
		num = num + 1;
	}
	
	public void decrementar () {
		num = num - 1;
	}
	
	public void mostrarValor () {
		System.out.println("El valor actual del contador es: " + num);
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
    	synchronized (contador) {
    		for (int i = 0; i < n; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador.incrementar();
            }
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
		synchronized (contador) {
			for (int i = 0; i < n; i++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				contador.decrementar();
			}
		}
		System.out.println("Al finalizar " + getName() + " el contador vale " + contador.valor());
	}
}

public class LanzaHilos218 {
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