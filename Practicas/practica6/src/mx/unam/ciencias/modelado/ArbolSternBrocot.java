package mx.unam.ciencias.modelado;

import java.util.LinkedList;


/**
* <p>Clase para árboles de Stern-Brocot, los cuales permiten obtener todos los
* 	números racionales n/m tales que n y m son primos relativos.</p>
* <p>La clase nos provee de un método para obtener la ruta una vez que se ha 
*	agregado un número racional de la forma n/m tal que n y m son primos
*  	relativos.</p> 
*/
public class ArbolSternBrocot 
	extends ArbolBinarioOrdenado<FraccionPrimosRelativos> {

	/* La base izquierda de las fracciones. */
	private FraccionPrimosRelativos baseIzquierda;

	/* La base derecha de las fracciones. */
	private FraccionPrimosRelativos baseDerecha;

	/* La ruta de agregación. */
	private String ruta;

	/* Constructor. */
	public ArbolSternBrocot() {
		this.baseIzquierda = new FraccionPrimosRelativos(0,1);	
		this.baseDerecha = new FraccionPrimosRelativos(1,0);
		raiz = nuevoVertice(this.baseIzquierda.suma(this.baseDerecha));
		this.ruta = "";
	}

	/**
	* Agrega una nueva fracción y graba la ruta para encontrarla.
	* @param fraccion La fracción que se agregará.
	*/
	@Override
	public void agrega(FraccionPrimosRelativos fraccion) {
		if (fraccion == null)
			throw new IllegalArgumentException();
		if (fraccion.getN() == 0 && fraccion.getM() == 0)
			throw new IllegalArgumentException("n y m no son coprimos.");
		FraccionPrimosRelativos bi = this.baseIzquierda;
		FraccionPrimosRelativos bd = this.baseDerecha;
		Vertice v = raiz;
		while (v.elemento.compareTo(fraccion) != 0) {
			if (v.elemento.compareTo(fraccion) < 0) {
				bi = v.elemento;
				//System.out.println("Camino derecho: " + v.elemento);
				if (v == raiz)
					v.derecho = nuevoVertice(v.elemento.suma(bd));
				else if (!esIzq(v))
						v.derecho = nuevoVertice(v.elemento.suma(bd));
					 else v.derecho = nuevoVertice(v.elemento.suma(v.padre.elemento));
				v.derecho.padre = v;
				v = v.derecho;
				ruta += "R";
				elementos++;
			} else {
				bd = v.elemento;
				//System.out.println("Camino izquierdo: " + v.elemento);
				if (v == raiz)
					v.izquierdo = nuevoVertice(v.elemento.suma(bi));
				else if (esIzq(v)) 
						v.izquierdo = nuevoVertice(v.elemento.suma(bi));
					 else v.izquierdo = nuevoVertice(v.elemento.suma(v.padre.elemento));
				v.izquierdo.padre = v;
				v = v.izquierdo;
				ruta += "L";
				elementos++;
			}
		}
		raiz.izquierdo = null;
		raiz.derecho = null;
	}

	/**
	* Regresa el abuelo del vértice.
	* @param El vértice del que necesitamos el abuelo.
	* @return El abuelo del vértice.
	*/
	private Vertice getAbuelo(Vertice v) {
		return v.padre.padre;
	}

	/**
	* Nos dice si el vértice es izquierdo.
	* @return <code>true</code> si el vértice es izquierdo, <code>false</code>
	* si es derecho.
	*/
	private boolean esIzq(Vertice v) {
		return (v.padre.izquierdo == v) ? true : false;
	}

	/**
	* Regresa la ruta.
	* @return la ruta en cadena.
	*/
	public String getRuta() {
		return this.ruta;
	}

	/**
	* Limpia la ruta para múltiples ejecuciones del árbol.
	*/
	public void limpiaRuta() {
		this.ruta = "";
	}
}