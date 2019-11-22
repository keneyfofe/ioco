package za.co.digitalplatoon.invoiceservice.service;

import java.util.List;
import java.util.Optional;

import za.co.digitalplatoon.invoiceservice.invoice.Invoice;

public interface InvoiceService {

	Optional<Invoice> viewInvoice(Long id);

	List<Invoice> viewAllInvoices();

	Optional<Invoice> addInvoice(Invoice invoice);

}
