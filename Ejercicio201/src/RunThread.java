public class RunThread {
	public static void main(String args[]) {
		HelloThread hilo = new HelloThread(); // Se crea un nuevo hilo de ejecución
		hilo.start();
		// se arranca el hilo creado anteriormente
		System.out.println("Hola desde el hilo principal!");
		System.out.println("Proceso acabando");
	}
}