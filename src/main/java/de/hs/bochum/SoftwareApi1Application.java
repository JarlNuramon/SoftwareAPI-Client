package de.hs.bochum;

import javafx.application.Application;
import javafx.stage.Stage;

public class SoftwareApi1Application extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			new Control(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
