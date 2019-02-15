import java.io.*;
import java.util.Arrays;

public class EjemploStream4 {
	public static void main (String args[]) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("java", "HolaMundo4");
		pb.directory(new File("/home/rafmar/eclipse-workspace/Holamundo4/bin"));
		
		Process process = pb.start();
		OutputStream os = process.getOutputStream();
		
		// Nombre para el archivo
		os.write("asdf\n".getBytes());
		os.flush();
		
		InputStream is = process.getInputStream() ;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		
		System.out.println("Linea normal");
		while ((line = br.readLine()) != null ) {
			System.out.println(line);
		}
	}
}