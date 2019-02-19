import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

import javax.swing.*;

public class ClienteChat extends JFrame implements ActionListener, Runnable {
    private static final long serialVersionUID = 1L;
    Socket socket = null;
    
    // streams
    DataInputStream fentrada; //PARA LEER LOS MENSAJES
    DataOutputStream fsalida; //PARA ESCRIBIR MENSAJES
    
    String nombre;
    static JTextField mensaje = new JTextField();
    
    private JScrollPane scrollpane1;
    static JTextArea textarea1;
    JButton botonEnviar = new JButton("Enviar");
    JButton botonSalir = new JButton("Salir");
    JTextArea usuariosConectados = new JTextArea("USUARIOS CONECTADOS: ");
    boolean repetir = true;
    
    // constructor
    public ClienteChat(Socket s, String nombre) {
        super(" CONEXIÓN DEL CLIENTE CHAT: " + nombre);
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);
        
        //Añadido
        mensaje.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    botonEnviar.doClick();
                    }
                }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
        
        textarea1 = new JTextArea();
        scrollpane1 = new JScrollPane(textarea1);
        scrollpane1.setBounds(10, 50, 400, 300);
        add(scrollpane1);
        
        botonEnviar.setBounds(420, 10, 100, 30);
        add(botonEnviar);
        botonSalir.setBounds(420, 50, 100, 30);
        add(botonSalir);
        
        usuariosConectados.setBounds(420, 90, 100, 260);
        add(usuariosConectados);
        
        textarea1.setEditable(false);
        botonEnviar.addActionListener(this);
        botonSalir.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        socket = s;
        this.nombre = nombre;
        try {
            fentrada = new DataInputStream(socket.getInputStream());
            fsalida = new DataOutputStream(socket.getOutputStream());
            String texto = " > Entra en el Chat ... " + nombre;
            fsalida.writeUTF(texto);
        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
            System.exit(0);
        }
    }// fin constructor
    
    // acción cuando pulsamos botones
    @Override
    public void actionPerformed(ActionEvent e) {
        //Declaramos una variable para guardar la hora y los minutos (arreglando que sólo muestre 1 número si los minutos son de 01 a 09)
        String fechaYHora = String.valueOf(LocalDateTime.now().getHour()) + ":" + String.format("%02d", LocalDateTime.now().getMinute());

        if (e.getSource() == botonEnviar) { // SE PULSA ENVIAR
            if (mensaje.getText().trim().length() == 0)
                return;
            //Se guarda la hora, el nombre y el mensaje
            String texto = fechaYHora + " " + nombre + "> " + mensaje.getText();

            try {
                mensaje.setText("");
                fsalida.writeUTF(texto);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == botonSalir) {
            //Implementación botón confirmación para salir o no del chat
            int dialogButton = JOptionPane.YES_NO_OPTION;// SE PULSA BOTON SALIR
            int dialogResult = JOptionPane.showConfirmDialog(this, "¿Desea salir del chat?", "Confirmar cierre", dialogButton);

            if (dialogResult == 0) {
                String texto = " > Abandona el Chat ... " + nombre;
                
                try {
                    fsalida.writeUTF(texto);
                    fsalida.writeUTF("*");
                    repetir = false; //para salir del bucle
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else { }
        }
    } // FIN acción de los botones
    
    @Override
    public void run() {
        String texto = "";
        while (repetir) {
            try {
                texto = fentrada.readUTF(); //leer mensajes
                textarea1.setText(texto); //visualizarlos
            } catch (IOException e) {
                // este error sale cuando el servidor se cierra
                JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" +
                e.getMessage(), "<<MENSAJE DE ERROR:2>>", JOptionPane.ERROR_MESSAGE);
                repetir = false; //salir del bucle
            }
        } // fin while
        
        try {
            socket.close(); // cerrar socket
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// fin run
    
    public static void main(String args[]) {
        int puerto = 44444;
        Socket s = null;
        
        //Añadido variable IP que pide la IP a conectarse en vez de tener una IP fija
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick: ");
        String ipServidor = JOptionPane.showInputDialog("Introduce la IP del servidor: ");
        /*String ipFila1 = "192.168.26.127";
        String ipFila2 = "192.168.26.127";
        String ipFila3 = "192.168.26.149";*/
        
        if (nombre.trim().length() == 0) {
            System.out.println("El nombre está vacío...");
            return;
        }
        
        if (nombre.equals("admin") || nombre.equals("administrador")) {
            System.out.println("El usuario admin/administrador ya existe.");
            return;
        }
        
        try {
            //Se conecta a la IP del servidor recogida con la variable ipServidor
            s = new Socket("192.168.26.127", puerto);
            ClienteChat cliente = new ClienteChat(s, nombre);
            cliente.setBounds(0, 0, 540, 400);
            cliente.setVisible(true);
            new Thread(cliente).start(); //lanzar hilo cliente
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" +
                e.getMessage(), "<<MENSAJE DE ERROR:1>>",
                JOptionPane.ERROR_MESSAGE);
        }
    } //main

}// ..ClienteChat
