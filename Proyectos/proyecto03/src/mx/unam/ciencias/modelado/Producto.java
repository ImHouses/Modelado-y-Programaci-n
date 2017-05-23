package mx.unam.ciencias.modelado;

import java.io.Serializable;

/**
*Esta clase simula un producto en el inventario de una tienda, 
*contiene informacion sobre el codigo, nombre, cantidad y precio 
*del producto, asi como el producto anterior y el siguiente 
*en la lista
*/
public class Producto implements Serializable{
	private String codigo;
	private String nombre;
	private int cantidad;
	private double precio;
	private Producto anterior;
	private Producto siguiente;

	/**
	* Construye un nuevo producto a partir del codigo, nombre, cantidad y precio 
	* dados como parametros, asigna null como producto siguiente y como anterior.
	* @param codigo el codigo del producto
	* @param nombre el nombre del producto
	* @param cantidad la cantidad de productos de este tipo en el inventario
	* @param precio el precio del producto
	*/
	public Producto(String codigo, String nombre, double precio, int cantidad){
		this.codigo = codigo;
		this.nombre = nombre;
      	this.precio = precio;
		this.cantidad = cantidad;
		anterior = null;
		siguiente = null;
	}

	/**
	* Este metodo define la forma de representar al producto con una cadena de texto
	* @return la representacion de este ptoducto como cadena de texto
	*/
	public String toString(){
		return codigo +
				": \t" +
				nombre +
				", \t" +
				cantidad +
				" unidades a \t$" +
				precio +
				" c/u";
	}

	/**
	* Método de acceso al atributo codigo
	* @return el codigo del producto
	*/
	public String daCodigo(){
		return codigo;
	}

	/**
	* Método de acceso al atributo nombre
	* @return el nombre del producto
	*/
	public String daNombre(){
		return nombre;
	}

	/**
	* Método de acceso al atributo cantidad
	* @return la cantidad de productos de este tipo en el inventario
	*/
	public int daCantidad(){
		return cantidad;
	}

	/**
	* Método de acceso al atributo precio
	* @return el precio del producto
	*/
	public double daPrecio(){
		return precio;
	}

	/**
	* Método de acceso al atributo anterior
	* @return el producto anterior en la lista
	*/
	public Producto daAnterior(){
		return anterior;
	}

	/**
	* Método de acceso al atributo siguiente
	* @return el producto siguiente en la lista
	*/
	public Producto daSiguiente(){
		return siguiente;
	}

	/**
	* Método modificador para el atributo anterior
	* @param ant el nuevo producto anterior en la lista
	*/
	public void ponAnterior(Producto ant){
		anterior = ant;
	}

	/**
	* Método modificador para el atributo siguiente
	* @param sig el nuevo producto siguiente en la lista
	*/
	public void ponSiguiente(Producto sig){
		siguiente = sig;
	}

	/**
	* Método modificador para el atributo nombre
	* @param nombre el nuevo nombre del producto
	*/
	public void ponNombre(String nombre){
		this.nombre = nombre;
	}

	/**
	* Método modificador para el atributo cantidad
	* @param cantidad la nueva cantidad de productos de este tipo en el inventario
	*/
	public void ponCantidad(int cantidad){
		this.cantidad = cantidad;
	}

	/**
	*metodo modificador para el atributo precio
	*@param precio el nuevo precio del producto
	*/
	public void ponPrecio(double precio){
		this.precio = precio;
	}

}
