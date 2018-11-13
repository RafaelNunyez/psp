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
		System.out.println(getName() + ":");
		
		int aux;
		int n1 = 0;
		int n2 = 1;
		int cont = 0;
		
		System.out.print(n1 + " ");
		System.out.print(n2 + " ");
		
		while (cont <= veces) {
			aux = n1;
            n1 = n2;
            n2 = aux + n1;
            cont++;

            System.out.print(n2 + " ");
		}
	}
}
