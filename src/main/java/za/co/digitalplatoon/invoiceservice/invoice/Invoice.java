package za.co.digitalplatoon.invoiceservice.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "INVOICE")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "CLIENT")
	private String client;
	@Column(name = "VAT_RATE")
	private Long vatRate;
	@Column(name = "INVOICE_DATE")
	private Date invoiceDate;
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
	private List<LineItem> lineItems;

	private transient BigDecimal subTotal;
	private transient BigDecimal vat;
	private transient BigDecimal total;

	public Invoice(Long id, String client, Long vatRate, Date invoiceDate) {
		this.id = id;
		this.client = client;
		this.vatRate = vatRate;
		this.invoiceDate = invoiceDate;
	}

	public Invoice() {

	}

	public Long getId() {
		return id;
	}

	public String getClient() {
		return client;
	}

	public Long getVatRate() {
		return vatRate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;

	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public BigDecimal getSubTotal() {
		subTotal = new BigDecimal(0);
		for (LineItem lineItem : lineItems) {
			subTotal = subTotal.add(lineItem.getLineItemTotal());
		}
		return subTotal.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVat() {
		vat = new BigDecimal(0);
		vat = getSubTotal().multiply(new BigDecimal(vatRate));
		return vat.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getTotal() {
		total = getSubTotal().add(getVat());
		return total.setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", client=" + client + ", vatRate=" + vatRate + ", invoiceDate=" + invoiceDate
				+ "]";
	}

}
