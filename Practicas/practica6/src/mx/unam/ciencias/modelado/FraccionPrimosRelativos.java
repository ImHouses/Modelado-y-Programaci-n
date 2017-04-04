package mx.unam.ciencias.modelado;

import java.lang.Math;
import java.lang.Comparable;

/**
* <p>Clase para fracciones donde el numerador es primo relativo con el denominador.
* Las fracciones donde n y m son coprimos pueden sumarse.</p>
*/
public class FraccionPrimosRelativos implements Comparable<FraccionPrimosRelativos> {
	
	/* Numerador. */
	private int m;
	/* Denominador. */
	private int n;

	/* Constructor único. */
	public FraccionPrimosRelativos(int m, int n) {
		if (m == 0 && n == 1 || m == 1 && n == 0) {
			this.m = m;
			this.n = n;
			return;
		}
		this.m = m;
		this.n = n;
	}

	/**
	* Regresa el numerador.
	* @return el numerador.
	*/
	public int getM() {
		return this.m;
	}

	/**
	* Regreesa el denominador.
	* @return el denominador.
	*/
	public int getN() {
		return this.n;
	}

	/**
	*
	*/
	@Override
	public int compareTo(FraccionPrimosRelativos fpr) {
		double n1 = (double) this.m / this.n;
		double n2 = (double) fpr.m / fpr.n;
		if (n1 < n2)
			return -1;
		if (n2 < n1)
			return 1;
		return 0;
	}

	/**
	* Suma dos fracciones de primos relativos.
	* @param fpr La fracción que se va a sumar.
	*/
	public FraccionPrimosRelativos suma(FraccionPrimosRelativos fpr) {
		return new FraccionPrimosRelativos(this.m + fpr.m, this.n + fpr.n);
	}

	/**
	* Regresa una representación en cadena de la fracción.
	* @return una representación en cadena de la fracción.
	*/
	public String toString() {
		return String.format("\n%d\n__\n%d\n", this.m, this.n);
	}
}