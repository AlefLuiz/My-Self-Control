package controller;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Expense;
import model.FixedExpense;
import model.User;
import view.StartMain;

public class ExpensesController {

	@FXML
	private VBox expenses, fixedexpenses;

	@FXML
	private Button addFixedExpense;

	@FXML
	private ComboBox<User> userslist;

	@FXML
	private Label user, totalFixedExpenses, totalExpenses;

	public static FixedExpense fixedExpenseAtual = null;
	public static Expense expenseAtual = null;
	public static User selectedUser;

	public static Stage s1;

	public static boolean telaon;

	public void refreshUsers() throws IOException {
		DAO<User> daoUser = new DAO<>(User.class);
		userslist.getItems().clear();
		userslist.getItems().addAll(daoUser.getAllList());
		userslist.setValue(null);
		fixedexpenses.getChildren().clear();
		expenses.getChildren().clear();
		user.setText("");
	}

	public void refreshAllExpenses() throws IOException {
		DAO<User> dao = new DAO<>(User.class);
		selectedUser = dao.getById(userslist.getValue().toString());
		user.setText(selectedUser.getName());
		selectedUser.refresh();
		List<FixedExpense> FixedExpenses = selectedUser.getFixedExpenses();
		fixedexpenses.getChildren().clear();
		if (FixedExpenses != null) {
			for (FixedExpense fixedExpense : FixedExpenses) {
				fixedExpenseAtual = fixedExpense;
				VBox newFixedExpense = (VBox) FXMLLoader.load(getClass().getResource("/view/VBoxFixedExpense.fxml"));
				newFixedExpense.getStylesheets().add(getClass().getResource("/view/FixedExpense.css").toExternalForm());
				fixedexpenses.getChildren().add(newFixedExpense);
			}
			totalFixedExpenses.setText("R$ " + selectedUser.getTotalFixedExpenses());
		}
		List<Expense> Expenses = selectedUser.getExpenses();
		expenses.getChildren().clear();
		if (Expenses != null) {
			for (Expense expense : Expenses) {
				expenseAtual = expense;
				VBox newExpense = (VBox) FXMLLoader.load(getClass().getResource("/view/VBoxExpense.fxml"));
				newExpense.getStylesheets().add(getClass().getResource("/view/Expense.css").toExternalForm());
				expenses.getChildren().add(newExpense);
			}
			totalExpenses.setText("R$ " + selectedUser.getTotalInstallments());
		}
		StartMain.refreshInfo();
	}

	@FXML
	public void initialize() throws IOException {
		refreshUsers();
	}

	public void buttonMenuUsers() throws IOException {
		StartMain.setScene("Users");
	}

	public void buttonMenuInfo() throws IOException {
		StartMain.setScene("Info");
	}

	public void addFixedExpense() throws IOException {
		if (!telaon) {
			if (selectedUser == null) {
				StartMain.popUp("Escolha um usuário!");
			} else {
				s1 = new Stage();
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/addFixedExpense.fxml")));
				scene.getStylesheets().add(getClass().getResource("/view/addFixedExpense.css").toExternalForm());

				s1.initStyle(StageStyle.UNDECORATED);
				s1.setScene(scene);
				s1.show();

				telaon = true;
			}
		}
	}

	public void addExpense() throws IOException {
		if (!telaon) {
			if (selectedUser == null) {
				StartMain.popUp("Escolha um usuário!");
			} else {
				s1 = new Stage();
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/addExpense.fxml")));
				scene.getStylesheets().add(getClass().getResource("/view/addFixedExpense.css").toExternalForm());

				s1.initStyle(StageStyle.UNDECORATED);
				s1.setScene(scene);
				s1.show();

				telaon = true;
			}
		}
	}

	public static void closeWindow() {
		s1.close();
		telaon = false;
	}
}
