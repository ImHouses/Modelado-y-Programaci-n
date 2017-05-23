package mx.unam.ciencias.modelado;

import java.awt.Color;
import javax.swing.JOptionPane; 
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField; 
import javax.swing.JPasswordField; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Menu extends JFrame   {

   private static final String nombreArchivo = "almacen.ser";
   ArrayList<Producto> almacen = new ArrayList<Producto>();
   JTabbedPane panelFichas = new JTabbedPane(); 
   

   public Menu() {
      super("Almacen de abarrotes ");
      almacen = leerProductos();
      ponPestaniaAgregar();
      ponPestaniaVerTodo();
      ponPestaniaBusqueda();
      ponPestaniaBorrar();
      ponPestaniaAltaCliente();
      ponPestaniaSalir();
      add( panelFichas ); // agrega objeto JTabbedPane al marco
   } // fin del constructor de MarcoJTabbedPane


   public void ponPestaniaAgregar() {

      Icon imagen = new ImageIcon(  "carrito.jpg"  );
      JLabel estampa = new JLabel(imagen);      
      //Etiquetas de los mensajes
      JLabel label0 = new JLabel("Rellena la siguiente información");
      JLabel label1 = new JLabel("Código de barras");
      JLabel label2 = new JLabel("Nombre del producto");
      JLabel label3 = new JLabel("Precio del producto");
      JLabel label4 = new JLabel("Cantidad de piezas");

      //Áreas de texto
      JTextField codigoBarrasProducto = new JTextField();
      JTextField nombreProducto = new JTextField();
      JTextField precioProducto = new JTextField();
      JTextField cantidadProducto = new JTextField();

      //Botón de envío
      JButton boton_registro = new JButton("Aceptar");
     
      JPanel panel1 = new JPanel(new GridLayout(1, 2));
      JPanel panel_1 = new JPanel(new GridLayout(6, 2, -100, 30));
      panel_1.add(label0);
      panel_1.add(new JLabel(""));
      panel_1.add(label1);
      panel_1.add(codigoBarrasProducto);
      panel_1.add(label2);
      panel_1.add(nombreProducto);
      panel_1.add(label3);
      panel_1.add(precioProducto);
      panel_1.add(label4);
      panel_1.add(cantidadProducto);
      panel_1.add(new JLabel(""));
      panel_1.add(boton_registro);
      
      panel1.add(panel_1);
      panel1.add(estampa);

      
      boton_registro.addActionListener(new ActionListener() {             
         public void actionPerformed(ActionEvent evento) {                                   
            String codigoB=codigoBarrasProducto.getText();
            String nombre =nombreProducto.getText();
            double precio = Double.parseDouble(precioProducto.getText());
            int cantidad = Integer.parseInt(cantidadProducto.getText());
            boolean se_pudo = agregaNuevoProducto(codigoB, nombre, precio, cantidad);
            if(se_pudo) {
               JOptionPane
                    .showMessageDialog(null,
                            "producto agregado exitosamente ",
                            "exito",
                            JOptionPane.INFORMATION_MESSAGE);
            } else {
               JOptionPane
                    .showMessageDialog(null,
                            "ocurrio un error ",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
            }
         }                       
      });                                     
      panelFichas.addTab("Agregar Artículo", panel1);
   }

   public void ponPestaniaVerTodo() {
      // PESTAÑA DE VER Todo
      JLabel todo = new JLabel();
      JPanel panel = new JPanel(new GridLayout(1,2)); 
      JButton boton_ver = new JButton("Actualizar inventario y Ver Todo");
      panel.add(boton_ver);
      
      boton_ver.addActionListener(new ActionListener() {             
         public void actionPerformed(ActionEvent evento) {                                   
           String respuesta="<html>";
            for(int i=0; i<almacen.size(); i++) {
               respuesta+="<br>"+almacen.get(i);
            } 
            respuesta+="</html>";    
            todo.setText(respuesta); 
         }                       
      });     
       
      panel.add(todo);
      panelFichas.addTab( "Ver todo", null, panel);
   }

   public void ponPestaniaBusqueda() {

      // PESTAÑA DE BUSQUEDA
      Icon imagen = new ImageIcon( "busqueda.jpg"  );
      JLabel estampa = new JLabel(imagen); 

      JPanel panel = new JPanel(new GridLayout(1,2)); 
      JPanel panel_1 = new JPanel(new GridLayout(3,2,-100,30));

      //Etiquetas de los mensajes
      JLabel label_b0 = new JLabel("Rellena la siguiente información");
      JLabel label_b1 = new JLabel("Palabra clave");
   
      //Áreas de texto
      JTextField search = new JTextField();

      //botón
      JButton boton_busqueda = new JButton("Buscar");

      panel_1.add(label_b0);
      panel_1.add(new JLabel(""));
      panel_1.add(label_b1);
      panel_1.add(search);
      panel_1.add(new JLabel(""));
      panel_1.add(boton_busqueda);
      panel.add(panel_1 ); 
      panel.add(estampa);  

      boton_busqueda.addActionListener(new ActionListener() {             
         public void actionPerformed(ActionEvent evento) {                                   
            String busqueda=search.getText();
            String respuesta=buscaProductos(busqueda);
            JOptionPane.showMessageDialog(null, respuesta, "exito", JOptionPane.INFORMATION_MESSAGE);
         }                       
      });  
      panelFichas.addTab( "Búsqueda de artículos", panel);

   }

   public void ponPestaniaBorrar() {
        // PESTAÑA DE BORRADO
        Icon imagen = new ImageIcon("borrar.png");
        JLabel estampa = new JLabel(imagen); 
        JPanel panel = new JPanel(new GridLayout(1,2)); 
        JPanel panel_1 = new JPanel(new GridLayout(3,2,-80,30));
        //panel.setBackground(Color.YELLOW);
        Color mi_color = new Color(120,100,40);
        panel.setBackground(mi_color);
        panel_1.setBackground(Color.YELLOW);

        //Etiquetas de los mensajes
        JLabel label_b0 = new JLabel("Borra un producto");
        JLabel label_b1 = new JLabel("Código de barras");
        //Áreas de texto
        JTextField borrar = new JTextField();
        //botón
        JButton boton_borrar = new JButton("Borrar");
        panel_1.add(label_b0);
        panel_1.add(new JLabel(""));
        panel_1.add(label_b1);
        panel_1.add(borrar);
        panel_1.add(new JLabel(""));
        panel_1.add(boton_borrar);
        panel.add(panel_1 ); 
        panel.add(estampa);  
        boton_borrar.addActionListener(new ActionListener() {             
        public void actionPerformed(ActionEvent evento) {                                   
            String delete = borrar.getText();
            boolean borrado = borraProducto(delete);
            if(borrado) {
                JOptionPane.showMessageDialog(null,
                    "Artículo borrado","exito",
                    JOptionPane.INFORMATION_MESSAGE);
           } else {
                JOptionPane.showMessageDialog(null,
                    "No se encontró un artículo con ese código de barras",
                    "error",
                    JOptionPane.ERROR_MESSAGE);
           } 
         }                       
      });  
      panelFichas.addTab("Borra artículos", panel);
   }

   public void ponPestaniaAltaCliente() {
        JPanel panel = new JPanel(new GridLayout(7,2));
        JTextField campoNombre = new JTextField();
        JTextField campoApellidoP = new JTextField();
        JTextField campoApellidoM = new JTextField();
        JTextField campoLogin = new JTextField();
        JTextField campoPass = new JTextField();
        JButton botonRegistro = new JButton("Registrar nuevo cliente");
        botonRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String apellidoPaterno = campoApellidoP.getText();
                String apellidoMaterno = campoApellidoM.getText();
                String login = campoLogin.getText();
                String pass = campoPass.getText();
                if (nombre.isEmpty() ||
                    apellidoPaterno.isEmpty() ||
                    apellidoMaterno.isEmpty() ||
                    login.isEmpty() ||
                    pass.isEmpty() ||
                    pass.length() < 6)
                    JOptionPane.showMessageDialog(null,
                        "Algún campo es vacío, pruebe otra vez.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                else 
                    JOptionPane.showMessageDialog(null,
                        "Registro",
                        "OK",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(new JLabel("Nombre"));
        panel.add(campoNombre);
        panel.add(new JLabel("Apellido paterno"));
        panel.add(campoApellidoP);
        panel.add(new JLabel("Apellido materno"));
        panel.add(campoApellidoM);
        panel.add(new JLabel("Login"));
        panel.add(campoLogin);
        panel.add(new JLabel("Contraseña"));
        panel.add(campoPass);
        panel.add(botonRegistro);
        panelFichas.addTab("Dar de alta cliente", panel);
   }


   public void ponPestaniaSalir() {
        // PESTAÑA DE SALIR
        Icon imagen = new ImageIcon("salir.jpg");
        JLabel estampa = new JLabel(imagen); 
        JPanel panel = new JPanel(new GridLayout(1,2)); // crea el segundo panel
        JButton boton_salir = new JButton("Salir");
        panel.add(boton_salir);
        panel.add(estampa);
        boton_salir.addActionListener(new ActionListener() {             
        public void actionPerformed(ActionEvent evento) {                                   
            grabar();
            System.exit(1);
         }                       
      });     
        panelFichas.addTab( "Cerrar sesión", panel); 
   }


   /**
    * Este metodo muestra la ventana de inicio de sesion 
    * y ajusta propiedades como sus dimesiones y 
    * la funcion del boton que cierra la ventana
    */
   public static void muestraInterfaz() {
        Menu m = new Menu();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(850, 400);
        m.setVisible(true);
        m.setResizable(false);
   }

   public boolean agregaNuevoProducto(String cb, String n, double p, int c) {
        Producto nuevo = new Producto(cb, n, p, c);
        almacen.add(nuevo);
        return true;
   }

   /**
   * Busca un pro
   *
   */
   public String buscaProductos(String cadena) {
        String res="<html>";
        for(int i=0; i<almacen.size(); i++) {
            String cad=(almacen.get(i)).toString();
            if(cad.contains(cadena)) {
                res+="<br>"+cad;
            }
        }
        return res;
   }

   /**
   * Borra un producto dado un código de barras.
   * @param codigo El código del producto a borrar.
   */
   public boolean borraProducto(String codigo) {
        boolean borrado=false;
        for(int i = 0; i < almacen.size(); i++) {
            String cp = almacen.get(i).daCodigo();
            if(cp.equals(codigo)) {
                almacen.remove(i);
                borrado = true;
        }
      }
      return borrado;
   }


   /**
    * Abre el archivo si este existe y carga la informacion al almacén.
    */
   @SuppressWarnings("unchecked")
   public ArrayList<Producto> leerProductos(){
      try{
        ObjectInputStream lector =
                new ObjectInputStream(new FileInputStream(nombreArchivo));
        ArrayList<Producto> productos = (ArrayList<Producto>) lector.readObject();
        lector.close();
        this.almacen=productos;
      }
      catch(IOException e){
            System.out.println("Lectura fallida: "+e);
      }
      catch(ClassNotFoundException e){
            System.out.println("Lectura fallida: "+e);
      }
      return almacen;
   }


   /**   
    * Envía a un archivo la informacion contenida en la agenda.
    */
   public void grabar(){
      try{
        ObjectOutputStream a= new ObjectOutputStream(new FileOutputStream(nombreArchivo));
        a.writeObject(almacen);
        a.close();
      }
      catch(NotSerializableException e){
        System.out.println("Error en la grabacion: " + e + "Objeto no serializable");
      }
      catch(IOException e){
        System.out.println("Error en la grabacion: " + e);
      }
   }
}

