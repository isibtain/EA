package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.time.LocalDate;
import java.util.Date;


@Embeddable
public class Payment {

	@Temporal(TemporalType.DATE)
    @Column(name = "PAYDATE", nullable = false)
    private Date paydate;

    
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;
    

	public Payment() {
	}
	

	public Payment(Date paydate, Double amount) {
		this.paydate = paydate;
		this.amount = amount;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
    
    
    
}