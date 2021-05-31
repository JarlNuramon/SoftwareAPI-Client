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
		Scene scene = new Scene(this.pane, 510, 240);
		primaryStage.setScene(scene);
		primaryStage.setTitle("EMU-Anwendung");
		initKomponenten();
		primaryStage.show();
	}



	private Pane pane = new Pane();
	private Label lblMessreihenId = new Label("MessreihenId");
	private Label lblLaufendeNummer = new Label("lfd. Nr. der Messung");
	private TextField txtMessreihenId = new TextField();
	private TextField txtLaufendeNummer = new TextField();
	private TextField txtAnzeige = new TextField();
	private Button btnLeseMessungenAusDB = new Button("Messungen aus DB lesen");
	private Button btnHoleMessungVonEMU = new Button("Messung aus EMU aufnehmen");
	private Button btnStarteMessreihe = new Button("Start: Messreihe aufnehmen");
	private Button btnStoppeMessreihe = new Button("Stopp: Messreihe aufnehmen");
	private void initKomponenten() {
		lblMessreihenId.setLayoutX(10);
		lblMessreihenId.setLayoutY(30);
		lblLaufendeNummer.setLayoutX(10);
		lblLaufendeNummer.setLayoutY(70);
		pane.getChildren().addAll(lblMessreihenId, lblLaufendeNummer); 
		// Textfelder
		txtMessreihenId.setLayoutX(140);
		txtMessreihenId.setLayoutY(30);
		txtLaufendeNummer.setLayoutX(140);
		txtLaufendeNummer.setLayoutY(70);
		txtAnzeige.setLayoutX(10);
		txtAnzeige.setLayoutY(110);
		txtAnzeige.setPrefWidth(480);
       	pane.getChildren().addAll(txtMessreihenId, txtLaufendeNummer, txtAnzeige);
     	// Buttons
       	btnLeseMessungenAusDB.setLayoutX(310);
       	btnLeseMessungenAusDB.setLayoutY(30);
       	btnLeseMessungenAusDB.setPrefWidth(180);
       	btnHoleMessungVonEMU.setLayoutX(310);
    	btnHoleMessungVonEMU.setLayoutY(70);
    	btnHoleMessungVonEMU.setPrefWidth(180);
        btnStarteMessreihe.setLayoutX(310);
        btnStarteMessreihe.setLayoutY(150);
        btnStarteMessreihe.setPrefWidth(180);
        btnStoppeMessreihe.setLayoutX(310);
        btnStoppeMessreihe.setLayoutY(190);
        btnStoppeMessreihe.setPrefWidth(180);
        pane.getChildren().addAll(
        	btnLeseMessungenAusDB, btnHoleMessungVonEMU,
        	btnStarteMessreihe, btnStoppeMessreihe); 
	}

	

	

}
