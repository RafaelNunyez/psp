public class CreaHilos {
	public static void main(String args[]) {
		//Creamos los hilos
		HiloEspera hilo1 = new HiloEspera();
		HiloEspera hilo2 = new HiloEspera();
		//Añadimos el nombre usando el método setName() del la clase Thread
		hilo1.setName("hilo 1");
		hilo2.setName("hilo 2");
		//Iniciamos los hilos usando el método start()
		hilo1.start();
		hilo2.start();
		try {
			//El hilo principal esperará 5 segundos
			Thread.currentThread().sleep(5000);
		//En caso de que el hilo principal lanzara una excepción, sería capturada
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrumpido. ");
			return;
		}
		//Interrumpimos el hilo1
		hilo1.interrupt();
	}
}

class HiloEspera extends Thread {
	@Override
	public void run() {
		//Mostramos el nombre del hilo actual
		System.out.println("Soy el " + getName() + " empezando.");
		//El hilo, al poder interrumpido mientras duerme, puede lanzar excepción 
		try {
			//Indicamos el tiempo de espera
			sleep(10000);
		//Capturamos la excepción y el hilo termina gracias al return
		} catch (InterruptedException e) {
			System.out.println(getName() + " interrumpido.");
			return;
		}
		//Mostramos el nombre del hilo terminado
		System.out.println(getName() + " acabado.");
	}
}
