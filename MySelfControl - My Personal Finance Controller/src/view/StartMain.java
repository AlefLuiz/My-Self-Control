package view;


import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

import controller.DAO;
import controller.ExpensesController;
import controller.InfoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartMain extends Application {

	public static LinkedHashMap<String, String> css = new LinkedHashMap<>();
	public static LinkedHashMap<String, FXMLLoader> loaders = new LinkedHashMap<>();
	
	private static Stage stage;
	
	private static Scene cena1;
	private static Scene cena2;
	private static Scene cena3;

	@Override
	public void start(Stage primaryStage) throws IOException {
		stage = primaryStage;
			css.put("Expenses", getClass().getResource("Expenses.css").toExternalForm());
			css.put("Info", getClass().getResource("Info.css").toExternalForm());
			css.put("Users", getClass().getResource("Users.css").toExternalForm());
			loaders.put("Expenses", new FXMLLoader(getClass().getResource("Expenses.fxml")));
			loaders.put("Info", new FXMLLoader(getClass().getResource("Info.fxml")));
			loaders.put("Users", new FXMLLoader(getClass().getResource("Users.fxml")));
			
			cena1 = new Scene(loaders.get("Users").load(), 666, 625);
			cena2 = new Scene(loaders.get("Info").load(), 666, 625);
			cena3 = new Scene(loaders.get("Expenses").load(), 666, 625);
			cena1.getStylesheets().add(css.get("Users"));
			cena2.getStylesheets().add(css.get("Info"));
			cena3.getStylesheets().add(css.get("Expenses"));
			
			primaryStage.setScene(cena1);
			primaryStage.show();
			
	}

	public static void main(String[] args) {
		DAO.openConnection();
		launch(args);
	}

	public static void refreshExpenses() {
		ExpensesController controlador = StartMain.loaders.get("Expenses").getController();
		try {
			controlador.refreshUsers();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void refreshInfo() {
		InfoController controlador = StartMain.loaders.get("Info").getController();
		controlador.refreshUsers();
	}	
	public static void setScene(String scene) throws IOException {
		if (scene == "Users") {
			stage.setScene(cena1);
		}
		if (scene == "Info") {
			stage.setScene(cena2);
		}
		if (scene == "Expenses") {
			stage.setScene(cena3);
		}
	}
	
	public static void popUp(String message) {
		JOptionPane.showMessageDialog(null, message,"",JOptionPane.INFORMATION_MESSAGE);
	}
	public static void popUpError(String message) {
		JOptionPane.showMessageDialog(null, message,"",JOptionPane.ERROR_MESSAGE);
	}
}
