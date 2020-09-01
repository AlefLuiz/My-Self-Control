package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.FixedExpense;
import view.StartMain;

public class FixedExpenseController {

	@FXML
	private Label description, price;

	@FXML
	private Button removeButton;

	@FXML
	private VBox vbox;

	boolean isVisible = true;

	private FixedExpense me;

	public FixedExpenseController() {
		me = ExpensesController.fixedExpenseAtual;
	}

	public void remove() {
		DAO<FixedExpense> dao = new DAO<>(FixedExpense.class);
		dao.removeById(me.getId());
		StartMain.refreshExpenses();
	}

	public void change() {
		isVisible = !isVisible;
		removeButton.setVisible(isVisible);
		if (isVisible) {
			removeButton.setPrefHeight(25);
			vbox.setPrefHeight(61);
		} else {
			vbox.setPrefHeight(31);
			removeButton.setPrefHeight(0);
		}
	}

	@FXML
	public void initialize() {
		description.setText(me.getDesc());
		price.setText("R$ " + me.getValue());
		change();
	}
}
