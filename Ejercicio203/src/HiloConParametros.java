public class HiloConParametros extends Thread {
	//Creamos una clase que hereda de thread y creamos una variable de la clase	
	private int veces;
	//Constructor con parametros, que recoge el hilo con un nombre y el número de iteraciones
	public HiloConParametros (String nombre, int veces) {
		//Se llama al constructor Thread pasando como parámetro nombre
		//Y al constructor de esta clase el número de veces
		
		//super(nombre);
		this.setName(nombre);
		
		this.veces = veces;
	}
	
	public void run () {
		for (int i = 1; i <= veces; i++) {
			System.out.println("El hilo " + getName() + " se ha ejecutado "	+ i + " veces.");
		}
	}
}
