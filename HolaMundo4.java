import java.util.Scanner;
import java.io.*;

class HolaMundo4{
    public static void main(String [] args) {
    	int num=0;
    	String nombre;
        
    	Scanner tcl = new Scanner (System.in);
    	System.out.println("Dime tu nombre");
    	nombre = tcl.nextLine();
    	while(num < 20) {
    		System.out.println("Hola " + nombre);
    		num = num + 1;     
    		try {
    			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("fichero.txt")));
    			for(int i = 0; i < 20; i++){

    				bw.write("Hola " + nombre);;
    				bw.newLine();
    			}
    			bw.close();
    		}
    		catch (IOException errorDeFichero) {
    			System.out.println("Ha habido problemas: " + errorDeFichero.getMessage() );
    		}
    	}
    }
}