package za.co.digitalplatoon.invoiceservice.invoice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "LINE_ITEM")
public class LineItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "QUANTITY")
	private Long quantity;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "UNIT_PRICE")
	private BigDecimal unitPrice;
	@JsonBackReference
    @JoinColumn(name = "invoice", referencedColumnName = "id")
    @ManyToOne(optional = false)
	private Invoice invoice;

	private transient BigDecimal lineItemTotal;

	public LineItem(Long id, Long quantity, String description, BigDecimal unitPrice) {
		this.id = id;
		this.quantity = quantity;
		this.description = description;
		this.unitPrice = unitPrice;
	}

	public LineItem() {

	}

	public Long getId() {
		return id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public BigDecimal getLineItemTotal() {
		lineItemTotal = new BigDecimal(quantity * unitPrice.doubleValue());
		return lineItemTotal.setScale(2, RoundingMode.HALF_UP);
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
		LineItem other = (LineItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LineItem [id=" + id + ", quantity=" + quantity + ", description=" + description + ", unitPrice="
				+ unitPrice + ", invoice=" + invoice + "]";
	}

}
