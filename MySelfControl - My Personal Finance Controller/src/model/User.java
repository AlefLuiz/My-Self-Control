package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import controller.DAO;

@Entity
@Table(name = "Users")
public class User{

	@Id
	private String CPF;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private double income, reserve, recreation;

	@Transient
	private List<Expense> Expenses;

	@Transient
	private List<FixedExpense> FixedExpenses;

	@Transient
	private double totalFixedExpenses;

	@Transient
	private double totalInstallments;
	
	@Transient
	private final DecimalFormat df = new DecimalFormat("#.00");

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		name = Name;
	}

	public String getCPF() {
		return CPF;
	}

	public List<Expense> getExpenses() {
		return Expenses;
	}

	public void setExpenses(List<Expense> list) {
		this.Expenses = list;
	}

	public List<FixedExpense> getFixedExpenses() {
		return FixedExpenses;
	}

	public void setFixedExpenses(List<FixedExpense> FixedExpenses) {
		this.FixedExpenses = FixedExpenses;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double Income) {
		income = Income;
	}

	public double getReserve() {
		return reserve;
	}

	public void setReserve(double Reserve) {
		reserve = Reserve;
	}

	public double getRecreation() {
		return recreation;
	}

	public void setRecreation(double Recreation) {
		recreation = Recreation;
	}

	public User() {

	}

	public User(String cpf, String Name, double Income, double Reserve, double Recreation) {
		CPF = cpf;
		name = Name;
		income = Income;
		reserve = Reserve;
		recreation = Recreation;
	}

	public double getTotalFixedExpenses() {
		return (new BigDecimal(totalFixedExpenses).setScale(2, RoundingMode.HALF_EVEN)).doubleValue();
	}

	public double getTotalInstallments() {
		return (new BigDecimal(totalInstallments).setScale(2, RoundingMode.HALF_EVEN)).doubleValue();
	}

	public void refresh() {
		DAO<FixedExpense> dao = new DAO<>(FixedExpense.class);
		setFixedExpenses(dao.getAllListByFilter("user_cpf = "+CPF));
		DAO<Expense> dao2 = new DAO<>(Expense.class);
		setExpenses(dao2.getAllListByFilter("user_cpf= "+CPF));
		totalInstallments = 0;
		for (Expense despesa : Expenses) {
			totalInstallments += despesa.getPartial();
		}
		totalFixedExpenses = 0;
		for (FixedExpense despesa : FixedExpenses) {
			totalFixedExpenses += despesa.getValue();
		}
	}

	@Override
	public String toString() {
		return CPF;
	}
}
