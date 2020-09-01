package controller;

import java.io.IOException;
import java.util.Arrays;

import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.User;
import view.StartMain;

public class UsersController {

	@FXML
	private TextField name, cpf, income, reserve, recreation;

	@FXML
	private ComboBox<User> userslist;

	@FXML
	private Button buttonSave, buttonRemove;



	public void Refresh() {
		DAO<User> dao = new DAO<>(User.class);
		userslist.getItems().clear();
		userslist.getItems().addAll(dao.getAllList());
	}

	public void AddUser() {
		Arrays.asList(name, cpf, income, reserve, recreation).forEach(field -> field.setText(""));
		cpf.setEditable(true);
		buttonSave.setText("SALVAR");
		buttonRemove.setDisable(true);
	}

	public void RemoveUser() {
		DAO<User> dao = new DAO<>(User.class);
		try {
			dao.removeById(cpf.getText());
		} catch (Exception e) {
			if (e.getCause().getCause().getCause() instanceof MySQLIntegrityConstraintViolationException) {
				StartMain.popUpError("Remova todas as despesas antes de deletar o usuário!");
				return;
			}
		}
		this.AddUser();
		Refresh();
		StartMain.popUp("Removido com sucesso!");
		StartMain.refreshInfo();
		StartMain.refreshExpenses();
	}

	public void SaveUser() {
		if (IsEmpty(name, cpf, income, reserve, recreation)) {
			StartMain.popUpError("Há campos em branco!");
		} else {
			DAO<User> dao = new DAO<>(User.class);
			User user = new User(cpf.getText(), name.getText(), Double.parseDouble(income.getText()),
					Double.parseDouble(reserve.getText()), Double.parseDouble(recreation.getText()));
			try {
				dao.insertAttomic(user);
				StartMain.popUp("Cadastrado com sucesso!");
			} catch (Exception e) {
				if (e.getCause().getCause() instanceof ConstraintViolationException) {
					dao.mergeAttomic(user);
					StartMain.popUp("Alterado com sucesso!");
				}
			}
			Refresh();
			StartMain.refreshInfo();
			StartMain.refreshExpenses();
		}
	}

	public void SelectUser() {
		if (userslist.getValue() != null) {
			DAO<User> dao = new DAO<>(User.class);
			User userSelected = dao.getById(userslist.getValue().toString());
			name.setText(userSelected.getName());
			cpf.setText(userSelected.getCPF());
			income.setText("" + userSelected.getIncome());
			reserve.setText("" + userSelected.getReserve());
			recreation.setText("" + userSelected.getRecreation());
			cpf.setEditable(false);
			buttonSave.setText("ALTERAR");
			buttonRemove.setDisable(false);
		}
	}

	public boolean IsEmpty(TextField... fields) {
		for (TextField textField : fields) {
			if (textField.getText().isEmpty())
				return true;
		}
		return false;
	}

	@FXML
	public void initialize() {
		Refresh();
	}

	public void buttonMenuExpenses() throws IOException {
		StartMain.setScene("Expenses");
	}

	public void buttonMenuInfo() throws IOException {
		StartMain.setScene("Info");
	}

}
