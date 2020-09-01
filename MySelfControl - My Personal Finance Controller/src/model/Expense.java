package model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import controller.Datas;

@Entity
@Table(name = "Expense")
public class Expense{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description,startDate;
	private double price;
	private int installments;
	
	@Transient
	private final DecimalFormat df = new DecimalFormat("#.00");
	
	@OneToOne
	private User user;
	
	public Expense() {
		
	}
	
	public Expense(int installments,String startDate,String desc, double price, User user) {
		super();
		this.description = desc;
		this.startDate = startDate;
		this.price = price;
		this.installments = installments;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public String getDate() {
		return startDate;
	}

	public void setDate(String startDate) {
		this.startDate = startDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double Price) {
		this.price = Price;
	}

	public int getInstallments() {
		return installments;
	}

	public void setInstallments(int Installments) {
		this.installments = Installments;
	}
	
	public int getInstallmentsRest() {
		return new Datas(startDate).calcFinalDate(installments);
	}
	
	public double getPartial() {
		return (new BigDecimal(price / installments).setScale(2, RoundingMode.HALF_EVEN)).doubleValue();
//		return Double.parseDouble(df.format(price / installments));
	}
	
	public double getPayOff() {
		return getPartial() * getInstallmentsRest();
	}

	@Override
	public String toString() {
		return "Installment [id=" + id + ", Description=" + description + ", Start Date=" + startDate + ", Price=" + price
				+ ", Installments=" + installments + ", Parcial: " + getPartial() + ", User=" + user.getCPF() + "]";
	}
}
