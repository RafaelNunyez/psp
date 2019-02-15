class Bienvenida {
	boolean clase_comenzada;

	public Bienvenida() {
		this.clase_comenzada = false;
	}

// Hasta que el profesor no salude no empieza la clase,
// por lo que los alumnos esperan con un wait
	public synchronized void saludarProfesor() {
		try {
			while (clase_comenzada == false) {
				wait();
			}
			System.out.println(Thread.currentThread().getName() + " Buenos dias, profesor.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

// Cuando el profesor saluda avisa a los alumnos con notifyAll de su llegada
	public synchronized void llegadaProfesor(String nombre) {
		System.out.println("Buenos días a todos. Soy el profesor " + nombre);
		this.clase_comenzada = true;
		notifyAll();
	}
}

class Alumno extends Thread {
	Bienvenida saludo;

	public Alumno(Bienvenida bienvenida) {
		this.saludo = bienvenida;
	}

	public void run() {
		System.out.println("Alumno " + Thread.currentThread().getName() + " llegó.");
		try {
			Thread.sleep(1000);
			saludo.saludarProfesor();
		} catch (InterruptedException e) {
			System.err.println("Thread alumno interrumpido!");
			System.exit(-1);
		}
	}
}

class Profesor extends Thread {
	String nombre;
	Bienvenida saludo;

	public Profesor(String nombre, Bienvenida bienvenida) {
		this.nombre = nombre;
		this.saludo = bienvenida;
	}

	public void run() {
		System.out.println(nombre + " llegó.");
		try {
			Thread.sleep(1000);
			saludo.llegadaProfesor(nombre);
		} catch (InterruptedException e) {
			System.err.println("Thread profesor interrumpido!");
			System.exit(-1);
		}
	}
}

public class ComienzoClase {
	public static void main(String[] args) {
// Objeto compartido
		Bienvenida b = new Bienvenida();
		int n_alumnos = 10;
		for (int i = 0; i < n_alumnos; i++) {
			Alumno alumno = new Alumno(b);
			alumno.setName("alumno-" + i);
			alumno.start();
		}
		Profesor profesor = new Profesor("Manolo Gómez", b);
		profesor.start();
	}
}