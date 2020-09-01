package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Expense;
import view.StartMain;

public class ExpenseController {

	@FXML
	private Label description, partial, installments, startDate, installmentsrest, price;

	@FXML
	private Button removeButton;

	@FXML
	private VBox vbox, vboxRest;

	boolean isVisible = true;

	private Expense me;

	public ExpenseController() {
		me = ExpensesController.expenseAtual;
	}

	public void remove() {
		DAO<Expense> dao = new DAO<>(Expense.class);
		dao.removeById(me.getId());
		StartMain.refreshExpenses();
	}

	public void change() {
		isVisible = !isVisible;
		vboxRest.setVisible(isVisible);
		if (isVisible) {
			vbox.setPrefHeight(125);
			vboxRest.setPrefHeight(87);
		} else {
			vbox.setPrefHeight(31);
			vboxRest.setPrefHeight(0);
		}
	}

	@FXML
	public void initialize() {
		description.setText(me.getDesc());
		price.setText("Resta R$ " + me.getPayOff());
		partial.setText("R$ " + me.getPartial());
		startDate.setText(me.getDate());
		installments.setText(me.getInstallments() + " Parcela(s)");
		installmentsrest.setText(me.getInstallmentsRest() + " Parcela(s) Restante(s)");
		change();
		if (me.getInstallmentsRest() <= 0) {
			remove();
		}
	}
}
