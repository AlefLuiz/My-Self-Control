package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Expense;
import model.User;
import view.StartMain;

public class addExpenseController {

	@FXML
	TextField description, value, installments;

	@FXML
	DatePicker startDate;

	User user;

	public addExpenseController() {
		user = ExpensesController.selectedUser;
	}

	public void save() {
		System.out.println(startDate.getValue().toString());
		if (!IsEmpty(description, value, installments)) {
			Expense expense = new Expense(Integer.parseInt(installments.getText()),
					Datas.returnFormattedDate(startDate.getValue().toString()), description.getText(),
					Double.parseDouble(value.getText()), user);
			DAO<Expense> dao = new DAO<>(Expense.class);
			dao.insertAttomic(expense);
			StartMain.refreshExpenses();
			cancel();
		} else {
			StartMain.popUpError("Há campos em branco!");
		}
	}

	public void cancel() {
		ExpensesController.closeWindow();
	}

	public boolean IsEmpty(TextField... fields) {
		for (TextField textField : fields) {
			if (textField.getText().isEmpty())
				return true;
		}
		return false;
	}
}
