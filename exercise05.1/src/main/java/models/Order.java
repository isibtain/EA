package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orderrrs")
public class Order {
	
	@Id
	@GeneratedValue
	private long id;
	private Date date;
	@ManyToOne
	@JoinColumn(name = "customer_idd")
	private Customer owner;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn(name = "orderIDD")
	private List<OrderLine> orderLines = new ArrayList();
	
	
	public Order(Date date, List<OrderLine> orderLines) {
		this.date = date;
		this.orderLines = orderLines;
	}

	public Order() {
	}
	
	public Order(Date date, Customer owner, List<OrderLine> orderLines) {
		
		this.date = date;
		this.owner = owner;
		this.orderLines = orderLines;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Customer getOwner() {
		return owner;
	}
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
}
