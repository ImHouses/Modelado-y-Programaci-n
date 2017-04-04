package mx.unam.ciencias.modelado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Practica6 {

	public static void main(String[] args) {
		try {
			long tiempoInicial = System.nanoTime();
			File archivo = new File("entrada.txt");
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			LinkedList<String> lineas = getLineas(lector);
			StringBuilder salida = new StringBuilder();
			for (String linea : lineas) {
				try {
				String[] datos = linea.split("\\s");
				int m = Integer.parseInt(datos[0]);
				int n = Integer.parseInt(datos[1]);
				if (m == 1 && n == 1)
					continue;
				salida.append(ManejadorSternBrocot.procesaEntrada(m,n) + "\n");
				} catch(ArrayIndexOutOfBoundsException aiobe) {
					continue;
				}
			}
			File s = new File("salida.txt");
			BufferedWriter escritor = new BufferedWriter(new FileWriter(s));
			escritor.write(salida.toString());
			escritor.close();
			lector.close();
			long tiempoFinal = System.nanoTime() - tiempoInicial;
			System.out.printf("Trabajo terminado en %2.9f segundos.\n",
								(tiempoFinal/1000000000.0));
			System.out.println("OK");
		} catch(FileNotFoundException fnfe) {
			System.err.println("ERROR: Archivo 'entrada.txt' no encontrado.");
			System.exit(1);
		} catch(IOException ioe) {
			System.err.println("ERROR: Ocurrió un error de entrada o salida.");
		}
		
	}

	private static LinkedList<String> getLineas(BufferedReader br) {
		LinkedList<String> l = new LinkedList<>();
		try {
			String s = "";
			while ((s = br.readLine()) != null) {
				l.add(s);
			}
		} catch(IOException ioe) {
			System.err.println("ERROR: No se pudo leer el archivo.");
			System.exit(1);
		}
		return l;
	}

	private static boolean isNumber(String s) {
		return s.matches("\\d+");
	}
}