package co.hillmerch.payments;

import java.time.LocalDate;

public class Payment {

	private Invoice invoice;
	private LocalDate dateOfPayment;
	private Double value;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public LocalDate getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(LocalDate dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Payment{" +
				"invoice=" + invoice +
				", dateOfPayment=" + dateOfPayment +
				", value=" + value +
				'}';
	}
}
