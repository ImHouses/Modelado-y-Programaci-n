package mx.unam.ciencias.modelado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Login extends JFrame implements ActionListener {
   
   JPanel panel;
   JButton boton_envio;
   JLabel usuario_label;
   JLabel password_label;
   JTextField usuario;
   JTextField password;
   JComboBox<String> opcionesCombobox;

   /**
	* Este metodo constructor construye y acomoda los elementos de la interfaz 
	* como botones y campos de texto
	*/
   public Login()  {
		String[] opciones = {"Usuario", "Administrador"};
		opcionesCombobox = new JComboBox<>(opciones);
		usuario_label = new JLabel();
		usuario_label.setText("Nombre de usuario:");
		usuario = new JTextField(10);
		password_label = new JLabel();
		password_label.setText("Password:");
		password = new JPasswordField(10);
		boton_envio = new JButton("Acceder");
		panel = new JPanel(new GridLayout(3,2));
		panel.add(usuario_label);
		panel.add(usuario);
		panel.add(password_label);
		panel.add(password);
		panel.add(boton_envio);
		panel.add(opcionesCombobox);
		add(panel);
		boton_envio.addActionListener(this);
		setTitle("Iniciar sesión Administrador");
   }
 
   /**
   * Este metodo detecta y procesa las peticiones de inicio de sesion, 
   * verifica los datos y si el nombre de usuario y password son validos 
   * procede a mostrar la interfaz principal del programa, 
   * en otro caso muestra una mensaje de error. 
   * @param ae el evento de peticion de inicio de sesion
   */
   public void actionPerformed(ActionEvent ae) {
		if (opcionesCombobox.getSelectedItem().equals("Usuario"))
			if (buscaUsuario(usuario.getText(), password.getText()) != null) {
        MenuCliente.muestraMenu(usuario.getText());
      } else {
        JOptionPane
                    .showMessageDialog(this,
                            "Usuario o password incorrectos o no se encuentra usuario",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
      }
		else if (opcionesCombobox.getSelectedItem().equals("Administrador"))
			inicioAdmin();
   }

   private void inicioAdmin() {
   		String value1 = usuario.getText();
		String value2 = password.getText();
		if (value1.equals("admin") && value2.equals("pass")) {
            Menu.muestraInterfaz();
            JOptionPane
                    .showMessageDialog(this,
                            "BIENVENIDO ADMINISTRADOR",
                            "ACCESO",
                            JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane
                    .showMessageDialog(this,
                            "Usuario o password incorrectos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
      }
   }

   @SuppressWarnings("unchecked")
   public Cliente buscaUsuario(String login, String pass) {
      try {
      ObjectInputStream input = new ObjectInputStream(new FileInputStream("clientes.ser"));
      ArrayList<Cliente> c = (ArrayList<Cliente>) input.readObject();
      for (Cliente cliente : c) {
        if (cliente.getLogin().equals(login) && cliente.getPassword().equals(pass)) {
          return cliente;
        }
      }
        } catch(Exception e) {
          e.printStackTrace();
        }
      return null;
   }
}
