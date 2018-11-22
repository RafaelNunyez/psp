public class HiloConParametros extends Thread {
	//Creamos una clase que hereda de thread y creamos una variable de la clase	
	private int veces;
	
	//Constructor con parametros, que recoge el hilo con un nombre y el número de iteraciones
	public HiloConParametros (String nombre) {
		//Se llama al constructor Thread pasando como parámetro nombre
		//Y al constructor de esta clase el número de veces
		
		//super(nombre);
		this.setName(nombre);
	}
	
	public void run () {
		System.out.println("Lanzando el Hilo " + getName() + ":");
		
		System.out.println("Creando Hilo " + getName());
		
		System.out.println("Finalizando Hilo " + getName() + ".");
	}
}
