package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.FixedExpense;
import model.User;
import view.StartMain;

public class addFixedExpenseController {

	@FXML
	TextField description, value;

	User user;

	public addFixedExpenseController() {
		user = ExpensesController.selectedUser;
	}
	
	public void save() {
		if (!IsEmpty(description, value)) {
			FixedExpense fixedExpense = new FixedExpense(description.getText(), Double.parseDouble(value.getText()),
					user);
			DAO<FixedExpense> dao = new DAO<>(FixedExpense.class);
			dao.insertAttomic(fixedExpense);
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
