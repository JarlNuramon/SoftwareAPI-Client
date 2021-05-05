package de.hs.bochum;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class View {
	private Stage primaryStage;
	private Model model;

	public View(Stage primaryStage, Model model) {
		this.primaryStage = primaryStage;
		this.model = model;
		initKomponenten();
	}

	private Pane pane = new Pane();
	private Label lblEingabe = new Label("Eingabe");
	private Label lblAnzeige = new Label("Anzeige");
	private Label lblGeburtstag = new Label("Geburtstag :");
	private Label lblGeburtsmonat = new Label("Geburtsmonat:");
	private Label lblGeburtsjahr = new Label("Geburtsjahr :");
	private TextField txtGeburtstag = new TextField();
	private TextField txtGeburtsmonat = new TextField();
	private TextField txtGeburtsjahr = new TextField();
	private TextArea txtAreaAnzeige = new TextArea();
	private Button btnEingabe = new Button("Eingabe");
	private Button btnAnzeige = new Button("Anzeige");
	// -------Ende Attribute der grafischen Oberflaeche-------

	private void initKomponenten() {
		Font font = new Font("Arial", 24);
		lblEingabe.setLayoutX(20);
		lblEingabe.setLayoutY(40);
		lblEingabe.setFont(font);
		lblEingabe.setStyle("-fx-font-weight: bold;");
		lblAnzeige.setLayoutX(310);
		lblAnzeige.setLayoutY(40);
		lblAnzeige.setFont(font);
		lblAnzeige.setStyle("-fx-font-weight: bold;");
		lblGeburtstag.setLayoutX(20);
		lblGeburtstag.setLayoutY(90);
		lblGeburtsmonat.setLayoutX(20);
		lblGeburtsmonat.setLayoutY(130);
		lblGeburtsjahr.setLayoutX(20);
		lblGeburtsjahr.setLayoutY(170);
		pane.getChildren().addAll(lblEingabe, lblAnzeige, lblGeburtstag, lblGeburtsmonat, lblGeburtsjahr);
		// Textfelder
		txtGeburtstag.setLayoutX(120);
		txtGeburtstag.setLayoutY(90);
		txtGeburtsmonat.setLayoutX(120);
		txtGeburtsmonat.setLayoutY(130);
		txtGeburtsjahr.setLayoutX(120);
		txtGeburtsjahr.setLayoutY(170);
		pane.getChildren().addAll(txtGeburtstag, txtGeburtsmonat, txtGeburtsjahr);
		// Textbereich
		txtAreaAnzeige.setEditable(false);
		txtAreaAnzeige.setLayoutX(310);
		txtAreaAnzeige.setLayoutY(90);
		txtAreaAnzeige.setMaxWidth(230);
		txtAreaAnzeige.setMaxHeight(100);
		pane.getChildren().add(txtAreaAnzeige);
		btnEingabe.setLayoutX(20);
		btnEingabe.setLayoutY(210);
		btnAnzeige.setLayoutX(310);
		btnAnzeige.setLayoutY(210);
		pane.getChildren().addAll(btnEingabe, btnAnzeige);
		Scene scene = new Scene(this.pane, 560, 260);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Verwaltung von Geburtsdaten");
		primaryStage.show();
	}

	private void zeigeInfoan() {
		txtAreaAnzeige.setText("");
	}

	private void zeigeInformationsfensterAn(String meldung) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setContentText(meldung);
		alert.showAndWait();
	}

	private void zeigeFehlermeldungAn(String meldung) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehlermeldung");
		alert.setContentText(meldung);
		alert.showAndWait();
	}

}
