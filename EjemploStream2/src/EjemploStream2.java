import java.io.*;
import java.util.Arrays;

public class EjemploStream2 {
	public static void main (String args[]) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("ls", "-z");
		
		Process process = pb.start();
		InputStream is = process.getErrorStream() ;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		System.out.println("Salida del proceso " + Arrays.toString(args) + ":" );
		
		System.out.println("Linea de Error");
		while ((line = br.readLine()) != null ) {
			System.out.println(line);
		}
		
		System.out.println();
		
		InputStream is1 = process.getInputStream() ;
		InputStreamReader isr1 = new InputStreamReader(is1);
		BufferedReader br1 = new BufferedReader(isr1);
		
		System.out.println("Linea normal");
		while ((line = br1.readLine()) != null ) {
			System.out.println(line);
		}
	}
}