package mx.unam.ciencias.modelado;

public class ManejadorSternBrocot {

	private static ArbolSternBrocot asb = new ArbolSternBrocot();


	public static String procesaEntrada(int m, int n) {
		if (!sonPrimosRelativos(m,n))
			return String.format("%d y %d NO SON PRIMOS RELATIVOS.",m,n);
		if (m == 1 && n == 1)
			return "NO HAY UNA RUTA PUES ES EL NÚMERO BASE";
		FraccionPrimosRelativos f = new FraccionPrimosRelativos(m,n);
		asb.agrega(f);
		String ruta = asb.getRuta();
		asb.limpiaRuta();
		return ruta;
	}

	/**
	* Calcula el máximo común divisor de dos números usando el algoritmo de 
	* Euclides.
	* @param x Primer número.
	* @param y Segundo número.
	* @return el máximo común divisor de dos números.
	*/
	private static int mcd(int x, int y) {
		int maximo = Math.max(x,y);
		int minimo = Math.min(x,y);
		int residuo = 1;
		int mcd = 0;
		while (residuo > 0) {
			if (maximo % minimo == 0) {
				mcd = minimo;
				break;
			}
			residuo = maximo % minimo;
			maximo = minimo;
			minimo = residuo;
		}
		return mcd;
	}

	/**
	* Nos dice si m y n son primos relativos.
	* @param m El numerador
	* @param n El denominador
	* @return <code>true</code> si son primos relativos, <code>false</code> 
	* en otro caso.
	*/
	private static boolean sonPrimosRelativos(int m, int n) {
		return mcd(m,n) == 1;
	}
}