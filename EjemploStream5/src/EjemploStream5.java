import java.io.*;

public class EjemploStream5 {
	public static void main (String args[]) throws IOException {
		//1
		ProcessBuilder pb1 = new ProcessBuilder("ls", "-la");
		//2
		ProcessBuilder pb2 = new ProcessBuilder("tr", "d", "D");
		pb1.directory(new File("/home/rafmar"));
		
		//ls -la
		//3
		Process process1 = pb1.start();
		
		//tr d D
		Process process2 = pb2.start();
		//4
		OutputStream os2 = process2.getOutputStream();
		
		//5
		InputStream is1 = process1.getInputStream();
		InputStreamReader isr1 = new InputStreamReader(is1);
		BufferedReader br1 = new BufferedReader(isr1);		
		
		String line1;
		String line2;
		
		System.out.println("Linea normal");
		while ((line1 = br1.readLine()) != null ) {
			os2.write(line1.getBytes());
		}
		
		InputStream is2 = process2.getInputStream();
		InputStreamReader isr2 = new InputStreamReader(is2);
		BufferedReader br2 = new BufferedReader(isr2);
	}
}