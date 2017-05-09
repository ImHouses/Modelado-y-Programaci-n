package mx.unam.ciencias.modelado;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.collections.ObservableList;

public class Parte1 extends Application {

	private String[] datos;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane pane = new GridPane();
		TextField inputNombre = new TextField();
		TextField inputEdad = new TextField();
		TextField inputEstatura = new TextField();
		TextField inputPeso = new TextField();
		Button buttonIntro = new Button("Aceptar");
		ComboBox<String> comboBox = new ComboBox<>();
		/* ComboBox para el género. */
		comboBox.getItems().addAll("Hombre", "Mujer");
		/* Agregamos al panel el hint de introducir nombre y el TextField de nombre. */
		pane.add(new Label("Introduce tu nombre"),0,1);
		pane.add(inputNombre,20,1);
		/* Agregamos al panel el hint de edad y el TextField de edad. */
		pane.add(new Label("Introduce tu edad"),0,10);
		pane.add(inputEdad,20,10);
		/* Agregamos al panel el hint de introducir el género y el ComboBox. */
		pane.add(new Label("Introduce tu género"),0,20);
		pane.add(comboBox, 20, 20);
		/* Agregamos al panel el hint de la estatura y el TextField de la estatura. */
		pane.add(new Label("Introduce tu estatura en metros"),0,30);
		pane.add(inputEstatura, 20, 30);
		/* Agregamos al panel el hint del peso y el TextField del peso. */
		pane.add(new Label("Introduce tu peso"),0,40);
		pane.add(inputPeso, 20, 40);

		datos = new String[3];
		/* Agregamos el listener al botón. */
		buttonIntro.setOnAction((e) -> {
			datos[0] = inputEdad.getText();
			datos[1] = inputEstatura.getText();
			datos[2] = inputPeso.getText();
			Alert a;
			if (!datosValidos(datos)) {
				a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText(null);
				a.setContentText("Los datos son inválidos.");
				a.showAndWait();
			} else {
				String indice = String.format("%2.2f",
								imc(Double.parseDouble(datos[2]),
									Double.parseDouble(datos[1])));
				a = new Alert(AlertType.INFORMATION);
				a.setTitle("IMC");
				a.setHeaderText(null);
				a.setContentText("Tu índice de masa corporal es " + indice);
				a.showAndWait();
			}
		});

		/* Agregamos el botón de aceptar. */
		pane.add(buttonIntro, 0, 200);
		Scene scene = new Scene(pane,400,200);
		stage.setScene(scene);
		stage.setTitle("Práctica 8");
		stage.show();
	}

	/**
	* Regresa el índice de masa corporal.
	* @param peso El peso para el IMC.
	* @param estatura La estatura para el IMC.
	* @return el índice de masa corporal.
	*/
	private double imc(double peso, double estatura) {
		return peso / (estatura * estatura);
	}

	/**
	* Nos dice si los datos dados son válidos.
	* @return <code>true</code> si los datos son válidos, <code>false</code>
	* en otro caso.
	*/
	private boolean datosValidos(String[] datos) {
		int edad = 0;
		double estatura = 0.0;
		double peso = 0;
		try {
			edad = Integer.parseInt(datos[0]);
			estatura = Double.parseDouble(datos[1]);
			peso = Double.parseDouble(datos[2]);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}