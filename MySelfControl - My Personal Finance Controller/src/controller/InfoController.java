package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.User;
import view.StartMain;

public class InfoController {

	@FXML
	Label user, income, reserve, recreation, fixedexpense, expense, totalexpenses, total;

	@FXML
	ComboBox<User> userslist;

	@FXML
	PieChart graficoPizza;

	public void refreshUsers() {
		DAO<User> daoUser = new DAO<>(User.class);
		userslist.getItems().clear();
		userslist.getItems().addAll(daoUser.getAllList());
		userslist.setValue(null);
		user.setText("");
		income.setText("R$ ");
		reserve.setText("R$ ");
		recreation.setText("R$ ");
		fixedexpense.setText("R$ ");
		expense.setText("R$ ");
		totalexpenses.setText("R$ ");
		total.setText("R$ ");
		graficoPizza.getData().clear();
	}

	@FXML
	public void initialize() {
		refreshUsers();
	}

	public void buttonMenuUsers() throws IOException {
		StartMain.setScene("Users");
	}

	public void buttonMenuExpenses() throws IOException {
		StartMain.setScene("Expenses");
	}

	public void selectedUser() {
		if (userslist.getValue() != null) {
			DAO<User> dao = new DAO<>(User.class);
			User selectedUser = dao.getById(userslist.getValue().toString());
			user.setText(selectedUser.getName());
			selectedUser.refresh();
			income.setText("R$ " + selectedUser.getIncome());
			reserve.setText("R$ " + selectedUser.getReserve());
			recreation.setText("R$ " + selectedUser.getRecreation());
			fixedexpense.setText("R$ " + selectedUser.getTotalFixedExpenses());
			expense.setText("R$ " + selectedUser.getTotalInstallments());
			totalexpenses.setText("R$ " + (selectedUser.getTotalInstallments() + selectedUser.getTotalFixedExpenses()));
			total.setText("R$ " + (selectedUser.getTotalInstallments() + selectedUser.getTotalFixedExpenses()
					+ selectedUser.getReserve() + selectedUser.getRecreation()));
			graficoPizza.getData().clear();
			graficoPizza.getData().addAll(new PieChart.Data("Despesa Fixa", selectedUser.getTotalFixedExpenses()),
					new PieChart.Data("Reserva", selectedUser.getReserve()),
					new PieChart.Data("Despesas", selectedUser.getTotalInstallments()),
					new PieChart.Data("Lazer", selectedUser.getRecreation()));
		}
	}
}
