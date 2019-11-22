package za.co.digitalplatoon.invoiceservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.digitalplatoon.invoiceservice.invoice.Invoice;
import za.co.digitalplatoon.invoiceservice.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Override
	public Optional<Invoice> viewInvoice(Long id) {
		return invoiceRepo.findById(id);
	}

	@Override
	public List<Invoice> viewAllInvoices() {
		return invoiceRepo.findAll();
	}

	@Override
	public Optional<Invoice> addInvoice(Invoice invoice) {
		return Optional.of(invoiceRepo.save(invoice));

	}

}
