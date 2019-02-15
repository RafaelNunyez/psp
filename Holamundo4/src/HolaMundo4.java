import java.util.Scanner;
import java.io.*;

class HolaMundo4{
    public static void main(String [] args) {
    	String nombre;
        
    	Scanner tcl = new Scanner (System.in);
    	System.out.print("Dime tu nombre");
    	nombre = tcl.nextLine();
    	try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("fichero.txt")));
			for(int i = 0; i < 20; i++){
				System.out.println("Hola " + nombre);
				bw.write("Hola " + nombre);
				bw.newLine();
			}
			bw.close();
		} catch (IOException errorDeFichero) {
			System.out.println("Ha habido problemas: " + errorDeFichero.getMessage() );
		}
    }
}