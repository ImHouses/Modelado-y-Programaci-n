package mx.unam.ciencias.modelado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
   
   JPanel panel;
   JButton boton_envio;
   JLabel usuario_label;
   JLabel password_label;
   JTextField usuario;
   JTextField password;

   /**
    * Este metodo constructor construye y acomoda los elementos de la interfaz 
    * como botones y campos de texto
    */
   public Login()  {
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
   public void actionPerformed(ActionEvent ae)  {
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
}
