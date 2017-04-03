package mx.unam.ciencias.modelado;

public class Practica6 {

	public static void main(String[] args) {
		for(String s : args) {
			String[] f = s.split(",");
			int m = Integer.parseInt(f[0]);
			int n = Integer.parseInt(f[1]);
			System.out.println(ManejadorSternBrocot.procesaEntrada(m,n));
		}
	}
}