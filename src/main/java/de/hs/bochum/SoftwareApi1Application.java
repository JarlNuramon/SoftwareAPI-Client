package de.hs.bochum;

import java.net.URI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class SoftwareApi1Application extends Application {
	 public static void main(String[] args) {
	        launch(args);
	    }

	@Override
	public void start(Stage primaryStage) {
		try {
			 Parent root = FXMLLoader.load(this.getClass().getResource("/de/hs/bochum/BasisView.fxml"));
		        primaryStage.setScene(new Scene(root));
		        primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
