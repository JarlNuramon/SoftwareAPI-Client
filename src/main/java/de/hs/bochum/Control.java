package de.hs.bochum;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import de.hs.bochum.buisness.messung.Messreihe;
import de.hs.bochum.buisness.messung.Messung;
import javafx.event.ActionEvent;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class Control implements Initializable {

	private static final String STRING_IS_INTEGER = "-?\\d+";
	private Model model;
	@FXML
	private TextField txtMessreihenId;
	@FXML
	private TextField txtZeitintervall;
	@FXML
	private TextField txtVerbraucher;
	@FXML
	private TextField txtMessgroesse;
	@FXML
	private Button btnStarteMessreihe;
	@FXML
	private Button btnStoppeMessreihe;
	@FXML
	private TableView<Messreihe> tblMessreihen;
	@FXML
	private TableColumn<Messreihe, Integer> clmnIdentnummer;
	@FXML
	private TableColumn<Messreihe, Integer> clmnZeitintervall;
	@FXML
	private TableColumn<Messreihe, String> clmnVerbraucher;
	@FXML
	private TableColumn<Messreihe, String> clmnMessgroesse;
	@FXML
	private TableColumn<Messreihe, String> clmnMessungen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Model.getInstance();
		try {
			initTable();
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@FXML
	public void speichereMessreihe(ActionEvent e) throws NumberFormatException, Exception {
		if (isInputValid()) {
			model.erstelleMessreihe(Integer.parseInt(txtMessreihenId.getText()),
					Integer.parseInt(txtZeitintervall.getText()), txtVerbraucher.getText(), txtMessgroesse.getText());
			leseMessreihe(e);
		}

	}

	@FXML
	public void stoppeMessreihe(ActionEvent e) {
		try {
			btnStarteMessreihe.setDisable(false);
			btnStoppeMessreihe.setDisable(true);
			model.stopMessung(tblMessreihen.getSelectionModel().getSelectedItems().get(0).getMessreihenId());
			leseMessreihe(e);
		} catch (Exception ex) {
			zeigeFehlermeldungAn(ex.getMessage());
		}
	}

	@FXML
	public void starteMessreihe(ActionEvent e) {
		try {
			btnStarteMessreihe.setDisable(true);
			btnStoppeMessreihe.setDisable(false);
			model.startMessung(tblMessreihen.getSelectionModel().getSelectedItems().get(0).getMessreihenId());
		} catch (Exception ex) {
			zeigeFehlermeldungAn(ex.getMessage());
		}
	}

	@FXML
	public void leseMessreihe(ActionEvent e) {
		try {
			showMessreihe(leseMessungenAusDemService());
		} catch (Exception ex) {
			zeigeFehlermeldungAn(ex.getMessage());
		}
	}

	@FXML
	public void onDeletePressed(KeyEvent e) {
		Messreihe selectedItem = tblMessreihen.getSelectionModel().getSelectedItem();
		if (selectedItem != null)
			if (e.getCode().equals(KeyCode.DELETE)) {
				try {
					loescheMessung(selectedItem.getMessreihenId());
				} catch (Exception e1) {
					zeigeFehlermeldungAn("Löschen war nicht möglich.");
					e1.printStackTrace();
				}
			}
	}

	public void zeigeFehlermeldungAn(String meldung) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehlermeldung");
		alert.setContentText(meldung);
		alert.showAndWait();
	}

	private void initTable() throws NumberFormatException, Exception {
		clmnIdentnummer.setCellValueFactory(new PropertyValueFactory<>("messreihenId"));
		clmnZeitintervall.setCellValueFactory(new PropertyValueFactory<>("zeitintervall"));
		clmnVerbraucher.setCellValueFactory(new PropertyValueFactory<>("verbraucher"));
		clmnMessgroesse.setCellValueFactory(new PropertyValueFactory<>("messgroesse"));
		clmnMessungen.setCellValueFactory(p -> new SimpleStringProperty(getMessungen(p.getValue().getElements())));
		tblMessreihen.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		try {
			showMessreihe(leseMessungenAusDemService());
		} catch (Exception e) {
			zeigeFehlermeldungAn(
					"Verbindung mit dem Server war nicht möglich. Die meisten Features werden nicht verfügbar sein.");
		}
	}

	private boolean isInputValid() {
		if (txtMessreihenId.getText() == null || !txtMessreihenId.getText().matches(STRING_IS_INTEGER)) {
			zeigeFehlermeldungAn("MessreihenID ist keine Zahl oder wurde nicht eingegeben");
			return false;
		}
		if (txtZeitintervall.getText() == null || !txtZeitintervall.getText().matches(STRING_IS_INTEGER)) {
			zeigeFehlermeldungAn("Zeitintervall ist keine Zahl oder wurde nicht eingegeben");
			return false;
		}
		if (txtVerbraucher.getText() == null) {
			zeigeFehlermeldungAn("Verbraucher wurde nicht eingegeben");
			return false;
		}
		if (txtMessgroesse.getText() == null) {
			zeigeFehlermeldungAn("Messgröße wurde nicht eingegeben");
			return false;
		}
		return true;
	}

	private String getMessungen(List<Messung> ergMessungen) {
		String erg = "";
		for (int i = 0; i < ergMessungen.size(); i++) {
			erg = erg + ergMessungen.get(i).gibAttributeAus() + " / ";
		}
		// view.getTxtAnzeige().setText(erg);
		return erg;
	}

	private List<Messreihe> leseMessungenAusDemService() throws NumberFormatException, Exception {
		return model.getMessungen();
	}

	private void showMessreihe(List<Messreihe> ergMessungen) {
		tblMessreihen.setItems(FXCollections.observableArrayList(ergMessungen));
	}

	private void loescheMessung(int id) throws Exception {
		model.loescheMessung(id);
		showMessreihe(leseMessungenAusDemService());
	}

}
