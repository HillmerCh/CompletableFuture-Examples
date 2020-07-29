package co.hillmerch.payments;

public class Invoice {
	private String reference;
	private Double total;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Invoice{" +
				"reference='" + reference + '\'' +
				", total=" + total +
				'}';
	}
}
