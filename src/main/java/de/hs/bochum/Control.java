package de.hs.bochum;

import javafx.stage.Stage;

public class Control {
	private View view;
	private Model model;

	public Control(Stage primaryStage) {
		this.model = Model.getInstance();
		this.view = new View(primaryStage, model);
		initListeners();
	}

	private void initListeners() {
		view.getBtnEingabe().setOnAction(aEvent -> {
			addData();
		});
		view.getBtnAnzeige().setOnAction(aEvent -> {
			showData();
		});
	}

	private void showData() {
		// TODO Auto-generated method stub

	}

	private void addData() {
		// TODO Auto-generated method stub

	}

}
