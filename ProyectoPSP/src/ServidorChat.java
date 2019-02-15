import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ServidorChat {
	static final int MAXIMO = 10;
	
	public static void main (String args[]) throws IOException {
		int PUERTO = 44444;
		ServerSocket servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor Iniciado...");
		
		Socket tabla[] = new Socket[MAXIMO];
		ComunHilos comun = new ComunHilos(MAXIMO, 0, 0, tabla);
		while (comun.getCONEXIONES() < MAXIMO) {
			Socket socket = new Socket();
			socket = servidor.accept();
			
			comun.addTabla(socket, comun.getCONEXIONES());
			comun.setACTUALES(comun.getACTUALES() + 1);
			
			comun.setCONEXIONES(comun.getCONEXIONES() + 1);
			
			HiloServidorChat hilo = new HiloServidorChat(socket, comun);
			hilo.start();
		}
		servidor.close();
	}
}

class HiloServidorChat extends Thread {
	DataInputStream fentrada;
	Socket socket = null;
	ComunHilos comun;
	
	public HiloServidorChat (Socket s, ComunHilos comun) {
		this.socket = s;
		this.comun = comun;
		try {
			fentrada = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Error de E/S");
			e.printStackTrace();
		}
	}
	
	public void run () {
		System.out.println("Número de conexiones actuales: " + comun.getACTUALES());
		
		String texto = comun.getMensajes();
		enviarMensajesATodos(texto);
		
		while (true) {
			String cadena = "";
			
			try {
				cadena = fentrada.readUTF();
				
				if (cadena.trim().equals("*")) {
					comun.setACTUALES(comun.getACTUALES() - 1);
					System.out.println("Número de conexiones actuales: " + comun.getACTUALES());
					
					break;
				}
				
				comun.setMensajes(comun.getMensajes() + cadena + "\n");
				enviarMensajesATodos(comun.getMensajes());
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void enviarMensajesATodos (String texto) {
		int i;
		
		for (i = 0; i < comun.getCONEXIONES(); i++) {
			Socket s1 = comun.getElementoTabla(i);
			if (!s1.isClosed()) {
				try {
					DataOutputStream fsalida2 = new DataOutputStream(s1.getOutputStream());
					fsalida2.writeUTF(texto);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class ComunHilos {
	int CONEXIONES;
	int ACTUALES;
	int MAXIMO;
	Socket tabla[] = new Socket[MAXIMO];
	String mensajes;
	
	public ComunHilos (int maximo, int actuales, int conexiones, Socket[] tabla) {
		MAXIMO = maximo;
		ACTUALES = actuales;
		CONEXIONES = conexiones;
		this.tabla = tabla;
		mensajes = "";
	}
	
	public ComunHilos () { super(); }
	
	public int getMAXIMO() { return MAXIMO; }
	public void setMAXIMO(int maximo) { MAXIMO = maximo; }
	
	public int getCONEXIONES() { return CONEXIONES; }
	public synchronized void setCONEXIONES(int conexiones) { CONEXIONES = conexiones; }

	public int getACTUALES() { return ACTUALES; }
	public synchronized void setACTUALES(int actuales) { ACTUALES = actuales; }

	public String getMensajes() { return mensajes; }
	public synchronized void setMensajes(String mensajes) { this.mensajes = mensajes; }
	
	public synchronized void addTabla (Socket s, int i) { tabla[i] = s; }
	public Socket getElementoTabla (int i) { return tabla[i]; }
}
