public class LanzaHilos {
	public static void main (String args[]) {
		HiloConParametros hilo1 = new HiloConParametros("David", 2);
		HiloConParametros hilo2 = new HiloConParametros("Seve", 3);
		HiloConParametros hilo3 = new HiloConParametros("Rafael", 4);
		
		System.out.println("**Soy LanzaHilos y voy a lanzar tres hilos...**");
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		System.out.println("**LanzaHilos ha terminado su misión**");
	}
}
