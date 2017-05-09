package mx.unam.ciencias.modelado;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.Optional;

public class Parte2 extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane gridPane = new GridPane();
		TextInputDialog inputDialog = new TextInputDialog();
		double[] promedios = new double[6];
		preguntarPromedios(promedios, inputDialog);
		Stage s1 = stage;
		Stage s2 = new Stage();
		s1.setTitle("Gráfica de barras");
		s2.setTitle("Información");
		llenaStageInformacion(s2, promedios);
		llenaRect(s1, promedios);
		s1.show();
		s2.show();
	}

	/**
	* Llena el stage que correspone al de la gráfica con rectángulos.
	* @param s El stage a llenar con rectángulos.
	* @param info Los promedios.
	*/
	private void llenaRect(Stage s, double[] info) {
		Canvas c = new Canvas(240,600);
		Group root = new Group();
		GraphicsContext gc = c.getGraphicsContext2D();
		Color[] colores = {Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.BROWN, Color.CYAN};
		for (int x = 0;x < 6; x++) {
			gc.setFill(colores[x]);
			for (int y = 10; (10 - (int)info[x]) - 1 != y; y--) {
				System.out.println(info[x]);
				gc.fillRect(x * 40, y * 60, 40, 60);
			}
			gc.setFill(Color.BLACK);
			gc.fillText(String.format("%d°", x + 1),x * 45, 600);	
		}

		root.getChildren().addAll(c);
		s.setScene(new Scene(root));
	}

	/**
	* Llena el stage que corresponde al de la información con los datos dados.
	* @param s El stage a llenar.
	* @param info El arreglo con el que se llenará el stage.
	*/
	private void llenaStageInformacion(Stage s, double[] info) {
		VBox vbox = new VBox(8);
		String[] filas = {"Primer grado " + info[0],
						"Segundo grado " + info[1],
						"Tercer grado " + info[2],
						"Cuarto grado " + info[3],
						"Quinto grado " + info[4],
						"Sexto grado " + info[5]};
		double promedio = promedio(info);
		vbox
			.getChildren()
			.addAll(new Label(filas[0]),
					new Label(filas[1]),
					new Label(filas[2]),
					new Label(filas[3]),
					new Label(filas[4]),
					new Label(filas[5]),
					new Label("Promedio de la escuela: " + promedio));
		Scene scene = new Scene(vbox, 400, 200);
		s.setScene(scene);
	}

	/** 
	* Calcula el promedio dado un arreglo con 6 doubles. 
	* @param info El arreglo con los 6 doubles.
	* @return El promedio de los 6 doubles.
	*/
	private double promedio(double[] info) {	
		return (info[0] + info[1] + info[2] + info[3] + info[4] + info[5]) / 6;
	}

	/* Pregunta los promedios a través de diálogos. */
	private void preguntarPromedios(double[] promedios, TextInputDialog inputDialog) {
		for (int i = 1;i <= promedios.length;i++) {
			String año = String.format("%d° grado.",i);
			inputDialog.setTitle("Promedio");
			inputDialog.setContentText("Introduce el promedio de los alumnos de " + año);
			Optional<String> result = inputDialog.showAndWait();
			Alert a;
			if (result.isPresent()) {
				try {
					promedios[i-1] = Double.parseDouble(inputDialog.getEditor().getText());
				} catch (NumberFormatException nfe) {
					a = new Alert(AlertType.ERROR);
					a.setTitle("Error de entrada");
					a.setContentText("Se deben introducir únicamente números");
					a.showAndWait();
					inputDialog.getEditor().setText("");
					i--;
					continue;
				}
				a = new Alert(AlertType.INFORMATION);
					a.setTitle("CORRECTO");
					a.setContentText("El promedio es " + inputDialog.getEditor().getText());
					a.showAndWait();
				inputDialog.getEditor().setText("");
			} else {
				ButtonType buttonOK = 
						new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
				ButtonType buttonNO = 
						new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
				Alert exitAlert = 
						new Alert(AlertType.CONFIRMATION,
								  "¿Quieres salir?",
								  buttonOK,
								  buttonNO);
				exitAlert.setTitle("¿Salir?");
				Optional<ButtonType> res = exitAlert.showAndWait();
				if (res.isPresent() && res.get() == buttonOK)
					System.exit(1);
				a = new Alert(AlertType.ERROR);
					a.setTitle("ERROR");
					a.setContentText("Debes introducir el promedio.");
					a.showAndWait();
				i--;
			}
		}
	}
}