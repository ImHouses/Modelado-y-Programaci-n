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
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;

public class MenuCliente extends JFrame {

	private static final String archivoClientes = "clientes.ser";
	private static final String archivoProductos = "almacen.ser";
	private ArrayList<Cliente> clientes;
	private ArrayList<Producto> productos;
	private ArrayList<Producto> carrito;
	private HashMap<String, Producto> hashProductos;
	private HashMap<String, Producto> hashCarrito;
	private HashMap<String, Producto> hashCarritoProductos;
	private JTabbedPane tabs = new JTabbedPane();
	private String usuario;
	private DefaultListModel<String> listaPagar;
	JComboBox comboBoxCarrito;

	public MenuCliente(String usuario) {
		super("Carrito de cliente");
		this.usuario = usuario;
		this.clientes = leeClientes();
		this.hashProductos = new HashMap<>();
		this.hashCarrito = new HashMap<>();
		this.hashCarritoProductos = new HashMap<>();
		this.carrito = new ArrayList<>();
		this.productos = leeProductos();
		this.listaPagar = new DefaultListModel<>();
		ponPestaniaComprar();
		ponPestaniaVer();
		ponPestaniaQuitar();
		ponPestaniaPagar();
		ponPestaniaSalir();
		add(tabs);
	}

	public static void muestraMenu(String usuario) {
		MenuCliente m = new MenuCliente(usuario);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setSize(1000, 400);
		m.setVisible(true);
		m.setResizable(false);
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Cliente> leeClientes() {
		ArrayList<Cliente> c = new ArrayList<>();
		try {
			ObjectInputStream reader = 
					new ObjectInputStream(new FileInputStream(archivoClientes));
			c = (ArrayList<Cliente>) reader.readObject();
			reader.close();
		} catch(NotSerializableException nse) {
			nse.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return c;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Producto> leeProductos() {
		ArrayList<Producto> p = new ArrayList<>();
		try {
			ObjectInputStream reader =
					new ObjectInputStream(new FileInputStream(archivoProductos));
			p = (ArrayList<Producto>) reader.readObject();
			for (Producto prod : p) {
				hashProductos.put(prod.toString(), prod);
			}
			reader.close();
		} catch(NotSerializableException nse) {
			nse.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();	
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	private void escribeProductos() {
		try {
			ObjectOutputStream writer
					= new ObjectOutputStream(new FileOutputStream(archivoProductos));
			writer.writeObject(productos);
			writer.close();
		} catch(NotSerializableException nse) {
			nse.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void ponPestaniaComprar() {
		JPanel panel = new JPanel(new GridLayout(4,1));
		JComboBox comboBoxProductos = new JComboBox(getListaComboBox(productos).toArray());
		JLabel label = new JLabel("Unidades");
		JTextField unidades = new JTextField();
		JButton botonAgregar = new JButton("Agregar al carrito");
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto p = hashProductos.get(comboBoxProductos.getSelectedItem());
				if (!isNumeric(unidades.getText())) {
					JOptionPane.showMessageDialog(null,
							"Cantidad inválida",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (p.daCantidad() < Integer.parseInt(unidades.getText())) {
					JOptionPane.showMessageDialog(null,
							"No hay suficientes productos",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Producto agregado = new Producto(p.daCodigo(),
													p.daNombre(),
													p.daPrecio(),
													Integer.parseInt(unidades.getText()));
					System.out.println("Elemento agregado: " + agregado);
					hashCarrito.put(agregado.toString(), agregado);
					hashCarritoProductos.put(agregado.toString(), p);
					carrito.add(agregado);
					JOptionPane.showMessageDialog(null,
                        	"Añadido correctamente",
                        	"OK",
                        	JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panel.add(comboBoxProductos);
		panel.add(label);
		panel.add(unidades);
		panel.add(botonAgregar);
		tabs.add("Compra", panel);
	}

	/**
	* Nos dice si la cadena dada es un número.
	* @return <code>true</code> si la cadena es un número, <code>false</code>
	* en otro caso.
	*/
	public static boolean isNumeric(String str) {
  		return str.matches("\\d+");
	}

	private void ponPestaniaVer() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		JButton botonVer = new JButton("Ver carrito");
		JList<String> listaArticulos = new JList<>();
		botonVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (carrito.size() == 0) {
					String[] a = {"Carrito vacío"};
					listaArticulos.setListData(a);
				} else {
					listaArticulos.setListData(getListaCarrito(carrito.size()));
				}	
			}
		});
		panel.add(botonVer);
		panel.add(listaArticulos);
		tabs.add("Ver carrito", panel);
	}

	@SuppressWarnings("unchecked")
	private void ponPestaniaQuitar() {
		JPanel panel = new JPanel(new GridLayout(3,1));
		JButton botonActualizar = new JButton("Actualizar lista");
		JButton botonQuitar = new JButton("Quitar articulo");
		comboBoxCarrito = new JComboBox(getListaComboBox(carrito).toArray());
		System.out.println(getListaComboBox(carrito).toString());
		botonQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (carrito.size() == 0) {
					JOptionPane.showMessageDialog(null,
						"Carrito vacío",
						"INFO",
						JOptionPane.INFORMATION_MESSAGE);
				}
				Producto removido = hashCarrito.get(comboBoxCarrito.getSelectedItem());
				carrito.remove(removido);
				comboBoxCarrito.removeItem(removido.toString());
				System.out.println(carrito);
				JOptionPane.showMessageDialog(null,
						removido.toString() + "Exitosamente removido",
						"OK",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxCarrito.removeAllItems();
				for (Producto p : carrito) {
					comboBoxCarrito.addItem(p.toString());
				}
			}
		});
		panel.add(botonActualizar);
		panel.add(comboBoxCarrito);
		panel.add(botonQuitar);
		tabs.add("Quitar producto", panel);
	}

	@SuppressWarnings("unchecked")
	private void ponPestaniaPagar() {
		JPanel panel = new JPanel(new GridLayout(4,1));
		JLabel total = new JLabel();
		JButton botonPagar = new JButton("Pagar");
		JButton botonActualizar = new JButton("Ver total");
		JList<String> listaArticulos = new JList<>(listaPagar);
		if (carrito.size() == 0) {
			String[] a = {"Carrito vacío"};
			listaArticulos.setListData(a);
		} else {
			actualizaModeloPagar();
			listaArticulos.setModel(listaPagar);
		}
		botonPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (carrito.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"El carrito está vacío",
							"INFO",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				for (Producto p : carrito) {
					Producto removido = hashCarritoProductos.get(p.toString());
					if (removido.daCantidad() - p.daCantidad() == 0) {
						productos.remove(removido);
					} else {
						productos.remove(removido);
						removido = new Producto(removido.daCodigo(),
												removido.daNombre(),
												removido.daPrecio(),
												removido.daCantidad() - p.daCantidad());
						productos.add(removido);
					}
				}
				carrito.clear();
				escribeProductos();
				JOptionPane.showMessageDialog(null,
						"Compra exitosa",
						"INFO",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaModeloPagar();
				listaArticulos.setModel(listaPagar);
				total.setText("Total: $" + new Double(getTotal()).toString());
			}
		});
		panel.add(botonActualizar);
		panel.add(listaArticulos);
		panel.add(total);
		panel.add(botonPagar);
		tabs.add("Pagar", panel);
	}

	private void ponPestaniaSalir() {
		Icon imagen = new ImageIcon("salir.jpg"); 
        JPanel panel = new JPanel(new GridLayout(1,2)); // crea el segundo panel
        JButton boton_salir = new JButton("Salir");
        panel.add(boton_salir);
        boton_salir.addActionListener(new ActionListener() {             
        	public void actionPerformed(ActionEvent evento) {                                   
        	escribeProductos();
            System.exit(1);
         	}                       
      	});     
        tabs.addTab( "Cerrar sesión", panel); 
	}

	private double getTotal() {
		double total = 0;
		for (Producto p : carrito) {
			total += p.daPrecio() * p.daCantidad();
		}
		return total;
	}

	private void actualizaModeloPagar() {
		listaPagar.clear();
		for (Producto p : carrito) {
			listaPagar.addElement(p.toString());
		}
	}

	private String[] getListaCarrito(int cantidad) {
		String[] productos = new String[cantidad];
		for(int i = 0; i < carrito.size(); i++) {
			productos[i] = carrito.get(i).toString();
		}
		return productos;
	}

	private ArrayList<String> getListaComboBox(ArrayList<Producto> a) {
		ArrayList<String> l = new ArrayList<>();
		for (Producto p : a) {
			l.add(p.toString());
		}
		return l;
	}

}